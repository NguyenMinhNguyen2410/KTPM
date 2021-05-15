/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import BUS.Tool;
import DTO.TaiKhoanDTO;
import EXT.FormChon;
import EXT.FormContent;
import EXT.MyTable;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Nguyen
 */
public class GUITaiKhoan extends FormContent {

    public static String[] header = {"Tài khoản", "Mã nhân viên", "Mã quyền", "Mật khẩu"};
    private final JLabel label_TaiKhoan[] = new JLabel[header.length];
    private JTextField txt_TaiKhoan_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_TaiKhoan_Sua[] = new JTextField[header.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private TaiKhoanBUS BUS = new TaiKhoanBUS();
    private JButton themNhanVien, suaNhanVien,themPhanQuyen,suaPhanQuyen;

    public GUITaiKhoan() {
        super();
    }

    //Hàm tạo Dialog thêm công thức
    @Override
    protected void Them_click() {
        super.Them_click();
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm tài khoản");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_TaiKhoan[i] = new JLabel(header[i]);
            label_TaiKhoan[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_TaiKhoan[i]);

            txt_TaiKhoan_Them[i] = new JTextField();
            txt_TaiKhoan_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            if(i==1)
            {
                themNhanVien=new JButton();
                themNhanVien.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/them1-30.png")));
                themNhanVien.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                themNhanVien.addActionListener((ae) -> {
                    btnChonNhanVien(txt_TaiKhoan_Them[1]);
                });
                themNhanVien.setBounds(355, y, 30, 30);
                Them_Frame.add(themNhanVien);
            }
            if(i==2)
            {
                themPhanQuyen=new JButton();
                themPhanQuyen.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/them1-30.png")));
                themPhanQuyen.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                themPhanQuyen.addActionListener((ae) -> {
                    btnChonQuyen(txt_TaiKhoan_Them[2]);
                });
                themPhanQuyen.setBounds(355, y, 30, 30);
                Them_Frame.add(themPhanQuyen);
            }
            y += 40;
            Them_Frame.add(txt_TaiKhoan_Them[i]);
        }
        txt_TaiKhoan_Them[2].setEditable(false);
        txt_TaiKhoan_Them[1].setEditable(false);
        Them_Frame.setVisible(true);

    }
    @Override
    protected void luuThem_Frame(){
        cohieu = 1;
                int a = op.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if(checkTextThem(txt_TaiKhoan_Them[1].getText(),
                            txt_TaiKhoan_Them[2].getText(),
                            txt_TaiKhoan_Them[3].getText()))
                    {
                        TaiKhoanDTO DTO = new TaiKhoanDTO(txt_TaiKhoan_Them[0].getText(),
                            txt_TaiKhoan_Them[1].getText(),
                            txt_TaiKhoan_Them[2].getText(),
                            txt_TaiKhoan_Them[3].getText(),
                            "Hiện");

                    BUS.them(DTO); //thêm công thức bên BUS đã có thêm vào database
                    table.addRow(DTO);
                    

                    Them_Frame.dispose();
                    }
                    

                }else
                    cohieu = 0;
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearThem_Frame(){
        //clear textfield trong Them
        for (int i = 0; i < header.length; i++) {
            txt_TaiKhoan_Them[i].setText("");
        }
    }
    //Hàm tạo Dialog sửa món ăn
    @Override
    protected void Sua_click() {
        super.Sua_click();
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            op.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
        //Tạo tiêu đề
        JLabel Title = new JLabel("Sửa tài khoản");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua_Frame.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < header.length; i++) {
            label_TaiKhoan[i] = new JLabel(header[i]);
            label_TaiKhoan[i].setBounds(100, y, 100, 30);
            Sua_Frame.add(label_TaiKhoan[i]);
            txt_TaiKhoan_Sua[i] = new JTextField();
            txt_TaiKhoan_Sua[i].setBounds(200, y, 150, 30);

            if(i==2)
            {
                suaPhanQuyen=new JButton();
                suaPhanQuyen.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
                suaPhanQuyen.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                suaPhanQuyen.addActionListener((ae) -> {
                    btnChonQuyen(txt_TaiKhoan_Sua[2]);
                });
                suaPhanQuyen.setBounds(355, y, 30, 30);
                Sua_Frame.add(suaPhanQuyen);
            }
            y += 40;
            Sua_Frame.add(txt_TaiKhoan_Sua[i]);
        }
        
        txt_TaiKhoan_Sua[2].setEditable(false);
        txt_TaiKhoan_Sua[1].setEditable(false);
        txt_TaiKhoan_Sua[0].setEditable(false);
            //Set tự động giá trị các field
            for (int j = 0; j < header.length; j++) {
                txt_TaiKhoan_Sua[j].setText(table.tb.getValueAt(row, j).toString());
            }
            Sua_Frame.setVisible(true);
        }
    }
    @Override
    protected void luuSua_Frame(){
        cohieu = 1;
                int a = op.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if(checkTextSua(txt_TaiKhoan_Sua[1].getText(),
                            txt_TaiKhoan_Sua[2].getText(),
                            txt_TaiKhoan_Sua[3].getText()))
                    {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                    int row = table.tb.getSelectedRow();
                    int colum = table.tb.getSelectedColumn();
                    String maTaiKhoan = table.tbModel.getValueAt(row, colum).toString();
                    //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
            //        int option = op.showConfirmDialog(Sua_Frame, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
            //        if (option == JOptionPane.YES_OPTION) {
                        //Sửa dữ liệu trên bảng
                        //model là ruột JTable   
                        //set tự động giá trị cho model
                        for (int j = 0; j < header.length; j++) {
                            table.tbModel.setValueAt(txt_TaiKhoan_Sua[j].getText(), row, j);
                        }

                        table.tb.setModel(table.tbModel);

                        //Sửa dữ liệu trong database và arraylist trên bus
                        //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
                        TaiKhoanDTO DTO = new TaiKhoanDTO(txt_TaiKhoan_Sua[0].getText(),
                                txt_TaiKhoan_Sua[1].getText(),
                                txt_TaiKhoan_Sua[2].getText(),
                                txt_TaiKhoan_Sua[3].getText());
                        //Tìm vị trí của row cần sửa
                        int index = TaiKhoanBUS.timViTri(maTaiKhoan);
                        //Truyền dữ liệu và vị trí vào bus
                        BUS.sua(DTO, index);
            //        }

                    Sua_Frame.dispose();
                    }
                    
                }else
                    cohieu = 0;
    }
    //Tạo hàm này dùng để clear các textfield trong Sua_Frame
    @Override
    protected void clearSua_Frame(){
        //clear textfield trong Them
        for (int i = 0; i < header.length; i++) {
            txt_TaiKhoan_Sua[i].setText("");
        }
    }


    @Override
    public void docDB() {
        table.setHeaders(header);
        if (TaiKhoanBUS.ds == null) {
            try {
                BUS.docDB();
            } catch (Exception ex) {
                Logger.getLogger(GUITaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (TaiKhoanDTO monAnDTO : TaiKhoanBUS.ds) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table.addRow(monAnDTO);

            }
        }
    }

    @Override
    protected JPanel TimKiem() {
        JPanel TimKiem = new JPanel(null);

        JLabel lbsearch = new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x = 400;
        cbSearch = new JComboBox<>(header);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);

        search = new JTextField();
        search.setBorder(new TitledBorder(header[0]));
        search.setBounds(155, 20, 150, 40);
        lbsearch.add(search);
        addDocumentListener(search);

        cbSearch.addActionListener((ActionEvent e) -> {
            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            search.requestFocus();

        });
        lbsearch.setBounds(x, 0, 315, 70);
        TimKiem.add(lbsearch);

        return TimKiem;
    }

    private void addDocumentListener(JTextField tx) { // để cho hàm tìm kiếm
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        tx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }
        });
    }
    
    public void txtSearchOnChange() {
        //Đẩy dữ liệu đi và nhận lại danh sách đúng với field tìm kiếm
        setDataToTable(Tool.searchTK(search.getText(),cbSearch.getSelectedItem().toString()), table); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<TaiKhoanDTO> taiKhoanDTO, MyTable myTable) {
        myTable.clear();
        for (TaiKhoanDTO taiKhoan : taiKhoanDTO) {
            table.addRow(taiKhoan);
        }
    }

    @Override
    protected void XuatExcel_click() {
        new XuatExcel().xuatFileExcelTaiKhoan();

    }

    @Override
    protected void NhapExcel_click() {
        new DocExcel().docFileExcelTaiKhoan();

    }

    public boolean checkTextThem(String maNhanVien, String maQuyen, String matKhau) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (maNhanVien.equals("")
                || maQuyen.equals("")
                || matKhau.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (Tool.isDuplicateMaNhanVien(maNhanVien)) {
            op.showMessageDialog(null, "Nhân viên này đã có tài khoản rồi");
            txt_TaiKhoan_Them[1].requestFocus();
        } else if (!Tool.isName((matKhau))) {
            op.showMessageDialog(null, "Mật khẩu không được chứa ký tự đặc biệt và dấu");
            txt_TaiKhoan_Them[3].requestFocus();
        } else if (!Tool.isLength50(matKhau)) {
            op.showMessageDialog(null, "Mật khẩu không được quá 50 ký tự");
            txt_TaiKhoan_Them[3].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    public boolean checkTextSua(String maNhanVien, String maQuyen, String matKhau) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (maNhanVien.equals("")
                || maQuyen.equals("")
                || matKhau.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }else if(!TaiKhoanBUS.checkChucVuVaMaQuyen(NhanVienBUS.getChucVuTuMaNhanVien(maNhanVien),maQuyen))
                {
                    //xuất chức vụ
                    System.out.println(NhanVienBUS.getChucVuTuMaNhanVien(maNhanVien));
                    //xuất 
                    System.out.println(maQuyen);
                    op.showMessageDialog(null, "Chức vụ của nhân viên không được phép có quyền này");
                }
        else if (!Tool.isName((matKhau))) {
            op.showMessageDialog(null, "Mật khẩu không được chứa ký tự đặc biệt và dấu");
            txt_TaiKhoan_Sua[3].requestFocus();
        } else if (!Tool.isLength50(matKhau)) {
            op.showMessageDialog(null, "Mật khẩu không được quá 50 ký tự");
            txt_TaiKhoan_Sua[3].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    private void btnChonNhanVien(JTextField textField){
        cohieu=1;
                    try {
                        formchon = new FormChon(textField,"Nhân viên");
                    } catch (Exception ex) {
                        Logger.getLogger(GUIBanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cohieu=0;
    }
    
    private void btnChonQuyen(JTextField textField){
            cohieu=1;
                    try {
                        formchon = new FormChon(textField,"Phân quyền");
                    } catch (Exception ex) {
                        Logger.getLogger(GUIBanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    formchon.addWindowListener(new WindowAdapter(){
                        @Override
                     public void windowClosed(WindowEvent e){
                            cohieu=0;
                        }
 
                    });
    }
    @Override
    protected void ChiTiet(){
        
    }
    @Override
    protected void Xoa(){
        
    }
    @Override
    protected void Them(){
        
    }
    @Override
    protected void InPDF(){
        
    }
    @Override
    protected void LamMoi_click(){
        super.LamMoi_click();
        search.setText("");
    }

    public JTextField getSearch() {
        return search;
    }

    public JComboBox getCbSearch() {
        return cbSearch;
    }
    
}

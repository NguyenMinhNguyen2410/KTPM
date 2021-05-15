/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhaCungCapBUS;
import BUS.Tool;
import DTO.NhaCungCapDTO;
import EXT.FormContent;
import EXT.MyTable;
import Excel.DocExcel;
import Excel.XuatExcel;
import EXT.FormContent;
import EXT.MyTable;
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
public class GUINhaCungCap extends FormContent {

    public static String[] header = {"Mã nhà cung cấp", "Tên", "SĐT", "Gmail", "Địa chỉ"};
    private final JLabel label_NhaCungCap[] = new JLabel[header.length];
    private JTextField txt_NhaCungCap_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_NhaCungCap_Sua[] = new JTextField[header.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private final NhaCungCapBUS BUS = new NhaCungCapBUS();

    public GUINhaCungCap() {
        super();
    }

    //Hàm tạo Dialog thêm nhà cung cấp
    @Override
    protected void Them_click() {
        super.Them_click();
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm nhà cung cấp");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_NhaCungCap[i] = new JLabel(header[i]);
            label_NhaCungCap[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_NhaCungCap[i]);

            txt_NhaCungCap_Them[i] = new JTextField();
            txt_NhaCungCap_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 

            y += 40;
            Them_Frame.add(txt_NhaCungCap_Them[i]);
        }
        String ma = Tool.tangMa3(NhaCungCapBUS.getMaNhaCungCapCuoi()); //tăng mã
        txt_NhaCungCap_Them[0].setText(ma); //set mã
        txt_NhaCungCap_Them[0].setEditable(false);
        Them_Frame.setVisible(true);

    }
    @Override
    protected void luuThem_Frame(){
        cohieu = 1;
                int a = op.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    
                    if(checkTextThem(txt_NhaCungCap_Them[1].getText(),
                            txt_NhaCungCap_Them[2].getText(),
                            txt_NhaCungCap_Them[3].getText(),
                            txt_NhaCungCap_Them[4].getText()))
                    {
                        NhaCungCapDTO DTO = new NhaCungCapDTO(txt_NhaCungCap_Them[0].getText(),
                            txt_NhaCungCap_Them[1].getText(),
                            txt_NhaCungCap_Them[2].getText(),
                            txt_NhaCungCap_Them[3].getText(),
                            txt_NhaCungCap_Them[4].getText(),
                            "Hiện");

                    BUS.them(DTO); //thêm nhà cung cấp bên BUS đã có thêm vào database
                    table.addRow(DTO);
                    clearThem_Frame();
                    
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
            txt_NhaCungCap_Them[i].setText("");
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
        JLabel Title = new JLabel("Sửa nhà cung cấp");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua_Frame.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < header.length; i++) {
            label_NhaCungCap[i] = new JLabel(header[i]);
            label_NhaCungCap[i].setBounds(100, y, 100, 30);
            Sua_Frame.add(label_NhaCungCap[i]);
            txt_NhaCungCap_Sua[i] = new JTextField();
            txt_NhaCungCap_Sua[i].setBounds(200, y, 150, 30);

            y += 40;
            Sua_Frame.add(txt_NhaCungCap_Sua[i]);
        }
        txt_NhaCungCap_Sua[0].setEditable(false);
        //Set tự động giá trị các field
            for (int j = 0; j < header.length; j++) {
                txt_NhaCungCap_Sua[j].setText(table.tb.getValueAt(row, j).toString());
            }
            Sua_Frame.setVisible(true);
        }
    }
    @Override
    protected void luuSua_Frame(){
        cohieu = 1;
                int a = op.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if(checkTextSua(txt_NhaCungCap_Sua[1].getText(), 
                            txt_NhaCungCap_Sua[2].getText(),
                            txt_NhaCungCap_Sua[3].getText(),
                            txt_NhaCungCap_Sua[4].getText()))
                    {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                    int row = table.tb.getSelectedRow();
        int colum = table.tb.getSelectedColumn();
        String maNhaCungCap = table.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = op.showConfirmDialog(Sua_Frame, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < header.length; j++) {
                table.tbModel.setValueAt(txt_NhaCungCap_Sua[j].getText(), row, j);
            }

            table.tb.setModel(table.tbModel);

            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            NhaCungCapDTO DTO = new NhaCungCapDTO(txt_NhaCungCap_Sua[0].getText(),
                    txt_NhaCungCap_Sua[1].getText(),
                    txt_NhaCungCap_Sua[2].getText(),
                    txt_NhaCungCap_Sua[3].getText(),
                    txt_NhaCungCap_Sua[4].getText());
            //Tìm vị trí của row cần sửa
            int index = NhaCungCapBUS.timViTri(maNhaCungCap);
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
        //clear textfield trong Sua_Frame
        for (int i = 0; i < header.length; i++) {
            txt_NhaCungCap_Sua[i].setText("");
        }
    }
    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click() {
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            op.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = op.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maNhaCungCap = table.tbModel.getValueAt(row, 0).toString();
                //truyền mã nhà cung cấp vào hàm timViTri ở NhaCungCapBUS 
                int index = NhaCungCapBUS.timViTri(maNhaCungCap);
                //Xóa hàng ở table
                table.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maNhaCungCap, index);
            }
        }

    }

    @Override
    public void docDB() {
        table.setHeaders(header);
        if (NhaCungCapBUS.ds == null) {
            try {
                BUS.docDB();
            } catch (Exception ex) {
                Logger.getLogger(GUINhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (NhaCungCapDTO monAnDTO : NhaCungCapBUS.ds) {
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

    @Override
    protected void XuatExcel_click() {
        new XuatExcel().xuatFileExcelNhaCungCap();

    }

    @Override
    protected void NhapExcel_click() {
        new DocExcel().docFileExcelNhaCungCap();

    }

    public boolean checkTextThem(String tenNhaCungCap, String soDienThoai, String gmail, String diaChi) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenNhaCungCap.equals("")
                || soDienThoai.equals("")
                || gmail.equals("")
                || diaChi.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(tenNhaCungCap))) {
            op.showMessageDialog(null, "Tên nhà cung cấp không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Them[1].requestFocus();
        } else if (!Tool.isLength50(tenNhaCungCap)) {
            op.showMessageDialog(null, "Tên nhà cung cấp không được quá 50 ký tự");
            txt_NhaCungCap_Them[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            op.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Them[2].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            op.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_NhaCungCap_Them[2].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            op.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_NhaCungCap_Them[2].requestFocus();
        } else if (!Tool.isGmail(gmail)) {
            op.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_NhaCungCap_Them[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(diaChi))) {
            op.showMessageDialog(null, "Địa chỉ không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Them[4].requestFocus();
        } else if (!Tool.isLength50(diaChi)) {
            op.showMessageDialog(null, "Địa chỉ không được quá 50 ký tự");
            txt_NhaCungCap_Them[4].requestFocus();
        } else {
            return true;

        }
        return false;
    }

    public boolean checkTextSua(String tenNhaCungCap, String soDienThoai, String gmail, String diaChi) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenNhaCungCap.equals("")
                || soDienThoai.equals("")
                || gmail.equals("")
                || diaChi.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(tenNhaCungCap))) {
            op.showMessageDialog(null, "Tên nhà cung cấp không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Sua[1].requestFocus();
        } else if (!Tool.isLength50(tenNhaCungCap)) {
            op.showMessageDialog(null, "Tên nhà cung cấp không được quá 50 ký tự");
            txt_NhaCungCap_Sua[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            op.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Sua[2].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            op.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_NhaCungCap_Sua[2].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            op.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_NhaCungCap_Sua[2].requestFocus();
        } else if (!Tool.isGmail(gmail)) {
            op.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_NhaCungCap_Sua[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(diaChi))) {
            op.showMessageDialog(null, "Địa chỉ không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Sua[4].requestFocus();
        } else if (!Tool.isLength50(diaChi)) {
            op.showMessageDialog(null, "Địa chỉ không được quá 50 ký tự");
            txt_NhaCungCap_Sua[4].requestFocus();
        } else {
            return true;

        }
        return false;
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
        setDataToTable(Tool.searchNCC(search.getText(),cbSearch.getSelectedItem().toString()), table); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<NhaCungCapDTO> nhaCungCapDTO, MyTable myTable) {
        myTable.clear();
        for (NhaCungCapDTO nhaCungCap : nhaCungCapDTO) {
            table.addRow(nhaCungCap);
        }
    }
    @Override
    protected void LamMoi_click(){
        super.LamMoi_click();
        search.setText("");
    }
    @Override
    protected void InPDF(){
        
    }
   @Override
    protected void ChiTiet(){
        
    }

    public JTextField getSearch() {
        return search;
    }

    public JComboBox getCbSearch() {
        return cbSearch;
    }
    
}

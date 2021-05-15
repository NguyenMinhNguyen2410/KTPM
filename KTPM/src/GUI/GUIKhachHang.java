/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.KhachHangBUS;
import BUS.Tool;
import DTO.KhachHangDTO;
import EXT.FormContent;
import EXT.MyTable;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.awt.Component;
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
public class GUIKhachHang extends FormContent {

    public static String[] header = {"Mã khách hàng", "Họ", "Tên", "Gmail", "Giới tính", "SĐT", "Tổng chi tiêu"};
    private final JLabel label_KhachHang[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_KhachHang_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_KhachHang_Sua[] = new JTextField[header.length];
    private JTextField search;
    private JComboBox cbSearch;
    private final KhachHangBUS BUS = new KhachHangBUS();
    private JTextField Ten,Tu_ChiTieu,Den_ChiTieu;
    private JComboBox cbGioiTinh_Them,cbGioiTinh_Sua;
    private String array_GioiTinh[]={"Nam","Nữ"};
    public GUIKhachHang() {
        super();
    }
    
    @Override
    protected void Them_click() {
        super.Them_click();
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm khách hàng");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_KhachHang[i] = new JLabel(header[i]);
            label_KhachHang[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_KhachHang[i]);
            //Tạo combobox
            if(i==4)
            {
                cbGioiTinh_Them=new JComboBox(array_GioiTinh);
                cbGioiTinh_Them.setBounds(200, y, 150, 30);
                Them_Frame.add(cbGioiTinh_Them);
                y+=40;
                continue;
            }
            txt_KhachHang_Them[i] = new JTextField();
            txt_KhachHang_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 

            y += 40;
            Them_Frame.add(txt_KhachHang_Them[i]);
        }
        txt_KhachHang_Them[6].setText("0");
        txt_KhachHang_Them[6].setEditable(false);
        
        String ma = Tool.tangMa(KhachHangBUS.getMaKhachHangCuoi());
        txt_KhachHang_Them[0].setText(ma);
        txt_KhachHang_Them[0].setEditable(false);
        Them_Frame.setVisible(true);

    }
    @Override
    protected void luuThem_Frame(){
        cohieu =1;
                int a = op.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if(checkTextThem(txt_KhachHang_Them[1].getText(),
                            txt_KhachHang_Them[2].getText(),
                            txt_KhachHang_Them[3].getText(), 
                            cbGioiTinh_Them.getSelectedItem().toString(),
                            txt_KhachHang_Them[5].getText(),
                            txt_KhachHang_Them[6].getText()))
                    {
                        KhachHangDTO DTO = new KhachHangDTO(txt_KhachHang_Them[0].getText(),
                            txt_KhachHang_Them[1].getText(),
                            txt_KhachHang_Them[2].getText(),
                            txt_KhachHang_Them[3].getText(),
                            cbGioiTinh_Them.getSelectedItem().toString(),
                            txt_KhachHang_Them[5].getText(),
                            Float.parseFloat(txt_KhachHang_Them[6].getText()),
                            "Hiện");

                    BUS.them(DTO); //thêm khách hàng bên BUS đã có thêm vào database
                    table.addRow(DTO);
                    //clear textfield trong Them
                    for (int i = 0; i < header.length; i++) {
                        if(i!=4)
                        txt_KhachHang_Them[i].setText("");
                    }
                    //Tắt cờ hiệu đi 
                    
                    Them_Frame.dispose();
                    }
                    

                }else
                    cohieu=0;
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearThem_Frame(){
        for (int i = 0; i < header.length; i++) {
            if(i!=4)
            txt_KhachHang_Them[i].setText("");
        }
    }
    @Override
    protected void Sua_click() {
        super.Sua_click();
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            op.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Tạo tiêu đề
            JLabel Title = new JLabel("Sửa khách hàng");
            Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
            Title.setForeground(Color.decode("#FF4081"));
            Title.setBounds(150, 0, 200, 40);
            Sua_Frame.add(Title);
            int y = 50;
            //Tạo tự động các lable và textfield
            for (int i = 0; i < header.length; i++) {
                label_KhachHang[i] = new JLabel(header[i]);
                label_KhachHang[i].setBounds(100, y, 100, 30);
                Sua_Frame.add(label_KhachHang[i]);
                //Tạo combobox
                if(i==4)
                {
                    cbGioiTinh_Sua=new JComboBox(array_GioiTinh);
                    cbGioiTinh_Sua.setBounds(200, y, 150, 30);
                    Sua_Frame.add(cbGioiTinh_Sua);
                    y+=40;
                    continue;
                }
                txt_KhachHang_Sua[i] = new JTextField();
                txt_KhachHang_Sua[i].setBounds(200, y, 150, 30);

                y += 40;
                Sua_Frame.add(txt_KhachHang_Sua[i]);
            }
            txt_KhachHang_Sua[0].setEditable(false);
            txt_KhachHang_Sua[6].setEditable(false);
            //Set tự động giá trị các field
            for (int j = 0; j < header.length; j++) {
               if(j!=4)
                    txt_KhachHang_Sua[j].setText(table.tb.getValueAt(row, j).toString());
                else if(j==4)
                {
                    int k;
                    for(k=0;k<array_GioiTinh.length;k++)
                        if(table.tb.getValueAt(row, j).toString().equals(array_GioiTinh[k]))
                            cbGioiTinh_Sua.setSelectedIndex(k);
                }
            }
            Sua_Frame.setVisible(true);
        }
    }
    @Override
    protected void luuSua_Frame(){
        cohieu =1;
                int a = op.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if(checkTextSua(txt_KhachHang_Sua[1].getText(),
                            txt_KhachHang_Sua[2].getText(),
                            txt_KhachHang_Sua[3].getText(),
                            cbGioiTinh_Sua.getSelectedItem().toString(),
                            txt_KhachHang_Sua[5].getText(),
                            txt_KhachHang_Sua[6].getText()))
                    {
        int row = table.tb.getSelectedRow();
        int colum = table.tb.getSelectedColumn();
        String maKhachHang = table.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = op.showConfirmDialog(Sua_Frame, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < header.length; j++) {
                if(j!=4)
                    table.tbModel.setValueAt(txt_KhachHang_Sua[j].getText(), row, j);
                else if(j==4)
                    table.tbModel.setValueAt(cbGioiTinh_Sua.getSelectedItem().toString(), row, j);
            }

            table.tb.setModel(table.tbModel);

            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            KhachHangDTO DTO = new KhachHangDTO(txt_KhachHang_Sua[0].getText(),
                    txt_KhachHang_Sua[1].getText(),
                    txt_KhachHang_Sua[2].getText(),
                    txt_KhachHang_Sua[3].getText(),
                    cbGioiTinh_Sua.getSelectedItem().toString(),
                    txt_KhachHang_Sua[5].getText(),
                    Float.parseFloat(txt_KhachHang_Sua[6].getText()));
            //Tìm vị trí của row cần sửa
            int index = KhachHangBUS.timViTri(maKhachHang);
            //Truyền dữ liệu và vị trí vào bus
            BUS.sua(DTO, index);
//        }
                    Sua_Frame.dispose();
                    }
                    
                }else
                    cohieu =0;
    }
    //Tạo hàm này dùng để clear các textfield trong Sua_Frame
    @Override
    protected void clearSua_Frame(){
        for (int i = 0; i < header.length; i++) {
            if(i!=4)
            txt_KhachHang_Sua[i].setText("");
        }
    }
    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click() {
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            op.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
//            op=new JOptionPane("Vui lòng chọn hàng muốn xóa");
//            op.setVisible(true);
        } else {
            int option = op.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maKhachHang = table.tbModel.getValueAt(row, 0).toString();
                //truyền mã khách hàng vào hàm timViTri ở KhachHangBUS 
                int index = KhachHangBUS.timViTri(maKhachHang);
                //Xóa hàng ở table
                table.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maKhachHang, index);
            }
        }

    }

    @Override
    public void docDB() {
        table.setHeaders(header);
        if (KhachHangBUS.ds == null) {

            try {
                BUS.docDB();
            } catch (Exception ex) {
                Logger.getLogger(GUIMonAn.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        for (KhachHangDTO KhachHang : KhachHangBUS.ds) {
            if (KhachHang.getTrangThai().equals("Hiện")) {
                table.addRow(KhachHang);

            }
        }

    }

    @Override
    protected JPanel TimKiem() {
        JPanel TimKiem=new JPanel(null);
        
        JLabel lbTen=new JLabel("");
        lbTen.setBorder(new TitledBorder("Tìm kiếm"));
        int x=200;
        cbSearch = new JComboBox<>(new String[]{"Mã khách hàng","Họ","Tên","Gmail","Giới tính","SĐT"});
        cbSearch.setBounds(5, 20, 100, 40);
        lbTen.add(cbSearch);
        
        Ten=new JTextField();
        Ten.setBorder(new TitledBorder("Mã khách hàng"));
        Ten.setBounds(105, 20, 150, 40);
        lbTen.add(Ten);
        addDocumentListener(Ten);
        
        cbSearch.addActionListener((ActionEvent e) -> {
            Ten.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            Ten.requestFocus();
            
        });
        lbTen.setBounds(x, 0, 265, 70);
        TimKiem.add(lbTen);

        
        JLabel chiTieu=new JLabel("");
        chiTieu.setBorder(new TitledBorder("Chi tiêu"));
                        
        Tu_ChiTieu=new JTextField();
        Tu_ChiTieu.setBorder(new TitledBorder("Từ"));
        Tu_ChiTieu.setBounds(5, 20, 100, 40);
        chiTieu.add(Tu_ChiTieu);
        addDocumentListener(Tu_ChiTieu);
        
        
        Den_ChiTieu=new JTextField();
        Den_ChiTieu.setBorder(new TitledBorder("Đến"));
        Den_ChiTieu.setBounds(105, 20, 100, 40);
        chiTieu.add(Den_ChiTieu);
        addDocumentListener(Den_ChiTieu);
       

        chiTieu.setBounds(x+=285, 0, 215, 70);
        TimKiem.add(chiTieu);
              
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
        double donGia1= -1, donGia2 = -1;
        //Ràng buộc
        try {
            donGia1 = Double.parseDouble(Tu_ChiTieu.getText());
            Tu_ChiTieu.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Tu_ChiTieu.setForeground(Color.red);
        }

        try {
            donGia2 = Double.parseDouble(Den_ChiTieu.getText());
            Den_ChiTieu.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Den_ChiTieu.setForeground(Color.red);
        }
        
        //Đẩy dữ liệu đi và nhận lại danh sách đúng với field tìm kiếm
        setDataToTable(Tool.searchKH(Ten.getText(),donGia1, donGia2, cbSearch.getSelectedItem().toString()), table); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<KhachHangDTO> khachHangDTO, MyTable myTable) {
        myTable.clear();
        for (KhachHangDTO khuyenMai : khachHangDTO) {
            table.addRow(khuyenMai);
        }
    }

    @Override
    protected void XuatExcel_click() {
        new XuatExcel().xuatFileExcelKhachHang();

    }

    @Override
    protected void NhapExcel_click() {
        new DocExcel().docFileExcelKhachHang();

    }

    public boolean checkTextThem(String hoKhachHang, String tenKhachHang, String gmail, String gioiTinh, String soDienThoai, String tongChiTieu) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (hoKhachHang.equals("")
                || tenKhachHang.equals("")
                || gmail.equals("")
                || gioiTinh.equals("")
                || soDienThoai.equals("")
                || tongChiTieu.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(hoKhachHang))) {
            op.showMessageDialog(null, "Họ khách hàng không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[1].requestFocus();
        } else if (!Tool.isLength50(hoKhachHang)) {
            op.showMessageDialog(null, "Họ khách hàng không được quá 50 ký tự");
            txt_KhachHang_Them[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(tenKhachHang))) {
            op.showMessageDialog(null, "Tên khách hàng không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[2].requestFocus();
        } else if (!Tool.isLength50(tenKhachHang)) {
            op.showMessageDialog(null, "Tên khách hàng không được quá 50 ký tự");
            txt_KhachHang_Them[2].requestFocus();
        }else if (Tool.haveSpace(tenKhachHang.trim())) {
            op.showMessageDialog(null, "Tên khách hàng không được quá 2 từ");
            txt_KhachHang_Them[2].requestFocus();
        }
        else if (!Tool.isGmail(gmail)) {
            op.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_KhachHang_Them[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(gioiTinh))) {
            op.showMessageDialog(null, "Giới tính không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[4].requestFocus();
        } else if (!Tool.isLength50(gioiTinh)) {
            op.showMessageDialog(null, "Giới tính không được quá 50 ký tự");
            txt_KhachHang_Them[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            op.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[5].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            op.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_KhachHang_Them[5].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            op.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_KhachHang_Them[5].requestFocus();
        } else if (!Tool.isTongTien(Tool.removeAccent(tongChiTieu))) {
            op.showMessageDialog(null, "Tổng chi tiêu không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[6].requestFocus();
        } else if (!Tool.isNumber(tongChiTieu)) {
            op.showMessageDialog(null, "Tổng chi tiêu phải là số nguyên dương");
            txt_KhachHang_Them[6].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    public boolean checkTextSua(String hoKhachHang, String tenKhachHang, String gmail, String gioiTinh, String soDienThoai, String tongChiTieu) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (hoKhachHang.equals("")
                || tenKhachHang.equals("")
                || gmail.equals("")
                || gioiTinh.equals("")
                || soDienThoai.equals("")
                || tongChiTieu.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(hoKhachHang))) {
            op.showMessageDialog(null, "Họ khách hàng không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[1].requestFocus();
        } else if (!Tool.isLength50(hoKhachHang)) {
            op.showMessageDialog(null, "Họ khách hàng không được quá 50 ký tự");
            txt_KhachHang_Sua[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(tenKhachHang))) {
            op.showMessageDialog(null, "Tên khách hàng không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[2].requestFocus();
        } else if (!Tool.isLength50(tenKhachHang)) {
            op.showMessageDialog(null, "Tên khách hàng không được quá 50 ký tự");
            txt_KhachHang_Sua[2].requestFocus();
        }else if (Tool.haveSpace(tenKhachHang.trim())) {
            op.showMessageDialog(null, "Tên khách hàng không được quá 2 từ");
            txt_KhachHang_Sua[2].requestFocus();
        } 
        else if (!Tool.isGmail(gmail)) {
            op.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_KhachHang_Sua[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(gioiTinh))) {
            op.showMessageDialog(null, "Giới tính không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[4].requestFocus();
        } else if (!Tool.isLength50(gioiTinh)) {
            op.showMessageDialog(null, "Giới tính không được quá 50 ký tự");
            txt_KhachHang_Sua[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            op.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[5].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            op.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_KhachHang_Sua[5].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            op.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_KhachHang_Sua[5].requestFocus();
        } else if (!Tool.isTongTien(Tool.removeAccent(tongChiTieu))) {
            op.showMessageDialog(null, "Tổng chi tiêu không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[6].requestFocus();
        } else if (!Tool.isNumber(tongChiTieu)) {
            op.showMessageDialog(null, "Tổng chi tiêu phải là số nguyên dương");
            txt_KhachHang_Sua[6].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    @Override
    protected void LamMoi_click(){
        super.LamMoi_click();
        Ten.setText("");
    }
    @Override
    protected void InPDF(){
        
    }
   @Override
    protected void ChiTiet(){
        
    }

    public JComboBox getCbSearch() {
        return cbSearch;
    }

    public JTextField getTen() {
        return Ten;
    }
    
}

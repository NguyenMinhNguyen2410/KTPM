/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import BUS.Tool;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
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
public class GUINhanVien extends FormContent {

    public static String[] header = {"Mã nhân viên", "Họ", "Tên", "Gmail", "Giới tính", "SĐT", "Chức vụ"};
    private final JLabel label_NhanVien[] = new JLabel[header.length];
    private JTextField txt_NhanVien_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_NhanVien_Sua[] = new JTextField[header.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private final NhanVienBUS BUS = new NhanVienBUS();
    private JComboBox cbGioiTinh_Them,cbGioiTinh_Sua;
    private String array_GioiTinh[]={"Nam","Nữ"};
    private JComboBox cbChucVu_Them,cbChucVu_Sua;
    private String array_ChucVu[]={"Nhân viên","Quản lý","Giám đốc"};
    public GUINhanVien() {
        super();
    }

    //Hàm tạo Dialog thêm nhân viên
    @Override
    protected void Them_click(MouseEvent evt) {
        super.Them_click(evt);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm nhân viên");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_NhanVien[i] = new JLabel(header[i]);
            label_NhanVien[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_NhanVien[i]);
            //Tạo combobox
            if(i==4)
            {
                cbGioiTinh_Them=new JComboBox(array_GioiTinh);
                cbGioiTinh_Them.setBounds(200, y, 150, 30);
                Them_Frame.add(cbGioiTinh_Them);
                y+=40;
                continue;
            }
            //Tạo combobox
            if(i==6)
            {
                cbChucVu_Them=new JComboBox(array_ChucVu);
                cbChucVu_Them.setBounds(200, y, 150, 30);
                Them_Frame.add(cbChucVu_Them);
                y+=40;
                continue;
            }
            txt_NhanVien_Them[i] = new JTextField();
            txt_NhanVien_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 

            y += 40;
            Them_Frame.add(txt_NhanVien_Them[i]);
        }
        String ma = Tool.tangMa(NhanVienBUS.getMaNhanVienCuoi()); //lấy mã tự động
        txt_NhanVien_Them[0].setText(ma); //set mã lên textfield
        txt_NhanVien_Them[0].setEditable(false);
        Them_Frame.setVisible(true);

    }
    @Override
     protected void luuThem_Frame(){
        cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if (checkTextThem(txt_NhanVien_Them[1].getText(),
                            txt_NhanVien_Them[2].getText(),
                            txt_NhanVien_Them[3].getText(),
                            cbGioiTinh_Them.getSelectedItem().toString(),
                            txt_NhanVien_Them[5].getText(),
                            cbChucVu_Them.getSelectedItem().toString())) {
                        NhanVienDTO DTO = new NhanVienDTO(txt_NhanVien_Them[0].getText(),
                                txt_NhanVien_Them[1].getText(),
                                txt_NhanVien_Them[2].getText(),
                                txt_NhanVien_Them[3].getText(),
                                cbGioiTinh_Them.getSelectedItem().toString(),
                                txt_NhanVien_Them[5].getText(),
                                cbChucVu_Them.getSelectedItem().toString(),
                                "Hiện");

                        BUS.them(DTO); //thêm nhân viên bên BUS đã có thêm vào database
                        table.addRow(DTO);
                        //Thêm tài khoản tự động
                        String tenTaiKhoan = Tool.removeAccent(txt_NhanVien_Them[1].getText().trim().replaceAll(" ", ""))
                                + Tool.removeAccent(txt_NhanVien_Them[2].getText().trim().replaceAll(" ", ""));
                        TaiKhoanDTO tk = new TaiKhoanDTO(tenTaiKhoan.trim(),
                                txt_NhanVien_Them[0].getText(),
                                traVeMaQuyenTuChucVu(cbChucVu_Them.getSelectedItem().toString()),
                                "123456",
                                "Hiện");
                        TaiKhoanBUS tkBUS = new TaiKhoanBUS();
                        tkBUS.them(tk);
                        clearThem_Frame();
                        Them_Frame.dispose();
                    }

                } else {
                    cohieu = 0;
                }
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearThem_Frame(){
        //clear textfield trong Them
        for (int i = 0; i < header.length; i++) {
            if(i!=4&&i!=6)
            txt_NhanVien_Them[i].setText("");
        }
    }
    //Hàm tạo Dialog sửa món ăn
   @Override
    protected void Sua_click(MouseEvent evt) {
        super.Sua_click(evt);
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Tạo tiêu đề
            JLabel Title = new JLabel("Sửa nhân viên");
            Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
            Title.setForeground(Color.decode("#FF4081"));
            Title.setBounds(150, 0, 200, 40);
            Sua_Frame.add(Title);
            int y = 50;
            //Tạo tự động các lable và textfield
            for (int i = 0; i < header.length; i++) {
                label_NhanVien[i] = new JLabel(header[i]);
                label_NhanVien[i].setBounds(100, y, 100, 30);
                Sua_Frame.add(label_NhanVien[i]);
                //Tạo combobox
                if(i==4)
                {
                    cbGioiTinh_Sua=new JComboBox(array_GioiTinh);
                    cbGioiTinh_Sua.setBounds(200, y, 150, 30);
                    Sua_Frame.add(cbGioiTinh_Sua);
                    y+=40;
                    continue;
                }
                //Tạo combobox
                if(i==6)
                {
                    cbChucVu_Sua=new JComboBox(array_ChucVu);
                    cbChucVu_Sua.setBounds(200, y, 150, 30);
                    Sua_Frame.add(cbChucVu_Sua);
                    y+=40;
                    continue;
                }
                txt_NhanVien_Sua[i] = new JTextField();
                txt_NhanVien_Sua[i].setBounds(200, y, 150, 30);

                y += 40;
                Sua_Frame.add(txt_NhanVien_Sua[i]);
            }
            txt_NhanVien_Sua[0].setEditable(false);
            //Set tự động giá trị các field
            for (int j = 0; j < header.length; j++) {
                if(j!=4&&j!=6)
                    txt_NhanVien_Sua[j].setText(table.tb.getValueAt(row, j).toString());
                else if(j==4)
                {
                    int k;
                    for(k=0;k<array_GioiTinh.length;k++)
                        if(table.tb.getValueAt(row, j).toString().equals(array_GioiTinh[k]))
                            cbGioiTinh_Sua.setSelectedIndex(k);
                }
                else if(j==6)
                {
                    int k;
                    for(k=0;k<array_ChucVu.length;k++)
                        if(table.tb.getValueAt(row, j).toString().equals(array_ChucVu[k]))
                            cbChucVu_Sua.setSelectedIndex(k);
                }
            }
            Sua_Frame.setVisible(true);
        }
    }
    @Override
    protected void luuSua_Frame(){
        //tắt cờ hiệu
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if (checkTextSua(txt_NhanVien_Sua[1].getText(),
                            txt_NhanVien_Sua[2].getText(),
                            txt_NhanVien_Sua[3].getText(),
                            cbGioiTinh_Sua.getSelectedItem().toString(),
                            txt_NhanVien_Sua[5].getText(),
                            cbChucVu_Sua.getSelectedItem().toString())) {
                        int row = table.tb.getSelectedRow();
                        int colum = table.tb.getSelectedColumn();
                        String maNhanVien = table.tbModel.getValueAt(row, colum).toString();

                        //Sửa dữ liệu trên bảng
                        //model là ruột JTable   
                        //set tự động giá trị cho model
                        for (int j = 0; j < header.length; j++) {
                            if(j!=4&&j!=6)
                                    table.tbModel.setValueAt(txt_NhanVien_Sua[j].getText(), row, j);
                                else if(j==4)
                                    table.tbModel.setValueAt(cbGioiTinh_Sua.getSelectedItem().toString(), row, j);
                                else if(j==6)
                                    table.tbModel.setValueAt(cbChucVu_Sua.getSelectedItem().toString(), row, j);
                        }

                        table.tb.setModel(table.tbModel);

                        //Sửa dữ liệu trong database và arraylist trên bus
                        //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
                        NhanVienDTO DTO = new NhanVienDTO(txt_NhanVien_Sua[0].getText(),
                                txt_NhanVien_Sua[1].getText(),
                                txt_NhanVien_Sua[2].getText(),
                                txt_NhanVien_Sua[3].getText(),
                                cbGioiTinh_Sua.getSelectedItem().toString(),
                                txt_NhanVien_Sua[5].getText(),
                                cbChucVu_Sua.getSelectedItem().toString());
                        //Tìm vị trí của row cần sửa
                        int index = NhanVienBUS.timViTri(maNhanVien);
                        //Truyền dữ liệu và vị trí vào bus
                        BUS.sua(DTO, index);
                //        }
                        clearSua_Frame();
                        Sua_Frame.dispose();
                    }
                    
                } else //bật cờ hiệu
                {
                    cohieu = 0;
                }
    }
    //Tạo hàm này dùng để clear các textfield trong Sua_Frame
    @Override
    protected void clearSua_Frame(){
        //clear textfield trong Them
        for (int i = 0; i < header.length; i++) {
            if(i!=4&&i!=6)
            txt_NhanVien_Sua[i].setText("");
        }
    }

    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maNhanVien = table.tbModel.getValueAt(row, 0).toString();
                //truyền mã nhân viên vào hàm timViTri ở NhanVienBUS 
                int index = NhanVienBUS.timViTri(maNhanVien);
                //Xóa hàng ở table
                table.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maNhanVien, index);
            }
        }

    }
    @Override
    public void docDB() {
        table.setHeaders(header);
        if (NhanVienBUS.ds == null) {
            try {
                BUS.docDSNV();
            } catch (Exception ex) {
                Logger.getLogger(GUINhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (NhanVienDTO NhanVienDTO : NhanVienBUS.ds) {
            if (NhanVienDTO.getTrangThai().equals("Hiện")) {
                table.addRow(NhanVienDTO);

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
//        search.addActionListener((ActionEvent e) -> {
//            if (!search.getText().equals("")) {
//                txtSearchOnChange();
//            }
//        });
        cbSearch.addActionListener((ActionEvent e) -> {
            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            search.requestFocus();
//            if (!txTim.getText().equals("")) {
//                txSearchOnChange();
//            }
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
        table.clear();
        ArrayList<NhanVienDTO> arraylist = Tool.searchNV(search.getText(), cbSearch.getSelectedItem().toString());
        for (NhanVienDTO DTO : arraylist) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table.addRow(DTO);

            }
        }
    }

    @Override
    protected void XuatExcel_click(MouseEvent evt) {
        new XuatExcel().xuatFileExcelNhanVien();

    }

    @Override
    protected void NhapExcel_click(MouseEvent evt) {
        new DocExcel().docFileExcelNhanVien();

    }

    public boolean checkTextThem(String hoNhanVien, String tenNhanVien, String gmail, String gioiTinh, String soDienThoai, String chucVu) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (hoNhanVien.equals("")
                || tenNhanVien.equals("")
                || gmail.equals("")
                || gioiTinh.equals("")
                || soDienThoai.equals("")
                || chucVu.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(hoNhanVien))) {
            JOptionPane.showMessageDialog(null, "Họ nhân viên không được chứa ký tự đặc biệt");
            txt_NhanVien_Them[1].requestFocus();
        } else if (!Tool.isLength50(hoNhanVien)) {
            JOptionPane.showMessageDialog(null, "Họ nhân viên không được quá 50 ký tự");
            txt_NhanVien_Them[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(tenNhanVien))) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được chứa ký tự đặc biệt");
            txt_NhanVien_Them[2].requestFocus();
        } else if (!Tool.isLength50(tenNhanVien)) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được quá 50 ký tự");
            txt_NhanVien_Them[2].requestFocus();
        } else if (Tool.haveSpace(tenNhanVien.trim())) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được quá 2 từ");
            txt_NhanVien_Them[2].requestFocus();
        } else if (!Tool.isGmail(gmail)) {
            JOptionPane.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_NhanVien_Them[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(gioiTinh))) {
            JOptionPane.showMessageDialog(null, "Giới tính không được chứa ký tự đặc biệt");
            txt_NhanVien_Them[4].requestFocus();
        } else if (!Tool.isLength50(gioiTinh)) {
            JOptionPane.showMessageDialog(null, "Giới tính không được quá 50 ký tự");
            txt_NhanVien_Them[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_NhanVien_Them[5].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_NhanVien_Them[5].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_NhanVien_Them[5].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(chucVu))) {
            JOptionPane.showMessageDialog(null, "Chức vụ không được chứa ký tự đặc biệt");
            txt_NhanVien_Them[6].requestFocus();
        } else if (!Tool.isLength50(chucVu)) {
            JOptionPane.showMessageDialog(null, "Chức vụ không được quá 50 ký tự");
            txt_NhanVien_Them[6].requestFocus();
        } else {
            return true;
        }
        return false;
    }

    public boolean checkTextSua(String hoNhanVien, String tenNhanVien, String gmail, String gioiTinh, String soDienThoai, String chucVu) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (hoNhanVien.equals("")
                || tenNhanVien.equals("")
                || gmail.equals("")
                || gioiTinh.equals("")
                || soDienThoai.equals("")
                || chucVu.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(hoNhanVien))) {
            JOptionPane.showMessageDialog(null, "Họ nhân viên không được chứa ký tự đặc biệt");
            txt_NhanVien_Sua[1].requestFocus();
        } else if (!Tool.isLength50(hoNhanVien)) {
            JOptionPane.showMessageDialog(null, "Họ nhân viên không được quá 50 ký tự");
            txt_NhanVien_Sua[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(tenNhanVien))) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được chứa ký tự đặc biệt");
            txt_NhanVien_Sua[2].requestFocus();
        } else if (!Tool.isLength50(tenNhanVien)) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được quá 50 ký tự");
            txt_NhanVien_Sua[2].requestFocus();
        } else if (Tool.haveSpace(tenNhanVien.trim())) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được quá 2 từ");
            txt_NhanVien_Sua[2].requestFocus();
        } else if (!Tool.isGmail(gmail)) {
            JOptionPane.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_NhanVien_Sua[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(gioiTinh))) {
            JOptionPane.showMessageDialog(null, "Giới tính không được chứa ký tự đặc biệt");
            txt_NhanVien_Sua[4].requestFocus();
        } else if (!Tool.isLength50(gioiTinh)) {
            JOptionPane.showMessageDialog(null, "Giới tính không được quá 50 ký tự");
            txt_NhanVien_Sua[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_NhanVien_Sua[5].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_NhanVien_Sua[5].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_NhanVien_Sua[5].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(chucVu))) {
            JOptionPane.showMessageDialog(null, "Chức vụ không được chứa ký tự đặc biệt");
            txt_NhanVien_Sua[6].requestFocus();
        } else if (!Tool.isLength50(chucVu)) {
            JOptionPane.showMessageDialog(null, "Chức vụ không được quá 50 ký tự");
            txt_NhanVien_Sua[6].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    public String traVeMaQuyenTuChucVu(String chucVu)
    {
        if(chucVu.equals("Giám đốc"))
        {
            return "PQ0";
        }
        if(chucVu.equals("Quản lý"))
        {
            return "PQ1";
        }
        if(chucVu.equals("Nhân viên"))
        {
            return "PQ4";
        }
        return null;
    }
    @Override
    protected void LamMoi_click(MouseEvent evt){
        super.LamMoi_click(evt);
        search.setText("");
    }
    @Override
    protected void InPDF(){
        
    }
   @Override
    protected void ChiTiet(){
        
    }
}




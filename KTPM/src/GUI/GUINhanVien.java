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
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
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
public class GUINhanVien extends GUIFormContent {

    public static String array_NhanVien[] = {"Mã nhân viên", "Họ", "Tên", "Gmail", "Giới tính", "SĐT", "Chức vụ"};
    public GUIMyTable table_NhanVien;
    private static JDialog Them_NhanVien;
    private static JDialog Sua;
    private final JLabel label_NhanVien[] = new JLabel[array_NhanVien.length];
    private JTextField txt_NhanVien_Them[] = new JTextField[array_NhanVien.length];
    //Phần textfield của sửa
    private JTextField txt_NhanVien_Sua[] = new JTextField[array_NhanVien.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private NhanVienBUS BUS = new NhanVienBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;
    private JComboBox cbGioiTinh_Them,cbGioiTinh_Sua;
    private String array_GioiTinh[]={"Nam","Nữ"};
    private JComboBox cbChucVu_Them,cbChucVu_Sua;
    private String array_ChucVu[]={"Nhân viên","Quản lý","Giám đốc"};
    public GUINhanVien() {
        super();
    }

    @Override
    //Tạo Panel chưa Table
    protected JPanel Table() {
        JPanel panel = new JPanel(null);
        //Tạo đối tượng cho table_NhanVien
        table_NhanVien = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_NhanVien.setHeaders(array_NhanVien);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_NhanVien.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
        table_NhanVien.setBounds(0, 0, GUImenu.width_content, 600);
        panel.add(table_NhanVien);

        return panel;
    }

    //Hàm tạo Dialog thêm nhân viên
    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Them_NhanVien = new JDialog(f);
        Them_NhanVien.setLayout(null);
        Them_NhanVien.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_NhanVien.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_NhanVien.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm nhân viên");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_NhanVien.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_NhanVien.length; i++) {
            label_NhanVien[i] = new JLabel(array_NhanVien[i]);
            label_NhanVien[i].setBounds(100, y, 100, 30);
            Them_NhanVien.add(label_NhanVien[i]);
            //Tạo combobox
            if(i==4)
            {
                cbGioiTinh_Them=new JComboBox(array_GioiTinh);
                cbGioiTinh_Them.setBounds(200, y, 150, 30);
                Them_NhanVien.add(cbGioiTinh_Them);
                y+=40;
                continue;
            }
            //Tạo combobox
            if(i==6)
            {
                cbChucVu_Them=new JComboBox(array_ChucVu);
                cbChucVu_Them.setBounds(200, y, 150, 30);
                Them_NhanVien.add(cbChucVu_Them);
                y+=40;
                continue;
            }
            txt_NhanVien_Them[i] = new JTextField();
            txt_NhanVien_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 

            y += 40;
            Them_NhanVien.add(txt_NhanVien_Them[i]);
        }
        //Tạo nút lưu
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        //Sự kiện khi click
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Them_NhanVien, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
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
                        table_NhanVien.addRow(DTO);
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
                        //clear textfield trong Them
                        for (int i = 0; i < array_NhanVien.length; i++) {
                            if(i!=4&&i!=6)
                            txt_NhanVien_Them[i].setText("");
                        }
                        Them_NhanVien.dispose();
                    }

                } else {
                    cohieu = 0;
                }
            }
        });
        Them_NhanVien.add(Luu);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear textfield trong Them
                for (int i = 0; i < array_NhanVien.length; i++) {
                    if(i!=4&&i!=6)
                    txt_NhanVien_Them[i].setText("");
                }
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them_NhanVien.dispose();
            }
        });

        Them_NhanVien.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_NhanVien.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });

        String ma = Tool.tangMa(NhanVienBUS.getMaNhanVienCuoi()); //lấy mã tự động
        txt_NhanVien_Them[0].setText(ma); //set mã lên textfield
        txt_NhanVien_Them[0].setEditable(false);
        Them_NhanVien.setVisible(true);

    }

    //Hàm tạo Dialog sửa món ăn
    private void Sua_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Sua = new JDialog(f);
        Sua.setLayout(null);
        Sua.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Sua.setLocationRelativeTo(null);
        Sua.setUndecorated(true);
        //Tạo tiêu đề
        JLabel Title = new JLabel("Sửa nhân viên");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_NhanVien.length; i++) {
            label_NhanVien[i] = new JLabel(array_NhanVien[i]);
            label_NhanVien[i].setBounds(100, y, 100, 30);
            Sua.add(label_NhanVien[i]);
            //Tạo combobox
            if(i==4)
            {
                cbGioiTinh_Sua=new JComboBox(array_GioiTinh);
                cbGioiTinh_Sua.setBounds(200, y, 150, 30);
                Sua.add(cbGioiTinh_Sua);
                y+=40;
                continue;
            }
            //Tạo combobox
            if(i==6)
            {
                cbChucVu_Sua=new JComboBox(array_ChucVu);
                cbChucVu_Sua.setBounds(200, y, 150, 30);
                Sua.add(cbChucVu_Sua);
                y+=40;
                continue;
            }
            txt_NhanVien_Sua[i] = new JTextField();
            txt_NhanVien_Sua[i].setBounds(200, y, 150, 30);

            y += 40;
            Sua.add(txt_NhanVien_Sua[i]);
        }
        //Lưu tất cả dữ liệu trên textfield lên database
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //tắt cờ hiệu
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if (checkTextSua(txt_NhanVien_Sua[1].getText(),
                            txt_NhanVien_Sua[2].getText(),
                            txt_NhanVien_Sua[3].getText(),
                            cbGioiTinh_Sua.getSelectedItem().toString(),
                            txt_NhanVien_Sua[5].getText(),
                            cbChucVu_Sua.getSelectedItem().toString())) {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                        buttonLuu_Sua();

                        Sua.dispose();
                    }

                } else //bật cờ hiệu
                {
                    cohieu = 0;
                }

            }
        });
        Sua.add(Luu);

        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Tắt cờ hiệu đi 
                cohieu = 1;
                Sua.dispose();
            }
        });
        Sua.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Sua.addWindowListener(new WindowAdapter() {

            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });
        Sua.setVisible(true);

    }

    //Hàm lưu dữ liệu khi sửa
    public void buttonLuu_Sua() {
        int row = table_NhanVien.tb.getSelectedRow();
        int colum = table_NhanVien.tb.getSelectedColumn();
        String maNhanVien = table_NhanVien.tbModel.getValueAt(row, colum).toString();
        
        //Sửa dữ liệu trên bảng
        //model là ruột JTable   
        //set tự động giá trị cho model
        for (int j = 0; j < array_NhanVien.length; j++) {
            if(j!=4&&j!=6)
                    table_NhanVien.tbModel.setValueAt(txt_NhanVien_Sua[j].getText(), row, j);
                else if(j==4)
                    table_NhanVien.tbModel.setValueAt(cbGioiTinh_Sua.getSelectedItem().toString(), row, j);
                else if(j==6)
                    table_NhanVien.tbModel.setValueAt(cbChucVu_Sua.getSelectedItem().toString(), row, j);
        }

        table_NhanVien.tb.setModel(table_NhanVien.tbModel);

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
    }

    @Override
    protected void Them_click(MouseEvent evt) {

        Them_Frame();
    }

    //Hàm sự kiện khi click vào nút Sửa
    @Override
    protected void Sua_click(MouseEvent evt) {

        int i = table_NhanVien.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_NhanVien_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for (int j = 0; j < array_NhanVien.length; j++) {
                if(j!=4&&j!=6)
                    txt_NhanVien_Sua[j].setText(table_NhanVien.tb.getValueAt(i, j).toString());
                else if(j==4)
                {
                    int k;
                    for(k=0;k<array_GioiTinh.length;k++)
                        if(table_NhanVien.tb.getValueAt(i, j).toString().equals(array_GioiTinh[k]))
                            cbGioiTinh_Sua.setSelectedIndex(k);
                }
                else if(j==6)
                {
                    int k;
                    for(k=0;k<array_ChucVu.length;k++)
                        if(table_NhanVien.tb.getValueAt(i, j).toString().equals(array_ChucVu[k]))
                            cbChucVu_Sua.setSelectedIndex(k);
                }
            }

        }
    }

    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {
        int row = table_NhanVien.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maNhanVien = table_NhanVien.tbModel.getValueAt(row, 0).toString();
                //truyền mã nhân viên vào hàm timViTri ở NhanVienBUS 
                int index = NhanVienBUS.timViTri(maNhanVien);
                //Xóa hàng ở table
                table_NhanVien.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maNhanVien, index);
            }
        }

    }

    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_NhanVien.clear();
        for (NhanVienDTO DTO : NhanVienBUS.dsnv) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_NhanVien.addRow(DTO);
            }
        }
    }

    public void docDB() {
        NhanVienBUS NhanVienBus = new NhanVienBUS();
        if (NhanVienBUS.dsnv == null) {
            try {
                NhanVienBus.docDSNV();
            } catch (Exception ex) {
                Logger.getLogger(GUINhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (NhanVienDTO NhanVienDTO : NhanVienBUS.dsnv) {
            if (NhanVienDTO.getTrangThai().equals("Hiện")) {
                table_NhanVien.addRow(NhanVienDTO);

            }
        }
    }

    @Override
    protected JPanel TimKiem() {
        JPanel TimKiem = new JPanel(null);

        JLabel lbsearch = new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x = 400;
        cbSearch = new JComboBox<>(array_NhanVien);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);

        search = new JTextField();
        search.setBorder(new TitledBorder(array_NhanVien[0]));
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

        JButton LamMoi = new JButton("Làm mới");
        LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        LamMoi.setFont(new Font("Segoe UI", 0, 14));
        LamMoi.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));
        LamMoi.setBackground(Color.decode("#90CAF9"));
        LamMoi.setBounds(x += 320, 10, 110, 30);
        LamMoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                search.setText("");
                LamMoi();

            }
        });
        TimKiem.add(LamMoi);

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
        table_NhanVien.clear();
        ArrayList<NhanVienDTO> arraylist = Tool.searchNV(search.getText(), cbSearch.getSelectedItem().toString());
        for (NhanVienDTO DTO : arraylist) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_NhanVien.addRow(DTO);

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
    
}




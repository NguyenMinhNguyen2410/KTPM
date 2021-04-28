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
public class GUITaiKhoan extends GUIFormContent {

    public static String array_TaiKhoan[] = {"Tài khoản", "Mã nhân viên", "Mã quyền", "Mật khẩu"};
    public GUIMyTable table_TaiKhoan;
    private static JDialog Them_TaiKhoan;
    private static JDialog Sua;
    private final JLabel label_TaiKhoan[] = new JLabel[array_TaiKhoan.length];
    private JTextField txt_TaiKhoan_Them[] = new JTextField[array_TaiKhoan.length];
    //Phần textfield của sửa
    private JTextField txt_TaiKhoan_Sua[] = new JTextField[array_TaiKhoan.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private TaiKhoanBUS BUS = new TaiKhoanBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;
    private JButton themNhanVien, suaNhanVien,themPhanQuyen,suaPhanQuyen;

    public GUITaiKhoan() {
        super();
    }

    @Override
    //Tạo Panel chưa Table
    protected JPanel Table() {
        JPanel panel = new JPanel(null);
        //Tạo đối tượng cho table_TaiKhoan
        table_TaiKhoan = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_TaiKhoan.setHeaders(array_TaiKhoan);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_TaiKhoan.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
        table_TaiKhoan.setBounds(0, 0, GUImenu.width_content, 600);
        panel.add(table_TaiKhoan);

        return panel;
    }

    //Hàm tạo Dialog thêm công thức
    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Them_TaiKhoan = new JDialog(f);
        Them_TaiKhoan.setLayout(null);
        Them_TaiKhoan.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_TaiKhoan.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_TaiKhoan.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm tài khoản");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_TaiKhoan.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_TaiKhoan.length; i++) {
            label_TaiKhoan[i] = new JLabel(array_TaiKhoan[i]);
            label_TaiKhoan[i].setBounds(100, y, 100, 30);
            Them_TaiKhoan.add(label_TaiKhoan[i]);

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
                Them_TaiKhoan.add(themNhanVien);
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
                Them_TaiKhoan.add(themPhanQuyen);
            }
            y += 40;
            Them_TaiKhoan.add(txt_TaiKhoan_Them[i]);
        }
        txt_TaiKhoan_Them[2].setEditable(false);
        txt_TaiKhoan_Them[1].setEditable(false);
        //Tạo nút lưu
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        //Sự kiện khi click
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Them_TaiKhoan, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
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
                    table_TaiKhoan.addRow(DTO);
                    //clear textfield trong Them
                    for (int i = 0; i < array_TaiKhoan.length; i++) {
                        txt_TaiKhoan_Them[i].setText("");
                    }

                    Them_TaiKhoan.dispose();
                    }
                    

                }else
                    cohieu = 0;
            }
        });
        Them_TaiKhoan.add(Luu);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear textfield trong Them
                for (int i = 0; i < array_TaiKhoan.length; i++) {
                    txt_TaiKhoan_Them[i].setText("");
                }
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them_TaiKhoan.dispose();
            }
        });

        Them_TaiKhoan.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
//        Them_TaiKhoan.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowDeactivated(WindowEvent e) {
//                if (cohieu == 0) {
//                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
//                }
//            }
//
//        });

        Them_TaiKhoan.setVisible(true);

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
        JLabel Title = new JLabel("Sửa tài khoản");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_TaiKhoan.length; i++) {
            label_TaiKhoan[i] = new JLabel(array_TaiKhoan[i]);
            label_TaiKhoan[i].setBounds(100, y, 100, 30);
            Sua.add(label_TaiKhoan[i]);
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
                Sua.add(suaPhanQuyen);
            }
            y += 40;
            Sua.add(txt_TaiKhoan_Sua[i]);
        }
        //Lưu tất cả dữ liệu trên textfield lên database
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if(checkTextSua(txt_TaiKhoan_Sua[1].getText(),
                            txt_TaiKhoan_Sua[2].getText(),
                            txt_TaiKhoan_Sua[3].getText()))
                    {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                    buttonLuu_Sua();

                    Sua.dispose();
                    }
                    
                }else
                    cohieu = 0;

            }
        });
        Sua.add(Luu);
        txt_TaiKhoan_Sua[2].setEditable(false);
        txt_TaiKhoan_Sua[1].setEditable(false);
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
        int row = table_TaiKhoan.tb.getSelectedRow();
        int colum = table_TaiKhoan.tb.getSelectedColumn();
        String maTaiKhoan = table_TaiKhoan.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < array_TaiKhoan.length; j++) {
                table_TaiKhoan.tbModel.setValueAt(txt_TaiKhoan_Sua[j].getText(), row, j);
            }

            table_TaiKhoan.tb.setModel(table_TaiKhoan.tbModel);

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
    }

//    @Override
//    protected void Them_click(MouseEvent evt) {
//        Them_Frame();
//    }

    //Hàm sự kiện khi click vào nút Sửa
    @Override
    protected void Sua_click(MouseEvent evt) {

        int i = table_TaiKhoan.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_TaiKhoan_Sua[0].setEditable(false);
            //Set tự động giá trị các field
            for (int j = 0; j < array_TaiKhoan.length; j++) {
                txt_TaiKhoan_Sua[j].setText(table_TaiKhoan.tb.getValueAt(i, j).toString());
            }

        }
    }

    //Hàm sự kiện khi click vào nút xóa
//    @Override
//    protected void Xoa_click(MouseEvent evt) {
//        int row = table_TaiKhoan.tb.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
//        } else {
//            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
//            if (option == JOptionPane.YES_OPTION) {
//                String maTaiKhoan = table_TaiKhoan.tbModel.getValueAt(row, 0).toString();
//                //truyền mã công thức vào hàm timViTri ở TaiKhoanBUS 
//                int index = TaiKhoanBUS.timViTri(maTaiKhoan);
//                //Xóa hàng ở table
//                table_TaiKhoan.tbModel.removeRow(row);
//                //Xóa ở arraylist và đổi chế độ ẩn ở database
//                BUS.xoa(maTaiKhoan, index);
//            }
//        }
//
//    }

    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_TaiKhoan.clear();
        for (TaiKhoanDTO DTO : TaiKhoanBUS.dstk) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_TaiKhoan.addRow(DTO);
            }
        }
    }

    public void docDB() {
        TaiKhoanBUS monAnBus = new TaiKhoanBUS();
        if (TaiKhoanBUS.dstk == null) {
            try {
                monAnBus.docDSTK();
            } catch (Exception ex) {
                Logger.getLogger(GUITaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (TaiKhoanDTO monAnDTO : TaiKhoanBUS.dstk) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table_TaiKhoan.addRow(monAnDTO);

            }
        }
    }

    @Override
    protected JPanel TimKiem() {
        JPanel TimKiem = new JPanel(null);

        JLabel lbsearch = new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x = 400;
        cbSearch = new JComboBox<>(array_TaiKhoan);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);

        search = new JTextField();
        search.setBorder(new TitledBorder(array_TaiKhoan[0]));
        search.setBounds(155, 20, 150, 40);
        lbsearch.add(search);
        addDocumentListener(search);

        cbSearch.addActionListener((ActionEvent e) -> {
            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            search.requestFocus();

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
        //Đẩy dữ liệu đi và nhận lại danh sách đúng với field tìm kiếm
        setDataToTable(Tool.searchTK(search.getText(),cbSearch.getSelectedItem().toString()), table_TaiKhoan); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<TaiKhoanDTO> taiKhoanDTO, GUIMyTable myTable) {
        myTable.clear();
        for (TaiKhoanDTO taiKhoan : taiKhoanDTO) {
            table_TaiKhoan.addRow(taiKhoan);
        }
    }

    @Override
    protected void XuatExcel_click(MouseEvent evt) {
        new XuatExcel().xuatFileExcelTaiKhoan();

    }

    @Override
    protected void NhapExcel_click(MouseEvent evt) {
        new DocExcel().docFileExcelTaiKhoan();

    }

    public boolean checkTextThem(String maNhanVien, String maQuyen, String matKhau) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (maNhanVien.equals("")
                || maQuyen.equals("")
                || matKhau.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (Tool.isDuplicateMaNhanVien(maNhanVien)) {
            JOptionPane.showMessageDialog(null, "Nhân viên này đã có tài khoản rồi");
            txt_TaiKhoan_Them[1].requestFocus();
        } else if (!Tool.isName((matKhau))) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được chứa ký tự đặc biệt và dấu");
            txt_TaiKhoan_Them[3].requestFocus();
        } else if (!Tool.isLength50(matKhau)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được quá 50 ký tự");
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
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }else if(!TaiKhoanBUS.checkChucVuVaMaQuyen(NhanVienBUS.getChucVuTuMaNhanVien(maNhanVien),maQuyen))
                {
                    //xuất chức vụ
                    System.out.println(NhanVienBUS.getChucVuTuMaNhanVien(maNhanVien));
                    //xuất 
                    System.out.println(maQuyen);
                    JOptionPane.showMessageDialog(null, "Chức vụ của nhân viên không được phép có quyền này");
                }
        else if (!Tool.isName((matKhau))) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được chứa ký tự đặc biệt và dấu");
            txt_TaiKhoan_Sua[3].requestFocus();
        } else if (!Tool.isLength50(matKhau)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được quá 50 ký tự");
            txt_TaiKhoan_Sua[3].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    private void btnChonNhanVien(JTextField textField){
        cohieu=1;
                    GUIFormChon a = null;
                    try {
                        a = new GUIFormChon(textField,"Nhân viên");
                    } catch (Exception ex) {
                        Logger.getLogger(GUIBanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cohieu=0;
    }
    
    private void btnChonQuyen(JTextField textField){
            cohieu=1;
                    GUIFormChon a = null;
                    try {
                        a = new GUIFormChon(textField,"Phân quyền");
                    } catch (Exception ex) {
                        Logger.getLogger(GUIBanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    a.addWindowListener(new WindowAdapter(){
                        @Override
                     public void windowClosed(WindowEvent e){
                            cohieu=0;
                        }
 
                    });
    }
    
    @Override
    protected JPanel CongCu() {
        JPanel CongCu=new JPanel();
        
        JButton Them=new JButton("Thêm");
        Them.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/them1-30.png")));
        Them.setFont(new Font("Segoe UI", 0, 14));
        Them.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));        
        Them.setBackground(Color.decode("#90CAF9"));
        
        Them.setBounds(350, 0, 70, 40);
        Them.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Them_click(evt);
            }
        });
        Them.setEnabled(false);
        CongCu.add(Them);
        
        JButton Sua=new JButton("Sửa");
        Sua.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/sua3-30.png")));
        Sua.setFont(new Font("Segoe UI", 0, 14));
        Sua.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        Sua.setBackground(Color.decode("#90CAF9"));
        Sua.setBounds(430, 0, 70, 30);
        Sua.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Sua_click(evt);
            }
        });
        CongCu.add(Sua);
        
        JButton Xoa=new JButton("Xóa");
        Xoa.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/delete1-30.png")));
        Xoa.setFont(new Font("Segoe UI", 0, 14));
        Xoa.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        Xoa.setBackground(Color.decode("#90CAF9"));
        Xoa.setBounds(510, 0, 70, 30);
        Xoa.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Xoa_click(evt);
            }
        });
        Xoa.setEnabled(false);
        CongCu.add(Xoa);
        
        JButton NhapExcel=new JButton("Nhập Excel");
        NhapExcel.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xls-30.png")));
        NhapExcel.setFont(new Font("Segoe UI", 0, 14));
        NhapExcel.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        NhapExcel.setBackground(Color.decode("#90CAF9"));
        NhapExcel.setBounds(590, 0, 100, 30);
        NhapExcel.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                NhapExcel_click(evt);
            }
        });
        CongCu.add(NhapExcel);
        
        JButton XuatExcel=new JButton("Xuất Excel");
        XuatExcel.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xls-30.png")));
        XuatExcel.setFont(new Font("Segoe UI", 0, 14));
        XuatExcel.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        XuatExcel.setBackground(Color.decode("#90CAF9"));
        XuatExcel.setBounds(670, 0, 100, 30);
        XuatExcel.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                XuatExcel_click(evt);
            }
        });
        CongCu.add(XuatExcel);
        return CongCu;
        
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.MonAnBUS;
import BUS.Tool;
import DTO.MonAnDTO;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
//Kế thừa từ 1 mẫu bố cục là GUIFormContent
public class GUIMonAn extends GUIFormContent {
    //Nút lấy tên ảnh
    private JButton btnFileAnh;
    //Tạo mảng tiêu đề
    public static String array_MonAn[] = {"Mã", "Tên món", "Đơn vị tính", "Giá", "Hình ảnh", "Loại", "Số lượng"};
    //Tạo JTable , GUIMyTable kế thừa từ JTable và được chỉnh sửa
    private GUIMyTable table_MonAn;
    //Panel chứa phần show thông tin món ăn
    private JPanel Show;
    //Tạo Dialog để thêm món ăn
    private static JDialog Them_MonAn;
    //Tạo Dialog để sửa món ăn
    private static JDialog Sua;
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_MonAn[] = new JLabel[array_MonAn.length];
    //Phần textfield của thêm
    private JTextField txt_MonAn_Them[] = new JTextField[array_MonAn.length];
    //Phần textfield của sửa
    private JTextField txt_MonAn_Sua[] = new JTextField[array_MonAn.length];
    //Tạo nhãn chứa hình ảnh
    private JLabel lbImage;
    //field thông tin khi click row
    private JTextField txMaMA, txTenMA, txDonGia, txSoLuong;
    //Các textfield trong thanh tìm kiếm
    public JTextField Ten, Tu_DonGia, Den_DonGia, Tu_SoLuong, Den_SoLuong;
    //Tạo đối tượng cho BUS
    MonAnBUS BUS = new MonAnBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;
    private JComboBox cbDonViTinh_Them,cbDonViTinh_Sua;
    private String array_DonViTinh[]={"Dĩa","Phần","Ly"};
    private JComboBox cbLoai_Them,cbLoai_Sua;
    private String array_Loai[]={"Món chính","Món phụ","Nước uống"};
    public GUIMonAn() {
        super();
    }

    @Override
    //Tạo Panel chưa Table
    protected JPanel Table() {
        JPanel panel = new JPanel(null);
        //Tạo đối tượng cho table_MonAn
        table_MonAn = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_MonAn.setHeaders(array_MonAn);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_MonAn.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
        table_MonAn.setBounds(0, 0, GUImenu.width_content, 300);
        panel.add(table_MonAn);

        //Tạo phần show thông tin
        Show = Show();
        Show.setBounds(0, 300, GUImenu.width_content, 300);
        panel.add(Show);

        return panel;
    }

    //Hàm tạo Dialog thêm món ăn
    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Them_MonAn = new JDialog(f);
        Them_MonAn.setLayout(null);
        Them_MonAn.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_MonAn.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_MonAn.setUndecorated(true);

        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm món ăn");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_MonAn.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_MonAn.length; i++) {
            label_MonAn[i] = new JLabel(array_MonAn[i]);
            label_MonAn[i].setBounds(100, y, 100, 30);
            Them_MonAn.add(label_MonAn[i]);
            //Tạo combobox
            if(i==2)
            {
                cbDonViTinh_Them=new JComboBox(array_DonViTinh);
                cbDonViTinh_Them.setBounds(200, y, 150, 30);
                Them_MonAn.add(cbDonViTinh_Them);
                y+=40;
                continue;
            }
            if(i==5)
            {
                cbLoai_Them=new JComboBox(array_Loai);
                cbLoai_Them.setBounds(200, y, 150, 30);
                Them_MonAn.add(cbLoai_Them);
                y+=40;
                continue;
            }
            txt_MonAn_Them[i] = new JTextField();
            txt_MonAn_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            if (i == 4) {
                btnFileAnh = new JButton();
                btnFileAnh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/hinhanh-30.png")));
                btnFileAnh.addActionListener((ae) -> {
                    btnFileAnh_Click("Thêm");
                });
                btnFileAnh.setBounds(360, y, 40, 40);
                Them_MonAn.add(btnFileAnh);
            }
            y += 40;
            Them_MonAn.add(txt_MonAn_Them[i]);
        }
        
        txt_MonAn_Them[4].setEditable(false); // không cho người dùng nhập hình ảnh
        //Tạo nút lưu
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        //Sự kiện khi click
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu=1;
                int a = JOptionPane.showConfirmDialog(Them_MonAn, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    //Kiểm tra luồng dữ liệu 
                    if (checkTextThem(txt_MonAn_Them[0].getText(),
                            txt_MonAn_Them[1].getText(),
                            cbDonViTinh_Them.getSelectedItem().toString(),
                            txt_MonAn_Them[3].getText(),
                            txt_MonAn_Them[4].getText(),
                            cbLoai_Them.getSelectedItem().toString(),
                            txt_MonAn_Them[6].getText())) {
                        //Tạo đối tượng và truyền dữ liệu trực tiếp vào 
                        MonAnDTO DTO = new MonAnDTO(txt_MonAn_Them[0].getText(),
                                txt_MonAn_Them[1].getText(),
                                cbDonViTinh_Them.getSelectedItem().toString(),
                                Integer.parseInt(txt_MonAn_Them[3].getText()),
                                txt_MonAn_Them[4].getText(),
                                cbLoai_Them.getSelectedItem().toString(),
                                Integer.parseInt(txt_MonAn_Them[6].getText()),
                                "Hiện");
                        //Gọi hàm thêm ở bus và truyền đối tượng DTO vào
                        BUS.them(DTO);
                        //Thêm vào table
                        table_MonAn.addRow(DTO);
                        //clear textfield trong Them_frame
                        clearThem_Frame();
                        
                        //Lệnh này để đóng dialog
                        Them_MonAn.dispose();
                    }
                }
                else
                    cohieu=0;
            }
        });
        Them_MonAn.add(Luu);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click lưu
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Clear textfield
                clearThem_Frame();
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them_MonAn.dispose();
            }
        });

        Them_MonAn.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_MonAn.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });

        String maMonAn = Tool.tangMa(MonAnBUS.getMaMonAnCuoi()); //lấy mã tự động
        txt_MonAn_Them[0].setEditable(false);
        txt_MonAn_Them[0].setText(maMonAn); //set mã lên textfield
        Them_MonAn.setVisible(true);

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
        JLabel Title = new JLabel("Sửa món ăn");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_MonAn.length; i++) {
            label_MonAn[i] = new JLabel(array_MonAn[i]);
            label_MonAn[i].setBounds(100, y, 100, 30);
            Sua.add(label_MonAn[i]);
            //Tạo combobox
            if(i==2)
            {
                cbDonViTinh_Sua=new JComboBox(array_DonViTinh);
                cbDonViTinh_Sua.setBounds(200, y, 150, 30);
                Sua.add(cbDonViTinh_Sua);
                y+=40;
                continue;
            }
            //Tạo combobox
            if(i==5)
            {
                cbLoai_Sua=new JComboBox(array_Loai);
                cbLoai_Sua.setBounds(200, y, 150, 30);
                Sua.add(cbLoai_Sua);
                y+=40;
                continue;
            }
            txt_MonAn_Sua[i] = new JTextField();
            txt_MonAn_Sua[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy file ảnh
            if (i == 4) {
                btnFileAnh = new JButton();
                btnFileAnh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/hinhanh-30.png")));
                btnFileAnh.addActionListener((ae) -> {
                    btnFileAnh_Click("Sửa");
                });
                btnFileAnh.setBounds(360, y, 40, 40);
                Sua.add(btnFileAnh);
            }

            y += 40;
            Sua.add(txt_MonAn_Sua[i]);
        }
        
        txt_MonAn_Sua[4].setEditable(false); // không cho người dùng nhập hình ảnh
        //Lưu tất cả dữ liệu trên textfield lên database
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Tắt cờ hiệu đi 
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    //Chạy hàm checkText để ràng buộc dữ liệu 
                    if (checkTextSua(txt_MonAn_Sua[0].getText(),
                            txt_MonAn_Sua[1].getText(),
                            cbDonViTinh_Sua.getSelectedItem().toString(),
                            txt_MonAn_Sua[3].getText(),
                            txt_MonAn_Sua[4].getText(),
                            cbLoai_Sua.getSelectedItem().toString(),
                            txt_MonAn_Sua[6].getText())) {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                        buttonLuu_Sua();
                        
                        //Lệnh này để tắt dialog
                        Sua.dispose();
                    }
                }
                else
                    cohieu=0;
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
                //Lệnh này để tắt dialog
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

    //Viết đè hàm tìm kiếm
    @Override
    protected JPanel TimKiem() {
        JPanel TimKiem = new JPanel(null);

        JLabel lbTen = new JLabel("");
        lbTen.setBorder(new TitledBorder("Tìm kiếm"));
        //Tìm kiếm theo tên
        Ten = new JTextField();
        Ten.setBorder(new TitledBorder("Tên"));
        Ten.setBounds(5, 20, 200, 40);
        lbTen.add(Ten);
        //Gọi sự kiện khi tìm kiếm với Ten
        addDocumentListener(Ten);
        lbTen.setBounds(300, 0, 215, 70);
        TimKiem.add(lbTen);

        //Tìm kiếm theo đơn giá
        JLabel DonGia = new JLabel("");
        DonGia.setBorder(new TitledBorder("Đơn giá"));

        Tu_DonGia = new JTextField();
        Tu_DonGia.setBorder(new TitledBorder("Từ"));
        Tu_DonGia.setBounds(5, 20, 100, 40);
        DonGia.add(Tu_DonGia);
        //Gọi sự kiện khi tìm kiếm với Ten Tu_DonGia
        addDocumentListener(Tu_DonGia);

        Den_DonGia = new JTextField();
        Den_DonGia.setBorder(new TitledBorder("Đến"));
        Den_DonGia.setBounds(105, 20, 100, 40);
        DonGia.add(Den_DonGia);
        //Gọi sự kiện khi tìm kiếm với Den_DonGia
        addDocumentListener(Den_DonGia);

        DonGia.setBounds(520, 0, 215, 70);
        TimKiem.add(DonGia);

        //Tìm kiếm theo số lượng
        JLabel SoLuong = new JLabel("");
        SoLuong.setBorder(new TitledBorder("Số lượng"));

        Tu_SoLuong = new JTextField();
        Tu_SoLuong.setBorder(new TitledBorder("Từ"));
        Tu_SoLuong.setBounds(5, 20, 100, 40);
        SoLuong.add(Tu_SoLuong);
        //Gọi sự kiện khi tìm kiếm với Tu_SoLuong
        addDocumentListener(Tu_SoLuong);

        Den_SoLuong = new JTextField();
        Den_SoLuong.setBorder(new TitledBorder("Đến"));
        Den_SoLuong.setBounds(105, 20, 100, 40);
        SoLuong.add(Den_SoLuong);
        //Gọi sự kiện khi tìm kiếm với Den_SoLuong
        addDocumentListener(Den_SoLuong);

        SoLuong.setBounds(740, 0, 215, 70);
        TimKiem.add(SoLuong);
        //Nút làm mới
        JButton LamMoi = new JButton("Làm mới");
        LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        LamMoi.setFont(new Font("Segoe UI", 0, 14));
        LamMoi.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));
        LamMoi.setBackground(Color.decode("#90CAF9"));
        LamMoi.setBounds(965, 10, 110, 30);
        LamMoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear hết các text tìm kiếm
                Ten.setText("");
                Tu_DonGia.setText("");
                Den_DonGia.setText("");
                Tu_SoLuong.setText("");
                Den_SoLuong.setText("");
                //gọi hàm làm mới
                LamMoi();
            }
        });
        TimKiem.add(LamMoi);

        return TimKiem;
    }

    public void docDB() {
        //Nếu dsMonAn vẫn chưa được đọc thì chạy hàm đọc
        if (MonAnBUS.dsMonAn == null) {
            try {
                BUS.docDSMonAn();
            } catch (Exception ex) {
                Logger.getLogger(GUIMonAn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Chỉ hiện những món ăn ở trạng thái hiện , trạng thái ẩn là khi xóa
        for (MonAnDTO monAnDTO : MonAnBUS.dsMonAn) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table_MonAn.addRow(monAnDTO);

            }
        }
    }

    @Override
    protected void Them_click(MouseEvent evt) {
        Them_Frame();
    }

    //Ràng buộc dữ liệu
    //Thứ tự truyền vào lần lượt trùng với các thứ tự ô text
    public boolean checkTextThem(String MaMonAn, String TenMonAn, String DonViTinh, String DonGia, String HinhAnh, String Loai, String SoLuong) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (MaMonAn.equals("")
                || TenMonAn.equals("")
                || DonViTinh.equals("")
                || DonGia.equals("")
                || HinhAnh.equals("")
                || Loai.equals("")
                || SoLuong.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(TenMonAn))) {
            JOptionPane.showMessageDialog(null, "Tên món ăn không được chứa ký tự đặc biệt");
            txt_MonAn_Them[1].requestFocus();
        } else if (!Tool.isLength50(TenMonAn)) {
            JOptionPane.showMessageDialog(null, "Tên món ăn không được quá 50 ký tự");
            txt_MonAn_Them[1].requestFocus();
        }
        
        else if (!Tool.isName(Tool.removeAccent(DonViTinh))) {
            JOptionPane.showMessageDialog(null, "Đơn vị tính không được chứa ký tự đặc biệt");
            txt_MonAn_Them[2].requestFocus();
        } else if (!Tool.isLength50(DonViTinh)) {
            JOptionPane.showMessageDialog(null, "Đơn vị tính không được quá 50 ký tự");
            txt_MonAn_Them[2].requestFocus();
        }
        
        else if (!Tool.isNumber(DonGia)) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải là số nguyên dương");
            txt_MonAn_Them[3].requestFocus();
        } else if (!Tool.isName((DonGia))) {
            JOptionPane.showMessageDialog(null, "Đơn giá không được chứa ký tự đặc biệt");
            txt_MonAn_Them[3].requestFocus();
        } else if (!Tool.isTenThousandToOneMil(DonGia)) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải nằm trong khoảng 10.000 đến 1.000.000");
            txt_MonAn_Them[3].requestFocus();
        }
        
        else if (!Tool.isHinhAnh(HinhAnh)) {
            JOptionPane.showMessageDialog(null, "Hình ảnh phải được định dạng là : *.jpg hoặc *.png ");
            txt_MonAn_Them[4].requestFocus();
        }
        
        else if (!Tool.isName(Tool.removeAccent(Loai))) {
            JOptionPane.showMessageDialog(null, "Loại không được chứa ký tự đặc biệt");
            txt_MonAn_Them[5].requestFocus();
        } else if (!Tool.isLength50(Loai)) {
            JOptionPane.showMessageDialog(null, "Loại không được quá 50 ký tự");
            txt_MonAn_Them[5].requestFocus();
        } else if (!Tool.isNumber(SoLuong)) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương");
            txt_MonAn_Them[6].requestFocus();
        } else if (!Tool.isName(SoLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng không được chứa ký tự đặc biệt");
            txt_MonAn_Them[6].requestFocus();
        } else if (!Tool.isOneToOneThousand(SoLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng phải nằm trong khoảng 1 đến 1.000");
            txt_MonAn_Them[6].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    public boolean checkTextSua(String MaMonAn, String TenMonAn, String DonViTinh, String DonGia, String HinhAnh, String Loai, String SoLuong) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (MaMonAn.equals("")
                || TenMonAn.equals("")
                || DonViTinh.equals("")
                || DonGia.equals("")
                || HinhAnh.equals("")
                || Loai.equals("")
                || SoLuong.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(TenMonAn))) {
            JOptionPane.showMessageDialog(null, "Tên món ăn không được chứa ký tự đặc biệt");
            txt_MonAn_Sua[1].requestFocus();
        } else if (!Tool.isLength50(TenMonAn)) {
            JOptionPane.showMessageDialog(null, "Tên món ăn không được quá 50 ký tự");
            txt_MonAn_Sua[1].requestFocus();
        }
        
        else if (!Tool.isName(Tool.removeAccent(DonViTinh))) {
            JOptionPane.showMessageDialog(null, "Đơn vị tính không được chứa ký tự đặc biệt");
            txt_MonAn_Sua[2].requestFocus();
        } else if (!Tool.isLength50(DonViTinh)) {
            JOptionPane.showMessageDialog(null, "Đơn vị tính không được quá 50 ký tự");
            txt_MonAn_Sua[2].requestFocus();
        }
        
        else if (!Tool.isNumber(DonGia)) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải là số nguyên dương");
            txt_MonAn_Sua[3].requestFocus();
        } else if (!Tool.isName((DonGia))) {
            JOptionPane.showMessageDialog(null, "Đơn giá không được chứa ký tự đặc biệt");
            txt_MonAn_Sua[3].requestFocus();
        } else if (!Tool.isTenThousandToOneMil(DonGia)) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải nằm trong khoảng 10.000 đến 1.000.000");
            txt_MonAn_Sua[3].requestFocus();
        }
        
        else if (!Tool.isHinhAnh(HinhAnh)) {
            JOptionPane.showMessageDialog(null, "Hình ảnh phải được định dạng là : *.jpg hoặc *.png ");
            txt_MonAn_Sua[4].requestFocus();
        }
        
        else if (!Tool.isName(Tool.removeAccent(Loai))) {
            JOptionPane.showMessageDialog(null, "Loại không được chứa ký tự đặc biệt");
            txt_MonAn_Sua[5].requestFocus();
        } else if (!Tool.isLength50(Loai)) {
            JOptionPane.showMessageDialog(null, "Loại không được quá 50 ký tự");
            txt_MonAn_Sua[5].requestFocus();
        } else if (!Tool.isNumber(SoLuong)) {

            
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương");
            txt_MonAn_Sua[6].requestFocus();
        } else if (!Tool.isName(SoLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng không được chứa ký tự đặc biệt");
            txt_MonAn_Sua[6].requestFocus();
        } else if (!Tool.isOneToOneThousand(SoLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng phải nằm trong khoảng 1 đến 1.000");
            txt_MonAn_Sua[6].requestFocus();
        } else {
            return true;

        }
        return false;
    }

    //Hàm lưu dữ liệu khi sửa
    public void buttonLuu_Sua() {
        int row = table_MonAn.tb.getSelectedRow();
        int colum = table_MonAn.tb.getSelectedColumn();
        String maMonAn = table_MonAn.tbModel.getValueAt(row, colum).toString();
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < array_MonAn.length; j++) {
                if(j!=2&&j!=5)
                    table_MonAn.tbModel.setValueAt(txt_MonAn_Sua[j].getText(), row, j);
                else if(j==2)
                    table_MonAn.tbModel.setValueAt(cbDonViTinh_Sua.getSelectedItem().toString(), row, j);
                else if(j==5)
                    table_MonAn.tbModel.setValueAt(cbLoai_Sua.getSelectedItem().toString(), row, j);
            }

            table_MonAn.tb.setModel(table_MonAn.tbModel);

            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            MonAnDTO DTO = new MonAnDTO(txt_MonAn_Sua[0].getText(),
                    txt_MonAn_Sua[1].getText(),
                    cbDonViTinh_Sua.getSelectedItem().toString(),
                    Integer.parseInt(txt_MonAn_Sua[3].getText()),
                    txt_MonAn_Sua[4].getText(),
                    cbLoai_Sua.getSelectedItem().toString(),
                    Integer.parseInt(txt_MonAn_Sua[6].getText()));
            //Tìm vị trí của row cần sửa
            int index = MonAnBUS.timViTri(maMonAn);
            //Truyền dữ liệu và vị trí vào bus
            BUS.sua(DTO, index);
//    }
    }

    //Clear textfield
    public void clearThem_Frame() {
        for (int i = 0; i < GUIMonAn.array_MonAn.length; i++) {
            if(i!=2&&i!=5)
            txt_MonAn_Them[i].setText("");
        }
    }

    //Hàm sự kiện khi click vào nút Sửa
    @Override
    protected void Sua_click(MouseEvent evt) {

        int i = table_MonAn.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_MonAn_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for (int j = 0; j < array_MonAn.length; j++) {
                if(j!=2&&j!=5)
                    txt_MonAn_Sua[j].setText(table_MonAn.tb.getValueAt(i, j).toString());
                else if(j==2)
                {
                    int k;
                    for(k=0;k<array_DonViTinh.length;k++)
                        if(table_MonAn.tb.getValueAt(i, j).toString().equals(array_DonViTinh[k]))
                            cbDonViTinh_Sua.setSelectedIndex(k);
                }
                else if(j==5)
                {
                    int k;
                    for(k=0;k<array_Loai.length;k++)
                        if(table_MonAn.tb.getValueAt(i, j).toString().equals(array_Loai[k]))
                            cbLoai_Sua.setSelectedIndex(k);
                }
            }

        }
    }

    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {
        int row = table_MonAn.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maMonAn = table_MonAn.tbModel.getValueAt(row, 0).toString();
                //truyền mã món ăn vào hàm timViTri ở MonAnBUS 
                int index = MonAnBUS.timViTri(maMonAn);
                //Xóa hàng ở table
                table_MonAn.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maMonAn, index);
            }
        }

    }

    //Show thông tin món ăn
    private JPanel Show() {
        //Panel chung
        JPanel panel = new JPanel(null);
        //Panel chứa các text thông tin món ăn
        JPanel ChiTiet = new JPanel(null);

        ChiTiet.setBounds(500, 0, 500, 300);
        //Nhãn dùng để hiện hình ảnh
        lbImage = new JLabel();
        lbImage.setBackground(Color.yellow);
        lbImage.setBounds(200, 0, 300, 300);

        //Các textfield thông tin
        txMaMA = new JTextField();
        txTenMA = new JTextField();
        txDonGia = new JTextField();
        txSoLuong = new JTextField();

        // Tạo border có tiêu đề
        txMaMA.setBorder(BorderFactory.createTitledBorder("Mã món ăn"));
        txTenMA.setBorder(BorderFactory.createTitledBorder("Tên món ăn"));
        txDonGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
//         Set các textfield không edit được
        txMaMA.setEditable(false);
        txTenMA.setEditable(false);
        txDonGia.setEditable(false);
        txSoLuong.setEditable(false);
        // Set font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaMA.setFont(f);
        txTenMA.setFont(f);
        txDonGia.setFont(f);
        txSoLuong.setFont(f);
        //set size

        txMaMA.setBounds(50, 0, 200, 40);
        txTenMA.setBounds(50, 50, 200, 40);
        txDonGia.setBounds(50, 100, 200, 40);
        txSoLuong.setBounds(50, 150, 200, 40);
        // add to panel
        ChiTiet.add(txMaMA);
        ChiTiet.add(txTenMA);
        ChiTiet.add(txDonGia);
        ChiTiet.add(txSoLuong);

        // event
        table_MonAn.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                String id = String.valueOf(table_MonAn.tbModel.getValueAt(table_MonAn.tb.getSelectedRow(), 0));
                if (id != null) {
                    //Hàm xử lý khi ấn vào 1 row trong table
                    showInfo(id);
                }
            }
        });

        panel.add(lbImage);
        panel.add(ChiTiet);
        return panel;
    }
    //Hàm show thông tin của món ăn
    private void showInfo(String id) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (id != null) {
            // show hình
            for (MonAnDTO ds : MonAnBUS.dsMonAn) {
                if (ds.getIDMonAn().equals(id)) {
                    //Lấy chiều dài và chiều cao của nhãn lbImage
                    int w = lbImage.getWidth();
                    int h = lbImage.getHeight();
                    //Hàm xử lý khi đưa ảnh lên
                    ImageIcon img = new ImageIcon(getClass().getResource("/Images/MonAn/" + ds.getHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
                    lbImage.setIcon(new ImageIcon(imgScaled));

                    // show info                   
                    txMaMA.setText(ds.getIDMonAn());
                    txTenMA.setText(ds.getTenMonAn());
                    txDonGia.setText(String.valueOf(ds.getDonGia()));
                    txSoLuong.setText(String.valueOf(ds.getSoLuong()));
                    return;
                }
            }
        }
    }

    //Hành động khi ấn nút FileAnh
    private void btnFileAnh_Click(String type) {
        //bật lên 1 cửa sổ mới nên cần gán giá trị 1
        cohieu = 1;
        if (type.equals("Thêm")) {
            //Tạo cửa sổ chọn file
            FileDialog fd = new FileDialog(Them_MonAn);
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename != null) {
                txt_MonAn_Them[4].setText(filename);

            }
        }
        if (type.equals("Sửa")) {
            //Tạo cửa sổ chọn file
            FileDialog fd = new FileDialog(Sua);
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename != null) {
                txt_MonAn_Sua[4].setText(filename);

            }
        }
        //đã thực hiện xong thì set lại giá trị 0
        cohieu = 0;
    }

    // để cho hàm tìm kiếm
    private void addDocumentListener(JTextField tx) {
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

    //Hàm tìm kiếm mỗi khi thao tác trên field
    public void txtSearchOnChange() {
        int soLuong1 = -1, soLuong2 = -1;
        double donGia1 = -1, donGia2 = -1;
        //Ràng buộc
        try {
            soLuong1 = Integer.parseInt(Tu_SoLuong.getText());
            Tu_SoLuong.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Tu_SoLuong.setForeground(Color.red);
        }

        try {
            soLuong2 = Integer.parseInt(Den_SoLuong.getText());
            Den_SoLuong.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Den_SoLuong.setForeground(Color.red);
        }

        try {
            donGia1 = Double.parseDouble(Tu_DonGia.getText());
            Tu_DonGia.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Tu_DonGia.setForeground(Color.red);
        }

        try {
            donGia2 = Double.parseDouble(Den_DonGia.getText());
            Den_DonGia.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Den_DonGia.setForeground(Color.red);
        }

        //Đẩy dữ liệu đi và nhận lại danh sách đúng với field tìm kiếm
        setDataToTable(Tool.searchMA(Ten.getText(), donGia1, donGia2, soLuong1, soLuong2), table_MonAn); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    //Set dữ liệu lên lại table
    private void setDataToTable(ArrayList<MonAnDTO> monAnDTO, GUIMyTable myTable) {
        myTable.clear();
        for (MonAnDTO monAn : monAnDTO) {
            table_MonAn.addRow(monAn);
        }
    }

    @Override
    protected void XuatExcel_click(MouseEvent evt) {
        new XuatExcel().xuatFileExcelMonAn();

    }

    @Override
    protected void NhapExcel_click(MouseEvent evt) {
        new DocExcel().docFileExcelMonAn();

    }

    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_MonAn.clear();
        for (MonAnDTO DTO : MonAnBUS.dsMonAn) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_MonAn.addRow(DTO);
            }
        }
    }
}





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.KhuyenMaiBUS;
import BUS.Tool;
import DTO.KhuyenMaiDTO;
import Excel.DocExcel;
import Excel.XuatExcel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
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
public class GUIKhuyenMai extends GUIFormContent {

    public static String array_KhuyenMai[] = {"Mã khuyến mãi", "Tên chương trình", "Tiền giảm", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung"};
    public GUIMyTable table_KhuyenMai;
    private static JDialog Them_KhuyenMai;
    private static JDialog Sua;
    private final JLabel label_KhuyenMai[] = new JLabel[array_KhuyenMai.length];
    private JTextField txt_KhuyenMai_Them[] = new JTextField[array_KhuyenMai.length];
    //Phần textfield của sửa
    private JTextField txt_KhuyenMai_Sua[] = new JTextField[array_KhuyenMai.length];
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private KhuyenMaiBUS BUS = new KhuyenMaiBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;
    private DatePicker Them_NgayBatDau, Them_NgayKetThuc, Sua_NgayBatDau, Sua_NgayKetThuc;
    private JTextField Ten,Tu_TienGiam,Den_TienGiam,Tu_NgayBatDau,Den_NgayKetThuc;
    private static DatePicker dp_Tu_NgayBatDau,dp_Den_NgayKetThuc;

    public GUIKhuyenMai() {
        super();
    }

    @Override
    //Tạo Panel chưa Table
    protected JPanel Table() {
        JPanel panel = new JPanel(null);
        //Tạo đối tượng cho table_KhuyenMai
        table_KhuyenMai = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_KhuyenMai.setHeaders(array_KhuyenMai);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_KhuyenMai.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
        table_KhuyenMai.setBounds(0, 0, GUImenu.width_content, 550);
        panel.add(table_KhuyenMai);

        return panel;
    }

    //Hàm tạo Dialog thêm Khuyến mãi 
    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Them_KhuyenMai = new JDialog(f);
        Them_KhuyenMai.setLayout(null);
        Them_KhuyenMai.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_KhuyenMai.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_KhuyenMai.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm khuyến mãi");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_KhuyenMai.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_KhuyenMai.length; i++) {
            label_KhuyenMai[i] = new JLabel(array_KhuyenMai[i]);
            label_KhuyenMai[i].setBounds(100, y, 100, 30);
            Them_KhuyenMai.add(label_KhuyenMai[i]);

            txt_KhuyenMai_Them[i] = new JTextField();
            txt_KhuyenMai_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            if (i == 3) {
                // khoang ngay
                DatePickerSettings pickerSettings = new DatePickerSettings();
                pickerSettings.setVisibleDateTextField(false);
                Them_NgayBatDau = new DatePicker(pickerSettings);
                Them_NgayBatDau.setDateToToday();
                // calendar icon
                JButton calendar = Them_NgayBatDau.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Them_NgayBatDau.setBounds(355, y, 35, 30);
                txt_KhuyenMai_Them[3].setEditable(false);
                Them_KhuyenMai.add(Them_NgayBatDau);
                Them_NgayBatDau.addDateChangeListener((dce) -> {
                    txt_KhuyenMai_Them[3].setText(Them_NgayBatDau.getDateStringOrEmptyString());

                });
            }
            if (i == 4) {
                // khoang ngay
                DatePickerSettings pickerSettings2 = new DatePickerSettings();
                pickerSettings2.setVisibleDateTextField(false);
                Them_NgayKetThuc = new DatePicker(pickerSettings2);
                Them_NgayKetThuc.setDateToToday();
                // calendar icon
                JButton calendar = Them_NgayKetThuc.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Them_NgayKetThuc.setBounds(355, y, 35, 30);
                txt_KhuyenMai_Them[4].setEditable(false);
                Them_KhuyenMai.add(Them_NgayKetThuc);
                Them_NgayKetThuc.addDateChangeListener((dce) -> {
                    txt_KhuyenMai_Them[4].setText(Them_NgayKetThuc.getDateStringOrEmptyString());

                });
            }
            y += 40;
            Them_KhuyenMai.add(txt_KhuyenMai_Them[i]);
        }
        //Tạo nút lưu
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        //Sự kiện khi click
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Tắt cờ hiệu đi 
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Them_KhuyenMai, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if (checkTextThem(txt_KhuyenMai_Them[1].getText(),
                            txt_KhuyenMai_Them[2].getText(),
                            txt_KhuyenMai_Them[3].getText(),
                            txt_KhuyenMai_Them[4].getText(),
                            txt_KhuyenMai_Them[5].getText())) {
                        KhuyenMaiDTO DTO = new KhuyenMaiDTO(txt_KhuyenMai_Them[0].getText(),
                                txt_KhuyenMai_Them[1].getText(),
                                Integer.parseInt(txt_KhuyenMai_Them[2].getText()),
                                LocalDate.parse(txt_KhuyenMai_Them[3].getText()),
                                LocalDate.parse(txt_KhuyenMai_Them[4].getText()),
                                txt_KhuyenMai_Them[5].getText(),
                                "Hiện");

                        BUS.them(DTO); //thêm Khuyến mãi  bên BUS đã có thêm vào database
                        table_KhuyenMai.addRow(DTO);
                        //clear textfield trong Them
                        for (int i = 0; i < array_KhuyenMai.length; i++) {
                            txt_KhuyenMai_Them[i].setText("");
                        }
                        //Tắt cờ hiệu đi 
                        Them_KhuyenMai.dispose();
                    }

                } else {
                    cohieu = 0;
                }
            }
        });
        Them_KhuyenMai.add(Luu);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear textfield trong Them
                for (int i = 0; i < array_KhuyenMai.length; i++) {
                    txt_KhuyenMai_Them[i].setText("");
                }
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them_KhuyenMai.dispose();
            }
        });

        Them_KhuyenMai.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_KhuyenMai.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });

        String ma = Tool.tangMa(KhuyenMaiBUS.getMaKhuyenMaiCuoi());
        txt_KhuyenMai_Them[0].setText(ma);
        txt_KhuyenMai_Them[0].setEditable(false);
        Them_KhuyenMai.setVisible(true);

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
        JLabel Title = new JLabel("Sửa khuyến mãi");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_KhuyenMai.length; i++) {
            label_KhuyenMai[i] = new JLabel(array_KhuyenMai[i]);
            label_KhuyenMai[i].setBounds(100, y, 100, 30);
            Sua.add(label_KhuyenMai[i]);
            txt_KhuyenMai_Sua[i] = new JTextField();
            txt_KhuyenMai_Sua[i].setBounds(200, y, 150, 30);
            if (i == 3) {
                // khoang ngay
                DatePickerSettings pickerSettings3 = new DatePickerSettings();
                pickerSettings3.setVisibleDateTextField(false);
                Sua_NgayBatDau = new DatePicker(pickerSettings3);
                Sua_NgayBatDau.setDateToToday();
                // calendar icon
                JButton calendar = Sua_NgayBatDau.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Sua_NgayBatDau.setBounds(355, y, 35, 30);
                txt_KhuyenMai_Sua[3].setEditable(false);
                Sua.add(Sua_NgayBatDau);
                Sua_NgayBatDau.addDateChangeListener((dce) -> {
                    txt_KhuyenMai_Sua[3].setText(Sua_NgayBatDau.getDateStringOrEmptyString());
                    
                });
            }
            if (i == 4) {
                // khoang ngay
                DatePickerSettings pickerSettings4 = new DatePickerSettings();
                pickerSettings4.setVisibleDateTextField(false);
                Sua_NgayKetThuc = new DatePicker(pickerSettings4);
                Sua_NgayKetThuc.setDateToToday();
                // calendar icon
                JButton calendar = Sua_NgayKetThuc.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Sua_NgayKetThuc.setBounds(355, y, 35, 30);
                txt_KhuyenMai_Sua[4].setEditable(false);
                Sua.add(Sua_NgayKetThuc);
                Sua_NgayKetThuc.addDateChangeListener((dce) -> {
                    txt_KhuyenMai_Sua[4].setText(Sua_NgayKetThuc.getDateStringOrEmptyString());
                    
                });
            }
            y += 40;
            Sua.add(txt_KhuyenMai_Sua[i]);
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
                    if (checkTextSua(txt_KhuyenMai_Sua[1].getText(),
                            txt_KhuyenMai_Sua[2].getText(),
                            txt_KhuyenMai_Sua[3].getText(),
                            txt_KhuyenMai_Sua[4].getText(),
                            txt_KhuyenMai_Sua[5].getText())) {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                        buttonLuu_Sua();
                        //Tắt cờ hiệu đi 

                        Sua.dispose();
                    }

                } else {
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
        int row = table_KhuyenMai.tb.getSelectedRow();
        int colum = table_KhuyenMai.tb.getSelectedColumn();
        String maKhuyenMai = table_KhuyenMai.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
        //Sửa dữ liệu trên bảng
        //model là ruột JTable   
        //set tự động giá trị cho model
        for (int j = 0; j < array_KhuyenMai.length; j++) {
            table_KhuyenMai.tbModel.setValueAt(txt_KhuyenMai_Sua[j].getText(), row, j);
        }

        table_KhuyenMai.tb.setModel(table_KhuyenMai.tbModel);

        //Sửa dữ liệu trong database và arraylist trên bus
        //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
        KhuyenMaiDTO DTO = new KhuyenMaiDTO(txt_KhuyenMai_Sua[0].getText(),
                txt_KhuyenMai_Sua[1].getText(),
                Integer.parseInt(txt_KhuyenMai_Sua[2].getText()),
                LocalDate.parse(txt_KhuyenMai_Sua[3].getText()),
                LocalDate.parse(txt_KhuyenMai_Sua[4].getText()),
                txt_KhuyenMai_Sua[5].getText());
        //Tìm vị trí của row cần sửa
        int index = KhuyenMaiBUS.timViTri(maKhuyenMai);
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

        int i = table_KhuyenMai.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_KhuyenMai_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for (int j = 0; j < array_KhuyenMai.length; j++) {
                txt_KhuyenMai_Sua[j].setText(table_KhuyenMai.tb.getValueAt(i, j).toString());
            }

        }
    }

    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {
        int row = table_KhuyenMai.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maKhuyenMai = table_KhuyenMai.tbModel.getValueAt(row, 0).toString();
                //truyền mã Khuyến mãi  vào hàm timViTri ở KhuyenMaiBUS 
                int index = KhuyenMaiBUS.timViTri(maKhuyenMai);
                //Xóa hàng ở table
                table_KhuyenMai.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maKhuyenMai, index);
            }
        }

    }

    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_KhuyenMai.clear();
        for (KhuyenMaiDTO DTO : KhuyenMaiBUS.dskm) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_KhuyenMai.addRow(DTO);
            }
        }
    }

    public void docDB() {
        KhuyenMaiBUS Bus = new KhuyenMaiBUS();
        if (KhuyenMaiBUS.dskm == null) {
            try {
                Bus.docDSKM();
            } catch (Exception ex) {
                Logger.getLogger(GUIKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (KhuyenMaiDTO KhuyenMai : KhuyenMaiBUS.dskm) {
            if (KhuyenMai.getTrangThai().equals("Hiện")) {
                table_KhuyenMai.addRow(KhuyenMai);

            }
        }
    }

    @Override
    protected JPanel TimKiem(){
        JPanel TimKiem=new JPanel(null);
        
        
        JLabel lbTen=new JLabel("");
        lbTen.setBorder(new TitledBorder("Tìm kiếm"));
        int x=200;
        cbSearch = new JComboBox<>(new String[]{"Mã khuyến mãi","Tên chương trình","Nội dung"});
        cbSearch.setBounds(5, 20, 100, 40);
        lbTen.add(cbSearch);
        
        Ten=new JTextField();
        Ten.setBorder(new TitledBorder("Mã khuyến mãi"));
        Ten.setBounds(105, 20, 150, 40);
        lbTen.add(Ten);
        addDocumentListener(Ten);
        
        cbSearch.addActionListener((ActionEvent e) -> {
            Ten.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            Ten.requestFocus();
            
        });
        lbTen.setBounds(x, 0, 265, 70);
        TimKiem.add(lbTen);
        
        JLabel NgayLap=new JLabel("");
        NgayLap.setBorder(new TitledBorder("Thời gian"));
                        
        Tu_NgayBatDau=new JTextField();
        Tu_NgayBatDau.setBorder(new TitledBorder("Từ"));
        Tu_NgayBatDau.setBounds(5, 20, 100, 40);
        Tu_NgayBatDau.setEditable(false);
        NgayLap.add(Tu_NgayBatDau);
        addDocumentListener(Tu_NgayBatDau);
        
        // khoang ngay
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dp_Tu_NgayBatDau = new DatePicker(pickerSettings);        
        dp_Tu_NgayBatDau.setDateToToday();       
        // calendar icon
        JButton calendar=dp_Tu_NgayBatDau.getComponentToggleCalendarButton();
        calendar.setText("");
        calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
        calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        dp_Tu_NgayBatDau.setBounds(105, 25, 35,30);
        NgayLap.add(dp_Tu_NgayBatDau);
        dp_Tu_NgayBatDau.addDateChangeListener((dce) -> {
            Tu_NgayBatDau.setText(dp_Tu_NgayBatDau.getDateStringOrEmptyString());
        });
        
        Den_NgayKetThuc=new JTextField();
        Den_NgayKetThuc.setBorder(new TitledBorder("Đến"));
        Den_NgayKetThuc.setBounds(140, 20, 100, 40);
        Den_NgayKetThuc.setEditable(false);
        NgayLap.add(Den_NgayKetThuc);
        addDocumentListener(Den_NgayKetThuc);
        
        // khoang ngay
        DatePickerSettings pickerSettings2 = new DatePickerSettings();
        pickerSettings2.setVisibleDateTextField(false);
        dp_Den_NgayKetThuc = new DatePicker(pickerSettings2);        
        dp_Den_NgayKetThuc.setDateToToday();       
        // calendar icon
        JButton calendar2=dp_Den_NgayKetThuc.getComponentToggleCalendarButton();
        calendar2.setText("");
        calendar2.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
        calendar2.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        dp_Den_NgayKetThuc.setBounds(240, 25, 35,30);
        NgayLap.add(dp_Den_NgayKetThuc);
        dp_Den_NgayKetThuc.addDateChangeListener((dce) -> {
            Den_NgayKetThuc.setText(dp_Den_NgayKetThuc.getDateStringOrEmptyString());
        });
        NgayLap.setBounds(x+=270, 0, 280, 70);
        TimKiem.add(NgayLap);                
        
        JLabel TienGiam=new JLabel("");
        TienGiam.setBorder(new TitledBorder("Tiền giảm"));
                        
        Tu_TienGiam=new JTextField();
        Tu_TienGiam.setBorder(new TitledBorder("Từ"));
        Tu_TienGiam.setBounds(5, 20, 100, 40);
        TienGiam.add(Tu_TienGiam);
        addDocumentListener(Tu_TienGiam);
        
        
        Den_TienGiam=new JTextField();
        Den_TienGiam.setBorder(new TitledBorder("Đến"));
        Den_TienGiam.setBounds(105, 20, 100, 40);
        TienGiam.add(Den_TienGiam);
        addDocumentListener(Den_TienGiam);
       

        TienGiam.setBounds(x+=285, 0, 215, 70);
        TimKiem.add(TienGiam);
        
        JButton LamMoi=new JButton("Làm mới");
        LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        LamMoi.setFont(new Font("Segoe UI", 0, 14));
        LamMoi.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));        
        LamMoi.setBackground(Color.decode("#90CAF9"));
        LamMoi.setBounds(x+=225, 10, 110, 30);
        LamMoi.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Ten.setText("");
                Tu_NgayBatDau.setText("");
                Den_NgayKetThuc.setText("");
                Tu_TienGiam.setText("");
                Den_TienGiam.setText("");
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
        double donGia1= -1, donGia2 = -1;
        //Ràng buộc
        try {
            donGia1 = Double.parseDouble(Tu_TienGiam.getText());
            Tu_TienGiam.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Tu_TienGiam.setForeground(Color.red);
        }

        try {
            donGia2 = Double.parseDouble(Den_TienGiam.getText());
            Den_TienGiam.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Den_TienGiam.setForeground(Color.red);
        }
        
        //Đẩy dữ liệu đi và nhận lại danh sách đúng với field tìm kiếm
        setDataToTable(Tool.searchKM(Ten.getText(),Tu_NgayBatDau.getText(),Den_NgayKetThuc.getText(), donGia1, donGia2, cbSearch.getSelectedItem().toString()), table_KhuyenMai); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<KhuyenMaiDTO> khuyenMaiDTO, GUIMyTable myTable) {
        myTable.clear();
        for (KhuyenMaiDTO khuyenMai : khuyenMaiDTO) {
            table_KhuyenMai.addRow(khuyenMai);
        }
    }
    
    public boolean checkTextThem(String tenChuongTrinh, String tienGiamGia, String ngayBatDau, String ngayKetThuc, String noiDung) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenChuongTrinh.equals("")
                || tienGiamGia.equals("")
                || noiDung.equals("")
                || ngayBatDau.equals("")
                || ngayKetThuc.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(tenChuongTrinh))) {
            JOptionPane.showMessageDialog(null, "Tên chương trình không được chứa ký tự đặc biệt");
            txt_KhuyenMai_Them[1].requestFocus();
        } else if (!Tool.isLength50(tenChuongTrinh)) {
            JOptionPane.showMessageDialog(null, "Tên chương trình không được quá 50 ký tự");
            txt_KhuyenMai_Them[1].requestFocus();
        } else if (!Tool.isNumber(tienGiamGia)) {
            JOptionPane.showMessageDialog(null, "Tiền giảm giá phải là số nguyên dương");
            txt_KhuyenMai_Them[2].requestFocus();
        } else if (!Tool.isName((tienGiamGia))) {
            JOptionPane.showMessageDialog(null, "Tiền giảm giá không được chứa ký tự đặc biệt");
            txt_KhuyenMai_Them[2].requestFocus();
        } else if (!Tool.isTenThousandToOneMil(tienGiamGia)) {
            JOptionPane.showMessageDialog(null, "Tiền giảm giá phải nằm trong khoảng 10.000 đến 1.000.000");
            txt_KhuyenMai_Them[2].requestFocus();
        } else if (!Tool.ngayBDTruocNgayKT(ngayBatDau, ngayKetThuc)) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu");
            txt_KhuyenMai_Them[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(noiDung))) {
            JOptionPane.showMessageDialog(null, "Nội dung khuyến mãi không được chứa ký tự đặc biệt");
            txt_KhuyenMai_Them[5].requestFocus();
        } else if (!Tool.isLength50(noiDung)) {
            JOptionPane.showMessageDialog(null, "Nội dung khuyến mãi không được quá 50 ký tự");
            txt_KhuyenMai_Them[5].requestFocus();
        } else {
            return true;

        }
        return false;
    }

    public boolean checkTextSua(String tenChuongTrinh, String tienGiamGia, String noiDung, String ngayBatDau, String ngayKetThuc) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenChuongTrinh.equals("")
                || tienGiamGia.equals("")
                || noiDung.equals("")
                || ngayBatDau.equals("")
                || ngayKetThuc.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(tenChuongTrinh))) {
            JOptionPane.showMessageDialog(null, "Tên chương trình không được chứa ký tự đặc biệt");
            txt_KhuyenMai_Sua[1].requestFocus();
        } else if (!Tool.isLength50(tenChuongTrinh)) {
            JOptionPane.showMessageDialog(null, "Tên chương trình không được quá 50 ký tự");
            txt_KhuyenMai_Sua[1].requestFocus();
        } else if (!Tool.isNumber(tienGiamGia)) {
            JOptionPane.showMessageDialog(null, "Tiền giảm giá phải là số nguyên dương");
            txt_KhuyenMai_Sua[2].requestFocus();
        } else if (!Tool.isName((tienGiamGia))) {
            JOptionPane.showMessageDialog(null, "Tiền giảm giá không được chứa ký tự đặc biệt");
            txt_KhuyenMai_Sua[2].requestFocus();
        } else if (!Tool.isTenThousandToOneMil(tienGiamGia)) {
            JOptionPane.showMessageDialog(null, "Tiền giảm giá phải nằm trong khoảng 10.000 đến 1.000.000");
            txt_KhuyenMai_Sua[2].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(noiDung))) {
            JOptionPane.showMessageDialog(null, "Nội dung khuyến mãi không được chứa ký tự đặc biệt");
            txt_KhuyenMai_Sua[3].requestFocus();
        } else if (!Tool.isLength50(noiDung)) {
            JOptionPane.showMessageDialog(null, "Nội dung khuyến mãi không được quá 50 ký tự");
            txt_KhuyenMai_Sua[3].requestFocus();
        } else if (!Tool.ngayBDTruocNgayKT(ngayBatDau, ngayKetThuc)) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu");
            txt_KhuyenMai_Sua[5].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    @Override
    protected void XuatExcel_click(MouseEvent evt) {
        new XuatExcel().xuatFileExcelKhuyenMai();

    }

    @Override
    protected void NhapExcel_click(MouseEvent evt) {
        new DocExcel().docFileExcelKhuyenMai();

    }
}

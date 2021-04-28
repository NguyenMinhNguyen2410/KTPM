/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.KhachHangBUS;
import BUS.Tool;
import DTO.KhachHangDTO;
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
public class GUIKhachHang extends GUIFormContent {

    public static String array_KhachHang[] = {"Mã khách hàng", "Họ", "Tên", "Gmail", "Giới tính", "SĐT", "Tổng chi tiêu"};
    public GUIMyTable table_KhachHang;
    private static JDialog Them_KhachHang;
    private static JDialog Sua;
    private final JLabel label_KhachHang[] = new JLabel[array_KhachHang.length];
    //Phần textfield của thêm
    private JTextField txt_KhachHang_Them[] = new JTextField[array_KhachHang.length];
    //Phần textfield của sửa
    private JTextField txt_KhachHang_Sua[] = new JTextField[array_KhachHang.length];
    private JTextField search;
    private JComboBox cbSearch;
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;
    private KhachHangBUS BUS = new KhachHangBUS();
    private JTextField Ten,Tu_ChiTieu,Den_ChiTieu;
    private JComboBox cbGioiTinh_Them,cbGioiTinh_Sua;
    private String array_GioiTinh[]={"Nam","Nữ"};
    public GUIKhachHang() {
        super();
    }

    @Override
    protected JPanel Table() {
        JPanel panel = new JPanel(null);
        //Tạo đối tượng cho table_KhachHang
        table_KhachHang = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_KhachHang.setHeaders(array_KhachHang);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_KhachHang.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
        table_KhachHang.setBounds(0, 0, GUImenu.width_content, 550);
        panel.add(table_KhachHang);

        return panel;
    }

    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Them_KhachHang = new JDialog(f);
        Them_KhachHang.setLayout(null);
        Them_KhachHang.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_KhachHang.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_KhachHang.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm khách hàng");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_KhachHang.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_KhachHang.length; i++) {
            label_KhachHang[i] = new JLabel(array_KhachHang[i]);
            label_KhachHang[i].setBounds(100, y, 100, 30);
            Them_KhachHang.add(label_KhachHang[i]);
            //Tạo combobox
            if(i==4)
            {
                cbGioiTinh_Them=new JComboBox(array_GioiTinh);
                cbGioiTinh_Them.setBounds(200, y, 150, 30);
                Them_KhachHang.add(cbGioiTinh_Them);
                y+=40;
                continue;
            }
            txt_KhachHang_Them[i] = new JTextField();
            txt_KhachHang_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 

            y += 40;
            Them_KhachHang.add(txt_KhachHang_Them[i]);
        }
        txt_KhachHang_Them[6].setText("0");
        //Tạo nút lưu
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        //Sự kiện khi click
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu =1;
                int a = JOptionPane.showConfirmDialog(Them_KhachHang, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
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
                    table_KhachHang.addRow(DTO);
                    //clear textfield trong Them
                    for (int i = 0; i < array_KhachHang.length; i++) {
                        if(i!=4)
                        txt_KhachHang_Them[i].setText("");
                    }
                    //Tắt cờ hiệu đi 
                    
                    Them_KhachHang.dispose();
                    }
                    

                }else
                    cohieu=0;
            }
        });
        txt_KhachHang_Them[6].setEditable(false);
        Them_KhachHang.add(Luu);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear textfield trong Them
                for (int i = 0; i < array_KhachHang.length; i++) {
                    if(i!=4)
                    txt_KhachHang_Them[i].setText("");
                }
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them_KhachHang.dispose();
            }
        });

        Them_KhachHang.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_KhachHang.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });

        String ma = Tool.tangMa(KhachHangBUS.getMaKhachHangCuoi());
        txt_KhachHang_Them[0].setText(ma);
        txt_KhachHang_Them[0].setEditable(false);
        Them_KhachHang.setVisible(true);

    }

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
        JLabel Title = new JLabel("Sửa khách hàng");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_KhachHang.length; i++) {
            label_KhachHang[i] = new JLabel(array_KhachHang[i]);
            label_KhachHang[i].setBounds(100, y, 100, 30);
            Sua.add(label_KhachHang[i]);
            //Tạo combobox
            if(i==4)
            {
                cbGioiTinh_Sua=new JComboBox(array_GioiTinh);
                cbGioiTinh_Sua.setBounds(200, y, 150, 30);
                Sua.add(cbGioiTinh_Sua);
                y+=40;
                continue;
            }
            txt_KhachHang_Sua[i] = new JTextField();
            txt_KhachHang_Sua[i].setBounds(200, y, 150, 30);

            y += 40;
            Sua.add(txt_KhachHang_Sua[i]);
        }
        //Lưu tất cả dữ liệu trên textfield lên database
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu =1;
                int a = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    if(checkTextSua(txt_KhachHang_Sua[1].getText(),
                            txt_KhachHang_Sua[2].getText(),
                            txt_KhachHang_Sua[3].getText(),
                            cbGioiTinh_Sua.getSelectedItem().toString(),
                            txt_KhachHang_Sua[5].getText(),
                            txt_KhachHang_Sua[6].getText()))
                    {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                    buttonLuu_Sua();
                    
                    Sua.dispose();
                    }
                    
                }else
                    cohieu =0;

            }
        });
        txt_KhachHang_Sua[6].setEditable(false);
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
        int row = table_KhachHang.tb.getSelectedRow();
        int colum = table_KhachHang.tb.getSelectedColumn();
        String maKhachHang = table_KhachHang.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < array_KhachHang.length; j++) {
                if(j!=4)
                    table_KhachHang.tbModel.setValueAt(txt_KhachHang_Sua[j].getText(), row, j);
                else if(j==4)
                    table_KhachHang.tbModel.setValueAt(cbGioiTinh_Sua.getSelectedItem().toString(), row, j);
            }

            table_KhachHang.tb.setModel(table_KhachHang.tbModel);

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
    }

    @Override
    protected void Them_click(MouseEvent evt) {

        Them_Frame();
    }

    //Hàm sự kiện khi click vào nút Sửa
    @Override
    protected void Sua_click(MouseEvent evt) {

        int i = table_KhachHang.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_KhachHang_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for (int j = 0; j < array_KhachHang.length; j++) {
               if(j!=4)
                    txt_KhachHang_Sua[j].setText(table_KhachHang.tb.getValueAt(i, j).toString());
                else if(j==4)
                {
                    int k;
                    for(k=0;k<array_GioiTinh.length;k++)
                        if(table_KhachHang.tb.getValueAt(i, j).toString().equals(array_GioiTinh[k]))
                            cbGioiTinh_Sua.setSelectedIndex(k);
                }

            }

        }
    }

    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {
        int row = table_KhachHang.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maKhachHang = table_KhachHang.tbModel.getValueAt(row, 0).toString();
                //truyền mã khách hàng vào hàm timViTri ở KhachHangBUS 
                int index = KhachHangBUS.timViTri(maKhachHang);
                //Xóa hàng ở table
                table_KhachHang.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maKhachHang, index);
            }
        }

    }

    public void docDB() {
        KhachHangBUS Bus = new KhachHangBUS();
        if (KhachHangBUS.dskh == null) {

            try {
                Bus.docDSKH();
            } catch (Exception ex) {
                Logger.getLogger(GUIMonAn.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        for (KhachHangDTO KhachHang : KhachHangBUS.dskh) {
            if (KhachHang.getTrangThai().equals("Hiện")) {
                table_KhachHang.addRow(KhachHang);

            }
        }

    }

    @Override
    protected JPanel TimKiem() {
//        JPanel TimKiem = new JPanel(null);
//
//        JLabel lbsearch = new JLabel("");
//        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
//        int x = 400;
//        cbSearch = new JComboBox<>(array_KhachHang);
//        cbSearch.setBounds(5, 20, 150, 40);
//        lbsearch.add(cbSearch);
//
//        search = new JTextField();
//        search.setBorder(new TitledBorder(array_KhachHang[0]));
//        search.setBounds(155, 20, 150, 40);
//        lbsearch.add(search);
//        addDocumentListener(search);
//
//        cbSearch.addActionListener((ActionEvent e) -> {
//            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
//            search.requestFocus();
//
//        });
//        lbsearch.setBounds(x, 0, 315, 70);
//        TimKiem.add(lbsearch);
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
        
        
        JButton LamMoi = new JButton("Làm mới");
        LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        LamMoi.setFont(new Font("Segoe UI", 0, 14));
        LamMoi.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));
        LamMoi.setBackground(Color.decode("#90CAF9"));
        LamMoi.setBounds(x += 320, 10, 110, 30);
        LamMoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                Ten.setText("");
                LamMoi();

            }
        });
        TimKiem.add(LamMoi);

        return TimKiem;
    }

    private void LamMoi() {
        table_KhachHang.clear();
        for (KhachHangDTO DTO : KhachHangBUS.dskh) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_KhachHang.addRow(DTO);
            }
        }
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

//    public void txtSearchOnChange() {
//        table_KhachHang.clear();
//        ArrayList<KhachHangDTO> arraylist = Tool.searchKH(search.getText(), cbSearch.getSelectedItem().toString());
//        for (KhachHangDTO DTO : arraylist) {
//            if (DTO.getTrangThai().equals("Hiện")) {
//                table_KhachHang.addRow(DTO);
//
//            }
//        }
//    }
    
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
        setDataToTable(Tool.searchKH(Ten.getText(),donGia1, donGia2, cbSearch.getSelectedItem().toString()), table_KhachHang); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<KhachHangDTO> khachHangDTO, GUIMyTable myTable) {
        myTable.clear();
        for (KhachHangDTO khuyenMai : khachHangDTO) {
            table_KhachHang.addRow(khuyenMai);
        }
    }

    @Override
    protected void XuatExcel_click(MouseEvent evt) {
        new XuatExcel().xuatFileExcelKhachHang();

    }

    @Override
    protected void NhapExcel_click(MouseEvent evt) {
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
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(hoKhachHang))) {
            JOptionPane.showMessageDialog(null, "Họ khách hàng không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[1].requestFocus();
        } else if (!Tool.isLength50(hoKhachHang)) {
            JOptionPane.showMessageDialog(null, "Họ khách hàng không được quá 50 ký tự");
            txt_KhachHang_Them[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(tenKhachHang))) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[2].requestFocus();
        } else if (!Tool.isLength50(tenKhachHang)) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được quá 50 ký tự");
            txt_KhachHang_Them[2].requestFocus();
        }else if (Tool.haveSpace(tenKhachHang.trim())) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được quá 2 từ");
            txt_KhachHang_Them[2].requestFocus();
        }
        else if (!Tool.isGmail(gmail)) {
            JOptionPane.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_KhachHang_Them[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(gioiTinh))) {
            JOptionPane.showMessageDialog(null, "Giới tính không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[4].requestFocus();
        } else if (!Tool.isLength50(gioiTinh)) {
            JOptionPane.showMessageDialog(null, "Giới tính không được quá 50 ký tự");
            txt_KhachHang_Them[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[5].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_KhachHang_Them[5].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_KhachHang_Them[5].requestFocus();
        } else if (!Tool.isTongTien(Tool.removeAccent(tongChiTieu))) {
            JOptionPane.showMessageDialog(null, "Tổng chi tiêu không được chứa ký tự đặc biệt");
            txt_KhachHang_Them[6].requestFocus();
        } else if (!Tool.isNumber(tongChiTieu)) {
            JOptionPane.showMessageDialog(null, "Tổng chi tiêu phải là số nguyên dương");
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
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(hoKhachHang))) {
            JOptionPane.showMessageDialog(null, "Họ khách hàng không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[1].requestFocus();
        } else if (!Tool.isLength50(hoKhachHang)) {
            JOptionPane.showMessageDialog(null, "Họ khách hàng không được quá 50 ký tự");
            txt_KhachHang_Sua[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(tenKhachHang))) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[2].requestFocus();
        } else if (!Tool.isLength50(tenKhachHang)) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được quá 50 ký tự");
            txt_KhachHang_Sua[2].requestFocus();
        }else if (Tool.haveSpace(tenKhachHang.trim())) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được quá 2 từ");
            txt_KhachHang_Sua[2].requestFocus();
        } 
        else if (!Tool.isGmail(gmail)) {
            JOptionPane.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_KhachHang_Sua[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(gioiTinh))) {
            JOptionPane.showMessageDialog(null, "Giới tính không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[4].requestFocus();
        } else if (!Tool.isLength50(gioiTinh)) {
            JOptionPane.showMessageDialog(null, "Giới tính không được quá 50 ký tự");
            txt_KhachHang_Sua[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[5].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_KhachHang_Sua[5].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_KhachHang_Sua[5].requestFocus();
        } else if (!Tool.isTongTien(Tool.removeAccent(tongChiTieu))) {
            JOptionPane.showMessageDialog(null, "Tổng chi tiêu không được chứa ký tự đặc biệt");
            txt_KhachHang_Sua[6].requestFocus();
        } else if (!Tool.isNumber(tongChiTieu)) {
            JOptionPane.showMessageDialog(null, "Tổng chi tiêu phải là số nguyên dương");
            txt_KhachHang_Sua[6].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NguyenLieuBUS;
import BUS.Tool;
import DTO.NguyenLieuDTO;
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
public class GUINguyenLieu extends GUIFormContent {

    //Nút lấy tên ảnh
    private JButton btnFileAnh;
    //Tạo mảng tiêu đề 
    private static String array_NguyenLieu[] = {"Mã", "Tên", "Đơn giá", "Hình ảnh", "Loại", "Đơn vị tính", "Số lượng"};
    //Tạo bảng nguyên liệu
    public GUIMyTable table_NguyenLieu;
    //Tạo Dialog để thêm nguyên liệu
    private static JDialog Them;
    //Tạo Dialog để sửa nguyên liệu
    private static JDialog Sua;
    //Tạo nhãn trong có Dialog thêm sửa
    private final JLabel label_NguyenLieu[] = new JLabel[array_NguyenLieu.length];
    //Phần textfield của thêm
    private JTextField txt_NguyenLieu_Them[] = new JTextField[array_NguyenLieu.length];
    //Phần textfield của sửa
    private JTextField txt_NguyenLieu_Sua[] = new JTextField[array_NguyenLieu.length];
    //field thông tin khi click row
    private JTextField txMaMA, txTenMA, txDonGia, txSoLuong;
    //Tạo nhãn chứa hình ảnh
    private JLabel lbImage;
    //Panel chứa phần show thông tin món ăn
    private JPanel Show;
    //Các textfield trong thanh tìm kiếm
    public JTextField Ten, Tu_DonGia, Den_DonGia, Tu_SoLuong, Den_SoLuong;
    //Tạo đối tượng cho BUS
    NguyenLieuBUS BUS = new NguyenLieuBUS();
    private int cohieu = 0;
    private JComboBox cbDonViTinh_Them,cbDonViTinh_Sua;
    private String array_DonViTinh[]={"Kg","Quả","Bịch"};
    public GUINguyenLieu() {
        super();
    }

    @Override
    protected JPanel Table() {
        JPanel panel = new JPanel(null);
        //Tạo đối tượng cho table_MonAn
        table_NguyenLieu = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_NguyenLieu.setHeaders(array_NguyenLieu);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_NguyenLieu.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
        table_NguyenLieu.setBounds(0, 0, GUImenu.width_content, 300);
        panel.add(table_NguyenLieu);
        //Tạo phần show thông tin
        Show = Show();
        Show.setBounds(0, 300, GUImenu.width_content, 300);
        panel.add(Show);

        return panel;
    }

    private void Them_Frame() {
        JFrame f = new JFrame();
        cohieu = 0;
        Them = new JDialog(f);
        Them.setLayout(null);
        Them.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm nguyên liệu");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_NguyenLieu.length; i++) {
            label_NguyenLieu[i] = new JLabel(array_NguyenLieu[i]);
            label_NguyenLieu[i].setBounds(100, y, 100, 30);
            Them.add(label_NguyenLieu[i]);
            //Tạo combobox
            if(i==5)
            {
                cbDonViTinh_Them=new JComboBox(array_DonViTinh);
                cbDonViTinh_Them.setBounds(200, y, 150, 30);
                Them.add(cbDonViTinh_Them);
                y+=40;
                continue;
            }
            txt_NguyenLieu_Them[i] = new JTextField();
            txt_NguyenLieu_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            if (i == 3) {
                btnFileAnh = new JButton();
                btnFileAnh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/hinhanh-30.png")));
                btnFileAnh.addActionListener((ae) -> {
                    btnFileAnh_Click("Thêm");
                });
                btnFileAnh.setBounds(360, y, 40, 40);
                Them.add(btnFileAnh);
            }
            y += 40;
            Them.add(txt_NguyenLieu_Them[i]);
        }
        
        txt_NguyenLieu_Them[3].setEditable(false);
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        //Sự kiện khi click
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Them, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    //gọi hàm ở bus để thêm dữ liệu 
                    if (checkTextThem(txt_NguyenLieu_Them[0].getText(),
                            txt_NguyenLieu_Them[1].getText(),
                            txt_NguyenLieu_Them[2].getText(),
                            txt_NguyenLieu_Them[3].getText(),
                            txt_NguyenLieu_Them[4].getText(),
                            cbDonViTinh_Them.getSelectedItem().toString(),
                            txt_NguyenLieu_Them[6].getText())) {
                        //Tạo đối tượng với các tham số truyền vào
                        NguyenLieuDTO DTO = new NguyenLieuDTO(txt_NguyenLieu_Them[0].getText(),
                                txt_NguyenLieu_Them[1].getText(),
                                cbDonViTinh_Them.getSelectedItem().toString(),
                                Integer.parseInt(txt_NguyenLieu_Them[2].getText()),
                                txt_NguyenLieu_Them[3].getText(),
                                txt_NguyenLieu_Them[4].getText(),
                                Integer.parseInt(txt_NguyenLieu_Them[6].getText()),
                                "Hiện");

                        BUS.them(DTO); //thêm nguyên liệu bên BUS đã có thêm vào database
                        table_NguyenLieu.addRow(DTO);
                        //clear textfield trong Them
                        for (int i = 0; i < array_NguyenLieu.length; i++) {
                            if(i!=5)
                            txt_NguyenLieu_Them[i].setText("");
                        }
                        Them.dispose();
                    }
                }
                else
                    cohieu = 0;
            }
        });
        Them.add(Luu);

        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Clear textfield
                for (int i = 0; i < array_NguyenLieu.length; i++) {
                    if(i!=5)
                    txt_NguyenLieu_Them[i].setText("");
                }
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them.dispose();
            }
        });

        Them.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });
        String maNguyenLieu = Tool.tangMa(NguyenLieuBUS.getMaMonAnCuoi());
        txt_NguyenLieu_Them[0].setText(maNguyenLieu);
        txt_NguyenLieu_Them[0].setEditable(false);
        Them.setVisible(true);

    }

    private void Sua_Frame() {
        JFrame f = new JFrame();
        cohieu = 0 ;
        Sua = new JDialog(f);
        Sua.setLayout(null);
        Sua.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Sua.setLocationRelativeTo(null);
        Sua.setUndecorated(true);
        //Tạo tiêu đề
        JLabel Title = new JLabel("Sửa nguyên liệu");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_NguyenLieu.length; i++) {
            label_NguyenLieu[i] = new JLabel(array_NguyenLieu[i]);
            label_NguyenLieu[i].setBounds(100, y, 100, 30);
            Sua.add(label_NguyenLieu[i]);
            //Tạo combobox
            if(i==5)
            {
                cbDonViTinh_Sua=new JComboBox(array_DonViTinh);
                cbDonViTinh_Sua.setBounds(200, y, 150, 30);
                Sua.add(cbDonViTinh_Sua);
                y+=40;
                continue;
            }
            txt_NguyenLieu_Sua[i] = new JTextField();
            txt_NguyenLieu_Sua[i].setBounds(200, y, 150, 30);

            //Tạo nút để lấy file ảnh
            if (i == 3) {
                btnFileAnh = new JButton();
                btnFileAnh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/hinhanh-30.png")));
                btnFileAnh.addActionListener((ae) -> {
                    btnFileAnh_Click("Sửa");
                });
                btnFileAnh.setBounds(360, y, 40, 40);
                Sua.add(btnFileAnh);
            }
            y += 40;
            Sua.add(txt_NguyenLieu_Sua[i]);
        }
        txt_NguyenLieu_Sua[3].setEditable(false);
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

                    //Chạy hàm checkText để ràng buộc dữ liệu 
                    if (checkTextSua(txt_NguyenLieu_Sua[0].getText(),
                            txt_NguyenLieu_Sua[1].getText(),
                            txt_NguyenLieu_Sua[2].getText(),
                            txt_NguyenLieu_Sua[3].getText(),
                            txt_NguyenLieu_Sua[4].getText(),
                            cbDonViTinh_Sua.getSelectedItem().toString(),
                            txt_NguyenLieu_Sua[6].getText())) {
                        //Chạy hàm để lưu lại việc sửa dữ liệu    
                        buttonLuu_Sua();
                        for (int i = 0; i < array_NguyenLieu.length; i++) {
                            if(i!=5)
                            txt_NguyenLieu_Sua[i].setText("");
                        }
                        Sua.dispose();
                    }
                }else
                cohieu = 0;

            }
            
        });
        Sua.add(Luu);

        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                
                cohieu = 1;
                Sua.dispose();
            }
        });

        Sua.add(Thoat);
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

    @Override
    protected void Them_click(MouseEvent evt) {

        Them_Frame();

    }

    //Hàm sự kiện khi click vào nút Sửa
    @Override
    protected void Sua_click(MouseEvent evt) {

        int i = table_NguyenLieu.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_NguyenLieu_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for (int j = 0; j < array_NguyenLieu.length; j++) {
               if(j!=5)
                    txt_NguyenLieu_Sua[j].setText(table_NguyenLieu.tb.getValueAt(i, j).toString());
                else if(j==5)
                {
                    int k;
                    for(k=0;k<array_DonViTinh.length;k++)
                        if(table_NguyenLieu.tb.getValueAt(i, j).toString().equals(array_DonViTinh[k]))
                            cbDonViTinh_Sua.setSelectedIndex(k);
                }
            }

        }
    }

    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {
        int row = table_NguyenLieu.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maNguyenLieu = table_NguyenLieu.tbModel.getValueAt(row, 0).toString();
                //truyền mã món ăn vào hàm timViTri ở NguyenLieuBUS 
                int index = NguyenLieuBUS.timViTri(maNguyenLieu);
                //Xóa hàng ở table
                table_NguyenLieu.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maNguyenLieu, index);
            }
        }

    }

    public void docDB() {
        //Nếu dsnl vẫn chưa được đọc thì chạy hàm đọc
        if (NguyenLieuBUS.dsnl == null) {
            try {
                BUS.docDSNL();
            } catch (Exception ex) {
                Logger.getLogger(GUINguyenLieu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Chỉ hiện những nguyên liệu ở trạng thái hiện , trạng thái ẩn là khi xóa
        for (NguyenLieuDTO monAnDTO : NguyenLieuBUS.dsnl) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table_NguyenLieu.addRow(monAnDTO);

            }
        }
    }

    //Show thông tin nguyên liệu
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
        txMaMA.setBorder(BorderFactory.createTitledBorder("Mã nguyên liệu"));
        txTenMA.setBorder(BorderFactory.createTitledBorder("Tên nguyên liệu"));
        txDonGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        // Set các textfield không edit được
        txMaMA.setEditable(false);
        txTenMA.setEditable(false);
        txDonGia.setEditable(false);
        txSoLuong.setEditable(false);
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaMA.setFont(f);
        txTenMA.setFont(f);
        txDonGia.setFont(f);
        txSoLuong.setFont(f);
        //setsize

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
        table_NguyenLieu.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                String id = String.valueOf(table_NguyenLieu.tbModel.getValueAt(table_NguyenLieu.tb.getSelectedRow(), 0));
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

    private void showInfo(String id) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (id != null) {
            // show hình
            for (NguyenLieuDTO ds : NguyenLieuBUS.dsnl) {
                if (ds.getIDNguyenLieu().equals(id)) {
                    //Lấy chiều dài và chiều cao của nhãn lbImage
                    int w = lbImage.getWidth();
                    int h = lbImage.getHeight();
                    //Hàm xử lý khi đưa ảnh lên
                    ImageIcon img = new ImageIcon(getClass().getResource("/Images/MonAn/" + ds.getHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
                    lbImage.setIcon(new ImageIcon(imgScaled));

                    // show info                   
                    txMaMA.setText(ds.getIDNguyenLieu());
                    txTenMA.setText(ds.getTenNguyenLieu());
                    txDonGia.setText(String.valueOf(ds.getDonGia()));
                    txSoLuong.setText(String.valueOf(ds.getSoLuong()));
                    return;
                }
            }
        }
    }

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
        addDocumentListener(Tu_DonGia);

        Den_DonGia = new JTextField();
        Den_DonGia.setBorder(new TitledBorder("Đến"));
        Den_DonGia.setBounds(105, 20, 100, 40);
        DonGia.add(Den_DonGia);
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
        addDocumentListener(Tu_SoLuong);

        Den_SoLuong = new JTextField();
        Den_SoLuong.setBorder(new TitledBorder("Đến"));
        Den_SoLuong.setBounds(105, 20, 100, 40);
        SoLuong.add(Den_SoLuong);
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
                Ten.setText("");
                Tu_DonGia.setText("");
                Den_DonGia.setText("");
                Tu_SoLuong.setText("");
                Den_SoLuong.setText("");
                LamMoi();
            }
        });
        TimKiem.add(LamMoi);

        return TimKiem;
    }

    public void txtSearchOnChange() {
        int soLuong1 = -1, soLuong2 = -1;
        double donGia1 = -1, donGia2 = -1;
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

        setDataToTable(Tool.searchNL(Ten.getText(), donGia1, donGia2, soLuong1, soLuong2), table_NguyenLieu); //chưa sửa xong hỏi Nguyên cái Textfield
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

    private void setDataToTable(ArrayList<NguyenLieuDTO> monAnDTO, GUIMyTable myTable) { //Sai gì nè
        myTable.clear();
        for (NguyenLieuDTO DTO : monAnDTO) {
            table_NguyenLieu.addRow(DTO);
        }
    }

    @Override
    protected void XuatExcel_click(MouseEvent evt) {
        new XuatExcel().xuatFileExcelNguyenLieu();

    }

    @Override
    protected void NhapExcel_click(MouseEvent evt) {
        new DocExcel().docFileExcelNguyenLieu();

    }

    //Hành động khi ấn nút FileAnh
    private void btnFileAnh_Click(String type) {
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 1;
        if (type.equals("Thêm")) {
            //Tạo cửa sổ chọn file
            FileDialog fd = new FileDialog(Them);
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename != null) {
                txt_NguyenLieu_Them[3].setText(filename);

            }
        }
        if (type.equals("Sửa")) {
            //Tạo cửa sổ chọn file
            FileDialog fd = new FileDialog(Sua);
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename != null) {
                txt_NguyenLieu_Sua[3].setText(filename);

            }
        }
        cohieu=0;
    }

    //Ràng buộc dữ liệu
    public boolean checkTextThem(String maNguyenLieu, String tenNguyenLieu, String donGia, String hinhAnh, String loai, String donViTinh, String soLuong) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (maNguyenLieu.equals("")
                || tenNguyenLieu.equals("")
                || donGia.equals("")
                || hinhAnh.equals("")
                || loai.equals("")
                || donViTinh.equals("")
                || soLuong.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(tenNguyenLieu))) {
            JOptionPane.showMessageDialog(null, "Tên nguyên liệu không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Them[1].requestFocus();
        } else if (!Tool.isLength50(tenNguyenLieu)) {
            JOptionPane.showMessageDialog(null, "Tên nguyên liệu không được quá 50 ký tự");
            txt_NguyenLieu_Them[1].requestFocus();
        } else if (!Tool.isNumber(donGia)) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải là số nguyên dương");
            txt_NguyenLieu_Them[2].requestFocus();
        } else if (!Tool.isName((donGia))) {
            JOptionPane.showMessageDialog(null, "Đơn giá không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Them[2].requestFocus();
        } else if (!Tool.isTenThousandToOneMil(donGia)) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải nằm trong khoảng 10.000 đến 1.000.000");
            txt_NguyenLieu_Them[2].requestFocus();
        } else if (!Tool.isHinhAnh(hinhAnh)) {
            JOptionPane.showMessageDialog(null, "Hình ảnh phải được định dạng là : *.jpg hoặc *.png ");
            txt_NguyenLieu_Them[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(loai))) {
            JOptionPane.showMessageDialog(null, "Loại nguyên liệu không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Them[4].requestFocus();
        } else if (!Tool.isLength50(loai)) {
            JOptionPane.showMessageDialog(null, "Loại nguyên liệu không được quá 50 ký tự");
            txt_NguyenLieu_Them[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(donViTinh))) {
            JOptionPane.showMessageDialog(null, "Đơn vị tính không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Them[5].requestFocus();
        } else if (!Tool.isLength50(donViTinh)) {
            JOptionPane.showMessageDialog(null, "Đơn vị tính không được quá 50 ký tự");
            txt_NguyenLieu_Them[5].requestFocus();
        } else if (!Tool.isNumber(soLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương");
            txt_NguyenLieu_Them[6].requestFocus();
        } else if (!Tool.isName(soLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Them[6].requestFocus();
        } else if (!Tool.isOneToOneThousand(soLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng phải nằm trong khoảng 1 đến 1.000");
            txt_NguyenLieu_Them[6].requestFocus();
        } else {
            return true;

        }
        return false;
    }
    
    public boolean checkTextSua(String maNguyenLieu, String tenNguyenLieu, String donGia, String hinhAnh, String loai, String donViTinh, String soLuong) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (maNguyenLieu.equals("")
                || tenNguyenLieu.equals("")
                || donGia.equals("")
                || hinhAnh.equals("")
                || loai.equals("")
                || donViTinh.equals("")
                || soLuong.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(tenNguyenLieu))) {
            JOptionPane.showMessageDialog(null, "Tên nguyên liệu không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Sua[1].requestFocus();
        } else if (!Tool.isLength50(tenNguyenLieu)) {
            JOptionPane.showMessageDialog(null, "Tên nguyên liệu không được quá 50 ký tự");
            txt_NguyenLieu_Sua[1].requestFocus();
        } else if (!Tool.isNumber(donGia)) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải là số nguyên dương");
            txt_NguyenLieu_Sua[2].requestFocus();
        } else if (!Tool.isName((donGia))) {
            JOptionPane.showMessageDialog(null, "Đơn giá không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Sua[2].requestFocus();
        } else if (!Tool.isTenThousandToOneMil(donGia)) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải nằm trong khoảng 10.000 đến 1.000.000");
            txt_NguyenLieu_Sua[2].requestFocus();
        } else if (!Tool.isHinhAnh(hinhAnh)) {
            JOptionPane.showMessageDialog(null, "Hình ảnh phải được định dạng là : *.jpg hoặc *.png ");
            txt_NguyenLieu_Sua[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(loai))) {
            JOptionPane.showMessageDialog(null, "Loại nguyên liệu không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Sua[4].requestFocus();
        } else if (!Tool.isLength50(loai)) {
            JOptionPane.showMessageDialog(null, "Loại nguyên liệu không được quá 50 ký tự");
            txt_NguyenLieu_Sua[4].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(donViTinh))) {
            JOptionPane.showMessageDialog(null, "Đơn vị tính không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Sua[5].requestFocus();
        } else if (!Tool.isLength50(donViTinh)) {
            JOptionPane.showMessageDialog(null, "Đơn vị tính không được quá 50 ký tự");
            txt_NguyenLieu_Sua[5].requestFocus();
        } else if (!Tool.isNumber(soLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương");
            txt_NguyenLieu_Sua[6].requestFocus();
        } else if (!Tool.isName(soLuong)) {

            JOptionPane.showMessageDialog(null, "Số lượng không được chứa ký tự đặc biệt");
            txt_NguyenLieu_Sua[6].requestFocus();
        } else if (!Tool.isOneToOneThousand(soLuong)) {
            JOptionPane.showMessageDialog(null, "Số lượng phải nằm trong khoảng 1 đến 1.000");
            txt_NguyenLieu_Sua[6].requestFocus();
        } else {
            return true;

        }
        return false;
    }
     //Hàm lưu dữ liệu khi sửa
    
    public void buttonLuu_Sua() {

        int row = table_NguyenLieu.tb.getSelectedRow();
        int colum = table_NguyenLieu.tb.getSelectedColumn();
        String maNguyenLieu = table_NguyenLieu.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < array_NguyenLieu.length; j++) {
                if(j!=5)
                    table_NguyenLieu.tbModel.setValueAt(txt_NguyenLieu_Sua[j].getText(), row, j);
                else if(j==5)
                    table_NguyenLieu.tbModel.setValueAt(cbDonViTinh_Sua.getSelectedItem().toString(), row, j);
            }

            table_NguyenLieu.tb.setModel(table_NguyenLieu.tbModel);

            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            NguyenLieuDTO DTO = new NguyenLieuDTO(txt_NguyenLieu_Sua[0].getText(),
                    txt_NguyenLieu_Sua[1].getText(),
                    cbDonViTinh_Sua.getSelectedItem().toString(),
                    Integer.parseInt(txt_NguyenLieu_Sua[2].getText()),
                    txt_NguyenLieu_Sua[3].getText(),
                    txt_NguyenLieu_Sua[4].getText(),
                    Integer.parseInt(txt_NguyenLieu_Sua[6].getText()));
            //Tìm vị trí của row cần sửa
            int index = NguyenLieuBUS.timViTri(maNguyenLieu);
            //Truyền dữ liệu và vị trí vào bus
            BUS.sua(DTO, index);
//        }
    }

    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_NguyenLieu.clear();
        for (NguyenLieuDTO DTO : NguyenLieuBUS.dsnl) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_NguyenLieu.addRow(DTO);
            }
        }
    }
}

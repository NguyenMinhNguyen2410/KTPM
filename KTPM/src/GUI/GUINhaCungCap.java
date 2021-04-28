/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhaCungCapBUS;
import BUS.Tool;
import DTO.NhaCungCapDTO;
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
public class GUINhaCungCap extends GUIFormContent {

    public static String array_NhaCungCap[] = {"Mã nhà cung cấp", "Tên", "SĐT", "Gmail", "Địa chỉ"};
    public GUIMyTable table_NhaCungCap;
    private static JDialog Them_NhaCungCap;
    private static JDialog Sua;
    private final JLabel label_NhaCungCap[] = new JLabel[array_NhaCungCap.length];
    private JTextField txt_NhaCungCap_Them[] = new JTextField[array_NhaCungCap.length];
    //Phần textfield của sửa
    private JTextField txt_NhaCungCap_Sua[] = new JTextField[array_NhaCungCap.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private NhaCungCapBUS BUS = new NhaCungCapBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;

    public GUINhaCungCap() {
        super();
    }

    @Override
    //Tạo Panel chưa Table
    protected JPanel Table() {
        JPanel panel = new JPanel(null);
        //Tạo đối tượng cho table_NhaCungCap
        table_NhaCungCap = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_NhaCungCap.setHeaders(array_NhaCungCap);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_NhaCungCap.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
        table_NhaCungCap.setBounds(0, 0, GUImenu.width_content, 600);
        panel.add(table_NhaCungCap);

        return panel;
    }

    //Hàm tạo Dialog thêm nhà cung cấp
    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Them_NhaCungCap = new JDialog(f);
        Them_NhaCungCap.setLayout(null);
        Them_NhaCungCap.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_NhaCungCap.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_NhaCungCap.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm nhà cung cấp");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_NhaCungCap.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_NhaCungCap.length; i++) {
            label_NhaCungCap[i] = new JLabel(array_NhaCungCap[i]);
            label_NhaCungCap[i].setBounds(100, y, 100, 30);
            Them_NhaCungCap.add(label_NhaCungCap[i]);

            txt_NhaCungCap_Them[i] = new JTextField();
            txt_NhaCungCap_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 

            y += 40;
            Them_NhaCungCap.add(txt_NhaCungCap_Them[i]);
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
                int a = JOptionPane.showConfirmDialog(Them_NhaCungCap, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
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
                    table_NhaCungCap.addRow(DTO);
                    //clear textfield trong Them
                    for (int i = 0; i < array_NhaCungCap.length; i++) {
                        txt_NhaCungCap_Them[i].setText("");
                    }
                    
                    Them_NhaCungCap.dispose();
                    }
                    

                }else
                    cohieu = 0;
            }
        });
        Them_NhaCungCap.add(Luu);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear textfield trong Them
                for (int i = 0; i < array_NhaCungCap.length; i++) {
                    txt_NhaCungCap_Them[i].setText("");
                }
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them_NhaCungCap.dispose();
            }
        });

        Them_NhaCungCap.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_NhaCungCap.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });

        String ma = Tool.tangMa3(NhaCungCapBUS.getMaNhaCungCapCuoi()); //tăng mã
        txt_NhaCungCap_Them[0].setText(ma); //set mã
        txt_NhaCungCap_Them[0].setEditable(false);
        Them_NhaCungCap.setVisible(true);

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
        JLabel Title = new JLabel("Sửa nhà cung cấp");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_NhaCungCap.length; i++) {
            label_NhaCungCap[i] = new JLabel(array_NhaCungCap[i]);
            label_NhaCungCap[i].setBounds(100, y, 100, 30);
            Sua.add(label_NhaCungCap[i]);
            txt_NhaCungCap_Sua[i] = new JTextField();
            txt_NhaCungCap_Sua[i].setBounds(200, y, 150, 30);

            y += 40;
            Sua.add(txt_NhaCungCap_Sua[i]);
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
                    if(checkTextSua(txt_NhaCungCap_Sua[1].getText(), 
                            txt_NhaCungCap_Sua[2].getText(),
                            txt_NhaCungCap_Sua[3].getText(),
                            txt_NhaCungCap_Sua[4].getText()))
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
        int row = table_NhaCungCap.tb.getSelectedRow();
        int colum = table_NhaCungCap.tb.getSelectedColumn();
        String maNhaCungCap = table_NhaCungCap.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < array_NhaCungCap.length; j++) {
                table_NhaCungCap.tbModel.setValueAt(txt_NhaCungCap_Sua[j].getText(), row, j);
            }

            table_NhaCungCap.tb.setModel(table_NhaCungCap.tbModel);

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
    }

    @Override
    protected void Them_click(MouseEvent evt) {

        Them_Frame();
    }

    //Hàm sự kiện khi click vào nút Sửa
    @Override
    protected void Sua_click(MouseEvent evt) {

        int i = table_NhaCungCap.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_NhaCungCap_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for (int j = 0; j < array_NhaCungCap.length; j++) {
                txt_NhaCungCap_Sua[j].setText(table_NhaCungCap.tb.getValueAt(i, j).toString());
            }

        }
    }

    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {
        int row = table_NhaCungCap.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maNhaCungCap = table_NhaCungCap.tbModel.getValueAt(row, 0).toString();
                //truyền mã nhà cung cấp vào hàm timViTri ở NhaCungCapBUS 
                int index = NhaCungCapBUS.timViTri(maNhaCungCap);
                //Xóa hàng ở table
                table_NhaCungCap.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maNhaCungCap, index);
            }
        }

    }

    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_NhaCungCap.clear();
        for (NhaCungCapDTO DTO : NhaCungCapBUS.dsncc) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_NhaCungCap.addRow(DTO);
            }
        }
    }

    public void docDB() {
        NhaCungCapBUS Bus = new NhaCungCapBUS();
        if (NhaCungCapBUS.dsncc == null) {
            try {
                Bus.docDSNCC();
            } catch (Exception ex) {
                Logger.getLogger(GUINhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (NhaCungCapDTO monAnDTO : NhaCungCapBUS.dsncc) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table_NhaCungCap.addRow(monAnDTO);

            }
        }
    }

    @Override
    protected JPanel TimKiem() {
        JPanel TimKiem = new JPanel(null);

        JLabel lbsearch = new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x = 400;
        cbSearch = new JComboBox<>(array_NhaCungCap);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);

        search = new JTextField();
        search.setBorder(new TitledBorder(array_NhaCungCap[0]));
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

    @Override
    protected void XuatExcel_click(MouseEvent evt) {
        new XuatExcel().xuatFileExcelNhaCungCap();

    }

    @Override
    protected void NhapExcel_click(MouseEvent evt) {
        new DocExcel().docFileExcelNhaCungCap();

    }

    public boolean checkTextThem(String tenNhaCungCap, String soDienThoai, String gmail, String diaChi) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenNhaCungCap.equals("")
                || soDienThoai.equals("")
                || gmail.equals("")
                || diaChi.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(tenNhaCungCap))) {
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Them[1].requestFocus();
        } else if (!Tool.isLength50(tenNhaCungCap)) {
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được quá 50 ký tự");
            txt_NhaCungCap_Them[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Them[2].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_NhaCungCap_Them[2].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_NhaCungCap_Them[2].requestFocus();
        } else if (!Tool.isGmail(gmail)) {
            JOptionPane.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_NhaCungCap_Them[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(diaChi))) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Them[4].requestFocus();
        } else if (!Tool.isLength50(diaChi)) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được quá 50 ký tự");
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
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if (!Tool.isName(Tool.removeAccent(tenNhaCungCap))) {
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Sua[1].requestFocus();
        } else if (!Tool.isLength50(tenNhaCungCap)) {
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được quá 50 ký tự");
            txt_NhaCungCap_Sua[1].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Sua[2].requestFocus();
        } else if (!Tool.isLength50(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
            txt_NhaCungCap_Sua[2].requestFocus();
        } else if (!Tool.isPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác");
            txt_NhaCungCap_Sua[2].requestFocus();
        } else if (!Tool.isGmail(gmail)) {
            JOptionPane.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
            txt_NhaCungCap_Sua[3].requestFocus();
        } else if (!Tool.isName(Tool.removeAccent(diaChi))) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được chứa ký tự đặc biệt");
            txt_NhaCungCap_Sua[4].requestFocus();
        } else if (!Tool.isLength50(diaChi)) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được quá 50 ký tự");
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
        setDataToTable(Tool.searchNCC(search.getText(),cbSearch.getSelectedItem().toString()), table_NhaCungCap); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<NhaCungCapDTO> nhaCungCapDTO, GUIMyTable myTable) {
        myTable.clear();
        for (NhaCungCapDTO nhaCungCap : nhaCungCapDTO) {
            table_NhaCungCap.addRow(nhaCungCap);
        }
    }
}

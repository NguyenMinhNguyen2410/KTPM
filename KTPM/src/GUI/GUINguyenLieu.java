/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NguyenLieuBUS;
import BUS.Tool;
import DTO.NguyenLieuDTO;
import EXT.FormContent;
import EXT.MyTable;
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
public class GUINguyenLieu extends FormContent {
    //Nút lấy tên ảnh
    private JButton btnFileAnh;
    //Tạo mảng tiêu đề 
    private static String[] header = {"Mã", "Tên", "Đơn giá", "Hình ảnh", "Loại", "Đơn vị tính", "Số lượng"};
    //Tạo nhãn trong có Dialog thêm sửa
    private final JLabel label_NguyenLieu[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_NguyenLieu_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_NguyenLieu_Sua[] = new JTextField[header.length];
    //field thông tin khi click row
    private JTextField txMaMA, txTenMA, txDonGia, txSoLuong;
    //Tạo nhãn chứa hình ảnh
    private JLabel lbImage;
    //Panel chứa phần show thông tin món ăn
    private JPanel Show;
    //Các textfield trong thanh tìm kiếm
    public JTextField Ten, Tu_DonGia, Den_DonGia, Tu_SoLuong, Den_SoLuong;
    //Tạo đối tượng cho BUS
    private final NguyenLieuBUS BUS = new NguyenLieuBUS();
    private JComboBox cbDonViTinh_Them,cbDonViTinh_Sua;
    private String array_DonViTinh[]={"Kg","Quả","Bịch"};
    public GUINguyenLieu() {
        super();
    }

    @Override
    protected void Them_click(MouseEvent evt) {
        super.Them_click(evt);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm nguyên liệu");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_NguyenLieu[i] = new JLabel(header[i]);
            label_NguyenLieu[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_NguyenLieu[i]);
            //Tạo combobox
            if(i==5)
            {
                cbDonViTinh_Them=new JComboBox(array_DonViTinh);
                cbDonViTinh_Them.setBounds(200, y, 150, 30);
                Them_Frame.add(cbDonViTinh_Them);
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
                Them_Frame.add(btnFileAnh);
            }
            y += 40;
            Them_Frame.add(txt_NguyenLieu_Them[i]);
        }
        
        txt_NguyenLieu_Them[3].setEditable(false);
        
        String maNguyenLieu = Tool.tangMa(NguyenLieuBUS.getMaMonAnCuoi());
        txt_NguyenLieu_Them[0].setText(maNguyenLieu);
        txt_NguyenLieu_Them[0].setEditable(false);
        Them_Frame.setVisible(true);

    }
    @Override
    protected void luuThem_Frame(){
        cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
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
                        table.addRow(DTO);
                        clearThem_Frame();
                        Them_Frame.dispose();
                    }
                }
                else
                    cohieu = 0;
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearThem_Frame(){
        //clear textfield trong Them_Frame
                        for (int i = 0; i < header.length; i++) {
                            if(i!=5)
                            txt_NguyenLieu_Them[i].setText("");
                        }
    }
   @Override
    protected void Sua_click(MouseEvent evt) {
        super.Sua_click(evt);
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Tạo tiêu đề
            JLabel Title = new JLabel("Sửa nguyên liệu");
            Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
            Title.setForeground(Color.decode("#FF4081"));
            Title.setBounds(150, 0, 200, 40);
            Sua_Frame.add(Title);
            int y = 50;
            //Tạo tự động các lable và textfield
            for (int i = 0; i < header.length; i++) {
                label_NguyenLieu[i] = new JLabel(header[i]);
                label_NguyenLieu[i].setBounds(100, y, 100, 30);
                Sua_Frame.add(label_NguyenLieu[i]);
                //Tạo combobox
                if(i==5)
                {
                    cbDonViTinh_Sua=new JComboBox(array_DonViTinh);
                    cbDonViTinh_Sua.setBounds(200, y, 150, 30);
                    Sua_Frame.add(cbDonViTinh_Sua);
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
                    Sua_Frame.add(btnFileAnh);
                }
                y += 40;
                Sua_Frame.add(txt_NguyenLieu_Sua[i]);
            }
            txt_NguyenLieu_Sua[0].setEditable(false);
            txt_NguyenLieu_Sua[3].setEditable(false);
            //Set tự động giá trị các field
            for (int j = 0; j < header.length; j++) {
               if(j!=5)
                    txt_NguyenLieu_Sua[j].setText(table.tb.getValueAt(row, j).toString());
                else if(j==5)
                {
                    int k;
                    for(k=0;k<array_DonViTinh.length;k++)
                        if(table.tb.getValueAt(row, j).toString().equals(array_DonViTinh[k]))
                            cbDonViTinh_Sua.setSelectedIndex(k);
                }
            }
            Sua_Frame.setVisible(true);
        }
    }
    @Override
    protected void luuSua_Frame(){
        cohieu = 1;
                int a = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {

                    //Chạy hàm checkText để ràng buộc dữ liệu 
                    if (checkTextSua(txt_NguyenLieu_Sua[0].getText(),
                            txt_NguyenLieu_Sua[1].getText(),
                            txt_NguyenLieu_Sua[2].getText(),
                            txt_NguyenLieu_Sua[3].getText(),
                            txt_NguyenLieu_Sua[4].getText(),
                            cbDonViTinh_Sua.getSelectedItem().toString(),
                            txt_NguyenLieu_Sua[6].getText())) {
                        int row = table.tb.getSelectedRow();
        int colum = table.tb.getSelectedColumn();
        String maNguyenLieu = table.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for (int j = 0; j < header.length; j++) {
                if(j!=5)
                    table.tbModel.setValueAt(txt_NguyenLieu_Sua[j].getText(), row, j);
                else if(j==5)
                    table.tbModel.setValueAt(cbDonViTinh_Sua.getSelectedItem().toString(), row, j);
            }

            table.tb.setModel(table.tbModel);

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
                        
                        Sua_Frame.dispose();
                    }
                }else
                cohieu = 0;
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearSua_Frame(){
        for (int i = 0; i < header.length; i++) {
            if(i!=5)
            txt_NguyenLieu_Sua[i].setText("");
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
                String maNguyenLieu = table.tbModel.getValueAt(row, 0).toString();
                //truyền mã món ăn vào hàm timViTri ở NguyenLieuBUS 
                int index = NguyenLieuBUS.timViTri(maNguyenLieu);
                //Xóa hàng ở table
                table.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maNguyenLieu, index);
            }
        }

    }

    @Override
    public void docDB() {
        table.setHeaders(header);
        //Nếu ds vẫn chưa được đọc thì chạy hàm đọc
        if (NguyenLieuBUS.ds == null) {
            try {
                BUS.docDB();
            } catch (Exception ex) {
                Logger.getLogger(GUINguyenLieu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Chỉ hiện những nguyên liệu ở trạng thái hiện , trạng thái ẩn là khi xóa
        for (NguyenLieuDTO monAnDTO : NguyenLieuBUS.ds) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table.addRow(monAnDTO);

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
        table.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                String id = String.valueOf(table.tbModel.getValueAt(table.tb.getSelectedRow(), 0));
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
            for (NguyenLieuDTO ds : NguyenLieuBUS.ds) {
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

        setDataToTable(Tool.searchNL(Ten.getText(), donGia1, donGia2, soLuong1, soLuong2), table); //chưa sửa xong hỏi Nguyên cái Textfield
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

    private void setDataToTable(ArrayList<NguyenLieuDTO> monAnDTO, MyTable myTable) { //Sai gì nè
        myTable.clear();
        for (NguyenLieuDTO DTO : monAnDTO) {
            table.addRow(DTO);
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
            FileDialog fd = new FileDialog(Them_Frame);
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename != null) {
                txt_NguyenLieu_Them[3].setText(filename);

            }
        }
        if (type.equals("Sửa")) {
            //Tạo cửa sổ chọn file
            FileDialog fd = new FileDialog(Sua_Frame);
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
    @Override
    protected void LamMoi_click(MouseEvent evt){
        super.LamMoi_click(evt);
        Ten.setText("");
        Tu_DonGia.setText("");
        Den_DonGia.setText("");
        Tu_SoLuong.setText("");
        Den_SoLuong.setText("");
    }
    @Override
    protected void InPDF(){
        
    }
   @Override
    protected void ChiTiet(){
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CongThucBUS;
import BUS.MonAnBUS;
import BUS.Tool;
import DTO.CongThucDTO;
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
public class GUICongThuc extends GUIFormContent{
    //Tạo mảng tiêu đề
    public static String array_CongThuc[]={"Mã công thức","Mã món ăn","Mô tả công thức"};
    //Tạo JTable , GUIMyTable kế thừa từ JTable và được chỉnh sửa
    public GUIMyTable table_CongThuc;
    //Tạo Dialog để thêm công thức
    private static JDialog Them_CongThuc;
    //Tạo Dialog để sửa công thức
    private static JDialog Sua;    
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_CongThuc[] = new JLabel[array_CongThuc.length];
    //Phần textfield của thêm
    private JTextField txt_CongThuc_Them[] = new JTextField[array_CongThuc.length];
    //Phần textfield của sửa
    private JTextField txt_CongThuc_Sua[] = new JTextField[array_CongThuc.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private CongThucBUS BUS = new CongThucBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    //cohieu=0 thì không được bấm ra ngoài. cohieu=1 thì được bấm ra ngoài
    private int cohieu=0;
    
    private JButton ThemMonAn,SuaMonAn;
    public GUICongThuc(){
        super();
    }
    @Override
    //Tạo Panel chưa Table
    protected JPanel Table(){
        JPanel panel =new JPanel(null);
        //Tạo đối tượng cho table_CongThuc
        table_CongThuc = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_CongThuc.setHeaders(array_CongThuc);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_CongThuc.pane.setPreferredSize(new Dimension(GUImenu.width_content*90/100, 300));        
        table_CongThuc.setBounds(0,0,GUImenu.width_content , 550);
        panel.add(table_CongThuc);          
        
        
        return panel;
    }
    //Hàm tạo Dialog thêm công thức
    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu=0;
        Them_CongThuc = new JDialog(f);
        Them_CongThuc.setLayout(null);
        Them_CongThuc.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_CongThuc.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_CongThuc.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm công thức");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_CongThuc.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_CongThuc.length; i++) {
            label_CongThuc[i] = new JLabel(array_CongThuc[i]);
            label_CongThuc[i].setBounds(100, y, 100, 30);
            Them_CongThuc.add(label_CongThuc[i]);

            txt_CongThuc_Them[i]=new JTextField();
            txt_CongThuc_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            if(i==1)
            {
                ThemMonAn=new JButton();
                ThemMonAn.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
                ThemMonAn.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                ThemMonAn.setBounds(355, y, 30,30);
                Them_CongThuc.add(ThemMonAn);
                ThemMonAn.addActionListener((ActionEvent ae) -> {
                    //Tắt cờ hiệu đi
                    cohieu=1;
                    GUIFormChon a = null;
                    try {
                        a = new GUIFormChon(txt_CongThuc_Them[1],"Món ăn");
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(GUIBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    a.setVisible(true);
                    a.addWindowListener(new WindowAdapter(){
                        @Override
                     public void windowClosed(WindowEvent e){
                            cohieu=0;
                            Them_CongThuc.setVisible(true);
                        }
 
                    });
                });
            }
            
            y += 40;
            Them_CongThuc.add(txt_CongThuc_Them[i]);
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
                cohieu=1;
                int a=JOptionPane.showConfirmDialog( Them_CongThuc,"Bạn chắc chứ ?" ,"",JOptionPane.YES_NO_OPTION);
                if(a==JOptionPane.YES_OPTION)
                {
                    if(checkTextThem(txt_CongThuc_Them[1].getText(), txt_CongThuc_Them[2].getText()))
                    {
                    CongThucDTO DTO = new CongThucDTO(txt_CongThuc_Them[0].getText(),
                                                  txt_CongThuc_Them[1].getText(),
                                                  txt_CongThuc_Them[2].getText(),
                                                  "Hiện");
                    
                    BUS.them(DTO); //thêm công thức bên BUS đã có thêm vào database
                    table_CongThuc.addRow(DTO);                    
                    //clear textfield trong Them
                    for(int i=0;i<array_CongThuc.length;i++)
                    {
                        txt_CongThuc_Them[i].setText("");
                    }
                    
                    Them_CongThuc.dispose();
                    }
                }
                else
                    cohieu=0;
            }
        });
        Them_CongThuc.add(Luu);
        
        txt_CongThuc_Them[1].setEditable(false);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear textfield trong Them
                    for(int i=0;i<array_CongThuc.length;i++)
                    {
                        txt_CongThuc_Them[i].setText("");
                    }
                    //Tắt cờ hiệu đi 
                    cohieu=1;
                //Lệnh này để đóng dialog
                Them_CongThuc.dispose();
            }
        });

        Them_CongThuc.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_CongThuc.addWindowListener(new WindowAdapter(){
            @Override
            public void windowDeactivated(WindowEvent e){
                if(cohieu==0)
                JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
            }
            
        });
        
        
        String maNguyenLieu= Tool.tangMa(CongThucBUS.getMaMonAnCuoi());
        txt_CongThuc_Them[0].setText(maNguyenLieu);
        txt_CongThuc_Them[0].setEditable(false);
        Them_CongThuc.setVisible(true);

    }
    //Hàm tạo Dialog sửa món ăn
    private void Sua_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu=0;
        Sua = new JDialog(f);
        Sua.setLayout(null);
        Sua.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Sua.setLocationRelativeTo(null);
        Sua.setUndecorated(true);
        //Tạo tiêu đề
        JLabel Title = new JLabel("Sửa công thức");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_CongThuc.length; i++) {
            label_CongThuc[i] = new JLabel(array_CongThuc[i]);
            label_CongThuc[i].setBounds(100, y, 100, 30);
            Sua.add(label_CongThuc[i]);
            txt_CongThuc_Sua[i] = new JTextField();
            txt_CongThuc_Sua[i].setBounds(200, y, 150, 30);
            if(i==1)
            {
                SuaMonAn=new JButton();
                SuaMonAn.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
                SuaMonAn.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                SuaMonAn.setBounds(355, y, 30,30);
                Sua.add(SuaMonAn);
                SuaMonAn.addActionListener((ActionEvent ae) -> {
                    cohieu=1;
                    GUIFormChon a = null;
                    try {
                        a = new GUIFormChon(txt_CongThuc_Them[1],"Món ăn");
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(GUIBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    a.setVisible(true);
                    a.addWindowListener(new WindowAdapter(){
                        @Override
                     public void windowClosed(WindowEvent e){
                            cohieu=0;
                            Sua.setVisible(true);
                        }
 
                    });
                });
            }
            y += 40;
            Sua.add(txt_CongThuc_Sua[i]);
        }
        //Lưu tất cả dữ liệu trên textfield lên database
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Tắt cờ hiệu đi 
                    cohieu=1;
                int a=JOptionPane.showConfirmDialog( Sua,"Bạn chắc chứ ?" ,"",JOptionPane.YES_NO_OPTION);
                if(a == JOptionPane.YES_OPTION){
                    if (checkTextSua(
                                txt_CongThuc_Sua[1].getText(),
                                txt_CongThuc_Sua[2].getText()
                                )) {
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
        
        txt_CongThuc_Sua[1].setEditable(false);

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
        Sua.addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowDeactivated(WindowEvent e){
                if(cohieu==0)
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                System.out.println(".windowDeactivated()");
            }
            
        });
        Sua.setVisible(true);
        
    }
    //Hàm lưu dữ liệu khi sửa
    public void buttonLuu_Sua() {
        int row = table_CongThuc.tb.getSelectedRow();
        int colum = table_CongThuc.tb.getSelectedColumn();
        String maCongThuc = table_CongThuc.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for(int j=0;j<array_CongThuc.length;j++)
                table_CongThuc.tbModel.setValueAt(txt_CongThuc_Sua[j].getText(), row, j);
            
            table_CongThuc.tb.setModel(table_CongThuc.tbModel);

            
            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            CongThucDTO DTO = new CongThucDTO(txt_CongThuc_Sua[0].getText(),
                                                  txt_CongThuc_Sua[1].getText(),
                                                  txt_CongThuc_Sua[2].getText());
            //Tìm vị trí của row cần sửa
            int index = CongThucBUS.timViTri(maCongThuc);
            //Truyền dữ liệu và vị trí vào bus
            BUS.sua(DTO, index);
//        }
    }
    @Override
    protected void Them_click(MouseEvent evt){
        
        Them_Frame();
    }
    //Hàm sự kiện khi click vào nút Sửa
    @Override
    protected void Sua_click(MouseEvent evt) {
        
        int i = table_CongThuc.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            txt_CongThuc_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for(int j=0;j<array_CongThuc.length;j++)
                txt_CongThuc_Sua[j].setText(table_CongThuc.tb.getValueAt(i, j).toString());
            
        }
    }
    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {       
        int row = table_CongThuc.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {       
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maCongThuc = table_CongThuc.tbModel.getValueAt(row, 0).toString();
                //truyền mã công thức vào hàm timViTri ở CongThucBUS 
                int index = CongThucBUS.timViTri(maCongThuc);
                //Xóa hàng ở table
                table_CongThuc.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maCongThuc, index);
            }
        }

    }
    public void docDB() {
        BUS=new CongThucBUS();
        if(CongThucBUS.CT == null) {
        try {
            BUS.docCT();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GUICongThuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        }
        
        for (CongThucDTO DTO : CongThucBUS.CT) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_CongThuc.addRow(DTO);
                    
            }
        }
    }
    //Hàm khi ấn nút làm mới
    private void LamMoi(){
        table_CongThuc.clear();
        for (CongThucDTO DTO : CongThucBUS.CT) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_CongThuc.addRow(DTO);
            }
        }
    }
    @Override
    protected JPanel TimKiem(){
        JPanel TimKiem=new JPanel(null);
        //Tạo nhãn tìm kiếm 
        JLabel lbsearch=new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x=400;
        //Tạo combobox cho người dùng chọn mục muốn search
        cbSearch = new JComboBox<>(array_CongThuc);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);
        
        search=new JTextField();
        //Set mặc định ở ô số 0 trong mảng array_CongThuc
        search.setBorder(new TitledBorder(array_CongThuc[0]));
        search.setBounds(155, 20, 150, 40);
        lbsearch.add(search);
        addDocumentListener(search);
        
        cbSearch.addActionListener((ActionEvent e) -> {
            //với mỗi giá trị của cbSearch thì sẽ set lại tiêu đề search
            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            search.requestFocus();           
        });
        lbsearch.setBounds(x, 0, 315, 70);
        TimKiem.add(lbsearch);       
        
        //Tạo nút làm mới
        JButton LamMoi=new JButton("Làm mới");
        LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        LamMoi.setFont(new Font("Segoe UI", 0, 14));
        LamMoi.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));        
        LamMoi.setBackground(Color.decode("#90CAF9"));
        LamMoi.setBounds(x+=320, 10, 110, 30);
        LamMoi.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                search.setText("");
                LamMoi();
            }
        });
        TimKiem.add(LamMoi);                
        
        JButton ChiTiet=new JButton("Chi tiết");
        ChiTiet.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        ChiTiet.setFont(new Font("Segoe UI", 0, 14));
        ChiTiet.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));        
        ChiTiet.setBackground(Color.decode("#90CAF9"));
        ChiTiet.setBounds(x, 40, 110, 30);
        ChiTiet.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                GUIFormChon a = null;
                int i=table_CongThuc.tb.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 công thức");
                    return;
                } 
                String MaCongThuc=String.valueOf(table_CongThuc.tbModel.getValueAt(i,0));
                try {
                    a = new GUIFormChon("Chi tiết công thức",MaCongThuc);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GUIBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

            }
        });
        TimKiem.add(ChiTiet);
        
        
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
        table_CongThuc.clear();
        ArrayList<CongThucDTO> arraylist=Tool.searchCT(search.getText(),cbSearch.getSelectedItem().toString() );
        for (CongThucDTO DTO : arraylist) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_CongThuc.addRow(DTO);
                    
            }
        }
    }
    
    public boolean checkTextThem(String MaMonAn, String moTaCongThuc) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (MaMonAn.equals("")
                || moTaCongThuc.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }
//        else if(!MonAnBUS.timMaMonAn(MaMonAn)) {
//            JOptionPane.showMessageDialog(null, "Mã món ăn không tồn tại");
//            txt_CongThuc_Them[1].requestFocus();
//        }
        else if (!Tool.isCongThuc(Tool.removeAccent(moTaCongThuc))) {
            JOptionPane.showMessageDialog(null, "Mô tả công thức không được chứa ký tự đặc biệt");
            txt_CongThuc_Them[2].requestFocus();
        }
        else if (!Tool.isLength50(moTaCongThuc)) {
            JOptionPane.showMessageDialog(null, "Mô tả công thức không được quá 50 ký tự");
            txt_CongThuc_Them[2].requestFocus();
        }
         else {
            return true;

        }
        return false;
    }
    
    public boolean checkTextSua(String MaMonAn, String moTaCongThuc) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (MaMonAn.equals("")
                || moTaCongThuc.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }
//        else if(!MonAnBUS.timMaMonAn(MaMonAn)) {
//            JOptionPane.showMessageDialog(null, "Mã món ăn không tồn tại");
//            txt_CongThuc_Them[1].requestFocus();
//        }
        else if (!Tool.isCongThuc(Tool.removeAccent(moTaCongThuc))) {
            JOptionPane.showMessageDialog(null, "Mô tả công thức không được chứa ký tự đặc biệt");
            txt_CongThuc_Sua[2].requestFocus();
        }
        else if (!Tool.isLength50(moTaCongThuc)) {
            JOptionPane.showMessageDialog(null, "Mô tả công thức không được quá 50 ký tự");
            txt_CongThuc_Sua[2].requestFocus();
        }
         else {
            return true;

        }
        return false;
    }
    
    @Override
    protected void XuatExcel_click(MouseEvent evt){
        new XuatExcel().xuatFileExcelCongThuc();
    }
    @Override
    protected void NhapExcel_click(MouseEvent evt){
        new DocExcel().docFileExcelCongThuc();
    }
}























































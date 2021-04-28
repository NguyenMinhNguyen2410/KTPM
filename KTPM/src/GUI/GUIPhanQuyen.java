/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.PhanQuyenBUS;
import BUS.Tool;
import BUS.TaiKhoanBUS;
import DTO.PhanQuyenDTO;
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
//import java.lang.System.Logger;
//import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class GUIPhanQuyen extends GUIFormContent{
    public static String array_PhanQuyen[]={"Mã quyền","Tên quyền"};
    public String arrString_Quyen[]={"Quản lý bán hàng","Quản lý nhập hàng",
        "Quản lý món ăn","Quản lý nguyên liệu",
        "Quản lý công thức","Quản lý hóa đơn",
        "Quản lý hóa đơn nhập","Quản lý khuyến mãi",
        "Quản lý khách hàng","Quản lý nhân viên",
        "Quản lý nhà cung cấp","Quản lý tài khoản",
        "Quản lý phân quyền","Quản lý thống kê"};
    private String arr_listmotaQuyen[]={"QLBanHang","QLNhapHang","QLMonAn","QLNguyenLieu","QLCongThuc","QLHoaDon","QLHDNhap","QLKhuyenMai",
        "QLKhachHang","QLNhanVien","QLNhaCungCap","QLTaiKhoan","QLPhanQuyen","QLThongKe"};
    private JCheckBox cbPhanQuyen[] = new JCheckBox[arrString_Quyen.length];
    public GUIMyTable table_PhanQuyen;
    private static JDialog Them_PhanQuyen;
    private static JDialog Sua;
    private final JLabel label_PhanQuyen[]=new JLabel[array_PhanQuyen.length];
    private JTextField txt_PhanQuyen_Them[] = new JTextField[array_PhanQuyen.length];
    //Phần textfield của sửa
    private JTextField txt_PhanQuyen_Sua[] = new JTextField[array_PhanQuyen.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private PhanQuyenBUS BUS = new PhanQuyenBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu=0;
    public GUIPhanQuyen(){
        super();
    }
    @Override
    //Tạo Panel chưa Table
    protected JPanel Table(){
        JPanel panel =new JPanel(null);
        //Tạo đối tượng cho table_PhanQuyen
        table_PhanQuyen = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_PhanQuyen.setHeaders(array_PhanQuyen);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_PhanQuyen.pane.setPreferredSize(new Dimension(GUImenu.width_content*90/100, 300));        
        table_PhanQuyen.setBounds(0,0,GUImenu.width_content , 600);
        panel.add(table_PhanQuyen);          
        
        
        return panel;
    }
    //Hàm tạo Dialog thêm công thức
    private void Them_Frame() {
        JFrame f = new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu=0;
        Them_PhanQuyen = new JDialog(f);
        Them_PhanQuyen.setLayout(null);
        Them_PhanQuyen.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_PhanQuyen.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_PhanQuyen.setUndecorated(true);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm phân quyền");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_PhanQuyen.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < array_PhanQuyen.length; i++) {
            label_PhanQuyen[i] = new JLabel(array_PhanQuyen[i]);
            label_PhanQuyen[i].setBounds(100, y, 100, 30);
            Them_PhanQuyen.add(label_PhanQuyen[i]);

            txt_PhanQuyen_Them[i]=new JTextField();
            txt_PhanQuyen_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            
            y += 40;
            Them_PhanQuyen.add(txt_PhanQuyen_Them[i]);
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
                int a=JOptionPane.showConfirmDialog( Them_PhanQuyen,"Bạn chắc chứ ?" ,"",JOptionPane.YES_NO_OPTION);
                if(a==JOptionPane.YES_OPTION)
                {
                    if(checkTextThem(txt_PhanQuyen_Them[1].getText()))
                    {
                        PhanQuyenDTO DTO = new PhanQuyenDTO(txt_PhanQuyen_Them[0].getText(),
                                                  txt_PhanQuyen_Them[1].getText(),
                                                  layMoTaTuCheckBox(),
                                                  "Hiện");
                    
                    BUS.them(DTO);
                    table_PhanQuyen.addRow(DTO);                    
                    //clear textfield trong Them
                    for(int i=0;i<array_PhanQuyen.length;i++)
                    {
                        txt_PhanQuyen_Them[i].setText("");
                    }
                    
                    Them_PhanQuyen.dispose();
                    
                    }
                    
                }else
                    cohieu = 0;
            }
        });
        Them_PhanQuyen.add(Luu);
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, y, 100, 50);
        //Sự kiên khi click
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //clear textfield trong Them
                    for(int i=0;i<array_PhanQuyen.length;i++)
                    {
                        txt_PhanQuyen_Them[i].setText("");
                    }
                    //Tắt cờ hiệu đi 
                    cohieu=1;
                //Lệnh này để đóng dialog
                Them_PhanQuyen.dispose();
            }
        });

        Them_PhanQuyen.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_PhanQuyen.addWindowListener(new WindowAdapter(){
            @Override
            public void windowDeactivated(WindowEvent e){
                if(cohieu==0)
                JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
            }
            
        });
        
        
        String ma = Tool.tangMa(PhanQuyenBUS.getMaPhanQuyenCuoi()); //tăng mã
        txt_PhanQuyen_Them[0].setText(ma); //set mã
        txt_PhanQuyen_Them[0].setEditable(false);
        Them_PhanQuyen.setVisible(true);
        
        //tạo check box
        int toaDoX= 30;
        int toaDoY= 200;
        for(int j=0; j<arrString_Quyen.length; j++)
        {
//            arrString_Quyen[j], false
            cbPhanQuyen[j] = new JCheckBox(arrString_Quyen[j], false);
//            cbPhanQuyen[j].setBorderPainted(true);
//            cbPhanQuyen[j].setBorder(border);
//            cbPhanQuyen[j].setBorderPaintedFlat(true);
            cbPhanQuyen[j].setBounds(toaDoX, toaDoY, 150, 40);
            Them_PhanQuyen.add(cbPhanQuyen[j]);
            toaDoX = toaDoX + 155;
            if(j ==2 || j == 5 || j == 8 || j == 11)
            {
                toaDoX = 30;
                toaDoY = toaDoY + 50;
            }
        }
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
        JLabel Title = new JLabel("Sửa phân quyền");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua.add(Title);
        int y = 50;
        //Tạo tự động các lable và textfield
        for (int i = 0; i < array_PhanQuyen.length; i++) {
            label_PhanQuyen[i] = new JLabel(array_PhanQuyen[i]);
            label_PhanQuyen[i].setBounds(100, y, 100, 30);
            Sua.add(label_PhanQuyen[i]);
            txt_PhanQuyen_Sua[i] = new JTextField();
            txt_PhanQuyen_Sua[i].setBounds(200, y, 150, 30);
            
            y += 40;
            Sua.add(txt_PhanQuyen_Sua[i]);
        }
        //Lưu tất cả dữ liệu trên textfield lên database
        JButton Luu = new JButton("Lưu");
        Luu.setBackground(Color.decode("#90CAF9"));
        Luu.setBounds(100, y, 100, 50);
        Luu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                cohieu = 1;
                int a=JOptionPane.showConfirmDialog( Sua,"Bạn chắc chứ ?" ,"",JOptionPane.YES_NO_OPTION);
                if(a==JOptionPane.YES_OPTION)
                {
                    if(checkTextSua(txt_PhanQuyen_Sua[1].getText()))
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
        Sua.addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowDeactivated(WindowEvent e){
                if(cohieu==0)
                JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
            }
            
        });
        Sua.setVisible(true);
        
        int toaDoX= 50;
        int toaDoY= 200;
        for(int j=0; j<arrString_Quyen.length; j++)
        {
            cbPhanQuyen[j] = new JCheckBox(arrString_Quyen[j], false);
//            if(PhanQuyenBUS.dspq.get(j).getMoTaQuyen().contains(arrString_Quyen[j]))
//            {
//                cbPhanQuyen[j].setSelected(true);
//            }
            cbPhanQuyen[j].setBounds(toaDoX, toaDoY, 150, 40);
            Sua.add(cbPhanQuyen[j]);
            toaDoX = toaDoX + 155;
            if(j ==2 || j == 5 || j == 8 || j == 11)
            {
                toaDoX = 50;
                toaDoY = toaDoY + 50;
            }
        }
        
    }
    //Hàm lưu dữ liệu khi sửa
    public void buttonLuu_Sua() {
        int row = table_PhanQuyen.tb.getSelectedRow();
        int colum = table_PhanQuyen.tb.getSelectedColumn();
        String maPhanQuyen = table_PhanQuyen.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for(int j=0;j<array_PhanQuyen.length;j++)
                table_PhanQuyen.tbModel.setValueAt(txt_PhanQuyen_Sua[j].getText(), row, j);
            
            table_PhanQuyen.tb.setModel(table_PhanQuyen.tbModel);

            
            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            PhanQuyenDTO DTO = new PhanQuyenDTO(txt_PhanQuyen_Sua[0].getText(),
                                                  txt_PhanQuyen_Sua[1].getText(),
                                                  layMoTaTuCheckBox());
            //Tìm vị trí của row cần sửa
            int index = PhanQuyenBUS.timViTri(maPhanQuyen);
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
        
        int i = table_PhanQuyen.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Hiện Dialog lên và set dữ liệu vào các field
            Sua_Frame();
            //set check cho checkbox từ mô tả quyền
            String maQuyen = table_PhanQuyen.tb.getValueAt(i, 0).toString(); // lấy mã quyền từ hàng đã chọn
            for(int j=0;j<arr_listmotaQuyen.length;j++) //duyệt mảng
            {
                if(PhanQuyenBUS.getMoTaQuyenTuMaQuyen(maQuyen).contains(arr_listmotaQuyen[j]))//so sánh xem mô tả quyền của thằng 
//                    được chọn có mô tả những thứ được quản lý trong arr_listmotaQuyen hay không
                {
                    cbPhanQuyen[j].setSelected(true);
                }
            }
            //xong rồi nè
            txt_PhanQuyen_Sua[0].setEnabled(false);
            //Set tự động giá trị các field
            for(int j=0;j<array_PhanQuyen.length;j++)
                txt_PhanQuyen_Sua[j].setText(table_PhanQuyen.tb.getValueAt(i, j).toString());
            
        }
    }
    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click(MouseEvent evt) {       
        int row = table_PhanQuyen.tb.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {       
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                if(checkXoaPhanQuyen(table_PhanQuyen.tb.getValueAt(row, 0).toString()))
                {
                   JOptionPane.showMessageDialog(null, "Vui lòng xóa tài khoản chứa quyền này trước khi xóa");
                }
                else
                {
                    String maPhanQuyen = table_PhanQuyen.tbModel.getValueAt(row, 0).toString();
                    //truyền mã công thức vào hàm timViTri ở PhanQuyenBUS 
                    int index = PhanQuyenBUS.timViTri(maPhanQuyen);
                    //Xóa hàng ở table
                    table_PhanQuyen.tbModel.removeRow(row);
                    //Xóa ở arraylist và đổi chế độ ẩn ở database
                    BUS.xoa(maPhanQuyen, index);
                }
                
            }
        }

    }
    
    //Hàm khi ấn nút làm mới
    private void LamMoi(){
        table_PhanQuyen.clear();
        for (PhanQuyenDTO DTO : PhanQuyenBUS.dspq) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_PhanQuyen.addRow(DTO);
            }
        }
    }
    public void docDB() {
        PhanQuyenBUS monAnBus = new PhanQuyenBUS();
        if(PhanQuyenBUS.dspq == null) {
            
            try {
                monAnBus.docDSPQ();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(GUIPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
        }
        
        for (PhanQuyenDTO DTO : PhanQuyenBUS.dspq) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_PhanQuyen.addRow(DTO);
                    
            }
        }
    }
    @Override
    protected JPanel TimKiem(){
        JPanel TimKiem=new JPanel(null);
        
        
        JLabel lbsearch=new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x=400;
        cbSearch = new JComboBox<>(array_PhanQuyen);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);
        
        search=new JTextField();
        search.setBorder(new TitledBorder(array_PhanQuyen[0]));
        search.setBounds(155, 20, 150, 40);
        lbsearch.add(search);
        addDocumentListener(search);

        cbSearch.addActionListener((ActionEvent e) -> {
            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            search.requestFocus();
        });
        lbsearch.setBounds(x, 0, 315, 70);
        TimKiem.add(lbsearch);       
        
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
        setDataToTable(Tool.searchPQ(search.getText(),cbSearch.getSelectedItem().toString()), table_PhanQuyen); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<PhanQuyenDTO> phanQuyenDTO, GUIMyTable myTable) {
        myTable.clear();
        for (PhanQuyenDTO phanQuyen : phanQuyenDTO) {
            table_PhanQuyen.addRow(phanQuyen);
        }
    }
    
    public boolean checkTextThem(String tenQuyen) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenQuyen.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }else if(!checkCoChonQuyen())
        {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một quyền");
        }
        else if (!Tool.isName(Tool.removeAccent(tenQuyen))) {
            JOptionPane.showMessageDialog(null, "Tên quyền không được chứa ký tự đặc biệt");
            txt_PhanQuyen_Them[1].requestFocus();
        }
        else if (!Tool.isLength50(tenQuyen)) {
            JOptionPane.showMessageDialog(null, "Tên quyền không được quá 50 ký tự");
            txt_PhanQuyen_Them[1].requestFocus();
        }
         else {
            return true;

        }
        return false;
    }
    
    public boolean checkTextSua(String tenQuyen) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenQuyen.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }else if(!checkCoChonQuyen())
        {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một quyền");
        }
        else if (!Tool.isName(Tool.removeAccent(tenQuyen))) {
            JOptionPane.showMessageDialog(null, "Tên quyền không được chứa ký tự đặc biệt");
            txt_PhanQuyen_Sua[1].requestFocus();
        }
        else if (!Tool.isLength50(tenQuyen)) {
            JOptionPane.showMessageDialog(null, "Tên quyền không được quá 50 ký tự");
            txt_PhanQuyen_Sua[1].requestFocus();
        }
         else {
            return true;

        }
        return false;
    }
    @Override
    protected void XuatExcel_click(MouseEvent evt) {
        new XuatExcel().xuatFileExcelPhanQuyen();

    }

    @Override
    protected void NhapExcel_click(MouseEvent evt) {
        new DocExcel().docFileExcelNhanVien();

    }
    
    public String layMoTaTuCheckBox()
    {   
        String moTaQuyen = "";
        for(int i=0;i<arrString_Quyen.length;i++)
        {
            if(cbPhanQuyen[i].isSelected())
            {
                moTaQuyen = moTaQuyen + arr_listmotaQuyen[i];
            }
        }
        return moTaQuyen;
    }
    
    public boolean checkCoChonQuyen()
    {
        for(int i=0; i<14;i++)
        {
            if(cbPhanQuyen[i].isSelected())
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkXoaPhanQuyen(String maQuyen)//kiểm tra xem mã quyền truyền vào có tồn tại trong mảng tài khoản không nếu có thì
            //return true để thông báo không được xóa
            //return false để xóa được
    {
        for(TaiKhoanDTO taiKhoanDTO : TaiKhoanBUS.dstk)
        {
            if(taiKhoanDTO.getIDPhanQuyen().equals(maQuyen))
            {
                return true; //không được xóa
            }
            else if(maQuyen.equals("PQ0") || maQuyen.equals("PQ1") || maQuyen.equals("PQ4"))
            {
                return true;
            }
        }
        return false;// xóa được
                
    }
}


























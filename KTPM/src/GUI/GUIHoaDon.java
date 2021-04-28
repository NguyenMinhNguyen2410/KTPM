/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.HoaDonBUS;
import BUS.Tool;
import DTO.HoaDonDTO;
import DTO.MonAnDTO;
import Excel.DocExcel;
import Excel.XuatExcel;
import Report.PDF;
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
import java.util.Arrays;
import java.util.Vector;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen
 */
public class GUIHoaDon extends GUIFormContent {
    //Tạo mảng tiêu đề
    private static String array_HoaDon[]={"Mã hóa đơn","Mã nhân viên","Mã khách hàng","Mã khuyến mãi","Ngày lập","Tiền giảm giá","Tổng tiền"};   
    //Tạo JTable , GUIMyTable kế thừa từ JTable và được chỉnh sửa
    public GUIMyTable table_HoaDon;
    //Tạo các nút để chọn ngày
    private static DatePicker dp1,dp_Tu_NgayLap,dp_Den_NgayLap;
    //Các textfield trong thanh tìm kiếm
    private JTextField Ten,Tu_TongTien,Den_TongTien,Tu_NgayLap,Den_NgayLap;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private HoaDonBUS BUS=new HoaDonBUS();
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu=0;
    public GUIHoaDon(){
        super();
    }
    
    @Override
    protected JPanel Table(){
        JPanel panel =new JPanel(null);
        //Tạo đối tượng cho table_HoaDon
        table_HoaDon = new GUIMyTable();
        //Tạo tiêu đề bảng
        table_HoaDon.setHeaders(array_HoaDon);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table_HoaDon.pane.setPreferredSize(new Dimension(GUImenu.width_content*90/100, 300));        
        table_HoaDon.setBounds(0,0,GUImenu.width_content , 550);
        panel.add(table_HoaDon);          
        
        return panel;

    }
    
    /**
     *
     * @return
     */
    @Override
    protected JPanel CongCu() {
        JPanel CongCu=new JPanel();
        
        JButton Them=new JButton("Thêm");
        Them.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon//them1-30.png")));
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
        //Tắt nút thêm ở hóa đơn 
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
        //Tắt nút sửa của hóa đơn
        Sua.setEnabled(false);
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
        //Tắt nút xóa của hóa đơn
        Xoa.setEnabled(false);
        CongCu.add(Xoa);
        //Nút nhập excel
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
        //Nút xuất excel
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
        //Nút in PDF
        JButton inPDF=new JButton("In PDF");
        inPDF.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/pdf-30.png")));
        inPDF.setFont(new Font("Segoe UI", 0, 14));
        inPDF.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        inPDF.setBackground(Color.decode("#90CAF9"));
        inPDF.setBounds(1000, 0, 100, 30);
        inPDF.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                int i =table_HoaDon.tb.getSelectedRow();
                if (i == -1){
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để in file");
                }
                else{
                    new PDF().writeHoaDon(String.valueOf(table_HoaDon.tbModel.getValueAt(i, 0)));
                }
            }
        });
        CongCu.add(inPDF);
        return CongCu;
        
        
    }
    //Hàm khi ấn nút làm mới
    private void LamMoi(){
        table_HoaDon.clear();
        for (HoaDonDTO DTO : HoaDonBUS.HD) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_HoaDon.addRow(DTO);
            }
        }
    }
    //Đọc dữ liệu hóa đơn
    public void docDB() {
        HoaDonBUS Bus = new HoaDonBUS();
        if(HoaDonBUS.HD == null) {
            
            try {
                Bus.docHD();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(GUIHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
        }
        
        for (HoaDonDTO HoaDonDTO : HoaDonBUS.HD) {
            if (HoaDonDTO.getTrangThai().equals("Hiện")) {
                table_HoaDon.addRow(HoaDonDTO);
                    
            }
        }
    }
    @Override
    //Hàm tạo thanh tìm kiếm
    protected JPanel TimKiem(){
        JPanel TimKiem=new JPanel(null);
        
        JLabel lbTen=new JLabel("");
        lbTen.setBorder(new TitledBorder("Tìm kiếm"));
        int x=200;
        cbSearch = new JComboBox(new String[]{"Mã hóa đơn","Mã nhân viên","Mã khách hàng","Mã khuyến mãi"});
        cbSearch.setBounds(5, 20, 100, 40);
        lbTen.add(cbSearch);
        
        Ten=new JTextField();
        Ten.setBorder(new TitledBorder("Mã hóa đơn"));
        Ten.setBounds(105, 20, 150, 40);
        lbTen.add(Ten);
        addDocumentListener(Ten);
        
        cbSearch.addActionListener((ActionEvent e) -> {
            Ten.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            Ten.requestFocus();
            
        });
        lbTen.setBounds(x, 0, 265, 70);
        TimKiem.add(lbTen);
        //Tạo cách tìm kiếm bằng ngày
        JLabel NgayLap=new JLabel("");
        NgayLap.setBorder(new TitledBorder("Ngày lập"));
                        
        Tu_NgayLap=new JTextField();
        Tu_NgayLap.setBorder(new TitledBorder("Từ"));
        Tu_NgayLap.setBounds(5, 20, 100, 40);
        Tu_NgayLap.setEditable(false);
        NgayLap.add(Tu_NgayLap);
        addDocumentListener(Tu_NgayLap);
        
        // khoang ngay
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dp_Tu_NgayLap = new DatePicker(pickerSettings);        
        dp_Tu_NgayLap.setDateToToday();       
        // calendar icon
        JButton calendar=dp_Tu_NgayLap.getComponentToggleCalendarButton();
        calendar.setText("");
        calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
        calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        dp_Tu_NgayLap.setBounds(105, 25, 35,30);
        NgayLap.add(dp_Tu_NgayLap);
        dp_Tu_NgayLap.addDateChangeListener((dce) -> {
            Tu_NgayLap.setText(dp_Tu_NgayLap.getDateStringOrEmptyString());
        });
        
        Den_NgayLap=new JTextField();
        Den_NgayLap.setBorder(new TitledBorder("Đến"));
        Den_NgayLap.setBounds(140, 20, 100, 40);
        Den_NgayLap.setEditable(false);
        NgayLap.add(Den_NgayLap);
        addDocumentListener(Den_NgayLap);
        
        // khoang ngay
        DatePickerSettings pickerSettings2 = new DatePickerSettings();
        pickerSettings2.setVisibleDateTextField(false);
        dp_Den_NgayLap = new DatePicker(pickerSettings2);        
        dp_Den_NgayLap.setDateToToday();       
        // calendar icon
        JButton calendar2=dp_Den_NgayLap.getComponentToggleCalendarButton();
        calendar2.setText("");
        calendar2.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
        calendar2.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        dp_Den_NgayLap.setBounds(240, 25, 35,30);
        NgayLap.add(dp_Den_NgayLap);
        dp_Den_NgayLap.addDateChangeListener((dce) -> {
            Den_NgayLap.setText(dp_Den_NgayLap.getDateStringOrEmptyString());
        });
        NgayLap.setBounds(x+=270, 0, 280, 70);
        TimKiem.add(NgayLap);                
        //Tạo cách tìm kiếm bằng tổng tiền
        JLabel TongTien=new JLabel("");
        TongTien.setBorder(new TitledBorder("Tổng tiền"));
                        
        Tu_TongTien=new JTextField();
        Tu_TongTien.setBorder(new TitledBorder("Từ"));
        Tu_TongTien.setBounds(5, 20, 100, 40);
        TongTien.add(Tu_TongTien);
        addDocumentListener(Tu_TongTien);
        
        Den_TongTien=new JTextField();
        Den_TongTien.setBorder(new TitledBorder("Đến"));
        Den_TongTien.setBounds(105, 20, 100, 40);
        TongTien.add(Den_TongTien);
        addDocumentListener(Den_TongTien);
       

        TongTien.setBounds(x+=285, 0, 215, 70);
        TimKiem.add(TongTien);
        //Nút làm mới các field và reset lại table
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
                Tu_NgayLap.setText("");
                Den_NgayLap.setText("");
                Tu_TongTien.setText("");
                Den_TongTien.setText("");
                LamMoi();
            }
        });
        TimKiem.add(LamMoi);                
        //Nút xem chi tiết hóa đơn
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
                int i=table_HoaDon.tb.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hóa đơn");
                    return;
                } 
                String MaHoaDon=String.valueOf(table_HoaDon.tbModel.getValueAt(i,0));
                try {
                    a = new GUIFormChon("Chi tiết hóa đơn",MaHoaDon);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GUIBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                a.setVisible(true);
            }
        });
        TimKiem.add(ChiTiet);
        
        
        return TimKiem;
    }
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
        double donGia1= -1, donGia2 = -1;
        //Ràng buộc
        try {
            donGia1 = Double.parseDouble(Tu_TongTien.getText());
            Tu_TongTien.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Tu_TongTien.setForeground(Color.red);
        }

        try {
            donGia2 = Double.parseDouble(Den_TongTien.getText());
            Den_TongTien.setForeground(Color.black);
        } catch (NumberFormatException e) {
            Den_TongTien.setForeground(Color.red);
        }
        
        //Đẩy dữ liệu đi và nhận lại danh sách đúng với field tìm kiếm
        setDataToTable(Tool.searchHD(Ten.getText(),Tu_NgayLap.getText(),Den_NgayLap.getText(), donGia1, donGia2, cbSearch.getSelectedItem().toString()), table_HoaDon); //chưa sửa xong hỏi Nguyên cái Textfield
    }
    //Set dữ liệu lên lại table
    private void setDataToTable(ArrayList<HoaDonDTO> hoaDonDTO, GUIMyTable myTable) {
        myTable.clear();
        for (HoaDonDTO hoaDon : hoaDonDTO) {
            table_HoaDon.addRow(hoaDon);
        }
    }
    
    @Override
    protected void XuatExcel_click(MouseEvent evt){
        new XuatExcel().xuatFileExcelHoaDon();
    }
    
    @Override
    protected void NhapExcel_click(MouseEvent evt){
        new DocExcel().docFileExcelHoaDon();
    }
}













































































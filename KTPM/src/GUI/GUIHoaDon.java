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
import EXT.FormChon;
import EXT.FormContent;
import EXT.MyTable;
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
public class GUIHoaDon extends FormContent {
    //Tạo mảng tiêu đề
    private static String[] header={"Mã hóa đơn","Mã nhân viên","Mã khách hàng","Mã khuyến mãi","Ngày lập","Tiền giảm giá","Tổng tiền"};   
    //Tạo các nút để chọn ngày
    private static DatePicker dp1,dp_Tu_NgayLap,dp_Den_NgayLap;
    //Các textfield trong thanh tìm kiếm
    private JTextField Ten,Tu_TongTien,Den_TongTien,Tu_NgayLap,Den_NgayLap;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private HoaDonBUS BUS=new HoaDonBUS();
    JButton ChiTiet;
    FormChon a;
    public GUIHoaDon(){
        super();
    }
    
    //Đọc dữ liệu hóa đơn
    @Override
    public void docDB() {
        table.setHeaders(header);
        HoaDonBUS Bus = new HoaDonBUS();
        if(HoaDonBUS.ds == null) {
            
            try {
                Bus.docDB();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(GUIHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
        }
        
        for (HoaDonDTO HoaDonDTO : HoaDonBUS.ds) {
            if (HoaDonDTO.getTrangThai().equals("Hiện")) {
                table.addRow(HoaDonDTO);
                    
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
        setDataToTable(Tool.searchHD(Ten.getText(),Tu_NgayLap.getText(),Den_NgayLap.getText(), donGia1, donGia2, cbSearch.getSelectedItem().toString()), table); //chưa sửa xong hỏi Nguyên cái Textfield
    }
    //Set dữ liệu lên lại table
    private void setDataToTable(ArrayList<HoaDonDTO> hoaDonDTO, MyTable myTable) {
        myTable.clear();
        for (HoaDonDTO hoaDon : hoaDonDTO) {
            table.addRow(hoaDon);
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
    @Override
    protected void Them(){
        
    }
    @Override
    protected void Sua(){
        
    }
    @Override
    protected void LamMoi_click(MouseEvent evt){
        super.LamMoi_click(evt);
        Ten.setText("");
        Tu_NgayLap.setText("");
        Den_NgayLap.setText("");
        Tu_TongTien.setText("");
        Den_TongTien.setText("");
    }
    @Override
    protected void ChiTiet_click(MouseEvent evt){
        a = null;
                int i=table.tb.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(ChiTiet, "Vui lòng chọn 1 hóa đơn");
                    return;
                } 
                String MaHoaDon=String.valueOf(table.tbModel.getValueAt(i,0));
                try {
                    a = new FormChon("Chi tiết hóa đơn",MaHoaDon);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GUIBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                a.setVisible(true);
    }
    @Override
    protected void InPDF_click(MouseEvent evt){
        int i =table.tb.getSelectedRow();
        if (i == -1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để in file");
        }
        else{
            new PDF().writeHoaDon(String.valueOf(table.tbModel.getValueAt(i, 0)));
        }
    }
}
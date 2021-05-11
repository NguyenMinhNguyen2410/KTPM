/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.HoaDonNhapBUS;
import BUS.Tool;
import DTO.HoaDonDTO;
import DTO.HoaDonNhapDTO;
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
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Nguyen
 */
public class GUIHoaDonNhap extends FormContent {
    private static String[] header={"Mã hóa đơn","Mã nhân viên","Mã nhà cung cấp","Ngày nhập","Tổng tiền"};   
    private final JLabel label_HoaDonNhap[]=new JLabel[header.length];
    private JTextField txt_HoaDonNhap_Them[]=new JTextField[header.length];
    private JTextField txt_HoaDonNhap_Sua[]=new JTextField[header.length];
    private static DatePicker dp1,dp_Tu_NgayNhap,dp_Den_NgayNhap;
    private JTextField Ten,Tu_TongTien,Den_TongTien,Tu_NgayNhap,Den_NgayNhap;
    private JComboBox cbSearch;
    private final HoaDonNhapBUS BUS = new HoaDonNhapBUS();
    public GUIHoaDonNhap(){
        super();
        
    }
    @Override
    public void docDB() {
        table.setHeaders(header);
        if(HoaDonNhapBUS.ds == null) {
            
            try {
                BUS.docDB();
            } catch (Exception ex) {
                Logger.getLogger(GUIHoaDonNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        for (HoaDonNhapDTO monAnDTO : HoaDonNhapBUS.ds) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table.addRow(monAnDTO);
                    
            }
        }
    }
    @Override
    protected JPanel TimKiem(){
        JPanel TimKiem=new JPanel(null);
        
        
        JLabel lbTen=new JLabel("");
        lbTen.setBorder(new TitledBorder("Tìm kiếm"));
        int x=200;
        cbSearch = new JComboBox<>(new String[]{"Mã hóa đơn","Mã nhân viên","Mã nhà cung cấp","Mã khuyến mãi"});
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
        
        JLabel NgayNhap=new JLabel("");
        NgayNhap.setBorder(new TitledBorder("Ngày nhập"));
                        
        Tu_NgayNhap=new JTextField();
        Tu_NgayNhap.setBorder(new TitledBorder("Từ"));
        Tu_NgayNhap.setBounds(5, 20, 100, 40);
        Tu_NgayNhap.setEditable(false);
        NgayNhap.add(Tu_NgayNhap);
        addDocumentListener(Tu_NgayNhap);
        
        // khoang ngay
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dp_Tu_NgayNhap = new DatePicker(pickerSettings);        
        dp_Tu_NgayNhap.setDateToToday();       
        // calendar icon
        JButton calendar=dp_Tu_NgayNhap.getComponentToggleCalendarButton();
        calendar.setText("");
        calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
        calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        dp_Tu_NgayNhap.setBounds(105, 25, 35,30);
        NgayNhap.add(dp_Tu_NgayNhap);
        dp_Tu_NgayNhap.addDateChangeListener((dce) -> {
            Tu_NgayNhap.setText(dp_Tu_NgayNhap.getDateStringOrEmptyString());
        });
        
        Den_NgayNhap=new JTextField();
        Den_NgayNhap.setBorder(new TitledBorder("Đến"));
        Den_NgayNhap.setBounds(140, 20, 100, 40);
        Den_NgayNhap.setEditable(false);
        NgayNhap.add(Den_NgayNhap);
        addDocumentListener(Den_NgayNhap);
        
        // khoang ngay
        DatePickerSettings pickerSettings2 = new DatePickerSettings();
        pickerSettings2.setVisibleDateTextField(false);
        dp_Den_NgayNhap = new DatePicker(pickerSettings2);        
        dp_Den_NgayNhap.setDateToToday();       
        // calendar icon
        JButton calendar2=dp_Den_NgayNhap.getComponentToggleCalendarButton();
        calendar2.setText("");
        calendar2.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
        calendar2.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        dp_Den_NgayNhap.setBounds(240, 25, 35,30);
        NgayNhap.add(dp_Den_NgayNhap);
        dp_Den_NgayNhap.addDateChangeListener((dce) -> {
            Den_NgayNhap.setText(dp_Den_NgayNhap.getDateStringOrEmptyString());
        });
        NgayNhap.setBounds(x+=270, 0, 280, 70);
        TimKiem.add(NgayNhap);                
        
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
        setDataToTable(Tool.searchHDN(Ten.getText(),Tu_NgayNhap.getText(),Den_NgayNhap.getText(), donGia1, donGia2, cbSearch.getSelectedItem().toString()), table); //chưa sửa xong hỏi Nguyên cái Textfield
    }
    //Set dữ liệu lên lại table
    private void setDataToTable(ArrayList<HoaDonNhapDTO> hoaDonNhapDTO, MyTable myTable) {
        myTable.clear();
        for (HoaDonNhapDTO hoaDonNhap : hoaDonNhapDTO) {
            table.addRow(hoaDonNhap);
        }
    }
    
    @Override
    protected void XuatExcel_click(MouseEvent evt){
        try {
            new XuatExcel().xuatFileExcelHoaDonNhap();
        } catch (Exception ex) {
            Logger.getLogger(GUIHoaDonNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void NhapExcel_click(MouseEvent evt){
        new DocExcel().docFileExcelHoaDonNhap();
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
        Tu_NgayNhap.setText("");
        Den_NgayNhap.setText("");
        Tu_TongTien.setText("");
        Den_TongTien.setText("");
    }
    @Override
    protected void ChiTiet_click(MouseEvent evt){
        FormChon a = null;
        int i=table.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hóa đơn");
            return;
        } 
        String MaHoaDon=String.valueOf(table.tbModel.getValueAt(i,0));
        try {
        a = new FormChon("Chi tiết hóa đơn nhập",MaHoaDon);
        } catch (Exception ex) {
        Logger.getLogger(GUIBanHang.class.getName()).log(Level.SEVERE, null, ex);
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
                try {
                    new PDF().writeHoaDonNhap(String.valueOf(table.tbModel.getValueAt(table.tb.getSelectedRow(), 0)));
                } catch (Exception ex) {
                    Logger.getLogger(GUIHoaDonNhap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
}



























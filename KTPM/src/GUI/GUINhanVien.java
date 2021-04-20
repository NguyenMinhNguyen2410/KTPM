/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUSNhanVien;
import DTO.DTONhanVien;
import EXT.FormContent;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nguye
 */
public class GUINhanVien extends FormContent {
     
    public static String header[]={"Họ tên","Ngày sinh","Số điện thoại","CMND"};
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_NhanVien[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_NhanVien_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_NhanVien_Sua[] = new JTextField[header.length];
    //Nút lấy tên ảnh
    private JButton btnFileAnh;
    //Tạo đối tượng BUS tương ứng
    private BUSNhanVien BUS=new BUSNhanVien();
    private DatePicker Them_NgaySinh,Sua_NgaySinh;
    public GUINhanVien() {
        super();
    }
    @Override
    protected void docDB(){
        table.setHeaders(header);
        if(BUSNhanVien.ds!=null)
        for(DTONhanVien DTO:BUSNhanVien.ds)
            table.addRow(DTO);
    }
    @Override
    protected void ChiTiet(){
        
    }
    @Override
    protected void InPDF(){
        
    }
    @Override
    protected void Them_click(MouseEvent evt){
        super.Them_click(evt);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm sản phẩm");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_NhanVien[i] = new JLabel(header[i]);
            label_NhanVien[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_NhanVien[i]);
            
            txt_NhanVien_Them[i] = new JTextField();
            txt_NhanVien_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy ngày tháng
            if (i == 1) {
                // khoang ngay
                DatePickerSettings pickerSettings = new DatePickerSettings();
                pickerSettings.setVisibleDateTextField(false);
                Them_NgaySinh = new DatePicker(pickerSettings);
                Them_NgaySinh.setDateToToday();
                // calendar icon
                JButton calendar = Them_NgaySinh.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Them_NgaySinh.setBounds(355, y, 35, 30);
                txt_NhanVien_Them[1].setEditable(false);
                Them_Frame.add(Them_NgaySinh);
                Them_NgaySinh.addDateChangeListener((dce) -> {
                    txt_NhanVien_Them[1].setText(Them_NgaySinh.getDateStringOrEmptyString());

                });
            }
            y += 40;
            Them_Frame.add(txt_NhanVien_Them[i]);
        }
        
        
    }
    @Override
    protected void luuThem_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) { 
                        DTONhanVien DTO = new DTONhanVien();
                        DTO.setIDNhanVien(BUSNhanVien.ds.size()+1);
                        DTO.setHoTen(txt_NhanVien_Them[0].getText());
                        DTO.setNgaySinh(LocalDate.parse((txt_NhanVien_Them[1].getText())));
                        DTO.setSDT(txt_NhanVien_Them[2].getText());
                        DTO.setCMND(txt_NhanVien_Them[3].getText());
                        //Gọi hàm thêm ở bus và truyền đối tượng DTO vào
                        BUS.them(DTO);
                        //Thêm vào table
                        table.addRow(DTO);
                        //clear textfield trong Them_frame
                        clearThem_Frame();
                        //Lệnh này để đóng dialog
                        Them_Frame.dispose();
                }
                else
                    cohieu=0;
    }
    @Override
    protected void Sua_click(MouseEvent evt){
        super.Sua_click(evt);
        Sua_Frame.setVisible(false);
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Sửa sản phẩm");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_NhanVien[i] = new JLabel(header[i]);
            label_NhanVien[i].setBounds(100, y, 100, 30);
            Sua_Frame.add(label_NhanVien[i]);
            
            txt_NhanVien_Sua[i] = new JTextField();
            txt_NhanVien_Sua[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy ngày tháng
            if (i == 1) {
                // khoang ngay
                DatePickerSettings pickerSettings = new DatePickerSettings();
                pickerSettings.setVisibleDateTextField(false);
                Sua_NgaySinh = new DatePicker(pickerSettings);
                Sua_NgaySinh.setDateToToday();
                // calendar icon
                JButton calendar = Sua_NgaySinh.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Sua_NgaySinh.setBounds(355, y, 35, 30);
                txt_NhanVien_Sua[1].setEditable(false);
                Sua_Frame.add(Sua_NgaySinh);
                Sua_NgaySinh.addDateChangeListener((dce) -> {
                    txt_NhanVien_Sua[1].setText(Sua_NgaySinh.getDateStringOrEmptyString());
                });
            }
            y += 40;
            Sua_Frame.add(txt_NhanVien_Sua[i]);
        }
        //Không cho tự nhập
        txt_NhanVien_Sua[4].setEditable(false);
        load_dataSua();
    }
    @Override
    protected void luuSua_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                        DTONhanVien DTO = new DTONhanVien();
                        DTO.setIDNhanVien(BUSNhanVien.ds.size()+1);
                        DTO.setHoTen(txt_NhanVien_Sua[0].getText());
                        DTO.setNgaySinh(LocalDate.parse((txt_NhanVien_Sua[1].getText())));
                        DTO.setSDT(txt_NhanVien_Sua[2].getText());
                        DTO.setCMND(txt_NhanVien_Sua[3].getText());
                        //Gọi hàm thêm ở bus và truyền đối tượng DTO vào
                        BUS.them(DTO);
                        //Thêm vào table
                        table.addRow(DTO);
                        //clear textfield trong Sua_frame
                        clearSua_Frame();
                        //Lệnh này để đóng dialog
                        Sua_Frame.dispose();
                }
                else
                    cohieu=0;
    }
    private void load_dataSua(){
        int i = table.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
            
        } else {            
            //Set tự động giá trị các field
            for (int j = 0; j < header.length; j++) {
                    txt_NhanVien_Sua[j].setText(table.tb.getValueAt(i, j).toString());
            Sua_Frame.setVisible(true);
            }
        }
    }
    @Override
    protected void clearThem_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_NhanVien_Them[i].setText("");
        }
    }
    @Override
    protected void clearSua_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_NhanVien_Sua[i].setText("");
        }
    }
    @Override
    protected void Xoa_click(MouseEvent evt){
        int i = table.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để xóa");
        } else {            
            BUS.xoa(i, i);
        }
    }
}

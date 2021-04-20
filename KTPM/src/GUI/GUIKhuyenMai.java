/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUSKhuyenMai;
import DTO.DTOKhuyenMai;
import EXT.FormContent;
import static GUI.GUIKhuyenMai.header;
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
public class GUIKhuyenMai extends FormContent{
    public static String header[]={"Tên chương trình","Chiết khấu","Mô tả chương trình","Ngày bắt đầu","Ngày kết thúc"};
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_KhuyenMai[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_KhuyenMai_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_KhuyenMai_Sua[] = new JTextField[header.length];
    //Tạo đối tượng BUS tương ứng
    private BUSKhuyenMai BUS=new BUSKhuyenMai();
    private DatePicker Them_NgayBatDau, Them_NgayKetThuc, Sua_NgayBatDau, Sua_NgayKetThuc;
    public GUIKhuyenMai() {
        super();
    }
    @Override
    protected void docDB(){
        table.setHeaders(header);
        if(BUSKhuyenMai.ds!=null)
        for(DTOKhuyenMai DTO:BUSKhuyenMai.ds)
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
        JLabel Title = new JLabel("Thêm khuyến mãi");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_KhuyenMai[i] = new JLabel(header[i]);
            label_KhuyenMai[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_KhuyenMai[i]);
            
            txt_KhuyenMai_Them[i] = new JTextField();
            txt_KhuyenMai_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy ngày tháng
            if (i == 3) {
                // khoang ngay
                DatePickerSettings pickerSettings = new DatePickerSettings();
                pickerSettings.setVisibleDateTextField(false);
                Them_NgayBatDau = new DatePicker(pickerSettings);
                Them_NgayBatDau.setDateToToday();
                // calendar icon
                JButton calendar = Them_NgayBatDau.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Them_NgayBatDau.setBounds(355, y, 35, 30);
                txt_KhuyenMai_Them[3].setEditable(false);
                Them_Frame.add(Them_NgayBatDau);
                Them_NgayBatDau.addDateChangeListener((dce) -> {
                    txt_KhuyenMai_Them[3].setText(Them_NgayBatDau.getDateStringOrEmptyString());

                });
            }
            //Tạo nút để lấy ngày tháng
            if (i == 4) {
                // khoang ngay
                DatePickerSettings pickerSettings = new DatePickerSettings();
                pickerSettings.setVisibleDateTextField(false);
                Them_NgayKetThuc = new DatePicker(pickerSettings);
                Them_NgayKetThuc.setDateToToday();
                // calendar icon
                JButton calendar = Them_NgayKetThuc.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Them_NgayKetThuc.setBounds(355, y, 35, 30);
                txt_KhuyenMai_Them[4].setEditable(false);
                Them_Frame.add(Them_NgayKetThuc);
                Them_NgayKetThuc.addDateChangeListener((dce) -> {
                    txt_KhuyenMai_Them[4].setText(Them_NgayKetThuc.getDateStringOrEmptyString());

                });
            }
            y += 40;
            Them_Frame.add(txt_KhuyenMai_Them[i]);
        }
        
        
    }
    @Override
    protected void luuThem_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) { 
                        DTOKhuyenMai DTO = new DTOKhuyenMai();
                        DTO.setIDKhuyenMai(BUSKhuyenMai.ds.size()+1);
                        DTO.setTenChuongTrinh(txt_KhuyenMai_Them[0].getText());
                        DTO.setChietKhau(Integer.parseInt(txt_KhuyenMai_Them[1].getText()));
                        DTO.setMoTaChuongTrinh(txt_KhuyenMai_Them[2].getText());
                        DTO.setNgayBatDau(LocalDate.parse((txt_KhuyenMai_Them[3].getText())));
                        DTO.setNgayKetThuc(LocalDate.parse((txt_KhuyenMai_Them[4].getText())));
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
        JLabel Title = new JLabel("Sửa khuyến mãi");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Sua_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_KhuyenMai[i] = new JLabel(header[i]);
            label_KhuyenMai[i].setBounds(100, y, 100, 30);
            Sua_Frame.add(label_KhuyenMai[i]);
            
            txt_KhuyenMai_Sua[i] = new JTextField();
            txt_KhuyenMai_Sua[i].setBounds(200, y, 150, 30);
             //Tạo nút để lấy ngày tháng
            if (i == 3) {
                // khoang ngay
                DatePickerSettings pickerSettings = new DatePickerSettings();
                pickerSettings.setVisibleDateTextField(false);
                Sua_NgayBatDau = new DatePicker(pickerSettings);
                Sua_NgayBatDau.setDateToToday();
                // calendar icon
                JButton calendar = Sua_NgayBatDau.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Sua_NgayBatDau.setBounds(355, y, 35, 30);
                txt_KhuyenMai_Sua[3].setEditable(false);
                Sua_Frame.add(Sua_NgayBatDau);
                Sua_NgayBatDau.addDateChangeListener((dce) -> {
                    txt_KhuyenMai_Sua[3].setText(Sua_NgayBatDau.getDateStringOrEmptyString());

                });
            }
            //Tạo nút để lấy ngày tháng
            if (i == 4) {
                // khoang ngay
                DatePickerSettings pickerSettings = new DatePickerSettings();
                pickerSettings.setVisibleDateTextField(false);
                Sua_NgayKetThuc = new DatePicker(pickerSettings);
                Sua_NgayKetThuc.setDateToToday();
                // calendar icon
                JButton calendar = Sua_NgayKetThuc.getComponentToggleCalendarButton();
                calendar.setText("");
                calendar.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/calendar-30.png")));
                calendar.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                Sua_NgayKetThuc.setBounds(355, y, 35, 30);
                txt_KhuyenMai_Sua[4].setEditable(false);
                Sua_Frame.add(Sua_NgayKetThuc);
                Sua_NgayKetThuc.addDateChangeListener((dce) -> {
                    txt_KhuyenMai_Sua[4].setText(Sua_NgayKetThuc.getDateStringOrEmptyString());

                });
            }
            y += 40;
            Sua_Frame.add(txt_KhuyenMai_Sua[i]);
        }
        //Không cho tự nhập
        txt_KhuyenMai_Sua[4].setEditable(false);
        load_dataSua();
    }
    @Override
    protected void luuSua_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                        DTOKhuyenMai DTO = new DTOKhuyenMai();
                        DTO.setIDKhuyenMai(BUSKhuyenMai.ds.size()+1);
                        DTO.setTenChuongTrinh(txt_KhuyenMai_Them[0].getText());
                        DTO.setChietKhau(Integer.parseInt(txt_KhuyenMai_Them[1].getText()));
                        DTO.setMoTaChuongTrinh(txt_KhuyenMai_Them[2].getText());
                        DTO.setNgayBatDau(LocalDate.parse((txt_KhuyenMai_Them[3].getText())));
                        DTO.setNgayKetThuc(LocalDate.parse((txt_KhuyenMai_Them[4].getText())));
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
                    txt_KhuyenMai_Sua[j].setText(table.tb.getValueAt(i, j).toString());
            Sua_Frame.setVisible(true);
            }
        }
    }
    @Override
    protected void clearThem_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_KhuyenMai_Them[i].setText("");
        }
    }
    @Override
    protected void clearSua_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_KhuyenMai_Sua[i].setText("");
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

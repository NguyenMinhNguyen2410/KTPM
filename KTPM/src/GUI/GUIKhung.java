/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUSKhung;
import DTO.DTOKhung;
import EXT.FormContent;
import static GUI.GUIKhung.header;
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
public class GUIKhung extends FormContent{
    public static String header[]={"Mã khung","Mã sản phẩm","Đơn giá","Màu"};
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_Khung[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_Khung_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_Khung_Sua[] = new JTextField[header.length];
    //Nút lấy tên ảnh
    private JButton btnFileAnh;
    //Tạo đối tượng BUS tương ứng
    private BUSKhung BUS=new BUSKhung();
    public GUIKhung() {
        super();
    }
    @Override
    protected void docDB(){
        table.setHeaders(header);
        if(BUSKhung.ds!=null)
        for(DTOKhung DTO:BUSKhung.ds)
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
            label_Khung[i] = new JLabel(header[i]);
            label_Khung[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_Khung[i]);
            
            txt_Khung_Them[i] = new JTextField();
            txt_Khung_Them[i].setBounds(200, y, 150, 30);
            
            y += 40;
            Them_Frame.add(txt_Khung_Them[i]);
        }
        
        
    }
    @Override
    protected void luuThem_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) { 
                        DTOKhung DTO = new DTOKhung();
                        DTO.setIDKhung(txt_Khung_Them[0].getText());
                        DTO.setIDSanPham(Integer.parseInt(txt_Khung_Them[1].getText()));
                        DTO.setDonGia(Float.valueOf(txt_Khung_Them[2].getText()));
                        DTO.setMau(txt_Khung_Them[3].getText());
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
            label_Khung[i] = new JLabel(header[i]);
            label_Khung[i].setBounds(100, y, 100, 30);
            Sua_Frame.add(label_Khung[i]);
            
            txt_Khung_Sua[i] = new JTextField();
            txt_Khung_Sua[i].setBounds(200, y, 150, 30);
            
            y += 40;
            Sua_Frame.add(txt_Khung_Sua[i]);
        }
        //Không cho tự nhập
        txt_Khung_Sua[4].setEditable(false);
        load_dataSua();
    }
    @Override
    protected void luuSua_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                        DTOKhung DTO = new DTOKhung();
                        DTO.setIDKhung(txt_Khung_Them[0].getText());
                        DTO.setIDSanPham(Integer.parseInt(txt_Khung_Them[1].getText()));
                        DTO.setDonGia(Float.valueOf(txt_Khung_Them[2].getText()));
                        DTO.setMau(txt_Khung_Them[3].getText());
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
                    txt_Khung_Sua[j].setText(table.tb.getValueAt(i, j).toString());
            Sua_Frame.setVisible(true);
            }
        }
    }
    @Override
    protected void clearThem_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_Khung_Them[i].setText("");
        }
    }
    @Override
    protected void clearSua_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_Khung_Sua[i].setText("");
        }
    }
    @Override
    protected void Xoa_click(MouseEvent evt){
        int i = table.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để xóa");
            
        } else {            
            BUS.xoa(table.tb.getValueAt(i, 0).toString(), i);
        }
        
    }
}

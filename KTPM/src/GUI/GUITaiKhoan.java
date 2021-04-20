/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUSTaiKhoan;
import DTO.DTOTaiKhoan;
import EXT.FormContent;
import static GUI.GUITaiKhoan.header;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nguye
 */
public class GUITaiKhoan extends FormContent{
    public static String header[]={"Tài khoản","Mật khẩu","Mã nhân viên","Mã phân quyền"};
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_TaiKhoan[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_TaiKhoan_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_TaiKhoan_Sua[] = new JTextField[header.length];
    //Nút lấy tên ảnh
    private JButton btnFileAnh;
    //Tạo đối tượng BUS tương ứng
    private BUSTaiKhoan BUS=new BUSTaiKhoan();
    public GUITaiKhoan() {
        super();
    }
    @Override
    protected void docDB(){
        table.setHeaders(header);
        if(BUSTaiKhoan.ds!=null)
        for(DTOTaiKhoan DTO:BUSTaiKhoan.ds)
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
            label_TaiKhoan[i] = new JLabel(header[i]);
            label_TaiKhoan[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_TaiKhoan[i]);
            
            txt_TaiKhoan_Them[i] = new JTextField();
            txt_TaiKhoan_Them[i].setBounds(200, y, 150, 30);
            y += 40;
            Them_Frame.add(txt_TaiKhoan_Them[i]);
        }
        
        
    }
    @Override
    protected void luuThem_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) { 
                        DTOTaiKhoan DTO = new DTOTaiKhoan();
                        DTO.setTaiKhoan(txt_TaiKhoan_Them[0].getText());
                        DTO.setMatKhau(txt_TaiKhoan_Them[1].getText());
                        DTO.setIDNhanVien(Integer.parseInt(txt_TaiKhoan_Them[2].getText()));
                        DTO.setIDPhanQuyen(Integer.parseInt(txt_TaiKhoan_Them[3].getText()));
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
            label_TaiKhoan[i] = new JLabel(header[i]);
            label_TaiKhoan[i].setBounds(100, y, 100, 30);
            Sua_Frame.add(label_TaiKhoan[i]);
            
            txt_TaiKhoan_Sua[i] = new JTextField();
            txt_TaiKhoan_Sua[i].setBounds(200, y, 150, 30);
            y += 40;
            Sua_Frame.add(txt_TaiKhoan_Sua[i]);
        }
        //Không cho tự nhập
        txt_TaiKhoan_Sua[4].setEditable(false);
        load_dataSua();
    }
    @Override
    protected void luuSua_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                        DTOTaiKhoan DTO = new DTOTaiKhoan();
                        DTO.setTaiKhoan(txt_TaiKhoan_Them[0].getText());
                        DTO.setMatKhau(txt_TaiKhoan_Them[1].getText());
                        DTO.setIDNhanVien(Integer.parseInt(txt_TaiKhoan_Them[2].getText()));
                        DTO.setIDPhanQuyen(Integer.parseInt(txt_TaiKhoan_Them[3].getText()));
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
                    txt_TaiKhoan_Sua[j].setText(table.tb.getValueAt(i, j).toString());
            Sua_Frame.setVisible(true);
            }
        }
    }
    @Override
    protected void clearThem_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_TaiKhoan_Them[i].setText("");
        }
    }
    @Override
    protected void clearSua_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_TaiKhoan_Sua[i].setText("");
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUSSanPham;
import DTO.DTOSanPham;
import EXT.FormContent;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nguye
 */
public class GUISanPham extends FormContent{
    public static String header[]={"Tên sản phẩm","Số lượng","Dòng","Chi tiết","Hình ảnh"};
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_SanPham[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_SanPham_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_SanPham_Sua[] = new JTextField[header.length];
    //Nút lấy tên ảnh
    private JButton btnFileAnh;
    //Tạo đối tượng BUS tương ứng
    private BUSSanPham BUS=new BUSSanPham();
    public GUISanPham() {
        super();
    }
    
    @Override
    protected void docDB(){
        table.setHeaders(header);
        if(BUSSanPham.ds!=null)
        for(DTOSanPham DTO:BUSSanPham.ds)
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
            label_SanPham[i] = new JLabel(header[i]);
            label_SanPham[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_SanPham[i]);
            
            txt_SanPham_Them[i] = new JTextField();
            txt_SanPham_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            if (i == 4) {
                btnFileAnh = new JButton();
                btnFileAnh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/hinhanh-30.png")));
                btnFileAnh.addActionListener((ae) -> {
                    btnFileAnh_Click("Thêm");
                });
                btnFileAnh.setBounds(360, y, 40, 40);
                Them_Frame.add(btnFileAnh);
            }
            y += 40;
            Them_Frame.add(txt_SanPham_Them[i]);
        }
        //Không cho tự nhập
        txt_SanPham_Them[4].setEditable(false);
        
    }
    @Override
    protected void luuThem_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Them_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) { 
                        DTOSanPham DTO = new DTOSanPham();
                        DTO.setIDSanPham(BUSSanPham.ds.size()+1);
                        DTO.setTenSanPham(txt_SanPham_Them[0].getText());
                        DTO.setSoLuong(Integer.parseInt(txt_SanPham_Them[1].getText()));
                        DTO.setDong(txt_SanPham_Them[2].getText());
                        DTO.setChiTiet(txt_SanPham_Them[3].getText());
                        DTO.setHinhAnh(txt_SanPham_Them[4].getText());
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
            label_SanPham[i] = new JLabel(header[i]);
            label_SanPham[i].setBounds(100, y, 100, 30);
            Sua_Frame.add(label_SanPham[i]);
            
            txt_SanPham_Sua[i] = new JTextField();
            txt_SanPham_Sua[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            if (i == 4) {
                btnFileAnh = new JButton();
                btnFileAnh.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/hinhanh-30.png")));
                btnFileAnh.addActionListener((ae) -> {
                    btnFileAnh_Click("Thêm");
                });
                btnFileAnh.setBounds(360, y, 40, 40);
                Sua_Frame.add(btnFileAnh);
            }
            y += 40;
            Sua_Frame.add(txt_SanPham_Sua[i]);
        }
        //Không cho tự nhập
        txt_SanPham_Sua[4].setEditable(false);
        load_dataSua();
    }
    @Override
    protected void luuSua_Frame(){
        cohieu=1;
                int a = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                        DTOSanPham DTO = new DTOSanPham();
                        DTO.setIDSanPham(BUSSanPham.ds.size()+1);
                        DTO.setTenSanPham(txt_SanPham_Sua[0].getText());
                        DTO.setSoLuong(Integer.parseInt(txt_SanPham_Sua[1].getText()));
                        DTO.setDong(txt_SanPham_Sua[2].getText());
                        DTO.setChiTiet(txt_SanPham_Sua[3].getText());
                        DTO.setHinhAnh(txt_SanPham_Sua[4].getText());
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
                    txt_SanPham_Sua[j].setText(table.tb.getValueAt(i, j).toString());
            Sua_Frame.setVisible(true);
            }
        }
    }
    @Override
    protected void clearThem_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_SanPham_Them[i].setText("");
        }
    }
    @Override
    protected void clearSua_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            txt_SanPham_Sua[i].setText("");
        }
    }
    //Hành động khi ấn nút FileAnh
    private void btnFileAnh_Click(String type) {
        //bật lên 1 cửa sổ mới nên cần gán giá trị 1
        cohieu = 1;
        if (type.equals("Thêm")) {
            //Tạo cửa sổ chọn file
            FileDialog fd = new FileDialog(Them_Frame);
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename != null) {
                txt_SanPham_Them[4].setText(filename);

            }
        }
        if (type.equals("Sửa")) {
            //Tạo cửa sổ chọn file
            FileDialog fd = new FileDialog(Sua_Frame);
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename != null) {
                txt_SanPham_Sua[4].setText(filename);
            }
        }
        //đã thực hiện xong thì set lại giá trị 0
        cohieu = 0;
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

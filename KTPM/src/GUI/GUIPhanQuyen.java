/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUSPhanQuyen;
import DTO.DTOPhanQuyen;
import EXT.FormContent;
import static GUI.GUIPhanQuyen.header;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nguye
 */
public class GUIPhanQuyen extends FormContent{
    public static String header[]={"Tên quyền","Mô tả quyền"};
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_PhanQuyen[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_PhanQuyen_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_PhanQuyen_Sua[] = new JTextField[header.length];
    //Nút lấy tên ảnh
    private BUSPhanQuyen BUS=new BUSPhanQuyen();  
    public String arrString_Quyen[]={"Quản lý bán hàng","Quản lý nhập hàng",
        "Quản lý món ăn","Quản lý nguyên liệu",
        "Quản lý công thức","Quản lý hóa đơn",
        "Quản lý hóa đơn nhập","Quản lý khuyến mãi",
        "Quản lý khách hàng","Quản lý nhân viên",
        "Quản lý nhà cung cấp","Quản lý tài khoản",
        "Quản lý phân quyền","Quản lý thống kê"};
    private String arr_listmotaQuyen[]={"QLBanHang","QLNhapHang","QLMonAn","QLNguyenLieu","QLCongThuc","QLHoaDon","QLHDNhap","QLKhuyenMai",
        "QLKhachHang","QLNhanVien","QLNhaCungCap","QLTaiKhoan","QLPhanQuyen","QLThongKe"};
    private JCheckBox cbPhanQuyen = new JCheckBox();
    private JComboBox ccbPhanQuyen;
    private boolean checkQuyen[]=new boolean[arrString_Quyen.length];
    public GUIPhanQuyen() {
        super();
    }
    @Override
    protected void docDB(){
        table.setHeaders(header);
        if(BUSPhanQuyen.ds!=null)
        for(DTOPhanQuyen DTO:BUSPhanQuyen.ds)
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
            label_PhanQuyen[i] = new JLabel(header[i]);
            label_PhanQuyen[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_PhanQuyen[i]);
            
            if(i==1)
            {
                ccbPhanQuyen=new JComboBox(arrString_Quyen);
                ccbPhanQuyen.setBounds(200, y, 150, 30);
                ccbPhanQuyen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if(checkQuyen[ccbPhanQuyen.getSelectedIndex()])
                        {
                            cbPhanQuyen.setSelected(true);
                        }
                        else
                        {
                            cbPhanQuyen.setSelected(false);
                        }
                    }
                });
                Them_Frame.add(ccbPhanQuyen);
                
                cbPhanQuyen.setBounds(360, y, 150, 30);
                cbPhanQuyen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        JCheckBox cb = (JCheckBox) event.getSource();
                        if (cb.isSelected()) {
                            checkQuyen[ccbPhanQuyen.getSelectedIndex()]=true;
                        } else {
                            checkQuyen[ccbPhanQuyen.getSelectedIndex()]=false;
                        }
                    }
                });
                Them_Frame.add(cbPhanQuyen);
                continue;
            }
            txt_PhanQuyen_Them[i] = new JTextField();
            txt_PhanQuyen_Them[i].setBounds(200, y, 150, 30);
            y += 40;
            Them_Frame.add(txt_PhanQuyen_Them[i]);
        }
        for(int i=0;i<checkQuyen.length;i++)
            checkQuyen[i]=false;
        
    }
    @Override
    protected void luuThem_Frame(){
        cohieu = 1;
                int a=JOptionPane.showConfirmDialog( Them_Frame,"Bạn chắc chứ ?" ,"",JOptionPane.YES_NO_OPTION);
                if(a==JOptionPane.YES_OPTION)
                {
                    DTOPhanQuyen DTO = new DTOPhanQuyen();
                    DTO.setIDPhanQuyen(BUSPhanQuyen.ds.size()+1);
                    DTO.setTenQuyen(txt_PhanQuyen_Them[0].getText());
                    DTO.setMoTaQuyen(layMoTaTuCheckBox());
                    BUS.them(DTO);
                    table.addRow(DTO);                    
                    //clear textfield trong Them
                    for(int i=0;i<header.length;i++)
                    {
                        txt_PhanQuyen_Them[i].setText("");
                    }
                    Them_Frame.dispose();
                    
                    
                    
                }else
                    cohieu = 0;
    }
    public String layMoTaTuCheckBox()
    {   
        String moTaQuyen = "";
        for(int i=0;i<checkQuyen.length;i++)
        {
            if(checkQuyen[i])
                moTaQuyen+=arr_listmotaQuyen[i];
        }
        return moTaQuyen;
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
            label_PhanQuyen[i] = new JLabel(header[i]);
            label_PhanQuyen[i].setBounds(100, y, 100, 30);
            Sua_Frame.add(label_PhanQuyen[i]);
            if(i==1)
            {
                ccbPhanQuyen=new JComboBox(arrString_Quyen);
                ccbPhanQuyen.setBounds(200, y, 150, 30);
                ccbPhanQuyen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if(checkQuyen[ccbPhanQuyen.getSelectedIndex()])
                        {
                            cbPhanQuyen.setSelected(true);
                        }
                        else
                        {
                            cbPhanQuyen.setSelected(false);
                        }
                    }
                });
                Them_Frame.add(ccbPhanQuyen);
                
                cbPhanQuyen.setBounds(360, y, 150, 30);
                cbPhanQuyen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        JCheckBox cb = (JCheckBox) event.getSource();
                        if (cb.isSelected()) {
                            checkQuyen[ccbPhanQuyen.getSelectedIndex()]=true;
                        } else {
                            checkQuyen[ccbPhanQuyen.getSelectedIndex()]=false;
                        }
                    }
                });
                Them_Frame.add(cbPhanQuyen);
                continue;
            }
            txt_PhanQuyen_Sua[i] = new JTextField();
            txt_PhanQuyen_Sua[i].setBounds(200, y, 150, 30);
            y += 40;
            Sua_Frame.add(txt_PhanQuyen_Sua[i]);
        }
        load_dataSua();
    }
    @Override
    protected void luuSua_Frame(){
        int row = table.tb.getSelectedRow();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
        int option = JOptionPane.showConfirmDialog(Sua_Frame, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            DTOPhanQuyen DTO = new DTOPhanQuyen();
            DTO.setIDPhanQuyen(BUSPhanQuyen.ds.size()+1);
            DTO.setTenQuyen(txt_PhanQuyen_Them[0].getText());
            DTO.setMoTaQuyen(layMoTaTuCheckBox());
            //Truyền dữ liệu và vị trí vào bus
            BUS.sua(DTO, row);
            BUSPhanQuyen.ds.set(Integer.parseInt(table.tb.getValueAt(row, 0).toString()), DTO);
        }
    }
    private void load_dataSua(){
        int i = table.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
            
        } else {            
            //Set tự động giá trị các field
            for (int j = 0; j < header.length-1; j++) 
                    txt_PhanQuyen_Sua[j].setText(table.tb.getValueAt(i, j).toString());
            String MoTaQuyen=table.tb.getValueAt(i, 1).toString();
            for(int j=0;j<checkQuyen.length;j++)
                if(MoTaQuyen.indexOf(arr_listmotaQuyen[j])!=-1)
                    checkQuyen[j]=true;
                else
                    checkQuyen[j]=false;
            Sua_Frame.setVisible(true);
            
        }
    }
    @Override
    protected void clearThem_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length-1; i++) {
            txt_PhanQuyen_Them[i].setText("");
        }
    }
    @Override
    protected void clearSua_Frame(){
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length-1; i++) {
            txt_PhanQuyen_Sua[i].setText("");
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

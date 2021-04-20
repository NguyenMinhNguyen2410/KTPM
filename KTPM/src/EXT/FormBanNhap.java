/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXT;

import GUI.GUIMenu;
import GUI.GUISanPham;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author nguye
 */
public class FormBanNhap extends JPanel{
    //Tạo các panel
    protected JPanel TimKiem,Trai,Phai,BangSanPham,Show,ThongTin,BangHoaDon,Button;
    protected JTextField Ten,txMaXe,txTenXe,txDonGia,txSoKhung;
    protected JLabel lbImage;
    
    public FormBanNhap(){
        initcomponent();
    }
    public void initcomponent(){
        setLayout(new BorderLayout());
        
        Trai=panelTrai();
        Trai.setPreferredSize(new Dimension(GUIMenu.width_content*49/100,0));
        add(Trai,BorderLayout.WEST);
        Phai=panelPhai();
        Phai.setPreferredSize(new Dimension(GUIMenu.width_content*49/100,0));
        add(Phai,BorderLayout.EAST);
        
        setVisible(true);
        setSize(GUIMenu.width_content, GUIMenu.height);
    }
    
    protected JPanel panelTrai(){
        JPanel panel=new JPanel(new BorderLayout());
        
        TimKiem=panelTimKiem();
        TimKiem.setPreferredSize(new Dimension(0,GUIMenu.height*10/100));
        panel.add(TimKiem,BorderLayout.NORTH);
        
        BangSanPham=panelBangSanPham();
//        BangSanPham.setPreferredSize(new Dimension(0,GUIMenu.height*40/100));
        panel.add(BangSanPham,BorderLayout.CENTER);
        
        Show=panelShow();
        Show.setPreferredSize(new Dimension(0,GUIMenu.height*50/100));
        panel.add(Show,BorderLayout.SOUTH);
        
        return panel;
    }
    protected JPanel panelTimKiem(){
        JPanel panel=new JPanel(new FlowLayout());
        
        //Tìm kiếm theo tên
        Ten = new JTextField();
        Ten.setBorder(new TitledBorder("Nhập tên sản phẩm"));
        Ten.setPreferredSize(new Dimension(200,50));
        panel.add(Ten);
        
        
        return panel;
    }
    protected JPanel panelBangSanPham(){
        JPanel panel=new JPanel(new BorderLayout());
        
        MyTable table=new MyTable();
        table.setHeaders(GUISanPham.header);
        table.setPreferredSize(new Dimension(0,GUIMenu.height*40/100));
        panel.add(table,BorderLayout.NORTH);
        
        return panel;
    }
    protected JPanel panelShow(){
        JPanel panel=new JPanel(null);
        JPanel ChiTiet=new JPanel(null);
        
        ChiTiet.setBounds(300,0, 500,300);
        lbImage=new JLabel();
        lbImage.setBackground(Color.yellow);
        lbImage.setBounds(0, 0, 300,300);
        
        txMaXe=new JTextField();
        txTenXe=new JTextField();
        txDonGia=new JTextField();
        txSoKhung=new JTextField();
        
        // border
        txMaXe.setBorder(BorderFactory.createTitledBorder("Mã xe"));       
        txTenXe.setBorder(BorderFactory.createTitledBorder("Tên xe"));
        txDonGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txSoKhung.setBorder(BorderFactory.createTitledBorder("Số khung"));
        // disable
        txMaXe.setEditable(false);
        txTenXe.setEditable(false);
        txDonGia.setEditable(false);
        txSoKhung.setEditable(false);
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaXe.setFont(f);
        txTenXe.setFont(f);
        txDonGia.setFont(f);
        txSoKhung.setFont(f);
        //setsize
        
        txMaXe.setBounds(50, 0, 200,40);
        txTenXe.setBounds(50, 50,200,40);
        txDonGia.setBounds(50, 100,200,40);
        txSoKhung.setBounds(50, 150,200,40);
        // add to panel
        ChiTiet.add(txMaXe);
        ChiTiet.add(txTenXe);
        ChiTiet.add(txDonGia);
        ChiTiet.add(txSoKhung);

        
        JButton Them=new JButton("Thêm");
        Them.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/them1-30.png")));
        Them.setFont(new Font("Segoe UI", 0, 14));        
        Them.setBackground(Color.decode("#90CAF9"));
        
        Them.setBounds(0, 310, GUIMenu.width_content*50/100, 40);
        //Sự kiện khi bấm nút thêm
        Them.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Them_click(evt);
            }
        });
        panel.add(Them);
        panel.add(lbImage);
        panel.add(ChiTiet);
        return panel;
    }
    
    protected JPanel panelPhai(){
        JPanel panel=new JPanel(new BorderLayout());
        
        ThongTin=panelThongTin();
        ThongTin.setPreferredSize(new Dimension(0,GUIMenu.height*50/100));
        panel.add(ThongTin,BorderLayout.NORTH);
        BangHoaDon=panelBangHoaDon();
//        BangHoaDon.setPreferredSize(new Dimension(0,GUIMenu.height*40/100));
        panel.add(BangHoaDon,BorderLayout.CENTER);
        Button=panelButton();
        Button.setPreferredSize(new Dimension(0,GUIMenu.height*10/100));
        panel.add(Button,BorderLayout.SOUTH);
        
        
        return panel;
    }
    protected JPanel panelThongTin(){
        JPanel panel=new JPanel();
        
        return panel;
    }
    protected JPanel panelBangHoaDon(){
        JPanel panel=new JPanel(new BorderLayout());
        
        MyTable table=new MyTable();
        table.setHeaders(new String[]{"Tên sản phẩm","Số lượng","Đơn giá","Dòng"});
        table.setPreferredSize(new Dimension(0,GUIMenu.height*40/100));
        panel.add(table,BorderLayout.NORTH);
        
        return panel;
    }
    protected JPanel panelButton(){
        JPanel panel=new JPanel(null);
        
        JButton Xoa=new JButton("Xóa");
        Xoa.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/delete1-30.png")));
        Xoa.setFont(new Font("Segoe UI", 0, 14));
        Xoa.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));        
        Xoa.setBackground(Color.decode("#90CAF9"));
        Xoa.setBounds(0, 0, GUIMenu.width_content*25/100, 50);
        Xoa.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Xoa_click(evt);
            }
        });
        panel.add(Xoa);
        
        JButton ThanhToan=new JButton("Thanh toán");
        ThanhToan.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/thanhtoan-30.png")));
        ThanhToan.setFont(new Font("Segoe UI", 0, 14));
        ThanhToan.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));        
        ThanhToan.setBackground(Color.decode("#90CAF9"));
        ThanhToan.setBounds( GUIMenu.width_content*25/100, 0, GUIMenu.width_content*25/100, 50);
        ThanhToan.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                ThanhToan_click(evt);
            }
        });
        panel.add(ThanhToan);
        return panel;
    }
    protected void ThanhToan_click(MouseEvent evt){
    }
    protected void Xoa_click(MouseEvent evt){
    }
    protected void Them_click(MouseEvent evt){
    }
}

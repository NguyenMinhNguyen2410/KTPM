/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXT;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietHoaDonNhapBUS;
import BUS.ChiTietNguyenLieuBUS;
import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import BUS.MonAnBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietHoaDonNhapDTO;
import DTO.ChiTietNguyenLieuDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.MonAnDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhanQuyenDTO;
import GUI.GUIKhachHang;
import GUI.GUIKhuyenMai;
import GUI.GUIMenu;
import GUI.GUIMonAn;
import GUI.GUINhaCungCap;
import GUI.GUINhanVien;
import GUI.GUIPhanQuyen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.lang.System.Logger;
//import java.lang.System.Logger.Level;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static GUI.GUIMonAn.header;


/**
 *
 * @author Nguyen
 */
//Class này kế thừa JDialog , dùng để hiện danh sách các thực thể khi cần mã của chúng nhưng người dùng không nhớ hoặc để hiện chi tiết với các mối quan hệ 1-n
public class FormChon extends JDialog{
    //Panel chứa tiêu đề và nút
    private JPanel Title,Button;
    private MyTable Table;
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;
    //Hàm khởi tạo với việc lấy mã thực thể trong danh sách
    public FormChon(JTextField txt,String a) throws Exception{
        setLayout(new BorderLayout());
        setSize(800, 500);
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        setLocationRelativeTo(null);
        setUndecorated(true);
        
        Title=pnTitle(a);
        Title.setPreferredSize(new Dimension(0,100));
        add(Title,BorderLayout.NORTH);
        
        pnTable(a);
        Table.setPreferredSize(new Dimension(0,350));
        add(Table,BorderLayout.CENTER);
        
        Button=pnButton(txt);
        Button.setPreferredSize(new Dimension(0,50));
        add(Button,BorderLayout.SOUTH);
        //Rào lại với mục đích không gây chồng cửa sổ 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });
        setVisible(true);
    }
    //Hàm khởi tạo với việc hiện danh sách các chi tiết 
    public FormChon(String a,String i) throws Exception{
        setLayout(new BorderLayout());
        setSize(800, 500);
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        setLocationRelativeTo(null);
        setUndecorated(true);
        
        Title=pnTitle(a);
        Title.setPreferredSize(new Dimension(0,100));
        add(Title,BorderLayout.NORTH);

        pnTable(a,i);
        Table.setPreferredSize(new Dimension(0,350));
        add(Table,BorderLayout.CENTER);

        Button=pnButton();
        Button.setPreferredSize(new Dimension(0,50));
        add(Button,BorderLayout.SOUTH);
        //Rào lại với mục đích không gây chồng cửa sổ 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }

        });
        setVisible(true);
    }
    //Hàm tạo tiêu đề
    public JPanel pnTitle(String a){
        JPanel panel=new JPanel(null);
        JLabel lbTitle = new JLabel(a);
        lbTitle.setFont(new Font("Time New Roman", Font.BOLD, 21));
        lbTitle.setForeground(Color.decode("#FF4081"));
        lbTitle.setBounds(350, 0, 200, 40);
        panel.add(lbTitle);
        
        //add thêm thanh tìm kiếm
        return panel;
    }
    //Hàm rẻ nhánh khi tạo bảng danh sách
    public void pnTable(String a) throws Exception{
        Table=new MyTable(); 
        
        if(a.equals("Nhà cung cấp"))
        {
            docDBNhaCungCap();
        }
        if(a.equals("Khách hàng"))
        {
            docDBKhachHang();
        }
         if(a.equals("Khuyến mãi"))
        {
            docDBKhuyenMai();
        }       
       if(a.equals("Món ăn"))
        {
            docDBMonAn();
        }
       if(a.equals("Nhân viên"))
       {
           docDBNhanVien();
       }
       if(a.equals("Phân quyền"))
       {
           docDBPhanQuyen();
       }
    }
    //Hàm rẻ nhánh khi tạo danh sách chi tiết
    public void pnTable(String a,String i) throws Exception{
        Table=new MyTable(); 
        
        if(a.equals("Chi tiết hóa đơn"))
        {
            docDBChiTietHoaDon(i);
            //Dùng để rào lại việc không có dữ liệu cần
            if(Table.tbModel.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "Hóa đơn này không có chi tiết");
                setVisible(false);
            }
            else
                setVisible(true);
        }
        if(a.equals("Chi tiết hóa đơn nhập"))
        {
            docDBChiTietHoaDonNhap(i);
            //Dùng để rào lại việc không có dữ liệu cần
            if(Table.tbModel.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "Hóa đơn nhập này không có chi tiết");
                setVisible(false);
            }
            else
                setVisible(true);
        }
        if(a.equals("Chi tiết công thức"))
        {
            docDBChiTietNguyenLieu(i);
            //Dùng để rào lại việc không có dữ liệu cần
            if(Table.tbModel.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "Công thức này không có chi tiết");
                setVisible(false);
            }
            else
                setVisible(true);
        }        
       
    }
    //Hàm tạo nút khi chỉ xem các danh sách chi tiết
    public JPanel pnButton(){
        JPanel panel=new JPanel(null);

        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(350, 0, 100, 50);
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Tắt cờ hiệu đi 
                cohieu = 1;
                dispose();
            }
        });
        panel.add(Thoat);
        return panel;
    }
    //Hàm tạo nút khi có thể chọn trong danh sách
    public JPanel pnButton(JTextField txt){
        JPanel panel=new JPanel(null);
        JButton Chon = new JButton("Chọn");
        Chon.setBackground(Color.decode("#90CAF9"));
        Chon.setBounds(300, 0, 100, 50);
        Chon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) { 
                int i = Table.tb.getSelectedRow();
                if (i == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng");
                } else {
                    txt.setText(String.valueOf(Table.tbModel.getValueAt(i,0)));
                    //Tắt cờ hiệu đi 
                    cohieu = 1;
                    dispose();
                }
            }
        });
        panel.add(Chon);

        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(450, 0, 100, 50);
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Tắt cờ hiệu đi 
                cohieu = 1;
                dispose();
            }
        });
        panel.add(Thoat);
        return panel;
    }
    //Load dữ liệu nhà cung cấp lên table 
    public void docDBNhaCungCap() throws Exception {
        Table.setHeaders(GUINhaCungCap.header);
        NhaCungCapBUS monAnBus = new NhaCungCapBUS();
        if(NhaCungCapBUS.ds == null) {            
                monAnBus.docDB();            
        }       
        for (NhaCungCapDTO DTO : NhaCungCapBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                Table.addRow(DTO);                    
            }
        }
        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
    //Load dữ liệu khách hàng lên table
    public void docDBKhachHang() throws Exception {
        Table.setHeaders(GUIKhachHang.header);
        KhachHangBUS Bus = new KhachHangBUS();
        if(KhachHangBUS.ds == null) {            
                Bus.docDB();            
        }       
        for (KhachHangDTO DTO : KhachHangBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                Table.addRow(DTO);                    
            }
        }
        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
    //Load dữ liệu khuyến mãi lên table
    public void docDBKhuyenMai() throws Exception {
        Table.setHeaders(GUIKhuyenMai.header);
        KhuyenMaiBUS Bus = new KhuyenMaiBUS();
        if(KhuyenMaiBUS.ds == null) {            
                Bus.docDB();            
        }       
        for (KhuyenMaiDTO DTO : KhuyenMaiBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                Table.addRow(DTO);                    
            }
        }
        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
    //Load dữ liệu món ăn lên table
    public void docDBMonAn() throws Exception {
        Table.setHeaders(GUIMonAn.header);
        MonAnBUS Bus = new MonAnBUS();
        if(MonAnBUS.ds == null) {            
                Bus.docDB();            
        }       
        for (MonAnDTO DTO : MonAnBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                Table.addRow(DTO);                    
            }
        }
        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
    //Load dữ liệu chi tiết hóa đơn lên table
    public void docDBChiTietHoaDon(String i) throws Exception {
        Table.setHeaders(new String[]{"Mã món ăn","Số lượng","Đơn giá","Thành tiền"});
        ChiTietHoaDonBUS Bus = new ChiTietHoaDonBUS();
        if(ChiTietHoaDonBUS.ds == null) {            
                Bus.docDB();            
        }
        float Tong=0;
        for (ChiTietHoaDonDTO DTO : ChiTietHoaDonBUS.ds) {
            if (DTO.getIDHoaDon().equals(i)) {
                Tong+=DTO.getThanhTien();
                Table.addRow(DTO);                    
            }
        }
        Table.addRow(new String[]{"","","",""});       
        Table.addRow(new String[]{"","","Tổng",String.valueOf(Tong)});
        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
    //Load dữ liệu chi tiết hóa đơn nhập lên table
    public void docDBChiTietHoaDonNhap(String i) throws Exception {
        Table.setHeaders(new String[]{"Mã nguyên liệu","Số lượng","Giá nhập","Thành tiền"});
        ChiTietHoaDonNhapBUS Bus = new ChiTietHoaDonNhapBUS();
        if(ChiTietHoaDonNhapBUS.ds == null) {            
                Bus.docDB();
        }
        float Tong=0;
        for (ChiTietHoaDonNhapDTO DTO : ChiTietHoaDonNhapBUS.ds) {
            if (DTO.getIDHoaDonNhap().equals(i)) {
                Tong+=DTO.getThanhTien();
                Table.addRow(DTO);                    
            }
        }
        Table.addRow(new String[]{"","","",""});       
        Table.addRow(new String[]{"","","Tổng",String.valueOf(Tong)});
        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
    //Load dữ liệu chi tiết công thức lên table
    public void docDBChiTietNguyenLieu(String i) throws Exception {
        Table.setHeaders(new String[]{"Mã nguyên liệu","Số lượng"});
        ChiTietNguyenLieuBUS Bus = new ChiTietNguyenLieuBUS();
        if(ChiTietNguyenLieuBUS.ds == null) {
                Bus.docDB();
        }

        for (ChiTietNguyenLieuDTO DTO : ChiTietNguyenLieuBUS.ds) {
            if (DTO.getIDCongThuc().equals(i)) {
                Table.addRow(DTO);
            }
        }

        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
    //Load dữ liệu nhân viên lên table
    public void docDBNhanVien() throws Exception {
        Table.setHeaders(GUINhanVien.header);
        NhanVienBUS Bus = new NhanVienBUS();
        if(NhanVienBUS.ds == null) {            
                Bus.docDSNV();
        }       
        for (NhanVienDTO DTO : NhanVienBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                Table.addRow(DTO);                    
            }
        }
        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
    //Load dữ liệu phân quyền lên table
    public void docDBPhanQuyen() throws Exception {
        Table.setHeaders(GUIPhanQuyen.header);
        PhanQuyenBUS Bus = new PhanQuyenBUS();
        if(PhanQuyenBUS.ds == null) {            
                Bus.docDB();
        }       
        for (PhanQuyenDTO DTO : PhanQuyenBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                Table.addRow(DTO);                    
            }
        }
        Table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));
    }
}



























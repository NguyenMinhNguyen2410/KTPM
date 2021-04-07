/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXT;

import GUI.GUIMenu;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author nguye
 */
public class FormContent extends JPanel {
    //Tạo Panel chứa thanh công cụ
    protected JPanel CongCu=new JPanel(new FlowLayout());
    //Tạo Panel chứa thanh tìm kiếm và chứa bảng
    protected JPanel TimKiem,Table;
    //Tạo bảng với định dạng MyTable
    protected MyTable table;
    //Biến chứa header của table
    protected String header[];
    //Tạo cửa sổ khi thêm hoặc sửa
    protected JDialog Them_Frame,Sua_Frame;
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    private int cohieu = 0;
    public FormContent(){
        initcomponent();
    }
    //Tạo Panel chung cho các Panel sau
    public void initcomponent(){
        setLayout(new BorderLayout());
        //Tạo thanh công cục ở phía trên
        CongCu=CongCu();
        CongCu.setPreferredSize(new Dimension(0,70));
        add(CongCu,BorderLayout.NORTH);
        //Tạo thanh tìm kiếm
        TimKiem=TimKiem();
        add(TimKiem,BorderLayout.CENTER);
        //Tạo bảng dữ liệu
        Table=Table();
        Table.setPreferredSize(new Dimension(0,600));
        add(Table,BorderLayout.SOUTH);
        
        setVisible(true);
        setSize(GUIMenu.width_content, 770);
    }
    //Tạo thanh công cụ ở phía trên
    protected JPanel CongCu(){
        //Nút thêm
        Them();
        //Nút sửa
        Sua();
        //Nút xóa
        Xoa();
        //Nút nhập excel
        NhapExcel();
        //Nút xuất excel
        XuatExcel();
        //Nút in PDF
        InPDF();
        //Nút xem chi tiết
        ChiTiet();
        //Nút làm mới
        LamMoi();
        return CongCu;
        
    }
    //Tạo hàm để lớp con kế thừa thiết kế thanh tìm kiếm
    protected JPanel TimKiem(){
        JPanel TimKiem=new JPanel();                
        return TimKiem;
    }
    //Tạo bảng dữ liệu
    protected JPanel Table(){
        JPanel panel =new JPanel(null);
        //Tạo đối tượng cho table
        table= new MyTable();
        //Tạo tiêu đề bảng
        table.setHeaders(header);
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));        
        table.setBounds(0,0,GUIMenu.width_content , 550);
        panel.add(table);          
        
        return panel;
    }
    //Lớp con kế thừa để đọc dữ liệu ở các biến ArrayList static
    protected void docDB(){
        
    }
    //Tạo sự kiện khi ấn nút thêm
    //Được viết tiếp bởi lớp kế thừa, nó sẽ thêm tiêu đề, label , textfield,...
    protected void Them_click(MouseEvent evt){
        JFrame f=new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Them_Frame = new JDialog(f);
        Them_Frame.setLayout(null);
        Them_Frame.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Them_Frame.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Them_Frame.setUndecorated(true);
        Them_Frame.setVisible(true);
        
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, 420, 100, 50);
        //Sự kiên khi click lưu
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Clear textfield
                clearThem_Frame();
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Them_Frame.dispose();
            }
        });

        Them_Frame.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Them_Frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }
        });
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    protected void clearThem_Frame(){
        
    }
    //Tạo sự kiện khi ấn nút sửa
    //Được viết tiếp bởi lớp kế thừa, nó sẽ thêm tiêu đề, label , textfield,...
    protected void Sua_click(MouseEvent evt){
        JFrame f=new JFrame();
        //Để cờ hiệu với giá trị 0 với ý nghĩa không được bấm ra khỏi Dialog trừ nút Thoát
        cohieu = 0;
        Sua_Frame = new JDialog(f);
        Sua_Frame.setLayout(null);
        Sua_Frame.setSize(500, 500);
        //Set vị trí của Dialog
        //https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Sua_Frame.setLocationRelativeTo(null);
        //Tắt thanh công cụ mặc định
        Sua_Frame.setUndecorated(true);
        Sua_Frame.setVisible(true);
        
        //Tạo nút thoát
        JButton Thoat = new JButton("Thoát");
        Thoat.setBackground(Color.decode("#90CAF9"));
        Thoat.setBounds(250, 420, 100, 50);
        //Sự kiên khi click lưu
        Thoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Clear textfield
                clearSua_Frame();
                //Tắt cờ hiệu đi 
                cohieu = 1;
                //Lệnh này để đóng dialog
                Sua_Frame.dispose();
            }
        });

        Sua_Frame.add(Thoat);
        //Chặn việc thao tác ngoài khi chưa tắt dialog gây lỗi phát sinh
        Sua_Frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (cohieu == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }
        });
    }
    //Tạo hàm này dùng để clear các textfield trong Sua_Frame
    protected void clearSua_Frame(){
        
    }
    //Tạo sự kiện khi ấn nút xóa
    //Hàm này sẽ được viết tiếp khi kế thừa, nó sẽ xóa các dòng được chỉ định
    protected void Xoa_click(MouseEvent evt){
        
    }
    //Tạo sự kiện khi ấn nút nhập excel
    //Hàm này sẽ được viết tiếp khi kế thừa, nó sẽ dùng file excel để nhập dữ liệu
    protected void NhapExcel_click(MouseEvent evt){
        
    }
    //Tạo sự kiện khi ấn nút xuất excel
    //Hàm này sẽ được viết tiếp khi kế thừa, nó sẽ xuất dữ liệu ra file excel
    protected void XuatExcel_click(MouseEvent evt){
        
    }
    //Tạo sự kiện khi ấn nút in PDF
    //Hàm này sẽ được viết ở những lớp hóa đơn,... . Dùng để in ra file PDF
    protected void InPDF_click(MouseEvent evt){
        
    }
    //Tạo sự kiện khi ấn nút xem chi tiết
    //Hàm này sẽ được viết ở những lớp hóa đơn,... . Dùng để xem chi tiết của hóa đơn có những gì
    protected void ChiTiet_click(MouseEvent evt){
        
    }
    //Tạo sự kiện khi ấn nút làm mới
    //Hàm này dùng để làm mới lại table khi vừa thao tác
    protected void LamMoi_click(MouseEvent evt){
        table.clear();
        docDB();
    }
    //Tạo nút thêm
    protected void Them(){
        JButton Them=new JButton("Thêm");
        Them.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/them1-30.png")));
        Them.setFont(new Font("Segoe UI", 0, 14));
        Them.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));        
        Them.setBackground(Color.decode("#90CAF9"));
        Them.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Them_click(evt);
            }
        });
        CongCu.add(Them);
    }
    //Tạo nút sửa
    protected void Sua(){
        JButton Sua=new JButton("Sửa");
        Sua.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/sua3-30.png")));
        Sua.setFont(new Font("Segoe UI", 0, 14));
        Sua.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        Sua.setBackground(Color.decode("#90CAF9"));
        Sua.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Sua_click(evt);
            }
        });
        CongCu.add(Sua);
    }
    //Tạo nút xóa
    protected void Xoa(){
        JButton Xoa=new JButton("Xóa");
        Xoa.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/delete1-30.png")));
        Xoa.setFont(new Font("Segoe UI", 0, 14));
        Xoa.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        Xoa.setBackground(Color.decode("#90CAF9"));
        Xoa.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Xoa_click(evt);
            }
        });
        CongCu.add(Xoa);
    }
    //Tạo nút nhập excel
    protected void NhapExcel(){
        JButton NhapExcel=new JButton("Nhập Excel");
        NhapExcel.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xls-30.png")));
        NhapExcel.setFont(new Font("Segoe UI", 0, 14));
        NhapExcel.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        NhapExcel.setBackground(Color.decode("#90CAF9"));
        NhapExcel.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                NhapExcel_click(evt);
            }
        });
        CongCu.add(NhapExcel);
    }
    //Tạo nút xuất excel
    protected void XuatExcel(){
        JButton XuatExcel=new JButton("Xuất Excel");
        XuatExcel.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xls-30.png")));
        XuatExcel.setFont(new Font("Segoe UI", 0, 14));
        XuatExcel.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        XuatExcel.setBackground(Color.decode("#90CAF9"));
        XuatExcel.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                XuatExcel_click(evt);
            }
        });
        CongCu.add(XuatExcel);
    }
    //Tạo nút in PDF
    protected void InPDF(){
        JButton InPDF=new JButton("In PDF");
        InPDF.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/pdf-30.png")));
        InPDF.setFont(new Font("Segoe UI", 0, 14));
        InPDF.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        InPDF.setBackground(Color.decode("#90CAF9"));
        InPDF.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                InPDF_click(evt);
            }
        });
        CongCu.add(InPDF);
    }
    //Tạo nút xem chi tiết
    protected void ChiTiet(){
        JButton ChiTiet=new JButton("Chi tiết");
        ChiTiet.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        ChiTiet.setFont(new Font("Segoe UI", 0, 14));
        ChiTiet.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        ChiTiet.setBackground(Color.decode("#90CAF9"));
        ChiTiet.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                ChiTiet_click(evt);
            }
        });
        CongCu.add(ChiTiet);
    }
    //Tạo nút làm mới
    protected void LamMoi(){
        JButton LamMoi = new JButton("Làm mới");
        LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        LamMoi.setFont(new Font("Segoe UI", 0, 14));
        LamMoi.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));
        LamMoi.setBackground(Color.decode("#90CAF9"));
        LamMoi.setBounds(965, 10, 110, 30);
        LamMoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                LamMoi_click(evt);
            }
        });
        CongCu.add(LamMoi);
    }
}


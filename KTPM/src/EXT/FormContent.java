/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXT;

import BUS.Tool;
import GUI.GUIMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
import javax.swing.border.Border;

/**
 *
 * @author Nguyen
 */
//Class này tạo bố cục cho các bảng khác
public class FormContent extends JPanel {
    //Tạo Panel chứa thanh công cụ
    protected JPanel CongCu=new JPanel(new FlowLayout());
    //Tạo Panel chứa thanh tìm kiếm và chứa bảng
    protected JPanel TimKiem,Table;
    //Tạo bảng với định dạng MyTable
    protected MyTable table;
    //Tạo cửa sổ khi thêm hoặc sửa
    protected JDialog Them_Frame,Sua_Frame;
    //Tạo cờ hiệu cho việc các Dialog có được tắt đúng cách hay không
    protected  int cohieu = 0;
    protected JButton Them,Sua,Xoa,InPDF,LamMoi,ChiTiet,NhapExcel,XuatExcel,Thoat,Luu;
    //Font button
    private final Font font=new Font("Segoe UI", 0, 14);
    //Màu button
    private final Color background=Color.decode("#90CAF9");
    private final Border border=BorderFactory.createLineBorder(background, 1);
    protected JOptionPane op;
    protected FormChon formchon;
    public FormContent() {
        initcomponent();
    }
    
    //Tạo Panel chung cho các Panel sau
    protected void initcomponent(){
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
        setSize(GUIMenu.width_content, 870);
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
        //Hàm đọc database
        docDB();
        //Set kích thước và vị trí
        table.pane.setPreferredSize(new Dimension(GUIMenu.width_content*90/100, 300));        
        table.setBounds(0,0,GUIMenu.width_content , 300);
        panel.add(table);          
        
        return panel;
    }
    //Lớp con kế thừa để đọc dữ liệu ở các biến ArrayList static
    protected void docDB(){
        
    }
    //Tạo sự kiện khi ấn nút thêm
    //Được viết tiếp bởi lớp kế thừa, nó sẽ thêm tiêu đề, label , textfield,...
    protected void Them_click(){
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
        
        //Tạo nút lưu
        Luu = new JButton("Lưu");
        Luu.setBackground(background);
        Luu.setBounds(100, 420, 100, 50);
        //Sự kiện khi click
        Luu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                luuThem_Frame();
            }
        });
        Them_Frame.add(Luu);
        
        //Tạo nút thoát
        Thoat = new JButton("Thoát");
        Thoat.setBackground(background);
        Thoat.setBounds(250, 420, 100, 50);
        //Sự kiên khi click lưu
        Thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
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
                    op.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }
        });
    }
    protected void luuThem_Frame(){
        
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    protected void clearThem_Frame(){
        
    }
    //Tạo sự kiện khi ấn nút sửa
    //Được viết tiếp bởi lớp kế thừa, nó sẽ thêm tiêu đề, label , textfield,...
    protected void Sua_click(){
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
        
        //Tạo nút lưu
        Luu = new JButton("Lưu");
        Luu.setBackground(background);
        Luu.setBounds(100, 420, 100, 50);
        //Sự kiện khi click
        Luu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                luuSua_Frame();
            }
        });
        Sua_Frame.add(Luu);
        
        //Tạo nút thoát
        Thoat = new JButton("Thoát");
        Thoat.setBackground(background);
        Thoat.setBounds(250, 420, 100, 50);
        //Sự kiên khi click lưu
        Thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
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
                    op.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
                }
            }
        });
    }
    protected void luuSua_Frame(){
        
    }
    //Tạo hàm này dùng để clear các textfield trong Sua_Frame
    protected void clearSua_Frame(){
        
    }
    //Tạo sự kiện khi ấn nút xóa
    //Hàm này sẽ được viết tiếp khi kế thừa, nó sẽ xóa các dòng được chỉ định
    protected void Xoa_click(){
        
    }
    //Tạo sự kiện khi ấn nút nhập excel
    //Hàm này sẽ được viết tiếp khi kế thừa, nó sẽ dùng file excel để nhập dữ liệu
    protected void NhapExcel_click(){
        
    }
    //Tạo sự kiện khi ấn nút xuất excel
    //Hàm này sẽ được viết tiếp khi kế thừa, nó sẽ xuất dữ liệu ra file excel
    protected void XuatExcel_click(){
        
    }
    //Tạo sự kiện khi ấn nút in PDF
    //Hàm này sẽ được viết ở những lớp hóa đơn,... . Dùng để in ra file PDF
    protected void InPDF_click(){
        
    }
    //Tạo sự kiện khi ấn nút xem chi tiết
    //Hàm này sẽ được viết ở những lớp hóa đơn,... . Dùng để xem chi tiết của hóa đơn có những gì
    protected void ChiTiet_click(){
        
    }
    //Tạo sự kiện khi ấn nút làm mới
    //Hàm này dùng để làm mới lại table khi vừa thao tác
    protected void LamMoi_click(){
        table.clear();
        docDB();
    }
    //Tạo nút thêm
    protected void Them(){
        Them=new JButton("Thêm");
        Them.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/them1-30.png")));
        Them.setFont(font);
        Them.setBorder(border);        
        Them.setBackground(background);
        Them.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Them_click();
            }
        });
        CongCu.add(Them);
    }
    //Tạo nút sửa
    protected void Sua(){
        Sua=new JButton("Sửa");
        Sua.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/sua3-30.png")));
        Sua.setFont(font);
        Sua.setBorder(border);
        Sua.setBackground(background);
        Sua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                Sua_click();
            }
        });
        CongCu.add(Sua);
    }
    //Tạo nút xóa
    protected void Xoa(){
        Xoa=new JButton("Xóa");
        Xoa.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/delete1-30.png")));
        Xoa.setFont(font);
        Xoa.setBorder(border);
        Xoa.setBackground(background);
        Xoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                Xoa_click();
            }
        });
        CongCu.add(Xoa);
    }
    //Tạo nút nhập excel
    protected void NhapExcel(){
        NhapExcel=new JButton("Nhập Excel");
        NhapExcel.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xls-30.png")));
        NhapExcel.setFont(font);
        NhapExcel.setBorder(border);
        NhapExcel.setBackground(background);
        NhapExcel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                NhapExcel_click();
            }
        });
        CongCu.add(NhapExcel);
    }
    //Tạo nút xuất excel
    protected void XuatExcel(){
        XuatExcel=new JButton("Xuất Excel");
        XuatExcel.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xls-30.png")));
        XuatExcel.setFont(font);
        XuatExcel.setBorder(border);
        XuatExcel.setBackground(background);
        XuatExcel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                XuatExcel_click();
            }
        });
        CongCu.add(XuatExcel);
    }
    //Tạo nút in PDF
    protected void InPDF(){
        InPDF=new JButton("In PDF");
        InPDF.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/pdf-30.png")));
        InPDF.setFont(font);
        InPDF.setBorder(border);
        InPDF.setBackground(background);
        InPDF.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                InPDF_click();
            }
        });
        CongCu.add(InPDF);
    }
    //Tạo nút xem chi tiết
    protected void ChiTiet(){
        ChiTiet=new JButton("Chi tiết");
        ChiTiet.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        ChiTiet.setFont(font);
        ChiTiet.setBorder(border);
        ChiTiet.setBackground(background);
        ChiTiet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ChiTiet_click();
            }
        });
        CongCu.add(ChiTiet);
    }
    //Tạo nút làm mới
    protected void LamMoi(){
        LamMoi = new JButton("Làm mới");
        LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
        LamMoi.setFont(font);
        LamMoi.setBorder(border);
        LamMoi.setBackground(background);
        LamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LamMoi_click();
            }
        });
        CongCu.add(LamMoi);
    }

    public JButton getThem() {
        return Them;
    }

    public JButton getSua() {
        return Sua;
    }

    public JButton getXoa() {
        return Xoa;
    }

    public JButton getInPDF() {
        return InPDF;
    }

    public JButton getLamMoi() {
        return LamMoi;
    }

    public JButton getChiTiet() {
        return ChiTiet;
    }

    public JButton getNhapExcel() {
        return NhapExcel;
    }

    public JButton getXuatExcel() {
        return XuatExcel;
    }

    public Color getColorButton() {
        return background;
    }

    public MyTable getTable() {
        return table;
    }

    public JDialog getThem_Frame() {
        return Them_Frame;
    }

    public JDialog getSua_Frame() {
        return Sua_Frame;
    }
    
    public JOptionPane getOp() {
        return op;
    }

    public FormChon getFormchon() {
        return formchon;
    }

    public JButton getThoat() {
        return Thoat;
    }

    public JButton getLuu() {
        return Luu;
    }
    
}
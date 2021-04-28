/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import BUS.Tool;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Nguyen
 */
//class mở đầu khi form đăng nhập được gọi , nơi sườn của cả bài
public class GUImenu extends JFrame{
    
    private String arr_listmotaQuyen[]={"QLBanHang","QLNhapHang","QLMonAn","QLNguyenLieu","QLCongThuc","QLHoaDon","QLHDNhap",
        "QLKhuyenMai","QLKhachHang","QLNhanVien","QLNhaCungCap","QLTaiKhoan","QLPhanQuyen","QLThongKe"};
    //Tạo mảng menuleft cho form
    private String arr_listmenu[]={"Bán hàng","Nhập hàng","Món ăn","Nguyên liệu","Công thức","Hóa đơn","Hóa đơn nhập",
        "Khuyến mãi","Khách Hàng","Nhân viên","Nhà cung cấp","Tài khoản","Phân quyền","Thống kê"};
    //Tạo mảng icon cho menuleft
    private String arr_icon[]={"src/Images/Icon/sell1-30.png","src/Images/Icon/nhaphang-30.png","src/Images/Icon/monan-30.png",
        "src/Images/Icon/nguyenlieu-30.png","src/Images/Icon/congthuc-30.png","src/Images/Icon/hoadon-30.png",
        "src/Images/Icon/hoadonnhap-30.png","src/Images/Icon/khuyenmai-30.png","src/Images/Icon/khachhang-30.png",
        "src/Images/Icon/nhanvien-30.png","src/Images/Icon/nhacungcap-30.png","src/Images/Icon/taikhoan-30.png",
        "src/Images/Icon/phanquyen1-30.png","src/Images/Icon/thongke-30.png"};
    //Tạo mảng nhãn cho menuleft
    private JLabel lbl_listmenu[]=new JLabel[arr_listmenu.length];
    //Tạo mảng bảng nhỏ để đặt các icon và nhãn của menuleft
    private JPanel pn_listmenu[]=new JPanel[arr_listmenu.length];
    //Tạo mảng panel chứa nội dung từng mục
    private JPanel pn_content[]=new JPanel[arr_listmenu.length];
    //Tạo Panel cho bảng nhân viên
    private JPanel pn_NhanVien=new JPanel();
    //Tạo Panel cho bảng khách hàng
    private JPanel pn_KhachHang=new JPanel();
    //Tạo Panel cho bảng món ăn
    private JPanel pn_MonAn=new JPanel();
    //Tạo Panel cho bảng hóa đơn
    private JPanel pn_HoaDon=new JPanel();
    //Tạo Panel cho bảng nguyên liệu
    private JPanel pn_NguyenLieu=new JPanel();
    //Tạo Panel cho bảng tài khoản
    private JPanel pn_TaiKhoan=new JPanel();
    //Tạo Panel cho bảng phân quyền
    private JPanel pn_PhanQuyen=new JPanel();
    //Tạo Panel cho bảng thống kê
    private JPanel pn_ThongKe=new JPanel();
    //Tạo Panel cho bảng hóa đơn nhập
    private JPanel pn_HoaDonNhap=new JPanel();
    //Tạo Panel cho bảng công thức
    private JPanel pn_CongThuc=new JPanel();
    
    //https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
    //Lệnh này để lấy kích thước màn hình
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //Tạo biến width với 90% độ dài của độ phân giải màn hình
    public static int width = (int)(screenSize.getWidth()*90/100);
    //Tạo biến height với 90% độ rộng của độ phân giải màn hình 
    public static int height = (int)(screenSize.getHeight()*90/100);
    //Tạo biến width_menu ,là chiều dài của menuleft , chiếm 15%
    public static int width_menu=width*15/100;
    //Tạo biến width_content ,là chiều dài của phần nội dung , chiếm 85%
    public static int width_content=width*85/100;
    //Tạo nhãn tiêu đề
    private JLabel title;
    //Tạo Panel chứa menu
    private JPanel menu;
    public GUImenu() throws Exception{
        initcomponent();
    }
    //Tạo kích thước và hình dáng của form
    public void initcomponent() throws Exception{
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));       
        setLayout(new BorderLayout());
        //làm mất thanh chức năng mặc định của Frame
        //Chú ý : phải set lệnh này trước visible nếu không thì lệnh không chạy và gây lỗi
        setUndecorated(true);
        setVisible(true);
        //Panel chứa menuleft
        menu=menu();
        JScrollPane pane = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        menu.setAutoscrolls(true);
        menu.setPreferredSize(new Dimension(width_menu,900));
        pane.setPreferredSize(new Dimension(width_menu, height));
        pane.setBorder(BorderFactory.createEmptyBorder());
        // setUnitIncrement giúp thanh cuộn mượt mà hơn , số càng nhỏ càng mượt ( chậm )
        pane.getVerticalScrollBar().setUnitIncrement(5);
        add(pane,BorderLayout.WEST);
        
        //Panel này xem như thanh công cụ , thay thế thanh công cụ mặc định của java
        JPanel header=header();
        header.setPreferredSize(new Dimension(0, 30));
        add(header,BorderLayout.NORTH);
        //Panel chứa phần nội dung của mỗi mục trong menuleft
        JPanel content=content();
        content.setPreferredSize(new Dimension(width_content,0));
        add(content,BorderLayout.CENTER);
        
        
        setSize(width,height);
    }
    private JPanel menu(){       
//        JPanel menu=new JPanel(new GridLayout(14, 1));
        JPanel menu=new JPanel(null);
        
        JLabel logo=new JLabel(Tool.showIcon(width_menu, 200, "src/Images/Icon/Logo-Design-removebg-preview.png"));
        logo.setBounds(0, 0, width_menu, 200);
        //Sự kiện khi ấn vào logo thì hiện tên 
        logo.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                title.setText("QUẢN LÝ BÁN THỨC ĂN NHANH");
            }
        });
        menu.add(logo);
        menu.setBackground(Color.decode("#64B5F6"));       
        int y=200;
        //Hàm tạo tự động các nhãn trong thanh menuleft
        for(int i=0;i<lbl_listmenu.length;i++)
        {   
            lbl_listmenu[i]=new JLabel(arr_listmenu[i], Tool.showIcon(30, 30, arr_icon[i]),SwingConstants.LEADING);// thêm icon ráng kiếm
            lbl_listmenu[i].setBounds(20, 0,width_menu,50);
            lbl_listmenu[i].setFont(new Font("Segoe UI", 0, 18));
            //Đổi màu chữ thành trắng
            lbl_listmenu[i].setForeground(Color.white);
            pn_listmenu[i]=new JPanel(null);
            pn_listmenu[i].setBackground(Color.decode("#64B5F6")); // mới sửa
            
            pn_listmenu[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                //Sự kiện khi click vào 1 mục bất kỳ thì sẽ hiện tiêu đề của mục ở thanh header
                public void mousePressed(java.awt.event.MouseEvent evt) 
                {
                    for(int i=0;i<pn_listmenu.length;i++)
                    {
                        if(evt.getSource()==pn_listmenu[i])
                        {
                
                            pn_content[i].setVisible(true);
                            String s="BẢNG ";
                            
                            s+=arr_listmenu[i].toUpperCase();
                            title.setText(s);
                            continue;
                        }
            
                            pn_content[i].setVisible(false);
                    }
                }
                @Override
                //Sự kiện khi kéo chuột đến đâu thì làm nổi bật ở mục đó lên
                public void mouseEntered(java.awt.event.MouseEvent evt) 
                {
                    for(int i=0;i<pn_listmenu.length;i++)
                    {
                         if(evt.getSource()==pn_listmenu[i])
                        {
                            pn_listmenu[i].setBackground(Color.decode("#2962FF"));   //mới sửa                       
                            continue;
                        }
                        pn_listmenu[i].setBackground(Color.decode("#64B5F6"));     //mới sửa                   
                    }
                }
                @Override
                //Sự kiện khi kéo chuột đi khỏi mục đó thì trở lại như cũ
                public void mouseExited(java.awt.event.MouseEvent evt) 
                {
                    for(int i=0;i<pn_listmenu.length;i++)
                    {
                         if(evt.getSource()==pn_listmenu[i])
                        {
                            pn_listmenu[i].setBackground(Color.decode("#64B5F6"));   //mới sửa                       
                            return;
                        }
                                                
                    }
                }
            });

            pn_listmenu[i].add(lbl_listmenu[i]);
            pn_listmenu[i].setBounds(0, y, width_menu, 50);
            //thêm mới
            //lấy mã quyền của user đang hiện hành
//            String maQuyen =TaiKhoanBUS.timKiemMaQuyenTheoTenTaiKhoan(Tool.maNVHienHanh);
            String maQuyen =TaiKhoanBUS.timKiemMaQuyenTheoTenTaiKhoan(LoginGUI.taiKhoan);
            //lấy mô tả quyền của user đang hiện hành
            String moTaQuyen = PhanQuyenBUS.timMoTaQuyenTheoIDPhanQuyen(maQuyen);
            //tìm kiếm trong mô tả quyền có cái quyền QL nào thì add nó vào
            if(timKiemMotQuyenTrongMoTaQuyen(moTaQuyen, arr_listmotaQuyen[i]))
            {
                menu.add(pn_listmenu[i]);
                y+=50;
            }
            //kết thúc thêm mới
            
              
        }
        
        return menu;
    }
    private JPanel content() throws Exception{
        JPanel trunggian=new JPanel(null);
        
        //Tạo thanh menu tự động , gồm các button tạo các bảng
        //Tạo đè tất cả các Panel lên 1 Panel trung gian , sử dụng cơ chế setVisible true fasle để xem từng bảng, sự kiện đã được thêm ở phía trên
        for(int i=0;i<arr_listmenu.length;i++)
        {
            //Với mỗi i chỉ tạo 1 panel
            switch(i){
                case 0: 
                    GUIBanHang BanHang=new GUIBanHang();
                    pn_content[i]=BanHang;    break;                  
                case 1: 
                    GUINhapHang NhapHang=new GUINhapHang();
                    pn_content[i]=NhapHang; break;
                case 2: 
                    GUIMonAn MonAn=new GUIMonAn();
                    pn_content[i]=MonAn;    break;                  
                case 3:
                    GUINguyenLieu NguyenLieu=new GUINguyenLieu();
                    pn_content[i]=NguyenLieu; break;
                case 4:
                    GUICongThuc CongThuc=new GUICongThuc();
                    pn_content[i]=CongThuc; break;
                case 5:
                    GUIHoaDon HoaDon=new GUIHoaDon();
                    pn_content[i]=HoaDon; break;
                case 6:
                    GUIHoaDonNhap HoaDonNhap=new GUIHoaDonNhap();
                    pn_content[i]=HoaDonNhap; break;
                case 7:
                    GUIKhuyenMai KhuyenMai=new GUIKhuyenMai();
                    pn_content[i]=KhuyenMai; break;
                case 8:
                    GUIKhachHang KhachHang=new GUIKhachHang();
                    pn_content[i]=KhachHang; break;                        
                case 9:
                    GUINhanVien NhanVien=new GUINhanVien();
                    pn_content[i]=NhanVien; break;                
                case 10:
                    GUINhaCungCap NhaCungCap=new GUINhaCungCap();
                    pn_content[i]=NhaCungCap; break;              
                case 11:
                    GUITaiKhoan TaiKhoan=new GUITaiKhoan();
                    pn_content[i]=TaiKhoan; break;
                case 12:
                    GUIPhanQuyen PhanQuyen=new GUIPhanQuyen();
                    pn_content[i]=PhanQuyen; break;
                case 13:
                    GUIThongKe ThongKe=new GUIThongKe();
                    pn_content[i]=ThongKe; break;
                
            }
            
            pn_content[i].setVisible(false);
            pn_content[i].setBounds(0, 0, width_content, 770);
            trunggian.add(pn_content[i]);
        }
        return trunggian;
        
    }
    private JPanel header(){
        JPanel header=new JPanel(null);
        header.setBackground(Color.black);
        
        title=new JLabel("QUẢN LÝ BÁN THỨC ĂN NHANH");
        title.setBounds(width*50/100, 0, 300, 30);
        title.setFont(new Font("Segoe UI", 0, 18));
        title.setForeground(Color.white);
        header.add(title);
        //Tạo nút logout
        JLabel logout=new JLabel();
        logout.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/icons8_exit_30px.png")));
        logout.setBounds(width*0, 0, 30, 30);
        logout.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                logout();
            }
        });
        header.add(logout);
        //Tạo nút lặn frame xuống thanh taskbar
        JLabel minimize=new JLabel();
        minimize.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/icons8_angle_down_30px.png")));
        minimize.setBounds(width*94/100, 0, 30, 30);
        minimize.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                setState(JFrame.ICONIFIED);
            }
        });
        header.add(minimize);
        //Tạo nút thoát
        JLabel exit=new JLabel();
        exit.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/icons8_shutdown_30px_1.png")));
        exit.setBounds(width*97/100, 0, 30, 30);
        exit.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                System.exit(0);
            }
        });
        header.add(exit);
        
        return header;
    } 
    //Hàm dùng để lọc lại thanh menuleft nếu quyền chưa đủ
    public boolean timKiemMotQuyenTrongMoTaQuyen(String moTaQuyen, String motQuyen)
    {
        if(moTaQuyen.indexOf(motQuyen) != -1)
        {
            return true;
        }
        return false;
    }
    //Hàm sự kiện đăng xuất
    private void logout() {
     
        if (LoginGUI.taiKhoan!=null){
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Bạn có chắc muốn đăng xuất khỏi "+ LoginGUI.taiKhoan +"?", "Chú ý",
                JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            new LoginGUI().setVisible(true);
            this.dispose();
        }
        }
    }
}



















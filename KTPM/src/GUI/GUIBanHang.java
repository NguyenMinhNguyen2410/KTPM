/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.KhuyenMaiBUS;
import BUS.MonAnBUS;
import BUS.TaiKhoanBUS;
import BUS.Tool;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhuyenMaiDTO;
import DTO.MonAnDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Nguyen
 */
//Class này được kế thừa từ GUIFormBanNhap , các bố cục đã được sắp xếp sẵn ở bên đó
public class GUIBanHang extends GUIFormBanNhap{
    //Tạo mảng tiêu đề của bảng món ăn
    private static String array_MonAn[]={"Mã món ăn","Tên món","Đơn vị tính","Giá","Hình ảnh","Loại","Số lượng"};   
    //Tạo bảng món ăn để nhân viên chọn danh sách món và add lên bảng thanh toán
    private GUIMyTable table_MonAn,ThanhToan;
    //Tạo Panel để show thông tin món ăn và để chứa thanh tìm kiếm
    private JPanel Show,TimKiem;
    //Tạo nhãn dùng để chứa hình của thông tin món ăn
    private JLabel lbImage;
    //Tạo các field chứa thông tin món ăn khi chọn
    private JTextField txMaMA,txTenMA,txDonGia,txSoLuong;
    //Tạo các field chứa thông tin hóa đơn khi thanh toán
    private JTextField MaHD,TongTien,KhachHang,NgayLap,NhanVien,KhuyenMai;
    //Tạo các nút để phục vụ cho việc thuận tiện khi chọn mã khách hàng hay khuyến mãi
    private JButton ChonNhanVien,ChonKhachHang,ChonKhuyenMai;
    //Tạo field tìm kiếm món ăn 
    private JTextField search;
    //Tạo ComboBox để chọn tiêu chí tìm kiếm
    private JComboBox cbSearch;
    public GUIBanHang(){
        super();
        
    }
    @Override
    protected JPanel panelDanhSach(){       
        JPanel panel=new JPanel(null);
        //Thanh tìm kiếm món ăn
        TimKiem=TimKiem();
        TimKiem.setBounds(0,0,GUImenu.width_content*50/100, 80);
        panel.add(TimKiem);
        //Bảng món ăn
        JPanel MonAn=Table();
        MonAn.setBounds(0,85,GUImenu.width_content*50/100, 300);
        panel.add(MonAn);
        //Show thông tin món ăn khi click vào
        Show=Show();
        Show.setBounds(0,390,GUImenu.width_content*50/100, 370);
        panel.add(Show);
        
        return panel;    
    }
    //Tạo bảng món ăn
    private JPanel Table(){        
        table_MonAn=new GUIMyTable();        
        table_MonAn.setHeaders(array_MonAn);           
        docDB();                  
        table_MonAn.pane.setPreferredSize(new Dimension(GUImenu.width_content*50/100, 300));
        return table_MonAn;
    }
    //Đọc dữ liệu bảng món ăn
    public void docDB() {
        MonAnBUS monAnBus = new MonAnBUS();
        if(MonAnBUS.dsMonAn == null) {
            try {
                monAnBus.docDSMonAn();
            } catch (Exception ex) {
                Logger.getLogger(GUIMonAn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        for (MonAnDTO monAnDTO : MonAnBUS.dsMonAn) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table_MonAn.addRow(monAnDTO);
                    
            }
        }
    }
    //Thanh tìm kiếm món ăn
    private JPanel TimKiem(){
        JPanel TimKiem=new JPanel(null);
        
        JLabel lbsearch=new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        
        search=new JTextField();
        search.setBorder(new TitledBorder("Tên"));
        search.setBounds(5, 20, 200, 40);
        lbsearch.add(search);
        addDocumentListener(search);
        
        lbsearch.setBounds(200, 0, 215, 70);
        TimKiem.add(lbsearch);
        return TimKiem;
    }
    // để cho hàm tìm kiếm
    private void addDocumentListener(JTextField tx) { 
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        tx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            
        });
    }
    //xóa ruột của table và đổ lên những kết quả tìm kiếm được
    public void txtSearchOnChange() {
        table_MonAn.clear();
        ArrayList<MonAnDTO> arraylist=Tool.searchBH(search.getText());
        for (MonAnDTO DTO : arraylist) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_MonAn.addRow(DTO);
                    
            }
        }
    }
    //Show thông tin món ăn
    private JPanel Show(){
        JPanel panel=new JPanel(null);
        JPanel ChiTiet=new JPanel(null);
        
        
        ChiTiet.setBounds(300,0, 500,300);
        lbImage=new JLabel();
        lbImage.setBackground(Color.yellow);
        lbImage.setBounds(0, 0, 300,300);
        
        txMaMA=new JTextField();
        txTenMA=new JTextField();
        txDonGia=new JTextField();
        txSoLuong=new JTextField();
        
        // border
        txMaMA.setBorder(BorderFactory.createTitledBorder("Mã món ăn"));       
        txTenMA.setBorder(BorderFactory.createTitledBorder("Tên món ăn"));
        txDonGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        // disable
        txMaMA.setEditable(false);
        txTenMA.setEditable(false);
        txDonGia.setEditable(false);
        txSoLuong.setEditable(true);
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaMA.setFont(f);
        txTenMA.setFont(f);
        txDonGia.setFont(f);
        txSoLuong.setFont(f);
        //setsize
        
        txMaMA.setBounds(50, 0, 200,40);
        txTenMA.setBounds(50, 50,200,40);
        txDonGia.setBounds(50, 100,200,40);
        txSoLuong.setBounds(50, 150,200,40);
        // add to panel
        ChiTiet.add(txMaMA);
        ChiTiet.add(txTenMA);
        ChiTiet.add(txDonGia);
        ChiTiet.add(txSoLuong);

        // Sự kiện khi click vào các row
        table_MonAn.getTable().addMouseListener(new MouseAdapter() { 
            @Override
            public void mouseReleased(MouseEvent me) {
                String id = String.valueOf(table_MonAn.tbModel.getValueAt(table_MonAn.tb.getSelectedRow(), 0));
                if (id != null) {
                    showInfo(id);
                }
            }
        });
        
        JButton Them=new JButton("Thêm");
        Them.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/them1-30.png")));
        Them.setFont(new Font("Segoe UI", 0, 14));        
        Them.setBackground(Color.decode("#90CAF9"));
        
        Them.setBounds(0, 310, GUImenu.width_content*50/100, 40);
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
    //Hàm hiển thị ảnh và show thông tin
    private void showInfo(String id) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (id != null) {
            // show hình
            for (MonAnDTO ds : MonAnBUS.dsMonAn) {
                if (ds.getIDMonAn().equals(id)) {
                    int w = lbImage.getWidth();
                    int h = lbImage.getHeight();
                    ImageIcon img = new ImageIcon(getClass().getResource("/Images/MonAn/" + ds.getHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
                    lbImage.setIcon(new ImageIcon(imgScaled));

                    // show info                   
                    txMaMA.setText(ds.getIDMonAn());
                    txTenMA.setText(ds.getTenMonAn());                
                    txDonGia.setText(String.valueOf(ds.getDonGia()));
                    txSoLuong.setText("1");
                    return;
                }
            }
        }
    }
    @Override
    //Ghi đè để lấy vị trí và tạo panel thông tin hóa đơn
    protected JPanel panelThongTin(){
        JPanel panel=new JPanel(null);
        
        MaHD=new JTextField();
        TongTien=new JTextField();
        KhachHang=new JTextField();
        NgayLap=new JTextField();
        NhanVien=new JTextField();
        ChonNhanVien=new JButton();
        ChonKhachHang=new JButton();
        KhuyenMai=new JTextField();
        ChonKhuyenMai=new JButton();
        // border
        MaHD.setBorder(BorderFactory.createTitledBorder("Mã hóa đơn"));       
        TongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền"));
        KhachHang.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
        NgayLap.setBorder(BorderFactory.createTitledBorder("Ngày lập"));
        NhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên"));
        KhuyenMai.setBorder(BorderFactory.createTitledBorder("Khuyến mãi"));
        ChonNhanVien.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        ChonNhanVien.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        ChonKhachHang.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        ChonKhachHang.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));        
        ChonKhuyenMai.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        ChonKhuyenMai.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));        
        // disable
        MaHD.setEditable(false);
        TongTien.setEditable(false);
        KhachHang.setEditable(false);
        NgayLap.setEditable(false);
        NhanVien.setEditable(false);
        KhuyenMai.setEditable(false);
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        MaHD.setFont(f);
        TongTien.setFont(f);
        KhachHang.setFont(f);
        NgayLap.setFont(f);
        NhanVien.setFont(f);
        KhuyenMai.setFont(f);
        //setsize
        int y=20;
        MaHD.setBounds(10, y, 200,40);
        TongTien.setBounds(300,y,200,40);y+=50;
        KhachHang.setBounds(10,y,200,40);
        ChonKhachHang.setBounds(210, y+10, 30, 30);
        NhanVien.setBounds(300, y,200,40);
        ChonNhanVien.setBounds(500, y+10, 30, 30);y+=50;
        NgayLap.setBounds(10, y,200,40);
        KhuyenMai.setBounds(300, y,200,40);
        ChonKhuyenMai.setBounds(500, y+10, 30, 30);
        ChonNhanVien.setEnabled(false);
        //set giá trị cho bảng thông tin
        //Chạy các hàm tự động
        String ngayLap = Tool.getNgayLap().toString(); // set ngày
        NgayLap.setText(ngayLap);
        NhanVien.setText(Tool.IDNhanVienHienHanh);
        
        
        if(HoaDonBUS.getMaHoaDonCuoi() != null)
        {
            String maHD = Tool.tangMa(HoaDonBUS.getMaHoaDonCuoi());
            MaHD.setText(maHD);
        }

        
        // add to panel
        panel.add(MaHD);
        panel.add(TongTien);
        panel.add(KhachHang);
        panel.add(NgayLap);
        panel.add(NhanVien);
        panel.add(KhuyenMai);
        panel.add(ChonNhanVien);
        panel.add(ChonKhachHang);
        panel.add(ChonKhuyenMai);
        //Tạo sự kiện khi ấn vào nút thì hiện cửa sổ chọn khách hàng nếu người dùng không nhớ mã khách hàng
        ChonKhachHang.addActionListener((ae) -> {
            
            GUIFormChon a = null;
            try {
                a = new GUIFormChon(KhachHang,"Khách hàng");
            } catch (Exception ex) {
                Logger.getLogger(GUIBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setVisible(true);
        });
        //Tạo sự kiện khi ấn vào nút thì hiện cửa sổ chọn khuyến mãi nếu người dùng không nhớ mã khuyến mãi
        ChonKhuyenMai.addActionListener((ae) -> {
            
            GUIFormChon a = null;
            try {
                a = new GUIFormChon(KhuyenMai,"Khuyến mãi");
            } catch (Exception ex) {
                Logger.getLogger(GUIBanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setVisible(true);
            a.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosed(WindowEvent e){
                    TinhTien();
                }
 
            });
           
        });
        return panel;
    }
    @Override
    //Hàm này tạo bảng chứa các món ăn order
    protected JPanel panelThanhToan(){
        JPanel panel=new JPanel();
        
        ThanhToan=new GUIMyTable();
        ThanhToan.setHeaders(new String[]{"Mã món","Tên món","Giá","Loại","Số lượng"});//chỗ này bỏ hình ảnh và đơn vị tính vì không cần
        
        ThanhToan.pane.setPreferredSize(new Dimension(GUImenu.width_content*49/100, 300));        
        
        panel.add(ThanhToan);      
        return panel;
    }
    //Hàm này xử lý việc ấn thêm món khi khách order
    private void Them_click(MouseEvent e){
        int i = table_MonAn.tb.getSelectedRow();
        int a=Integer.parseInt(txSoLuong.getText());
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để thêm");
        } 
        else 
        {
            int SlTrongTable=Integer.parseInt(String.valueOf(table_MonAn.tbModel.getValueAt(i, 6)));
            //Rào việc đặt số lượng lớn hơn số hiện có
            if(a>SlTrongTable)
            {
                JOptionPane.showMessageDialog(null, "Số lượng không đủ");
            } 
            else 
            {
                
                for(int j=0;j<ThanhToan.tbModel.getRowCount();j++)
                {
                    if(ThanhToan.tbModel.getValueAt(j, 0)==table_MonAn.tbModel.getValueAt(i, 0))
                    {
                        int SlTrongThanhToan=a+Integer.valueOf(String.valueOf(ThanhToan.tbModel.getValueAt(j, 4)));
                        if(SlTrongThanhToan<=SlTrongTable)
                        {
                            ThanhToan.tbModel.setValueAt(SlTrongThanhToan, j, 4);
                            TinhTien();
                            return;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Số lượng không đủ");
                            return;
                        }
                    }
                }
                    ThanhToan.addRow(new String[]{
                        String.valueOf(table_MonAn.tbModel.getValueAt(i, 0)),
                        String.valueOf(table_MonAn.tbModel.getValueAt(i, 1)),
                        String.valueOf(table_MonAn.tbModel.getValueAt(i, 3)),
                        String.valueOf(table_MonAn.tbModel.getValueAt(i, 5)),
                        String.valueOf(a)
                    });
                    TinhTien();
                
            }
        }
    }
    @Override
    //Hàm tạo nút xóa món đã đặt và nút thanh toán hóa đơn
    protected JPanel panelCongCu(){
        JPanel panel=new JPanel(null);
        //Nút xóa
        JButton Xoa=new JButton("Xóa");
        Xoa.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/delete1-30.png")));
        Xoa.setFont(new Font("Segoe UI", 0, 14));        
        Xoa.setBackground(Color.decode("#90CAF9"));
        
        Xoa.setBounds(0, 0, GUImenu.width_content*25/100, 40);
        Xoa.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                Xoa_click(evt);
            }
        });
        panel.add(Xoa);
        //Nút thanh toán
        JButton btnThanhToan=new JButton("Thanh toán");
        btnThanhToan.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/thanhtoan-30.png")));
        btnThanhToan.setFont(new Font("Segoe UI", 0, 14));        
        btnThanhToan.setBackground(Color.decode("#90CAF9"));
        btnThanhToan.setBounds(GUImenu.width_content*25/100, 0, GUImenu.width_content*25/100, 40);
        btnThanhToan.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt){
                ThanhToan_click(evt);
            }
        });
        panel.add(btnThanhToan);
        
        return panel;
    }
    //Hàm xử lý khi ấn vào nút xóa nằm ở thanh công cụ
    private void Xoa_click(MouseEvent e){
        int i=ThanhToan.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để xóa");
        } 
        else 
        {
            int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                
            ThanhToan.tbModel.removeRow(i);
            TinhTien();
            }
        }
    }
    //Hàm xử lý khi ấn vào nút thanh toán nằm ở thanh công cụ
    private void ThanhToan_click(MouseEvent e){
        //Ràng buộc dữ liệu
        if(checkText(MaHD.getText(),
                TongTien.getText(),
                KhachHang.getText(),
                NgayLap.getText(),
                NhanVien.getText(),
                KhuyenMai.getText(),
                ThanhToan.tb.getRowCount())){
            //Tạo đối tượng cho HoaDonBUS để chuẩn bị cho việc ghi vào arraylist và database
            HoaDonBUS hdbus=new HoaDonBUS();
            //Tạo đối tượng cho ChiTietHoaDonBUS để chuẩn bị cho việc ghi vào arraylist và database
            ChiTietHoaDonBUS cthdbus=new ChiTietHoaDonBUS();
            //Tạo biến và xác định số tiền giảm trong mỗi hóa đơn
            float TienKhuyenMai = 0;
            for(KhuyenMaiDTO DTO: KhuyenMaiBUS.dskm)
                if(DTO.getIDKhuyenMai().equals(KhuyenMai.getText())){
                    TienKhuyenMai=DTO.getTienGiam();
                }
            //Tạo DTO và truyền dữ liệu trực tiếp thông qua constructor 
            HoaDonDTO hdDTO=new HoaDonDTO(MaHD.getText(),
                                            NhanVien.getText(),
                                            KhachHang.getText(),
                                            KhuyenMai.getText(),
                                            LocalDate.parse(NgayLap.getText()),
                                            TienKhuyenMai,
                                            Float.parseFloat(TongTien.getText()),
                                            "Hiện");
            //Thêm vào hóa đơn
            hdbus.them(hdDTO);
            //Tạo hàm duyệt vì cần thêm nhiều chi tiết hóa đơn
            for(int i=0;i<ThanhToan.tb.getRowCount();i++){
                String mamonan=String.valueOf(ThanhToan.tbModel.getValueAt(i, 0));
                int soluong=Integer.parseInt(String.valueOf(ThanhToan.tbModel.getValueAt(i, 4)));
                float dongia=Float.valueOf(String.valueOf(ThanhToan.tbModel.getValueAt(i, 2)));
                float thanhtien=dongia*soluong;
                //Tạo DTO và truyền dữ liệu trực tiếp thông qua constructor 
                ChiTietHoaDonDTO ctDTO=new ChiTietHoaDonDTO(MaHD.getText(),mamonan,soluong,dongia,thanhtien );
                //Thêm vào chi tiết hóa đơn 
                cthdbus.them(ctDTO);
                //Cập nhật lại số lượng
                cthdbus.trusoluong(ctDTO);
            }
            //Clear
            MaHD.setText(Tool.tangMa(HoaDonBUS.getMaHoaDonCuoi()));
            KhachHang.setText("");
            KhuyenMai.setText("");
            NgayLap.setText(Tool.getNgayLap().toString()); // set ngày
            TongTien.setText("");
            ThanhToan.clear();
            LamMoi();
        }
    }
    //Ràng buộc dữ liệu
    //Thứ tự truyền vào lần lượt trùng với các thứ tự ô text
    public boolean checkText(String checkMaHD,String checkTien,String checkMaKH,String checkNgay,String checkMaNV,String checkMaKM,int somonan){
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (checkMaHD.equals("") 
                || checkTien.equals("") 
                || checkMaKH.equals("") 
                || checkNgay.equals("") 
                || checkMaNV.equals("") 
                || checkMaKM.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if(somonan==0){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn món ăn");
        } else {
            return true;

        }
        return false;
    }
    //Hàm tính tiền khi thanh toán 
    public void TinhTien(){
        if(ThanhToan.tb.getRowCount()!=0){
            float thanhtien=0;
            for(int i=0;i<ThanhToan.tb.getRowCount();i++){
                int soluong=Integer.parseInt(String.valueOf(ThanhToan.tbModel.getValueAt(i, 4)));
                float dongia=Float.valueOf(String.valueOf(ThanhToan.tbModel.getValueAt(i, 2)));
                thanhtien+=dongia*soluong;
                
            }
            for(KhuyenMaiDTO DTO:KhuyenMaiBUS.dskm)
            {
                if(KhuyenMai.getText().equals(DTO.getIDKhuyenMai()))
                    thanhtien-=DTO.getTienGiam();
            }
            //Trường hợp có mã khuyến mãi và mua đồ có giá thấp hơn
            if(thanhtien<0)
                thanhtien=0;
            TongTien.setText(String.valueOf(thanhtien));
        }
        else
        {
            TongTien.setText("0");
        }
    }
    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_MonAn.clear();
        for (MonAnDTO DTO : MonAnBUS.dsMonAn) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_MonAn.addRow(DTO);
            }
        }
    }
}








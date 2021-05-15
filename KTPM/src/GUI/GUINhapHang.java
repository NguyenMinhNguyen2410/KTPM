/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NguyenLieuBUS;
import BUS.HoaDonNhapBUS;
import BUS.ChiTietHoaDonNhapBUS;
import BUS.TaiKhoanBUS;
import BUS.Tool;
import DTO.NguyenLieuDTO;
import DTO.ChiTietHoaDonNhapDTO;
import DTO.HoaDonNhapDTO;
import EXT.FormBanNhap;
import EXT.FormChon;
import EXT.MyTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class GUINhapHang extends FormBanNhap{
    //Tạo mảng tiêu đề của bảng nguyên liệu
    private static String array_NguyenLieu[]={"Mã nguyên liệu","Tên","Đơn giá","Hình ảnh","Loại","Đơn vị tính","Số lượng"};   
    //Tạo bảng nguyên liệu để nhân viên chọn danh sách và add lên bảng thanh toán
    private MyTable table_NguyenLieu,ThanhToan;
    //Tạo Panel để show thông tin nguyên liệu và để chứa thanh tìm kiếm
    private JPanel Show,TimKiem;
    //Tạo nhãn dùng để chứa hình của thông tin nguyên liệu
    private JLabel lbImage;
    //Tạo các field chứa thông tin nguyên liệu khi chọn
    private JTextField txMaNL,txTenNL,txDonGia,txSoLuong;
    //Tạo các field chứa thông tin hóa đơn khi thanh toán
    private JTextField MaHDN,TongTien,NhaCungCap,NgayNhap,NhanVien;
    //Tạo các nút để phục vụ cho việc thuận tiện khi chọn mã khách hàng hay khuyến mãi
    private JButton ChonNhanVien,ChonNhaCungCap,Them,Xoa,btnThanhToan;
    //Tạo field tìm kiếm nguyên liệu 
    private JTextField search;
    public GUINhapHang(){
        super();       
    }
    @Override
    protected JPanel panelDanhSach(){       
        JPanel panel=new JPanel(null);
        //Thanh tìm kiếm nguyên liệu
        TimKiem=TimKiem();
        TimKiem.setBounds(0,0,GUIMenu.width_content*50/100, 80);
        panel.add(TimKiem);
        //Bảng nguyên liệu
        JPanel NguyenLieu=Table();
        NguyenLieu.setBounds(0,85,GUIMenu.width_content*50/100, 300);
        panel.add(NguyenLieu);
        //Show thông tin nguyên liệu khi click vào
        Show=Show();
        Show.setBounds(0,390,GUIMenu.width_content*50/100, 370);
        panel.add(Show);
        
        return panel;    
    }
    //Tạo bảng nguyên liệu
    private JPanel Table(){        
        table_NguyenLieu=new MyTable();        
        table_NguyenLieu.setHeaders(array_NguyenLieu);              
        docDB();
        table_NguyenLieu.pane.setPreferredSize(new Dimension(GUIMenu.width_content*50/100, 300));
        return table_NguyenLieu;
    }
    //Đọc dữ liệu bảng nguyên liệu
    public void docDB() {
        NguyenLieuBUS monAnBus = new NguyenLieuBUS();
        if(NguyenLieuBUS.ds == null) {
            try {
                monAnBus.docDB();
            } catch (Exception ex) {
                Logger.getLogger(GUINguyenLieu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        for (NguyenLieuDTO monAnDTO : NguyenLieuBUS.ds) {
            if (monAnDTO.getTrangThai().equals("Hiện")) {
                table_NguyenLieu.addRow(monAnDTO);
                    
            }
        }
    }
    //Thanh tìm kiếm nguyên liệu
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
    private void addDocumentListener(JTextField tx) { // để cho hàm tìm kiếm
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
        table_NguyenLieu.clear();
        ArrayList<NguyenLieuDTO> arraylist=Tool.searchNH(search.getText());
        for (NguyenLieuDTO DTO : arraylist) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_NguyenLieu.addRow(DTO);
                    
            }
        }
    }
    //Show thông tin nguyên liệu
    private JPanel Show(){
        JPanel panel=new JPanel(null);
        JPanel ChiTiet=new JPanel(null);
        
        
        ChiTiet.setBounds(300,0, 500,300);
        lbImage=new JLabel();
        lbImage.setBackground(Color.yellow);
        lbImage.setBounds(0, 0, 300,300);
        
        txMaNL=new JTextField();
        txTenNL=new JTextField();
        txDonGia=new JTextField();
        txSoLuong=new JTextField();
        
        // border
        txMaNL.setBorder(BorderFactory.createTitledBorder("Mã nguyên liệu"));       
        txTenNL.setBorder(BorderFactory.createTitledBorder("Tên nguyên liệu"));
        txDonGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        // disable
        txMaNL.setEditable(false);
        txTenNL.setEditable(false);
        txDonGia.setEditable(false);
        txSoLuong.setEditable(true);
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaNL.setFont(f);
        txTenNL.setFont(f);
        txDonGia.setFont(f);
        txSoLuong.setFont(f);
        //setsize
        
        txMaNL.setBounds(50, 0, 200,40);
        txTenNL.setBounds(50, 50,200,40);
        txDonGia.setBounds(50, 100,200,40);
        txSoLuong.setBounds(50, 150,200,40);
        // add to panel
        ChiTiet.add(txMaNL);
        ChiTiet.add(txTenNL);
        ChiTiet.add(txDonGia);
        ChiTiet.add(txSoLuong);

        // Sự kiện khi click vào các row
        table_NguyenLieu.getTable().addMouseListener(new MouseAdapter() { 
            @Override
            public void mouseReleased(MouseEvent me) {
                String id = String.valueOf(table_NguyenLieu.tbModel.getValueAt(table_NguyenLieu.tb.getSelectedRow(), 0));
                if (id != null) {
                    showInfo(id);
                }
            }
        });
        
        Them=new JButton("Thêm");
        Them.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/them1-30.png")));
        Them.setFont(new Font("Segoe UI", 0, 14));        
        Them.setBackground(Color.decode("#90CAF9"));
        
        Them.setBounds(0, 310, GUIMenu.width_content*50/100, 40);
        //Sự kiện khi bấm nút thêm
        Them.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                Them_click(evt);
            }
        });
        panel.add(Them);
        panel.add(lbImage);
        panel.add(ChiTiet);
        return panel;
    }
    //Hàm hiển thị ảnh và show thông tin
    void showInfo(String id) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (id != null) {
            // show hình
            for (NguyenLieuDTO ds : NguyenLieuBUS.ds) {
                if (ds.getIDNguyenLieu().equals(id)) {
                    int w = lbImage.getWidth();
                    int h = lbImage.getHeight();
                    ImageIcon img = new ImageIcon(getClass().getResource("/Images/MonAn/" + ds.getHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
                    lbImage.setIcon(new ImageIcon(imgScaled));

                    // show info                   
                    txMaNL.setText(ds.getIDNguyenLieu());
                    txTenNL.setText(ds.getTenNguyenLieu());                
                    txDonGia.setText(String.valueOf(ds.getDonGia()));
                    txSoLuong.setText("1");
                    return;
                }
            }
        }
    }
    @Override
    //Ghi đè để lấy vị trí và tạo panel thông tin hóa đơn nhập
    protected JPanel panelThongTin(){
        JPanel panel=new JPanel(null);
        
        MaHDN=new JTextField();
        TongTien=new JTextField();
        NhaCungCap=new JTextField();
        NgayNhap=new JTextField();
        NhanVien=new JTextField();
        ChonNhanVien=new JButton();
        ChonNhaCungCap=new JButton();
        // border
        MaHDN.setBorder(BorderFactory.createTitledBorder("Mã hóa đơn nhập"));       
        TongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền"));
        NhaCungCap.setBorder(BorderFactory.createTitledBorder("Nhà cung cấp"));
        NgayNhap.setBorder(BorderFactory.createTitledBorder("Ngày nhập"));
        NhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên"));
        ChonNhanVien.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        ChonNhanVien.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
        ChonNhaCungCap.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        ChonNhaCungCap.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));        
        // disable
        MaHDN.setEditable(false);
        TongTien.setEditable(false);
        NhaCungCap.setEditable(false);
        NgayNhap.setEditable(false);
        NhanVien.setEditable(false);
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        MaHDN.setFont(f);
        TongTien.setFont(f);
        NhaCungCap.setFont(f);
        NgayNhap.setFont(f);
        NhanVien.setFont(f);
        //setsize
        int y=20;
        MaHDN.setBounds(10, y, 200,40);
        TongTien.setBounds(300,y,200,40);y+=50;
        NhaCungCap.setBounds(10,y,200,40);
        ChonNhaCungCap.setBounds(210, y+10, 30, 30);
        NhanVien.setBounds(300, y,200,40);
        ChonNhanVien.setBounds(500, y+10, 30, 30);y+=50;
        NgayNhap.setBounds(10, y,200,40);
        // add to panel
        ChonNhanVien.setEnabled(false);
        panel.add(MaHDN);
        panel.add(TongTien);
        panel.add(NhaCungCap);
        panel.add(NgayNhap);
        panel.add(NhanVien);
        panel.add(ChonNhanVien);
        panel.add(ChonNhaCungCap);
        
        
        //set tăng mã tự động
        String maHoaDonNhap= Tool.tangMa3(HoaDonNhapBUS.getMaHoaDonNhapCuoi()); 
        MaHDN.setText(maHoaDonNhap);
        //Lấy mã nhân viên từ tài khoản đã đăng nhập
        NhanVien.setText(Tool.IDNhanVienHienHanh);
        String ngayNhap = Tool.getNgayLap().toString(); // set ngày
        NgayNhap.setText(ngayNhap);
        //kết thúc thêm mới
        //Tạo sự kiện khi ấn vào nút thì hiện cửa sổ chọn nhà cung cấp nếu người dùng không nhớ mã nhà cung cấp
        ChonNhaCungCap.addActionListener((ae) -> {
            try {
                formchon = new FormChon(NhaCungCap,"Nhà cung cấp");
            } catch (Exception ex) {
                Logger.getLogger(GUINhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            formchon.setVisible(true);
            
        });
        return panel;
    }
    @Override
    //Hàm này tạo bảng chứa các nguyên liệu cần nhập
    protected JPanel panelThanhToan(){
        JPanel panel=new JPanel();
        ThanhToan=new MyTable();
        ThanhToan.setHeaders(new String[]{"Mã nguyên liệu","Tên nguyên liệu","Giá","Loại","Số lượng"});//chỗ này bỏ hình ảnh và đơn vị tính vì không cần
        ThanhToan.pane.setPreferredSize(new Dimension(GUIMenu.width_content*49/100, 300));        
        panel.add(ThanhToan);   
        return panel;
    }
    //Hàm này xử lý việc ấn thêm nguyên liệu 
    private void Them_click(ActionEvent e){
        int i = table_NguyenLieu.tb.getSelectedRow();
        
        if (i == -1) {
            op.showMessageDialog(null, "Vui lòng chọn 1 hàng để thêm");
        } 
        else 
        {
            int a=0;
            if (!Tool.isNumber(txSoLuong.getText())) {
                op.showMessageDialog(null, "Vui lòng nhập lại số lượng");
            } 
            else
            {    
                a=Integer.parseInt(txSoLuong.getText());
                int SlTrongTable=Integer.parseInt(String.valueOf(table_NguyenLieu.tbModel.getValueAt(i, 6)));
                //Rào việc đặt số lượng lớn hơn số hiện có
                if(a>SlTrongTable)
                {
                    op.showMessageDialog(null, "Số lượng không đủ");
                } 
                else 
                {
                    for(int j=0;j<ThanhToan.tbModel.getRowCount();j++)
                        {
                            if(ThanhToan.tbModel.getValueAt(j, 0)==table_NguyenLieu.tbModel.getValueAt(i, 0))
                            {
                                int SlTrongThanhToan=a+Integer.valueOf(String.valueOf(ThanhToan.tbModel.getValueAt(j, 4)));

                                ThanhToan.tbModel.setValueAt(SlTrongThanhToan, j, 4);
                                TinhTien();
                                return;


                            }
                        }
                            ThanhToan.addRow(new String[]{
                                String.valueOf(table_NguyenLieu.tbModel.getValueAt(i, 0)),
                                String.valueOf(table_NguyenLieu.tbModel.getValueAt(i, 1)),
                                String.valueOf(table_NguyenLieu.tbModel.getValueAt(i, 2)),
                                String.valueOf(table_NguyenLieu.tbModel.getValueAt(i, 4)),
                                String.valueOf(a)
                            });
                            TinhTien();
                
                }
            }
        }
    }
    @Override
    //Hàm tạo nút xóa món đã đặt và nút thanh toán hóa đơn
    protected JPanel panelCongCu(){
        JPanel panel=new JPanel(null);
        //Nút xóa
        Xoa=new JButton("Xóa");
        Xoa.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/delete1-30.png")));
        Xoa.setFont(new Font("Segoe UI", 0, 14));        
        Xoa.setBackground(Color.decode("#90CAF9"));
        
        Xoa.setBounds(0, 0, GUIMenu.width_content*25/100, 40);
        Xoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                Xoa_click(evt);
            }
        });
        panel.add(Xoa);
        //Nút thanh toán
        btnThanhToan=new JButton("Thanh toán");
        btnThanhToan.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/thanhtoan-30.png")));
        btnThanhToan.setFont(new Font("Segoe UI", 0, 14));        
        btnThanhToan.setBackground(Color.decode("#90CAF9"));
        btnThanhToan.setBounds(GUIMenu.width_content*25/100, 0, GUIMenu.width_content*25/100, 40);
        btnThanhToan.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ThanhToan_click(evt);
            }
        });
        panel.add(btnThanhToan);
        
        return panel;
    }
    //Hàm xử lý khi ấn vào nút xóa nằm ở thanh công cụ
    private void Xoa_click(ActionEvent e){
        int i=ThanhToan.tb.getSelectedRow();
        if (i == -1) {
            op.showMessageDialog(null, "Vui lòng chọn 1 hàng để xóa");
        } 
        else 
        {
            int option = op.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", op.YES_NO_OPTION);
            if (option == op.YES_OPTION) {
                
            ThanhToan.tbModel.removeRow(i);
            TinhTien();
            }
        }
    }
    //Hàm xử lý khi ấn vào nút thanh toán nằm ở thanh công cụ
    private void ThanhToan_click(ActionEvent e){
        //Ràng buộc dữ liệu
        if(checkText(MaHDN.getText(),
                TongTien.getText(),
                NhaCungCap.getText(),
                NgayNhap.getText(),
                NhanVien.getText(),
                ThanhToan.tb.getRowCount())){
            //Tạo đối tượng cho HoaDonBUS để chuẩn bị cho việc ghi vào arraylist và database
            HoaDonNhapBUS hdnbus=new HoaDonNhapBUS();
            //Tạo đối tượng cho ChiTietHoaDonBUS để chuẩn bị cho việc ghi vào arraylist và database
            ChiTietHoaDonNhapBUS cthdnbus=new ChiTietHoaDonNhapBUS();
            
            //Tạo DTO và truyền dữ liệu trực tiếp thông qua constructor 
            HoaDonNhapDTO hdDTO=new HoaDonNhapDTO(MaHDN.getText(),
                                            NhanVien.getText(),
                                            NhaCungCap.getText(),
                                            LocalDate.parse(NgayNhap.getText()),
                                            Double.parseDouble(TongTien.getText()),
                                            "Hiện");
            //Thêm vào hóa đơn nhập
            hdnbus.them(hdDTO);
            //Tạo hàm duyệt vì cần thêm nhiều chi tiết hóa đơn nhập
            for(int i=0;i<ThanhToan.tb.getRowCount();i++){
                String manguyenlieu=String.valueOf(ThanhToan.tbModel.getValueAt(i, 0));
                int soluong=Integer.parseInt(String.valueOf(ThanhToan.tbModel.getValueAt(i, 4)));
                float dongia=Float.valueOf(String.valueOf(ThanhToan.tbModel.getValueAt(i, 2)));
                float thanhtien=dongia*soluong;
                //Tạo DTO và truyền dữ liệu trực tiếp thông qua constructor 
                ChiTietHoaDonNhapDTO ctDTO=new ChiTietHoaDonNhapDTO(MaHDN.getText(),manguyenlieu,soluong,dongia,thanhtien );
                //Thêm vào chi tiết hóa đơn 
                cthdnbus.them(ctDTO);
                cthdnbus.trusoluong(ctDTO);
            }
            //Clear
            String maHoaDonNhap= Tool.tangMa3(HoaDonNhapBUS.getMaHoaDonNhapCuoi());
            MaHDN.setText(maHoaDonNhap);
            NhaCungCap.setText("");
            NgayNhap.setText(Tool.getNgayLap().toString());
            TongTien.setText("");
            ThanhToan.clear();
            LamMoi();
        }
    }
    //Ràng buộc dữ liệu
    //Thứ tự truyền vào lần lượt trùng với các thứ tự ô text
    public boolean checkText(String checkMaHDN,String checkTien,String checkMaNCC,String checkNgay,String checkMaNV,int songuyenlieu){
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (checkMaHDN.equals("") 
                || checkTien.equals("") 
                || checkMaNCC.equals("") 
                || checkNgay.equals("") 
                || checkMaNV.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        } else if(songuyenlieu==0){
            op.showMessageDialog(null, "Vui lòng chọn nguyên liệu");
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
            TongTien.setText(String.valueOf(thanhtien));
        }
        else
        {
            TongTien.setText("");
        }
    }
    //Hàm khi ấn nút làm mới
    private void LamMoi() {
        table_NguyenLieu.clear();
        for (NguyenLieuDTO DTO : NguyenLieuBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table_NguyenLieu.addRow(DTO);
            }
        }
    }

    public MyTable getTable_NguyenLieu() {
        return table_NguyenLieu;
    }

    public MyTable getThanhToan() {
        return ThanhToan;
    }

    public JTextField getTxMaNL() {
        return txMaNL;
    }

    public JTextField getTxTenNL() {
        return txTenNL;
    }

    public JTextField getTxDonGia() {
        return txDonGia;
    }

    public JTextField getTxSoLuong() {
        return txSoLuong;
    }

    public JTextField getMaHDN() {
        return MaHDN;
    }

    public JTextField getNhaCungCap() {
        return NhaCungCap;
    }

    public JTextField getNgayNhap() {
        return NgayNhap;
    }

    public JTextField getNhanVien() {
        return NhanVien;
    }

    public JButton getChonNhaCungCap() {
        return ChonNhaCungCap;
    }

    public JTextField getSearch() {
        return search;
    }

    public JTextField getTongTien() {
        return TongTien;
    }

    public JButton getThem() {
        return Them;
    }

    public JButton getXoa() {
        return Xoa;
    }

    public JButton getBtnThanhToan() {
        return btnThanhToan;
    }
    
}


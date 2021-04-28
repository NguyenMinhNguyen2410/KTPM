package GUI;


import BUS.*;
import DTO.*;
import GUI.*;
import Report.PriceFormatter;

import button.MoreButton;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import button.DateButton;
import button.RefreshButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getIcon;


/**
 *
 * @author nguye
 */
public class GUIThongKe extends JPanel {

    public static final int width = 1300, height = 740;
    
    public GUIThongKe() throws Exception {

        ThongKe_Hoang tkH = new ThongKe_Hoang();//tạo panel thống kê tổng quát trong thống kê

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);//tạo panel thống kê
        tabs.setPreferredSize(new Dimension(width, height));

        //add tab thong ke san pham

        tabs.addTab("Thống kê tổng quát", getIcon("thongke-30.png"), tkH);
        tabs.addTab("Món ăn", getIcon("monan-30.png"), null);
        tabs.addTab("Nhân viên", getIcon("nhanvien-30.png"), null);
        tabs.addTab("Khách hàng", getIcon("khachhang-30.png"), null);
        tabs.addTab("Nhà cung cấp", getIcon("nhacungcap-30.png"), null);

        tabs.addChangeListener((ce) -> {//chọn các tab jpanel
            int i = tabs.getSelectedIndex();
            if (tabs.getComponentAt(i) == null) {
                switch (tabs.getTitleAt(i)) {
                    case "Món ăn":
                        tabs.setComponentAt(i, new ThongKeMonAn());
                        break;
                    case "Nhân viên":
                        tabs.setComponentAt(i, new ThongKeNhanVien());
                        break;
                    case "Khách hàng":
                        tabs.setComponentAt(i, new ThongKeKhachHang());
                        break;
                    case "Nhà cung cấp":
                        tabs.setComponentAt(i, new ThongKeNhaCungCap());
                        break;
                }
            }
        });
        this.add(tabs);
    }
    private ImageIcon getIcon(String filename) {        
        return new ImageIcon(this.getClass().getResource("/Images/Icon/" + filename));
    }
}

class ThongKe_Hoang extends JPanel {
    MonAnBUS qlmaBUS = new MonAnBUS();
    NhanVienBUS qlnvBUS = new NhanVienBUS();
    KhachHangBUS qlkhBUS = new KhachHangBUS();
    HoaDonBUS qlhdBUS = new HoaDonBUS();
    NhaCungCapBUS qlnccBUS = new NhaCungCapBUS();
    HoaDonNhapBUS qlhdnBUS = new HoaDonNhapBUS();
    ChiTietHoaDonBUS qlcthdBUS = new ChiTietHoaDonBUS();
    ChiTietHoaDonNhapBUS qlcthdnBUS = new ChiTietHoaDonNhapBUS();
    NguyenLieuBUS qlnlBUS = new NguyenLieuBUS();

    JTextField txNgay1 = new JTextField(7);
    JTextField txNgay2 = new JTextField(7);
    JTextField txNhanVien = new JTextField(10);
    JTextField txKhachHang = new JTextField(10);
    JTextField txNhaCC = new JTextField(10);
    JTextField txMonAn = new JTextField(10);
    JTextField txNguyenlieu = new JTextField(10);


    DatePicker dPicker1;
    DatePicker dPicker2;
    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonKhachHang = new MoreButton();
    MoreButton btnChonNhaCC = new MoreButton();
    MoreButton btnChonMonAn = new MoreButton();
    MoreButton btnChonNguyenlieu = new MoreButton();

    JTabbedPane tabDoiTuongThongKe = new JTabbedPane();
    JPanel plThongKeHoaDon = new JPanel();
    JPanel plThongKePhieuNhap = new JPanel();
    JPanel plThongKeSoLuong = new JPanel();

    GUIMyTable tbThongKeHoaDon = new GUIMyTable();
    GUIMyTable tbThongKePhieuNhap = new GUIMyTable();

    GUIMyTable tbKetQuaHoaDon = new GUIMyTable();
    GUIMyTable tbKetQuaPhieuNhap = new GUIMyTable();

    JPanel plMonAn, plNhanVien, plKhachHang, plNhaCC,plNguyenlieu;
    RefreshButton btnRefresh = new RefreshButton();

    public ThongKe_Hoang() throws Exception {
        setLayout(new BorderLayout());

        // panel chon ngay
        DatePickerSettings ds = new DatePickerSettings();
        ds.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(ds);
        dPicker2 = new DatePicker(ds.copySettings());
        dPicker1.addDateChangeListener((dce) -> {
            txNgay1.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2.addDateChangeListener((dce) -> {
            txNgay2.setText(dPicker2.getDateStringOrEmptyString());
        });
        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);
        
        txNgay1.setBorder(BorderFactory.createTitledBorder("Từ"));
        txNgay2.setBorder(BorderFactory.createTitledBorder("Đến"));

        JPanel plChonNgay = new JPanel();
        plChonNgay.setBorder(BorderFactory.createTitledBorder("Khoảng ngày"));

        addDocumentListener(txNgay1);
        addDocumentListener(txNgay2);
        plChonNgay.add(txNgay1);
        plChonNgay.add(dPicker1);
        plChonNgay.add(txNgay2);
        plChonNgay.add(dPicker2);
        
        // event
        // nút chọn
        btnChonMonAn.addActionListener((ae) -> {
            GUIFormChon a = null;
            try {
                a = new GUIFormChon(txMonAn,"Món ăn");
                
            } catch (Exception ex) {
                Logger.getLogger(GUIThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setVisible(true);
            
        });
        btnChonNhanVien.addActionListener((ae) -> {
           GUIFormChon a = null;
            try {
                a = new GUIFormChon(txNhanVien,"Nhân viên");
            } catch (Exception ex) {
                Logger.getLogger(GUIThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setVisible(true);
        });
        btnChonKhachHang.addActionListener((ae) -> {
            GUIFormChon a = null;
            try {
                a = new GUIFormChon(txKhachHang,"Khách hàng");
            } catch (Exception ex) {
                Logger.getLogger(GUIThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setVisible(true);        
        });
        btnChonNhaCC.addActionListener((ae) -> {
           GUIFormChon a = null;
            try {
                a = new GUIFormChon(txNhaCC,"Nhà cung cấp");
            } catch (Exception ex) {
                Logger.getLogger(GUIThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setVisible(true);    
        });
        btnChonNguyenlieu.addActionListener((ae) -> {
           GUIFormChon a = null;
            try {
                a = new GUIFormChon(txNguyenlieu,"Nguyên liệu");
            } catch (Exception ex) {
                Logger.getLogger(GUIThongKe.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setVisible(true);    
        });
        btnRefresh.addActionListener((ae) -> {         
            try {           
                refresh();
            } catch (Exception ex) {
                Logger.getLogger(ThongKe_Hoang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //panel tìm kiếm
        plMonAn = getPanelTieuChi("Món ăn", txMonAn, btnChonMonAn);
        plNhanVien = getPanelTieuChi("Nhân viên", txNhanVien, btnChonNhanVien);
        plKhachHang = getPanelTieuChi("Khách hàng", txKhachHang, btnChonKhachHang);
        plNhaCC = getPanelTieuChi("Nhà cung cấp", txNhaCC, btnChonNhaCC);
        plNguyenlieu = getPanelTieuChi("Nguyên liệu", txNguyenlieu, btnChonNguyenlieu);

        // panel chon tieu chi
        JPanel plChonTieuChi = new JPanel();
        plChonTieuChi.add(plChonNgay);
        plChonTieuChi.add(plMonAn);
        plChonTieuChi.add(plNhanVien);
        plChonTieuChi.add(plKhachHang);
        plChonTieuChi.add(plNhaCC);
        plChonTieuChi.add(plNguyenlieu);
        plChonTieuChi.add(btnRefresh);
        this.add(plChonTieuChi, BorderLayout.NORTH);

        // panel thong ke hoa don (ban duoc)
        plThongKeHoaDon.setLayout(new BorderLayout());
        tbThongKeHoaDon.setHeaders(new String[]{"Hóa đơn", "Tên nhân viên", "Tên khách hàng", "Tên món ăn", "Số lượng", "Đơn giá", "Thành tiền"});
        //sắp xếp vị trí từ trong cột
        tbThongKeHoaDon.setAlignment(0, JLabel.CENTER);
        tbThongKeHoaDon.setAlignment(4, JLabel.CENTER);
        tbThongKeHoaDon.setAlignment(5, JLabel.RIGHT);
        tbThongKeHoaDon.setAlignment(6, JLabel.RIGHT);
        plThongKeHoaDon.add(tbThongKeHoaDon, BorderLayout.CENTER);

        tbKetQuaHoaDon.setHeaders(new String[]{"TỔNG TẤT CẢ", "", "", "", "", "", "DOANH THU"});
        tbKetQuaHoaDon.setPreferredSize(new Dimension(GUIThongKe.width, 75));
        tbKetQuaHoaDon.setAlignment(0, JLabel.CENTER);
        tbKetQuaHoaDon.setAlignment(4, JLabel.CENTER);
        tbKetQuaHoaDon.setAlignment(5, JLabel.RIGHT);
        tbKetQuaHoaDon.setAlignment(6, JLabel.RIGHT);
        plThongKeHoaDon.add(tbKetQuaHoaDon, BorderLayout.SOUTH);

        // panal thong ke nhap (nhap kho)
        plThongKePhieuNhap.setLayout(new BorderLayout());
        tbThongKePhieuNhap.setHeaders(new String[]{"Hóa đơn nhập", "Tên nhân viên", "Tên nhà cung cấp", "Tên nguyên liệu", "Số lượng", "Đơn giá", "Thành tiền"});
        tbThongKePhieuNhap.setAlignment(0, JLabel.CENTER);
        tbThongKePhieuNhap.setAlignment(4, JLabel.CENTER);
        tbThongKePhieuNhap.setAlignment(5, JLabel.RIGHT);
        tbThongKePhieuNhap.setAlignment(6, JLabel.RIGHT);
        plThongKePhieuNhap.add(tbThongKePhieuNhap, BorderLayout.CENTER);

        tbKetQuaPhieuNhap.setHeaders(new String[]{"TỔNG TẤT CẢ", "", "", "", "", "", "CHI TIÊU"});
        tbKetQuaPhieuNhap.setPreferredSize(new Dimension(GUIThongKe.width, 75));
        tbKetQuaPhieuNhap.setAlignment(0, JLabel.CENTER);
        tbKetQuaPhieuNhap.setAlignment(4, JLabel.CENTER);
        tbKetQuaPhieuNhap.setAlignment(5, JLabel.RIGHT);
        tbKetQuaPhieuNhap.setAlignment(6, JLabel.RIGHT);
        plThongKePhieuNhap.add(tbKetQuaPhieuNhap, BorderLayout.SOUTH);
        
        // panel thong ke tong so
        plThongKeSoLuong = new JPanel();
        setDataToPanelTong();

        // tabpane doi tuong thong ke tong quat
        tabDoiTuongThongKe.setBackground(Color.yellow);
        tabDoiTuongThongKe.addTab("Tổng",plThongKeSoLuong);
        tabDoiTuongThongKe.addTab("Bán ra",  plThongKeHoaDon);
        tabDoiTuongThongKe.addTab("Nhập vào",  plThongKePhieuNhap);

        // event chuyen tab
        // tab ban dau la tong ke tong quat nen an het
        plNguyenlieu.setVisible(false);
        plNhaCC.setVisible(false);
        plNhanVien.setVisible(false);
        plMonAn.setVisible(false);
        plKhachHang.setVisible(false);
//        plChonNgay.setVisible(false);
        btnRefresh.setVisible(false);
        
        // event biến đổi panel tiềm kiếm theo tab bán hoặc nhập trong thống kê tổng quát
        tabDoiTuongThongKe.addChangeListener((ce) -> {
            Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
            Boolean HoaDonNhap_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKePhieuNhap);
            btnRefresh.setVisible(HoaDon_isSelected||HoaDonNhap_isSelected);
            plNhaCC.setVisible(HoaDonNhap_isSelected);
            plNguyenlieu.setVisible(HoaDonNhap_isSelected);
            plNhanVien.setVisible(HoaDon_isSelected || HoaDonNhap_isSelected);
            plKhachHang.setVisible(HoaDon_isSelected);
            plMonAn.setVisible(HoaDon_isSelected);
        });

        this.add(tabDoiTuongThongKe, BorderLayout.CENTER);

        // show giá trị đầu
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
    }
    
//    Này là panel tổng trong thống kê tổng quát
    private void setDataToPanelTong() {
        plThongKeSoLuong.removeAll();
        plThongKeSoLuong.add(getJPanelTong("MÓN ĂN", "thucan1-100.png", qlmaBUS.getMonAnDTO().size(), Color.BLUE));
        plThongKeSoLuong.add(getJPanelTong("NHÂN VIÊN", "nhanvien1-100.png", qlnvBUS.getNhanVienDTO().size(), Color.BLUE));
        plThongKeSoLuong.add(getJPanelTong("KHÁCH HÀNG", "khachhang1-100.png", qlkhBUS.getKhachHangDTO().size(), Color.BLUE));
        plThongKeSoLuong.add(getJPanelTong("NHÀ CUNG CẤP", "nhacungcap-100.png", qlnccBUS.getNhaCungCapDTO().size(), Color.BLUE));
    }

    public void refresh() throws Exception  {
        qlmaBUS.docDSMonAn();
        qlnvBUS.docDSNV();
        qlkhBUS.docDSKH();
        qlnccBUS.docDSNCC();
        qlnlBUS.docDSNL();
        
        dPicker1.setDate(null);
        dPicker2.setDate(null);
        txMonAn.setText("");
        txNhanVien.setText("");
        txKhachHang.setText("");
        txNhaCC.setText(""); 
        txNguyenlieu.setText("");

        //chọn panel bán và nhập hàng
        Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
        if (HoaDon_isSelected) {
           onChangeThongKeBanHang();
        } else {
           onChangeThongKeNhapHang();
        }
        
        setDataToPanelTong();
    }

    //tạo panel cho các nút 
    private JPanel getPanelTieuChi(String name, JTextField tx, MoreButton b) {
        JPanel result = new JPanel();
        result.setBorder(BorderFactory.createTitledBorder(name));
        tx.setBorder(BorderFactory.createTitledBorder(" "));

        addDocumentListener(tx);

        result.add(tx);
        result.add(b);

        return result;
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
                if (HoaDon_isSelected) {
                    try {
                        onChangeThongKeBanHang();
                    } catch (Exception ex) {
                        Logger.getLogger(ThongKe_Hoang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        onChangeThongKeNhapHang();
                    } catch (Exception ex) {
                        Logger.getLogger(ThongKe_Hoang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
        });
    }

    private ImageIcon getIcon(String filename) {
        return new ImageIcon(this.getClass().getResource("/Images/Icon/" + filename));
    }

//   này là phần bán ra
    private void onChangeThongKeBanHang() throws Exception {
        tbThongKeHoaDon.clear();

        int tongSLHoaDon = 0;
        int tongSLMonAn = 0;
        float tongTatCaTien = 0;

        String mama = txMonAn.getText();
        String manvLoc = txNhanVien.getText();
        String makhLoc = txKhachHang.getText();

        ArrayList<NhanVienDTO> dsnv = new ArrayList<>(); // danh sách lưu các nhân viên có làm hóa đơn
        ArrayList<KhachHangDTO> dskh = new ArrayList<>(); // danh sách lưu các khách hàng có làm hóa đơn
        ArrayList<MonAnDTO> dsma = new ArrayList<>(); // lưu các mon an

        MyCheckDate mcd = new MyCheckDate(txNgay1, txNgay2);

        for (HoaDonDTO hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
            float tongTien = 0;
            float tongTien2 = 0;

            ArrayList<ChiTietHoaDonDTO> dscthd = qlcthdBUS.getALLChiTiet(hd.getIDHoaDon());//bổ sung thêm ở cthd bus

            if (dscthd.size() > 0) {
                NhanVienDTO nv = qlnvBUS.getNhanVienDTO(hd.getIDNhanVien());  //bổ sung thêm phần getNhanVienDTO, getKhachHangDTO giống ông Nhân
                KhachHangDTO kh = qlkhBUS.getKhachHangDTO(hd.getIDKhachHang());  //trong nhân viên với khách hàng bus

                // lọc theo textfield mã
                // bỏ qua lần lặp for này nếu nhân viên hoặc khách hàng ko thỏa bộ lọc
                if (!manvLoc.equals("") && !nv.getIDNhanVien().equals(manvLoc)
                        || !makhLoc.equals("") && !kh.getIDKhachHang().equals(makhLoc)) {
                    continue; // continue này sẽ lấy vòng lặp HoaDon tiếp theo
                }

                // thêm vào arraylist để đếm
                if (!dsnv.contains(nv)) {
                    dsnv.add(nv); // thêm vào nếu chưa có
                }
                if (!dskh.contains(kh)) {
                    dskh.add(kh); // thêm vào nếu chưa có
                }

                tbThongKeHoaDon.addRow(new String[]{
                    hd.getIDHoaDon() + " (" + hd.getNgayLap().toString() + ")",
                    nv.getTenNhanVien() + " (" + nv.getIDNhanVien() + ")",
                    kh.getTenKhachHang() + " (" + kh.getIDKhachHang()+ ")",
                    "", "", "", "","",""
                });

                for (ChiTietHoaDonDTO cthd : dscthd) {
                    MonAnDTO ma = qlmaBUS.getMonAnDTO(cthd.getIDMonAn());
                    // lọc
                    if (!mama.equals("") && !ma.getIDMonAn().equals(mama)) {
                        continue; // continue này sẽ lấy vòng lặp ChiTietHoaDon tiếp theo
                    }
                    // thêm vào danh sách để đếm
                    if (!dsma.contains(ma)) {
                        dsma.add(ma); // thêm vào nếu chưa có
                    }
                               
                    if(!mama.equals("") && ma.getIDMonAn().equals(mama))
                    {     
                       if (!mama.equals("") && !ma.getIDMonAn().equals(mama)) {
                        continue; // continue này sẽ lấy vòng lặp ChiTietHoaDon tiếp theo
                       }
                       if (!manvLoc.equals("") && !nv.getIDNhanVien().equals(manvLoc)
                        || !makhLoc.equals("") && !kh.getIDKhachHang().equals(makhLoc)) 
                       {
                         continue ; // continue này sẽ lấy vòng lặp HoaDon tiếp theo
                       }      
                       if (!dsma.contains(ma)) {
                        dsma.add(ma); // thêm vào nếu chưa có
                       }
                       
                       tongTien +=hd.getTienGiamGia();
                       
                    }
                    
                    tbThongKeHoaDon.addRow(new String[]{"", "", "",
                        ma.getTenMonAn()+ " (" + ma.getIDMonAn()+ ")",
                        String.valueOf(cthd.getSoLuong()),
                        PriceFormatter.format((float) cthd.getDonGia()),                       
                        PriceFormatter.format((float) cthd.getThanhTien())                        
                    });
                    
                    tongTien2 = (float) (tongTien += cthd.getDonGia()*cthd.getSoLuong())-hd.getTienGiamGia();  
                    tongSLMonAn += cthd.getSoLuong();
                    
                }
               
            }
            tbThongKeHoaDon.addRow(new String[]{"", "", "", "", "", "Giảm giá", PriceFormatter.format((float) hd.getTienGiamGia())});             
            tbThongKeHoaDon.addRow(new String[]{"", "", "", "","","Tổng cộng", PriceFormatter.format(tongTien2)});
            tbThongKeHoaDon.addRow(new String[]{"", "", "", "", "","",""});

            tongTatCaTien += tongTien2;
            tongSLHoaDon++;
        }        

        tbKetQuaHoaDon.clear();
        tbKetQuaHoaDon.addRow(new String[]{
            tongSLHoaDon + " hóa đơn",
            dsnv.size() + " nhân viên", 
            dskh.size() + " khách hàng",
            dsma.size() + " món ăn",
            tongSLMonAn + " phần ",
            "",
            PriceFormatter.format(tongTatCaTien)
        });
    }
//   này là phần nguyên liêu nhập 
    private void onChangeThongKeNhapHang() throws Exception {
        tbThongKePhieuNhap.clear();

        int tongSLPhieuNhap = 0;
        int tongSLSanPham = 0;
        float tongTatCaTien = 0;

        String manlLoc = txNguyenlieu.getText();
        String manvLoc = txNhanVien.getText();
        String manccLoc = txNhaCC.getText();

        ArrayList<NhanVienDTO> dsnv = new ArrayList<>(); // danh sách lưu các nhân viên có làm phiếu nhập
        ArrayList<NhaCungCapDTO> dsncc = new ArrayList<>(); // danh sách lưu các ncc có làm phiếu nhập
        ArrayList<NguyenLieuDTO> dsnl = new ArrayList<>(); // lưu các sản phẩm

        MyCheckDate mcd = new MyCheckDate(txNgay1, txNgay2);

        for (HoaDonNhapDTO hdn : qlhdnBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
            float tongTien = 0;
            ArrayList<ChiTietHoaDonNhapDTO> cthdn = qlcthdnBUS.getAllChiTiet(hdn.getIDHoaDonNhap());

            if (cthdn.size() > 0) {
                NhanVienDTO nv = qlnvBUS.getNhanVienDTO(hdn.getIDNhanVien());
                NhaCungCapDTO ncc = qlnccBUS.getNhaCungCapDTO(hdn.getIDNhaCungCap());

//                // lọc theo textfield mã
//                // bỏ qua lần lặp for này nếu nhân viên hoặc nha cung cap ko thỏa bộ lọc
                if (!manvLoc.equals("") && !nv.getIDNhanVien().equals(manvLoc)
                        || !manccLoc.equals("") && !ncc.getIDNhaCungCap().equals(manccLoc)) {
                    continue; // continue này sẽ lấy vòng lặp PhieuNhap tiếp theo
                }

                // thêm vào arraylist để đếm
                if (!dsnv.contains(nv)) {
                    dsnv.add(nv); // thêm vào nếu chưa có
                }
                if (!dsncc.contains(ncc)) {
                    dsncc.add(ncc); // thêm vào nếu chưa có
                }

                tbThongKePhieuNhap.addRow(new String[]{
                    hdn.getIDHoaDonNhap()+ " (" + hdn.getNgayNhap().toString() + ")",
                    nv.getTenNhanVien()+ " (" + nv.getIDNhanVien()+ ")",
                    ncc.getTenNhaCungCap()+ " (" + ncc.getIDNhaCungCap()+ ")",
                    "", "", "", "",
                });

                for (ChiTietHoaDonNhapDTO dsctpn : cthdn) {
                    NguyenLieuDTO nl = qlnlBUS.getNguyenLieuDTO(dsctpn.getIDNguyenLieu());

                    // lọc
                    if (!manlLoc.equals("") && !nl.getIDNguyenLieu().equals(manlLoc)) {
                        continue; // continue này sẽ lấy vòng lặp ChiTietPhieuNhap tiếp theo
                    }

                    // thêm vào danh sách để đếm
                    if (!dsnl.contains(nl)) {
                        dsnl.add(nl); // thêm vào nếu chưa có
                    }

                    tbThongKePhieuNhap.addRow(new String[]{"", "", "",
                        nl.getTenNguyenLieu()+ " (" + nl.getIDNguyenLieu()+ ")",
                        String.valueOf(dsctpn.getSoLuong()),
                        PriceFormatter.format(dsctpn.getGiaNhap()),
                        PriceFormatter.format(dsctpn.getSoLuong() * dsctpn.getGiaNhap())
                    });

                    tongTien += dsctpn.getGiaNhap()* dsctpn.getSoLuong();
                    tongSLSanPham += dsctpn.getSoLuong();
                }
            }
            tbThongKePhieuNhap.addRow(new String[]{"", "", "", "", "", "Tổng cộng", PriceFormatter.format(tongTien)});
            tbThongKePhieuNhap.addRow(new String[]{"", "", "", "", "", "", ""});

            tongTatCaTien += tongTien;
            tongSLPhieuNhap++;
        }

        tbKetQuaPhieuNhap.clear();
        tbKetQuaPhieuNhap.addRow(new String[]{
            tongSLPhieuNhap + " hóa đơn nhập",
            dsnv.size() + " nhân viên",
            dsncc.size() + " nhà cung cấp",
            dsnl.size() + " loại",
            tongSLSanPham + " phần",
            "",
            PriceFormatter.format(tongTatCaTien)
        });
    }
    
    // tạo nội dung trong panel tổng
    private JPanel getJPanelTong(String name, String iconName, int soluong, Color c) {
        JPanel result = new JPanel();
        result.setLayout(new BorderLayout());
        result.setPreferredSize(new Dimension(GUIThongKe.width / 4 - 15, 200));
        result.setBorder(BorderFactory.createLineBorder(c));
        
        // hinh anh           
        JLabel lbIcon = new JLabel();
        lbIcon.setIcon(getIcon(iconName));
        result.add(lbIcon, BorderLayout.WEST);
        
        // tieu de
        JPanel plLeft = new JPanel();
        
        JLabel lbTieuDe = new JLabel(name, JLabel.CENTER);
        lbTieuDe.setPreferredSize(new Dimension(GUIThongKe.width / 4 - 100, 70));
        lbTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        plLeft.add(lbTieuDe);
        
        JLabel lbSoLuong = new JLabel(String.valueOf(soluong), JLabel.CENTER);
        lbSoLuong.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        lbSoLuong.setForeground(c);
        plLeft.add(lbSoLuong);
        
        result.add(plLeft, BorderLayout.CENTER);
        
        return result;
    }
}
// panel tìm kiếm theo ngày
class MyCheckDate {
    LocalDate fromDate = null;
    LocalDate toDate = null;
    String khoangTG = "";

    public MyCheckDate(JTextField txFrom, JTextField txTo) {
        try {
            fromDate = LocalDate.parse(txFrom.getText());
            txFrom.setForeground(Color.black);
            khoangTG += String.valueOf(fromDate);

        } catch (DateTimeParseException e) {
            txFrom.setForeground(Color.red);
            khoangTG += "Từ ngày đầu";
        }
        try {
            toDate = LocalDate.parse(txTo.getText());
            txTo.setForeground(Color.black);
            khoangTG += " đến " + String.valueOf(toDate);

        } catch (DateTimeParseException e) {
            txTo.setForeground(Color.red);
            khoangTG += " đến nay";
        }
       
    }
    public LocalDate getNgayTu() {
        return fromDate;
    }

    public LocalDate getNgayDen() {
        return toDate;
    }

    public String getKhoangTG() {
        return khoangTG;
    }
}

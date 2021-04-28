/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

/**
 *
 * @author Piger Streaming
 */
import BUS.CongThucBUS;
import BUS.HoaDonBUS;
import BUS.HoaDonNhapBUS;
import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import BUS.MonAnBUS;
import BUS.NguyenLieuBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.CongThucDTO;
import DTO.HoaDonDTO;
import DTO.HoaDonNhapDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.MonAnDTO;
import DTO.NguyenLieuDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhanQuyenDTO;
import DTO.TaiKhoanDTO;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DocExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Đọc excel", FileDialog.LOAD);

    public DocExcel() {

    }
    private String getFile() {
        fd.setFile("*.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
    
    //Đọc file excel Món ăn
    public void docFileExcelMonAn() {
        fd.setTitle("Nhập dữ liệu món ăn từ excel"); //set Tiêu đề
        String url = getFile(); //tạo file
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream); //Tạo workbook excel mới
            HSSFSheet sheet = workbook.getSheetAt(0); // Tạo sheet excel mới
            Iterator<Row> rowIterator = sheet.iterator(); //Set row trong sheet
            Row row1 = rowIterator.next(); //Tạo biến row
//Tạo biến khi trùng, đếm số lần thêm, ghi đè, bỏ qua
            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
//Tạo cell (ô) - giá trị, 
                    String id = cellIterator.next().getStringCellValue(); 
                    String tenmon = cellIterator.next().getStringCellValue();
                    String donvitinh = cellIterator.next().getStringCellValue();
                    int dongia = (int) cellIterator.next().getNumericCellValue();
                    String hinhanh = cellIterator.next().getStringCellValue();
                    String loai = cellIterator.next().getStringCellValue();
                    int soluong = (int) cellIterator.next().getNumericCellValue();

                    MonAnBUS monanBUS = new MonAnBUS();
                    MonAnDTO monanOld = monanBUS.getMonAnDTO(id);

                    if (monanOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã", "Tên Món", "Đơn vị tính", "Giá", "Hình ảnh", "Loại", "Số lượng" });
                            mtb.addRow(new String[]{
                                "Cũ:", monanOld.getIDMonAn(),
                                monanOld.getTenMonAn(),
                                monanOld.getDonViTinh(),
                                String.valueOf(monanOld.getDonGia()),
                                monanOld.getHinhAnh(),
                                monanOld.getLoai(),
                                String.valueOf(monanOld.getSoLuong())
                            });

                            mtb.addRow(new String[]{
                                "Mới:", id, tenmon, donvitinh, String.valueOf(dongia), hinhanh, loai, String.valueOf(soluong)                            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            MonAnDTO DTO=new MonAnDTO(id, tenmon, donvitinh,dongia, hinhanh, loai, soluong, "Hiện");
                            monanBUS.sua(DTO,MonAnBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        MonAnDTO monan = new MonAnDTO(id, tenmon, donvitinh, dongia, hinhanh, loai, soluong, "Hiện");
                        monanBUS.them(monan);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    
     //Đọc file excel Nguyên liệu
    public void docFileExcelNguyenLieu() {
        fd.setTitle("Nhập dữ liệu nguyên liệu từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String tennguyenlieu = cellIterator.next().getStringCellValue();
                    int dongia = (int) cellIterator.next().getNumericCellValue();
                    String hinhanh = cellIterator.next().getStringCellValue();
                    String loai = cellIterator.next().getStringCellValue();
                    String donvitinh = cellIterator.next().getStringCellValue();
                    int soluong = (int) cellIterator.next().getNumericCellValue();

                    NguyenLieuBUS nguyenlieuBUS = new NguyenLieuBUS();
                    NguyenLieuDTO nguyenlieuOld = nguyenlieuBUS.getNguyenLieuDTO(id);

                    if (nguyenlieuOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"","Mã", "Tên", "Đơn vị tính", "Đơn giá", "Hình ảnh", "Loại", "Số lượng"});
                            mtb.addRow(new String[]{
                                "Cũ:", nguyenlieuOld.getIDNguyenLieu(),
                                nguyenlieuOld.getTenNguyenLieu(),
                                nguyenlieuOld.getDonViTinh(),
                                String.valueOf(nguyenlieuOld.getDonGia()),
                                nguyenlieuOld.getHinhAnh(),
                                nguyenlieuOld.getLoai(),
                                String.valueOf(nguyenlieuOld.getSoLuong())
                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, tennguyenlieu, donvitinh, String.valueOf(dongia), hinhanh, loai, String.valueOf(soluong)                            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            NguyenLieuDTO DTO=new NguyenLieuDTO(id, tennguyenlieu, donvitinh, dongia, hinhanh, loai, soluong, "Hiện");
                            nguyenlieuBUS.sua(DTO,NguyenLieuBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        NguyenLieuDTO nguyenlieu = new NguyenLieuDTO(id, tennguyenlieu, donvitinh, dongia, hinhanh, loai, soluong, "Hiện");
                        nguyenlieuBUS.them(nguyenlieu);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Công thức
    public void docFileExcelCongThuc() {
        fd.setTitle("Nhập dữ liệu công thức từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String idmonan = cellIterator.next().getStringCellValue();
                    String mota = cellIterator.next().getStringCellValue();
                    
                    
                    CongThucBUS congthucBUS = new CongThucBUS();
                    CongThucDTO congthucOld = congthucBUS.getCongThucDTO(id);

                    if (congthucOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã công thức", "Mã món ăn", "Mô tả" });
                            mtb.addRow(new String[]{
                                "Cũ:", congthucOld.getIDCongThuc(),
                                congthucOld.getIDMonAn(),
                                congthucOld.getMoTaCongThuc(),
                                
                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, idmonan, mota                            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            CongThucDTO DTO=new CongThucDTO(id, idmonan, mota, "Hiện");
                            congthucBUS.sua(DTO,CongThucBUS.timViTri(id));

                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        CongThucDTO ct = new CongThucDTO(id, idmonan, mota, "Hiện");
                        congthucBUS.them(ct);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Hóa đơn
    
    public void docFileExcelHoaDon() {
        fd.setTitle("Nhập dữ liệu hóa đơn từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String idnv = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    String idkh = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    String idkm = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    LocalDate ngaylap = LocalDate.parse(cellIterator.next().getStringCellValue());
                    int tiengiam = (int) cellIterator.next().getNumericCellValue();
                    int tongtien = (int) cellIterator.next().getNumericCellValue();

                    HoaDonBUS hoadonBUS = new HoaDonBUS();
                    HoaDonDTO hdOld = hoadonBUS.getHoaDonDTO(id);
                   
                    if (hdOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Mã khuyến mãi", "Ngày lập", "Tiền giảm giá", " Tổng tiền" });
                            mtb.addRow(new String[]{
                                "Cũ:", hdOld.getIDHoaDon(),
                                hdOld.getIDNhanVien(),
                                hdOld.getIDKhachHang(),
                                hdOld.getIDKhuyenMai(),
                                String.valueOf(hdOld.getNgayLap()),
                                String.valueOf(hdOld.getTienGiamGia()),
                                String.valueOf(hdOld.getTongTien()),
                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, idnv, idkh, idkm, String.valueOf(ngaylap), String.valueOf(tiengiam), String.valueOf(tongtien)            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            HoaDonDTO DTO = new HoaDonDTO(id, idnv, idkh, idkm, ngaylap, tiengiam, tongtien, "Hiện");
                            hoadonBUS.sua(DTO,HoaDonBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        HoaDonDTO hd = new HoaDonDTO(id, idnv, idkh, idkm, ngaylap, tiengiam, tongtien, "Hiện");
                        hoadonBUS.them(hd);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    
     //Đọc file excel Hóa đơn nhập
    public void docFileExcelHoaDonNhap() {
        fd.setTitle("Nhập dữ liệu hóa đơn nhập từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String idnv = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    String idncc = cellIterator.next().getStringCellValue(); // cần tách chuỗi
                    LocalDate ngaynhap = LocalDate.parse(cellIterator.next().getStringCellValue());
                    int tongtien = (int) cellIterator.next().getNumericCellValue();

                    HoaDonNhapBUS hdnBUS = new HoaDonNhapBUS();
                    HoaDonNhapDTO hdnOld = hdnBUS.getHoaDonNhapDTO(id);
                   
                    if (hdnOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã hóa đơn", "Mã nhân viên", "Mã nhà cung cấp", "Ngày nhập", " Tổng tiền" });
                            mtb.addRow(new String[]{
                                "Cũ:", hdnOld.getIDHoaDonNhap(),
                                hdnOld.getIDNhanVien(),
                                hdnOld.getIDNhaCungCap(),
                                String.valueOf(hdnOld.getNgayNhap()),
                                String.valueOf(hdnOld.getTongTien()),
                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, idnv, idncc, String.valueOf(ngaynhap), String.valueOf(tongtien)            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            HoaDonNhapDTO DTO = new HoaDonNhapDTO(id, idnv, idncc, ngaynhap, tongtien, "Hiện");
                            hdnBUS.sua(DTO,HoaDonNhapBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        HoaDonNhapDTO hdn = new HoaDonNhapDTO(id, idnv, idncc, ngaynhap, tongtien, "Hiện");
                        hdnBUS.them(hdn);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Khuyến mãi
    public void docFileExcelKhuyenMai() {
        fd.setTitle("Nhập dữ liệu khuyến mãi từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    int tiengiam = (int) cellIterator.next().getNumericCellValue();
                    LocalDate ngaybd = LocalDate.parse(cellIterator.next().getStringCellValue());
                    LocalDate ngaykt = LocalDate.parse(cellIterator.next().getStringCellValue());
                    String noidung = cellIterator.next().getStringCellValue();

                    KhuyenMaiBUS khuyenmaiBUS = new KhuyenMaiBUS();
                    KhuyenMaiDTO kmOld = khuyenmaiBUS.getKhuyenMaiDTO(id);
                   
                    if (kmOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"Mã khuyến mãi", "Tên chương trình", "Tiền giảm", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung" });
                            mtb.addRow(new String[]{
                                "Cũ:", kmOld.getIDKhuyenMai(),
                                kmOld.getTenChuongTrinh(),
                                String.valueOf(kmOld.getTienGiam()),
                                String.valueOf(kmOld.getNgayBatDau()),
                                String.valueOf(kmOld.getNgayKetThuc()),
                                kmOld.getNoiDungGiamGia(),
                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, ten, String.valueOf(tiengiam), String.valueOf(ngaybd), String.valueOf(ngaykt), noidung               
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            KhuyenMaiDTO DTO = new KhuyenMaiDTO(id, ten, tiengiam, ngaybd, ngaykt, noidung, "Hiện");
                            khuyenmaiBUS.sua(DTO,KhuyenMaiBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        KhuyenMaiDTO km = new KhuyenMaiDTO(id, ten, tiengiam, ngaybd, ngaykt, noidung, "Hiện");
                        khuyenmaiBUS.them(km);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    
     //Đọc file excel Khách hàng
    public void docFileExcelKhachHang() {
        fd.setTitle("Nhập dữ liệu món ăn từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ho = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String gmail = cellIterator.next().getStringCellValue();
                    String gioitinh = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    float tongchitieu = (float) cellIterator.next().getNumericCellValue();

                    KhachHangBUS khBUS = new KhachHangBUS();
                    KhachHangDTO khOld = khBUS.getKhachHangDTO(id);

                    
                    
                    if (khOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã khách hàng", "Họ", "Tên", "Gmail", "Giới tính", "SĐT", "Tổng chi tiêu" });
                            mtb.addRow(new String[]{
                                "Cũ:", khOld.getIDKhachHang(),
                                khOld.getHoKhachHang(),
                                khOld.getTenKhachHang(),
                                khOld.getGmail(),
                                khOld.getGioiTinh(),
                                khOld.getSoDienThoai(),
                                String.valueOf(khOld.getTongChiTieu())
                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, ho, ten, gmail, gioitinh, sdt, String.valueOf(tongchitieu)                   
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            KhachHangDTO DTO=new KhachHangDTO(id, ho, ten, gmail, gioitinh, sdt, tongchitieu, "Hiện");
                            khBUS.sua(DTO,KhachHangBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        KhachHangDTO khaha = new KhachHangDTO(id, ho, ten, gmail, gioitinh, sdt, tongchitieu, "Hiện");
                        khBUS.them(khaha);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Nhân viên
    public void docFileExcelNhanVien() {
        fd.setTitle("Nhập dữ liệu nhân viên từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ho = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String gmail = cellIterator.next().getStringCellValue();
                    String gioitinh = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    String chucvu = cellIterator.next().getStringCellValue();

                    NhanVienBUS nvBUS = new NhanVienBUS();
                    NhanVienDTO nvOld = nvBUS.getNhanVienDTO(id);

                    if (nvOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã nhân viên", "Họ", "Tên", "Gmail", "Giới tính", "SĐT", "Chức vụ" });
                            mtb.addRow(new String[]{
                                "Cũ:", nvOld.getIDNhanVien(),
                                nvOld.getHoNhanVien(),
                                nvOld.getTenNhanVien(),
                                nvOld.getGmail(),
                                nvOld.getGioiTinh(),
                                nvOld.getSoDienThoai(),
                                nvOld.getChucVu(),
                                
                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, ho, ten, gmail, gioitinh, sdt, chucvu                         
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            NhanVienDTO DTO=new NhanVienDTO(id, ho, ten, gmail, gioitinh, sdt, chucvu, "Hiện");
                            nvBUS.sua(DTO,NguyenLieuBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        NhanVienDTO nhanvien = new NhanVienDTO(id, ho, ten, gmail, gioitinh, sdt, chucvu, "Hiện");
                        nvBUS.them(nhanvien);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Nhà cung cấp
    public void docFileExcelNhaCungCap() {
        fd.setTitle("Nhập dữ liệu nhà cung cấp từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    String gmail = cellIterator.next().getStringCellValue();
                    String diachi = cellIterator.next().getStringCellValue();

                    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
                    NhaCungCapDTO nccOld = nccBUS.getNhaCungCapDTO(id);

                    if (nccOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã nhà cung cấp", "Tên", "SĐT", "Gmail", "Địa chỉ" });
                            mtb.addRow(new String[]{
                                "Cũ:", nccOld.getIDNhaCungCap(),
                                nccOld.getTenNhaCungCap(),
                                nccOld.getSoDienThoai(),
                                nccOld.getGmail(),
                                nccOld.getDiaChi(),
                            });
                            
                            mtb.addRow(new String[]{
                                "Mới:", id, ten, sdt, gmail, diachi                            
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            NhaCungCapDTO DTO=new NhaCungCapDTO(id, ten, sdt, gmail, diachi, "Hiện");
                            nccBUS.sua(DTO,NhaCungCapBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        NhaCungCapDTO nhacc = new NhaCungCapDTO(id, ten, sdt, gmail, diachi, "Hiện");
                        nccBUS.them(nhacc);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Tài khoản
    public void docFileExcelTaiKhoan() {
        fd.setTitle("Nhập dữ liệu tài khoản từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String idnv = cellIterator.next().getStringCellValue();
                    String idquyen = cellIterator.next().getStringCellValue();
                    String matkhau = cellIterator.next().getStringCellValue();


                    TaiKhoanBUS tkBUS = new TaiKhoanBUS();
                    TaiKhoanDTO tkOld = tkBUS.getTaiKhoanDTO(id);

                    if (tkOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Tài khoản", "Mã nhân viên", "Mã quyền", "Mật khẩu"});
                            mtb.addRow(new String[]{
                                "Cũ:", tkOld.getTaiKhoan(),
                                tkOld.getIDNhanVien(),
                                tkOld.getIDPhanQuyen(),
                                tkOld.getMatKhau(),

                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, idnv, idquyen, matkhau                  
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            TaiKhoanDTO DTO=new TaiKhoanDTO(id, idnv, idquyen, matkhau, "Hiện");
                            tkBUS.sua(DTO,TaiKhoanBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        TaiKhoanDTO taka = new TaiKhoanDTO(id, idnv, idquyen, matkhau, "Hiện");
                        tkBUS.them(taka);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
     //Đọc file excel Phân quyền
    public void docFileExcelPhanQuyen() {
        fd.setTitle("Nhập dữ liệu phân quyền từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String id = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String mota = cellIterator.next().getStringCellValue();

                    PhanQuyenBUS pqBUS = new PhanQuyenBUS();
                    PhanQuyenDTO pqOld = pqBUS.getPhanQuyenDTO(id);
                    

                    if (pqOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã quyền", "Tên quyền"});
                            mtb.addRow(new String[]{
                                "Cũ:", pqOld.getIDPhanQuyen(),
                                pqOld.getTenQuyen()

                            });
                            mtb.addRow(new String[]{
                                "Mới:", id, ten                          
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            PhanQuyenDTO DTO=new PhanQuyenDTO(id, ten, mota, "Hiện");
                            pqBUS.sua(DTO,PhanQuyenBUS.timViTri(id));
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }                      
                        
                    //Khi database trống    
                     
                    } else {          
                        PhanQuyenDTO phaqu = new PhanQuyenDTO(id, ten, mota, "Hiện");
                        pqBUS.them(phaqu);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            ex.printStackTrace();
           
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
}





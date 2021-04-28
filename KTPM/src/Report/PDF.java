/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietHoaDonNhapBUS;
import BUS.HoaDonBUS;
import BUS.HoaDonNhapBUS;
import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import BUS.MonAnBUS;
import BUS.NguyenLieuBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietHoaDonNhapDTO;
import DTO.HoaDonDTO;
import DTO.HoaDonNhapDTO;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.awt.FileDialog;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class PDF {
    Document document;
    FileOutputStream file;
    Font fontData;
    Font fontTitle;
    Font fontHeader;
    //Tạo của sổ file
    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    public PDF() {
        try {
            //Set font theo Arial trong Windows và các kích cỡ, định dạng thường
            //Tạo font mới cho dữ liệu
            fontData = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.NORMAL);
            //Tạo font mới cho tiêu đề
            fontTitle = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.NORMAL);
            //Tạo font cho các mục nhỏ bên trong
            fontHeader = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 13, Font.NORMAL);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //
    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy đường dẫn file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Không gọi được document !");
        }
    }
    //
    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    public void writeObject(String[] data) {
        Paragraph pdfData = new Paragraph();
        for (int i = 0; i < data.length; i++) {
            pdfData.add(data[i] + "  ");
        }
        try {
            document.add(pdfData);
        } catch (DocumentException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeTable(JTable _table) {
        PdfPTable pdfTable = new PdfPTable(_table.getColumnCount());
        for (int i = 0; i < _table.getRowCount(); i++) {
            for (int j = 0; j < _table.getColumnCount(); j++) {
                String data = String.valueOf(_table.getValueAt(i, j));
                PdfPCell cell = new PdfPCell(new Phrase(data, fontData));
                pdfTable.addCell(cell);
            }
        }
        try {
            document.add(pdfTable);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    private String getFile() {
        fd.setFile("untitled.pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
    
    
    public void writeHoaDon(String mahd) {
        String url = "";
        try {
            fd.setTitle("In hóa đơn");
            url = getFile();
            if (url == null) {
                return;
            }
            //đổ dòng dữ liệu vào file
            file = new FileOutputStream(url);
            //Tạo mới document
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            
            setTitle("Thông tin hóa đơn");
            //Hiện thông tin của hóa đơn hiện tại
            HoaDonBUS hdBUS = new HoaDonBUS();
            KhachHangBUS khBUS = new KhachHangBUS();
            NhanVienBUS nvBUS = new NhanVienBUS();
            KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
            MonAnBUS maBUS = new MonAnBUS();
            ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();

            HoaDonDTO hd = hdBUS.getHoaDonDTO(mahd);
            //Khoảng trống giữa hàng
            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Hóa đơn: " + String.valueOf(hd.getIDHoaDon()), fontData));

            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add(String.valueOf("Khách hàng: " + khBUS.getKhachHangDTO(hd.getIDKhachHang()).getTenKhachHang() + "  -  " + hd.getIDKhachHang()));
            para1.add(glue);
            para1.add("Ngày lập: " + String.valueOf(hd.getNgayLap()));

            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            para2.add(String.valueOf("Nhân viên: " + nvBUS.getNhanVienDTO(hd.getIDNhanVien()).getTenNhanVien()+ "  -  " + hd.getIDNhanVien()));
            para2.add(glue);

            Paragraph paraKhuyenMai = new Paragraph();
            paraKhuyenMai.setPaddingTop(30);
            paraKhuyenMai.setFont(fontData);
            paraKhuyenMai.add("Khuyến mãi: " + kmBUS.getKhuyenMaiDTO(hd.getIDKhuyenMai()).getTenChuongTrinh());

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(para2);
            document.add(paraKhuyenMai);
            document.add(Chunk.NEWLINE);//add hàng trống để tạo khoảng cách

            //Tạo table cho các chi tiết của hóa đơn
            PdfPTable pdfTable = new PdfPTable(5);
            double tongKhuyenMai = 0;
            float tongThanhTien = 0;

            //Set headers cho table chi tiết
            pdfTable.addCell(new PdfPCell(new Phrase("Mã món ăn", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên món ăn", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tổng tiền", fontHeader)));

            for (int i = 0; i < 5; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }

            //Truyền thông tin chi tiết vào table
            for (ChiTietHoaDonDTO cthd : cthdBUS.getAllChiTiet(mahd)) {

                String ma = cthd.getIDMonAn();
                String ten = maBUS.getMonAnDTO(cthd.getIDMonAn()).getTenMonAn();
                String gia = PriceFormatter.format(cthd.getDonGia());
                String soluong = String.valueOf(cthd.getSoLuong());
                String thanhtien = PriceFormatter.format(cthd.getDonGia() * cthd.getSoLuong());

                pdfTable.addCell(new PdfPCell(new Phrase(ma, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ten, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(gia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(soluong, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(thanhtien, fontData)));

                tongThanhTien += cthd.getDonGia() * cthd.getSoLuong();
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            tongKhuyenMai = hd.getTongTien() - tongThanhTien;
            Paragraph paraTongThanhTien = new Paragraph(new Phrase("Tổng thành tiền: " + PriceFormatter.format(tongThanhTien), fontData));
            paraTongThanhTien.setIndentationLeft(300);
            document.add(paraTongThanhTien);
            Paragraph paraTongKhuyenMai = new Paragraph(new Phrase("Tổng khuyến mãi: " + PriceFormatter.format1(tongKhuyenMai), fontData));
            paraTongKhuyenMai.setIndentationLeft(300);
            document.add(paraTongKhuyenMai);
            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + PriceFormatter.format1(hd.getTongTien()), fontData));
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);
            document.close();
            
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }
    }
    public void writeHoaDonNhap(String mahdn) throws Exception {
        String url = "";
        try {
            fd.setTitle("In hóa đơn nhập");
            url = getFile();
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            
            setTitle("Thông tin hóa đơn nhập");
            //Hien thong tin cua hoa don hien tai
            HoaDonNhapBUS hdnBUS = new HoaDonNhapBUS();
            NhaCungCapBUS nccBUS = new NhaCungCapBUS();
            NhanVienBUS nvBUS = new NhanVienBUS();
            NguyenLieuBUS nlBUS = new NguyenLieuBUS();
            ChiTietHoaDonNhapBUS cthdnBUS = new ChiTietHoaDonNhapBUS();

            HoaDonNhapDTO hdn = hdnBUS.getHoaDonNhapDTO(mahdn);

            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDonNhap = new Paragraph(new Phrase("Hóa đơn nhập: " + String.valueOf(hdn.getIDHoaDonNhap()), fontData));

            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add(String.valueOf("Nhà cung cấp: " + nccBUS.getNhaCungCapDTO(hdn.getIDNhaCungCap()).getTenNhaCungCap() + "  -  " + hdn.getIDNhaCungCap()));
            para1.add(glue);
            para1.add("Ngày lập: " + String.valueOf(hdn.getNgayNhap()));

            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            para2.add(String.valueOf("Nhân viên: " + nvBUS.getNhanVienDTO(hdn.getIDNhanVien()).getTenNhanVien()+ "  -  " + hdn.getIDNhanVien()));
            para2.add(glue);
//            para2.add("Giờ lập: " + String.valueOf(hd.getGioLap()));


            document.add(paraMaHoaDonNhap);
            document.add(para1);
            document.add(para2);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(5);
            float tongThanhTien = 0;

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã nguyên liệu", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên nguyên liệu", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Giá nhập", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tổng tiền", fontHeader)));

            for (int i = 0; i < 5; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }

            //Truyen thong tin tung chi tiet vao table
            for (ChiTietHoaDonNhapDTO cthdn : cthdnBUS.getAllChiTiet(mahdn)) {

                String ma = cthdn.getIDNguyenLieu();
                String ten = nlBUS.getNguyenLieuDTO(cthdn.getIDNguyenLieu()).getTenNguyenLieu();
                String gia = PriceFormatter.format(cthdn.getGiaNhap());
                String soluong = String.valueOf(cthdn.getSoLuong());
                String thanhtien = PriceFormatter.format(cthdn.getGiaNhap() * cthdn.getSoLuong());

                pdfTable.addCell(new PdfPCell(new Phrase(ma, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ten, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(gia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(soluong, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(thanhtien, fontData)));

                tongThanhTien += cthdn.getGiaNhap() * cthdn.getSoLuong();
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

           
            Paragraph paraTongThanhTien = new Paragraph(new Phrase("Tổng thành tiền: " + PriceFormatter.format(tongThanhTien), fontData));
            paraTongThanhTien.setIndentationLeft(300);
            document.add(paraTongThanhTien);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + PriceFormatter.format1(hdn.getTongTien()), fontData));
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);
            document.close();
            
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }
    }
    
    
    
    public void closeFile() {
        document.close();
    } 
}












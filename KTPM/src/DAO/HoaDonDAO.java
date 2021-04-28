//main
package DAO;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class HoaDonDAO {

    ConnectDB connection = new ConnectDB();

    public HoaDonDAO() {

    }

    public ArrayList docHD() throws SQLException, Exception { //cần ghi lại khi qua class khác
        connection = new ConnectDB();
        ArrayList<HoaDonDTO> HD = new ArrayList<>();
        try {
            String qry = "SELECT * FROM hoadon";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setIDHoaDon(result.getString("IDHoaDon"));
                    hd.setIDNhanVien(result.getString("IDNhanVien"));
                    hd.setIDKhachHang(result.getString("IDKhachHang"));
                    hd.setIDKhuyenMai(result.getString("IDKhuyenMai"));
                    hd.setNgayLap(result.getDate("NgayLap").toLocalDate());
                    hd.setTienGiamGia(result.getFloat("TienGiamGia"));
                    hd.setTongTien(result.getFloat("TongTien"));
                    hd.setTrangThai(result.getString("TrangThai"));
                    HD.add(hd);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng hóa đơn");
        }
        return HD;
    }

    public void them(HoaDonDTO HD) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO hoadon values (";
            qry = qry + "'" + HD.getIDHoaDon()+ "'";
            qry = qry + "," + "'" + HD.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + HD.getIDKhachHang()+ "'";
            qry = qry + "," + "'" + HD.getIDKhuyenMai()+ "'";
            qry = qry + "," + "'" + HD.getNgayLap()+ "'";
            qry = qry + "," + "'" + HD.getTienGiamGia()+ "'";
            qry = qry + "," + "'" + HD.getTongTien()+ "'";
            qry = qry + "," + "'" + HD.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }
    
    public void xoa(String IDHoaDon) { 
        try {
            String qry = "Update hoadon Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where IDHoaDon='" + IDHoaDon+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }

    public void xoa(HoaDonDTO HD) {
        try {
            String qry = "Update hoadon Set ";
            qry = qry + "TrangThai=" + "'" + HD.getTrangThai() + "'";
            qry = qry + " " + "where IDHoaDon='" + HD.getIDHoaDon()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }

    public void sua(HoaDonDTO HD) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update hoadon Set ";
            qry = qry + "IDNhanVien=" + "'" + HD.getIDNhanVien()+ "'";
            qry = qry + ",IDKhachHang=" + "'" + HD.getIDKhachHang()+ "'";
            qry = qry + ",IDKhuyenMai=" + "'" + HD.getIDKhuyenMai()+ "'";
            qry = qry + ",NgayLap=" + "'" + HD.getNgayLap()+ "'";
            qry = qry + ",TienGiamGia=" + "'" + HD.getTienGiamGia()+ "'";
            qry = qry + ",TongTien=" + "'" + HD.getTongTien()+ "'";
            qry = qry+" "+" WHERE IDHoaDon=' "+HD.getIDHoaDon()+"'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }

}















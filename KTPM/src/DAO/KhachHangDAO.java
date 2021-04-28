
package DAO;


import DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class KhachHangDAO {
    
    ConnectDB connection= new ConnectDB();
    
    public KhachHangDAO()
    {
        
    }
    
    public ArrayList docDSKH() throws SQLException, Exception {       
        ArrayList<KhachHangDTO> dskh = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khachhang";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    KhachHangDTO  kh = new KhachHangDTO();
                    kh.setIDKhachHang(result.getString("IDKhachHang"));
                    kh.setHoKhachHang(result.getString("HoKhachHang"));
                    kh.setTenKhachHang(result.getString("TenKhachHang"));
                    kh.setGmail(result.getString("Gmail"));
                    kh.setGioiTinh(result.getString("GioiTinh"));
                    kh.setSoDienThoai(result.getString("SoDienThoai"));
                    kh.setTongChiTieu(result.getFloat("TongChiTieu"));
                    kh.setTrangThai(result.getString("TrangThai"));
                    dskh.add(kh);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng khách hàng");
        } finally {
            connection.closeConnect();
        }
        return dskh;   
    }
    
public void them(KhachHangDTO kh ) throws SQLException {
        try {
            String qry = "INSERT INTO khachhang values (";
            qry = qry + "'" + kh.getIDKhachHang() + "'";
            qry = qry + "," + "'" + kh.getHoKhachHang() + "'";
            qry = qry + "," + "'" + kh.getTenKhachHang() + "'";
            qry = qry + "," + "'" + kh.getGmail() + "'";
            qry = qry + "," + "'" + kh.getGioiTinh() + "'";
            qry = qry + "," + "'" + kh.getSoDienThoai() + "'";
            qry = qry + "," + "'" + kh.getTongChiTieu() + "'";
            qry = qry + "," + "'" + kh.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }        
    }

    public void xoa(String makh) {
        try {
            String qry = "Update khachhang Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where IDKhachHang='" + makh + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }
    public void xoa(KhachHangDTO kh) {
        try {
            String qry = "Update khachhang Set ";
            qry = qry + "TrangThai=" + "'" + kh.getTrangThai() + "'";
            qry = qry + " " + "where IDKhachHang='" + kh.getIDKhachHang() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }
    public void sua(KhachHangDTO kh) throws SQLException
    {
        try {
            String qry = "Update khachhang Set ";
            qry = qry + "HoKhachHang=" + "'" + kh.getHoKhachHang() + "'";
            qry = qry + ",TenKhachHang=" + "'" + kh.getTenKhachHang() + "'";
            qry = qry + ",Gmail=" + "'" + kh.getGmail() + "'";
            qry = qry + ",GioiTinh=" + "'" + kh.getGioiTinh() + "'";
            qry = qry + ",SoDienThoai=" + "'" + kh.getSoDienThoai() + "'";
            qry = qry + ",TongChiTieu=" + "'" + kh.getTongChiTieu() + "'";
            qry = qry + " " + "where IDKhachHang='" + kh.getIDKhachHang() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }
 
}
































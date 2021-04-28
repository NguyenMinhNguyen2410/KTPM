//main
package DAO;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MonAnDAO {

    ConnectDB connection = new ConnectDB();

    public MonAnDAO() {

    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<MonAnDTO> dsma = new ArrayList<>();
        try {
            String qry = "SELECT * FROM monan";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    MonAnDTO monan = new MonAnDTO();
                    monan.setIDMonAn(result.getString("IDMonAn"));
                    monan.setTenMonAn(result.getString("TenMonAn"));
                    monan.setDonViTinh(result.getString("DonViTinh"));
                    monan.setDonGia(result.getInt("DonGia"));
                    monan.setHinhAnh(result.getString("HinhAnh"));
                    monan.setLoai(result.getString("Loai"));
                    monan.setSoLuong(result.getInt("SoLuong"));
                    monan.setTrangThai(result.getString("TrangThai"));
                    dsma.add(monan);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng món ăn");
        }
        return dsma;
    }

    public void them(MonAnDTO monan) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO monan values (";
            qry = qry + "'" + monan.getIDMonAn() + "'";
            qry = qry + "," + "'" + monan.getTenMonAn() + "'";
            qry = qry + "," + "'" + monan.getDonViTinh() + "'";
            qry = qry + "," + "'" + monan.getDonGia() + "'";
            qry = qry + "," + "'" + monan.getHinhAnh() + "'";
            qry = qry + "," + "'" + monan.getLoai() + "'";
            qry = qry + "," + "'" + monan.getSoLuong() + "'";
            qry = qry + "," + "'" + monan.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "Update monan Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where IDMonAn='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }

    public void xoa(MonAnDTO monan) {
        try {
            String qry = "Update monan Set ";
            qry = qry + "TrangThai=" + "'" + monan.getTrangThai() + "'";
            qry = qry + " " + "where IDMonAn='" + monan.getIDMonAn() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            connection.closeConnect();
        } catch (Exception e) {
            
        }
    }

    public void sua(MonAnDTO monan) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update monan Set ";
            qry = qry + "TenMonAn=" + "'" + monan.getTenMonAn() + "'";
            qry = qry + ",DonViTinh=" + "'" + monan.getDonViTinh() + "'";
            qry = qry + ",DonGia=" + "'" + monan.getDonGia() + "'";
            qry = qry + ",HinhAnh=" + "'" + monan.getHinhAnh() + "'";
            qry = qry + ",Loai=" + "'" + monan.getLoai() + "'";
            qry = qry + ",SoLuong=" + "'" + monan.getSoLuong() + "'";
            qry = qry + " " + "where IDMonAn='" + monan.getIDMonAn() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }

}















/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTODonBan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAODonBan {
    ConnectDB connection = new ConnectDB();

    public DAODonBan() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTODonBan> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM donban";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTODonBan DTO=new DTODonBan();
                    DTO.setIDDonBan(result.getInt("IDDonBan"));
                    DTO.setIDKhachHang(result.getInt("IDKhachHang"));
                    DTO.setIDNhanVien(result.getInt("IDNhanVien"));
                    DTO.setIDKhuyenMai(result.getInt("IDKhuyenMai"));
                    DTO.setNgayBan(result.getDate("NgayBan").toLocalDate());
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng donban DAO");
        }
        return ds;
    }

    public void them(DTODonBan DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO donban values (";
            qry = qry + "'" + DTO.getIDDonBan()+ "'";
            qry = qry + "," + "'" + DTO.getIDKhachHang()+ "'";
            qry = qry + "," + "'" + DTO.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + DTO.getIDKhuyenMai()+ "'";
            qry = qry + "," + "'" + DTO.getNgayBan()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng donban DAO");
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM donban";
            qry = qry + " where IDDonBan='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng donban DAO");
        }
    }
}

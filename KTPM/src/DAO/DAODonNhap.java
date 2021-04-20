/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTODonNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAODonNhap {
    ConnectDB connection = new ConnectDB();

    public DAODonNhap() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTODonNhap> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM donnhap";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTODonNhap DTO=new DTODonNhap();
                    DTO.setIDDonNhap(result.getInt("IDDonNhap"));
                    DTO.setIDNhanVien(result.getInt("IDNhanVien"));
                    DTO.setNgayNhap(result.getDate("NgayNhap").toLocalDate());
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng donnhap DAO");
        }
        return ds;
    }

    public void them(DTODonNhap DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO donnhap values (";
            qry = qry + "'" + DTO.getIDDonNhap()+ "'";
            qry = qry + "," + "'" + DTO.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + DTO.getNgayNhap()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng donnhap DAO");
        }
    }

    public void xoa(int ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM donnhap";
            qry = qry + " where IDDonNhap='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng donnhap DAO");
        }
    }

    public void sua(DTODonNhap DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update donnhap Set ";
            qry = qry + "IDNhanVien=" + "'" + DTO.getIDNhanVien()+ "'";
            qry = qry + ",NgayNhap=" + "'" + DTO.getNgayNhap()+ "'";
            qry = qry + " " + "where IDDonNhap='" + DTO.getIDDonNhap()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng donnhap DAO");
        }
    }
}

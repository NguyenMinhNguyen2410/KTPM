/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTONhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAONhanVien {
    ConnectDB connection = new ConnectDB();

    public DAONhanVien() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTONhanVien> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM nhanvien";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTONhanVien DTO=new DTONhanVien();
                    DTO.setIDNhanVien(result.getInt("IDNhanVien"));
                    DTO.setHoTen(result.getString("HoTen"));
                    DTO.setNgaySinh(result.getDate("NgaySinh").toLocalDate());
                    DTO.setSDT(result.getString("SDT"));
                    DTO.setCMND(result.getString("CMND"));
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng nhanvien DAO");
        }
        return ds;
    }

    public void them(DTONhanVien DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO nhanvien values (";
            qry = qry + "'" + DTO.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + DTO.getHoTen()+ "'";
            qry = qry + "," + "'" + DTO.getNgaySinh()+ "'";
            qry = qry + "," + "'" + DTO.getSDT()+ "'";
            qry = qry + "," + "'" + DTO.getCMND()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng nhanvien DAO");
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM nhanvien";
            qry = qry + " where IDNhanVien='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng nhanvien DAO");
        }
    }

    public void sua(DTONhanVien DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update nhanvien Set ";
            qry = qry + "HoTen=" + "'" + DTO.getHoTen()+ "'";
            qry = qry + ",NgaySinh=" + "'" + DTO.getNgaySinh()+ "'";
            qry = qry + ",SDT=" + "'" + DTO.getSDT()+ "'";
            qry = qry + ",CMND=" + "'" + DTO.getCMND()+ "'";
            qry = qry + " " + "where IDNhanVien='" + DTO.getIDNhanVien()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng nhanvien DAO");
        }
    }
}

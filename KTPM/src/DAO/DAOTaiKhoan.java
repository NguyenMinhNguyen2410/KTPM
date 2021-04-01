/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DTO.DTOTaiKhoan;
/**
 *
 * @author nguye
 */
public class DAOTaiKhoan {
    ConnectDB connection = new ConnectDB();

    public DAOTaiKhoan() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTOTaiKhoan> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM taikhoan";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTOTaiKhoan DTO=new DTOTaiKhoan();
                    DTO.setIDNhanVien(result.getInt("IDNhanVien"));
                    DTO.setIDPhanQuyen(result.getInt("IDPhanQuyen"));
                    DTO.setMatKhau(result.getString("MatKhau"));
                    DTO.setTaiKhoan(result.getString("TaiKhoan"));
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng taikhoan DAO");
        }
        return ds;
    }

    public void them(DTOTaiKhoan DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO taikhoan values (";
            qry = qry + "'" + DTO.getTaiKhoan()+ "'";
            qry = qry + "," + "'" + DTO.getMatKhau()+ "'";
            qry = qry + "," + "'" + DTO.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + DTO.getIDPhanQuyen()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng taikhoan DAO");
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM taikhoan";
            qry = qry + " where TaiKhoan='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng taikhoan DAO");
        }
    }

    public void sua(DTOTaiKhoan DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update taikhoan Set ";
            qry = qry + "MatKhau=" + "'" + DTO.getMatKhau() + "'";
            qry = qry + ",IDNhanVien=" + "'" + DTO.getIDNhanVien()+ "'";
            qry = qry + ",IDPhanQuyen=" + "'" + DTO.getIDPhanQuyen()+ "'";
            qry = qry + " " + "where TaiKhoan='" + DTO.getTaiKhoan()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng taikhoan DAO");
        }
    }
}

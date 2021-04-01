/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTOKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAOKhachHang {
    ConnectDB connection = new ConnectDB();

    public DAOKhachHang() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTOKhachHang> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khachhang";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTOKhachHang DTO=new DTOKhachHang();
                    DTO.setIDKhachHang(result.getInt("IDKhachHang"));
                    DTO.setHoTen(result.getString("HoTen"));
                    DTO.setNgaySinh(result.getDate("NgaySinh").toLocalDate());
                    DTO.setSDT(result.getString("SDT"));
                    DTO.setCMND(result.getString("CMND"));
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng khachhang DAO");
        }
        return ds;
    }

    public void them(DTOKhachHang DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO khachhang values (";
            qry = qry + "'" + DTO.getIDKhachHang()+ "'";
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
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng khachhang DAO");
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM khachhang";
            qry = qry + " where IDKhachHang='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng khachhang DAO");
        }
    }

    public void sua(DTOKhachHang DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update khachhang Set ";
            qry = qry + "HoTen=" + "'" + DTO.getHoTen()+ "'";
            qry = qry + ",NgaySinh=" + "'" + DTO.getNgaySinh()+ "'";
            qry = qry + ",SDT=" + "'" + DTO.getSDT()+ "'";
            qry = qry + ",CMND=" + "'" + DTO.getCMND()+ "'";
            qry = qry + " " + "where IDKhachHang='" + DTO.getIDKhachHang()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng khachhang DAO");
        }
    }
}

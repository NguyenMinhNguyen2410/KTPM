/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTOPhanQuyen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAOPhanQuyen {
    ConnectDB connection = new ConnectDB();

    public DAOPhanQuyen() {
    }

    

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTOPhanQuyen> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM phanquyen";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTOPhanQuyen DTO=new DTOPhanQuyen();
                    DTO.setIDPhanQuyen(result.getInt("IDPhanQuyen"));
                    DTO.setTenQuyen(result.getString("TenQuyen"));
                    DTO.setMoTaQuyen(result.getString("MoTaQuyen"));
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng phanquyen DAO");
        }
        return ds;
    }

    public void them(DTOPhanQuyen DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO phanquyen values (";
            qry = qry + "'" + DTO.getIDPhanQuyen()+ "'";
            qry = qry + "," + "'" + DTO.getTenQuyen()+ "'";
            qry = qry + "," + "'" + DTO.getMoTaQuyen()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng phanquyen DAO");
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM phanquyen";
            qry = qry + " where IDPhanQuyen='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng phanquyen DAO");
        }
    }

    public void sua(DTOPhanQuyen DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update phanquyen Set ";
            qry = qry + "TenQuyen=" + "'" + DTO.getTenQuyen()+ "'";
            qry = qry + ",MoTaQuyen=" + "'" + DTO.getMoTaQuyen()+ "'";
            qry = qry + " " + "where IDPhanQuyen='" + DTO.getIDPhanQuyen()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng phanquyen DAO");
        }
    }
}

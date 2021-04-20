/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTOChiTietDonNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAOChiTietDonNhap {
    ConnectDB connection = new ConnectDB();

    public DAOChiTietDonNhap() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTOChiTietDonNhap> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM chitietdonnhap";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTOChiTietDonNhap DTO=new DTOChiTietDonNhap();
                    DTO.setIDDonNhap(result.getInt("IDDonNhap"));
                    DTO.setIDKhung(result.getString("IDKhung"));
                    DTO.setIDSanPham(result.getInt("IDSanPham"));
                    DTO.setDonGia(result.getFloat("DonGia"));
                    DTO.setMau(result.getString("Mau"));
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng chitietdonnhap DAO");
        }
        return ds;
    }

    public void them(DTOChiTietDonNhap DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO chitietdonnhap values (";
            qry = qry + "'" + DTO.getIDDonNhap()+ "'";
            qry = qry + "," + "'" + DTO.getIDKhung()+ "'";
            qry = qry + "," + "'" + DTO.getIDSanPham()+ "'";
            qry = qry + "," + "'" + DTO.getDonGia()+ "'";
            qry = qry + "," + "'" + DTO.getMau()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng chitietdonnhap DAO");
        }
    }

    public void xoa(int ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM chitietdonnhap";
            qry = qry + " where IDDonNhap='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng chitietdonnhap DAO");
        }
    }

    
}

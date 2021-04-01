/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTOKhung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAOKhung {
    ConnectDB connection = new ConnectDB();

    public DAOKhung() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTOKhung> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khung";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTOKhung DTO=new DTOKhung();
                    DTO.setIDKhung(result.getString("IDKhung"));
                    DTO.setIDSanPham(result.getInt("IDSanPham"));
                    DTO.setDonGia(result.getInt("DonGia"));
                    DTO.setMau(result.getString("Mau"));
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng khung DAO");
        }
        return ds;
    }

    public void them(DTOKhung DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO khung values (";
            qry = qry + "'" + DTO.getIDKhung()+ "'";
            qry = qry + "," + "'" + DTO.getIDSanPham()+ "'";
            qry = qry + "," + "'" + DTO.getDonGia()+ "'";
            qry = qry + "," + "'" + DTO.getMau()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng khung DAO");
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM khung";
            qry = qry + " where IDKhung='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng khung DAO");
        }
    }

    public void sua(DTOKhung DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update khung Set ";
            qry = qry + "IDSanPham=" + "'" + DTO.getIDSanPham()+ "'";
            qry = qry + ",DonGia=" + "'" + DTO.getDonGia()+ "'";
            qry = qry + ",Mau=" + "'" + DTO.getMau()+ "'";
            qry = qry + " " + "where IDKhung='" + DTO.getIDKhung()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng khung DAO");
        }
    }
}

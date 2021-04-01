/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTOChiTietDonBan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAOChiTietDonBan {
    ConnectDB connection = new ConnectDB();

    public DAOChiTietDonBan() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTOChiTietDonBan> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM chitietdonban";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTOChiTietDonBan DTO=new DTOChiTietDonBan();
                    DTO.setIDDonBan(result.getInt("IDDonBan"));
                    DTO.setIDKhung(result.getString("IDKhung"));
                    DTO.setDonGia(result.getFloat("DonGia"));
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng chitietdonban DAO");
        }
        return ds;
    }

    public void them(DTOChiTietDonBan DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO chitietdonban values (";
            qry = qry + "'" + DTO.getIDDonBan()+ "'";
            qry = qry + "," + "'" + DTO.getIDKhung()+ "'";
            qry = qry + "," + "'" + DTO.getDonGia()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng chitietdonban DAO");
        }
    }

    public void xoa(String ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM chitietdonban";
            qry = qry + " where IDDonBan='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng chitietdonban DAO");
        }
    }
}

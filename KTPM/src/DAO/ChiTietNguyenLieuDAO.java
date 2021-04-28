/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietNguyenLieuDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Phat
 */
public class ChiTietNguyenLieuDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docCTNL() throws Exception {
        ArrayList<ChiTietNguyenLieuDTO> CTNL = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM chitietnguyenlieu";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    ChiTietNguyenLieuDTO  ctnl = new ChiTietNguyenLieuDTO();
                    ctnl.setIDCongThuc(rs.getString("IDCongThuc"));
                    ctnl.setIDNguyenLieu(rs.getString("IDNguyenLieu"));
                    ctnl.setSoLuong(rs.getInt("SoLuong"));
                    CTNL.add(ctnl);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng chi tiết nguyên liệu");
        } 
        return CTNL;   
    }
}











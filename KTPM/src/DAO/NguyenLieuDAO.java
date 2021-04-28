/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NguyenLieuDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Phat
 */
public class NguyenLieuDAO {
    ConnectDB connection = new ConnectDB();

    public NguyenLieuDAO() {

    }

    public ArrayList docDSNL() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<NguyenLieuDTO> dsnl = new ArrayList<>();
        try {
            String qry = "SELECT * FROM nguyenlieu";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    NguyenLieuDTO nl = new NguyenLieuDTO();
                    nl.setIDNguyenLieu(result.getString("IDNguyenLieu"));
                    nl.setTenNguyenLieu(result.getString("TenNguyenLieu"));
                    nl.setDonGia(result.getInt("DonGia"));
                    nl.setHinhAnh(result.getString("HinhAnh"));
                    nl.setLoai(result.getString("Loai"));
                    nl.setDonViTinh(result.getString("DonViTinh"));
                    nl.setSoLuong(result.getInt("SoLuong"));
                    nl.setTrangThai(result.getString("TrangThai"));
                    dsnl.add(nl);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng nguyên liệu");
        }
        return dsnl;
    }

    public void them(NguyenLieuDTO nl) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO nguyenlieu values (";
            qry = qry + "'" + nl.getIDNguyenLieu() + "'";
            qry = qry + "," + "'" + nl.getTenNguyenLieu() + "'";         
            qry = qry + "," + "'" + nl.getDonGia() + "'";
            qry = qry + "," + "'" + nl.getHinhAnh() + "'";
            qry = qry + "," + "'" + nl.getLoai() + "'";
            qry = qry + "," + "'" + nl.getDonViTinh() + "'";
            qry = qry + "," + "'" + nl.getSoLuong() + "'";
            qry = qry + "," + "'" + nl.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }

    public void xoa(String maNguyenLieu) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update nguyenlieu Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where IDNguyenLieu='" + maNguyenLieu + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }

    public void xoa(NguyenLieuDTO nl) {
        try {
            String qry = "Update nguyenlieu Set ";
            qry = qry + "TrangThai=" + "'" + nl.getTrangThai() + "'";
            qry = qry + " " + "where IDNguyenLieu='" + nl.getIDNguyenLieu() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sua(NguyenLieuDTO nl) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update nguyenlieu Set ";
            qry = qry + "TenNguyenLieu=" + "'" + nl.getTenNguyenLieu() + "'";
            qry = qry + ",DonGia=" + "'" + nl.getDonGia() + "'";
            qry = qry + ",HinhAnh=" + "'" + nl.getHinhAnh() + "'";
            qry = qry + ",Loai=" + "'" + nl.getLoai() + "'";
            qry = qry + ",DonViTinh=" + "'" + nl.getDonViTinh() + "'";
            qry = qry + ",SoLuong=" + "'" + nl.getSoLuong() + "'";
            qry = qry + " " + "where IDNguyenLieu='" + nl.getIDNguyenLieu() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }
}














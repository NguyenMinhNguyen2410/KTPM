/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhuyenMaiDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Phat
 */
public class KhuyenMaiDAO {
    ConnectDB connection = new ConnectDB();

    public KhuyenMaiDAO() {

    }

    public ArrayList docDSKM() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<KhuyenMaiDTO> dskm = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khuyenmai";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    KhuyenMaiDTO km = new KhuyenMaiDTO();
                    km.setIDKhuyenMai(result.getString("IDKhuyenMai"));
                    km.setTenChuongTrinh(result.getString("TenChuongTrinh"));
                    km.setTienGiam(result.getInt("TienGiam"));
                    km.setNgayBatDau(result.getDate("NgayBatDau").toLocalDate());
                    km.setNgayKetThuc(result.getDate("NgayKetThuc").toLocalDate());
                    km.setNoiDungGiamGia(result.getString("NoiDungGiamGia"));                    
                    km.setTrangThai(result.getString("TrangThai"));
                    dskm.add(km);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng khuyến mãi");
        }
        return dskm;
    }

    public void them(KhuyenMaiDTO km) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO khuyenmai values (";
            qry = qry + "'" + km.getIDKhuyenMai() + "'";
            qry = qry + "," + "'" + km.getTenChuongTrinh() + "'";
            qry = qry + "," + "'" + km.getTienGiam() + "'";
            qry = qry + "," + "'" + km.getNgayBatDau() + "'";
            qry = qry + "," + "'" + km.getNgayKetThuc() + "'";
            qry = qry + "," + "'" + km.getNoiDungGiamGia() + "'";
            qry = qry + "," + "'" + km.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }

    public void xoa(String maKhuyenMai) {
        try {
            String qry = "Update khuyenmai Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where IDKhuyenMai='" + maKhuyenMai + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }

    public void xoa(KhuyenMaiDTO km) {
        try {
            String qry = "Update khuyenmai Set ";
            qry = qry + "TrangThai=" + "'" + km.getTrangThai() + "'";
            qry = qry + " " + "where IDKhuyenMai='" + km.getIDKhuyenMai() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }

    public void sua(KhuyenMaiDTO km) { 
        try {
            String qry = "Update khuyenmai Set ";
            qry = qry + "TenChuongTrinh=" + "'" + km.getTenChuongTrinh() + "'";
            qry = qry + ",TienGiam=" + "'" + km.getTienGiam() + "'";
            qry = qry + ",NgayBatDau=" + "'" + km.getNgayBatDau() + "'";
            qry = qry + ",NgayKetThuc=" + "'" + km.getNgayKetThuc() + "'";
            qry = qry + ",NoiDungGiamGia=" + "'" + km.getNoiDungGiamGia() + "'";
            qry = qry + " " + "where IDKhuyenMai='" + km.getIDKhuyenMai() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }

}



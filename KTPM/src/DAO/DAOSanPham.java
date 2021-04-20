/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTOSanPham;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author nguye
 */
public class DAOSanPham {
    ConnectDB connection = new ConnectDB();

    public DAOSanPham() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTOSanPham> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM sanpham";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTOSanPham DTO=new DTOSanPham();
                    DTO.setIDSanPham(result.getInt("IDSanPham"));
                    DTO.setTenSanPham(result.getString("TenSanPham"));
                    DTO.setSoLuong(result.getInt("SoLuong"));
                    DTO.setDong(result.getString("Dong"));
                    DTO.setChiTiet(result.getString("ChiTiet"));
                    DTO.setHinhAnh(result.getString("HinhAnh"));
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng sanpham DAO");
        }
        return ds;
    }

    public void them(DTOSanPham DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO sanpham values (";
            qry = qry + "'" + DTO.getIDSanPham()+ "'";
            qry = qry + "," + "'" + DTO.getTenSanPham()+ "'";
            qry = qry + "," + "'" + DTO.getSoLuong()+ "'";
            qry = qry + "," + "'" + DTO.getDong()+ "'";
            qry = qry + "," + "'" + DTO.getChiTiet()+ "'";
            qry = qry + "," + "'" + DTO.getHinhAnh()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng sanpham DAO");
        }
    }

    public void xoa(int ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM sanpham";
            qry = qry + " where IDSanPham='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng sanpham DAO");
        }
    }

    public void sua(DTOSanPham DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update sanpham Set ";
            qry = qry + "TenSanPham=" + "'" + DTO.getTenSanPham()+ "'";
            qry = qry + ",SoLuong=" + "'" + DTO.getSoLuong()+ "'";
            qry = qry + ",Dong=" + "'" + DTO.getDong()+ "'";
            qry = qry + ",ChiTiet=" + "'" + DTO.getChiTiet()+ "'";
            qry = qry + ",HinhAnh=" + "'" + DTO.getHinhAnh()+ "'";
            qry = qry + " " + "where IDSanPham='" + DTO.getIDSanPham()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng sanpham DAO");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTOKhuyenMai;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class DAOKhuyenMai {
    ConnectDB connection = new ConnectDB();

    public DAOKhuyenMai() {
    }

    public ArrayList docDB() throws SQLException, Exception { //cần ghi lại khi qua class khác
        
        ArrayList<DTOKhuyenMai> ds = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khuyenmai";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    DTOKhuyenMai DTO=new DTOKhuyenMai();
                    DTO.setIDKhuyenMai(result.getInt("IDKhuyenMai"));
                    DTO.setTenChuongTrinh(result.getString("TenChuongTrinh"));
                    DTO.setChietKhau(result.getInt("ChietKhau"));
                    DTO.setMoTaChuongTrinh(result.getString("MoTaChuongTrinh"));
                    DTO.setNgayBatDau(result.getDate("NgayBatDau").toLocalDate());
                    DTO.setNgayKetThuc(result.getDate("NgayKetThuc").toLocalDate());
                    ds.add(DTO);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng khuyenmai DAO");
        }
        return ds;
    }

    public void them(DTOKhuyenMai DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO khuyenmai values (";
            qry = qry + "'" + DTO.getIDKhuyenMai()+ "'";
            qry = qry + "," + "'" + DTO.getTenChuongTrinh()+ "'";
            qry = qry + "," + "'" + DTO.getChietKhau()+ "'";
            qry = qry + "," + "'" + DTO.getMoTaChuongTrinh()+ "'";
            qry = qry + "," + "'" + DTO.getNgayBatDau()+ "'";
            qry = qry + "," + "'" + DTO.getNgayKetThuc()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng khuyenmai DAO");
        }
    }

    public void xoa(int ID) { //cần ghi lại khi qua class khác
        
        try {
            String qry = "DELETE FROM khuyenmai";
            qry = qry + " where IDKhuyenMai='" + ID + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng khuyenmai DAO");
        }
    }

    public void sua(DTOKhuyenMai DTO) { //cần ghi lại khi qua class khác
        try {
            String qry = "Update khuyenmai Set ";
            qry = qry + "TenChuongTrinh=" + "'" + DTO.getTenChuongTrinh()+ "'";
            qry = qry + ",ChietKhau=" + "'" + DTO.getChietKhau()+ "'";
            qry = qry + ",MoTaChuongTrinh=" + "'" + DTO.getMoTaChuongTrinh()+ "'";
            qry = qry + ",NgayBatDau=" + "'" + DTO.getNgayBatDau()+ "'";
            qry = qry + ",NgayKetThuc=" + "'" + DTO.getNgayKetThuc()+ "'";
            qry = qry + " " + "where IDKhuyenMai='" + DTO.getIDKhuyenMai()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng khuyenmai DAO");
        }
    }
}

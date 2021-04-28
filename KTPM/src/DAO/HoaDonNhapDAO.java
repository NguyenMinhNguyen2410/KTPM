/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDonNhapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Phat
 */
public class HoaDonNhapDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docPQ() throws Exception {
        ArrayList<HoaDonNhapDTO> HDN = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM hoadonnhap";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    HoaDonNhapDTO  hdn = new HoaDonNhapDTO();
                    hdn.setIDHoaDonNhap(rs.getString("IDHoaDonNhap"));
                    hdn.setIDNhanVien(rs.getString("IDNhanVien"));
                    hdn.setIDNhaCungCap(rs.getString("IDNhaCungCap"));
                    hdn.setTongTien(rs.getFloat("TongTien"));
                    hdn.setNgayNhap(rs.getDate("NgayNhap").toLocalDate());
                    hdn.setTrangThai(rs.getString("TrangThai"));
                    HDN.add(hdn);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng hóa đơn nhập");
        } 
        return HDN;   
    }
    public void them(HoaDonNhapDTO  hdn ) {
        try{
            String qry ="INSERT INTO hoadonnhap values (";
            qry = qry + "'" + hdn.getIDHoaDonNhap()+ "'";
            qry = qry + "," + "'" + hdn.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + hdn.getIDNhaCungCap()+ "'";
            qry = qry + "," + "'" + hdn.getNgayNhap()+ "'";
            qry = qry + "," + "'" + hdn.getTongTien()+ "'";
            qry = qry + "," + "'" + hdn.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);  
            System.out.println(qry);
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
    
    public void sua(HoaDonNhapDTO  hdn){
        try{
             String qry="Update hoadonnhap Set ";
                    qry = qry + "IDNhanVien= '"+hdn.getIDNhanVien()+"'";
                    qry = qry + ",IDNhaCungCap= '"+hdn.getIDNhaCungCap()+"'";
                    qry = qry + ",TongTien= '"+hdn.getTongTien()+"'";
                    qry = qry + ",NgayNhap= '"+hdn.getNgayNhap()+"'";
                    qry = qry + " "+" WHERE IDHoaDonNhap=' "+hdn.getIDHoaDonNhap()+"'";
                    connection.getStatement();
                    connection.ExecuteUpdate(qry);
                    System.out.println(qry);
                    connection.closeConnect();     
        }catch (Exception ex){
            
        }
    }
    
    public void xoa(String  IDHoaDonNhap){
        try{
           String qry = "Update hoadonnhap Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn"+ "'";
            qry = qry + " " + "where IDHoaDonNhap='" + IDHoaDonNhap+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        }catch(Exception ex){
            
        }
    }
    
    public void xoa(HoaDonNhapDTO hdn) {
    try {
            String qry = "Update hoadonnhap Set ";
            qry = qry + "TrangThai=" + "'" + hdn.getTrangThai() + "'";
            qry = qry + " " + "where IDHoaDonNhap='" + hdn.getIDHoaDonNhap()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }

}





















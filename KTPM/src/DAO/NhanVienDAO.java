/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;



import DTO.NhanVienDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NhanVienDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docDSNV() throws Exception {
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM nhanvien";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    NhanVienDTO  nv = new NhanVienDTO();
                    nv.setIDNhanVien(rs.getString("IDNhanVien"));
                    nv.setHoNhanVien(rs.getString("HoNhanVien"));
                    nv.setTenNhanVien(rs.getString("TenNhanVien"));
                    nv.setGmail(rs.getString("Gmail"));
                    nv.setGioiTinh(rs.getString("GioiTinh"));
                    nv.setSoDienThoai(rs.getString("SoDienThoai"));
                    nv.setChucVu(rs.getString("ChucVu"));
                    nv.setTrangThai(rs.getString("TrangThai"));
                    dsnv.add(nv);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng nhân viên");
        } 
        return dsnv;   
    }
    public void them(NhanVienDTO nv ) {
        try{
            String qry ="INSERT INTO nhanvien values (";
            qry = qry+"'"+nv.getIDNhanVien()+"'";
            qry = qry+","+"'"+nv.getHoNhanVien()+"'";
            qry = qry+","+"'"+nv.getTenNhanVien()+"'";
            qry = qry+","+"'"+nv.getGmail()+"'";
            qry = qry+","+"'"+nv.getGioiTinh()+"'";
            qry = qry+","+"'"+nv.getSoDienThoai()+"'";
            qry = qry+","+"'"+nv.getChucVu()+"'";
            qry = qry+","+"'"+nv.getTrangThai()+"'";
            qry = qry+")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
    
    public void sua(NhanVienDTO nv){
        try{
             String qry="Update nhanvien Set ";
                    qry = qry+"HoNhanVien= ' "+nv.getHoNhanVien()+"'";
                    qry = qry+",TenNhanVien= '"+nv.getTenNhanVien()+"'";
                    qry = qry+",Gmail= '"+nv.getGmail()+"'";
                    qry = qry+",GioiTinh= '"+nv.getGioiTinh()+"'";
                    qry = qry+",SoDienThoai= '"+nv.getSoDienThoai()+"'";
                    qry = qry+",ChucVu= '"+nv.getChucVu()+"'";
                    qry = qry+" "+" WHERE IDNhanVien=' "+nv.getIDNhanVien()+"'";
                    connection.getStatement();
                    connection.ExecuteUpdate(qry); 
                    System.out.println(qry);
                    connection.closeConnect();     
        }catch (Exception ex){
            
        }
    }
    
    public void xoa(String  IDNhanVien){
        try{
           String qry = "Update nhanvien Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where IDNhanVien='" + IDNhanVien+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        }catch(Exception ex){
            
        }
    }
    public void xoa(NhanVienDTO nv) {
        try {
            String qry = "Update nhanvien Set ";
            qry = qry + "TrangThai=" + "'" + nv.getTrangThai() + "'";
            qry = qry + " " + "where IDNhanVien='" + nv.getIDNhanVien() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }
}





















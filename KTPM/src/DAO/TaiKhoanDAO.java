/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import Connect.ConnectDB;
import DTO.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TaiKhoanDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docDSTK() throws Exception {
        ArrayList<TaiKhoanDTO> dstk = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM taikhoannhanvien";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    TaiKhoanDTO  tk = new TaiKhoanDTO();
                    tk.setTaiKhoan(rs.getString("TaiKhoan"));
                    tk.setIDNhanVien(rs.getString("IDNhanVien"));
                    tk.setIDPhanQuyen(rs.getString("IDPhanQuyen"));
                    tk.setMatKhau(rs.getString("MatKhau"));
                    tk.setTrangThai(rs.getString("TrangThai"));
                    dstk.add(tk);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng tài khoản nhân viên");
        } 
        return dstk;   
    }
    public void them(TaiKhoanDTO tk ) {
        try{
            String qry ="INSERT INTO taikhoannhanvien values (";
            qry = qry + "'" + tk.getTaiKhoan()+ "'";
            qry = qry + "," + "'" + tk.getIDNhanVien()+ "'";
            qry = qry + "," + "'" + tk.getIDPhanQuyen()+ "'";
            qry = qry + "," + "'" + tk.getMatKhau()+ "'";
            qry = qry + "," + "'" + tk.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);  
            System.out.println(qry);
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
    
    public void sua(TaiKhoanDTO tk){
        try{
             String qry="Update taikhoannhanvien Set ";
                    qry = qry + "IDNhanVien= '"+tk.getIDNhanVien()+"'";
                    qry = qry + ",IDPhanQuyen= '"+tk.getIDPhanQuyen()+"'";
                    qry = qry + ",MatKhau= '"+tk.getMatKhau()+"'";
                    qry = qry + " "+" WHERE TaiKhoan='"+tk.getTaiKhoan()+"'";
                    connection.getStatement();
                    connection.ExecuteUpdate(qry);
                    System.out.println(qry);
                    connection.closeConnect();     
        }catch (Exception ex){
            
        }
    }
    
    public void xoa(String  TaiKhoan){
        try{
           String qry = "Update taikhoannhanvien Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where TaiKhoan='" + TaiKhoan + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        }catch(Exception ex){
            
        }
    }
    public void xoa(TaiKhoanDTO tk) {
        try {
            String qry = "Update taikhoannhanvien Set ";
            qry = qry + "TrangThai=" + "'" + tk.getTrangThai() + "'";
            qry = qry + " " + "where TaiKhoan='" + tk.getTaiKhoan() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }
}




















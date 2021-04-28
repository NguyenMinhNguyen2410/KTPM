/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;



import DTO.NhaCungCapDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NhaCungCapDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docDSNCC() throws Exception {
        ArrayList<NhaCungCapDTO> dsncc = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM nhacungcap";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    NhaCungCapDTO  ncc = new NhaCungCapDTO();
                    ncc.setIDNhaCungCap(rs.getString("IDNhaCungCap"));
                    ncc.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
                    ncc.setGmail(rs.getString("Gmail"));
                    ncc.setSoDienThoai(rs.getString("SoDienThoai"));
                    ncc.setDiaChi(rs.getString("DiaChi"));
                    ncc.setTrangThai(rs.getString("TrangThai"));
                    dsncc.add(ncc);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng nhà cung cấp");
        } 
        return dsncc;   
    }
    public void them(NhaCungCapDTO ncc ) {
        try{
            String qry ="INSERT INTO nhacungcap values(";
            qry = qry+"'"+ncc.getIDNhaCungCap()+"'";
            qry = qry+","+"'"+ncc.getTenNhaCungCap()+"'";
            qry = qry+","+"'"+ncc.getGmail()+"'";
            qry = qry+","+"'"+ncc.getSoDienThoai()+"'";
            qry = qry+","+"'"+ncc.getDiaChi()+"'";
            qry = qry+","+"'"+ncc.getTrangThai()+"'";
            qry = qry+")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
    
    public void sua(NhaCungCapDTO ncc ){
        try{
             String qry="Update nhacungcap Set ";
                    qry = qry + "TenNhaCungCap=" + "'" + ncc.getTenNhaCungCap() + "'";
                    qry = qry + ",SoDienThoai=" + "'" + ncc.getSoDienThoai() + "'";
                    qry = qry + ",Gmail=" + "'" + ncc.getGmail() + "'";
                    qry = qry + ",DiaChi=" + "'" + ncc.getDiaChi() + "'";                    
                    qry = qry + " " + "where IDNhaCungCap='" + ncc.getIDNhaCungCap() + "'";
                    connection.getStatement();
                    connection.ExecuteUpdate(qry);
                    System.out.println(qry);
                    connection.closeConnect();     
        }catch (Exception ex){
            
        }
    }
    
    public void xoa(String  IDNhaCungCap){
        try {
            String qry = "Update nhacungcap Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn" + "'";
            qry = qry + " " + "where IDNhaCungCap='" + IDNhaCungCap + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }
    public void xoa(NhaCungCapDTO ncc) {
        try {
            String qry = "Update nhacungcap Set ";
            qry = qry + "TrangThai=" + "'" + ncc.getTrangThai() + "'";
            qry = qry + " " + "where IDNhaCungCap='" + ncc.getIDNhaCungCap() + "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }
}


























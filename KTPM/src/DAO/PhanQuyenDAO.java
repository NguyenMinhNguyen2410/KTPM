/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhanQuyenDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Phat
 */
public class PhanQuyenDAO {
 
    ConnectDB connection = new ConnectDB();
    public ArrayList docPQ() throws Exception {
        ArrayList<PhanQuyenDTO> PQ = new ArrayList<>() ;
        try {
            String qry = "SELECT * FROM phanquyen";
            ResultSet rs = connection.excuteQuery(qry);  
            while (rs.next()) {
                    PhanQuyenDTO  pq = new PhanQuyenDTO();
                    pq.setIDPhanQuyen(rs.getString("IDPhanQuyen"));
                    pq.setTenQuyen(rs.getString("TenQuyen"));
                    pq.setMoTaQuyen(rs.getString("MoTaQuyen"));
                    pq.setTrangThai(rs.getString("TrangThai"));
                    PQ.add(pq);
                }
            }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng phân quyền");
        } 
        return PQ;   
    }
    public void them(PhanQuyenDTO  pq) {
        try{
            String qry ="INSERT INTO phanquyen values (";
            qry = qry + "'" + pq.getIDPhanQuyen()+ "'";
            qry = qry + "," + "'" + pq.getTenQuyen()+ "'";
            qry = qry + "," + "'" + pq.getMoTaQuyen()+ "'";
            qry = qry + "," + "'" + pq.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

       } catch (Exception ex) {
       }
    }    
    
    public void sua(PhanQuyenDTO  pq){
        try{
             String qry="Update phanquyen Set ";
                    qry = qry + "TenQuyen = ' "+pq.getTenQuyen()+"'";
                    qry = qry + ",MoTaQuyen = '"+pq.getMoTaQuyen()+"'";
                    qry = qry + " "+" WHERE IDPhanQuyen=' "+pq.getIDPhanQuyen()+"'";
                    connection.getStatement();
                    connection.ExecuteUpdate(qry);
                    System.out.println(qry);
                    connection.closeConnect();     
        }catch (Exception ex){
            
        }
    }
    
    public void xoa(String  IDPhanQuyen){
        try{
           String qry = "Update phanquyen Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn"+ "'";
            qry = qry + "where IDPhanQuyen='" + IDPhanQuyen+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        }catch(Exception ex){
            
        }
    }

    public void xoa(PhanQuyenDTO pq) {
    try {
            String qry = "Update phanquyen Set ";
            qry = qry + "TrangThai=" + "'" + pq.getTrangThai() + "'";
            qry = qry + "where IDPhanQuyen='" + pq.getIDPhanQuyen()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }
}













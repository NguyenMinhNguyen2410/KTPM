/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAOChiTietDonBan;
import DTO.DTOChiTietDonBan;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class BUSChiTietDonBan {
    public static ArrayList<DTOChiTietDonBan> ds;
    DAOChiTietDonBan DAO=new DAOChiTietDonBan();

    public BUSChiTietDonBan() {
    }
    public void docDB(){
        try{
            if(ds==null)
            {
                ds=new ArrayList<>();
                ds=DAO.docDB();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng taikhoan BUS");
        }
    }
    public void them(DTOChiTietDonBan DTO) //cần ghi lại khi qua class khác
    {
        try{
            DAO.them(DTO);//ghi vào database
            ds.add(DTO);//cập nhật arraylist
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng taikhoan BUS");
        }
    }

    public void xoa(String ID, int index) //cần ghi lại khi qua class khác
    {
        try{
            DAO.xoa(ID); // Xóa dữ liệu trên database
            if(ds!=null)
                ds.remove(index);
    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng taikhoan BUS");
        }
    }
}

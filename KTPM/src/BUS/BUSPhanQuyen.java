/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAOPhanQuyen;
import DTO.DTOPhanQuyen;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class BUSPhanQuyen {
    public static ArrayList<DTOPhanQuyen> ds;
    DAOPhanQuyen DAO=new DAOPhanQuyen();

    public BUSPhanQuyen() {
    }
    public void docDB(){
        try{
            if(ds==null)
            {
                ds=new ArrayList<>();
                ds=DAO.docDB();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng phanquyen BUS");
        }
    }
    public void them(DTOPhanQuyen DTO) //cần ghi lại khi qua class khác
    {
        try{
            DAO.them(DTO);//ghi vào database
            ds.add(DTO);//cập nhật arraylist
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng phanquyen BUS");
        }
    }

    public void sua(DTOPhanQuyen DTO, int index) //cần ghi lại khi qua class khác
    {
        try{
            DAO.sua(DTO);
            if(ds!=null)
                ds.set(index, DTO);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng phanquyen BUS");
        }
    }

    public void xoa(String ID, int index) //cần ghi lại khi qua class khác
    {
        try{
            DAO.xoa(ID); // Xóa dữ liệu trên database
            if(ds!=null)
                ds.remove(index);
    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng phanquyen BUS");
        }
    }
}

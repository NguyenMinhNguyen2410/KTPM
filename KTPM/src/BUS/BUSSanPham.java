/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAOSanPham;
import DTO.DTOSanPham;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class BUSSanPham {
    public static ArrayList<DTOSanPham> ds;
    DAOSanPham DAO=new DAOSanPham();

    public BUSSanPham() {
    }
    public void docDB(){
        try{
            if(ds==null)
            {
                ds=new ArrayList<>();
                ds=DAO.docDB();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng sanpham BUS");
        }
    }
    public void them(DTOSanPham DTO) //cần ghi lại khi qua class khác
    {
        try{
            DAO.them(DTO);//ghi vào database
            ds.add(DTO);//cập nhật arraylist
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng sanpham BUS");
        }
    }

    public void sua(DTOSanPham DTO, int index) //cần ghi lại khi qua class khác
    {
        try{
            DAO.sua(DTO);
            if(ds!=null)
                ds.set(index, DTO);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng sanpham BUS");
        }
    }

    public void xoa(int ID, int index) //cần ghi lại khi qua class khác
    {
        try{
            DAO.xoa(ID); // Xóa dữ liệu trên database
            if(ds!=null)
                ds.remove(index);
    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng sanpham BUS");
        }
    }
}

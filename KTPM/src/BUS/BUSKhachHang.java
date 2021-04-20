/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAOKhachHang;
import DTO.DTOKhachHang;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class BUSKhachHang {
    public static ArrayList<DTOKhachHang> ds;
    DAOKhachHang DAO=new DAOKhachHang();

    public BUSKhachHang() {
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
    public void them(DTOKhachHang DTO) //cần ghi lại khi qua class khác
    {
        try{
            DAO.them(DTO);//ghi vào database
            ds.add(DTO);//cập nhật arraylist
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng taikhoan BUS");
        }
    }

    public void sua(DTOKhachHang DTO, int index) //cần ghi lại khi qua class khác
    {
        try{
            DAO.sua(DTO);
            if(ds!=null)
                ds.set(index, DTO);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không sửa được dữ liệu bảng taikhoan BUS");
        }
    }

    public void xoa(int ID, int index) //cần ghi lại khi qua class khác
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

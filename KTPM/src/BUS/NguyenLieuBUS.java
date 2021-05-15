/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NguyenLieuDAO;
import java.util.ArrayList;
import DTO.NguyenLieuDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class NguyenLieuBUS {
   public static ArrayList<NguyenLieuDTO> ds;
   public static NguyenLieuDAO DAO=new NguyenLieuDAO();
   public NguyenLieuBUS()
    {
        
    }
    public static void  docDB() throws Exception 
    {
        
        if(ds==null) ds=new ArrayList<NguyenLieuDTO>();
        ds =DAO.docDSNL();
    }
    public void  them(NguyenLieuDTO nl)
    {
        try
        {
            DAO.them(nl);
            if(ds!=null)
            ds.add(nl);
        }
        catch (Exception ex) {
           Logger.getLogger(NguyenLieuBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(NguyenLieuDTO nl,int i)
    {
        try
        {
           DAO.sua(nl);
           if(ds!=null)
           ds.set(i, nl);
        }
        catch (Exception ex) {
           Logger.getLogger(NguyenLieuBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void xoa(String ID, int index) //cần ghi lại khi qua class khác
    {
        DAO.xoa(ID); // update trạng thái lên database
        NguyenLieuDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(ds!=null)
        ds.set(index, DTO);
    }
     public void xoa(NguyenLieuDTO nl,int index)
    {
        String xoanl = ds.get(index).getIDNguyenLieu();
        DAO.xoa(xoanl);
        if(ds!=null)
        ds.set(index, nl);
    }
     
     //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDNguyenLieu().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public NguyenLieuDTO getNguyenLieuDTO(String idnl) {
        for (NguyenLieuDTO nlDTO : ds) {
            if (nlDTO.getIDNguyenLieu().equals(idnl)) {
                return nlDTO;
            }
        }
        return null;
    }
     
    public ArrayList<NguyenLieuDTO> getNguyenLieuDTO() {
    return ds;
    }
    
    public static String getMaMonAnCuoi()
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDNguyenLieu();
         return ma;
        }
         return null;
    }
}











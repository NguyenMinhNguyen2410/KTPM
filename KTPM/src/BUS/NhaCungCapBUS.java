/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class NhaCungCapBUS {
   public static ArrayList<NhaCungCapDTO> ds;
   public static NhaCungCapDAO DAO=new NhaCungCapDAO();
   public NhaCungCapBUS()
    {
        
    }
    public  static void  docDB() throws Exception 
    {
        
        if(ds==null) ds=new ArrayList<NhaCungCapDTO>();
        ds =DAO.docDSNCC();
    }
    public void them(NhaCungCapDTO ncc)
    {
        try
        {
            DAO.them(ncc);
            if(ds!=null)
            ds.add(ncc);
        }
        catch (Exception ex) {
           Logger.getLogger(NhaCungCapBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(NhaCungCapDTO ncc,int i)
    {
        try
        {
           DAO.sua(ncc);
           if(ds!=null)
           ds.set(i, ncc);
        }
        catch (Exception ex) {
           Logger.getLogger(NhaCungCapBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     public void xoa(NhaCungCapDTO ncc,int index)
    {
        String xoancc = ds.get(index).getIDNhaCungCap();
        DAO.xoa(xoancc);
        if(ds!=null)
        ds.set(index,ncc);
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        DAO.xoa(ID); // update trạng thái lên database
        NhaCungCapDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(ds!=null)
        ds.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDNhaCungCap().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public NhaCungCapDTO getNhaCungCapDTO(String idncc) {
        for (NhaCungCapDTO nccDTO : ds) {
            if (nccDTO.getIDNhaCungCap().equals(idncc)) {
                return nccDTO;
            }
        }
        return null;
    }

    public ArrayList<NhaCungCapDTO> getNhaCungCapDTO() {
    return ds;
    }
    
    public static String getMaNhaCungCapCuoi() //lấy mã cuối để tăng
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDNhaCungCap();
         return ma;
        }
         return null;
    }
}







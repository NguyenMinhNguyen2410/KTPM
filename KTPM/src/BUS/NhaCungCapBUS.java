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
   public static ArrayList<NhaCungCapDTO> dsncc;
   public NhaCungCapBUS()
    {
        
    }
    public  void  docDSNCC() throws Exception 
    {
        NhaCungCapDAO nccdata=new NhaCungCapDAO();
        if(dsncc==null) dsncc=new ArrayList<NhaCungCapDTO>();
        dsncc =nccdata.docDSNCC();
    }
    public void them(NhaCungCapDTO ncc)
    {
        try
        {
            NhaCungCapDAO nccdata=new NhaCungCapDAO();
            nccdata.them(ncc);
            if(dsncc!=null)
            dsncc.add(ncc);
        }
        catch (Exception ex) {
           Logger.getLogger(NhaCungCapBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(NhaCungCapDTO ncc,int i)
    {
        try
        {

           NhaCungCapDAO nccdata=new NhaCungCapDAO();
           nccdata.sua(ncc);
           if(dsncc!=null)
           dsncc.set(i, ncc);
        }
        catch (Exception ex) {
           Logger.getLogger(NhaCungCapBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     public void xoa(NhaCungCapDTO ncc,int index)
    {
        NhaCungCapDAO nccDao =new NhaCungCapDAO();
        String xoancc = dsncc.get(index).getIDNhaCungCap();
        nccDao.xoa(xoancc);
        if(dsncc!=null)
        dsncc.set(index,ncc);
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        NhaCungCapDAO data = new NhaCungCapDAO();
        data.xoa(ID); // update trạng thái lên database
        NhaCungCapDTO DTO=dsncc.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(dsncc!=null)
        dsncc.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dsncc.size(); i++) {
            if (dsncc.get(i).getIDNhaCungCap().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public NhaCungCapDTO getNhaCungCapDTO(String idncc) {
        for (NhaCungCapDTO nccDTO : dsncc) {
            if (nccDTO.getIDNhaCungCap().equals(idncc)) {
                return nccDTO;
            }
        }
        return null;
    }

    public ArrayList<NhaCungCapDTO> getNhaCungCapDTO() {
    return dsncc;
    }
    
    public static String getMaNhaCungCapCuoi() //lấy mã cuối để tăng
    {
        if(dsncc == null)
        {
            dsncc = new ArrayList<>();
        }
        if(dsncc.size() > 0)
        {
            String ma;
         ma = dsncc.get(dsncc.size()-1).getIDNhaCungCap();
         return ma;
        }
         return null;
    }
}







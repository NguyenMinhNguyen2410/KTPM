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
   public static ArrayList<NguyenLieuDTO> dsnl;
   public NguyenLieuBUS()
    {
        
    }
    public  void  docDSNL() throws Exception 
    {
        NguyenLieuDAO nldata=new NguyenLieuDAO();
        if(dsnl==null) dsnl=new ArrayList<NguyenLieuDTO>();
        dsnl =nldata.docDSNL();
    }
    public void  them(NguyenLieuDTO nl)
    {
        try
        {
            NguyenLieuDAO nldata=new NguyenLieuDAO();
            nldata.them(nl);
            if(dsnl!=null)
            dsnl.add(nl);
        }
        catch (Exception ex) {
           Logger.getLogger(NguyenLieuBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(NguyenLieuDTO nl,int i)
    {
        try
        {

           NguyenLieuDAO nldata=new NguyenLieuDAO();
           nldata.sua(nl);
           if(dsnl!=null)
           dsnl.set(i, nl);
        }
        catch (Exception ex) {
           Logger.getLogger(NguyenLieuBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void xoa(String ID, int index) //cần ghi lại khi qua class khác
    {
        NguyenLieuDAO data = new NguyenLieuDAO();
        data.xoa(ID); // update trạng thái lên database
        NguyenLieuDTO DTO=dsnl.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(dsnl!=null)
        dsnl.set(index, DTO);
    }
     public void xoa(NguyenLieuDTO nl,int index)
    {
        NguyenLieuDAO nlDao =new NguyenLieuDAO();
        String xoanl = dsnl.get(index).getIDNguyenLieu();
        nlDao.xoa(xoanl);
        if(dsnl!=null)
        dsnl.set(index, nl);
    }
     
     //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dsnl.size(); i++) {
            if (dsnl.get(i).getIDNguyenLieu().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public NguyenLieuDTO getNguyenLieuDTO(String idnl) {
        for (NguyenLieuDTO nlDTO : dsnl) {
            if (nlDTO.getIDNguyenLieu().equals(idnl)) {
                return nlDTO;
            }
        }
        return null;
    }
     
    public ArrayList<NguyenLieuDTO> getNguyenLieuDTO() {
    return dsnl;
    }
    
    public static String getMaMonAnCuoi()
    {
        if(dsnl == null)
        {
            dsnl = new ArrayList<>();
        }
        if(dsnl.size() > 0)
        {
            String ma;
         ma = dsnl.get(dsnl.size()-1).getIDNguyenLieu();
         return ma;
        }
         return null;
    }
}











/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhuyenMaiDAO;
import java.util.ArrayList;
import DTO.KhuyenMaiDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class KhuyenMaiBUS {
   public static ArrayList<KhuyenMaiDTO> ds;
   public static KhuyenMaiDAO kmdata=new KhuyenMaiDAO();
   public KhuyenMaiBUS()
    {
        
    }
    public static void  docDB() throws Exception 
    {
        
        if(ds==null) 
            ds=new ArrayList<>();
        ds =kmdata.docDSKM();
    }
    public void  them(KhuyenMaiDTO km)
    {
        try
        {
            KhuyenMaiDAO kmdata=new KhuyenMaiDAO();
            kmdata.them(km);
            if(ds!=null)
            ds.add(km);
        }
        catch (Exception ex) {
           Logger.getLogger(KhuyenMaiBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(KhuyenMaiDTO km,int i)
    {
        try
        {

           KhuyenMaiDAO kmdata=new KhuyenMaiDAO();
           kmdata.sua(km);
           if(ds!=null)
            ds.set(i, km);
        }
        catch (Exception ex) {
           Logger.getLogger(KhuyenMaiBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     public void xoa(KhuyenMaiDTO km,int index)
    {
        KhuyenMaiDAO kmdata =new KhuyenMaiDAO();
        String xoakm = ds.get(index).getIDKhuyenMai();
        kmdata.xoa(xoakm);
        if(ds!=null)
        ds.set(index,km);
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        KhuyenMaiDAO data = new KhuyenMaiDAO();
        data.xoa(ID); // update trạng thái lên database
        KhuyenMaiDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(ds!=null)
        ds.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDKhuyenMai().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    
    public static String getMaKhuyenMaiCuoi()
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDKhuyenMai();
         return ma;
        }
         return null;
    }
    //
    public ArrayList<KhuyenMaiDTO> getKhuyenMaiDTO(){
        return ds; 
    }
    public KhuyenMaiDTO getKhuyenMaiDTO(String idkhuyenmai){
         for (KhuyenMaiDTO kmDTO: ds){
             if(kmDTO.getIDKhuyenMai().equals(idkhuyenmai))
                 return kmDTO;
         }
         return null;
     }
}










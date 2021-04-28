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
   public static ArrayList<KhuyenMaiDTO> dskm;
   public KhuyenMaiBUS()
    {
        
    }
    public  void  docDSKM() throws Exception 
    {
        KhuyenMaiDAO kmdata=new KhuyenMaiDAO();
        if(dskm==null) 
            dskm=new ArrayList<>();
        dskm =kmdata.docDSKM();
    }
    public void  them(KhuyenMaiDTO km)
    {
        try
        {
            KhuyenMaiDAO kmdata=new KhuyenMaiDAO();
            kmdata.them(km);
            if(dskm!=null)
            dskm.add(km);
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
           if(dskm!=null)
            dskm.set(i, km);
        }
        catch (Exception ex) {
           Logger.getLogger(KhuyenMaiBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     public void xoa(KhuyenMaiDTO km,int index)
    {
        KhuyenMaiDAO kmdata =new KhuyenMaiDAO();
        String xoakm = dskm.get(index).getIDKhuyenMai();
        kmdata.xoa(xoakm);
        if(dskm!=null)
        dskm.set(index,km);
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        KhuyenMaiDAO data = new KhuyenMaiDAO();
        data.xoa(ID); // update trạng thái lên database
        KhuyenMaiDTO DTO=dskm.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(dskm!=null)
        dskm.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dskm.size(); i++) {
            if (dskm.get(i).getIDKhuyenMai().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    
    public static String getMaKhuyenMaiCuoi()
    {
        if(dskm == null)
        {
            dskm = new ArrayList<>();
        }
        if(dskm.size() > 0)
        {
            String ma;
         ma = dskm.get(dskm.size()-1).getIDKhuyenMai();
         return ma;
        }
         return null;
    }
    //
    public ArrayList<KhuyenMaiDTO> getKhuyenMaiDTO(){
        return dskm; 
    }
    public KhuyenMaiDTO getKhuyenMaiDTO(String idkhuyenmai){
         for (KhuyenMaiDTO kmDTO: dskm){
             if(kmDTO.getIDKhuyenMai().equals(idkhuyenmai))
                 return kmDTO;
         }
         return null;
     }
}










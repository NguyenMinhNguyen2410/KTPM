/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhachHangDAO;
import java.util.ArrayList;
import DTO.KhachHangDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class KhachHangBUS {
   public static ArrayList<KhachHangDTO> ds;
   public static KhachHangDAO khdata=new KhachHangDAO();
   public KhachHangBUS()
    {
        
    }
    public static void  docDB() throws Exception 
    {
        
        if(ds==null) 
            ds=new ArrayList<KhachHangDTO>();
        ds =khdata.docDSKH();
    }
    public void  them(KhachHangDTO kh)
    {
        try
        {
            KhachHangDAO khdata=new KhachHangDAO();
            khdata.them(kh);
            if(ds!=null)
            ds.add(kh);
        }
        catch (Exception ex) {
           Logger.getLogger(KhachHangBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(KhachHangDTO kh,int i)
    {
        try
        {

           KhachHangDAO khdata=new KhachHangDAO();
           khdata.sua(kh);
           if(ds!=null)
           ds.set(i, kh);
        }
        catch (Exception ex) {
           Logger.getLogger(KhachHangBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     public void xoa(KhachHangDTO kh,int index) throws SQLException
    {
        KhachHangDAO khDao =new KhachHangDAO();
        String xoakh = ds.get(index).getIDKhachHang();
        khDao.xoa(xoakh);
        if(ds!=null)
        ds.set(index,kh);
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        KhachHangDAO data = new KhachHangDAO();
        data.xoa(ID); // update trạng thái lên database
        KhachHangDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(ds!=null)
        ds.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDKhachHang().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public KhachHangDTO getKhachHangDTO(String idkh) {
        for (KhachHangDTO khDTO : ds) {
            if (khDTO.getIDKhachHang().equals(idkh)) {
                return khDTO;
            }
        }
        return null;
    }

    public ArrayList<KhachHangDTO> getKhachHangDTO() {
    return ds;
    }
    
    public static String getMaKhachHangCuoi()
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDKhachHang();
         return ma;
        }
         return null;
    }
    public static void TongChiTieu(String makh,float tongtien){
        try
        {

           KhachHangDAO khdata=new KhachHangDAO();
           KhachHangDTO DTO = null;
           int i=timViTri(makh);
           if(ds!=null){
               DTO=ds.get(i);
               DTO.setTongChiTieu(DTO.getTongChiTieu()+tongtien);
               ds.set(i, DTO);
               khdata.sua(DTO);
           }

        }
        catch (SQLException ex) {
           Logger.getLogger(KhachHangBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}










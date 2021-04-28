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
   public static ArrayList<KhachHangDTO> dskh;
   public KhachHangBUS()
    {
        
    }
    public  void  docDSKH() throws Exception 
    {
        KhachHangDAO khdata=new KhachHangDAO();
        if(dskh==null) 
            dskh=new ArrayList<KhachHangDTO>();
        dskh =khdata.docDSKH();
    }
    public void  them(KhachHangDTO kh)
    {
        try
        {
            KhachHangDAO khdata=new KhachHangDAO();
            khdata.them(kh);
            if(dskh!=null)
            dskh.add(kh);
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
           if(dskh!=null)
           dskh.set(i, kh);
        }
        catch (Exception ex) {
           Logger.getLogger(KhachHangBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     public void xoa(KhachHangDTO kh,int index) throws SQLException
    {
        KhachHangDAO khDao =new KhachHangDAO();
        String xoakh = dskh.get(index).getIDKhachHang();
        khDao.xoa(xoakh);
        if(dskh!=null)
        dskh.set(index,kh);
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        KhachHangDAO data = new KhachHangDAO();
        data.xoa(ID); // update trạng thái lên database
        KhachHangDTO DTO=dskh.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(dskh!=null)
        dskh.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dskh.size(); i++) {
            if (dskh.get(i).getIDKhachHang().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public KhachHangDTO getKhachHangDTO(String idkh) {
        for (KhachHangDTO khDTO : dskh) {
            if (khDTO.getIDKhachHang().equals(idkh)) {
                return khDTO;
            }
        }
        return null;
    }

    public ArrayList<KhachHangDTO> getKhachHangDTO() {
    return dskh;
    }
    
    public static String getMaKhachHangCuoi()
    {
        if(dskh == null)
        {
            dskh = new ArrayList<>();
        }
        if(dskh.size() > 0)
        {
            String ma;
         ma = dskh.get(dskh.size()-1).getIDKhachHang();
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
           if(dskh!=null){
               DTO=dskh.get(i);
               DTO.setTongChiTieu(DTO.getTongChiTieu()+tongtien);
               dskh.set(i, DTO);
               khdata.sua(DTO);
           }

        }
        catch (SQLException ex) {
           Logger.getLogger(KhachHangBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}










/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PhanQuyenDAO;
import DTO.PhanQuyenDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class PhanQuyenBUS {
   public static ArrayList<PhanQuyenDTO> ds;
   public static PhanQuyenDAO DAO = new PhanQuyenDAO();
   public PhanQuyenBUS()
    {
        
    }
    public static void  docDB() throws Exception 
    {
        
        if (ds == null) {
            ds = new ArrayList<>();
        }
        ds = DAO.docPQ(); // đọc dữ liệu từ database
    }
    public void  them(PhanQuyenDTO PQDTO)
    {
        DAO.them(PQDTO);//ghi vào database
        if (ds != null)
        ds.add(PQDTO);//cập nhật arraylist
        
    }
    public void sua(PhanQuyenDTO PQDTO,int i)
    {
        DAO.sua(PQDTO);//ghi vào database
        if (ds != null)
        ds.set(i,PQDTO);//cập nhật arraylist
        
    }
     public void xoa(PhanQuyenDTO PQDTO,int index)
    {
        DAO.xoa(PQDTO); // update trạng thái lên database
        if (ds != null)
        ds.set(index, PQDTO); // sửa lại thông tin trong list
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        DAO.xoa(ID); // update trạng thái lên database
        PhanQuyenDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if (ds != null)
        ds.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDPhanQuyen().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    //Tìm mô tả quyền bằng IDPhanQuyen
    public static String timMoTaQuyenTheoIDPhanQuyen(String IDPhanQuyen)
        {
        for(PhanQuyenDTO pqDTO : ds)
        {
            if(pqDTO.getIDPhanQuyen().equals(IDPhanQuyen))
            {
                return pqDTO.getMoTaQuyen();
            }
        }
        return null;
        }
    
    public static String getMaPhanQuyenCuoi() //lấy mã cuối dể tăng
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDPhanQuyen();
         return ma;
        }
         return null;
    }
    
    public ArrayList<PhanQuyenDTO> getPhanQuyenDTO(){
         return ds;
    }
    public PhanQuyenDTO getPhanQuyenDTO(String idphanquyen){
        for (PhanQuyenDTO pqDTO: ds){
            if (pqDTO.getIDPhanQuyen().equals(idphanquyen))
                return pqDTO;
        }  
        return null;
    }
    
    public static String getMoTaQuyenTuMaQuyen(String maQuyen)
    {
        for (PhanQuyenDTO pqDTO: ds){
            if (pqDTO.getIDPhanQuyen().equals(maQuyen))
                return pqDTO.getMoTaQuyen();
        }  
        return null;
    }
}






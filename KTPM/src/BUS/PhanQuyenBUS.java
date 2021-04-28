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
   public static ArrayList<PhanQuyenDTO> dspq;
   public PhanQuyenBUS()
    {
        
    }
    public  void  docDSPQ() throws Exception 
    {
        PhanQuyenDAO pq = new PhanQuyenDAO();
        if (dspq == null) {
            dspq = new ArrayList<>();
        }
        dspq = pq.docPQ(); // đọc dữ liệu từ database
    }
    public void  them(PhanQuyenDTO PQDTO)
    {
        PhanQuyenDAO pq = new PhanQuyenDAO();
        pq.them(PQDTO);//ghi vào database
        if (dspq != null)
        dspq.add(PQDTO);//cập nhật arraylist
        
    }
    public void sua(PhanQuyenDTO PQDTO,int i)
    {
        PhanQuyenDAO pq = new PhanQuyenDAO();
        pq.sua(PQDTO);//ghi vào database
        if (dspq != null)
        dspq.set(i,PQDTO);//cập nhật arraylist
        
    }
     public void xoa(PhanQuyenDTO PQDTO,int index)
    {
        PhanQuyenDAO pq = new PhanQuyenDAO();
        pq.xoa(PQDTO); // update trạng thái lên database
        if (dspq != null)
        dspq.set(index, PQDTO); // sửa lại thông tin trong list
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        PhanQuyenDAO data = new PhanQuyenDAO();
        data.xoa(ID); // update trạng thái lên database
        PhanQuyenDTO DTO=dspq.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if (dspq != null)
        dspq.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dspq.size(); i++) {
            if (dspq.get(i).getIDPhanQuyen().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    //Tìm mô tả quyền bằng IDPhanQuyen
    public static String timMoTaQuyenTheoIDPhanQuyen(String IDPhanQuyen)
        {
        for(PhanQuyenDTO pqDTO : dspq)
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
        if(dspq == null)
        {
            dspq = new ArrayList<>();
        }
        if(dspq.size() > 0)
        {
            String ma;
         ma = dspq.get(dspq.size()-1).getIDPhanQuyen();
         return ma;
        }
         return null;
    }
    
    public ArrayList<PhanQuyenDTO> getPhanQuyenDTO(){
         return dspq;
    }
    public PhanQuyenDTO getPhanQuyenDTO(String idphanquyen){
        for (PhanQuyenDTO pqDTO: dspq){
            if (pqDTO.getIDPhanQuyen().equals(idphanquyen))
                return pqDTO;
        }  
        return null;
    }
    
    public static String getMoTaQuyenTuMaQuyen(String maQuyen)
    {
        for (PhanQuyenDTO pqDTO: dspq){
            if (pqDTO.getIDPhanQuyen().equals(maQuyen))
                return pqDTO.getMoTaQuyen();
        }  
        return null;
    }
}






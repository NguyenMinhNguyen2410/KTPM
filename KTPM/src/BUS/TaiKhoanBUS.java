/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class TaiKhoanBUS {
   public static ArrayList<TaiKhoanDTO> dstk;
   public TaiKhoanBUS()
    {
        
    }
    public  void  docDSTK() throws Exception 
    {
        TaiKhoanDAO tkdata=new TaiKhoanDAO();
        if(dstk==null) dstk=new ArrayList<TaiKhoanDTO>();
        dstk =tkdata.docDSTK();
    }
    public void them(TaiKhoanDTO tk)
    {
        try
        {
            TaiKhoanDAO tkdata=new TaiKhoanDAO();
            tkdata.them(tk);
            if(dstk!=null)
            dstk.add(tk);
        }
        catch (Exception ex) {
           Logger.getLogger(TaiKhoanBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(TaiKhoanDTO tk,int i)
    {
        try
        {

           TaiKhoanDAO tkdata=new TaiKhoanDAO();
           tkdata.sua(tk);
           if(dstk!=null)
           dstk.set(i, tk);
        }
        catch (Exception ex) {
           Logger.getLogger(TaiKhoanBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
     public void xoa(TaiKhoanDTO tk,int index)
    {
        TaiKhoanDAO nvDao =new TaiKhoanDAO();
        String xoatk = dstk.get(index).getIDNhanVien();
        nvDao.xoa(xoatk);
        if(dstk!=null)
        dstk.set(index,tk);
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        TaiKhoanDAO data = new TaiKhoanDAO();
        data.xoa(ID); // update trạng thái lên database
        TaiKhoanDTO DTO=dstk.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(dstk!=null)
        dstk.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dstk.size(); i++) {
            if (dstk.get(i).getTaiKhoan().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    public TaiKhoanDTO getTaiKhoanDTO(String idtk) {
        for (TaiKhoanDTO tkDTO : dstk) {
            if (tkDTO.getTaiKhoan().equals(idtk)) {
                return tkDTO;
            }
        }
        return null;
    }

    public ArrayList<TaiKhoanDTO> getTaiKhoanDTO() {
    return dstk;
    }
    //Tìm kiếm IDPhanQuyen theo TaiKhoan
     public static String timKiemMaQuyenTheoTenTaiKhoan(String tenTaiKhoan)
    {
        for(TaiKhoanDTO tkDTO : dstk)
        {
            if(tkDTO.getTaiKhoan().equals(tenTaiKhoan))
            {
                return tkDTO.getIDPhanQuyen();
            }
        }
        return null;
    }
     
     public static String timKiemMaNhanVienTheoTenTaiKhoan(String tenTaiKhoan)
    {
        for(TaiKhoanDTO tkDTO : dstk)
        {
            if(tkDTO.getTaiKhoan().equals(tenTaiKhoan))
            {
                return tkDTO.getIDNhanVien();
            }
        }
        return null;
    }
     
     public static boolean checkChucVuVaMaQuyen(String chucVu, String maQuyen)
    {
        if(chucVu.equals("Quản lý") && maQuyen.equals("PQ0"))
        {
            return false;
        }
        if((chucVu.equals("Nhân viên") && maQuyen.equals("PQ0")) || (chucVu.equals("Nhân viên") && maQuyen.equals("PQ1")) )
        {
            return false;
        }
        return true;
    }
}









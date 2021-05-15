/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhanVienDAO;
import java.util.ArrayList;
import DTO.NhanVienDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class NhanVienBUS {
   public static ArrayList<NhanVienDTO> ds;
   public static NhanVienDAO DAO=new NhanVienDAO();
   public NhanVienBUS()
    {
        
    }
    public static void  docDB() throws Exception 
    {
        
        if(ds==null) ds=new ArrayList<NhanVienDTO>();
        ds =DAO.docDSNV();
    }
    public void  them(NhanVienDTO nv)
    {
        try
        {
            DAO.them(nv);
            if(ds!=null)
            ds.add(nv);
        }
        catch (Exception ex) {
           Logger.getLogger(NhanVienBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void sua(NhanVienDTO nv,int i)
    {
        try
        {
           DAO.sua(nv);
           if(ds!=null)
           ds.set(i, nv);
        }
        catch (Exception ex) {
           Logger.getLogger(NhanVienBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public void xoa(NhanVienDTO nv,int index)
    {
        String xoanv = ds.get(index).getIDNhanVien();
        DAO.xoa(xoanv);
        if(ds!=null)
        ds.set(index,nv);
    }
    //Xóa với ID
    public void xoa(String ID, int index) 
    {
        DAO.xoa(ID); // update trạng thái lên database
        NhanVienDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(ds!=null)
            ds.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDNhanVien().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
     public NhanVienDTO getNhanVienDTO(String idnv) {
        for (NhanVienDTO nvDTO : ds) {
            if (nvDTO.getIDNhanVien().equals(idnv)) {
                return nvDTO;
            }
        }
        return null;
    }

    public ArrayList<NhanVienDTO> getNhanVienDTO() {
    return ds;
    }
    
    public static String getMaNhanVienCuoi() //lấy mã cuối dể tăng
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDNhanVien();
         return ma;
        }
         return null;
    }
    
    public static String getChucVuTuMaNhanVien(String maNhanVien)// trả về chức vụ từ mã nhân viên
    {
        for(NhanVienDTO nhanVienDTO : NhanVienBUS.ds)
        {
            if(nhanVienDTO.getIDNhanVien().equals(maNhanVien))
            {
                return nhanVienDTO.getChucVu();
            }
        }
        return null;
    }
}





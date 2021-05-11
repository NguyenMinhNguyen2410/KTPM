/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.CongThucDAO;
import DTO.CongThucDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CongThucBUS {
    public static ArrayList<CongThucDTO> ds;
    public static CongThucDAO ct = new CongThucDAO();
    public CongThucBUS() {

    }

    public static void docDB() throws Exception //cần ghi lại khi qua class khác
    {
        
        if (ds == null) {
            ds = new ArrayList<>();
            ds = ct.docCT(); // đọc dữ liệu từ database
        }
        

    }

    public void them(CongThucDTO CTDTO) //cần ghi lại khi qua class khác
    {
        CongThucDAO ct = new CongThucDAO();
        ct.them(CTDTO);//ghi vào database
        if (ds != null)
        ds.add(CTDTO);//cập nhật arraylist
    }

    public void sua(CongThucDTO CTDTO, int i) 
    {
        CongThucDAO ct = new CongThucDAO();
        ct.sua(CTDTO);
        if (ds != null)
        ds.set(i, CTDTO);
    }
    public void xoa(CongThucDTO CTDTO, int index) //cần ghi lại khi qua class khác
    {
        CongThucDAO ct = new CongThucDAO();
        ct.xoa(CTDTO); // update trạng thái lên database
        if (ds != null)
        ds.set(index, CTDTO); // sửa lại thông tin trong list
    }
    //Xóa với ID
    public void xoa(String ID, int index) 
    {
        CongThucDAO data = new CongThucDAO();
        data.xoa(ID); // update trạng thái lên database
        CongThucDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if (ds != null)
        ds.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDCongThuc().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    
    public static String getMaMonAnCuoi()
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDMonAn();
         return ma;
        }
         return null;
    }
    public ArrayList<CongThucDTO> getCongThucDTO() {
        return ds;
    }
    public CongThucDTO getCongThucDTO(String idcongthuc) {
        for (CongThucDTO ctDTO : ds) {
            if (ctDTO.getIDCongThuc().equals(idcongthuc)) {
                return ctDTO;
            }
        }
        return null;
    }
}







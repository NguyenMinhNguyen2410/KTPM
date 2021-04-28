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
    public static ArrayList<CongThucDTO> CT;

    public CongThucBUS() {

    }

    public void docCT() throws Exception //cần ghi lại khi qua class khác
    {
        CongThucDAO ct = new CongThucDAO();
        if (CT == null) {
            CT = new ArrayList<>();
            CT = ct.docCT(); // đọc dữ liệu từ database
        }
        

    }

    public void them(CongThucDTO CTDTO) //cần ghi lại khi qua class khác
    {
        CongThucDAO ct = new CongThucDAO();
        ct.them(CTDTO);//ghi vào database
        if (CT != null)
        CT.add(CTDTO);//cập nhật arraylist
    }

    public void sua(CongThucDTO CTDTO, int i) 
    {
        CongThucDAO ct = new CongThucDAO();
        ct.sua(CTDTO);
        if (CT != null)
        CT.set(i, CTDTO);
    }
    public void xoa(CongThucDTO CTDTO, int index) //cần ghi lại khi qua class khác
    {
        CongThucDAO ct = new CongThucDAO();
        ct.xoa(CTDTO); // update trạng thái lên database
        if (CT != null)
        CT.set(index, CTDTO); // sửa lại thông tin trong list
    }
    //Xóa với ID
    public void xoa(String ID, int index) 
    {
        CongThucDAO data = new CongThucDAO();
        data.xoa(ID); // update trạng thái lên database
        CongThucDTO DTO=CT.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if (CT != null)
        CT.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < CT.size(); i++) {
            if (CT.get(i).getIDCongThuc().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    
    public static String getMaMonAnCuoi()
    {
        if(CT == null)
        {
            CT = new ArrayList<>();
        }
        if(CT.size() > 0)
        {
            String ma;
         ma = CT.get(CT.size()-1).getIDMonAn();
         return ma;
        }
         return null;
    }
    public ArrayList<CongThucDTO> getCongThucDTO() {
        return CT;
    }
    public CongThucDTO getCongThucDTO(String idcongthuc) {
        for (CongThucDTO ctDTO : CT) {
            if (ctDTO.getIDCongThuc().equals(idcongthuc)) {
                return ctDTO;
            }
        }
        return null;
    }
}







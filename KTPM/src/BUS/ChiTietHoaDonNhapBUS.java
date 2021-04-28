/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietHoaDonNhapDAO;
import DTO.ChiTietHoaDonNhapDTO;
import DTO.NguyenLieuDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ChiTietHoaDonNhapBUS {
   public static ArrayList<ChiTietHoaDonNhapDTO> dscthdn;
   public ChiTietHoaDonNhapBUS()
    {
        
    }
    public  void  docCTHDN() throws Exception 
    {
        ChiTietHoaDonNhapDAO cthdn = new ChiTietHoaDonNhapDAO();
        if (dscthdn == null) {
            dscthdn = new ArrayList<>();
        }
        dscthdn = cthdn.docCTHDN(); // đọc dữ liệu từ database
    }
    public void  them(ChiTietHoaDonNhapDTO CTHDNDTO)
    {
        ChiTietHoaDonNhapDAO cthdn = new ChiTietHoaDonNhapDAO();
        cthdn.them(CTHDNDTO);//ghi vào database
        if(dscthdn !=null){
            dscthdn.add(CTHDNDTO);//cập nhật arraylist
        }
    }

     public void trusoluong(ChiTietHoaDonNhapDTO ctHDN){
         NguyenLieuBUS bus=new NguyenLieuBUS();
         for(NguyenLieuDTO DTO:NguyenLieuBUS.dsnl)
         {
             if(ctHDN.getIDNguyenLieu().equals(DTO.getIDNguyenLieu()))
             {
                 int i=NguyenLieuBUS.timViTri(DTO.getIDNguyenLieu());
                 DTO.setSoLuong(DTO.getSoLuong()+ctHDN.getSoLuong());
                 NguyenLieuBUS.dsnl.set(i, DTO);
                 bus.sua(DTO, i);
                 return;
             }
         }
     }
//    public ArrayList<ChiTietHoaDonNhapDTO> getAllChiTiet(String mahdn) {
//        ArrayList<ChiTietHoaDonNhapDTO> result = new ArrayList<>();
//        for (ChiTietHoaDonNhapDTO cthdn : dscthdn) {
//            if (cthdn.getIDHoaDonNhap().equals(mahdn)) {
//                result.add(cthdn);
//            }
//        }
//        return result;
//    }
    
    public  ArrayList<ChiTietHoaDonNhapDTO> getChiTietHoaDonNhapDTO() {
    return dscthdn;
    }
    
    public ChiTietHoaDonNhapDTO getChiTiet(String mahd, String manl) {
        for (ChiTietHoaDonNhapDTO cthdnDTO : dscthdn) {
            if (cthdnDTO.getIDHoaDonNhap().equals(mahd) && cthdnDTO.getIDNguyenLieu().equals(manl)) {
                return cthdnDTO;
            }
        }
        return null;
    }
    public ArrayList<ChiTietHoaDonNhapDTO> getAllChiTiet(String mahdn) throws Exception  {
        ArrayList<ChiTietHoaDonNhapDTO> result = new ArrayList<>();
        if(dscthdn==null)
        {
            docCTHDN();
        }
        for (ChiTietHoaDonNhapDTO ctHDN : dscthdn) {
            if (ctHDN.getIDHoaDonNhap().equals(mahdn)) {
                result.add(ctHDN);
            }
        }
        return result;
    }
    public ArrayList<ChiTietHoaDonNhapDTO> search(String type, String value) {
        ArrayList<ChiTietHoaDonNhapDTO> result = new ArrayList<>();
        dscthdn.forEach((cthdn) -> {
            if (type.equals("Tất cả")) {
                if (cthdn.getIDHoaDonNhap().toLowerCase().contains(value.toLowerCase())
                        || cthdn.getIDNguyenLieu().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(cthdn.getGiaNhap()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(cthdn.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                    result.add(cthdn);
                }
            } else {
                switch (type) {
                    case "Mã hóa đơn nhập":
                        if (cthdn.getIDHoaDonNhap().toLowerCase().contains(value.toLowerCase())) {
                            result.add(cthdn);
                        }
                        break;
                    case "Mã nguyên liệu":
                        if (cthdn.getIDNguyenLieu().toLowerCase().contains(value.toLowerCase())) {
                            result.add(cthdn);
                        }
                        break;
                    case "Đơn giá":
                        if (String.valueOf(cthdn.getGiaNhap()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(cthdn);
                        }
                        break;
                    case "Số lượng":
                        if (String.valueOf(cthdn.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(cthdn);
                        }
                        break;
                }
            }

        });        
        return result;
    }
}












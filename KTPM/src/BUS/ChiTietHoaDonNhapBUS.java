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
   public static ArrayList<ChiTietHoaDonNhapDTO> ds;
   public static ChiTietHoaDonNhapDAO cthdn = new ChiTietHoaDonNhapDAO();
   public ChiTietHoaDonNhapBUS()
    {
        
    }
    public static  void  docDB() throws Exception 
    {
        
        if (ds == null) {
            ds = new ArrayList<>();
        }
        ds = cthdn.docCTHDN(); // đọc dữ liệu từ database
    }
    public void  them(ChiTietHoaDonNhapDTO CTHDNDTO)
    {
        ChiTietHoaDonNhapDAO cthdn = new ChiTietHoaDonNhapDAO();
        cthdn.them(CTHDNDTO);//ghi vào database
        if(ds !=null){
            ds.add(CTHDNDTO);//cập nhật arraylist
        }
    }

     public void trusoluong(ChiTietHoaDonNhapDTO ctHDN){
         NguyenLieuBUS bus=new NguyenLieuBUS();
         for(NguyenLieuDTO DTO:NguyenLieuBUS.ds)
         {
             if(ctHDN.getIDNguyenLieu().equals(DTO.getIDNguyenLieu()))
             {
                 int i=NguyenLieuBUS.timViTri(DTO.getIDNguyenLieu());
                 DTO.setSoLuong(DTO.getSoLuong()+ctHDN.getSoLuong());
                 NguyenLieuBUS.ds.set(i, DTO);
                 bus.sua(DTO, i);
                 return;
             }
         }
     }
//    public ArrayList<ChiTietHoaDonNhapDTO> getAllChiTiet(String mahdn) {
//        ArrayList<ChiTietHoaDonNhapDTO> result = new ArrayList<>();
//        for (ChiTietHoaDonNhapDTO cthdn : ds) {
//            if (cthdn.getIDHoaDonNhap().equals(mahdn)) {
//                result.add(cthdn);
//            }
//        }
//        return result;
//    }
    
    public  ArrayList<ChiTietHoaDonNhapDTO> getChiTietHoaDonNhapDTO() {
    return ds;
    }
    
    public ChiTietHoaDonNhapDTO getChiTiet(String mahd, String manl) {
        for (ChiTietHoaDonNhapDTO cthdnDTO : ds) {
            if (cthdnDTO.getIDHoaDonNhap().equals(mahd) && cthdnDTO.getIDNguyenLieu().equals(manl)) {
                return cthdnDTO;
            }
        }
        return null;
    }
    public ArrayList<ChiTietHoaDonNhapDTO> getAllChiTiet(String mahdn) throws Exception  {
        ArrayList<ChiTietHoaDonNhapDTO> result = new ArrayList<>();
        if(ds==null)
        {
            docDB();
        }
        for (ChiTietHoaDonNhapDTO ctHDN : ds) {
            if (ctHDN.getIDHoaDonNhap().equals(mahdn)) {
                result.add(ctHDN);
            }
        }
        return result;
    }
    public ArrayList<ChiTietHoaDonNhapDTO> search(String type, String value) {
        ArrayList<ChiTietHoaDonNhapDTO> result = new ArrayList<>();
        ds.forEach((cthdn) -> {
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












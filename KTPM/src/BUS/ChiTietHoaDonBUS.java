/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import DTO.*;
import DAO.*;
import java.util.ArrayList;

public class ChiTietHoaDonBUS {

    public static ArrayList<ChiTietHoaDonDTO> cthd;

    public ChiTietHoaDonBUS() {

    }

    public void docCTHD() throws Exception //cần ghi lại khi qua class khác
    {
        ChiTietHoaDonDAO CTHD = new ChiTietHoaDonDAO();
        if (cthd == null) {
            cthd = new ArrayList<>();
        }
        cthd = CTHD.docCTHD(); // đọc dữ liệu từ database

    }

    public void them(ChiTietHoaDonDTO ctHD) //cần ghi lại khi qua class khác
    {
        ChiTietHoaDonDAO CTHD = new ChiTietHoaDonDAO();
        CTHD.them(ctHD);//ghi vào database
        if(cthd!=null){
            cthd.add(ctHD);//cập nhật arraylist
        }
        
    }

//    public void sua(ChiTietHoaDonDTO ctHD, int i) //cần ghi lại khi qua class khác
//    {
//        ChiTietHoaDonDAO CTHD = new ChiTietHoaDonDAO();
//        CTHD.sua(ctHD);
//        cthd.set(i, ctHD);
//    }

//    public void xoa(ChiTietHoaDonDTO ctHD, int index) //cần ghi lại khi qua class khác
//    {
//        ChiTietHoaDonDAO CTHD = new ChiTietHoaDonDAO();
//        CTHD.xoa(ctHD); // update trạng thái lên database
//        cthd.set(index, ctHD); // sửa lại thông tin trong list
//    }
     public void trusoluong(ChiTietHoaDonDTO ctHD){
         MonAnBUS bus=new MonAnBUS();
         for(MonAnDTO DTO:MonAnBUS.dsMonAn)
         {
             if(ctHD.getIDMonAn().equals(DTO.getIDMonAn()))
             {
                 int i=MonAnBUS.timViTri(DTO.getIDMonAn());
                 DTO.setSoLuong(DTO.getSoLuong()-ctHD.getSoLuong());
                 MonAnBUS.dsMonAn.set(i, DTO);
                 bus.sua(DTO, i);
                 return;
             }
         }
     }
    //PDF
    public ArrayList<ChiTietHoaDonDTO> getAllChiTiet(String mahd) {
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();
        for (ChiTietHoaDonDTO ct : cthd) {
            if (ct.getIDHoaDon().equals(mahd)) {
                result.add(ct);
            }
        }
        return result;
    }
    public ArrayList<ChiTietHoaDonDTO> getALLChiTiet(String idHoaDon) throws Exception {
        ArrayList<ChiTietHoaDonDTO> cthdDTO = new ArrayList<>();
        if(cthd==null)
        {
            docCTHD();
        }
        for (ChiTietHoaDonDTO ctHD : cthd) {
            if (ctHD.getIDHoaDon().equals(idHoaDon)) {
                cthdDTO.add(ctHD);
            }           
        }
        return cthdDTO;
    }
    public ChiTietHoaDonDTO getChiTiet(String idHoaDon, String IDma) {
        for (ChiTietHoaDonDTO cthdDTO : cthd) {
            if (cthdDTO.getIDHoaDon().equals(idHoaDon) && cthdDTO.getIDMonAn().equals(IDma) ) {
                return cthdDTO;
            }
        }
        return null;
    }

    public ArrayList<ChiTietHoaDonDTO> search(String type, String keyword, int soLuong1, int soLuong2, float thanhTien1, float thanhTien2) {       
        ArrayList<ChiTietHoaDonDTO> result  = new ArrayList<>();
        cthd.forEach((ctHD) -> {
            switch (type) {
                case "Tất cả":
                    if (ctHD.getIDHoaDon().toLowerCase().contains(keyword.toLowerCase())
                            || ctHD.getIDMonAn().toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(ctHD.getSoLuong()).toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(ctHD.getDonGia()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(ctHD);
                    }

                    break;

                case "Mã hóa đơn":
                    if (ctHD.getIDHoaDon().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(ctHD);
                    }
                    break;

                case "Mã món ăn":
                    if (ctHD.getIDMonAn().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(ctHD);
                    }
                    break;

                case "Số lượng":
                    if (String.valueOf(ctHD.getSoLuong()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(ctHD);
                    }
                    break;

                case "Đơn giá":
                    if (String.valueOf(ctHD.getDonGia()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(ctHD);
                    }
                    break;
            }
        });

        for (int i = result.size() - 1; i >= 0; i--) {
            ChiTietHoaDonDTO ctHD = result.get(i);
            int sl = ctHD.getSoLuong();
            float tt = (float) (ctHD.getDonGia() * sl);

            Boolean soLuongKhongThoa = (soLuong1 != -1 && sl < soLuong1) || (soLuong2 != -1 && sl > soLuong2);
            Boolean donGiaKhongThoa = (thanhTien1 != -1 && tt < thanhTien1) || (thanhTien2 != -1 && tt > thanhTien2);

            if (soLuongKhongThoa || donGiaKhongThoa) {
                result.remove(ctHD);
            }
        }
        return result;
    }
}

    









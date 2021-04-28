/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class HoaDonBUS {

    public static ArrayList<HoaDonDTO> HD;

    public HoaDonBUS() {

    }

    public void docHD() throws Exception //cần ghi lại khi qua class khác
    {
        HoaDonDAO hd = new HoaDonDAO();
        if (HD == null) {
            HD = new ArrayList<>();
        }
        HD = hd.docHD(); // đọc dữ liệu từ database

    }

    public void them(HoaDonDTO HDDTO) //cần ghi lại khi qua class khác
    {
        HoaDonDAO hd = new HoaDonDAO();
        hd.them(HDDTO);//ghi vào database
        if (HD != null)
        HD.add(HDDTO);//cập nhật arraylist
        KhachHangBUS.TongChiTieu(HDDTO.getIDKhachHang(), HDDTO.getTongTien());
    }

    public void sua(HoaDonDTO HDDTO, int i) //cần ghi lại khi qua class khác
    {
        HoaDonDAO hd = new HoaDonDAO();
        hd.sua(HDDTO);
        if (HD != null)
        HD.set(i, HDDTO);
    }

    public void xoa(HoaDonDTO HDDTO, int index) //cần ghi lại khi qua class khác
    {
        HoaDonDAO hd = new HoaDonDAO();
        hd.xoa(HDDTO); // update trạng thái lên database
        if (HD != null)
        HD.set(index, HDDTO); // sửa lại thông tin trong list
    }
    //Xóa với ID
    public void xoa(String ID, int index) 
    {
        HoaDonDAO data = new HoaDonDAO();
        data.xoa(ID); // update trạng thái lên database
        HoaDonDTO DTO=HD.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if (HD != null)
        HD.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < HD.size(); i++) {
            if (HD.get(i).getIDHoaDon().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    
    //mới thêm lấy mã hóa đơn cuối cùng của arraylist để tăng mã
    public static String getMaHoaDonCuoi()
    {
        if(HD == null)
        {
            HD = new ArrayList<>();
        }
        if(HD.size() > 0)
        {
            String ma;
         ma = HD.get(HD.size()-1).getIDHoaDon();
         return ma;
        }
         return null;
    }
    //Get làm Excel PDF
    public ArrayList<HoaDonDTO> getHoaDonDTO() {
        return HD;
    }
    public HoaDonDTO getHoaDonDTO(String idhoadon) {
        for (HoaDonDTO hdDTO : HD) {
            if (hdDTO.getIDHoaDon().equals(idhoadon)) {
                return hdDTO;
            }
        }
        return null;
    }
    
    public ArrayList<HoaDonDTO> search(String type, String value, LocalDate _ngay1, LocalDate _ngay2, int _tong1, int _tong2) {
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        HD.forEach((HoaDonDTO) -> {
            switch (type) {
                case "Tất cả":
                    if (HoaDonDTO.getIDHoaDon().toLowerCase().contains(value.toLowerCase())
                            || HoaDonDTO.getIDNhanVien().toLowerCase().contains(value.toLowerCase())
                            || HoaDonDTO.getIDKhachHang().toLowerCase().contains(value.toLowerCase())
                            || HoaDonDTO.getIDKhachHang().toLowerCase().contains(value.toLowerCase())
                            || HoaDonDTO.getNgayLap().toString().toLowerCase().contains(value.toLowerCase())
                            || String.valueOf(HoaDonDTO.getTienGiamGia()).toLowerCase().contains(value.toLowerCase())
                            || String.valueOf(HoaDonDTO.getTongTien()).toLowerCase().contains(value.toLowerCase())) {
                        result.add(HoaDonDTO);
                    }

                    break;

                case "Mã hóa đơn":
                    if (HoaDonDTO.getIDHoaDon().toLowerCase().contains(value.toLowerCase())) {
                        result.add(HoaDonDTO);
                    }
                    break;

                case "Mã nhân viên":
                    if (HoaDonDTO.getIDNhanVien().toLowerCase().contains(value.toLowerCase())) {
                        result.add(HoaDonDTO);
                    }
                    break;

                case "Mã khách hàng":
                    if (HoaDonDTO.getIDKhachHang().toLowerCase().contains(value.toLowerCase())) {
                        result.add(HoaDonDTO);
                    }
                    break;

                case "Ngày lập":
                    if (HoaDonDTO.getNgayLap().toString().toLowerCase().contains(value.toLowerCase())) {
                        result.add(HoaDonDTO);
                    }
                    break;
                case "Giảm giá":
                    if (String.valueOf(HoaDonDTO.getTienGiamGia()).toLowerCase().contains(value.toLowerCase())) {
                        result.add(HoaDonDTO);
                    }
                    break;

                case "Tổng tiền":
                    if (String.valueOf(HoaDonDTO.getTongTien()).toLowerCase().contains(value.toLowerCase())) {
                        result.add(HoaDonDTO);
                    }
            }
        });
        //Ngay lap, tong tien
        for (int i = result.size() - 1; i >= 0; i--) {
            HoaDonDTO hd = result.get(i);
            LocalDate ngaylap = hd.getNgayLap();
            float tongtien = (float) hd.getTongTien();

            Boolean ngayKhongThoa = (_ngay1 != null && ngaylap.isBefore(_ngay1)) || (_ngay2 != null && ngaylap.isAfter(_ngay2));
            Boolean tienKhongThoa = (_tong1 != -1 && tongtien < _tong1) || (_tong2 != -1 && tongtien > _tong2);

            if (ngayKhongThoa || tienKhongThoa) {
                result.remove(hd);
            }
        }
        
        return result;
    }
}





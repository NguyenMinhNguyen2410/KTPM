/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonNhapDAO;
import DTO.HoaDonNhapDTO;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class HoaDonNhapBUS {
   public static ArrayList<HoaDonNhapDTO> ds;
   public static HoaDonNhapDAO hdn = new HoaDonNhapDAO();
   public HoaDonNhapBUS()
    {
        
    }
    public static void  docDB() throws Exception 
    {
        if (ds == null) {
            ds = new ArrayList<>();
        }
        ds = hdn.docPQ(); // đọc dữ liệu từ database
    }
    public void  them(HoaDonNhapDTO HDNDTO)
    {
        HoaDonNhapDAO hdn = new HoaDonNhapDAO();
        hdn.them(HDNDTO);//ghi vào database
        if (ds != null)
        ds.add(HDNDTO);//cập nhật arraylist
        
    }
    public void sua(HoaDonNhapDTO HDNDTO,int i)
    {
        HoaDonNhapDAO hdn = new HoaDonNhapDAO();
        hdn.xoa(HDNDTO);//ghi vào database
        if (ds != null)
        ds.set(i,HDNDTO);//cập nhật arraylist
        
    }
     public void xoa(HoaDonNhapDTO HDNDTO,int index)
    {
        HoaDonNhapDAO hdn = new HoaDonNhapDAO();
        hdn.xoa(HDNDTO); // update trạng thái lên database
        if (ds != null)
        ds.set(index, HDNDTO); // sửa lại thông tin trong list
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        HoaDonNhapDAO data = new HoaDonNhapDAO();
        data.xoa(ID); // update trạng thái lên database
        HoaDonNhapDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if (ds != null)
        ds.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDHoaDonNhap().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    
    public static String getMaHoaDonNhapCuoi()
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDHoaDonNhap();
         return ma;
        }
         return null;
    }
    public ArrayList<HoaDonNhapDTO> getHoaDonNhapDTO(){
        return ds;
    }
    public HoaDonNhapDTO getHoaDonNhapDTO(String idhoadonnhap){
        for (HoaDonNhapDTO hdnDTO : ds){
            if (hdnDTO.getIDHoaDonNhap().equals(idhoadonnhap))
                return hdnDTO;
        }          
        return null;
    }
    
    public ArrayList<HoaDonNhapDTO> search(String type, String keyword, LocalDate _ngay1, LocalDate _ngay2, int _tong1, int _tong2) {
        ArrayList<HoaDonNhapDTO> result = new ArrayList<>();
        ds.forEach((hdn) -> {
            switch (type) {
                case "Tất cả":
                    if (hdn.getIDHoaDonNhap().toLowerCase().contains(keyword.toLowerCase())
                            || hdn.getIDHoaDonNhap().toLowerCase().contains(keyword.toLowerCase())
                            || hdn.getIDNhanVien().toLowerCase().contains(keyword.toLowerCase())
                            || hdn.getIDNhaCungCap().toLowerCase().contains(keyword.toLowerCase())
                            || hdn.getNgayNhap().toString().toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(hdn.getTongTien()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hdn);
                    }

                    break;

                case "Mã hóa đơn":
                    if (hdn.getIDHoaDonNhap().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hdn);
                    }
                    break;

                case "Mã nhân viên":
                    if (hdn.getIDNhanVien().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hdn);
                    }
                    break;

                case "Mã nhà cung cấp  ":
                    if (hdn.getIDNhaCungCap().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hdn);
                    }
                    break;

                case "Ngày lập":
                    if (hdn.getNgayNhap().toString().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hdn);
                    }
                    break;

                case "Tổng tiền":
                    if (String.valueOf(hdn.getTongTien()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hdn);
                    }
            }
        });

        //Ngay lap, tong tien
        for (int i = result.size() - 1; i >= 0; i--) {
            HoaDonNhapDTO hdn = result.get(i);
            LocalDate ngaylap = hdn.getNgayNhap();
            float tongtien = (float) hdn.getTongTien();

            Boolean ngayKhongThoa = (_ngay1 != null && ngaylap.isBefore(_ngay1)) || (_ngay2 != null && ngaylap.isAfter(_ngay2));
            Boolean tienKhongThoa = (_tong1 != -1 && tongtien < _tong1) || (_tong2 != -1 && tongtien > _tong2);

            if (ngayKhongThoa || tienKhongThoa) {
                result.remove(hdn);
            }
        }

        return result;
}
}







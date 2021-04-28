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
   public static ArrayList<HoaDonNhapDTO> dshdn;
   public HoaDonNhapBUS()
    {
        
    }
    public  void  docHDN() throws Exception 
    {
        HoaDonNhapDAO hdn = new HoaDonNhapDAO();
        if (dshdn == null) {
            dshdn = new ArrayList<>();
        }
        dshdn = hdn.docPQ(); // đọc dữ liệu từ database
    }
    public void  them(HoaDonNhapDTO HDNDTO)
    {
        HoaDonNhapDAO hdn = new HoaDonNhapDAO();
        hdn.them(HDNDTO);//ghi vào database
        if (dshdn != null)
        dshdn.add(HDNDTO);//cập nhật arraylist
        
    }
    public void sua(HoaDonNhapDTO HDNDTO,int i)
    {
        HoaDonNhapDAO hdn = new HoaDonNhapDAO();
        hdn.xoa(HDNDTO);//ghi vào database
        if (dshdn != null)
        dshdn.set(i,HDNDTO);//cập nhật arraylist
        
    }
     public void xoa(HoaDonNhapDTO HDNDTO,int index)
    {
        HoaDonNhapDAO hdn = new HoaDonNhapDAO();
        hdn.xoa(HDNDTO); // update trạng thái lên database
        if (dshdn != null)
        dshdn.set(index, HDNDTO); // sửa lại thông tin trong list
    }
     //Xóa với ID
    public void xoa(String ID, int index) 
    {
        HoaDonNhapDAO data = new HoaDonNhapDAO();
        data.xoa(ID); // update trạng thái lên database
        HoaDonNhapDTO DTO=dshdn.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if (dshdn != null)
        dshdn.set(index, DTO);
    }
    
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < dshdn.size(); i++) {
            if (dshdn.get(i).getIDHoaDonNhap().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    
    public static String getMaHoaDonNhapCuoi()
    {
        if(dshdn == null)
        {
            dshdn = new ArrayList<>();
        }
        if(dshdn.size() > 0)
        {
            String ma;
         ma = dshdn.get(dshdn.size()-1).getIDHoaDonNhap();
         return ma;
        }
         return null;
    }
    public ArrayList<HoaDonNhapDTO> getHoaDonNhapDTO(){
        return dshdn;
    }
    public HoaDonNhapDTO getHoaDonNhapDTO(String idhoadonnhap){
        for (HoaDonNhapDTO hdnDTO : dshdn){
            if (hdnDTO.getIDHoaDonNhap().equals(idhoadonnhap))
                return hdnDTO;
        }          
        return null;
    }
    
    public ArrayList<HoaDonNhapDTO> search(String type, String keyword, LocalDate _ngay1, LocalDate _ngay2, int _tong1, int _tong2) {
        ArrayList<HoaDonNhapDTO> result = new ArrayList<>();
        dshdn.forEach((hdn) -> {
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







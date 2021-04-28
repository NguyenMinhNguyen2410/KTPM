/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nguyen
 */
public class KhachHangDTO {
    private String IDKhachHang,HoKhachHang,TenKhachHang,Gmail,GioiTinh,SoDienThoai,TrangThai;
    private float TongChiTieu;

    public KhachHangDTO(String IDKhachHang, String HoKhachHang, String TenKhachHang, String Gmail, String GioiTinh, String SoDienThoai, float TongChiTieu,String TrangThai) {
        this.IDKhachHang = IDKhachHang;
        this.HoKhachHang = HoKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.Gmail = Gmail;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.TongChiTieu = TongChiTieu;
        this.TrangThai=TrangThai;
    }

    public KhachHangDTO(String IDKhachHang, String HoKhachHang, String TenKhachHang, String Gmail, String GioiTinh, String SoDienThoai, float TongChiTieu) {
        this.IDKhachHang = IDKhachHang;
        this.HoKhachHang = HoKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.Gmail = Gmail;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.TongChiTieu = TongChiTieu;
    }
    
    public KhachHangDTO(){
        
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getHoKhachHang() {
        return HoKhachHang;
    }

    public void setHoKhachHang(String HoKhachHang) {
        this.HoKhachHang = HoKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String Gmail) {
        this.Gmail = Gmail;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public float getTongChiTieu() {
        return TongChiTieu;
    }

    public void setTongChiTieu(float TongChiTieu) {
        this.TongChiTieu = TongChiTieu;
    }
    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}










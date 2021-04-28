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
public class NhanVienDTO {
    private String IDNhanVien,HoNhanVien,TenNhanVien,Gmail,GioiTinh,SoDienThoai,ChucVu,TrangThai;
    public NhanVienDTO(String IDNhanVien,String HoNhanVien,String TenNhanVien,String Gmail,String GioiTinh,String SoDienThoai,String ChucVu){
        this.IDNhanVien = IDNhanVien;
        this.HoNhanVien = HoNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.Gmail = Gmail;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.ChucVu = ChucVu;      
    }

    public NhanVienDTO(String IDNhanVien, String HoNhanVien, String TenNhanVien, String Gmail, String GioiTinh, String SoDienThoai, String ChucVu, String TrangThai) {
        this.IDNhanVien = IDNhanVien;
        this.HoNhanVien = HoNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.Gmail = Gmail;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.ChucVu = ChucVu;
        this.TrangThai = TrangThai;
    }

    public NhanVienDTO(){
        
    }
    
    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getHoNhanVien() {
        return HoNhanVien;
    }

    public void setHoNhanVien(String HoNhanVien) {
        this.HoNhanVien = HoNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
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

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }
    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
}









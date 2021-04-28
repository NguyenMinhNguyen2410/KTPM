/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author Nguyen
 */
public class HoaDonNhapDTO {
    private String IDHoaDonNhap,IDNhanVien,IDNhaCungCap,TrangThai;
    private double TongTien;
    private LocalDate NgayNhap;
    public HoaDonNhapDTO(String IDHoaDonNhap, String IDNhanVien, String IDNhaCungCap, LocalDate NgayNhap, double TongTien,String TrangThai) {
        this.IDHoaDonNhap = IDHoaDonNhap;
        this.IDNhanVien = IDNhanVien;
        this.IDNhaCungCap = IDNhaCungCap;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public HoaDonNhapDTO(String IDHoaDonNhap, String IDNhanVien, String IDNhaCungCap, LocalDate NgayNhap, double TongTien) {
        this.IDHoaDonNhap = IDHoaDonNhap;
        this.IDNhanVien = IDNhanVien;
        this.IDNhaCungCap = IDNhaCungCap;
        this.TongTien = TongTien;
        this.NgayNhap = NgayNhap;
    }
    
    public HoaDonNhapDTO() {
        
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public String getIDHoaDonNhap() {
        return IDHoaDonNhap;
    }

    public void setIDHoaDonNhap(String IDHoaDonNhap) {
        this.IDHoaDonNhap = IDHoaDonNhap;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(String IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public LocalDate getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(LocalDate NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
    
}







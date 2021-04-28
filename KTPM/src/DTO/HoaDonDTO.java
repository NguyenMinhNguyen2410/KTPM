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
public class HoaDonDTO {
    private String IDHoaDon,IDNhanVien,IDKhachHang,IDKhuyenMai,TrangThai;
    private float TienGiamGia,TongTien;
    private LocalDate NgayLap;
    public HoaDonDTO(String IDHoaDon, String IDNhanVien, String IDKhachHang, String IDKhuyenMai, LocalDate NgayLap, float TienGiamGia, float TongTien, String TrangThai) {
        this.IDHoaDon = IDHoaDon;
        this.IDNhanVien = IDNhanVien;
        this.IDKhachHang = IDKhachHang;
        this.IDKhuyenMai = IDKhuyenMai;
        this.NgayLap = NgayLap;
        this.TrangThai = TrangThai;
        this.TienGiamGia = TienGiamGia;
        this.TongTien = TongTien;
    }

    public HoaDonDTO(String IDHoaDon, String IDNhanVien, String IDKhachHang, String IDKhuyenMai, LocalDate NgayLap, float TienGiamGia, float TongTien) {
        this.IDHoaDon = IDHoaDon;
        this.IDNhanVien = IDNhanVien;
        this.IDKhachHang = IDKhachHang;
        this.IDKhuyenMai = IDKhuyenMai;
        this.TienGiamGia = TienGiamGia;
        this.TongTien = TongTien;
        this.NgayLap = NgayLap;
    }
    
    public HoaDonDTO()
    {
        
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getIDKhuyenMai() {
        return IDKhuyenMai;
    }

    public void setIDKhuyenMai(String IDKhuyenMai) {
        this.IDKhuyenMai = IDKhuyenMai;
    }

    public LocalDate getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(LocalDate NgayLap) {
        this.NgayLap = NgayLap;
    }

    public float getTienGiamGia() {
        return TienGiamGia;
    }

    public void setTienGiamGia(float TienGiamGia) {
        this.TienGiamGia = TienGiamGia;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

   
}

















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
public class KhuyenMaiDTO {
    private String IDKhuyenMai,TenChuongTrinh;
    private int TienGiam;
    private LocalDate NgayBatDau,NgayKetThuc;
    private String NoiDungGiamGia,TrangThai;

    public KhuyenMaiDTO(String IDKhuyenMai, String TenChuongTrinh, int TienGiam, LocalDate NgayBatDau, LocalDate NgayKetThuc, String NoiDungGiamGia, String TrangThai) {
        this.IDKhuyenMai = IDKhuyenMai;
        this.TenChuongTrinh = TenChuongTrinh;
        this.TienGiam = TienGiam;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.NoiDungGiamGia = NoiDungGiamGia;
        this.TrangThai = TrangThai;
    }

    public KhuyenMaiDTO(String IDKhuyenMai, String TenChuongTrinh, int TienGiam, LocalDate NgayBatDau, LocalDate NgayKetThuc, String NoiDungGiamGia) {
        this.IDKhuyenMai = IDKhuyenMai;
        this.TenChuongTrinh = TenChuongTrinh;
        this.TienGiam = TienGiam;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.NoiDungGiamGia = NoiDungGiamGia;
    }

        public KhuyenMaiDTO()
        {
            
        }
        
    public String getIDKhuyenMai() {
        return IDKhuyenMai;
    }

    public void setIDKhuyenMai(String IDKhuyenMai) {
        this.IDKhuyenMai = IDKhuyenMai;
    }

    public String getTenChuongTrinh() {
        return TenChuongTrinh;
    }

    public void setTenChuongTrinh(String TenChuongTrinh) {
        this.TenChuongTrinh = TenChuongTrinh;
    }

    public int getTienGiam() {
        return TienGiam;
    }

    public void setTienGiam(int TienGiam) {
        this.TienGiam = TienGiam;
    }

    public LocalDate getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(LocalDate NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(LocalDate NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public String getNoiDungGiamGia() {
        return NoiDungGiamGia;
    }

    public void setNoiDungGiamGia(String NoiDungGiamGia) {
        this.NoiDungGiamGia = NoiDungGiamGia;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    
}

















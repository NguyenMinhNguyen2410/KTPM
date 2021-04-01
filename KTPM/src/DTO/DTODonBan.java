/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author nguye
 */
public class DTODonBan {
    private int IDDonBan,IDKhachHang,IDNhanVien,IDKhuyenMai,TongTien;
    private LocalDate NgayBan;

    public DTODonBan(int IDDonBan, int IDKhachHang, int IDNhanVien, int IDKhuyenMai, int TongTien, LocalDate NgayBan) {
        this.IDDonBan = IDDonBan;
        this.IDKhachHang = IDKhachHang;
        this.IDNhanVien = IDNhanVien;
        this.IDKhuyenMai = IDKhuyenMai;
        this.TongTien = TongTien;
        this.NgayBan = NgayBan;
    }

    public DTODonBan() {
    }

    public int getIDDonBan() {
        return IDDonBan;
    }

    public void setIDDonBan(int IDDonBan) {
        this.IDDonBan = IDDonBan;
    }

    public int getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(int IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public int getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(int IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public int getIDKhuyenMai() {
        return IDKhuyenMai;
    }

    public void setIDKhuyenMai(int IDKhuyenMai) {
        this.IDKhuyenMai = IDKhuyenMai;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public LocalDate getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(LocalDate NgayBan) {
        this.NgayBan = NgayBan;
    }
    
}

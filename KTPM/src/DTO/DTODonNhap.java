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
public class DTODonNhap {
    private int IDDonNhap, IDNhanVien,TongTien;
    private LocalDate NgayNhap;

    public DTODonNhap() {
    }

    public DTODonNhap(int IDDonNhap, int IDNhanVien, int TongTien, LocalDate NgayNhap) {
        this.IDDonNhap = IDDonNhap;
        this.IDNhanVien = IDNhanVien;
        this.TongTien = TongTien;
        this.NgayNhap = NgayNhap;
    }

    public int getIDDonNhap() {
        return IDDonNhap;
    }

    public void setIDDonNhap(int IDDonNhap) {
        this.IDDonNhap = IDDonNhap;
    }

    public int getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(int IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public LocalDate getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(LocalDate NgayNhap) {
        this.NgayNhap = NgayNhap;
    }
    
}

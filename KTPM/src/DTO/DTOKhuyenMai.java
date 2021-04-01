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
public class DTOKhuyenMai {
    private int IDKhuyenMai,ChietKhau;
    private String TenChuongTrinh,MoTaChuongTrinh;
    private LocalDate NgayBatDau,NgayKetThuc;

    public DTOKhuyenMai(int IDKhuyenMai, int ChietKhau, String TenChuongTrinh, String MoTaChuongTrinh, LocalDate NgayBatDau, LocalDate NgayKetThuc) {
        this.IDKhuyenMai = IDKhuyenMai;
        this.ChietKhau = ChietKhau;
        this.TenChuongTrinh = TenChuongTrinh;
        this.MoTaChuongTrinh = MoTaChuongTrinh;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
    }

    public DTOKhuyenMai() {
    }

    public int getIDKhuyenMai() {
        return IDKhuyenMai;
    }

    public void setIDKhuyenMai(int IDKhuyenMai) {
        this.IDKhuyenMai = IDKhuyenMai;
    }

    public int getChietKhau() {
        return ChietKhau;
    }

    public void setChietKhau(int ChietKhau) {
        this.ChietKhau = ChietKhau;
    }

    public String getTenChuongTrinh() {
        return TenChuongTrinh;
    }

    public void setTenChuongTrinh(String TenChuongTrinh) {
        this.TenChuongTrinh = TenChuongTrinh;
    }

    public String getMoTaChuongTrinh() {
        return MoTaChuongTrinh;
    }

    public void setMoTaChuongTrinh(String MoTaChuongTrinh) {
        this.MoTaChuongTrinh = MoTaChuongTrinh;
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
    
}

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
public class DTOKhachHang {
    private int IDKhachHang;
    private String HoTen,SDT,CMND;
    private LocalDate NgaySinh;

    public DTOKhachHang(int IDKhachHang, String HoTen, String SDT, String CMND, LocalDate NgaySinh) {
        this.IDKhachHang = IDKhachHang;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.CMND = CMND;
        this.NgaySinh = NgaySinh;
    }

    public DTOKhachHang() {
    }

    public int getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(int IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate NgaySinh) {
        this.NgaySinh = NgaySinh;
    }
    
}

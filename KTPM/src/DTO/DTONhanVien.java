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
public class DTONhanVien {
    private int IDNhanVien;
    private String HoTen,SDT,CMND;
    private LocalDate NgaySinh;

    public DTONhanVien(int IDNhanVien, String HoTen, String SDT, String CMND, LocalDate NgaySinh) {
        this.IDNhanVien = IDNhanVien;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.CMND = CMND;
        this.NgaySinh = NgaySinh;
    }

    public DTONhanVien() {
    }

    public int getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(int IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
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

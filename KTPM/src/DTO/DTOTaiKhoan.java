/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author nguye
 */
public class DTOTaiKhoan {
    private String TaiKhoan,MatKhau;
    private int IDNhanVien,IDPhanQuyen;

    public DTOTaiKhoan(String TaiKhoan, String MatKhau, int IDNhanVien, int IDPhanQuyen) {
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.IDNhanVien = IDNhanVien;
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public DTOTaiKhoan() {
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(int IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public int getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(int IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }
    
}

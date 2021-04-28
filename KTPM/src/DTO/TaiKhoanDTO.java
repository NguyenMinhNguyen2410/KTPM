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
public class TaiKhoanDTO {
    private String TaiKhoan,IDNhanVien,IDPhanQuyen,MatKhau,TrangThai;

    public TaiKhoanDTO(String TaiKhoan, String IDNhanVien, String IDPhanQuyen, String MatKhau) {
        this.TaiKhoan = TaiKhoan;
        this.IDNhanVien = IDNhanVien;
        this.IDPhanQuyen = IDPhanQuyen;
        this.MatKhau = MatKhau;
    }
    

    public TaiKhoanDTO(String TaiKhoan, String IDNhanVien, String IDPhanQuyen, String MatKhau, String TrangThai) {
        this.TaiKhoan = TaiKhoan;
        this.IDNhanVien = IDNhanVien;
        this.IDPhanQuyen = IDPhanQuyen;
        this.MatKhau = MatKhau;
        this.TrangThai = TrangThai;
    }
    
    public TaiKhoanDTO(){
        
    }
    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(String IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
    public String getTaiKhoan() {
        return TaiKhoan;
    }    
    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }   
    public String getTrangThai() {
        return TrangThai;
    }   
    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}









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
public class PhanQuyenDTO {
    private String IDPhanQuyen,TenQuyen,MoTaQuyen, TrangThai;

    public PhanQuyenDTO(String IDPhanQuyen, String TenQuyen, String MoTaQuyen,String TrangThai) {
        this.IDPhanQuyen = IDPhanQuyen;
        this.TenQuyen = TenQuyen;
        this.MoTaQuyen = MoTaQuyen;
        this.TrangThai=TrangThai;
    }

    public PhanQuyenDTO(String IDPhanQuyen, String TenQuyen, String MoTaQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
        this.TenQuyen = TenQuyen;
        this.MoTaQuyen = MoTaQuyen;
    }

    public PhanQuyenDTO() {
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public String getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(String IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public String getTenQuyen() {
        return TenQuyen;
    }

    public void setTenQuyen(String TenQuyen) {
        this.TenQuyen = TenQuyen;
    }

    public String getMoTaQuyen() {
        return MoTaQuyen;
    }

    public void setMoTaQuyen(String MoTaQuyen) {
        this.MoTaQuyen = MoTaQuyen;
    }
    
}






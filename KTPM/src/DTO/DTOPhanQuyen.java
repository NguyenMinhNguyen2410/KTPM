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
public class DTOPhanQuyen {
    private int IDPhanQuyen;
    private String TenQuyen,MoTaQuyen;

    public DTOPhanQuyen(int IDPhanQuyen, String TenQuyen, String MoTaQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
        this.TenQuyen = TenQuyen;
        this.MoTaQuyen = MoTaQuyen;
    }

    public DTOPhanQuyen() {
    }

    public int getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(int IDPhanQuyen) {
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

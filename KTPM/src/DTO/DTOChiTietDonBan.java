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
public class DTOChiTietDonBan {
    private int IDDonBan;
    private String IDKhung;
    private float DonGia;

    public DTOChiTietDonBan() {
    }

    public DTOChiTietDonBan(int IDDonBan, String IDKhung, float DonGia) {
        this.IDDonBan = IDDonBan;
        this.IDKhung = IDKhung;
        this.DonGia = DonGia;
    }

    public int getIDDonBan() {
        return IDDonBan;
    }

    public void setIDDonBan(int IDDonBan) {
        this.IDDonBan = IDDonBan;
    }

    public String getIDKhung() {
        return IDKhung;
    }

    public void setIDKhung(String IDKhung) {
        this.IDKhung = IDKhung;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }
    
}

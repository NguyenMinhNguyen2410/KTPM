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
    private int IDDonBan,IDSanPham;
    private String IDKhung,Mau;
    private float DonGia;

    public DTOChiTietDonBan() {
    }

    public DTOChiTietDonBan(int IDDonBan, int IDSanPham, String IDKhung, String Mau, float DonGia) {
        this.IDDonBan = IDDonBan;
        this.IDSanPham = IDSanPham;
        this.IDKhung = IDKhung;
        this.Mau = Mau;
        this.DonGia = DonGia;
    }

    public int getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(int IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public String getMau() {
        return Mau;
    }

    public void setMau(String Mau) {
        this.Mau = Mau;
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

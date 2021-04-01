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
public class DTOKhung {
    private String IDKhung,Mau;
    private int IDSanPham;
    private float DonGia;

    public DTOKhung(String IDKhung, String Mau, int IDSanPham, float DonGia) {
        this.IDKhung = IDKhung;
        this.Mau = Mau;
        this.IDSanPham = IDSanPham;
        this.DonGia = DonGia;
    }

    public DTOKhung() {
    }

    public String getIDKhung() {
        return IDKhung;
    }

    public void setIDKhung(String IDKhung) {
        this.IDKhung = IDKhung;
    }

    public String getMau() {
        return Mau;
    }

    public void setMau(String Mau) {
        this.Mau = Mau;
    }

    public int getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(int IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }
    
}

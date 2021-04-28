/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author HP
 */
public class ChiTietHoaDonDTO {

    private String IDHoaDon,IDMonAn;
    private int SoLuong;
    private float DonGia,ThanhTien;
    public ChiTietHoaDonDTO(String IDHoaDon,String IDMonAn,int SoLuong,float DonGia,float ThanhTien){
        this.IDHoaDon = IDHoaDon;
        this.IDMonAn = IDMonAn;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
        
    }


    public ChiTietHoaDonDTO() {
        
    }

    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getIDMonAn() {
        return IDMonAn;
    }

    public void setIDMonAn(String IDMonAn) {
        this.IDMonAn = IDMonAn;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }



}






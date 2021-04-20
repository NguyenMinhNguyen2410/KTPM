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
public class DTOSanPham {
    private int IDSanPham,SoLuong;
    private String TenSanPham,Dong,ChiTiet,HinhAnh;

    public DTOSanPham(int IDSanPham, int SoLuong, String TenSanPham, String Dong, String ChiTiet, String HinhAnh) {
        this.IDSanPham = IDSanPham;
        this.SoLuong = SoLuong;
        this.TenSanPham = TenSanPham;
        this.Dong = Dong;
        this.ChiTiet = ChiTiet;
        this.HinhAnh = HinhAnh;
    }

    

    public DTOSanPham() {
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    
    public int getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(int IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getDong() {
        return Dong;
    }

    public void setDong(String Dong) {
        this.Dong = Dong;
    }

    public String getChiTiet() {
        return ChiTiet;
    }

    public void setChiTiet(String ChiTiet) {
        this.ChiTiet = ChiTiet;
    }
    
}

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
public class NguyenLieuDTO {

    private String IDNguyenLieu, TenNguyenLieu, HinhAnh, Loai, DonViTinh, TrangThai;

    private int SoLuong, DonGia;

    public NguyenLieuDTO(String IDNguyenLieu, String TenNguyenLieu, String DonViTinh, int DonGia, String HinhAnh, String Loai, int SoLuong, String TrangThai) {
        this.IDNguyenLieu = IDNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
        this.HinhAnh = HinhAnh;
        this.SoLuong = SoLuong;
        this.Loai = Loai;
        this.DonViTinh = DonViTinh;
        this.DonGia = DonGia;
        this.TrangThai = TrangThai;
    }

    public NguyenLieuDTO(String IDNguyenLieu, String TenNguyenLieu, String DonViTinh, int DonGia, String HinhAnh, String Loai, int SoLuong) {
        this.IDNguyenLieu = IDNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
        this.DonViTinh = DonViTinh;
        this.DonGia = DonGia;
        this.HinhAnh = HinhAnh;
        this.Loai = Loai;
        this.SoLuong = SoLuong;
    }

    public NguyenLieuDTO() {

    }

    public String getTrangThai() {
        return TrangThai;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getIDNguyenLieu() {
        return IDNguyenLieu;
    }

    public void setIDNguyenLieu(String IDNguyenLieu) {
        this.IDNguyenLieu = IDNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }

    public void setTenNguyenLieu(String TenNguyenLieu) {
        this.TenNguyenLieu = TenNguyenLieu;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String Loai) {
        this.Loai = Loai;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

}

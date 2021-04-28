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
public class NhaCungCapDTO {
    private String IDNhaCungCap,TenNhaCungCap,SoDienThoai,Gmail,DiaChi,TrangThai;
    public NhaCungCapDTO(String IDNhaCungCap,String TenNhaCungCap,String SoDienThoai,String Gmail,String DiaChi,String TrangThai){
        this.IDNhaCungCap = IDNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.SoDienThoai = SoDienThoai;
        this.Gmail = Gmail;
        this.DiaChi = DiaChi;
        this.TrangThai=TrangThai;
    }

    public NhaCungCapDTO(String IDNhaCungCap, String TenNhaCungCap, String SoDienThoai, String Gmail, String DiaChi) {
        this.IDNhaCungCap = IDNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.SoDienThoai = SoDienThoai;
        this.Gmail = Gmail;
        this.DiaChi = DiaChi;
    }

    public NhaCungCapDTO(){
        
    }
    
    public String getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(String IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String Gmail) {
        this.Gmail = Gmail;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
}










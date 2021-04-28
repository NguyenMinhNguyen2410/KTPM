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
public class CongThucDTO {
    private String IDCongThuc,IDMonAn,MoTaCongThuc,TrangThai;

    public CongThucDTO(String IDCongThuc, String IDMonAn, String MoTaCongThuc,String TrangThai) {
        this.IDCongThuc = IDCongThuc;
        this.IDMonAn = IDMonAn;
        this.MoTaCongThuc = MoTaCongThuc;
        this.TrangThai = TrangThai;
    }
    public CongThucDTO(String IDCongThuc, String IDMonAn, String MoTaCongThuc) {
        this.IDCongThuc = IDCongThuc;
        this.IDMonAn = IDMonAn;
        this.MoTaCongThuc = MoTaCongThuc;
        
    }
    public CongThucDTO() {
    }

    public String getIDCongThuc() {
        return IDCongThuc;
    }

    public void setIDCongThuc(String IDCongThuc) {
        this.IDCongThuc = IDCongThuc;
    }

    public String getIDMonAn() {
        return IDMonAn;
    }

    public void setIDMonAn(String IDMonAn) {
        this.IDMonAn = IDMonAn;
    }

    public String getMoTaCongThuc() {
        return MoTaCongThuc;
    }

    public void setMoTaCongThuc(String MoTaCongThuc) {
        this.MoTaCongThuc = MoTaCongThuc;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}






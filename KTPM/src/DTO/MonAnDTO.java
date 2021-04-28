package DTO;
//main
/**
 *
 * @author Nguyen
 */
public class MonAnDTO {
    private String  IDMonAn,TenMonAn,DonViTinh,HinhAnh,Loai,TrangThai;
    private int DonGia;
    private int SoLuong;
    public MonAnDTO(String IDMonAn, String TenMonAn, String DonViTinh, int DonGia, String HinhAnh, String Loai, int SoLuong, String TrangThai) {
        this.IDMonAn = IDMonAn;
        this.TenMonAn = TenMonAn;
        this.DonViTinh = DonViTinh;
        this.HinhAnh = HinhAnh;
        this.Loai = Loai;
        this.TrangThai = TrangThai;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
    }
    //Mới của Nhân
    public MonAnDTO(String IDMonAn, String TenMonAn, String DonViTinh, int DonGia, String HinhAnh, String Loai, int SoLuong) {
        this.IDMonAn = IDMonAn;
        this.TenMonAn = TenMonAn;
        this.DonViTinh = DonViTinh;
        this.DonGia = DonGia;
        this.HinhAnh = HinhAnh;
        this.Loai = Loai;
        this.SoLuong = SoLuong;
    }

    public MonAnDTO()
    {
        
    }
    public String getIDMonAn() {
        return IDMonAn;
    }

    public void setIDMonAn(String IDMonAn) {
        this.IDMonAn = IDMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String TenMonAn) {
        this.TenMonAn = TenMonAn;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
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

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    

    
}




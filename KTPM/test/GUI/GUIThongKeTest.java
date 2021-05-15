/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.HoaDonBUS;
import BUS.HoaDonNhapBUS;
import BUS.KhachHangBUS;
import BUS.MonAnBUS;
import BUS.NguyenLieuBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.Tool;
import java.time.LocalDate;
import javax.swing.SwingUtilities;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nguye
 */
public class GUIThongKeTest {
    GUIThongKe thongke;
    ThongKe_Hoang thongke_hoang;
    ThongKeMonAn thongke_MonAn;
    public GUIThongKeTest() throws Exception {
        MonAnBUS.docDB();
        NguyenLieuBUS.docDB();
        NhaCungCapBUS.docDB();
        KhachHangBUS.docDB();
        NhanVienBUS.docDB();
        HoaDonBUS.docDB();
        HoaDonNhapBUS.docDB();
        thongke=new GUIThongKe();
        thongke_hoang=new ThongKe_Hoang();
        thongke_MonAn=new ThongKeMonAn();
    }
    
    @Test
    public void testcaseID_1(){
        int expected =800;
        assertEquals("Chiều cao không bằng expected",expected, thongke.height);
    }
    @Test
    public void testcaseID_2(){
        int expected =900;
        assertEquals("Chiều ngang không bằng expected",expected, thongke.width);
    }
    @Test
    public void testcaseID_3(){
        thongke.tabs.setSelectedIndex(0);
        assertTrue("Không hiện thống kê tổng quát",thongke.tabs.getComponentAt(0).isVisible());
    }
    @Test
    public void testcaseID_4(){
        thongke.tabs.setSelectedIndex(1);
        assertTrue("Không hiện thống kê tổng quát",thongke.tabs.getComponentAt(1).isVisible());
    }
    @Test
    public void testcaseID_5(){
        thongke_hoang.dPicker1.getComponentToggleCalendarButton().doClick();
        assertTrue("Không hiện calendar của Tu_NgayLap",thongke_hoang.dPicker1.isVisible());
    }
    @Test
    public void testcaseID_6(){
        thongke_hoang.dPicker1.setDate(LocalDate.of(2021, 5, 1));
        assertEquals("Sai ngày được chọn","2021-05-01", thongke_hoang.txNgay1.getText());
    }
    @Test
    public void testcaseID_7(){
        thongke_hoang.dPicker2.getComponentToggleCalendarButton().doClick();
        assertTrue("Không hiện calendar của Tu_NgayLap",thongke_hoang.dPicker2.isVisible());
    }
    @Test
    public void testcaseID_8(){
        thongke_hoang.dPicker2.setDate(LocalDate.of(2021, 5, 2));
        assertEquals("Sai ngày được chọn","2021-05-02", thongke_hoang.txNgay2.getText());
    }
    @Test
    public void testcaseID_9(){
        thongke_hoang.tabDoiTuongThongKe.setSelectedIndex(0);
        assertTrue("Không hiện tab tổng",thongke_hoang.tabDoiTuongThongKe.getComponentAt(0).isVisible());
    }
    @Test
    public void testcaseID_10(){
        thongke_hoang.tabDoiTuongThongKe.setSelectedIndex(1);
        assertTrue("Không hiện tab tổng",thongke_hoang.tabDoiTuongThongKe.getComponentAt(1).isVisible());
    }
    @Test
    public void testcaseID_11(){
        String text="MA0";
        boolean check=true;
        thongke_hoang.txMonAn.setText(text);
        System.out.println(thongke_hoang.txMonAn.getText());
        for(int i=0;i<thongke_hoang.tbThongKeHoaDon.tb.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.tbThongKeHoaDon.tbModel.getValueAt(i, 3)).equals("")
                    || String.valueOf(thongke_hoang.tbThongKeHoaDon.tbModel.getValueAt(i, 3)).indexOf(text)!=-1) {
            } else{
                check=false;
                break;
            }
        }
        assertTrue("Không hiện đúng mã món ăn",check);
    }
    @Test
    public void testcaseID_12(){
        thongke_hoang.btnChonMonAn.doClick();
        assertTrue("Không hiển thị form chọn món ăn",thongke_hoang.formchon.isVisible());
    }
    @Test
    public void testcaseID_13(){
        String text="Cơm Bò Trứng";
        String expected="MA01";
        thongke_hoang.btnChonMonAn.doClick();
        for(int i=0;i<thongke_hoang.formchon.getTable().tbModel.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.formchon.getTable().tbModel.getValueAt(i, 1)).contains(text))
            {
                thongke_hoang.formchon.getTable().tb.setRowSelectionInterval(i, i);
            }
        }
        thongke_hoang.formchon.getChon().doClick();
        assertEquals("Sai mã món ăn",expected, thongke_hoang.txMonAn.getText());
    }
    @Test
    public void testcaseID_14(){
        String text="0";
        boolean check=true;
        thongke_hoang.txNhanVien.setText(text);
        for(int i=0;i<thongke_hoang.tbThongKeHoaDon.tb.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.tbThongKeHoaDon.tbModel.getValueAt(i, 1)).equals("")
                    || String.valueOf(thongke_hoang.tbThongKeHoaDon.tbModel.getValueAt(i, 1)).contains(text))
                continue;
            else{
                check=false;
                break;
            }
        }
        assertTrue("Không hiện đúng mã nhân viên",check);
    }
    @Test
    public void testcaseID_15(){
        thongke_hoang.btnChonNhanVien.doClick();
        boolean check=thongke_hoang.formchon.isShowing();
        assertTrue("Không hiển form chọn nhân viên",check);
    }
    @Test
    public void testcaseID_16(){
        String text="Nguyên";
        String expected="NV01";
        thongke_hoang.btnChonNhanVien.doClick();
        for(int i=0;i<thongke_hoang.formchon.getTable().tbModel.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.formchon.getTable().tbModel.getValueAt(i, 2)).contains(text))
            {
                thongke_hoang.formchon.getTable().tb.setRowSelectionInterval(i, i);
            }
        }
        thongke_hoang.formchon.getChon().doClick();
        assertEquals("Sai mã nhân viên",expected, thongke_hoang.txNhanVien.getText());
    }
    @Test
    public void testcaseID_17(){
        String text="0";
        boolean check=true;
        thongke_hoang.txKhachHang.setText(text);
        for(int i=0;i<thongke_hoang.tbThongKeHoaDon.tb.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.tbThongKeHoaDon.tbModel.getValueAt(i, 2)).equals("")
                    || String.valueOf(thongke_hoang.tbThongKeHoaDon.tbModel.getValueAt(i, 2)).contains(text))
                continue;
            else{
                check=false;
                break;
            }
        }
        assertTrue("Không hiện đúng mã khách hàng",check);
    }
    @Test
    public void testcaseID_18(){
        thongke_hoang.btnChonKhachHang.doClick();
        boolean check=thongke_hoang.formchon.isShowing();
        assertTrue("Không hiển form chọn khách hàng",check);
    }
    @Test
    public void testcaseID_19(){
        String text="Lợi";
        String expected="KH01";
        thongke_hoang.btnChonKhachHang.doClick();
        for(int i=0;i<thongke_hoang.formchon.getTable().tbModel.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.formchon.getTable().tbModel.getValueAt(i, 2)).contains(text))
            {
                thongke_hoang.formchon.getTable().tb.setRowSelectionInterval(i, i);
            }
        }
        thongke_hoang.formchon.getChon().doClick();
        assertEquals("Sai mã khách hàng",expected, thongke_hoang.txKhachHang.getText());
    }
    @Test
    public void testcaseID_20(){
        assertTrue("Không đủ 7 cột",thongke_hoang.tbThongKeHoaDon.tb.getColumnCount()==7);
        String[] expected={"Hóa đơn", "Tên nhân viên", "Tên khách hàng", "Tên món ăn", "Số lượng", "Đơn giá", "Thành tiền"};
        boolean check=true;
        for(int i=0;i<thongke_hoang.tbThongKeHoaDon.tb.getColumnCount();i++)
            if(!(expected[i]==thongke_hoang.tbThongKeHoaDon.tbModel.getColumnName(i)))
                check=false;
        assertTrue("Không đúng tiêu đề",check);
    }
    
    @Test
    public void testcaseID_21(){
        thongke_hoang.tabDoiTuongThongKe.setSelectedIndex(2);
        assertTrue("Không hiện tab nhập vào",thongke_hoang.tabDoiTuongThongKe.getComponentAt(2).isVisible());
    }
    @Test
    public void testcaseID_22(){
        String text="2";
        boolean check=true;
        thongke_hoang.txNhaCC.setText(text);
        for(int i=0;i<thongke_hoang.tbThongKePhieuNhap.tb.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.tbThongKePhieuNhap.tbModel.getValueAt(i, 2)).equals("")
                    || String.valueOf(thongke_hoang.tbThongKePhieuNhap.tbModel.getValueAt(i, 2)).contains(text))
                continue;
            else{
                check=false;
                break;
            }
        }
        assertTrue("Không hiện đúng mã nhà cung cấp",check);
    }
    @Test
    public void testcaseID_23(){
        thongke_hoang.btnChonNhaCC.doClick();
        boolean check=thongke_hoang.formchon.isShowing();
        assertTrue("Không hiển form chọn nhà cung cấp",check);
    }
    @Test
    public void testcaseID_24(){
        String text="Circle K";
        String expected="NCC1";
        thongke_hoang.btnChonNhaCC.doClick();
        for(int i=0;i<thongke_hoang.formchon.getTable().tbModel.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.formchon.getTable().tbModel.getValueAt(i, 1)).contains(text))
            {
                thongke_hoang.formchon.getTable().tb.setRowSelectionInterval(i, i);
            }
        }
        thongke_hoang.formchon.getChon().doClick();
        assertEquals("Sai mã khách hàng",expected, thongke_hoang.txNhaCC.getText());
    }
    @Test
    public void testcaseID_25(){
        String text="NL01";
        boolean check=true;
        thongke_hoang.txNhaCC.setText(text);
        for(int i=0;i<thongke_hoang.tbThongKePhieuNhap.tb.getRowCount();i++)
        {
            if(String.valueOf(thongke_hoang.tbThongKePhieuNhap.tbModel.getValueAt(i, 3)).equals("")
                    || String.valueOf(thongke_hoang.tbThongKePhieuNhap.tbModel.getValueAt(i, 3)).contains(text))
                continue;
            else{
                check=false;
                break;
            }
        }
        assertTrue("Không hiện đúng mã nguyên liệu",check);
    }
    @Test
    public void testcaseID_26(){
        thongke_hoang.txMonAn.setText("MA01");
        thongke_hoang.btnRefresh.doClick();
        assertEquals("chưa clear","",thongke_hoang.txMonAn.getText());
    }
    @Test
    public void testcaseID_27(){
        thongke_hoang.tabDoiTuongThongKe.setSelectedIndex(2);
        assertTrue("Không đủ 7 cột",thongke_hoang.tbThongKeHoaDon.tb.getColumnCount()==7);
        String[] expected={"Hóa đơn nhập", "Tên nhân viên", "Tên nhà cung cấp", "Tên nguyên liệu", "Số lượng", "Đơn giá", "Thành tiền"};
        boolean check=true;
        for(int i=0;i<thongke_hoang.tbThongKePhieuNhap.tb.getColumnCount();i++)
            if(!(expected[i]==thongke_hoang.tbThongKePhieuNhap.tbModel.getColumnName(i)))
                check=false;
        assertTrue("Không đúng tiêu đề",check);
    }
    @Test
    public void testcaseID_28(){
        String[] expected={"Mã nguyên liệu", "Tên nguyên liệu", "Mã phiếu nhập", "Tên nhà cung cấp", "Ngày nhập", "Số lượng", "Ðơn giá", "Tổng chi"};
        thongke_MonAn.cbTieuChi.setSelectedIndex(0);
        assertTrue("Không đủ 8 cột",thongke_MonAn.tb.tb.getColumnCount()==8);
        boolean check=true;
        for(int i=0;i<thongke_MonAn.tb.tb.getColumnCount();i++)
            if(!(expected[i]==thongke_MonAn.tb.tbModel.getColumnName(i)))
                check=false;
        assertTrue("Nội dung bảng không đúng",check);
    }
    @Test
    public void testcaseID_29(){
        String[] expected={"Mã món ăn", "Tên món ăn", "Mã hóa don", "Tên nhân viên", "Ngày lập", "Số lượng", "Ðơn giá", "Tổng thu"};
        thongke_MonAn.cbTieuChi.setSelectedIndex(1);
        assertTrue("Không đủ 8 cột",thongke_MonAn.tb.tb.getColumnCount()==8);
        boolean check=true;
        for(int i=0;i<thongke_MonAn.tb.tb.getColumnCount();i++)
            if(!(expected[i]==thongke_MonAn.tb.tbModel.getColumnName(i)))
                check=false;
        assertTrue("Nội dung bảng không đúng",check);
    }
    
}

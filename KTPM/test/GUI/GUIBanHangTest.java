/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.HoaDonBUS;
import BUS.KhuyenMaiBUS;
import BUS.MonAnBUS;
import BUS.Tool;
import static BUS.Tool.removeAccent;
import DTO.MonAnDTO;
import EXT.MyTable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nguye
 */
public class GUIBanHangTest {
    private GUIBanHang instance;

    public GUIBanHangTest() throws Exception {
        MonAnBUS.docDB();
        KhuyenMaiBUS.docDB();
        HoaDonBUS.docDB();
        instance = new GUIBanHang();
        instance.getMaHD().setText(Tool.tangMa(HoaDonBUS.getMaHoaDonCuoi()));
        instance.getNhanVien().setText("NV01");
    }
    
    @Test
    public void testcaseID_1(){
        int expected =800;
        assertEquals("Chiều cao không bằng expected",expected, instance.getHeight());
    }
    @Test
    public void testcaseID_2(){
        int expected =900;
        assertEquals("Chiều ngang không bằng expected",expected, instance.getWidth());
    }
    @Test
    public void testcaseID_3(){
        boolean check=true;
        String text="ga";
        ArrayList<MonAnDTO> expected=new ArrayList<>();
        for(MonAnDTO DTO:MonAnBUS.ds)
            if(removeAccent(DTO.getTenMonAn().toLowerCase()).contains(removeAccent(text.toLowerCase())))//Tìm kiếm theo chữ VN
                expected.add(DTO);
        instance.getSearch().setText(text);
        MyTable table=instance.getTable_MonAn();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 0)).equals(expected.get(i).getIDMonAn()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_4(){
        assertFalse("Có thể nhập được",instance.getTxMaMA().isEditable());
    }
    @Test
    public void testcaseID_5(){
        String text="Cơm Bò Trứng";
        String expected="MA01";
        instance.getSearch().setText(text);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        assertEquals("Hiển thị sai",instance.getTxMaMA().getText(),expected);
    }
    @Test
    public void testcaseID_6(){
        assertFalse("Có thể nhập được",instance.getTxTenMA().isEditable());
    }
    @Test
    public void testcaseID_7(){
        String text="Cơm Bò Trứng";
        String expected="Cơm Bò Trứng";
        instance.getSearch().setText(text);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        assertEquals("Hiển thị sai",instance.getTxTenMA().getText(),expected);
    }
    @Test
    public void testcaseID_8(){
        assertFalse("Có thể nhập được",instance.getTxDonGia().isEditable());
    }
    @Test
    public void testcaseID_9(){
        String text="Cơm Bò Trứng";
        String expected="70000";
        instance.getSearch().setText(text);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        assertEquals("Hiển thị sai",instance.getTxDonGia().getText(),expected);
    }
    @Test
    public void testcaseID_10(){
        assertTrue("Không thể nhập được",instance.getTxSoLuong().isEditable());
    }
    @Test
    public void testcaseID_11(){
        String text="Cơm Bò Trứng";
        String expected="1";
        instance.getSearch().setText(text);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        assertTrue("Hiển thị sai",instance.getTxSoLuong().getText().equals(expected));
    }
    @Test
    public void testcaseID_12(){
        int expected=7;
        assertTrue("Bảng bên trái không phải 7 cột",instance.getTable_MonAn().tbModel.getColumnCount()==expected);
    }
    @Test
    public void testcaseID_13(){
        int expected=5;
        assertTrue("Bảng bên phải không phải 5 cột",instance.getThanhToan().tbModel.getColumnCount()==expected);
    }
    @Test
    public void testcaseID_14(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getThem().doClick();
                assertTrue("Không hiện cửa sổ chặn",instance.getOp().isShowing());
            }
        });
        
    }
    @Test
    public void testcaseID_15(){
        String text="Cơm Bò Trứng";
        boolean check=false;
        String[] expected={"MA01", "Cơm Bò Trứng", "70000", "Món chính", "1"};
        instance.getSearch().setText(text);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            int k=0;
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(table.tbModel.getValueAt(i, j).equals(expected[j]))
                    k++;
            if(k==expected.length)
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_16(){
        String text="Cơm Bò Trứng";
        boolean check=false;
        String[] expected={"MA01", "Cơm Bò Trứng", "70000", "Món chính", "3"};
        instance.getSearch().setText(text);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getTxSoLuong().setText("3");
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            int k=0;
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(table.tbModel.getValueAt(i, j).equals(expected[j]))
                    k++;
            if(k==expected.length)
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_17(){
        String text="Cơm Bò Trứng";
                instance.getSearch().setText(text);
                instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
                String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
                if (id != null) {
                    instance.showInfo(id);
                }
                instance.getTxSoLuong().setText("a");
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                
                instance.getThem().doClick();
                assertFalse("Có cửa số hiện ra",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_18(){
        String text="Cơm Bò Trứng";
        boolean check=false;
        String[] expected={"MA01", "Cơm Bò Trứng", "70000", "Món chính", "2"};
        instance.getSearch().setText(text);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            int k=0;
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(String.valueOf(table.tbModel.getValueAt(i, j)).equals(expected[j]))
                    k++;
            if(k==expected.length)
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_19(){
        String text="Cơm Bò Trứng";
        boolean check=false;
        String[] expected={"MA01", "Cơm Bò Trứng", "70000", "Món chính", "4"};
        instance.getSearch().setText(text);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getTxSoLuong().setText("3");
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            int k=0;
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(String.valueOf(table.tbModel.getValueAt(i, j)).equals(expected[j]))
                    k++;
            if(k==expected.length)
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_20(){
        String text1="Cơm Bò Trứng";
        String text2="Cơm chiên Trứng";
        boolean check=false;
        String[] expected1={"MA01", "Cơm Bò Trứng", "70000", "Món chính", "1"};
        String[] expected2={"MA03", "Cơm chiên Trứng", "50000", "Món chính", "1"};
        instance.getSearch().setText(text1);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getSearch().setText(text2);
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        int k=0;
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(String.valueOf(table.tbModel.getValueAt(i, j)).equals(expected1[j]) || String.valueOf(table.tbModel.getValueAt(i, j)).equals(expected2[j]))
                    k++;
            if(k==(expected1.length+expected2.length))
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_21(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                String expected="Vui lòng chọn 1 hàng để xóa";
                assertEquals("Sai thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_22(){
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getThanhToan().tb.setRowSelectionInterval(0, 0);
                instance.getXoa().doClick();
                String expected="Bạn có chắc chắn xóa";
                assertEquals("Sai thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_23(){
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        int row=instance.getThanhToan().tbModel.getRowCount();
        instance.getThanhToan().tb.setRowSelectionInterval(0, 0);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                instance.getOp().setValue(JOptionPane.YES_OPTION);
                assertFalse("Không xóa dữ liệu trong bảng bên phải",row==instance.getThanhToan().tbModel.getRowCount());
            }
        });
    }
    @Test
    public void testcaseID_24(){
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        int row=instance.getThanhToan().tbModel.getRowCount();
        instance.getThanhToan().tb.setRowSelectionInterval(0, 0);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                instance.getOp().setValue(JOptionPane.NO_OPTION);
                assertTrue("Có xóa dữ liệu trong bảng bên phải",row==instance.getThanhToan().tbModel.getRowCount());
            }
        });
    }
    @Test
    public void testcaseID_25(){
        assertFalse("Có thể nhập được",instance.getMaHD().isEditable());
    }
    @Test
    public void testcaseID_26(){
        assertFalse("Có thể nhập được",instance.getTongTien().isEditable());
    }
    @Test
    public void testcaseID_27(){
        assertFalse("Có thể nhập được",instance.getNgayLap().isEditable());
    }
    @Test
    public void testcaseID_28(){
        assertFalse("Có thể nhập được",instance.getNhanVien().isEditable());
    }
    @Test
    public void testcaseID_29(){
        assertFalse("Có thể nhập được",instance.getKhachHang().isEditable());
    }
    @Test
    public void testcaseID_30(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getChonKhachHang().doClick();
                assertTrue("Không hiện Dialog chọn khách hàng",instance.getFormchon().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_31(){
        String expected="KH01";
        instance.getChonKhachHang().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        assertEquals("formchon không đúng khách hàng",expected, instance.getKhachHang().getText());
    }
    @Test
    public void testcaseID_32(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getChonKhuyenMai().doClick();
                assertTrue("Không hiện Dialog chọn khuyến mãi",instance.getFormchon().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_33(){
        String expected="KM01";
        instance.getChonKhuyenMai().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        assertEquals("formchon không đúng khuyến mãi",expected, instance.getKhuyenMai().getText());
    }
    @Test
    public void testcaseID_34(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getBtnThanhToan().doClick();
                assertTrue("Không hiện JOptionPane ở nút ThanhToan",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_35(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getBtnThanhToan().doClick();
                String expected="Vui lòng điền đầy đủ thông tin";
                assertEquals("Sai thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_36(){
        instance.getChonKhachHang().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        instance.getChonKhuyenMai().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getBtnThanhToan().doClick();
                String expected="Vui lòng chọn món ăn";
                assertEquals("Sai thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_37(){
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        
        instance.getChonKhachHang().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        
        instance.getChonKhuyenMai().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        
        instance.getBtnThanhToan().doClick();
        assertEquals("Chưa clear TongTien","", instance.getTongTien().getText());
        assertEquals("Chưa clear KhachHang","", instance.getKhachHang().getText());
        assertEquals("Chưa clear KhuyenMai","", instance.getKhuyenMai().getText());
    }
    @Test
    public void testcaseID_38(){
        String MaHD=instance.getMaHD().getText();
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getChonKhachHang().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        instance.getChonKhuyenMai().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        instance.getBtnThanhToan().doClick();
        assertEquals("Không tăng mã hóa đơn",Tool.tangMa(MaHD), instance.getMaHD().getText());
    }
    @Test
    public void testcaseID_39(){
        int SoLuong= Integer.parseInt(String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 6)));
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getChonKhachHang().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        instance.getChonKhuyenMai().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        instance.getBtnThanhToan().doClick();
        assertEquals("Không giảm số lượng khi thanh toán",SoLuong-1, Integer.parseInt(String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 6))));
    }
    @Test
    public void testcaseID_40(){
        instance.getTable_MonAn().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_MonAn().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getChonKhachHang().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        instance.getChonKhuyenMai().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        instance.getBtnThanhToan().doClick();
        assertEquals("Không reset table thanh toán",0,instance.getThanhToan().tbModel.getRowCount());
    }
    
}

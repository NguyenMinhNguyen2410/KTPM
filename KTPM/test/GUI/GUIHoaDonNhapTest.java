/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.HoaDonNhapBUS;
import DTO.HoaDonNhapDTO;
import EXT.MyTable;
import Excel.DocExcel;
import Excel.XuatExcel;
import Report.PDF;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author nguye
 */
public class GUIHoaDonNhapTest {
    private GUIHoaDonNhap instance;
    public GUIHoaDonNhapTest() throws Exception {
        HoaDonNhapBUS BUS=new HoaDonNhapBUS();
        BUS.docDB();
        instance=new GUIHoaDonNhap();
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
        assertTrue("Có tồn tại nút Thêm",instance.getThem()==null);
    }
    @Test
    public void testcaseID_4(){
        assertTrue("Có tồn tại nút Sửa",instance.getSua()==null);
    }
    @Test
    public void testcaseID_5(){
        assertTrue("Có tồn tại nút Xóa",instance.getXoa()==null);
    }
    @Test
    public void testcaseID_6(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                DocExcel de=new DocExcel();
                instance.getNhapExcel().doClick();
                assertTrue("Không hiện cửa sổ chọn file",de.getFd().isShowing());
                String expected="Nhập dữ liệu hóa đơn từ Excel";
                assertEquals("Tên cửa sổ sai",expected,de.getFd().getTitle() );
            }
        });
    }
    @Test
    public void testcaseID_7(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                XuatExcel xe=new XuatExcel();
                instance.getXuatExcel().doClick();
                assertTrue("Không hiện cửa sổ chọn file",xe.getFd().isShowing());
                String expected="Xuất dữ liệu hóa đơn ra Excel";
                assertEquals("Tên cửa sổ sai",expected,xe.getFd().getTitle() );
            }
        });
    }
    @Test
    public void testcaseID_8(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getInPDF().doClick();
                assertTrue("Không hiện cửa sổ chặn JOpitonPane",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_9(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                PDF pdf=new PDF();
                instance.getTable().tb.setRowSelectionInterval(0, 0);
                instance.getInPDF().doClick();
                assertTrue("Không hiện cửa sổ chọn file",pdf.getFd().isShowing());
                String expected="In hóa đơn";
                assertEquals("Tên cửa sổ sai",expected,pdf.getFd().getTitle() );
            }
        });
    }
    
    @Test
    public void testcaseID_10(){
        instance.getCbSearch().setSelectedIndex(1);
        String expected="Mã nhân viên";
        assertEquals("Sai chức năng combobox",expected, ((TitledBorder)instance.getTen().getBorder()).getTitle());
    }
    
    @Test
    public void testcaseID_11(){
        boolean check=true;
        String text="1";
        ArrayList<HoaDonNhapDTO> expected=new ArrayList<>();
        for(HoaDonNhapDTO DTO:HoaDonNhapBUS.ds)
            if(DTO.getIDHoaDonNhap().contains(text))
                expected.add(DTO);
        instance.getTen().setText(text);
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 0)).equals(expected.get(i).getIDHoaDonNhap()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_12(){
        boolean check=true;
        String text="a";
        ArrayList<HoaDonNhapDTO> expected=new ArrayList<>();
        for(HoaDonNhapDTO DTO:HoaDonNhapBUS.ds)
            if(DTO.getIDHoaDonNhap().contains(text))
                expected.add(DTO);
        instance.getTen().setText(text);
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 0)).equals(expected.get(i).getIDHoaDonNhap()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_13(){
        String text="@";
        instance.getTen().setText(text);
        MyTable table=instance.getTable();
        assertTrue("Hiển thị sai",table.tbModel.getRowCount()==0);
    }
    @Test
    public void testcaseID_14(){
        assertFalse("Có thể nhập được",instance.getTu_NgayNhap().isEditable());
    }
    @Test
    public void testcaseID_15(){
        assertFalse("Có thể nhập được",instance.getDen_NgayNhap().isEditable());
    }
    @Test
    public void testcaseID_16(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getDp_Tu_NgayNhap().getComponentToggleCalendarButton().doClick();
                assertTrue("Không hiện calendar của Tu_NgayNhap",instance.getDp_Tu_NgayNhap().isVisible());
            }
        });
    }
    @Test
    public void testcaseID_17(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getDp_Tu_NgayNhap().getComponentToggleCalendarButton().doClick();
                assertTrue("Không hiện calendar của Den_NgayNhap",instance.getDp_Den_NgayNhap().isVisible());
            }
        });
    }
    @Test
    public void testcaseID_18(){
        boolean check=true;
        float number=200000;
        instance.getTu_TongTien().setText(String.valueOf(number));
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(Float.valueOf(String.valueOf(table.tbModel.getValueAt(i, 4)))<number)
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_19(){
        boolean check=true;
        float number=200000;
        instance.getDen_TongTien().setText(String.valueOf(number));
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(Float.valueOf(String.valueOf(table.tbModel.getValueAt(i, 4)))>number)
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_20(){
        boolean check=true;
        float number=100000,number2=200000;
        instance.getTu_TongTien().setText(String.valueOf(number));
        instance.getDen_TongTien().setText(String.valueOf(number2));
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(Float.valueOf(String.valueOf(table.tbModel.getValueAt(i, 4)))<number||Float.valueOf(String.valueOf(table.tbModel.getValueAt(i, 4)))>number2)
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_21(){
        String text="abc";
        instance.getTu_TongTien().setText(text);
        Color expected=Color.red;
        assertEquals("Sai màu",expected,instance.getTu_TongTien().getForeground());
    }
    @Test
    public void testcaseID_22(){
        String text="@!";
        instance.getDen_TongTien().setText(text);
        Color expected=Color.red;
        assertEquals("Sai màu",expected,instance.getTu_TongTien().getForeground());
    }
    @Test
    public void testcaseID_23(){
        instance.getTen().setText("01");
        instance.getTu_TongTien().setText("70000");
        instance.getLamMoi().doClick();
        assertTrue("Chưa clear textfield Ten",instance.getTen().getText().equals(""));
        assertTrue("Chưa clear textfield Tu_TongTien",instance.getTu_TongTien().getText().equals(""));
        assertTrue("",HoaDonNhapBUS.ds.size()==instance.getTable().tbModel.getRowCount());
    }
    @Test
    public void testcaseID_24(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getChiTiet().doClick();
                assertTrue("Không hiện cửa sổ chặn JOpitonPane",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_25(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getTable().tb.setRowSelectionInterval(0, 0);
                instance.getChiTiet().doClick();
                assertTrue("Không hiện cửa sổ chặn JOpitonPane",instance.getFormchon().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_26(){
        int expected=5;
        assertTrue("Bảng không đúng 5 cột",instance.getTable().tbModel.getColumnCount()==expected);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import EXT.MyTable;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nguye
 */
public class GUITaiKhoanTest {
    private GUITaiKhoan instance;
    public GUITaiKhoanTest() throws Exception {
        TaiKhoanBUS.docDB();
        instance=new GUITaiKhoan();
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
        assertTrue("Có tồn tại",instance.getThem()==null);
        
    }
    @Test
    public void testcaseID_4(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getTable().tb.setRowSelectionInterval(0, 0);
                instance.getSua().doClick();
                assertTrue("Không hiện cửa sổ sửa",instance.getSua_Frame().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_5(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getSua().doClick();
                String expected="Vui lòng chọn 1 hàng để sửa";
                assertEquals("Không đúng thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_6(){
        assertTrue("Có tồn tại",instance.getXoa()==null);
    }
    @Test
    public void testcaseID_7(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                DocExcel de=new DocExcel();
                instance.getNhapExcel().doClick();
                assertTrue("Không hiện cửa sổ chọn file",de.getFd().isShowing());
                String expected="Nhập dữ liệu công thức từ Excel";
                assertEquals("Tên cửa sổ sai",expected,de.getFd().getTitle() );
            }
        });
    }
    @Test
    public void testcaseID_8(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                XuatExcel xe=new XuatExcel();
                instance.getXuatExcel().doClick();
                assertTrue("Không hiện cửa sổ chọn file",xe.getFd().isShowing());
                String expected="Xuất dữ liệu công thức ra Excel";
                assertEquals("Tên cửa sổ sai",expected,xe.getFd().getTitle() );
            }
        });
    }
    @Test
    public void testcaseID_9(){
        instance.getCbSearch().setSelectedIndex(1);
        String expected="Mã nhân viên";
        assertEquals("Sai chức năng combobox",expected, ((TitledBorder)instance.getSearch().getBorder()).getTitle());
    }
    @Test
    public void testcaseID_10(){
        boolean check=true;
        String text="q";
        ArrayList<TaiKhoanDTO> expected=new ArrayList<>();
        for(TaiKhoanDTO DTO:TaiKhoanBUS.ds)
            if(DTO.getTaiKhoan().toLowerCase().contains(text))
                expected.add(DTO);
        instance.getSearch().setText(text);
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 0)).equals(expected.get(i).getTaiKhoan()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_11(){
        boolean check=true;
        instance.getCbSearch().setSelectedIndex(1);
        String text="1";
        ArrayList<TaiKhoanDTO> expected=new ArrayList<>();
        for(TaiKhoanDTO DTO:TaiKhoanBUS.ds)
            if(DTO.getIDNhanVien().contains(text))
                expected.add(DTO);
        instance.getSearch().setText(text);
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 1)).equals(expected.get(i).getIDNhanVien()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_12(){
        boolean check=true;
        instance.getCbSearch().setSelectedIndex(2);
        String text="2";
        ArrayList<TaiKhoanDTO> expected=new ArrayList<>();
        for(TaiKhoanDTO DTO:TaiKhoanBUS.ds)
            if(DTO.getIDPhanQuyen().contains(text))
                expected.add(DTO);
        instance.getSearch().setText(text);
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 2)).equals(expected.get(i).getIDPhanQuyen()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_13(){
        boolean check=true;
        instance.getCbSearch().setSelectedIndex(3);
        String text="a";
        ArrayList<TaiKhoanDTO> expected=new ArrayList<>();
        for(TaiKhoanDTO DTO:TaiKhoanBUS.ds)
            if(DTO.getMatKhau().contains(text))
                expected.add(DTO);
        instance.getSearch().setText(text);
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 3)).equals(expected.get(i).getMatKhau()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_14(){
        instance.getSearch().setText("quanly");
        instance.getLamMoi().doClick();
        assertEquals("Chưa clear textfield Search","",instance.getSearch().getText());
    }
    @Test
    public void testcaseID_15(){
        int expected=4;
        assertTrue("Bảng không đúng 4 cột",instance.getTable().tbModel.getColumnCount()==expected);
    }
}

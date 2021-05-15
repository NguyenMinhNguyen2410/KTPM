/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CongThucBUS;
import BUS.MonAnBUS;
import DTO.CongThucDTO;
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
public class GUICongThucTest {
    private GUICongThuc instance;
    public GUICongThucTest() throws Exception {
        CongThucBUS.docDB();
        MonAnBUS.docDB();
        instance=new GUICongThuc();
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
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getThem().doClick();
                assertTrue("Không hiện cửa sổ thêm",instance.getThem_Frame().isShowing());
            }
        });
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
                assertTrue("Không hiện cửa sổ chặn sửa",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_6(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getTable().tb.setRowSelectionInterval(0, 0);
                instance.getXoa().doClick();
                assertTrue("Không hiện cửa sổ xác nhận xóa",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_7(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                assertTrue("Không hiện cửa sổ chặn xóa",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_8(){
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
    public void testcaseID_9(){
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
    public void testcaseID_10(){
        instance.getCbSearch().setSelectedIndex(1);
        String expected="Mã món ăn";
        assertEquals("Sai chức năng combobox",expected, ((TitledBorder)instance.getSearch().getBorder()).getTitle());
    }
    @Test
    public void testcaseID_11(){
        boolean check=true;
        String text="0";
        ArrayList<CongThucDTO> expected=new ArrayList<>();
        for(CongThucDTO DTO:CongThucBUS.ds)
            if(DTO.getIDCongThuc().contains(text))
                expected.add(DTO);
        instance.getSearch().setText(text);
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 0)).equals(expected.get(i).getIDCongThuc()))
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
        ArrayList<CongThucDTO> expected=new ArrayList<>();
        for(CongThucDTO DTO:CongThucBUS.ds)
            if(DTO.getIDCongThuc().contains(text))
                expected.add(DTO);
        instance.getSearch().setText(text);
        MyTable table=instance.getTable();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 0)).equals(expected.get(i).getIDCongThuc()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_13(){
        String text="@";
        instance.getSearch().setText(text);
        MyTable table=instance.getTable();
        assertTrue("Hiển thị sai",table.tbModel.getRowCount()==0);
    }
    @Test
    public void testcaseID_14(){
        instance.getSearch().setText("oaw");
        instance.getLamMoi().doClick();
        assertEquals("Chưa clear textfield Search","",instance.getSearch().getText());
    }
    @Test
    public void testcaseID_15(){
        int expected=3;
        assertTrue("Bảng không đúng 3 cột",instance.getTable().tbModel.getColumnCount()==expected);
    }
    
}

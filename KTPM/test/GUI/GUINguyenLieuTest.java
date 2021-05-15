/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NguyenLieuBUS;
import BUS.Tool;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nguye
 */
public class GUINguyenLieuTest {
    GUINguyenLieu instance;

    public GUINguyenLieuTest() throws Exception{
        NguyenLieuBUS.docDB();
        instance=new GUINguyenLieu();
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
//        System.out.println(((TitledBorder)instance.getTen().getBorder()).getTitleColor());
        assertTrue("Sai màu",Color.black==((TitledBorder)instance.getTen().getBorder()).getTitleColor());
    }
    @Test
    public void testcaseID_4(){
        assertFalse("Đúng màu",Color.BLUE==((TitledBorder)instance.getTen().getBorder()).getTitleColor());
    }
    @Test
    public void testcaseID_5(){
        System.out.println(instance.getThem().getForeground());
        assertTrue("Sai màu",instance.getThem().getForeground()==Color.BLACK);
    }
    @Test
    public void testcaseID_6(){
        System.out.println(instance.getThem().getForeground());
        assertFalse("Đúng màu",instance.getThem().getForeground()==Color.WHITE);
    }
    @Test
    public void testcaseID_7(){
        Font f=new Font("Segoe UI", 0, 14);
        assertTrue("Sai font",instance.getTen().getFont().getFontName().equals(f.getFontName()));
    }
    @Test
    public void testcaseID_8(){
        Color background=Color.decode("#90CAF9");
        assertTrue("Sai màu",instance.getThem().getBackground().equals(background));
        
    }
    @Test
    public void testcaseID_9(){
        String text="th";
        boolean check=true;
        instance.getTen().setText(text);
        for(int i=0;i<instance.getTable().tbModel.getRowCount();i++)
            if(!Tool.removeAccent(String.valueOf(instance.getTable().tbModel.getValueAt(i, 1))).toLowerCase().contains(text.toLowerCase()))
            {
                check=false;
                break;
            }
        assertTrue("Search sai",check);
    }
    @Test
    public void testcaseID_10(){
        int expected=7;
        assertTrue("Bảng không đúng 7 cột",instance.getTable().tbModel.getColumnCount()==expected);
    }
    @Test
    public void testcaseID_11(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getTable().tb.setRowSelectionInterval(0, 0);
                instance.getXoa().doClick();
                assertTrue("Không hiện cửa sổ chặn",instance.getOp().isShowing());
                String expected="Bạn chằc chắn xóa?";
                assertEquals("Thông điệp sai",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_12(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                assertTrue("Không hiện cửa sổ chặn",instance.getOp().isShowing());
                String expected="Vui lòng chọn hàng muốn xóa";
                assertEquals("Thông điệp sai",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_13(){
      instance.getThem().doClick();
      boolean check=instance.getThem_Frame().isVisible();
      instance.getThoat().doClick();
        assertTrue("Không hiện",check);
    }
    @Test
    public void testcaseID_14(){
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
      boolean check=instance.getSua_Frame().isVisible();
      instance.getThoat().doClick();
        assertTrue("Không hiện",check);
    }
    @Test
    public void testcaseID_15(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
               instance.getSua().doClick();
                assertTrue("Không hiện cửa sổ chặn",instance.getOp().isShowing());
                String expected="Vui lòng chọn hàng muốn sửa";
                assertEquals("Thông điệp sai",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_16(){
        String text="-10000";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_NguyenLieu_Sua()[3].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Giá phải là số nguyên dương";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_17(){
        String text="a30aâ";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_NguyenLieu_Sua()[3].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Giá phải là số nguyên dương";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_18(){
        String text="1000#";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_NguyenLieu_Sua()[3].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Giá phải là số nguyên dương";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_19(){
        String text="10000";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_NguyenLieu_Sua()[3].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Thành công",instance.getOp()==null);
            }
        });
    }
    @Test
    public void testcaseID_20(){
        String text="-10";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_NguyenLieu_Sua()[6].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Số lượng phải là số nguyên dương";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_21(){
        String text="10a";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_NguyenLieu_Sua()[6].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Số lượng phải là số nguyên dương";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_22(){
        String text="1000#";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_NguyenLieu_Sua()[6].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Số lượng phải là số nguyên dương";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_23(){
        String text="100";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_NguyenLieu_Sua()[3].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Thành công",instance.getOp()==null);
            }
        });
    }
    @Test
    public void testcaseID_24(){
        String text="abc";
        instance.getTu_DonGia().setText(text);
        assertTrue("Sai màu",instance.getTu_DonGia().getForeground().equals(Color.red));
    }
    @Test
    public void testcaseID_25(){
        String text="50000";
        boolean check=true;
        instance.getTu_DonGia().setText(text);
        for(int i=0;i<instance.getTable().tbModel.getRowCount();i++)
            if(Integer.parseInt(String.valueOf(instance.getTable().tbModel.getValueAt(i, 3)))<Integer.parseInt(text))
            {
                check=false;
                break;
            }
        assertTrue("Search sai",check);
    }
    @Test
    public void testcaseID_26(){
        String text="@!";
        instance.getDen_DonGia().setText(text);
        assertTrue("Sai màu",instance.getDen_DonGia().getForeground().equals(Color.red));
    }
    @Test
    public void testcaseID_27(){
        String text="100000";
        boolean check=true;
        instance.getDen_DonGia().setText(text);
        for(int i=0;i<instance.getTable().tbModel.getRowCount();i++)
            if(Integer.parseInt(String.valueOf(instance.getTable().tbModel.getValueAt(i, 3)))>Integer.parseInt(text))
            {
                check=false;
                break;
            }
        assertTrue("Search sai",check);
    }
    @Test
    public void testcaseID_28(){
        String text="abc";
        instance.getTu_SoLuong().setText(text);
        assertTrue("Sai màu",instance.getTu_SoLuong().getForeground().equals(Color.red));
    }
    @Test
    public void testcaseID_29(){
        String text="3";
        boolean check=true;
        instance.getTu_SoLuong().setText(text);
        for(int i=0;i<instance.getTable().tbModel.getRowCount();i++)
            if(Integer.parseInt(String.valueOf(instance.getTable().tbModel.getValueAt(i, 6)))<Integer.parseInt(text))
            {
                check=false;
                break;
            }
        assertTrue("Search sai",check);
    }
    @Test
    public void testcaseID_30(){
        String text="@#";
        instance.getDen_SoLuong().setText(text);
        assertTrue("Sai màu",instance.getDen_SoLuong().getForeground().equals(Color.red));
    }
    @Test
    public void testcaseID_31(){
        String text="16";
        boolean check=true;
        instance.getDen_SoLuong().setText(text);
        for(int i=0;i<instance.getTable().tbModel.getRowCount();i++)
            if(Integer.parseInt(String.valueOf(instance.getTable().tbModel.getValueAt(i, 6)))>Integer.parseInt(text))
            {
                check=false;
                break;
            }
        assertTrue("Search sai",check);
    }
    @Test
    public void testcaseID_32(){
        instance.getTen().setText("com");
        instance.getTu_DonGia().setText("50000");
        instance.getLamMoi().doClick();
        assertTrue("Chưa clear Ten",instance.getTen().getText().isEmpty());
        assertTrue("Chưa clear Tu_DonGia",instance.getTu_DonGia().getText().isEmpty());
    }
    @Test
    public void testcaseID_33(){
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
    public void testcaseID_34(){
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
}

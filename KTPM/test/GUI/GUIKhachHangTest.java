/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.KhachHangBUS;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nguye
 */
public class GUIKhachHangTest {
    private GUIKhachHang instance;
    public GUIKhachHangTest() throws Exception {
        KhachHangBUS.docDB();
        instance=new GUIKhachHang();
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
        assertTrue("Sai màu",Color.black==((TitledBorder)instance.getTen().getBorder()).getTitleColor());
    }
    @Test
    public void testcaseID_4(){
        Font f=new Font("Times New Roman", 0, 14);
        assertTrue("Sai font",instance.getThem().getFont().getFontName().equals(f.getFontName()));
    }
    @Test
    public void testcaseID_5(){
        Color background=Color.decode("#90CAF9");
        assertTrue("Sai màu",instance.getSua().getBackground().equals(background));
    }
    @Test
    public void testcaseID_6(){
        instance.getCbSearch().setSelectedIndex(1);
        assertTrue(((TitledBorder)instance.getTen().getBorder()).getTitle().equals("Họ"));
    }
    @Test
    public void testcaseID_7(){
        String text="1";
        boolean check=true;
        instance.getTen().setText(text);
        for(int i=0;i<instance.getTable().tbModel.getRowCount();i++)
            if(!instance.getTable().tbModel.getValueAt(i, 0).toString().contains(text))
            {
                check=false;
                break;
            }
        assertTrue("Search sai",check);
    }
    @Test
    public void testcaseID_8(){
        String text="x";
        boolean check=true;
        instance.getTen().setText(text);
        for(int i=0;i<instance.getTable().tbModel.getRowCount();i++)
            if(!instance.getTable().tbModel.getValueAt(i, 0).toString().contains(text))
            {
                check=false;
                break;
            }
        assertTrue("Search sai",check);
    }
    @Test
    public void testcaseID_9(){
        String text=",./";
        instance.getTen().setText(text);
        assertTrue("Search sai",instance.getTable().tbModel.getRowCount()==0);
    }
    @Test
    public void testcaseID_10(){
        boolean check=true;
        assertTrue("Bảng không đúng 7 cột",instance.getTable().tbModel.getColumnCount()==7);
        String[] expected={"Mã khách hàng", "Họ", "Tên", "Gmail", "Giới tính", "SĐT", "Tổng chi tiêu"};
        for(int i=0;i<instance.getTable().tbModel.getColumnCount();i++)
            if(!instance.getTable().tb.getColumnName(i).equals(expected[i]))
            {
                check=false;
                break;
            }
        assertTrue("Sai header",check);
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
        instance.getThem().doClick();
      boolean check=instance.getTxt_KhachHang_Them()[0].isEditable();
      instance.getThoat().doClick();
        assertFalse("Nhập được",check);
    }
    @Test
    public void testcaseID_15(){
        String text="";
        for(int i=1;i<53;i++)
            text+="a";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[1].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Họ khách hàng không được quá 50 ký tự";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_16(){
        String text="/";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[1].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Họ nhân viên không được chưa ký tự đặc biệt";
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
        String text="Nguyễn";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[1].setText(text);
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
    public void testcaseID_18(){
        String text="";
        for(int i=1;i<53;i++)
            text+="a";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[2].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Tên khách hàng không được quá 50 ký tự";
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
        String text="@";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[2].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Tên khách hàng không được chưa ký tự đặc biệt";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_20(){
        String text="linh";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[2].setText(text);
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
    public void testcaseID_21(){
        String text="92738129138";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[5].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Số điện thoại không chính xác";
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
        String text="0193j194n42";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[5].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Số điện thoại không chính xác";
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
        String text="2104829245";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[5].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Số điện thoại không chính xác";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_24(){
        String text="0971635999";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[5].setText(text);
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
    public void testcaseID_25(){
        String text="linhgmail.com";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[3].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Email phải đúng định dạng và không chứa ký tự đặc biệt";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_26(){
        String text="linh.;@gmail.com";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[3].setText(text);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                String expected="Email phải đúng định dạng và không chứa ký tự đặc biệt";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_27(){
        String text="linh@gmail.com";
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[3].setText(text);
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
    public void testcaseID_28(){
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getSua().doClick();
                assertTrue("Không hiện cửa sổ chặn",instance.getOp().isShowing());
                String expected="Bạn chằc chắn xóa?";
                assertEquals("Thông điệp sai",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_29(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getSua().doClick();
                assertTrue("Không hiện cửa sổ chặn",instance.getOp().isShowing());
                String expected="Vui lòng chọn hàng muốn xóa";
                assertEquals("Thông điệp sai",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_30(){
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
        instance.getTxt_KhachHang_Sua()[1].setText("");
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
               String expected="Vui lòng điền đầy đủ thông tin";
                instance.getLuu().doClick();
               instance.getOp().setOptionType(JOptionPane.YES_OPTION);
               assertTrue("Sai thông điệp",instance.getOp().getMessage().equals(expected));
               instance.getOp().setOptionType(JOptionPane.OK_OPTION);
               instance.getThoat().doClick();
            }
        });
    }
    @Test
    public void testcaseID_31(){
        instance.getTable().tb.setRowSelectionInterval(0, 0);
        instance.getSua().doClick();
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
    public void testcaseID_32(){
        instance.getTen().setText("kh01");
        instance.getTu_ChiTieu().setText("100000");
        instance.getLamMoi().doClick();
        assertTrue("Chưa clear Ten",instance.getTen().getText().isEmpty());
        assertTrue("Chưa clear Tu_ChiTieu",instance.getTu_ChiTieu().getText().isEmpty());
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
    @Test
    public void testcaseID_35(){
        instance.getTu_ChiTieu().setText("ads");
        assertTrue("Sai màu",instance.getTu_ChiTieu().getForeground().equals(Color.red));
    }
    @Test
    public void testcaseID_36(){
        String text="20000";
        boolean check=true;
        instance.getTu_ChiTieu().setText(text);
        for(int i=0;i<instance.getTable().tbModel.getRowCount();i++)
            if(Float.parseFloat(instance.getTable().tbModel.getValueAt(i, 6).toString())<Float.parseFloat(text))
            {
                check=false;
                break;
            }
        assertTrue("Tu_ChiTieu sai",check);
    }
    @Test
    public void testcaseID_37(){
        String text="-2";
        boolean check=false;
        instance.getDen_ChiTieu().setText(text);
        if(instance.getTable().tbModel.getRowCount()==0)
            check=true;
        assertTrue("Den_ChiTieu sai",check);
    }
}

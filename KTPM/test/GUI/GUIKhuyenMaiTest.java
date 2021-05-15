/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.KhuyenMaiBUS;
import static BUS.Tool.removeAccent;
import DTO.KhuyenMaiDTO;
import EXT.MyTable;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nguye
 */
public class GUIKhuyenMaiTest {
    private GUIKhuyenMai instance;
    public GUIKhuyenMaiTest() throws Exception {
        KhuyenMaiBUS BUS=new KhuyenMaiBUS();
        BUS.docDB();
        instance=new GUIKhuyenMai();
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
        
    }
    @Test
    public void testcaseID_4(){
        
    }
    @Test
    public void testcaseID_5(){
        
    }
    @Test
    public void testcaseID_6(){
        
    }
    @Test
    public void testcaseID_7(){
        
    }
    @Test
    public void testcaseID_8(){
        
    }
    @Test
    public void testcaseID_9(){
        
    }
    @Test
    public void testcaseID_10(){
        int expected=6;
        assertTrue("Bảng không đúng 6 cột",instance.getTable().tbModel.getColumnCount()==expected);
    }
    @Test
    public void testcaseID_11(){
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
    public void testcaseID_12(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                assertTrue("Không hiện cửa sổ chặn xóa",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_13(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getThem().doClick();
                assertTrue("Không hiện cửa sổ thêm",instance.getThem_Frame().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_14(){
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
    public void testcaseID_15(){
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
    public void testcaseID_16(){
        
    }
    @Test
    public void testcaseID_17(){
        
    }
    @Test
    public void testcaseID_18(){
        
    }
    @Test
    public void testcaseID_19(){
        
    }
    @Test
    public void testcaseID_20(){
    }
    @Test
    public void testcaseID_21(){
                assertFalse("Có thể nhập được",instance.getTu_NgayBatDau().isEditable());
    }
    @Test
    public void testcaseID_22(){
                assertFalse("Có thể nhập được",instance.getDen_NgayKetThuc().isEditable());
    }
    @Test
    public void testcaseID_23(){
        instance.getTen().setText("01");
        instance.getTu_TienGiam().setText("30000");
        instance.getLamMoi().doClick();
        assertTrue("Chưa clear textfield Ten",instance.getTen().getText().equals(""));
        assertTrue("Chưa clear textfield Tu_TienGiam",instance.getTu_TienGiam().getText().equals(""));
        assertTrue("",KhuyenMaiBUS.ds.size()==instance.getTable().tbModel.getRowCount());
    }
    @Test
    public void testcaseID_24(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                DocExcel de=new DocExcel();
                instance.getNhapExcel().doClick();
                assertTrue("Không hiện cửa sổ chọn file",de.getFd().isShowing());
                String expected="Nhập dữ liệu khuyến mãi từ Excel";
                assertEquals("Tên cửa sổ sai",expected,de.getFd().getTitle() );
            
                }
            });
    }
    @Test
    public void testcaseID_25(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                XuatExcel xe=new XuatExcel();
                instance.getXuatExcel().doClick();
                assertTrue("Không hiện cửa sổ chọn file",xe.getFd().isShowing());
                String expected="Xuất dữ liệu Khuyến mãi ra Excel";
                assertEquals("Tên cửa sổ sai",expected,xe.getFd().getTitle() );
            }
        });
    }
}
                
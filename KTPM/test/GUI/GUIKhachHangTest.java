/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.KhachHangBUS;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nguye
 */
public class GUIKhachHangTest {
    private GUIKhachHang instance;
    public GUIKhachHangTest() throws Exception {
        KhachHangBUS BUS=new KhachHangBUS();
        BUS.docDB();
        instance=new GUIKhachHang();
        instance.setVisible(true);
    }
    
    @Test
    public void testcaseID_1(){
        String expected_size="800x900";
        String actual_size=String.valueOf(instance.getWidth())+"x"+String.valueOf(instance.getHeight());
        assertEquals("Sai kích thước",expected_size,actual_size);
    }
    @Test
    public void testcaseID_5(){
        Color expected=Color.BLUE;
        assertEquals("Sai màu button",expected,instance.getColorButton());
    }
    @Test
    public void testcaseID_6(){
        JComboBox a=instance.getCbSearch();
        String[] combobox=new String[]{"Mã khách hàng","Họ","Tên"};
        int check=0;
        for(int i=0;i<combobox.length;i++)
            for(int j=0;j<a.getItemCount();j++)
                if(String.valueOf(a.getItemAt(j)).equals(combobox[i]))
                    check++;
        assertTrue("Sai combobox",check==combobox.length);
    }
    @Test
    public void testcaseID_7(){
        JTextField actual=instance.getTen();
        Border expected=BorderFactory.createLineBorder(instance.getColorButton());
        assertEquals("Sai border",expected, actual.getBorder());
    }
    @Test
    public void testcaseID_8(){
        int expected=7;
        assertTrue("Không đúng 7 cột",instance.getTable().tbModel.getColumnCount()==expected);
    }
    @Test
    public void testcaseID_10(){
        //https://www.infoworld.com/article/2073056/automate-gui-tests-for-swing-applications.html
        //Hướng dẫn cách lấy thuộc tính của JOptionPane
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                assertTrue("Không hiện cửa sổ thêm",instance.getOp().isShowing());
            }
        });
        
    }
    @Test
    public void testcaseID_11(){
       instance.getThem().doClick();
        assertTrue("Không hiện cửa sổ thêm",instance.getThem_Frame().isShowing());
    }
    @Test
    public void testcaseID_12(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getTable().tb.setRowSelectionInterval(0, 0);
                instance.getXoa().doClick();
                assertTrue("Không hiện cửa sổ thêm",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_13(){
        
    }
    @Test
    public void testcaseID_14(){
        
    }
    @Test
    public void testcaseID_15(){
        
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
        
    }
    @Test
    public void testcaseID_22(){
        
    }
    @Test
    public void testcaseID_23(){
        
    }
    @Test
    public void testcaseID_24(){
        
    }
    @Test
    public void testcaseID_25(){
        
    }
    @Test
    public void testcaseID_26(){
        
    }
    @Test
    public void testcaseID_27(){
        
    }
    @Test
    public void testcaseID_28(){
        
    }
    @Test
    public void testcaseID_29(){
        
    }
    @Test
    public void testcaseID_30(){
        
    }
    @Test
    public void testcaseID_31(){
        
    }
    @Test
    public void testcaseID_32(){
        
    }
    @Test
    public void testcaseID_33(){
        
    }
    @Test
    public void testcaseID_34(){
        
    }
    @Test
    public void testcaseID_35(){
        
    }
    @Test
    public void testcaseID_36(){
        
    }
    @Test
    public void testcaseID_37(){
        
    }
    @Test
    public void testcaseID_38(){
        
    }
    @Test
    public void testcaseID_39(){
        
    }
    @Test
    public void testcaseID_40(){
        
    }
    
}

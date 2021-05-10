/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Robot;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.UIManager;
import static junit.framework.Assert.*;
import org.junit.Test;
public class GUIHoaDonTest {
    private GUIHoaDon object;
    public GUIHoaDonTest() {
        object=new GUIHoaDon();
    }

    @Test
    public void testXemChiTiet() throws AWTException, Exception {
        JButton instance=object.getChiTiet();
        instance.getc
        assertTrue(instance.isVisible());
    }
    
    
}

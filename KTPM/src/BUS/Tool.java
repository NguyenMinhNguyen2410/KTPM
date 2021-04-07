/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author nguye
 */
public class Tool {
    public static ImageIcon showIcon(int width, int height, String fileName) {
        ImageIcon icon;
        try {
            BufferedImage image = ImageIO.read(new File(fileName));
            icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
            return icon;
        } catch (IOException e) {
            return null;
        }
    }
}

package button;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MoreButton extends JButton {

    public MoreButton() {
        this.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
        this.setPreferredSize(new Dimension(30, 30));
    }
}

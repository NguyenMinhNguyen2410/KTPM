/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author DELL
 */
public class DateButton_1 extends JButton {
    
    public DateButton_1(DatePicker dp) {
        ImageIcon dPickerIcon = new ImageIcon(this.getClass().getResource("/Images/Icon/icons8_calendar_31_30px.png"));
        JButton datePickerButton = dp.getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dPickerIcon);
    }
    
}

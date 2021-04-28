/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.TaiKhoanBUS;
import BUS.Tool;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Nguyen
 */
public class mainGUI {
    public static void main(String args[]) throws Exception{
        //Hàm đọc tất cả dữ liệu
        Tool.docDB();
        //chạy form đăng nhập
        new LoginGUI().setVisible(true);
        
    }
}



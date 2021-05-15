/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXT;

import GUI.GUIMenu;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen
 */
//Class này tạo bố cục sẵn cho form bán hàng và nhập hàng
public class FormBanNhap extends JPanel{
    //Tạo các panel
    private JPanel pDanhSach,pThongTin,pThanhToan,pCongCu;
    protected JOptionPane op;
    protected FormChon formchon;
    public FormBanNhap(){
        initcomponent();
    }
    public void initcomponent(){
        setLayout(null);
        
        //Dùng để chứa Panel có danh sách món ăn , nguyên liệu
        pDanhSach=panelDanhSach();
        pDanhSach.setBounds(0, 0, GUIMenu.width_content*50/100,GUIMenu.height );
        add(pDanhSach);
        //Dùng để chứa Panel có thông tin hóa đơn , hóa đơn nhập 
        pThongTin=panelThongTin();
        pThongTin.setBounds(GUIMenu.width_content*51/100, 0,GUIMenu.width_content*49/100 ,250 );
        add(pThongTin);
        //Dùng để chứa các món ăn đã order hoặc các nguyên liệu được yêu cầu
        pThanhToan=panelThanhToan();
        pThanhToan.setBounds(GUIMenu.width_content*51/100, 270, GUIMenu.width_content*49/100,430 );
        add(pThanhToan);
        //Chứa nút thanh toán và xóa các order và yêu cầu
        pCongCu=panelCongCu();
        pCongCu.setBounds(GUIMenu.width_content*51/100, 700, GUIMenu.width_content*49/100,50 );
        add(pCongCu);
        
        setVisible(true);
        setSize(GUIMenu.width_content, GUIMenu.height);
    }
    protected JPanel panelDanhSach(){
        JPanel panel=new JPanel();
        
        return panel;
    }
    protected JPanel panelThongTin(){
        JPanel panel=new JPanel();
        
        return panel;
    }
    protected JPanel panelThanhToan(){
        JPanel panel=new JPanel();
        
        return panel;
    }
    protected JPanel panelCongCu(){
        JPanel panel=new JPanel();
        
        return panel;
    }

    public JOptionPane getOp() {
        return op;
    }

    public void setOp(JOptionPane op) {
        this.op = op;
    }

    public FormChon getFormchon() {
        return formchon;
    }
    
}







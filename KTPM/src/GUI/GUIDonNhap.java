/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUSDonNhap;
import DTO.DTODonNhap;
import EXT.FormContent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class GUIDonNhap extends FormContent{
    public static String header[]={"Mã nhân viên","Ngày nhập","Tổng tiền"};
    private BUSDonNhap BUS=new BUSDonNhap();
    public GUIDonNhap() {
        super();
    }
    @Override
    protected void docDB(){
        table.setHeaders(header);
        if(BUSDonNhap.ds!=null)
        for(DTODonNhap DTO:BUSDonNhap.ds)
            table.addRow(DTO);
    }
    @Override
    protected void Them(){
        
    }
    @Override
    protected void Sua(){
        
    }
    @Override
    protected void Xoa_click(MouseEvent evt){
        int i = table.tb.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để xóa");
        } else {            
            BUS.xoa(i, i);
        }
    }
}

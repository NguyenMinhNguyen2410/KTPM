/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUSDonBan;
import DTO.DTODonBan;
import EXT.FormContent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class GUIDonBan extends FormContent{
    public static String header[]={"Mã khách hàng","Mã nhân viên","Mã khuyến mãi","Ngày bán","Tổng tiền"};
    private BUSDonBan BUS=new BUSDonBan();
    public GUIDonBan() {
        super();
    }
    @Override
    protected void docDB(){
        table.setHeaders(header);
        if(BUSDonBan.ds!=null)
        for(DTODonBan DTO:BUSDonBan.ds)
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

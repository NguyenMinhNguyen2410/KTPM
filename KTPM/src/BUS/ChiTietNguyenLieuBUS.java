/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietNguyenLieuDAO;
import DTO.ChiTietNguyenLieuDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ChiTietNguyenLieuBUS {
   public static ArrayList<ChiTietNguyenLieuDTO> ds;
   public static ChiTietNguyenLieuDAO ctnl = new ChiTietNguyenLieuDAO();
   public ChiTietNguyenLieuBUS()
    {
        
    }
    public static void  docDB() throws Exception 
    {
        
        if (ds == null) {
            ds = new ArrayList<>();
        }
        ds = ctnl.docCTNL(); // đọc dữ liệu từ database
    }
}
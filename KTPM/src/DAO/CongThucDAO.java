//main
package DAO;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CongThucDAO {

    ConnectDB connection = new ConnectDB();

    public CongThucDAO() {

    }

    public ArrayList docCT() throws SQLException, Exception { //cần ghi lại khi qua class khác
        connection = new ConnectDB();
        ArrayList<CongThucDTO> CT = new ArrayList<>();
        try {
            String qry = "SELECT * FROM congthuc";
            ResultSet result = connection.excuteQuery(qry);
            if (result != null) {
                while (result.next()) {
                    CongThucDTO ct = new CongThucDTO();
                    ct.setIDCongThuc(result.getString("IDCongThuc"));
                    ct.setIDMonAn(result.getString("IDMonAn"));
                    ct.setMoTaCongThuc(result.getString("MoTaCongThuc"));
                    ct.setTrangThai(result.getString("TrangThai"));
                    CT.add(ct);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng công thức");
        }
        return CT;
    }

    public void them(CongThucDTO CT) { //cần ghi lại khi qua class khác
        try {
            String qry = "INSERT INTO congthuc values (";
            qry = qry + "'" + CT.getIDCongThuc()+ "'";
            qry = qry + "," + "'" + CT.getIDMonAn()+ "'";
            qry = qry + "," + "'" + CT.getMoTaCongThuc()+ "'";
            qry = qry + "," + "'" + CT.getTrangThai()+ "'";
            qry = qry + ")";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {
        }
    }

    public void xoa(String IDCongThuc) { 
        try {
            String qry = "Update congthuc Set ";
            qry = qry + "TrangThai=" + "'" + "Ẩn"+ "'";
            qry = qry + " " + "WHERE IDCongThuc='" + IDCongThuc+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception ex) {

        }
    }
    
    public void xoa(CongThucDTO CT) {
        try {
            String qry = "Update congthuc Set ";
            qry = qry + "TrangThai=" + "'" + CT.getTrangThai() + "'";
            qry = qry + " " + "WHERE IDCongThuc='" + CT.getIDCongThuc()+ "'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();
        } catch (Exception e) {
        }
    }

    public void sua(CongThucDTO CT) { 
        try {
            String qry = "Update congthuc Set ";
            qry = qry + "IDMonAn=" + "'" + CT.getIDMonAn()+ "'";
            qry = qry + ",MoTaCongThuc=" + "'" + CT.getMoTaCongThuc()+ "'";
            qry = qry+" "+" WHERE IDCongThuc='"+CT.getIDCongThuc()+"'";
            connection.getStatement();
            connection.ExecuteUpdate(qry);
            System.out.println(qry);
            connection.closeConnect();

        } catch (Exception ex) {
        }
    }
}














package BUS;
import DTO.*;
import DAO.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
// copy paste qua hết
public class MonAnBUS {

    public static ArrayList<MonAnDTO> ds;
    public static MonAnDAO data = new MonAnDAO();
    public MonAnBUS() {

    }

    public void docDB() throws Exception //cần ghi lại khi qua class khác
    {
        try{
        if (ds == null) {
            ds = new ArrayList<>();
        }
        ds = data.docDB(); // đọc dữ liệu từ database
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng món ăn ở BUS");
        }
    }

    public void them(MonAnDTO monAn) //cần ghi lại khi qua class khác
    {
        data.them(monAn);//ghi vào database
        if(ds!=null)
            ds.add(monAn);//cập nhật arraylist
    }

    public void sua(MonAnDTO monAn, int i) //cần ghi lại khi qua class khác
    {
        data.sua(monAn);
        if(ds!=null)
            ds.set(i, monAn);
    }

    public void xoa(String ID, int index) //cần ghi lại khi qua class khác
    {
        data.xoa(ID); // update trạng thái lên database
        MonAnDTO DTO=ds.get(index); // sửa lại thông tin trong list
        DTO.setTrangThai("Ẩn");
        if(ds!=null)
            ds.set(index, DTO);
    }
    //tìm vị trí của thằng có chứa mã mà mình cần
    public static int timViTri( String ID) 
    {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getIDMonAn().equals(ID)) {
                return i;
            }
        }
        return 0;
    }
    public ArrayList<MonAnDTO> getMonAnDTO() {
        return ds;
    }
    public MonAnDTO getMonAnDTO(String idmonan) {
        for (MonAnDTO maDTO : ds) {
            if (maDTO.getIDMonAn().equals(idmonan)) {
                return maDTO;
            }
        }
        return null;
    }
 
    public static String getMaMonAnCuoi()
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        if(ds.size() > 0)
        {
            String ma;
         ma = ds.get(ds.size()-1).getIDMonAn();
         return ma;
        }
         return null;
    }
    
    public static boolean timMaMonAn(String maMonAn)
    {
        if(ds == null)
        {
            ds = new ArrayList<>();
        }
        for(MonAnDTO monAnDTO : ds)
        {
            if(monAnDTO.getIDMonAn().equals(maMonAn))
            {
                return true;
            }
        }
         return false;
    }
}













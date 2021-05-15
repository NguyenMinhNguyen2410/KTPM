package GUI;

import BUS.HoaDonNhapBUS;
import BUS.KhuyenMaiBUS;
import BUS.NguyenLieuBUS;
import BUS.NhaCungCapBUS;
import BUS.Tool;
import static BUS.Tool.removeAccent;
import DTO.NguyenLieuDTO;
import EXT.MyTable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nguye
 */
public class GUINhapHangTest {
    private GUINhapHang instance;

    public GUINhapHangTest() throws Exception {
        NguyenLieuBUS.docDB();
        HoaDonNhapBUS.docDB();
        NhaCungCapBUS.docDB();
        instance = new GUINhapHang();
        instance.getMaHDN().setText(Tool.tangMa3(HoaDonNhapBUS.getMaHoaDonNhapCuoi()));
        instance.getNhanVien().setText("NV01");
    }
    
    @Test
    public void testcaseID_1(){
        int expected =800;
        assertEquals("Chiều cao không bằng expected",expected, instance.getHeight());
    }
    @Test
    public void testcaseID_2(){
        int expected =900;
        assertEquals("Chiều ngang không bằng expected",expected, instance.getWidth());
    }
    @Test
    public void testcaseID_3(){
        boolean check=true;
        String text="ga";
        ArrayList<NguyenLieuDTO> expected=new ArrayList<>();
        for(NguyenLieuDTO DTO:NguyenLieuBUS.ds)
            if(removeAccent(DTO.getTenNguyenLieu().toLowerCase()).contains(removeAccent(text.toLowerCase())) &&DTO.getTrangThai().equals("Hiện") )//Tìm kiếm theo chữ VN
                expected.add(DTO);
        instance.getSearch().setText(text);
        MyTable table=instance.getTable_NguyenLieu();
        for(int i=0;i<table.tb.getRowCount();i++)
            if(!String.valueOf(table.tbModel.getValueAt(i, 0)).equals(expected.get(i).getIDNguyenLieu()))
            {
                check=false;
                break;
            }
        assertTrue("Hiển thị sai",check);
    }
    @Test
    public void testcaseID_4(){
        assertFalse("Có thể nhập được",instance.getTxMaNL().isEditable());
    }
    @Test
    public void testcaseID_5(){
        String text="Gạo";
        String expected="NL01";
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        assertEquals("Hiển thị sai",instance.getTxMaNL().getText(),expected);
    }
    @Test
    public void testcaseID_6(){
        assertFalse("Có thể nhập được",instance.getTxTenNL().isEditable());
    }
    @Test
    public void testcaseID_7(){
        String text="Gạo";
        String expected="Gạo";
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        assertEquals("Hiển thị sai",instance.getTxTenNL().getText(),expected);
    }
    @Test
    public void testcaseID_8(){
        assertFalse("Có thể nhập được",instance.getTxDonGia().isEditable());
    }
    @Test
    public void testcaseID_9(){
        String text="Gạo";
        String expected="20000";
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        assertEquals("Hiển thị sai",instance.getTxDonGia().getText(),expected);
    }
    @Test
    public void testcaseID_10(){
        assertTrue("Không thể nhập được",instance.getTxSoLuong().isEditable());
    }
    @Test
    public void testcaseID_11(){
        String text="Gạo";
        String expected="1";
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        assertTrue("Hiển thị sai",instance.getTxSoLuong().getText().equals(expected));
    }
    @Test
    public void testcaseID_12(){
        int expected=7;
        assertTrue("Bảng bên trái không phải 7 cột",instance.getTable_NguyenLieu().tbModel.getColumnCount()==expected);
    }
    @Test
    public void testcaseID_13(){
        int expected=5;
        assertTrue("Bảng bên phải không phải 5 cột",instance.getThanhToan().tbModel.getColumnCount()==expected);
    }
    @Test
    public void testcaseID_14(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getThem().doClick();
                assertTrue("Không hiện cửa sổ chặn",instance.getOp().isShowing());
            }
        });
        
    }
    @Test
    public void testcaseID_15(){
        String text="Gạo";
        boolean check=false;
        String[] expected={"NL01", "Gạo", "20000", "a", "1"};
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            int k=0;
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(table.tbModel.getValueAt(i, j).equals(expected[j]))
                    k++;
            if(k==expected.length)
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_16(){
        String text="Gạo";
        boolean check=false;
        String[] expected={"NL01", "Gạo", "20000", "a", "3"};
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getTxSoLuong().setText("3");
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            int k=0;
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(table.tbModel.getValueAt(i, j).equals(expected[j]))
                    k++;
            if(k==expected.length)
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_17(){
        String text="Gạo";
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getTxSoLuong().setText("a");
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getThem().doClick();
                assertFalse("Có cửa số hiện ra",instance.getOp().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_18(){
        String text="Gạo";
        boolean check=false;
        String[] expected={"NL01", "Gạo", "20000", "a", "2"};
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            int k=0;
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(String.valueOf(table.tbModel.getValueAt(i, j)).equals(expected[j]))
                    k++;
            if(k==expected.length)
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_19(){
        String text="Gạo";
        boolean check=false;
        String[] expected={"NL01", "Gạo", "20000", "a", "4"};
        instance.getSearch().setText(text);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getTxSoLuong().setText("3");
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            int k=0;
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(String.valueOf(table.tbModel.getValueAt(i, j)).equals(expected[j]))
                    k++;
            if(k==expected.length)
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_20(){
        String text1="Gạo";
        String text2="Thịt heo";
        boolean check=false;
        String[] expected1={"NL01", "Gạo", "20000", "a", "1"};
        String[] expected2={"NL03", "Thịt heo", "50000", "a", "1"};
        instance.getSearch().setText(text1);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        instance.getSearch().setText(text2);
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        MyTable table=instance.getThanhToan();
        int k=0;
        for(int i=0;i<table.tb.getRowCount();i++)
        {
            for(int j=0;j<table.tb.getColumnCount();j++)
                if(String.valueOf(table.tbModel.getValueAt(i, j)).equals(expected1[j]) || String.valueOf(table.tbModel.getValueAt(i, j)).equals(expected2[j]))
                    k++;
            if(k==(expected1.length+expected2.length))
            {
                check=true;
                break;
            }
        }
        assertTrue("Thông tin ở bảng bên phải không đúng",check);
    }
    @Test
    public void testcaseID_21(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                String expected="Vui lòng chọn 1 hàng để xóa";
                assertEquals("Sai thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_22(){
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getThanhToan().tb.setRowSelectionInterval(0, 0);
                instance.getXoa().doClick();
                String expected="Bạn có chắc chắn xóa";
                assertEquals("Sai thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_23(){
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        int row=instance.getThanhToan().tbModel.getRowCount();
        instance.getThanhToan().tb.setRowSelectionInterval(0, 0);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                instance.getOp().setValue(JOptionPane.YES_OPTION);
                assertFalse("Không xóa dữ liệu trong bảng bên phải",row==instance.getThanhToan().tbModel.getRowCount());
            }
        });
    }
    @Test
    public void testcaseID_24(){
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        int row=instance.getThanhToan().tbModel.getRowCount();
        instance.getThanhToan().tb.setRowSelectionInterval(0, 0);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getXoa().doClick();
                instance.getOp().setValue(JOptionPane.NO_OPTION);
                assertTrue("Có xóa dữ liệu trong bảng bên phải",row==instance.getThanhToan().tbModel.getRowCount());
            }
        });
    }
    @Test
    public void testcaseID_25(){
        assertFalse("Có thể nhập được",instance.getMaHDN().isEditable());
    }
    @Test
    public void testcaseID_26(){
        assertFalse("Có thể nhập được",instance.getTongTien().isEditable());
    }
    @Test
    public void testcaseID_27(){
        assertFalse("Có thể nhập được",instance.getNgayNhap().isEditable());
    }
    @Test
    public void testcaseID_28(){
        assertFalse("Có thể nhập được",instance.getNhanVien().isEditable());
    }
    @Test
    public void testcaseID_29(){
        assertFalse("Có thể nhập được",instance.getNhaCungCap().isEditable());
    }
    @Test
    public void testcaseID_30(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getChonNhaCungCap().doClick();
                assertTrue("Không hiện Dialog chọn nhà cung cấp",instance.getFormchon().isShowing());
            }
        });
    }
    @Test
    public void testcaseID_31(){
        String expected="NCC1";
        instance.getChonNhaCungCap().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        assertEquals("formchon không đúng nhà cung cấp",expected, instance.getNhaCungCap().getText());
    }
    
    @Test
    public void testcaseID_32(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getBtnThanhToan().doClick();
                String expected="Vui lòng điền đầy đủ thông tin";
                assertEquals("Sai thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_33(){
        instance.getChonNhaCungCap().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance.getBtnThanhToan().doClick();
                String expected="Vui lòng chọn nguyên liệu";
                assertEquals("Sai thông điệp",expected,instance.getOp().getMessage());
            }
        });
    }
    @Test
    public void testcaseID_34(){
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        
        instance.getChonNhaCungCap().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        
        
        instance.getBtnThanhToan().doClick();
        assertEquals("Chưa clear TongTien","", instance.getTongTien().getText());
        assertEquals("Chưa clear NhaCungCap","", instance.getNhaCungCap().getText());
    }
    @Test
    public void testcaseID_35(){
        String MaHD=instance.getMaHDN().getText();
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        
        instance.getChonNhaCungCap().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        
        instance.getBtnThanhToan().doClick();
        assertEquals("Không tăng mã hóa đơn nhập",Tool.tangMa3(MaHD), instance.getMaHDN().getText());
    }
    @Test
    public void testcaseID_36(){
        int SoLuong= Integer.parseInt(String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 6)));
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        
        instance.getChonNhaCungCap().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        
        instance.getBtnThanhToan().doClick();
        assertEquals("Không tăng số lượng khi thanh toán",SoLuong+1, Integer.parseInt(String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 6))));
    }
    @Test
    public void testcaseID_37(){
        instance.getTable_NguyenLieu().tb.setRowSelectionInterval(0, 0);
        String id = String.valueOf(instance.getTable_NguyenLieu().tbModel.getValueAt(0, 0));
        if (id != null) {
            instance.showInfo(id);
        }
        instance.getThem().doClick();
        
        instance.getChonNhaCungCap().doClick();
        instance.getFormchon().getTable().tb.setRowSelectionInterval(0, 0);
        instance.getFormchon().getChon().doClick();
        
        instance.getBtnThanhToan().doClick();
        assertEquals("Không reset table thanh toán",0,instance.getThanhToan().tbModel.getRowCount());
    }
}

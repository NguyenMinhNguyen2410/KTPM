/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.CongThucBUS;
import BUS.MonAnBUS;
import BUS.Tool;
import DTO.CongThucDTO;
import EXT.FormChon;
import EXT.FormContent;
import EXT.MyTable;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Nguyen
 */
public class GUICongThuc extends FormContent{
    //Tạo mảng tiêu đề
    public static String[] header={"Mã công thức","Mã món ăn","Mô tả công thức"};  
    //Phần nhãn bên trong Dialog thêm sửa 
    private JLabel label_CongThuc[] = new JLabel[header.length];
    //Phần textfield của thêm
    private JTextField txt_CongThuc_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_CongThuc_Sua[] = new JTextField[header.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    //Tạo sẵn đối tượng BUS
    private final CongThucBUS BUS = new CongThucBUS();
    private JButton ThemMonAn,SuaMonAn;
    public GUICongThuc(){
        super();
    }
    //Hàm tạo Dialog thêm công thức
    @Override
    protected void Them_click() {
        super.Them_click();
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm công thức");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_CongThuc[i] = new JLabel(header[i]);
            label_CongThuc[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_CongThuc[i]);

            txt_CongThuc_Them[i]=new JTextField();
            txt_CongThuc_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            if(i==1)
            {
                ThemMonAn=new JButton();
                ThemMonAn.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
                ThemMonAn.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                ThemMonAn.setBounds(355, y, 30,30);
                Them_Frame.add(ThemMonAn);
                ThemMonAn.addActionListener((ActionEvent ae) -> {
                    //Tắt cờ hiệu đi
                    cohieu=1;
                    try {
                        formchon = new FormChon(txt_CongThuc_Them[1],"Món ăn");
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(GUIBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    formchon.setVisible(true);
                    formchon.addWindowListener(new WindowAdapter(){
                        @Override
                     public void windowClosed(WindowEvent e){
                            cohieu=0;
                            Them_Frame.setVisible(true);
                        }
 
                    });
                });
            }
            
            y += 40;
            Them_Frame.add(txt_CongThuc_Them[i]);
        }
        txt_CongThuc_Them[1].setEditable(false);
        String maNguyenLieu= Tool.tangMa(CongThucBUS.getMaMonAnCuoi());
        txt_CongThuc_Them[0].setText(maNguyenLieu);
        txt_CongThuc_Them[0].setEditable(false);
        Them_Frame.setVisible(true);

    }
    @Override
    protected void luuThem_Frame(){
        //Tắt cờ hiệu đi 
                cohieu=1;
                int a=op.showConfirmDialog(Them_Frame,"Bạn chắc chứ ?" ,"",JOptionPane.YES_NO_OPTION);
                if(a==JOptionPane.YES_OPTION)
                {
                    if(checkTextThem(txt_CongThuc_Them[1].getText(), txt_CongThuc_Them[2].getText()))
                    {
                    CongThucDTO DTO = new CongThucDTO(txt_CongThuc_Them[0].getText(),
                                                  txt_CongThuc_Them[1].getText(),
                                                  txt_CongThuc_Them[2].getText(),
                                                  "Hiện");
                    
                    BUS.them(DTO); //thêm công thức bên BUS đã có thêm vào database
                    table.addRow(DTO);                    
                    clearThem_Frame();
                    
                    Them_Frame.dispose();
                    }
                }
                else
                    cohieu=0;
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearThem_Frame(){
        //clear textfield trong Them
        for(int i=0;i<header.length;i++)
        {
            txt_CongThuc_Them[i].setText("");
        }
    }
    //Hàm tạo Dialog sửa món ăn
    @Override
    protected void Sua_click(){
        super.Sua_click();
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            op.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
        } else {
            //Tạo tiêu đề
            JLabel Title = new JLabel("Sửa công thức");
            Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
            Title.setForeground(Color.decode("#FF4081"));
            Title.setBounds(150, 0, 200, 40);
            Sua_Frame.add(Title);
            int y = 50;
            //Tạo tự động các lable và textfield
            for (int i = 0; i < header.length; i++) {
                label_CongThuc[i] = new JLabel(header[i]);
                label_CongThuc[i].setBounds(100, y, 100, 30);
                Sua_Frame.add(label_CongThuc[i]);
                txt_CongThuc_Sua[i] = new JTextField();
                txt_CongThuc_Sua[i].setBounds(200, y, 150, 30);
                if(i==1)
                {
                    SuaMonAn=new JButton();
                    SuaMonAn.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/xemchitiet-30.png")));
                    SuaMonAn.setBorder(BorderFactory.createLineBorder(Color.decode("#90CAF9"), 1));
                    SuaMonAn.setBounds(355, y, 30,30);
                    Sua_Frame.add(SuaMonAn);
                    SuaMonAn.addActionListener((ActionEvent ae) -> {
                        cohieu=1;
                        try {
                            formchon = new FormChon(txt_CongThuc_Them[1],"Món ăn");
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(GUIBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        formchon.setVisible(true);
                        formchon.addWindowListener(new WindowAdapter(){
                            @Override
                         public void windowClosed(WindowEvent e){
                                cohieu=0;
                                Sua_Frame.setVisible(true);
                            }

                        });
                    });
                }
                y += 40;
                Sua_Frame.add(txt_CongThuc_Sua[i]);
            }
            txt_CongThuc_Sua[0].setEditable(false);
            txt_CongThuc_Sua[1].setEditable(false);
            //Set tự động giá trị các field
            for(int j=0;j<header.length;j++)
                txt_CongThuc_Sua[j].setText(table.tb.getValueAt(row, j).toString());
            Sua_Frame.setVisible(true);
        }
    }
    @Override
    protected void luuSua_Frame(){
        //Tắt cờ hiệu đi 
                    cohieu=1;
                int a=op.showConfirmDialog(Sua_Frame,"Bạn chắc chứ ?" ,"",JOptionPane.YES_NO_OPTION);
                if(a == JOptionPane.YES_OPTION){
                    if (checkTextSua(
                                txt_CongThuc_Sua[1].getText(),
                                txt_CongThuc_Sua[2].getText()
                                )) {
                            //Chạy hàm để lưu lại việc sửa dữ liệu    
                            int row = table.tb.getSelectedRow();
        int colum = table.tb.getSelectedColumn();
        String maCongThuc = table.tbModel.getValueAt(row, colum).toString();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
//        int option = op.showConfirmDialog(Sua_Frame, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trên bảng
            //model là ruột JTable   
            //set tự động giá trị cho model
            for(int j=0;j<header.length;j++)
                table.tbModel.setValueAt(txt_CongThuc_Sua[j].getText(), row, j);
            
            table.tb.setModel(table.tbModel);

            
            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            CongThucDTO DTO = new CongThucDTO(txt_CongThuc_Sua[0].getText(),
                                                  txt_CongThuc_Sua[1].getText(),
                                                  txt_CongThuc_Sua[2].getText());
            //Tìm vị trí của row cần sửa
            int index = CongThucBUS.timViTri(maCongThuc);
            //Truyền dữ liệu và vị trí vào bus
            BUS.sua(DTO, index);
//        }
                            
                            //Lệnh này để tắt dialog
                            Sua_Frame.dispose();
                        }
                    
                }
                else
                    cohieu=0;
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearSua_Frame(){
        //clear textfield trong Them
        for(int i=0;i<header.length;i++)
        {
            txt_CongThuc_Sua[i].setText("");
        }
    }
    //Hàm sự kiện khi click vào nút xóa
    @Override
    protected void Xoa_click() {       
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            op.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
        } else {       
            int option = op.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                String maCongThuc = table.tbModel.getValueAt(row, 0).toString();
                //truyền mã công thức vào hàm timViTri ở CongThucBUS 
                int index = CongThucBUS.timViTri(maCongThuc);
                //Xóa hàng ở table
                table.tbModel.removeRow(row);
                //Xóa ở arraylist và đổi chế độ ẩn ở database
                BUS.xoa(maCongThuc, index);
            }
        }

    }
    @Override
    public void docDB() {
        table.setHeaders(header);
        if(CongThucBUS.ds == null) {
            try {
                BUS.docDB();
            } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GUICongThuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        
        for (CongThucDTO DTO : CongThucBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table.addRow(DTO);
                    
            }
        }
    }
    @Override
    protected JPanel TimKiem(){
        JPanel TimKiem=new JPanel(null);
        //Tạo nhãn tìm kiếm 
        JLabel lbsearch=new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x=400;
        //Tạo combobox cho người dùng chọn mục muốn search
        cbSearch = new JComboBox<>(header);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);
        
        search=new JTextField();
        //Set mặc định ở ô số 0 trong mảng header
        search.setBorder(new TitledBorder(header[0]));
        search.setBounds(155, 20, 150, 40);
        lbsearch.add(search);
        addDocumentListener(search);
        
        cbSearch.addActionListener((ActionEvent e) -> {
            //với mỗi giá trị của cbSearch thì sẽ set lại tiêu đề search
            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            search.requestFocus();           
        });
        lbsearch.setBounds(x, 0, 315, 70);
        TimKiem.add(lbsearch);
        
        return TimKiem;
    }
    @Override
    protected void ChiTiet_click(){
                int i=table.tb.getSelectedRow();
                if (i == -1) {
                    op.showMessageDialog(null, "Vui lòng chọn 1 công thức");
                    return;
                } 
                String MaCongThuc=String.valueOf(table.tbModel.getValueAt(i,0));
                try {
                    formchon = new FormChon("Chi tiết công thức",MaCongThuc);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(GUIBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
    }
    private void addDocumentListener(JTextField tx) { // để cho hàm tìm kiếm
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        tx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txtSearchOnChange();
            }
        });
    }
    public void txtSearchOnChange() {
        table.clear();
        ArrayList<CongThucDTO> arraylist=Tool.searchCT(search.getText(),cbSearch.getSelectedItem().toString() );
        for (CongThucDTO DTO : arraylist) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table.addRow(DTO);
                    
            }
        }
    }
    
    public boolean checkTextThem(String MaMonAn, String moTaCongThuc) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (MaMonAn.equals("")
                || moTaCongThuc.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }
//        else if(!MonAnBUS.timMaMonAn(MaMonAn)) {
//            op.showMessageDialog(null, "Mã món ăn không tồn tại");
//            txt_CongThuc_Them[1].requestFocus();
//        }
        else if (!Tool.isCongThuc(Tool.removeAccent(moTaCongThuc))) {
            op.showMessageDialog(null, "Mô tả công thức không được chứa ký tự đặc biệt");
            txt_CongThuc_Them[2].requestFocus();
        }
        else if (!Tool.isLength50(moTaCongThuc)) {
            op.showMessageDialog(null, "Mô tả công thức không được quá 50 ký tự");
            txt_CongThuc_Them[2].requestFocus();
        }
         else {
            return true;

        }
        return false;
    }
    
    public boolean checkTextSua(String MaMonAn, String moTaCongThuc) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (MaMonAn.equals("")
                || moTaCongThuc.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }
//        else if(!MonAnBUS.timMaMonAn(MaMonAn)) {
//            op.showMessageDialog(null, "Mã món ăn không tồn tại");
//            txt_CongThuc_Them[1].requestFocus();
//        }
        else if (!Tool.isCongThuc(Tool.removeAccent(moTaCongThuc))) {
            op.showMessageDialog(null, "Mô tả công thức không được chứa ký tự đặc biệt");
            txt_CongThuc_Sua[2].requestFocus();
        }
        else if (!Tool.isLength50(moTaCongThuc)) {
            op.showMessageDialog(null, "Mô tả công thức không được quá 50 ký tự");
            txt_CongThuc_Sua[2].requestFocus();
        }
         else {
            return true;

        }
        return false;
    }
    
    @Override
    protected void XuatExcel_click(){
        new XuatExcel().xuatFileExcelCongThuc();
    }
    @Override
    protected void NhapExcel_click(){
        new DocExcel().docFileExcelCongThuc();
    }
    @Override
    protected void LamMoi_click(){
        super.LamMoi_click();
        search.setText("");
    }
    @Override
    protected void InPDF(){
        
    }

    public JTextField getSearch() {
        return search;
    }

    public JComboBox getCbSearch() {
        return cbSearch;
    }
    
}























































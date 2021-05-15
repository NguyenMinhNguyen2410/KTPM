/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.PhanQuyenBUS;
import BUS.Tool;
import BUS.TaiKhoanBUS;
import DTO.PhanQuyenDTO;
import DTO.TaiKhoanDTO;
import EXT.FormContent;
import EXT.MyTable;
import Excel.DocExcel;
import Excel.XuatExcel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.lang.System.Logger;
//import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class GUIPhanQuyen extends FormContent{
    public static String[] header={"Mã quyền","Tên quyền","Chọn quyền"};
    public String arrString_Quyen[]={"Quản lý bán hàng","Quản lý nhập hàng",
        "Quản lý món ăn","Quản lý nguyên liệu",
        "Quản lý công thức","Quản lý hóa đơn",
        "Quản lý hóa đơn nhập","Quản lý khuyến mãi",
        "Quản lý khách hàng","Quản lý nhân viên",
        "Quản lý nhà cung cấp","Quản lý tài khoản",
        "Quản lý phân quyền","Quản lý thống kê"};
    private String arr_listmotaQuyen[]={"QLBanHang","QLNhapHang","QLMonAn","QLNguyenLieu","QLCongThuc","QLHoaDon","QLHDNhap","QLKhuyenMai",
        "QLKhachHang","QLNhanVien","QLNhaCungCap","QLTaiKhoan","QLPhanQuyen","QLThongKe"};
    
    private final JLabel label_PhanQuyen[]=new JLabel[header.length];
    private JTextField txt_PhanQuyen_Them[] = new JTextField[header.length];
    //Phần textfield của sửa
    private JTextField txt_PhanQuyen_Sua[] = new JTextField[header.length];
    //Phần textfield để tìm kiếm
    private JTextField search;
    //Combobox để chọn thuộc tính muốn tìm
    private JComboBox cbSearch;
    
    private JCheckBox cbPhanQuyen = new JCheckBox();
    private JComboBox ccbPhanQuyen;
    private boolean checkQuyen[]=new boolean[arrString_Quyen.length];
    //Tạo sẵn đối tượng BUS
    private final PhanQuyenBUS BUS = new PhanQuyenBUS();
    public GUIPhanQuyen(){
        super();
    }
    
    //Hàm tạo Dialog thêm công thức
    @Override
    protected void Them_click() {
        super.Them_click();
        //Tạo tiêu đề và set hình thức
        JLabel Title = new JLabel("Thêm phân quyền");
        Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
        Title.setForeground(Color.decode("#FF4081"));
        Title.setBounds(150, 0, 200, 40);
        Them_Frame.add(Title);
        int y = 50;
        //Tạo tự động các label và textfield
        for (int i = 0; i < header.length; i++) {
            label_PhanQuyen[i] = new JLabel(header[i]);
            label_PhanQuyen[i].setBounds(100, y, 100, 30);
            Them_Frame.add(label_PhanQuyen[i]);
            if(i==2)
            {
                ccbPhanQuyen=new JComboBox(arrString_Quyen);
                ccbPhanQuyen.setBounds(200, y, 150, 30);
                ccbPhanQuyen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if(checkQuyen[ccbPhanQuyen.getSelectedIndex()])
                        {
                            cbPhanQuyen.setSelected(true);
                        }
                        else
                        {
                            cbPhanQuyen.setSelected(false);
                        }
                    }
                });
                Them_Frame.add(ccbPhanQuyen);
                
                cbPhanQuyen.setBounds(360, y, 150, 30);
                cbPhanQuyen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        JCheckBox cb = (JCheckBox) event.getSource();
                        if (cb.isSelected()) {
                            checkQuyen[ccbPhanQuyen.getSelectedIndex()]=true;
                        } else {
                            checkQuyen[ccbPhanQuyen.getSelectedIndex()]=false;
                        }
                    }
                });
                Them_Frame.add(cbPhanQuyen);
                continue;
            }
            txt_PhanQuyen_Them[i]=new JTextField();
            txt_PhanQuyen_Them[i].setBounds(200, y, 150, 30);
            //Tạo nút để lấy tên ảnh 
            
            y += 40;
            Them_Frame.add(txt_PhanQuyen_Them[i]);
        }      
        String ma = Tool.tangMa(PhanQuyenBUS.getMaPhanQuyenCuoi()); //tăng mã
        txt_PhanQuyen_Them[0].setText(ma); //set mã
        txt_PhanQuyen_Them[0].setEditable(false);
        Them_Frame.setVisible(true);
    }
    @Override
    protected void luuThem_Frame(){
        cohieu = 1;
                int a=op.showConfirmDialog( Them_Frame,"Bạn chắc chứ ?" ,"",JOptionPane.YES_NO_OPTION);
                if(a==JOptionPane.YES_OPTION)
                {
                    PhanQuyenDTO DTO = new PhanQuyenDTO();
                    DTO.setIDPhanQuyen(txt_PhanQuyen_Them[0].getText());
                    DTO.setTenQuyen(txt_PhanQuyen_Them[1].getText());
                    DTO.setMoTaQuyen(layMoTaTuCheckBox());
                    BUS.them(DTO);
                    table.addRow(DTO);                    
                    clearThem_Frame();
                    Them_Frame.dispose();
                }else
                    cohieu = 0;
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearThem_Frame(){
        //clear textfield trong Them
        for(int i=0;i<header.length;i++)
        {
            if(i!=2)
                txt_PhanQuyen_Them[i].setText("");
        }
        for(int i=0;i<checkQuyen.length;i++)
            checkQuyen[i]=false;
    }
    //Hàm tạo Dialog sửa món ăn
    @Override
    protected void Sua_click() {
        super.Sua_click();
        int row = table.tb.getSelectedRow();
        if (row == -1) {
            op.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
            
        } else {
            //Tạo tiêu đề
            JLabel Title = new JLabel("Sửa phân quyền");
            Title.setFont(new Font("Time New Roman", Font.BOLD, 21));
            Title.setForeground(Color.decode("#FF4081"));
            Title.setBounds(150, 0, 200, 40);
            Sua_Frame.add(Title);
            int y = 50;
            //Tạo tự động các lable và textfield
            for (int i = 0; i < header.length; i++) {
                label_PhanQuyen[i] = new JLabel(header[i]);
                label_PhanQuyen[i].setBounds(100, y, 100, 30);
                Sua_Frame.add(label_PhanQuyen[i]);
                if(i==2)
                {
                    ccbPhanQuyen=new JComboBox(arrString_Quyen);
                    ccbPhanQuyen.setBounds(200, y, 150, 30);
                    ccbPhanQuyen.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            if(checkQuyen[ccbPhanQuyen.getSelectedIndex()])
                            {
                                cbPhanQuyen.setSelected(true);
                            }
                            else
                            {
                                cbPhanQuyen.setSelected(false);
                            }
                        }
                    });
                    Sua_Frame.add(ccbPhanQuyen);

                    cbPhanQuyen.setBounds(360, y, 150, 30);
                    cbPhanQuyen.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            JCheckBox cb = (JCheckBox) event.getSource();
                            if (cb.isSelected()) {
                                checkQuyen[ccbPhanQuyen.getSelectedIndex()]=true;
                            } else {
                                checkQuyen[ccbPhanQuyen.getSelectedIndex()]=false;
                            }
                        }
                    });
                    Sua_Frame.add(cbPhanQuyen);
                    continue;
                }
                txt_PhanQuyen_Sua[i] = new JTextField();
                txt_PhanQuyen_Sua[i].setBounds(200, y, 150, 30);

                y += 40;
                Sua_Frame.add(txt_PhanQuyen_Sua[i]);
            }
            txt_PhanQuyen_Sua[0].setEditable(false);
            //Set tự động giá trị các field
            for (int j = 0; j < header.length-1; j++) 
                if(row!=2)
                    txt_PhanQuyen_Sua[j].setText(table.tb.getValueAt(row, j).toString());
            String MoTaQuyen=table.tb.getValueAt(row, 2).toString();
            for(int j=0;j<checkQuyen.length;j++)
                if(MoTaQuyen.indexOf(arr_listmotaQuyen[j])!=-1)
                {
                    checkQuyen[j]=true;
                }
                else
                {
                    checkQuyen[j]=false;
                }
            if(checkQuyen[0]==true)
                cbPhanQuyen.setSelected(true);
            Sua_Frame.setVisible(true);
            
        }
    }
    @Override
    protected void luuSua_Frame(){
        int row = table.tb.getSelectedRow();
        //Hỏi để xác nhận việc lưu dữ liệu đã sửa chữa
        int option = op.showConfirmDialog(Sua_Frame, "Bạn chắc chắn sửa?", "", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            //Sửa dữ liệu trong database và arraylist trên bus
            //Tạo đối tượng monAnDTO và truyền dữ liệu trực tiếp thông qua constructor
            PhanQuyenDTO DTO = new PhanQuyenDTO();
            DTO.setIDPhanQuyen(txt_PhanQuyen_Sua[0].getText());
            DTO.setTenQuyen(txt_PhanQuyen_Sua[1].getText());
            DTO.setMoTaQuyen(layMoTaTuCheckBox());
            //Truyền dữ liệu và vị trí vào bus
            BUS.sua(DTO, row);
            for(int i=0;i<header.length;i++)
                if(i!=2)
                    table.tbModel.setValueAt(txt_PhanQuyen_Sua[i].getText(), row, i);
                else
                    table.tbModel.setValueAt(DTO.getMoTaQuyen(), row, i);
            PhanQuyenBUS.ds.set(row, DTO);
            
        }
    }
    //Tạo hàm này dùng để clear các textfield trong Them_Frame
    @Override
    protected void clearSua_Frame(){
        //clear textfield trong Them
        for(int i=0;i<header.length;i++)
        {
            if(i!=2)
                txt_PhanQuyen_Sua[i].setText("");
        }
        for(int i=0;i<checkQuyen.length;i++)
            checkQuyen[i]=false;
        cbPhanQuyen.setSelected(false);
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
                if(checkXoaPhanQuyen(table.tb.getValueAt(row, 0).toString()))
                {
                   op.showMessageDialog(null, "Vui lòng xóa tài khoản chứa quyền này trước khi xóa");
                }
                else
                {
                    String maPhanQuyen = table.tbModel.getValueAt(row, 0).toString();
                    //truyền mã công thức vào hàm timViTri ở PhanQuyenBUS 
                    int index = PhanQuyenBUS.timViTri(maPhanQuyen);
                    //Xóa hàng ở table
                    table.tbModel.removeRow(row);
                    //Xóa ở arraylist và đổi chế độ ẩn ở database
                    BUS.xoa(maPhanQuyen, index);
                }
                
            }
        }

    }
    @Override
    public void docDB() {
        table.setHeaders(new String[]{"Mã quyền","Tên quyền"});
        if(PhanQuyenBUS.ds == null) {
            
            try {
                BUS.docDB();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(GUIPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
        }
        
        for (PhanQuyenDTO DTO : PhanQuyenBUS.ds) {
            if (DTO.getTrangThai().equals("Hiện")) {
                table.addRow(DTO);
                    
            }
        }
    }
    @Override
    protected JPanel TimKiem(){
        JPanel TimKiem=new JPanel(null);
        
        
        JLabel lbsearch=new JLabel("");
        lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
        int x=400;
        cbSearch = new JComboBox<>(header);
        cbSearch.setBounds(5, 20, 150, 40);
        lbsearch.add(cbSearch);
        
        search=new JTextField();
        search.setBorder(new TitledBorder(header[0]));
        search.setBounds(155, 20, 150, 40);
        lbsearch.add(search);
        addDocumentListener(search);

        cbSearch.addActionListener((ActionEvent e) -> {
            search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
            search.requestFocus();
        });
        lbsearch.setBounds(x, 0, 315, 70);
        TimKiem.add(lbsearch);       
        
        return TimKiem;
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
        //Đẩy dữ liệu đi và nhận lại danh sách đúng với field tìm kiếm
        setDataToTable(Tool.searchPQ(search.getText(),cbSearch.getSelectedItem().toString()), table); //chưa sửa xong hỏi Nguyên cái Textfield
    }

    private void setDataToTable(ArrayList<PhanQuyenDTO> phanQuyenDTO, MyTable myTable) {
        myTable.clear();
        for (PhanQuyenDTO phanQuyen : phanQuyenDTO) {
            table.addRow(phanQuyen);
        }
    }
    
    public boolean checkTextThem(String tenQuyen) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenQuyen.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }else if(!checkCoChonQuyen())
        {
            op.showMessageDialog(null, "Vui lòng chọn ít nhất một quyền");
        }
        else if (!Tool.isName(Tool.removeAccent(tenQuyen))) {
            op.showMessageDialog(null, "Tên quyền không được chứa ký tự đặc biệt");
            txt_PhanQuyen_Them[1].requestFocus();
        }
        else if (!Tool.isLength50(tenQuyen)) {
            op.showMessageDialog(null, "Tên quyền không được quá 50 ký tự");
            txt_PhanQuyen_Them[1].requestFocus();
        }
         else {
            return true;

        }
        return false;
    }
    
    public boolean checkTextSua(String tenQuyen) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
        if (tenQuyen.equals("")) {
            op.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
        }else if(!checkCoChonQuyen())
        {
            op.showMessageDialog(null, "Vui lòng chọn ít nhất một quyền");
        }
        else if (!Tool.isName(Tool.removeAccent(tenQuyen))) {
            op.showMessageDialog(null, "Tên quyền không được chứa ký tự đặc biệt");
            txt_PhanQuyen_Sua[1].requestFocus();
        }
        else if (!Tool.isLength50(tenQuyen)) {
            op.showMessageDialog(null, "Tên quyền không được quá 50 ký tự");
            txt_PhanQuyen_Sua[1].requestFocus();
        }
         else {
            return true;

        }
        return false;
    }
    @Override
    protected void XuatExcel_click() {
        new XuatExcel().xuatFileExcelPhanQuyen();

    }

    @Override
    protected void NhapExcel_click() {
        new DocExcel().docFileExcelNhanVien();

    }
    
    public String layMoTaTuCheckBox()
    {   
        String moTaQuyen = "";
        for(int i=0;i<checkQuyen.length;i++)
        {
            if(checkQuyen[i])
                moTaQuyen+=arr_listmotaQuyen[i];
        }
        return moTaQuyen;
    }
    
    public boolean checkCoChonQuyen()
    {
        for(int i=0;i<checkQuyen.length;i++){
            if(checkQuyen[i])
                return true;
        }
        return false;
    }
    
    public boolean checkXoaPhanQuyen(String maQuyen)//kiểm tra xem mã quyền truyền vào có tồn tại trong mảng tài khoản không nếu có thì
            //return true để thông báo không được xóa
            //return false để xóa được
    {
        for(TaiKhoanDTO taiKhoanDTO : TaiKhoanBUS.ds)
        {
            if(taiKhoanDTO.getIDPhanQuyen().equals(maQuyen))
            {
                return true; //không được xóa
            }
            else if(maQuyen.equals("PQ0") || maQuyen.equals("PQ1") || maQuyen.equals("PQ4"))
            {
                return true;
            }
        }
        return false;// xóa được
                
    }
    @Override
    protected void ChiTiet(){
        
    }
    @Override
    protected void InPDF(){
        
    }
    @Override
    protected void LamMoi_click(){
        super.LamMoi_click();
        search.setText("");
    }

    public JTextField getSearch() {
        return search;
    }

    public JComboBox getCbSearch() {
        return cbSearch;
    }
    
}


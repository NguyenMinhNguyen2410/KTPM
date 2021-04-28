package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectDB {
    private  String hostName = "localhost";
    private  String dbName = "banthucannhanh";
    private  String userName = "root";
    private  String password = "";
    private Connection connect;
    Statement st = null;
    ResultSet rs = null;
   
    private String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=yes&characterEncoding=UTF-8";
   
    public ConnectDB()
    {
    }
    // sử dụng lớp 
    public Connection getConnect() 
    {
	connect = null;
	try {
                connect =  (Connection) DriverManager.getConnection(connectionURL, userName, password);
            } catch (SQLException er) 
              {
                    System.out.println(er.toString());
              }
	return (Connection) connect;
    
    }

     public Statement getStatement() throws Exception{
        try{
            if(this.st==null?true:this.st.isClosed()){
                this.st=this.getConnect().createStatement();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
    }
        return this.st;
    }
     public ResultSet excuteQuery(String qry) throws Exception{
        try{
            this.rs = this.getStatement().executeQuery(qry);
        } catch (Exception ex){
            throw new Exception("Error: "+ex.getMessage()+"-"+qry);
    }
        return this.rs;
    }
    public int ExecuteUpdate(String qry) throws Exception{
        int res =0;
        try{
            res = this.getStatement().executeUpdate(qry);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
     public void closeConnect() throws SQLException{
        if(this.rs!=null && !this.rs.isClosed()){
        this.rs.close();
        this.rs=null;
    }
        if(this.st!=null && !this.st.isClosed()){
        this.st.close();
        this.st=null;
    }
        if(this.connect!=null && !this.connect.isClosed()){
        this.connect.close();
        this.connect=null;
    }
    }

         
}






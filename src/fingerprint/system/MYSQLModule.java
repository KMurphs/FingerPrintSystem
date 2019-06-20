/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stephane.kibonge
 */
public class MYSQLModule implements IDatabase {
    private String dbIPAddress;
    private String dbUsername;
    private String dbPassword;
    private String dbName;
    private Connection dbCon;
    
    private String _chkstmt = "SELECT IF(COUNT(`FingerPrint`) > 0, CONCAT(`Surname`, \" \", `Name`), 'Unregistered User') as User from `jrfingerprintproject`.`persons` where `FingerPrint` = ?;";
    private String _insstmt = "INSERT INTO `jrfingerprintproject`.`persons` (`Name`, `Surname`, `FingerPrint`, `IDNumber`, `DateofBirth`) VALUES (?, ?, ?, ?, Now());";    
    
    @Override
    public String[] processCmd(String cmd, String data){
        String response = "";
        
        try {
            //Class.forName("com.mysql.jdbc.Driver"); 
            dbCon = DriverManager.getConnection("jdbc:mysql://" + dbIPAddress + ":3306/" + dbName, dbUsername, dbPassword);  
             
            PreparedStatement chkstmt = null;  
            PreparedStatement insstmt = null;  
            ResultSet rs;
            
            switch(cmd){
                case "getAll":
                    Statement stmt = dbCon.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM jrfingerprintproject.persons");  
                    while(rs.next()){
                        response = rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6);
                        //System.out.println(response);                        
                    }
                    break;
                    
                case "check":
                    chkstmt = dbCon.prepareStatement(_chkstmt);
                    chkstmt.setString(1, data);
                    rs = chkstmt.executeQuery();
                    while(rs.next()){
                        response = rs.getString(1);
                        //System.out.println(response);                         
                    }
                    break;
                    
                    
                case "insert":
                    insstmt = dbCon.prepareStatement(_insstmt);  
                    String[] details = ParserConcatenator.Parser(data, ";");
                    insstmt.setString(1, details[0]);
                    insstmt.setString(2, details[1]);
                    insstmt.setString(3, details[2]);
                    insstmt.setString(4, details[3]);
                    insstmt.executeUpdate();
                    
                    chkstmt = dbCon.prepareStatement(_chkstmt);
                    chkstmt.setString(1, details[2]);
                    rs = chkstmt.executeQuery();
                    while(rs.next()){
                        response = rs.getString(1);
                        System.out.println(response);                         
                    }
                    break;
                    
                default:
                    System.out.println("mysqlModule received unsupported command: " + cmd + "::" + data);
                    response = "Unsupported Command";
            }
            
            dbCon.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new String[]{cmd, response};
    }
   
    MYSQLModule(String thread_name, String ipAddress, String userName, String password, String name){
        dbIPAddress = ipAddress;
        dbUsername = userName;
        dbPassword = password;
        dbName = name;
    }  
}

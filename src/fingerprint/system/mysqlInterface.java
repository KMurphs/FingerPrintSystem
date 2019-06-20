/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

/**
 *
 * @author stephane.kibonge
 */
public class mysqlInterface implements IDatabase {
   
    private String dbIPAddress;
    private String dbUsername;
    private String dbPassword;
    private String dbName;
  
    
    public String[] processCmd(String cmd, String data){
        return new String[]{"Good", "Better"};
    }
   
    mysqlInterface(String thread_name, String ipAddress, String userName, String password, String name) {
        dbIPAddress = ipAddress;
        dbUsername = userName;
        dbPassword = password;
        dbName = name;
    }
}

    


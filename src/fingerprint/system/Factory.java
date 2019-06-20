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
public class Factory {
    public static class Create{
        public static IDatabase getDBInterface(String thread_name, String ipAddress, String userName, String password, String name){
            return new mysqlInterface(thread_name, ipAddress, userName, password, name);
        }        
    }
}

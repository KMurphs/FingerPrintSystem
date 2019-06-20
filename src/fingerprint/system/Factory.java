/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 *
 * @author stephane.kibonge
 */
public class Factory {
    public static class Create{
        
        public static IDatabase getDBInterface(String thread_name, String ipAddress, String userName, String password, String name){
            return new MYSQLModule(thread_name, ipAddress, userName, password, name);
        } 
        
        public static IProcessLog getProcessLogger(String logfilepath){
            return new ProcessLogger();
        }
        
        public static BlockingQueue<String> getQ(){
            return new LinkedBlockingQueue<String>();
        }
        
    }
}

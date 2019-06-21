/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stephane.kibonge
 */
public class ConfigFilesContainer {
    public static Dictionary configData;
    public static Dictionary configData_Default;
    public static String configFilePath;
    
    public static class ConfigFiles{
        public static void writeFile(String someConfigFilePath, Dictionary data){
            configData = data;
            configFilePath = someConfigFilePath;
            Properties prop;

            try {
                //set the properties value
                prop = (Properties) configData;

                //save properties to project root folder
                prop.store(new FileOutputStream(configFilePath), null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        public static void writeFile(Dictionary data){
            writeFile(configFilePath, data);
        }
        
        
        public static Dictionary readFile(String someConfigFilePath){
            configData = getDefaults();
            configFilePath = someConfigFilePath;
            
            File fs = new File(configFilePath);
            
            try {  
                if(fs.isFile()){
                    FileReader reader = new FileReader(configFilePath);
                    Properties p = new Properties();  
                    p.load(reader); 
                    
                    configData = p;
                    
                } else {
                    writeFile(configFilePath, configData);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConfigFilesContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConfigFilesContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return configData;
        }
        
        public static Dictionary readFile(){
            return readFile(configFilePath);
        }
        
        public static Dictionary getDefaults(){
            
            configData_Default = new Properties(); 
            
            configData_Default.put("dbIPAddress", "127.0.0.1");
            configData_Default.put("dbName", "jrfingerprintproject");
            configData_Default.put("dbUser", "tester");
            configData_Default.put("dbPassword", "tester321!");
            
            return configData_Default;
        }  
        
        public static Dictionary getData(){ 
            return configData;
        }  
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author stephane.kibonge
 */
public class Config{
    public static class Files{

    }
        public void writeConfig(){

            Properties prop = new Properties();

            try {
                //set the properties value
                prop.setProperty("database", "localhost");
                prop.setProperty("dbuser", "myuser");
                prop.setProperty("dbpassword", "mypwd");

                //save properties to project root folder
                prop.store(new FileOutputStream("c://test//config.properties"), null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        public Properties configFile;
        public void readConfig()
        {
            configFile = new java.util.Properties();
            try {
                configFile.load(getClass().getClassLoader().getResourceAsStream("c://test//config.properties"));
            }catch(Exception eta){
                eta.printStackTrace();
            }
        }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.io.File;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author stephane.kibonge
 */
public class ProjectFoldersContainer {
    public static class ProjectFolders{
        public static Dictionary makeProjectFolders_byName(String projectname){
            String temp = projectname;
            if ("".equals(temp)) {
               temp = "undefined";
            }
            return makeProjectFolders_byPath(Paths.get(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(), temp).toString());           
        }
        
        public static Dictionary makeProjectFolders_byPath(String projectfolderpath){
            String temp = projectfolderpath;
            File fs;
            if  ("".equals(temp))  {
                temp = Paths.get(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(), "undefined").toString();
            }
            
            Dictionary folders = new Hashtable(); 
            folders.put("project", temp);
            folders.put("data", Paths.get(temp, "data").toString());
            folders.put("config", Paths.get(temp, "config").toString());
            folders.put("documents", Paths.get(temp, "documents").toString());
            
            fs = new File((String) folders.get("project"));
            if (!fs.isDirectory()) {
                fs.mkdir();
            }
            fs = new File((String) folders.get("data"));
            if (!fs.isDirectory()) {
                fs.mkdir();
            }
            fs = new File((String) folders.get("config"));
            if (!fs.isDirectory()) {
                fs.mkdir();
            }
            fs = new File((String) folders.get("documents"));
            if (!fs.isDirectory()) {
                fs.mkdir();
            }     
            
            return folders;
        }
    }   
}

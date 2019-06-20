/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.util.ArrayList;

/**
 *
 * @author stephane.kibonge
 */
public class ProcessLogger implements IProcessLog, IObtainableLogs{
    private ArrayList<String>logs;
    
    ProcessLogger(){
        logs = new ArrayList<String>();
    }
    
    public void Log(String logitem){
        logs.add(logitem);
        System.out.println(logitem);
    }
    
    public ArrayList<String> getLogs(){
        return logs;
    }
}

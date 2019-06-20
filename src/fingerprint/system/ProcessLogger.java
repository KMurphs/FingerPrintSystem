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
public class ProcessLogger implements IProcessLog{
    ProcessLogger(){
        
    }
    
    public void Log(String logitem){
        System.out.println(logitem);
    }
}

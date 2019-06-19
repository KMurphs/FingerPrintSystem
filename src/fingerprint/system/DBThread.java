/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import fingerprint.system.ParserConcatenator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author stephane.kibonge
 */
public class DBThread  extends Thread {
    private Thread t;
    private String threadName;
   
    private String dbIPAddress;
    private String dbUsername;
    private String dbPassword;
    private String dbName;
   
    public static BlockingQueue<String> q;
    public static BlockingQueue<String> getQ() {
        return q;
    }
   
    DBThread(String thread_name, String ipAddress, String userName, String password, String name) {
        threadName = thread_name;
        dbIPAddress = ipAddress;
        dbUsername = userName;
        dbPassword = password;
        dbName = name;
        
        q = new LinkedBlockingQueue<String>();
        

        System.out.println("Creating " +  threadName );
    }
   
    public void run() {
        System.out.println("Running " +  threadName );
        String cmd = "";
        String data = "";
        try {
            while(!"exit".equals(cmd)){
                //Dequeue msg, parse, and extract command and data (cmd, data)
                String[] msg = ParserConcatenator.Parser(q.poll(100, TimeUnit.MILLISECONDS)); 
                cmd = msg[0];
                if(msg.length > 1){
                    data = msg[1];
                }
                System.out.println(threadName + "Received Msg: " + cmd + "  -  " + data);
                
                //Handle msg cmd
                switch(cmd){
                    case "":
                        break;
                    case "exit":
                        break;
                    default:
                        System.out.println(threadName + ": Unhandled Msg: " + cmd + "  -  " + data);
                }
                
            }
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
    }
   
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

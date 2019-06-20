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
public class ServerThread extends Thread {
    private Thread t;
    private String threadName;
   
    private String serverPort;
   
    public static BlockingQueue<String> q;
    public static BlockingQueue<String> getQ() {
        return q;
    }
   
    ServerThread(String thread_name, String port) {
        threadName = thread_name;
        serverPort = port;
        
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
                String[] msg = ParserConcatenator.Parser(q.poll(10, TimeUnit.MILLISECONDS)); 
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
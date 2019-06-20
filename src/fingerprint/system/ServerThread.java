/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stephane.kibonge
 */
public class ServerThread extends Thread {
    private Thread t;
    private final String threadName;
    private final int serverPort;
    private IDatabase dbOject;
   
    
    ServerThread(String thread_name, String port, IDatabase someDBbOject) {
        threadName = thread_name;
        serverPort = Integer.parseInt(port);
        dbOject = someDBbOject;
        
        System.out.println("Creating " +  threadName );
    }
   
    
    public void run() {
        System.out.println("Running " +  threadName );
        System.out.println(" Server is Running  " );

        
        String cmd = "";
        String data = "";
        try {
            ServerSocket mysocket = new ServerSocket(serverPort);

            while(!"exit".equals(cmd))
            {
                Socket connectionSocket = mysocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

                writer.write("*** Welcome to the FingerPrint Server ***\r\n");            
                writer.flush();
                String[] msg = ParserConcatenator.Parser(reader.readLine().trim());
                cmd = msg[0];
                if(msg.length == 1){
                    data = "";
                }else{
                    data = msg[1];
                }
                
                String[] result = dbOject.processCmd(cmd, data);
        
                System.out.println(ParserConcatenator.Concatenator(result));
                writer.write("\r\n" + ParserConcatenator.Concatenator(result));
                writer.flush();
                connectionSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
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



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.io.*;
import java.net.*;
import java.util.concurrent.BlockingQueue;
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
    private IProcessLog logObject;
    private BlockingQueue<String> controllerQ;
    
    ServerThread(String thread_name, String port, IDatabase someDBbOject, IProcessLog someLogger, BlockingQueue<String> someControllerQ) {
        threadName = thread_name;
        serverPort = Integer.parseInt(port);
        dbOject = someDBbOject;
        logObject = someLogger;
        controllerQ = someControllerQ;
        
        logObject.Log("Creating " +  threadName);
    }
   
    
    public void run() {
        logObject.Log("Running " +  threadName);
        logObject.Log("Server is Running");

        
        String cmd = "";
        String data = "";
        String tcpResponse = "";
        try {
            ServerSocket mysocket = new ServerSocket(serverPort);

            while(!"exit".equals(cmd))
            {
                Socket connectionSocket = mysocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

                //writer.write("*** Welcome to the FingerPrint Server ***\r\n");            
                //writer.flush();
                
                String tcpmsg = reader.readLine().trim();
                logObject.Log("Server Received: " + tcpmsg);
                
                String[] msg = ParserConcatenator.Parser(tcpmsg);
                cmd = msg[0];
                if(msg.length == 1){
                    data = "";
                }else{
                    data = msg[1];
                }
                
                if("exit".equals(cmd)){
                    tcpResponse = "Server Thread is Exiting";
                }else if("upload".equals(cmd)){
                    controllerQ.put(tcpmsg);
                    tcpResponse = "Upload is being Processed";
                }else{
                    tcpResponse = ParserConcatenator.Concatenator(dbOject.processCmd(cmd, data));
                }                
                
                logObject.Log("Server Responded with: " + tcpResponse + "\n\n\n");
                
                writer.write("\r\n" + tcpResponse);
                writer.flush();
                
                connectionSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        logObject.Log(threadName + " exiting.");
    }
   
   
    public void start () {
        logObject.Log("Starting " +  threadName);
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}



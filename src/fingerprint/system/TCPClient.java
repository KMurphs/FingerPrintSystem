/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author stephane.kibonge
 */
public class TCPClient {
    public static class Client{
        private static String serverIPAddress;
        private static int serverPort;
        private static IProcessLog logObject;
        

        public static void Init(String ip, String port, IProcessLog someProcessLogObject){
            serverIPAddress = ip;
            serverPort = Integer.parseInt(port);
            logObject = someProcessLogObject;
        }

        public static String sendMsg(String msg){
            String serverMsg = "";

            try{
                Socket socketClient= new Socket(serverIPAddress, serverPort);
                logObject.Log("Client Connection Established");
                logObject.Log("Client Sending Message: " + msg);

                BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

                writer.write(msg + "\r\n");
                writer.flush();
                while((serverMsg = reader.readLine()) != null){
                    if(!"".equals(serverMsg)){
                        logObject.Log("Client: Received Response: " + serverMsg);
                    } 
                }

            }catch(IOException e){
                e.printStackTrace();
            }

            return serverMsg;
        }       
    }
}

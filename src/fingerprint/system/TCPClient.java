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

        public static void Init(String ip, String port){
            serverIPAddress = ip;
            serverPort = Integer.parseInt(port);
        }

        public static String sendMsg(String msg){
            String serverMsg = "";

            try{
                Socket socketClient= new Socket(serverIPAddress, serverPort);
                System.out.println("Client: " + "Connection Established");

                BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

                writer.write(msg + "\r\n");
                writer.flush();
                while((serverMsg = reader.readLine()) != null){
                   System.out.println("Client: " + serverMsg);
                }

            }catch(IOException e){
                e.printStackTrace();
            }

            return serverMsg;
        }       
    }
}

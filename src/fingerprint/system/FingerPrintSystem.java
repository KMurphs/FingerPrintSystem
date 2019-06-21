/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import fingerprint.system.ParserConcatenator;
import java.nio.file.Paths;
import java.util.Dictionary;


/**
 *
 * @author stephane.kibonge
 */
public class FingerPrintSystem extends Application {
    public static BlockingQueue<String> controllerQ;
    @Override
    public void start(Stage stage) throws Exception {
        //Reading Configuration File
        Dictionary folders = ProjectFoldersContainer.ProjectFolders.makeProjectFolders_byName("FingerPrint System");
        String configFilePath = Paths.get((String)folders.get("config"), "config.properties").toString();
        Dictionary configData = ConfigFilesContainer.ConfigFiles.readFile(configFilePath);
        
        //Objects Creation
        controllerQ = Factory.Create.getQ();
        IDatabase dbObject = Factory.Create.getDBInterface("Database Thread", (String)configData.get("dbIPAddress"), (String)configData.get("dbUser"), (String)configData.get("dbPassword"), (String)configData.get("dbName"));
        IProcessLog logObject = Factory.Create.getProcessLogger("");
        ServerThread Server = new ServerThread( "Server Thread", "5555", dbObject, logObject, controllerQ);
        Server.start();
        
        //Initialize TCP Client for further use, example on how to use tcp client to talk to the server
        TCPClient.Client.Init("localhost", "5555", logObject);
        //TCPClient.Client.sendMsg(ParserConcatenator.Concatenator(new String[] {"exit"}));
        //TCPClient.Client.sendMsg(ParserConcatenator.Concatenator(new String[] {"getAll"}));
        //TCPClient.Client.sendMsg(ParserConcatenator.Concatenator(new String[] {"check", ""}));
        //TCPClient.Client.sendMsg(ParserConcatenator.Concatenator(new String[] {"check", "TestFingerPrint"}));
        //TCPClient.Client.sendMsg(ParserConcatenator.Concatenator(new String[] {"insert", ParserConcatenator.Concatenator(new String[] {"myName", "mySurname", "myFingerPrint", "myIDNumber"}, ";")}));
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI.fxml"));     
        Parent root = (Parent)fxmlLoader.load();          
        UIController uiController = fxmlLoader.<UIController>getController();
        uiController.getObjects(controllerQ, logObject);
        //Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("RegisterNewUser.fxml"));
        
    
        Scene scene = new Scene(root);     
        stage.setTitle("FingerPrint Server System");
        stage.setScene(scene);
        stage.show();
        
        //Config.Files.writeConfig();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import static fingerprint.system.FingerPrintSystem.controllerQ;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.stage.StageStyle;
import fingerprint.system.ParserConcatenator;
import java.util.concurrent.BlockingQueue;
/**
 *
 * @author stephane.kibonge
 */
public class UIController implements Initializable {
    
    private BlockingQueue<String> controllerQ;
    
    @FXML
    private void close(ActionEvent event) throws InterruptedException {
        //ServerThread.q.put());
        TCPClient.Client.sendMsg(ParserConcatenator.Concatenator(new String[] {"exit"}));
        System.exit(0);
    }
    
    
    @FXML
    private void Run(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("RunUI.fxml"));
            
            Parent root = (Parent)fxmlLoader.load();          
            RunUIController controller = fxmlLoader.<RunUIController>getController();
            controller.getObjects(controllerQ);
            Scene scene = new Scene(root);
            
            //Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            
            stage.setTitle("Running System");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("An IOException was caught :" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void Register(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("RegisterNewUser.fxml"));
            
            Parent root = (Parent)fxmlLoader.load();          
            RegisterNewUserController controller = fxmlLoader.<RegisterNewUserController>getController();
            controller.getObjects(controllerQ);
            Scene scene = new Scene(root);
            
            //Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            
            stage.setTitle("Register New User");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("An IOException was caught :" + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    
    @FXML
    private void settings(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Settings.fxml"));
  
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            
            stage.setTitle("Running System");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("An IOException was caught :" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void getObjects(BlockingQueue<String> someControllerQ){
        controllerQ = someControllerQ;
    }
}

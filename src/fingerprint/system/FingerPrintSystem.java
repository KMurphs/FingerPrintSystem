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

/**
 *
 * @author stephane.kibonge
 */
public class FingerPrintSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("RegisterNewUser.fxml"));
        
        
        Scene scene = new Scene(root);
        
        stage.setTitle("FingerPrint Server System");
        stage.setScene(scene);
        stage.show();
        
        
        
        DBThread DB = new DBThread( "Database Thread", "127.0.0.1", "tester", "tester321!", "jrfingerprintproject");
        DB.start();
        
        
        //Thread.sleep(500);
        //DBThread.q.put(ParserConcatenator.Concatenator(new String[] {"exit"}));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stephane.kibonge
 */
public class RunUIController implements Initializable {
    
    @FXML private javafx.scene.control.Button btnReturn;
    @FXML private TextArea taLogs;
    private BlockingQueue<String> controllerQ;
    private IObtainableLogs logObject;
    private Timer timer;
    private String cmd = "";
    private String data = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                     public void run() {
                        String currentLogs = ParserConcatenator.Concatenator(logObject.getLogs().toArray(new String[]{}), "\n");
                        if(!currentLogs.equals(taLogs.getText())){
                            taLogs.setText(currentLogs);
                            taLogs.appendText("");
                        }
                    }
                });
            }
        }, 0, 100);
    }    
    
    @FXML
    private void closeWindow(ActionEvent event) {
        close();
    }
    
    private void close(){
        // get a handle to the stage
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        // do what you have to do
        stage.close();
        timer.cancel();        
    }
    
    public void getObjects(BlockingQueue<String> someControllerQ, IObtainableLogs someLogObject){
        controllerQ = someControllerQ;
        logObject = someLogObject;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stephane.kibonge
 */
public class ObtainFingerprintController implements Initializable {
    
    @FXML private javafx.scene.control.Button btnReturn;
    @FXML private Button btnRetry;
    @FXML private Text userInstructions;
    @FXML private TextArea fpRetry1;
    @FXML private TextArea fpRetry2;
    @FXML private TextArea fpRetry3;
    
    private BlockingQueue<String> controllerQ;
    private String[] userData;
    private Timer timer;
    private int i = 0;
    private int fpCounter = 0;
    private String cmd = "";
    private String data = "";
    
    private static boolean isDataUploaded = false;
    public static boolean getIsDataUploaded(){
        System.out.println("isDataUploaded is: " + isDataUploaded);
        return isDataUploaded;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userInstructions.setText("Place Finger on fingerprint Reader");
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Dequeue msg, parse, and extract command and data (cmd, data)
                            String[] msg = ParserConcatenator.Parser(controllerQ.poll(50, TimeUnit.MILLISECONDS)); 
                            cmd = msg[0];
                            data = "";
                            if(msg.length > 1){
                                data = msg[1];
                            }

                            //Handle msg cmd
                            switch(cmd){
                                case "":
                                    break;
                                case "exit":
                                    break;
                                case "upload":
                                    
                                    String matchData = "";
                                    isDataUploaded = false;
                                    
                                    if(fpCounter == 0){
                                        fpRetry1.setText(data);
                                        userInstructions.setText("Okay. Please Place Finger on fingerprint Reader");
                                        fpRetry1.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        fpRetry2.setStyle("-fx-border-color: transparent transparent red transparent;");
                                        fpRetry3.setStyle("-fx-border-color: transparent transparent red transparent;");
                                        if(fpRetry1.getText().equals(fpRetry2.getText())){
                                            matchData = fpRetry1.getText();
                                            fpRetry2.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        } else if(fpRetry1.getText().equals(fpRetry3.getText())){
                                            matchData = fpRetry1.getText();
                                            fpRetry3.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        }   
                                        fpCounter++;
                                    } else if (fpCounter == 1){
                                        fpRetry2.setText(data);
                                        userInstructions.setText("Awesome, we have some Data. Please Place again your Finger on fingerprint Reader");
                                        fpRetry1.setStyle("-fx-border-color: transparent transparent red transparent;");
                                        fpRetry2.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        fpRetry3.setStyle("-fx-border-color: transparent transparent red transparent;");
                                        if(fpRetry2.getText().equals(fpRetry1.getText())){
                                            matchData = fpRetry2.getText();
                                            fpRetry1.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        } else if(fpRetry2.getText().equals(fpRetry3.getText())){
                                            matchData = fpRetry2.getText();
                                            fpRetry3.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        } 
                                        fpCounter++;
                                    } else if (fpCounter == 2){
                                        fpRetry3.setText(data);
                                        userInstructions.setText("Retry Once More...");
                                        fpRetry1.setStyle("-fx-border-color: transparent transparent red transparent;");
                                        fpRetry2.setStyle("-fx-border-color: transparent transparent red transparent;");
                                        fpRetry3.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        if(fpRetry3.getText().equals(fpRetry1.getText())){
                                            matchData = fpRetry3.getText();
                                            fpRetry1.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        } else if(fpRetry3.getText().equals(fpRetry2.getText())){
                                            matchData = fpRetry3.getText();
                                            fpRetry2.setStyle("-fx-border-color: transparent transparent green transparent;");
                                        } 
                                        fpCounter = 0;
                                    } else{
                                        fpCounter = 0;
                                        fpRetry1.setStyle("-fx-border-color: transparent transparent transparent transparent;");
                                        fpRetry2.setStyle("-fx-border-color: transparent transparent transparent transparent;");
                                        fpRetry3.setStyle("-fx-border-color: transparent transparent transparent transparent;");
                                    }
                                    
                                   
                                    
                                    if(!"".equals(matchData)){
                                        System.out.println("Match Obtained. FingerPrint is: " + matchData);
                                        userData[2] = matchData;
                                        TCPClient.Client.sendMsg(ParserConcatenator.Concatenator(new String[] {"insert", ParserConcatenator.Concatenator(userData, ";")}));
                                        isDataUploaded = true;
                                        
                                        close();
                                    }
      
                                    break;
                                default:
                                    System.out.println("Controller Q Unhandled Msg: " + cmd + "  -  " + data);
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ObtainFingerprintController.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                        
                    }
                });
            }
        }, 0, 100);
        
    }    
    

    @FXML
    private void retry(ActionEvent event) {
        fpCounter = 0;
        fpRetry1.setText("");
        fpRetry2.setText("");
        fpRetry3.setText("");
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
    

    
    
    public void getObjects(BlockingQueue<String> someControllerQ, String[] someUserData){
        controllerQ = someControllerQ;
        userData = someUserData;
        //System.out.println(userData[0] + " " + userData[1] + " " + userData[2] + " " + userData[3] + " ");
    }
}

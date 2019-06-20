/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stephane.kibonge
 */
public class RunUIController implements Initializable {
    
    @FXML private javafx.scene.control.Button btnReturn;
    private BlockingQueue<String> controllerQ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void closeWindow(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    public void getObjects(BlockingQueue<String> someControllerQ){
        controllerQ = someControllerQ;
    }
}

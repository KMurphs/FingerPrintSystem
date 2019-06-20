/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author stephane.kibonge
 */
public class RegisterNewUserController implements Initializable {
    
    @FXML private javafx.scene.control.Button btnReturn;
    @FXML private javafx.scene.control.ChoiceBox userRole;
    private BlockingQueue<String> controllerQ;
    ObservableList<String> personRoles = FXCollections.observableArrayList("Student", "Lecturer");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userRole.setItems(personRoles);
    }    
    
    
    
    @FXML
    private void closeWindow(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    
    @FXML
    private void obtainFingerprint(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ObtainFingerprint.fxml"));
                        
            Parent root = (Parent)fxmlLoader.load();          
            ObtainFingerprintController controller = fxmlLoader.<ObtainFingerprintController>getController();
            controller.getObjects(controllerQ);
            Scene scene = new Scene(root);
            
            //Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            
            stage.setTitle("Obtain Fingerprint of New User");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("An IOException was caught :" + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    
    public void getObjects(BlockingQueue<String> someControllerQ){
        controllerQ = someControllerQ;
    }
}

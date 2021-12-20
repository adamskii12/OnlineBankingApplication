/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdamsDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adamierullo
 */
public class FXMLController implements Initializable {

    @FXML
    private TextArea textFirstName;
    @FXML
    private TextArea textLastName;
    @FXML
    private TextArea textUsername;
    @FXML
    private TextArea textPassword;
    @FXML
    private TextArea textEmail;
    @FXML
    private TextField inputFirstName;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputPassword;
    @FXML
    private TextField inputUsername;
    @FXML
    private Button btnSubmit;
    @FXML
    private TextField inputLastName;
    @FXML
    private TextArea resultOfSubmission;
    @FXML
    private Button btnBack;
    @FXML
    private TextArea textNewAccount;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultOfSubmission.setVisible(false);
    }

    @FXML
    private void handleSubmitButton(ActionEvent event) {
        
        TableCommander commander = new TableCommander();
        
        String cusFirstName = inputFirstName.getText();
        String cusLastName = inputLastName.getText();
        String cusUsername = inputUsername.getText();
        String cusPassword = inputPassword.getText();
        String cusEmail = inputEmail.getText();
        
   
        commander.insertNewCustomer(cusFirstName, cusLastName, cusUsername, cusPassword, cusEmail);
        inputFirstName.setText(null);
        inputLastName.setText(null);
        inputUsername.setText(null);
        inputPassword.setText(null);
        inputEmail.setText(null);
        resultOfSubmission.setVisible(true);
        
        
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(getClass().getResource("/AdamsDatabase/FXML2.fxml"));
        Scene scene = new Scene(root);        
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
}

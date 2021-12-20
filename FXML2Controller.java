/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdamsDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adamierullo
 */
public class FXML2Controller implements Initializable {
    private TableCommander commander;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnLogIn;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputPassword;
    @FXML
    private TextArea textNewAccount;
    @FXML
    private TextArea textUsername;
    @FXML
    private TextArea textPassword;
    @FXML
    private TextArea textWelcome;
    @FXML
    private TextArea textIncorrect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commander=new TableCommander();
        textIncorrect.setVisible(false);
    }    

    @FXML
    private void handleBtnRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AdamsDatabase/FXML.fxml"));
        Scene scene = new Scene(root);        
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handleBtnLoginClicked(ActionEvent event) throws IOException {
        
       
       if(commander.checkIfCustomerExists(inputUsername.getText())){
              if(commander.getPasswordByUsername(inputUsername.getText()).equals(inputPassword.getText())){
                          FXMLLoader loader = new FXMLLoader();
                          loader.setLocation(getClass().getResource("/AdamsDatabase/FXMLCustomerView.fxml"));
                          Parent root = loader.load();
                          
                          Scene scene = new Scene(root);    
                          
                          FXMLCVController controller = loader.getController();
                          controller.initializeTextCusFirstName(commander.getFirstNameByUsername(inputUsername.getText()));
                          Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                          primaryStage.setTitle("Accounts Summary");
                          primaryStage.setScene(scene);
                          primaryStage.show();
              }//end of inner if
              else{
       textIncorrect.setVisible(true);
       }
                  }//end of outer if
       
           
           
        
    }
    
}

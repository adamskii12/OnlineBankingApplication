/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdamsDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adamierullo
 */
public class FXMLCVController implements Initializable {

    private String cusFirstName;
    @FXML
    private TextArea textWelcome;
    @FXML
    private TextArea textCusName;
    @FXML
    private TableView<String> accountsTable;
    @FXML
    private TableColumn<?, String> accountNumberColumn;
    @FXML
    private TableColumn<?, String> accountTypeColumn;
    @FXML
    private TableColumn<?, String> accountBalanceColumn;
    @FXML
    private Button btnLogOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textCusName.setVisible(true);
        /*accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("AccountNumber"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("AccountType"));
        accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("AccountBalance"));*/
        ObservableList<String> ol = FXCollections.observableArrayList("14829","Chequing","9283.4");
        accountsTable.setItems(ol);
       // accountsTable.getColumns().addAll(accountNumberColumn,accountTypeColumn,accountBalanceColumn);
    }    
    
    
    
    public void initializeTextCusFirstName(String firstName){
               cusFirstName=firstName;
               textCusName.setText(cusFirstName);
    
    }

    @FXML
    private void handleLogOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AdamsDatabase/FXML2.fxml"));
        Scene scene = new Scene(root);        
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

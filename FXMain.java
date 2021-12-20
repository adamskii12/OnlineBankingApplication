/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdamsDatabase;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/**
 *
 * @author adamierullo
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        TableCommander commander = new TableCommander();
        commander.createAllTables();
        Parent root = FXMLLoader.load(getClass().getResource("/AdamsDatabase/FXML2.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("stylesCSS.css").toExternalForm());
        //scene.getStylesheets().add("path/styles.css");
        
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

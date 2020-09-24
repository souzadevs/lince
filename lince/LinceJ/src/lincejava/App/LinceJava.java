package lincejava.App;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sauloluz
 */
public class LinceJava extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/lincejava/View/Principal.fxml"));
        
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/CSS/Sample.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Lince 2!0");
        primaryStage.show();
    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}

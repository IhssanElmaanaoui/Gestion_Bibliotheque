package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML principal
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            
            // Créer la scène
            Scene scene = new Scene(root, 1000, 600);
            
            // Appliquer le style CSS
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            // Configurer la fenêtre
            primaryStage.setTitle("Gestion de Bibliothèque");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(500);
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
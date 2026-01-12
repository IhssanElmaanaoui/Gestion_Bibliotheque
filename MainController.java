package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.Parent;

public class MainController {

    // ==== PATTERN SINGLETON ====
    private static MainController instance;

    public static MainController getInstance() {
        return instance;
    }
    // ============================

    @FXML
    private ScrollPane centerScrollPane;

    @FXML
    private Label subtitleLabel;

    @FXML
    private MenuItem menuAddBook;

    @FXML
    private MenuItem menuRegisterMember;

    @FXML
    private void initialize() {
        // Enregistrer l'instance unique du contrôleur
        instance = this;

        // Charger le contenu par défaut (dashboard)
        loadContent("dashboard-content.fxml", "Tableau de bord");
    }

    // Méthode pour le bouton Tableau de bord
    @FXML
    private void handleDashboard() {
        loadContent("dashboard-content.fxml", "Tableau de bord");
    }

    // Méthodes de navigation depuis le MenuBar
    @FXML
    private void handleMenuAddBook() {
        loadContent("book-content.fxml", "Ajouter un livre");
    }

    @FXML
    private void handleMenuRegisterMember() {
        loadContent("member-content.fxml", "Inscrire un membre");
    }
    
    @FXML
    public void Quitter() {
        System.exit(0);
    }

    // Méthode publique pour charger dynamiquement un contenu
    public void loadContent(String fxmlFile, String subtitle) {
        try {
            System.out.println("Chargement de: " + fxmlFile);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            
            // CORRECTION: Utiliser Parent au lieu de VBox
            Parent content = loader.load();

            // Définir le contenu dans le ScrollPane
            centerScrollPane.setContent(content);

            // Mettre à jour le sous-titre
            if (subtitleLabel != null) {
                subtitleLabel.setText(subtitle);
            }
            
            System.out.println("Contenu chargé avec succès: " + fxmlFile);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur de chargement du fichier: " + fxmlFile);
            showErrorAlert("Erreur de chargement", "Impossible de charger: " + fxmlFile);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("Fichier non trouvé: " + fxmlFile);
            showErrorAlert("Fichier non trouvé", "Le fichier " + fxmlFile + " n'a pas été trouvé.");
        }
    }
    
    private void showErrorAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.ERROR
        );
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
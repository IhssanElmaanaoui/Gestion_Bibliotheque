package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BookContentController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField categoryField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField publisherField;

    @FXML
    private TextField editionField;

    @FXML
    private TextField copiesField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;

    @FXML
    private void handleBackToDashboard() {
        MainController.getInstance().loadContent(
            "dashboard-content.fxml",
            "Tableau de bord"
        );
    }

    @FXML
    private void handleSave() {
        // Validation des champs obligatoires
        if (titleField.getText().trim().isEmpty() || authorField.getText().trim().isEmpty()) {
            showErrorAlert("Erreur", "Le titre et l'auteur sont obligatoires.");
            return;
        }
        
        System.out.println("=== Données du livre ===");
        System.out.println("Titre: " + titleField.getText());
        System.out.println("Auteur: " + authorField.getText());
        System.out.println("ISBN: " + isbnField.getText());
        System.out.println("Catégorie: " + categoryField.getText());
        System.out.println("Date: " + dateField.getValue());
        System.out.println("Éditeur: " + publisherField.getText());
        System.out.println("Édition: " + editionField.getText());
        System.out.println("Exemplaires: " + copiesField.getText());
        System.out.println("Description: " + descriptionField.getText());

        // TODO: Enregistrement dans la base de données
        
        // Afficher un message de succès
        showSuccessAlert("Succès", "Livre enregistré avec succès !");
        
        // Réinitialiser les champs
        clearFields();
    }
    
    private void clearFields() {
        titleField.clear();
        authorField.clear();
        isbnField.clear();
        categoryField.clear();
        dateField.setValue(null);
        publisherField.clear();
        editionField.clear();
        copiesField.clear();
        descriptionField.clear();
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
    
    private void showSuccessAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.INFORMATION
        );
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
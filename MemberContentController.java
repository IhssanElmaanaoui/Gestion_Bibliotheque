package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class MemberContentController {
    
    @FXML private TextField nameField;
    @FXML private TextField cinField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextArea addressField;
    
    @FXML private RadioButton studentRadio;
    @FXML private RadioButton teacherRadio;
    @FXML private RadioButton externalRadio;
    
    @FXML private CheckBox activeCheck;
    @FXML private TextField studentIdField;
    @FXML private TextField emergencyNameField;
    @FXML private TextField emergencyPhoneField;
    
    @FXML private Button resetButton;
    @FXML private Button backButton;
    @FXML private Button registerButton;
    
    private ToggleGroup memberTypeGroup;
    
    @FXML
    public void initialize() {
        // Initialiser le ToggleGroup pour les RadioButtons
        memberTypeGroup = new ToggleGroup();
        studentRadio.setToggleGroup(memberTypeGroup);
        teacherRadio.setToggleGroup(memberTypeGroup);
        externalRadio.setToggleGroup(memberTypeGroup);
        
        // Sélectionner "Étudiant" par défaut
        studentRadio.setSelected(true);
    }
    
    @FXML
    private void handleReset(ActionEvent event) {
        // Réinitialiser tous les champs
        nameField.clear();
        cinField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        studentIdField.clear();
        emergencyNameField.clear();
        emergencyPhoneField.clear();
        activeCheck.setSelected(true);
        
        // Réinitialiser les radio buttons
        studentRadio.setSelected(true);
        teacherRadio.setSelected(false);
        externalRadio.setSelected(false);
    }
    
    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        // Utiliser MainController.getInstance() pour retourner au dashboard
        MainController mainController = MainController.getInstance();
        if (mainController != null) {
            mainController.loadContent("dashboard-content.fxml", "Tableau de bord");
        } else {
            System.err.println("Erreur: MainController instance est null");
        }
    }
    
    @FXML
    private void handleRegister(ActionEvent event) {
        // Validation simple
        if (!validateForm()) {
            showAlert("Erreur de validation", "Veuillez remplir tous les champs obligatoires.", Alert.AlertType.ERROR);
            return;
        }
        
        // Afficher les données dans la console
        System.out.println("=== DONNÉES DU MEMBRE ===");
        System.out.println("Nom: " + nameField.getText());
        System.out.println("CIN: " + cinField.getText());
        System.out.println("Téléphone: " + phoneField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Adresse: " + addressField.getText());
        
        String memberType = "Non spécifié";
        if (studentRadio.isSelected()) memberType = "Étudiant";
        else if (teacherRadio.isSelected()) memberType = "Enseignant";
        else if (externalRadio.isSelected()) memberType = "Externe";
        
        System.out.println("Type: " + memberType);
        System.out.println("Statut actif: " + activeCheck.isSelected());
        System.out.println("ID Étudiant: " + studentIdField.getText());
        System.out.println("Contact urgence: " + emergencyNameField.getText());
        System.out.println("Tél urgence: " + emergencyPhoneField.getText());
        System.out.println("=========================\n");
        
        // TODO: Enregistrer dans la base de données
        
        // Afficher un message de succès
        showAlert("Succès", "Membre inscrit avec succès !", Alert.AlertType.INFORMATION);
        
        // Réinitialiser le formulaire après enregistrement
        handleReset(event);
    }
    
    private boolean validateForm() {
        boolean isValid = true;
        
        // Validation des champs obligatoires
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            nameField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            nameField.setStyle("");
        }
        
        if (cinField.getText() == null || cinField.getText().trim().isEmpty()) {
            cinField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            cinField.setStyle("");
        }
        
        // Validation optionnelle pour l'email
        String email = emailField.getText();
        if (email != null && !email.trim().isEmpty() && !email.contains("@")) {
            emailField.setStyle("-fx-border-color: orange;");
            // Juste un avertissement, pas d'erreur bloquante
        } else {
            emailField.setStyle("");
        }
        
        return isValid;
    }
    
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
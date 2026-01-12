package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardContentController {

    @FXML
    private Button addBookCard;

    @FXML
    private Button registerMemberCard;

    @FXML
    private Button searchBooksCard;

    @FXML
    private void initialize() {
        // Les boutons sont volontairement inactifs
        // (sécurité + clarté pour l'utilisateur)

        if (addBookCard != null) {
            addBookCard.setDisable(true);
        }

        if (registerMemberCard != null) {
            registerMemberCard.setDisable(true);
        }

        if (searchBooksCard != null) {
            searchBooksCard.setDisable(true);
        }
    }
}

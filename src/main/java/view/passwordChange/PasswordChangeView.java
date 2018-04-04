package view.passwordChange;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import view.controlElements.LabelField;

public class PasswordChangeView extends VBox {

    private LabelField oldPassField, newPassField, newPassAgainField;

    private Button btnChange, btnBack;

    private Label lbWarning;

    public PasswordChangeView() {
        createSceneElements();
    }

    private void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Změna hesla");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-50, 0, 20, 0));

        oldPassField = new LabelField("Staré heslo: ", 110, 2);

        newPassField = new LabelField("Nové heslo: ", 110, 2);

        newPassAgainField = new LabelField("Nové heslo znovu: ", 110, 2);

        btnChange = new Button("Změnit heslo");

        btnBack = new Button("Zpět");

        lbWarning = new Label("Chybně zadané údaje");
        lbWarning.setVisible(false);

        getChildren().addAll(lbTitle, oldPassField, newPassField, newPassAgainField, btnChange, btnBack, lbWarning);

    }

    public LabelField getOldPassField() {
        return oldPassField;
    }

    public LabelField getNewPassField() {
        return newPassField;
    }

    public LabelField getNewPassAgainField() {
        return newPassAgainField;
    }

    public Button getBtnChange() {
        return btnChange;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}

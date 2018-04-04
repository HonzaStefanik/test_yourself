package view.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.controlElements.LabelField;


public class LogInView extends VBox{

    private Stage stage;

    private LabelField nickField, passwordField;

    private Button btnLogIn;

    private Label lbWarning;

    public LogInView() {
        createSceneElements();
        createStage();
    }

    private void createStage(){
        Scene scene = new Scene(this);
        stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(300);
        stage.setWidth(600);
        stage.setHeight(450);
        stage.setTitle("Otestuj se!");
        stage.show();
    }

    private void createSceneElements(){
        setSpacing(25);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Otestuj se!");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(0, 0, 20, 0));

        nickField = new LabelField("Nick: ", 40,1);

        passwordField = new LabelField("Heslo: ", 40,2);

        btnLogIn = new Button("Přihlásit se");

        lbWarning = new Label("Chybně zadané údaje.");
        lbWarning.setVisible(false);
        getChildren().addAll(lbTitle, nickField, passwordField, btnLogIn, lbWarning);
    }

    public Stage getStage() {
        return stage;
    }

    public LabelField getNickField() {
        return nickField;
    }

    public LabelField getPasswordField() {
        return passwordField;
    }

    public Button getBtnLogIn() {
        return btnLogIn;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}


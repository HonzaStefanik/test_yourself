package view.studentAccess.menu;

import helpClasses.LoggedInUtil;
import helpClasses.PasswordUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StudentMenuView extends VBox {

    private Button btnChangePassword, btnStartQuiz, btnListResults;

    private Label lbWarning;

    public StudentMenuView() {
        createSceneElements();
    }

    private void createSceneElements() {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Menu - student");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-70, 0, 20, 0));

        btnStartQuiz = new Button("Začít kvíz");
        btnStartQuiz.setMaxWidth(Double.MAX_VALUE);

        btnListResults = new Button("Přehled výsledků");
        btnListResults.setMaxWidth(Double.MAX_VALUE);

        btnChangePassword = new Button("Změnit heslo");
        btnChangePassword.setMaxWidth(Double.MAX_VALUE);


        lbWarning = new Label();

        String passwd = PasswordUtil.decryptPassword(LoggedInUtil.getLoggedInUser().getPassword());
        if(passwd.equals("1234")){
            lbWarning.setText("Máte nastavené defaultní heslo, doporučujeme ho změnit.");
        }

        VBox vBoxBtns = new VBox();
        vBoxBtns.setSpacing(30);
        vBoxBtns.setAlignment(Pos.CENTER);
        vBoxBtns.setMaxWidth(150);
        vBoxBtns.getChildren().addAll(btnStartQuiz, btnListResults, btnChangePassword);

        getChildren().addAll(lbTitle, vBoxBtns, lbWarning);
    }

    public Button getBtnChangePassword() {
        return btnChangePassword;
    }

    public Button getBtnStartQuiz() {
        return btnStartQuiz;
    }

    public Button getBtnListResults() {
        return btnListResults;
    }
}

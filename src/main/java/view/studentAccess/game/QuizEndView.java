package view.studentAccess.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuizEndView extends VBox {

    private Label lbAnswerNumber, lbCorrectAnswers, lbWrongAnswers, lbSuccessRate;

    private Button btnBack;

    public QuizEndView() {
        createSceneElements();
    }

    private void createSceneElements() {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label lbEnd = new Label("Konec kvízu");
        lbEnd.setFont(new Font("System", 48));
        lbEnd.setPadding(new Insets(-50, 0, 0, 0));

        lbAnswerNumber = new Label();
        lbAnswerNumber.setFont(new Font("System", 24));

        lbCorrectAnswers = new Label();
        lbCorrectAnswers.setFont(new Font("System", 24));

        lbWrongAnswers = new Label();
        lbWrongAnswers.setFont(new Font("System", 24));

        lbSuccessRate = new Label();
        lbSuccessRate.setFont(new Font("System", 24));

        btnBack = new Button("Zpět do menu");


        getChildren().addAll(lbEnd, lbAnswerNumber, lbCorrectAnswers, lbWrongAnswers, lbSuccessRate, btnBack);
    }

    public Label getLbAnswerNumber() {
        return lbAnswerNumber;
    }

    public Label getLbCorrectAnswers() {
        return lbCorrectAnswers;
    }

    public Label getLbWrongAnswers() {
        return lbWrongAnswers;
    }

    public Label getLbSuccessRate() {
        return lbSuccessRate;
    }

    public Button getBtnBack() {
        return btnBack;
    }

}

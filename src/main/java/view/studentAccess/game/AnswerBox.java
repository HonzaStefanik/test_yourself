package view.studentAccess.game;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.CorrectSolution;
import model.Solution;


public class AnswerBox extends HBox {

    private Solution solution;

    private Label lbAnswer;

    public AnswerBox() {
        setPrefSize(240,115);
        setStyle("-fx-background-color: lightblue ;");
        setAlignment(Pos.CENTER);

        lbAnswer = new Label();
        lbAnswer.setFont(new Font("System", 16));
        lbAnswer.setTextAlignment(TextAlignment.CENTER);
        lbAnswer.setWrapText(true);

        getChildren().add(lbAnswer);
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
        lbAnswer.setText(solution.getAnswer());

    }

    public Label getLbAnswer() {
        return lbAnswer;
    }
}


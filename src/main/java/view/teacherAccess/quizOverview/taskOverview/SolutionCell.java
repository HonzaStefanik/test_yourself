package view.teacherAccess.quizOverview.taskOverview;

import helpClasses.CheckString;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import model.*;

public class SolutionCell extends TableCell<Task, Solution> {

    private TextField solutionTextField;
    private ImageView imgViewSolution;


    public SolutionCell() {
        solutionTextField = new TextField();
        imgViewSolution = new ImageView();

    }

    @Override
    public void startEdit() {
        super.startEdit();
        Solution solution = getItem();

        HBox hb = new HBox(5);
        hb.getChildren().addAll(solutionTextField, imgViewSolution);
        setGraphic(hb);

        if(solution instanceof CorrectSolution)
            imgViewSolution.setImage(new Image("correct.png"));
        else
            imgViewSolution.setImage(new Image("wrong.png"));

        solutionTextField.setText(solution.getAnswer());
        solutionTextField.requestFocus();

        setOnKeyPressed(this::keyPressed);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        Solution item = getItem();
        HBox hb = new HBox(5);
        hb.setAlignment(Pos.BASELINE_LEFT);
        Label text = new Label();
        text.setText(item.answerProperty().getValue());
        ImageView imgView = new ImageView();
        if (item instanceof CorrectSolution) {
            imgView.setImage(new Image("correct.png"));
        } else if (item instanceof WrongSolution) {
            imgView.setImage(new Image("wrong.png"));
        }
        hb.getChildren().addAll(text, imgView);
        setGraphic(hb);
    }

    public void commitEdit(String answer) {
        if(getItem() instanceof CorrectSolution){
            CorrectSolution solution = (CorrectSolution) getItem();
            solution.setAnswer(answer);
            new SolutionDao().updateCorrectSolution(solution);
            updateItem(solution, false);
        }
        else{
            WrongSolution solution = (WrongSolution) getItem();
            solution.setAnswer(answer);
            new SolutionDao().updateWrongSolution(solution);
            updateItem(solution, false);
        }
    }

    @Override // nastavení zobrazení
    protected void updateItem(Solution item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText(null);
        } else {
            // vytvoří HBox, přidá do něj Label a ImageView
            HBox hb = new HBox(5);
            hb.setAlignment(Pos.BASELINE_LEFT);
            Label text = new Label();
            text.setText(item.answerProperty().getValue());
            ImageView imgView = new ImageView();
            // rozhodne o obrázku v ImageView na základě instance Solution
            if (item instanceof CorrectSolution) {
                imgView.setImage(new Image("correct.png"));
            } else if (item instanceof WrongSolution) {
                imgView.setImage(new Image("wrong.png"));
            }
            hb.getChildren().addAll(text, imgView);
            setGraphic(hb);
        }
    }
    // zpracovává stisk kláves
    public void keyPressed(KeyEvent e) {
        if(e.getCode().equals(KeyCode.ENTER)) {
            String answer = solutionTextField.getText();
            if(CheckString.isNotBlank(answer)) {
                commitEdit(answer);
            }
            else
                updateItem(getItem(), false);
        }
    }

}
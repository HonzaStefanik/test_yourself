package view.studentAccess.game;

import javafx.beans.property.SimpleStringProperty;
import view.studentAccess.root.StudentAccessRootView;

import java.text.DecimalFormat;

public class QuizEndController {

    private SimpleStringProperty taskNumberProperty = new SimpleStringProperty();
    private SimpleStringProperty correctAnswersProperty = new SimpleStringProperty();
    private SimpleStringProperty wrongAnswersProperty = new SimpleStringProperty();
    private SimpleStringProperty successRateProperty = new SimpleStringProperty();

    private QuizEndView view;

    private StudentAccessRootView rootView;

    public QuizEndController(QuizEndView view, StudentAccessRootView rootView, int taskNumber, int correctAnswers, int wrongAnswers) {
        this.view = view;
        this.rootView = rootView;
        bindLabels();
        setValues(taskNumber, correctAnswers, wrongAnswers);
        this.view.getBtnBack().setOnAction(e -> backAction());
    }

    private String getSuccessRate(int taskNumber, int correctAnswers){
        double successRate =  correctAnswers / ((double) taskNumber / 100);
        DecimalFormat f = new DecimalFormat("##.##");
        return f.format(successRate);

    }

    private void bindLabels(){
        view.getLbAnswerNumber().textProperty().bind(taskNumberProperty);
        view.getLbCorrectAnswers().textProperty().bind(correctAnswersProperty);
        view.getLbWrongAnswers().textProperty().bind(wrongAnswersProperty);
        view.getLbSuccessRate().textProperty().bind(successRateProperty);
    }
    private void setValues(int taskNumber, int correctAnswers, int wrongAnswers){
        taskNumberProperty.setValue("Počet otázek: " + taskNumber);
        correctAnswersProperty.setValue("Počet správných odpovědí: " + correctAnswers);
        wrongAnswersProperty.setValue("Počet špatných odpovědí: " + wrongAnswers);
        successRateProperty.setValue("Úspěšnost: " + getSuccessRate(taskNumber, correctAnswers) + " %");
    }

    private void backAction(){
        rootView.setMenuMiddle();
    }
}

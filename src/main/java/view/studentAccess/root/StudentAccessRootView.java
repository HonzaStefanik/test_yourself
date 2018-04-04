package view.studentAccess.root;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import helpClasses.LoggedInUtil;
import view.controlElements.LogOutButton;
import view.controlElements.RootBorderPane;
import view.passwordChange.PasswordChangeController;
import view.passwordChange.PasswordChangeView;
import view.studentAccess.studentResultOverview.StudentResultOverviewController;
import view.studentAccess.studentResultOverview.StudentResultOverviewView;
import view.studentAccess.game.*;
import view.studentAccess.menu.StudentMenuController;
import view.studentAccess.menu.StudentMenuView;

public class StudentAccessRootView extends RootBorderPane {

    private Stage stage;

    public StudentAccessRootView() {
        System.out.println(LoggedInUtil.getLoggedInUser().getNick());
        createStage();
        setMenuMiddle();
        setTopButton();
    }

    private void createStage(){
        Scene scene = new Scene(this);
        stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(500);
        stage.setWidth(600);
        stage.setHeight(500);
        stage.setTitle("Otestuj se! - přístup pro studenty");
        stage.show();
    }

    private void setTopButton(){
        setTop(new LogOutButton(stage));
    }

    public void setMenuMiddle(){
        StudentMenuView view = new StudentMenuView();
        new StudentMenuController(view, this);
        setNewStageSize(600, 500);
        setCenter(view);
    }

    public void setSelectQuizMiddle() {
        SelectQuizView view = new SelectQuizView();
        new SelectQuizController(view, this);
        setNewStageSize(600, 500);
        setCenter(view);
    }

    public void setQuizGameMiddle(){
        QuizGameView view = new QuizGameView(this);
        new QuizGameController(view);
        setNewStageSize(850, 750);
        setTop(null);
        setCenter(view);
    }

    public void setEndMiddle(int taskNumber, int correctAnswers, int wrongAnswers){
        QuizEndView view = new QuizEndView();
        new QuizEndController(view, this, taskNumber, correctAnswers, wrongAnswers);
        setNewStageSize(800, 600);
        setTopButton();
        setCenter(view);
    }

    public void setResultOverviewMiddle(){
        StudentResultOverviewView view = new StudentResultOverviewView();
        new StudentResultOverviewController(view, this);
        setNewStageSize(1050, 650);
        setCenter(view);
    }

    public void setPasswordChangeMiddle(){
        PasswordChangeView view = new PasswordChangeView();
        new PasswordChangeController(view, this);
        setCenter(view);
    }

    private void setNewStageSize(int newWidth, int newHeight){
        stage.setX(stage.getX() - ((newWidth - stage.getWidth()) / 2));
        stage.setY(stage.getY() - ((newHeight - stage.getHeight()) / 2));
        stage.setWidth(newWidth);
        stage.setHeight(newHeight);
    }

    public Stage getStage() {
        return stage;
    }
}
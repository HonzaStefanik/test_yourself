package view.teacherAccess.root;

import javafx.scene.Scene;
import javafx.stage.Stage;
import helpClasses.LoggedInUtil;
import model.Task;
import view.controlElements.LogOutButton;
import view.controlElements.RootBorderPane;
import view.passwordChange.PasswordChangeController;
import view.passwordChange.PasswordChangeView;
import view.teacherAccess.menu.TeacherMenuController;
import view.teacherAccess.menu.TeacherMenuView;
import view.teacherAccess.quizOverview.QuizOverviewController;
import view.teacherAccess.quizOverview.QuizOverviewView;
import view.teacherAccess.quizOverview.taskOverview.*;
import view.teacherAccess.resultOverview.ResultOverviewController;
import view.teacherAccess.resultOverview.ResultOverviewView;

public class TeacherAccessRootView extends RootBorderPane {

    private Stage stage;

    public TeacherAccessRootView() {
        System.out.println(LoggedInUtil.getLoggedInUser().getNick());
        createStage();
        setTop();
        setMenuMiddle();
    }

    private void createStage(){
        Scene scene = new Scene(this);
        stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(500);
        stage.setWidth(600);
        stage.setHeight(500);
        stage.setTitle("Otestuj se - přístup pro učitele");
        stage.show();
    }

    private void setTop(){
        setTop(new LogOutButton(stage));
    }

    public void setMenuMiddle(){
        TeacherMenuView view = new TeacherMenuView();
        new TeacherMenuController(view, this);
        setNewStageSize(600, 500);
        setCenter(view);
    }

    public void setPasswordChangeMiddle(){
        PasswordChangeView view = new PasswordChangeView();
        new PasswordChangeController(view,this);
        setCenter(view);
    }

    public void setQuizOverviewMiddle(){
        QuizOverviewView view = new QuizOverviewView();
        new QuizOverviewController(view, this);
        setNewStageSize(800, 600);
        setCenter(view);
    }

    public void setTaskOverviewMiddle(){
        TaskOverviewView view = new TaskOverviewView();
        new TaskOverviewController(view, this);
        setNewStageSize(1200, 600);
        setCenter(view);
    }

    public void setAddPictureTaskMiddle(){
        AddTaskView view = new AddTaskView();
        new AddTaskController(view, this);
        setNewStageSize(700, 600);
        setCenter(view);
    }


    public void setResultOverviewMiddle(){
        ResultOverviewView view = new ResultOverviewView();
        new ResultOverviewController(view, this);
        setNewStageSize(1150, 650);
        setCenter(view);
    }

    public void setUpdateTaskMiddle(Task task){
        AddTaskView view = new AddTaskView();
        new UpdateTaskController(view, this, task);
        setNewStageSize(700, 600);
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


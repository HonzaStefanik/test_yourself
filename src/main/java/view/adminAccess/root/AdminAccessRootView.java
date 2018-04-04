package view.adminAccess.root;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.adminAccess.changeFilePath.ChangeDirController;
import view.adminAccess.changeFilePath.ChangeDirView;
import view.adminAccess.classroomOverview.AddClassroomController;
import view.adminAccess.classroomOverview.AddClassroomView;
import view.adminAccess.classroomOverview.ClassroomOverviewController;
import view.adminAccess.classroomOverview.ClassroomOverviewView;
import view.adminAccess.menu.AdminMenuController;
import view.adminAccess.menu.AdminMenuView;
import view.adminAccess.studentOverview.AddStudentController;
import view.adminAccess.studentOverview.AddStudentView;
import view.adminAccess.teacherOverview.AddTeacherController;
import view.adminAccess.teacherOverview.AddTeacherView;
import view.adminAccess.studentOverview.StudentOverviewController;
import view.adminAccess.studentOverview.StudentOverviewView;
import view.adminAccess.teacherOverview.TeacherOverviewController;
import view.adminAccess.teacherOverview.TeacherOverviewView;
import view.controlElements.LogOutButton;
import view.controlElements.RootBorderPane;
import view.passwordChange.PasswordChangeController;
import view.passwordChange.PasswordChangeView;

public class AdminAccessRootView extends RootBorderPane {

    private Stage stage;

    public AdminAccessRootView(){
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
        stage.setTitle("Otestuj se - administrátorský přístup");
        stage.show();
    }


    private void setTop(){
        LogOutButton logOutButton = new LogOutButton(stage);
        setTop(logOutButton);
    }

    public void setMenuMiddle(){
        AdminMenuView view = new AdminMenuView();
        new AdminMenuController(view, this);
        setNewStageSize(600, 500);
        setCenter(view);

    }

    public void setPasswordChangeMiddle(){
        PasswordChangeView view = new PasswordChangeView();
        new PasswordChangeController(view,this);
        setCenter(view);
    }

    public void setAddTeacherMiddle(){
        AddTeacherView view = new AddTeacherView();
        new AddTeacherController(view,this);
        setCenter(view);
    }

    public void setTeacherOverviewMiddle(){
        TeacherOverviewView view = new TeacherOverviewView();
        new TeacherOverviewController(view, this);
        setNewStageSize(800,600);
        setCenter(view);
    }

    public void setStudentOverviewMiddle(){
        StudentOverviewView view = new StudentOverviewView();
        new StudentOverviewController(view, this);
        setNewStageSize(1000, 600);
        setCenter(view);
    }

    public void setAddStudentMiddle(){
        AddStudentView view = new AddStudentView();
        new AddStudentController(view,this);
        setCenter(view);
    }

    public void setClassroomOverviewMiddle(){
        ClassroomOverviewView view = new ClassroomOverviewView();
        new ClassroomOverviewController(view, this);
        setNewStageSize(800, 600);
        setCenter(view);
    }

    public void setAddClassroomMiddle(){
        AddClassroomView view = new AddClassroomView();
        new AddClassroomController(view, this);
        setNewStageSize(800, 600);
        setCenter(view);
    }

    public void setChangeDirMiddle(){
        ChangeDirView view = new ChangeDirView();
        new ChangeDirController(view, this);
        setCenter(view);
    }

    private void setNewStageSize(int newWidth, int newHeight){
        // nastaví okno na střed
        stage.setX(stage.getX() - ((newWidth - stage.getWidth()) / 2));
        stage.setY(stage.getY() - ((newHeight - stage.getHeight()) / 2));
        // nastaví novou velikost
        stage.setWidth(newWidth);
        stage.setHeight(newHeight);
    }

    public Stage getStage() {
        return stage;
    }


}

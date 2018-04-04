package view.adminAccess.studentOverview;

import helpClasses.CheckString;
import model.*;
import view.adminAccess.root.AdminAccessRootView;

import java.util.ArrayList;
import java.util.List;

public class AddStudentController {

    private AddStudentView view;

    private AdminAccessRootView rootView;

    public AddStudentController(AddStudentView view, AdminAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnAddStudent().setOnAction(e -> addStudentAction());
        view.getBtnBack().setOnAction(e -> backAction());
        rootView.setOnMouseClicked(e -> view.getLbWarning().setVisible(false));
    }

    private void addStudentAction(){
        String name = view.getNameField().getField().getText();
        String surname = view.getSurnameField().getField().getText();
        String nick = view.getNickField().getField().getText();
        String password = view.getPasswordField().getField().getText();
        Classroom classroom = (Classroom) view.getClassroomComboBox().getSelectionModel().getSelectedItem();
        String message;

        if(CheckString.isNotBlank(name) && CheckString.isNotBlank(surname)  && CheckString.isNotBlank(nick)  && CheckString.isNotBlank(password) && classroom != null){

            if(checkNick(nick)){

                Student student = new Student(name, surname, nick, password, classroom);
                new StudentDao().insertStudent(student);
                message = "Student " + student.getNick() + " byl vytvořen";

                view.getNameField().getField().clear();
                view.getSurnameField().getField().clear();
                view.getNickField().getField().clear();
                view.getPasswordField().getField().clear();
            }
            else
                message = "Uživatel s tímto nickem již existuje";
        }
        else
            message = "Vyplňte všechny údaje";

        view.getLbWarning().setText(message);
        view.getLbWarning().setVisible(true);
    }

    private void backAction(){
        rootView.setStudentOverviewMiddle();
    }

    private boolean checkNick(String nick){
        Student student = new StudentDao().getStudentByNick(nick);
        Teacher teacher = new TeacherDao().getTeacherByNick(nick);
        Admin admin = new AdminDao().getAdminByNick(nick);
        if (student == null && teacher == null && admin == null)
            return true;
        return false;
    }
}

package view.adminAccess.teacherOverview;

import helpClasses.CheckString;
import model.*;
import view.adminAccess.root.AdminAccessRootView;

import java.util.ArrayList;
import java.util.List;

public class AddTeacherController {

    private AddTeacherView view;

    private AdminAccessRootView rootView;

    public AddTeacherController(AddTeacherView view, AdminAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnAddTeacher().setOnAction(e -> addTeacherAction());
        view.getBtnBack().setOnAction(e -> backAction());
    }

    private void addTeacherAction(){
        String name = view.getNameField().getField().getText();
        String surname = view.getSurnameField().getField().getText();
        String nick = view.getNickField().getField().getText();
        String password = view.getPasswordField().getField().getText();
        String message;

        if(CheckString.isNotBlank(name) && CheckString.isNotBlank(surname) && CheckString.isNotBlank(nick) && CheckString.isNotBlank(password)){
            if(checkNick(nick)){
                Teacher teacher = new Teacher(name, surname, nick, password);
                new TeacherDao().insertTeacher(teacher);
                message = "Učitel " + teacher.getNick() + " byl vytvořen";

                view.getLbWarning().setText(message);
                view.getLbWarning().setVisible(true);

                view.getNameField().getField().clear();
                view.getSurnameField().getField().clear();
                view.getNickField().getField().clear();
                view.getPasswordField().getField().clear();
            }
            else {
                message = "Uživatel s tímto nickem již existuje";
                view.getLbWarning().setText(message);
                view.getLbWarning().setVisible(true);

            }
        }
        else{
            message = "Vyplňte všechny údaje";
            view.getLbWarning().setText(message);
            view.getLbWarning().setVisible(true);
        }
    }

    private void backAction(){
        rootView.setTeacherOverviewMiddle();
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
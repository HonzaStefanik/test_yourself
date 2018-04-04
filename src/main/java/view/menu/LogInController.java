package view.menu;

import helpClasses.LoggedInUtil;
import helpClasses.PasswordUtil;
import database.HibernateUtil;
import model.*;
import view.adminAccess.root.AdminAccessRootController;
import view.adminAccess.root.AdminAccessRootView;
import view.studentAccess.root.StudentAccessRootController;
import view.studentAccess.root.StudentAccessRootView;
import view.teacherAccess.root.TeacherAccessRootController;
import view.teacherAccess.root.TeacherAccessRootView;

public class LogInController {

    private LogInView view;

    public LogInController(LogInView mv) {
        view = mv;
        setActions();
    }

    private void setActions(){
        view.getBtnLogIn().setOnAction(e -> logInAction());
        view.getStage().setOnCloseRequest(e -> windowClosed());
    }

    private void logInAction(){
        view.getLbWarning().setVisible(false);
        String nick = view.getNickField().getField().getText();
        String password = view.getPasswordField().getField().getText();

        Student s = new StudentDao().getStudentByNick(nick);
        Teacher t = new TeacherDao().getTeacherByNick(nick);
        Admin a = new AdminDao().getAdminByNick(nick);

        if(a != null){
            if(nick.equals(a.getNick()) && password.equals(PasswordUtil.decryptPassword(a.getPassword()))) {
                LoggedInUtil.setLoggedInUser(a);
                adminAccess();
            }
            else{
                view.getLbWarning().setVisible(true);
            }
        }
        else if(t != null){
            if(nick.equals(t.getNick()) && password.equals(PasswordUtil.decryptPassword(t.getPassword()))) {
                LoggedInUtil.setLoggedInUser(t);
                teacherAccess();
            }
            else{
                view.getLbWarning().setVisible(true);
            }
        }
        else if(s != null){
            if(nick.equals(s.getNick()) && password.equals(PasswordUtil.decryptPassword(s.getPassword()))){
                LoggedInUtil.setLoggedInUser(s);
                studentAccess();
            }
            else{
                view.getLbWarning().setVisible(true);
            }
        }
        else{
            view.getLbWarning().setVisible(true);
        }

    }

    private void studentAccess(){
        StudentAccessRootView rootView = new StudentAccessRootView();
        new StudentAccessRootController(rootView.getStage());
        view.getStage().close();
    }

    private void teacherAccess(){
        TeacherAccessRootView rootView = new TeacherAccessRootView();
        new TeacherAccessRootController(rootView.getStage());
        view.getStage().close();
    }

    private void adminAccess(){
        AdminAccessRootView rootView = new AdminAccessRootView();
        new AdminAccessRootController(rootView.getStage());
        view.getStage().close();
    }

    private void windowClosed(){
        LoggedInUtil.deleteLoggedInStatus();
        HibernateUtil.shutDown();
        System.exit(0);
    }
}

package view.passwordChange;

import helpClasses.CheckString;
import helpClasses.LoggedInUtil;
import helpClasses.PasswordUtil;
import model.*;
import view.controlElements.RootBorderPane;

public class PasswordChangeController {

    private PasswordChangeView view;

    private RootBorderPane rootView;

    public PasswordChangeController(PasswordChangeView view, RootBorderPane rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions() {
        view.getBtnChange().setOnAction(e -> passwordChangeAction());
        view.getBtnBack().setOnAction(e -> backAction());
    }

    private void passwordChangeAction(){
        String oldPassword = view.getOldPassField().getField().getText();
        String newPassword = view.getNewPassField().getField().getText();
        String newPasswordRepeat = view.getNewPassAgainField().getField().getText();
        String message;

        if(oldPassword.equals(PasswordUtil.decryptPassword(LoggedInUtil.getLoggedInUser().getPassword()))){
            if(CheckString.isNotBlank(newPassword)) {
                if (newPassword.equals(newPasswordRepeat)) {
                    if (LoggedInUtil.getLoggedInUser() instanceof Admin) {
                        Admin a = (Admin) LoggedInUtil.getLoggedInUser();
                        a.setPassword(PasswordUtil.encryptPassword(newPassword));
                        new AdminDao().updateAdmin(a);
                    } else if (LoggedInUtil.getLoggedInUser() instanceof Teacher) {
                        Teacher t = (Teacher) LoggedInUtil.getLoggedInUser();
                        t.setPassword(PasswordUtil.encryptPassword(newPassword));
                        new TeacherDao().updateTeacher(t);
                    } else if (LoggedInUtil.getLoggedInUser() instanceof Student) {
                        Student s = (Student) LoggedInUtil.getLoggedInUser();
                        s.setPassword(PasswordUtil.encryptPassword(newPassword));
                        new StudentDao().updateStudent(s);
                    }

                    message = "Heslo bylo změněno.";
                    view.getLbWarning().setText(message);
                    view.getLbWarning().setVisible(true);
                } else {
                    message = "Hesla se neshodují.";
                    view.getLbWarning().setText(message);
                    view.getLbWarning().setVisible(true);
                }
            }
            else{
                message = "Neplatné heslo.";
                view.getLbWarning().setText(message);
                view.getLbWarning().setVisible(true);
             }

        }
        else {
            message = "Chybně zadané heslo.";
            view.getLbWarning().setText(message);
            view.getLbWarning().setVisible(true);
        }
    }

    private void backAction(){
        rootView.setMenuMiddle();
    }

}

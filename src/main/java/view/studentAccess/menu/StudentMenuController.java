package view.studentAccess.menu;

import view.studentAccess.root.StudentAccessRootView;

public class StudentMenuController {

    private StudentMenuView view;

    private StudentAccessRootView rootView;

    public StudentMenuController(StudentMenuView view, StudentAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions() {
        view.getBtnStartQuiz().setOnAction(e -> startQuizAction());
        view.getBtnListResults().setOnAction(e -> resultOverviewAction());
        view.getBtnChangePassword().setOnAction(e -> changePasswordAction());
    }

    private void startQuizAction(){
        rootView.setSelectQuizMiddle();
    }

    private void resultOverviewAction(){
        rootView.setResultOverviewMiddle();
    }

    private void changePasswordAction(){
        rootView.setPasswordChangeMiddle();
    }

}

package view.teacherAccess.menu;

import view.teacherAccess.root.TeacherAccessRootView;

public class TeacherMenuController {

    private TeacherMenuView view;

    private TeacherAccessRootView rootView;

    public TeacherMenuController(TeacherMenuView view, TeacherAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnListQuizzes().setOnAction(e -> listQuizzesAction());
        view.getBtnChangePassword().setOnAction(e -> changePasswordAction());
        view.getBtnListResults().setOnAction(e -> listResultsAction());
    }

    private void listQuizzesAction(){
        rootView.setQuizOverviewMiddle();
    }

    private void listResultsAction(){
        rootView.setResultOverviewMiddle();
    }

    private void changePasswordAction(){
        rootView.setPasswordChangeMiddle();
    }
}

package view.adminAccess.menu;


import view.adminAccess.root.AdminAccessRootView;

public class AdminMenuController{

    private AdminMenuView view;

    private AdminAccessRootView rootView;

    public AdminMenuController(AdminMenuView view, AdminAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnChangePassword().setOnAction(e -> changePasswordAction());
        view.getBtnListTeachers().setOnAction(e -> listTeachersAction());
        view.getBtnListStudents().setOnAction(e -> listStudentsAction());
        view.getBtnListClassrooms().setOnAction(e -> listClassroomsAction());
        view.getBtnChangeDir().setOnAction(e -> changeDirAction());
    }

    private void changePasswordAction(){
        rootView.setPasswordChangeMiddle();
    }

    private void listTeachersAction(){
        rootView.setTeacherOverviewMiddle();
    }

    private void listStudentsAction(){
        rootView.setStudentOverviewMiddle();
    }

    private void listClassroomsAction(){
        rootView.setClassroomOverviewMiddle();
    }

    private void changeDirAction(){
        rootView.setChangeDirMiddle();
    }
}

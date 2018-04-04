package view.adminAccess.classroomOverview;

import model.Classroom;
import model.ClassroomDao;
import view.adminAccess.root.AdminAccessRootView;

public class ClassroomOverviewController {

    private ClassroomOverviewView view;

    private AdminAccessRootView rootView;

    public ClassroomOverviewController(ClassroomOverviewView view, AdminAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnAddClassroom().setOnAction(e -> addClassroom());
        view.getBtnDeleteClassroom().setOnAction(e -> deleteClassroom());
        view.getBtnBack().setOnAction(e -> backAction());
    }


    private void deleteClassroom() {
        Classroom selectedClassroom = (Classroom) view.getClassroomTableView().getSelectionModel().getSelectedItem();

        if(selectedClassroom != null && new ClassroomDao().deleteClassroom(selectedClassroom))
            view.getClassroomTableView().getItems().remove(selectedClassroom);
    }

    private void addClassroom(){
        rootView.setAddClassroomMiddle();
    }

    private void backAction(){
        rootView.setMenuMiddle();
    }
}
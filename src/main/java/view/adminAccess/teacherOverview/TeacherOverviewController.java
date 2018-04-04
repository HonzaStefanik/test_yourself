package view.adminAccess.teacherOverview;

import helpClasses.LoadFromFile;
import javafx.collections.ObservableList;
import model.Teacher;
import model.TeacherDao;
import view.adminAccess.root.AdminAccessRootView;

public class TeacherOverviewController {

    private TeacherOverviewView view;

    private AdminAccessRootView rootView;

    public TeacherOverviewController(TeacherOverviewView view, AdminAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions() {
        view.getBtnDelete().setOnAction(e -> deleteTeachers());
        view.getBtnBack().setOnAction(e -> backAction());
        view.getBtnAddTeacher().setOnAction(e -> addTeacherAction());
        view.getBtnAddFromFile().setOnAction(e -> loadFromFileAction());
        view.setOnMouseClicked(e -> view.getLbWarning().setVisible(false));
    }

    private void deleteTeachers() {
        ObservableList<Teacher> selectedTeachers, allTeachers;

        allTeachers = view.getTeacherTableView().getItems();

        selectedTeachers = view.getTeacherTableView().getSelectionModel().getSelectedItems();

        for (Teacher t : selectedTeachers) {
            System.out.println(t.getNick());
            new TeacherDao().deleteTeacher(t);
        }
        allTeachers.removeAll(selectedTeachers);
    }

    private void loadFromFileAction() {
        LoadFromFile.loadTeachers(rootView, view);
    }

    private void addTeacherAction() {
        rootView.setAddTeacherMiddle();
    }

    private void backAction() {
        rootView.setMenuMiddle();
    }
}
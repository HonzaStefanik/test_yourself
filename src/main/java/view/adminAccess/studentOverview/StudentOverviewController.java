package view.adminAccess.studentOverview;

import helpClasses.LoadFromFile;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.Student;
import model.StudentDao;
import view.adminAccess.root.AdminAccessRootView;
import view.controlElements.ClassroomComboBox;

import java.util.function.Predicate;

public class StudentOverviewController {

    private StudentOverviewView view;

    private AdminAccessRootView rootView;

    public StudentOverviewController(StudentOverviewView view, AdminAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnDelete().setOnAction(e -> deleteStudents());
        view.getBtnAddStudent().setOnAction(e -> addStudentAction());
        view.getBtnAddFromFile().setOnAction(e -> loadFromFileAction());
        view.setOnMouseClicked(e -> view.getLbWarning().setVisible(false));
        view.getBtnBack().setOnAction(e -> backAction());
        filterData();
    }

    private void filterData() {
        ClassroomComboBox classroomComboBox = view.getClassroomComboBox();

        ObjectProperty<Predicate<Student>> classroomFilter = new SimpleObjectProperty<>();

        classroomFilter.bind(Bindings.createObjectBinding(() ->
                        student -> classroomComboBox.getValue() == view.getAllStudentsChoice() || classroomComboBox.getValue() == student.getClassroom(),
                classroomComboBox.valueProperty()));


        FilteredList<Student> filteredItems = new FilteredList<>(FXCollections.observableList(view.getStudentTableView().getStudentList()));
        view.getStudentTableView().setItems(filteredItems);

        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(
                () -> classroomFilter.get(),
                classroomFilter));

    }

    private void deleteStudents() {
        ObservableList<Student> selectedStudents = FXCollections.observableArrayList();
        selectedStudents.setAll(view.getStudentTableView().getSelectionModel().getSelectedItems());

        ObservableList<Student> allStudents = FXCollections.observableArrayList();
        allStudents.setAll(view.getStudentTableView().getItems());


        for (Student s : selectedStudents) {
            new StudentDao().deleteStudent(s);
        }

        allStudents.removeAll(selectedStudents);
        view.getStudentTableView().setItems(allStudents);
        view.getStudentTableView().setStudentList(view.getStudentTableView().getItems());
    }

    private void addStudentAction(){
        rootView.setAddStudentMiddle();
    }

    private void loadFromFileAction() {
        LoadFromFile.loadStudents(rootView, view);
    }

    private void backAction(){
        rootView.setMenuMiddle();
    }
}

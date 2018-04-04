package view.studentAccess.studentResultOverview;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import model.Result;
import model.Teacher;
import view.controlElements.TeacherComboBox;
import view.studentAccess.root.StudentAccessRootView;

import java.util.function.Predicate;

public class StudentResultOverviewController{

    private StudentResultOverviewView view;

    private StudentAccessRootView rootView;

    public StudentResultOverviewController(StudentResultOverviewView view, StudentAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        view.getBtnBack().setOnAction(e -> backAction());
        view.getBtnResetFilters().setOnAction(e -> resetFiltersAction());
        filterData();

    }

    private void filterData(){
        TextField quizFilterField = view.getQuizFilterField();
        TeacherComboBox teacherComboBox = view.getTeacherComboBox();

        ObjectProperty<Predicate<Result>> quizFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Result>> teacherFilter = new SimpleObjectProperty<>();


        quizFilter.bind(Bindings.createObjectBinding(() ->
                        result -> result.getQuizName().toLowerCase().contains(quizFilterField.getText().toLowerCase()),
                quizFilterField.textProperty()));

        teacherFilter.bind(Bindings.createObjectBinding(() ->
                        result -> teacherComboBox.getValue() == view.getAllTeachersChoice() || teacherComboBox.getValue() == result.getTeacher(),
                teacherComboBox.valueProperty()));


        FilteredList<Result> filteredItems = new FilteredList<>(FXCollections.observableList(view.getResultTableView().getAllResultsList()));
        view.getResultTableView().setItems(filteredItems);


        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(
                () -> quizFilter.get().and(teacherFilter.get()),
                quizFilter, teacherFilter));
    }

    private void resetFiltersAction(){
        view.getQuizFilterField().clear();
        view.getTeacherComboBox().setValue(view.getAllTeachersChoice());
    }

    private void backAction(){
        rootView.setMenuMiddle();
    }
}

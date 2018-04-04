package view.teacherAccess.resultOverview;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import model.*;
import view.controlElements.ClassroomComboBox;
import view.controlElements.StudentComboBox;
import view.teacherAccess.root.TeacherAccessRootView;

import java.util.function.Predicate;

public class ResultOverviewController {

    private ResultOverviewView view;

    private TeacherAccessRootView rootView;

    public ResultOverviewController(ResultOverviewView view, TeacherAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnBack().setOnAction(e -> backAction());
        view.getBtnDelete().setOnAction(e -> deleteAction());
        view.getBtnResetFilters().setOnAction(e -> resetFiltersAction());
        classroomComboBoxListener();
        filterData();
    }

    private void deleteAction(){
        ObservableList<Result> selectedResults = FXCollections.observableArrayList();
        ObservableList<Result> allResults = FXCollections.observableArrayList();

        allResults.setAll(view.getResultTableView().getItems());

        selectedResults.setAll(view.getResultTableView().getSelectionModel().getSelectedItems());

        for (Result r : selectedResults) {
            new ResultDao().deleteResult(r);
        }

        allResults.removeAll(selectedResults);
        view.getResultTableView().setItems(allResults);

    }

    private void classroomComboBoxListener(){
        view.getClassroomComboBox().getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {

            if(newValue.equals(view.getAllClassroomsChoice())){
                view.getStudentComboBox().setAllStudents();
            }
            else{
                view.getStudentComboBox().setStudentsByClassroom((Classroom) newValue);
            }
        });

    }

    private void filterData(){
        TextField quizFilterField = view.getQuizFilterField();
        ClassroomComboBox classroomComboBox = view.getClassroomComboBox();
        StudentComboBox studentComboBox = view.getStudentComboBox();

        ObjectProperty<Predicate<Result>> quizFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Result>> classroomFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Result>> studentFilter = new SimpleObjectProperty<>();


        quizFilter.bind(Bindings.createObjectBinding(() ->
                        result -> result.getQuizName().toLowerCase().contains(quizFilterField.getText().toLowerCase()),
                quizFilterField.textProperty()));

        classroomFilter.bind(Bindings.createObjectBinding(() ->
                        result -> classroomComboBox.getValue() == view.getAllClassroomsChoice() || classroomComboBox.getValue() == result.getStudent().getClassroom(),
                classroomComboBox.valueProperty()));

        studentFilter.bind(Bindings.createObjectBinding(() ->
                    result -> studentComboBox.getValue() == studentComboBox.getAllStudentsChoice() || studentComboBox.getValue() == result.getStudent(),
            studentComboBox.valueProperty()));


        FilteredList<Result> filteredItems = new FilteredList<>(FXCollections.observableList(view.getResultTableView().getAllResultsList()));
        view.getResultTableView().setItems(filteredItems);


        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(
                () -> quizFilter.get().and(classroomFilter.get()).and(studentFilter.get()),
                quizFilter, classroomFilter, studentFilter));

    }

    private void resetFiltersAction(){
        view.getQuizFilterField().clear();
        view.getClassroomComboBox().setValue(view.getAllClassroomsChoice());
        view.getStudentComboBox().setValue(view.getStudentComboBox().getAllStudentsChoice());
    }

    private void backAction(){
        rootView.setMenuMiddle();
    }


}

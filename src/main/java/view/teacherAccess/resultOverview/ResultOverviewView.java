package view.teacherAccess.resultOverview;

import javafx.scene.control.TextField;
import model.Classroom;
import model.Student;
import view.controlElements.ClassroomComboBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import view.controlElements.StudentComboBox;

public class ResultOverviewView extends VBox{

    private TeacherResultTable resultTableView;

    private Button btnDelete, btnResetFilters, btnBack;

    private ClassroomComboBox classroomComboBox;

    private StudentComboBox studentComboBox;

    private Classroom allClassroomsChoice;

    private Student allStudentsChoice;

    private TextField quizFilterField;

    public ResultOverviewView() {
        createSceneElements();
    }

    private void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Přehled výsledků");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        int prefWidth = 175;
        int prefHeight = 20;

        quizFilterField = new TextField();
        quizFilterField.setPrefSize(prefWidth,prefHeight);
        quizFilterField.setPromptText("Kvíz");

        allClassroomsChoice = new Classroom("Všechny třídy", "");
        classroomComboBox = new ClassroomComboBox();
        classroomComboBox.setPrefSize(prefWidth,prefHeight);
        classroomComboBox.getItems().add(0, allClassroomsChoice);
        classroomComboBox.setValue(allClassroomsChoice);

        allStudentsChoice = new Student("Všichni studenti", "", "", "", allClassroomsChoice);
        studentComboBox = new StudentComboBox(allStudentsChoice);
        studentComboBox.setPrefSize(prefWidth,prefHeight);


        btnResetFilters = new Button("Reset filtrů");
        btnResetFilters.setPrefSize(125, prefHeight);

        btnDelete = new Button("Smazat výsledky");
        btnDelete.setPrefSize(125, prefHeight);

        btnBack = new Button("zpět");

        VBox vBoxControls = new VBox();
        vBoxControls.setSpacing(15);
        vBoxControls.setAlignment(Pos.TOP_CENTER);
        vBoxControls.getChildren().addAll(quizFilterField, classroomComboBox, studentComboBox, btnResetFilters, btnDelete, btnBack);

        resultTableView = new TeacherResultTable();

        HBox hBoxWrapper = new HBox();
        hBoxWrapper.setAlignment(Pos.CENTER);
        hBoxWrapper.setSpacing(20);
        hBoxWrapper.getChildren().addAll(resultTableView, vBoxControls);

        getChildren().addAll(lbTitle, hBoxWrapper);
    }

    public TeacherResultTable getResultTableView() {
        return resultTableView;
    }

    public TextField getQuizFilterField() {
        return quizFilterField;
    }

    public ClassroomComboBox getClassroomComboBox() {
        return classroomComboBox;
    }

    public StudentComboBox getStudentComboBox() {
        return studentComboBox;
    }

    public Classroom getAllClassroomsChoice() {
        return allClassroomsChoice;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public Button getBtnResetFilters() {
        return btnResetFilters;
    }
}

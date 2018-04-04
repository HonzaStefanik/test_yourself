package view.studentAccess.studentResultOverview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Teacher;
import view.controlElements.TeacherComboBox;

public class StudentResultOverviewView extends VBox {

    private StudentResultTable resultTableView;

    private TextField quizFilterField;

    private TeacherComboBox teacherComboBox;

    private Teacher allTeachersChoice;

    private Button btnResetFilters, btnBack;

    public StudentResultOverviewView() {
        createSceneElements();
    }

    private void createSceneElements() {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Přehled vašich výsledků");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        int prefWidth = 175;
        int prefHeight = 20;

        quizFilterField = new TextField();
        quizFilterField.setPrefSize(prefWidth,prefHeight);
        quizFilterField.setPromptText("Kvíz");

        allTeachersChoice = new Teacher("Všichni učitelé", "", "", "");
        teacherComboBox = new TeacherComboBox();
        teacherComboBox.setPrefSize(prefWidth,prefHeight);
        teacherComboBox.getItems().add(0, allTeachersChoice);
        teacherComboBox.setValue(allTeachersChoice);

        btnResetFilters = new Button("Reset filtrů");
        btnResetFilters.setPrefSize(125, prefHeight);

        btnBack = new Button("Zpět");

        VBox vBoxControls = new VBox();
        vBoxControls.setSpacing(15);
        vBoxControls.setAlignment(Pos.TOP_CENTER);
        vBoxControls.getChildren().addAll(quizFilterField, teacherComboBox, btnResetFilters, btnBack);

        resultTableView = new StudentResultTable();

        HBox hBoxWrapper = new HBox();
        hBoxWrapper.setSpacing(20);
        hBoxWrapper.getChildren().addAll(resultTableView, vBoxControls);
        hBoxWrapper.setAlignment(Pos.CENTER);

        getChildren().addAll(lbTitle, hBoxWrapper);
    }

    public StudentResultTable getResultTableView() {
        return resultTableView;
    }

    public TextField getQuizFilterField() {
        return quizFilterField;
    }

    public TeacherComboBox getTeacherComboBox() {
        return teacherComboBox;
    }

    public Teacher getAllTeachersChoice() {
        return allTeachersChoice;
    }

    public Button getBtnResetFilters() {
        return btnResetFilters;
    }

    public Button getBtnBack() {
        return btnBack;
    }
}
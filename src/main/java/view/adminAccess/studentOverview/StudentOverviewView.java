package view.adminAccess.studentOverview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Classroom;
import view.controlElements.ClassroomComboBox;

public class StudentOverviewView extends VBox {

    private StudentTableView studentTableView;

    private Button btnDelete, btnAddFromFile, btnAddStudent, btnBack;

    private ClassroomComboBox classroomComboBox;

    private Label lbWarning;

    private Classroom allStudentsChoice;

    public StudentOverviewView() {
        createSceneElements();
    }

    private void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Přehled studentů");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        btnAddStudent = new Button("Přidat studenty");
        btnAddStudent.setPrefSize(150,20);

        btnAddFromFile = new Button("Nahrát ze souboru");
        btnAddFromFile.setPrefSize(150,20);

        btnDelete = new Button("Smazat studenta");
        btnDelete.setPrefSize(150,20);

        classroomComboBox = new ClassroomComboBox();

        allStudentsChoice = new Classroom("Vše", "");
        classroomComboBox.getItems().add(0, allStudentsChoice);
        classroomComboBox.setValue(allStudentsChoice);

        btnBack = new Button("Zpět");

        lbWarning = new Label();
        lbWarning.setVisible(false);

        VBox vBoxControls = new VBox();
        vBoxControls.setSpacing(20);
        vBoxControls.setAlignment(Pos.TOP_CENTER);
        vBoxControls.getChildren().addAll(btnAddStudent, btnAddFromFile, btnDelete, classroomComboBox, btnBack, lbWarning);

        studentTableView = new StudentTableView();

        HBox hBoxWrapper = new HBox();
        hBoxWrapper.setAlignment(Pos.CENTER);
        hBoxWrapper.setSpacing(20);
        hBoxWrapper.getChildren().addAll(studentTableView, vBoxControls);

        getChildren().addAll(lbTitle, hBoxWrapper);
    }

    public StudentTableView getStudentTableView() {
        return studentTableView;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public Button getBtnAddFromFile() {
        return btnAddFromFile;
    }

    public Button getBtnAddStudent() {
        return btnAddStudent;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLbWarning() {
        return lbWarning;
    }

    public ClassroomComboBox getClassroomComboBox() {
        return classroomComboBox;
    }

    public Classroom getAllStudentsChoice() {
        return allStudentsChoice;
    }
}

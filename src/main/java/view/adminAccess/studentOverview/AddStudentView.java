package view.adminAccess.studentOverview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import view.controlElements.ClassroomComboBox;
import view.controlElements.LabelField;

public class AddStudentView extends VBox {

    private LabelField nameField, surnameField, nickField, passwordField;

    private Button btnAddStudent, btnBack;

    private Label lbWarning;

    private ClassroomComboBox classroomComboBox;

    public AddStudentView() {
        createSceneElements();
    }

    private void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Nový student");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        nameField = new LabelField("Jméno:", 50, 1);

        surnameField = new LabelField("Příjmení:", 50, 1);

        nickField = new LabelField("Nick:", 50, 1);

        passwordField = new LabelField("Heslo:", 50, 2);

        classroomComboBox = new ClassroomComboBox();

        btnAddStudent = new Button("Přidat studenta");

        btnBack = new Button("Zpět");

        lbWarning = new Label("Chybně zadané údaje");
        lbWarning.setVisible(false);

        getChildren().addAll(lbTitle, nameField, surnameField, nickField, passwordField, classroomComboBox, btnAddStudent, btnBack, lbWarning);
    }

    public Button getBtnAddStudent() {
        return btnAddStudent;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public LabelField getNameField() {
        return nameField;
    }

    public LabelField getSurnameField() {
        return surnameField;
    }

    public LabelField getNickField() {
        return nickField;
    }

    public LabelField getPasswordField() {
        return passwordField;
    }

    public ClassroomComboBox getClassroomComboBox() {
        return classroomComboBox;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}

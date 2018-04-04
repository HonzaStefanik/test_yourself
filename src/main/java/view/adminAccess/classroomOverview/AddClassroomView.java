package view.adminAccess.classroomOverview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import view.controlElements.LabelField;

public class AddClassroomView extends VBox {

    private LabelField nameField, yearField;

    private Button btnAddClassroom, btnBack;

    private Label lbWarning;

    public AddClassroomView() {
        createSceneElements();
    }

    public void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Nová třída");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        nameField = new LabelField("Třída:", 50, 1);

        yearField = new LabelField("Rok:", 50, 1);

        btnAddClassroom = new Button("Přidat třídu");

        btnBack = new Button("Zpět");

        lbWarning = new Label("Chybně zadané údaje");
        lbWarning.setVisible(false);

        getChildren().addAll(lbTitle, nameField, yearField, btnAddClassroom, btnBack, lbWarning);

    }

    public LabelField getNameField() {
        return nameField;
    }

    public LabelField getYearField() {
        return yearField;
    }

    public Button getBtnAddClassroom() {
        return btnAddClassroom;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}

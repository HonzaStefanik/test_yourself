package view.adminAccess.classroomOverview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ClassroomOverviewView extends VBox {

    private ClassroomTableView classroomTableView;

    private Button btnBack, btnAddClassroom, btnDeleteClassroom;

    private Label lbWarning;

    public ClassroomOverviewView() {
        createSceneElements();
    }

    private void createSceneElements() {
        setSpacing(20);
        setAlignment(Pos.CENTER);


        Label lbTitle = new Label("Přehled tříd");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        btnAddClassroom = new Button("Přidat třídu");
        btnAddClassroom.setPrefSize(150,20);

        btnDeleteClassroom = new Button("Smazat třídu");
        btnDeleteClassroom.setPrefSize(150,20);

        btnBack = new Button("Zpět");

        lbWarning = new Label();
        lbWarning.setVisible(false);

        VBox vBoxControls = new VBox();
        vBoxControls.setSpacing(20);
        vBoxControls.setAlignment(Pos.TOP_CENTER);
        vBoxControls.getChildren().addAll(btnAddClassroom, btnDeleteClassroom, btnBack);

        classroomTableView = new ClassroomTableView();

        HBox hBoxWrapper = new HBox();
        hBoxWrapper.setAlignment(Pos.CENTER);
        hBoxWrapper.setSpacing(20);
        hBoxWrapper.getChildren().addAll(classroomTableView, vBoxControls);

        getChildren().addAll(lbTitle, hBoxWrapper);

    }

    public ClassroomTableView getClassroomTableView() {
        return classroomTableView;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnAddClassroom() {
        return btnAddClassroom;
    }

    public Button getBtnDeleteClassroom() {
        return btnDeleteClassroom;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}

package view.teacherAccess.quizOverview.taskOverview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TaskOverviewView extends VBox {


    private TaskTableView taskTableView;

    private Button btnAddPictureTask, btnDeleteTask, btnEditTask, btnBack;

    private Label lbWarning;

    public TaskOverviewView( ) {
        createSceneElements();
    }

    private void createSceneElements() {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Přehled otázek");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        btnAddPictureTask = new Button("Přidat otázku");
        btnAddPictureTask.setPrefSize(150,20);

        btnEditTask = new Button("Upravit otázku");
        btnEditTask.setPrefSize(150,20);

        btnDeleteTask = new Button("Smazat otázky");
        btnDeleteTask.setPrefSize(150,20);

        btnBack = new Button("zpět");

        lbWarning = new Label();
        lbWarning.setVisible(false);

        VBox vBoxControls = new VBox();
        vBoxControls.setSpacing(20);
        vBoxControls.setAlignment(Pos.TOP_CENTER);
        vBoxControls.getChildren().addAll(btnAddPictureTask, btnEditTask, btnDeleteTask, btnBack, lbWarning);

        taskTableView = new TaskTableView();

        HBox hBoxWrapper = new HBox();
        hBoxWrapper.setAlignment(Pos.CENTER);
        hBoxWrapper.setSpacing(20);
        hBoxWrapper.getChildren().addAll(taskTableView, vBoxControls);

        getChildren().addAll(lbTitle, hBoxWrapper);
    }

    public TaskTableView getTaskTableView() {
        return taskTableView;
    }

    public Button getBtnDeleteTask() {
        return btnDeleteTask;
    }

    public Button getBtnEditTask() {
        return btnEditTask;
    }

    public Button getBtnAddPictureTask() {
        return btnAddPictureTask;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}

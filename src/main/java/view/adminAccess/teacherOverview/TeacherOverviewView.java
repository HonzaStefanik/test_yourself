package view.adminAccess.teacherOverview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Teacher;
import model.TeacherDao;

import java.util.List;

public class TeacherOverviewView extends VBox{

    private TeacherTableView teacherTableView;

    private Button btnDelete, btnAddFromFile, btnAddTeacher, btnBack;

    private Label lbWarning;

    public TeacherOverviewView() {
        createSceneElements();
    }

    private void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Přehled učitelů");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        btnAddTeacher = new Button("Přidat učitele");
        btnAddTeacher.setPrefSize(150,20);

        btnAddFromFile = new Button("Nahrát ze souboru");
        btnAddFromFile.setPrefSize(150,20);

        btnDelete = new Button("Smazat učitele");
        btnDelete.setPrefSize(150,20);

        btnBack = new Button("Zpět");

        lbWarning = new Label();
        lbWarning.setVisible(false);

        VBox vBoxButtons = new VBox(btnAddTeacher, btnAddFromFile, btnDelete, btnBack, lbWarning);
        vBoxButtons.setSpacing(20);
        vBoxButtons.setAlignment(Pos.TOP_CENTER);

        teacherTableView = new TeacherTableView();

        HBox hBoxWrapper = new HBox();
        hBoxWrapper.setAlignment(Pos.CENTER);
        hBoxWrapper.setSpacing(20);
        hBoxWrapper.getChildren().addAll(teacherTableView, vBoxButtons);

        getChildren().addAll(lbTitle, hBoxWrapper);
    }

    public TeacherTableView getTeacherTableView() {
        return teacherTableView;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public Button getBtnAddFromFile() {
        return btnAddFromFile;
    }

    public Button getBtnAddTeacher() {
        return btnAddTeacher;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}

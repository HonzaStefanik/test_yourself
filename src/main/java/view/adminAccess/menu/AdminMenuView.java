package view.adminAccess.menu;

import helpClasses.LoggedInUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AdminMenuView extends VBox {

    private Button btnChangePassword, btnListTeachers, btnListStudents, btnListClassrooms, btnChangeDir;

    public AdminMenuView() {
        createSceneElements();
    }

    private void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Menu - admin");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-80, 0, 20, 0));

        btnListTeachers = new Button("Spravovat učitele");
        btnListTeachers.setMaxWidth(Double.MAX_VALUE);

        btnListStudents = new Button("Spravovat studenty");
        btnListStudents.setMaxWidth(Double.MAX_VALUE);

        btnChangePassword = new Button("Změnit heslo");
        btnChangePassword.setMaxWidth(Double.MAX_VALUE);

        btnListClassrooms = new Button("Spravovat třídy");
        btnListClassrooms.setMaxWidth(Double.MAX_VALUE);

        btnChangeDir = new Button("Změnit adresář");
        btnChangeDir.setMaxWidth(Double.MAX_VALUE);


        VBox vBoxBtns = new VBox();
        vBoxBtns.setSpacing(30);
        vBoxBtns.setAlignment(Pos.CENTER);
        vBoxBtns.setMaxWidth(150);
        vBoxBtns.getChildren().addAll(btnListTeachers, btnListClassrooms, btnListStudents, btnChangeDir, btnChangePassword);

        getChildren().addAll(lbTitle, vBoxBtns);
    }

    public Button getBtnListClassrooms() {
        return btnListClassrooms;
    }

    public Button getBtnChangePassword() {
        return btnChangePassword;
    }

    public Button getBtnListTeachers() {
        return btnListTeachers;
    }

    public Button getBtnListStudents() {
        return btnListStudents;
    }

    public Button getBtnChangeDir() {
        return btnChangeDir;
    }
}
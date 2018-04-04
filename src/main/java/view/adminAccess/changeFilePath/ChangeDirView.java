package view.adminAccess.changeFilePath;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.FilePathBaseDao;

public class ChangeDirView extends VBox {

    private Button btnBack, btnChangeDir, btnSelectDir;

    private Label lbCurrentDir;

    private CheckBox cbMoveFiles;

    private TextField newDirTextField;

    public ChangeDirView() {
        createSceneElements();
    }

    private void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Změna cesty k souborům");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-30, 0, 20, 0));

        Label lbDirDescription = new Label("Adresář: ");
        lbCurrentDir = new Label();
        lbCurrentDir.setText(new FilePathBaseDao().getFilePathBase().toString());
        HBox hbCurrentDir = new HBox(5, lbDirDescription, lbCurrentDir);
        hbCurrentDir.setAlignment(Pos.CENTER);


        newDirTextField = new TextField();
        newDirTextField.setPromptText("Nová cesta");
        newDirTextField.setMinWidth(250);
        newDirTextField.setEditable(false);
        newDirTextField.setMouseTransparent(true);
        newDirTextField.setFocusTraversable(false);
        btnSelectDir = new Button("...");
        HBox hbNewDir = new HBox(5, newDirTextField, btnSelectDir);
        hbNewDir.setAlignment(Pos.CENTER);

        cbMoveFiles = new CheckBox();
        cbMoveFiles.setSelected(true);
        Label lbMoveFiles = new Label("Zkopírovat soubory");
        HBox hbMoveFiles = new HBox(5, cbMoveFiles, lbMoveFiles);
        hbMoveFiles.setAlignment(Pos.CENTER);

        btnChangeDir = new Button("Změnit adresář");

        btnBack = new Button("Zpět");


        getChildren().addAll(lbTitle, hbCurrentDir, hbNewDir, hbMoveFiles, btnChangeDir,btnBack);
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnChangeDir() {
        return btnChangeDir;
    }

    public Label getLbCurrentDir() {
        return lbCurrentDir;
    }

    public CheckBox getCbMoveFiles() {
        return cbMoveFiles;
    }

    public TextField getNewDirTextField() {
        return newDirTextField;
    }

    public Button getBtnSelectDir() {
        return btnSelectDir;
    }
}

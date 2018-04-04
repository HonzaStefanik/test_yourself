package view.teacherAccess.quizOverview.taskOverview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import view.controlElements.FileComboBox;
import view.controlElements.LabelField;
import view.controlElements.NewSolutionField;

public class AddTaskView extends VBox {

    private LabelField questionField;

    private FileComboBox allFilesComboBox;

    private NewSolutionField solution1Field, solution2Field,solution3Field,solution4Field;

    private Button btnUploadFile, btnAddTask, btnBack;

    private Label lbTitle, lbWarning;

    public AddTaskView() {
        createSceneElements();
    }

    private void createSceneElements(){
        setSpacing(20);
        setAlignment(Pos.CENTER);

        lbTitle = new Label("Nová otázka");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        Label lbAllFiles = new Label("Obrázek / video");
        lbAllFiles.setPrefWidth(100);

        allFilesComboBox = new FileComboBox();
        allFilesComboBox.setPrefWidth(300);

        btnUploadFile = new Button("...");

        HBox uploadFileWrapper = new HBox(5);
        uploadFileWrapper.setAlignment(Pos.CENTER);
        uploadFileWrapper.getChildren().addAll(lbAllFiles, allFilesComboBox, btnUploadFile);
        uploadFileWrapper.setPadding(new Insets(0,0,0, -60 ));

        questionField = new LabelField("Otázka:", 100, 1);
        questionField.setPadding(new Insets(0, 0, 0, -92));

        solution1Field = new NewSolutionField("Odpověď 1:", 100);

        solution2Field = new NewSolutionField("Odpověď 2:", 100);

        solution3Field = new NewSolutionField("Odpověď 3:", 100);

        solution4Field = new NewSolutionField("Odpověď 4:", 100);

        btnAddTask = new Button("Přidat otázku");

        btnBack = new Button("Zpět");

        lbWarning = new Label("Chybně zadané údaje");
        lbWarning.setVisible(false);

        getChildren().addAll(lbTitle, uploadFileWrapper, questionField, solution1Field, solution2Field, solution3Field, solution4Field, btnAddTask, btnBack, lbWarning);
    }


    public Label getLbTitle() {
        return lbTitle;
    }

    public LabelField getQuestionField() {
        return questionField;
    }

    public NewSolutionField getSolution1Field() {
        return solution1Field;
    }

    public NewSolutionField getSolution2Field() {
        return solution2Field;
    }

    public NewSolutionField getSolution3Field() {
        return solution3Field;
    }

    public NewSolutionField getSolution4Field() {
        return solution4Field;
    }

    public FileComboBox getAllFilesComboBox() {
        return allFilesComboBox;
    }

    public Button getBtnUploadFile() {
        return btnUploadFile;
    }

    public Button getBtnAddTask() {
        return btnAddTask;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}


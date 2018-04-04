package view.studentAccess.game;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Teacher;
import view.controlElements.QuizComboBox;
import view.controlElements.TeacherComboBox;

public class SelectQuizView extends VBox{

    private TeacherComboBox teacherComboBox;

    private QuizComboBox quizComboBox;

    private Teacher allTeachersChoice;

    private Button btnPlay, btnBack;

    private Label lbWarning;

    public SelectQuizView() {
        createSceneElements();
    }

    private void createSceneElements() {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Výběr kvízu");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-30, 0, 20, 0));

        Label lbTeacher = new Label("Učitelé");
        lbTeacher.setPrefWidth(50);

        allTeachersChoice = new Teacher("Všichni učitelé", "", "", "");

        teacherComboBox = new TeacherComboBox();
        teacherComboBox.getItems().add(0, allTeachersChoice);
        teacherComboBox.setValue(allTeachersChoice);
        teacherComboBox.setPrefWidth(150);

        HBox hbTeacher = new HBox(5);
        hbTeacher.setPrefWidth(200);
        hbTeacher.setAlignment(Pos.CENTER);
        hbTeacher.getChildren().addAll(lbTeacher, teacherComboBox);

        Label lbQuiz = new Label("Kvízy");
        lbQuiz.setPrefWidth(50);

        quizComboBox = new QuizComboBox();
        quizComboBox.setPrefWidth(150);

        HBox hbQuiz = new HBox(5);
        hbQuiz.setAlignment(Pos.CENTER);
        hbQuiz.getChildren().addAll(lbQuiz, quizComboBox);

        btnPlay = new Button("Hrát");
        btnPlay.setPrefWidth(75);

        btnBack = new Button("Zpět");
        btnBack.setPrefWidth(75);

        lbWarning = new Label();
        lbWarning.setVisible(false);

        getChildren().addAll(lbTitle, hbTeacher, hbQuiz, btnPlay, btnBack, lbWarning);
    }

    public TeacherComboBox getTeacherComboBox() {
        return teacherComboBox;
    }

    public QuizComboBox getQuizComboBox() {
        return quizComboBox;
    }

    public Teacher getAllTeachersChoice() {
        return allTeachersChoice;
    }

    public Button getBtnPlay() {
        return btnPlay;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLbWarning() {
        return lbWarning;
    }
}

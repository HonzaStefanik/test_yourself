package view.teacherAccess.quizOverview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuizOverviewView extends VBox {

    private QuizTableView quizTableView;

    private Button btnAddQuiz, btnDeleteQuiz, btnQuizDetail, btnBack;

    private Label lbWarning;

    private TextField newQuizTextField;

    public QuizOverviewView() {
        createSceneElements();
    }


    private void createSceneElements() {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label lbTitle = new Label("Přehled kvízů");
        lbTitle.setFont(new Font("System", 36));
        lbTitle.setPadding(new Insets(-20, 0, 20, 0));

        Label lbNewQUiz = new Label("Nový kvíz");
        lbNewQUiz.setPrefSize(150,20);
        lbNewQUiz.setPadding(new Insets(0, 0, -10, 0));

        btnAddQuiz = new Button("Přidat kvíz");
        btnAddQuiz.setPrefSize(150,20);

        btnDeleteQuiz = new Button("Smazat kvízy");
        btnDeleteQuiz.setPrefSize(150,20);

        btnQuizDetail = new Button("Detail kvízu");
        btnQuizDetail.setPrefSize(150,20);

        btnBack = new Button("zpět");

        lbWarning = new Label();
        lbWarning.setVisible(false);

        newQuizTextField = new TextField();
        newQuizTextField.setPrefSize(150,20);

        VBox vBoxControls = new VBox();
        vBoxControls.setSpacing(20);
        vBoxControls.setAlignment(Pos.TOP_CENTER);
        vBoxControls.getChildren().addAll(btnDeleteQuiz, btnQuizDetail, lbNewQUiz, newQuizTextField, btnAddQuiz, btnBack, lbWarning);

        quizTableView = new QuizTableView();

        HBox hBoxWrapper = new HBox();
        hBoxWrapper.setAlignment(Pos.CENTER);
        hBoxWrapper.setSpacing(20);
        hBoxWrapper.getChildren().addAll(quizTableView, vBoxControls);

        getChildren().addAll(lbTitle, hBoxWrapper);
    }

    public QuizTableView getQuizTableView() {
        return quizTableView;
    }

    public Button getBtnAddQuiz() {
        return btnAddQuiz;
    }

    public Button getBtnDeleteQuiz() {
        return btnDeleteQuiz;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnQuizDetail() {
        return btnQuizDetail;
    }

    public Label getLbWarning() {
        return lbWarning;
    }

    public TextField getNewQuizTextField() {
        return newQuizTextField;
    }
}

package view.studentAccess.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import view.studentAccess.root.StudentAccessRootView;

public class QuizGameView extends FlowPane {

    private Label lbQuestion;

    private AnswerBox answerBox1, answerBox2, answerBox3, answerBox4;

    private StudentAccessRootView rootView;

    private ImageView imgView;

    private MediaView mediaView;

    public QuizGameView(StudentAccessRootView rootView) {
        this.rootView = rootView;
        createSceneElements();
    }

    private void createSceneElements(){
        setAlignment(Pos.CENTER);

        lbQuestion = new Label("question");
        lbQuestion.setFont(new Font("System", 36));
        lbQuestion.setPadding(new Insets(0, 0, 50, 0));
        lbQuestion.setWrapText(true);
        lbQuestion.setAlignment(Pos.CENTER);
        lbQuestion.setTextAlignment(TextAlignment.CENTER);
        lbQuestion.prefWidthProperty().bind(rootView.getStage().widthProperty());

        answerBox1 = new AnswerBox();
        answerBox2 = new AnswerBox();
        answerBox3 = new AnswerBox();
        answerBox4 = new AnswerBox();

        imgView = new ImageView();

        mediaView = new MediaView();
        mediaView.setFitHeight(300);

        GridPane grid = new GridPane();

        grid.setVgap(40);
        grid.setHgap(100);

        grid.add(answerBox1,0,0);
        grid.add(answerBox2,1,0);
        grid.add(answerBox3,0,1);
        grid.add(answerBox4,1,1);


        getChildren().addAll(imgView, mediaView, lbQuestion, grid);
    }

    public void setDefaultColor(AnswerBox answerBox){
        answerBox.setStyle("-fx-background-color: lightblue ;");
    }

    public void setChoiceColor(AnswerBox answerBox){
        answerBox.setStyle("-fx-background-color: sandybrown ;");
    }

    public void setGreenColor(AnswerBox answerBox){
        answerBox.setStyle("-fx-background-color: lightgreen ;");
    }

    public void setRedColor(AnswerBox answerBox){
        answerBox.setStyle("-fx-background-color: #ff6666 ;");
    }

    public Label getLbQuestion() {
        return lbQuestion;
    }

    public AnswerBox getAnswerBox1() {
        return answerBox1;
    }

    public AnswerBox getAnswerBox2() {
        return answerBox2;
    }

    public AnswerBox getAnswerBox3() {
        return answerBox3;
    }

    public AnswerBox getAnswerBox4() {
        return answerBox4;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public StudentAccessRootView getRootView() {
        return rootView;
    }
}


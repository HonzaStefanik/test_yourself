package view.studentAccess.game;

import database.HibernateUtil;
import helpClasses.LoggedInUtil;
import helpClasses.SelectedQuiz;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.*;

import view.studentAccess.root.StudentAccessRootView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizGameController {


    private QuizGameView view;

    private StudentAccessRootView rootView;

    private List<Task> taskList;

    private int taskNumber, correctAnswers, wrongAnswers;

    private boolean threadRunning;


    public QuizGameController(QuizGameView view) {
        this.view = view;
        this.rootView = view.getRootView();
        fillTaskList();
        loadTask();
        setActions();
    }


    private void setActions(){
        view.getAnswerBox1().setOnMouseClicked(e -> startTask(view.getAnswerBox1()));
        view.getAnswerBox2().setOnMouseClicked(e -> startTask(view.getAnswerBox2()));
        view.getAnswerBox3().setOnMouseClicked(e -> startTask(view.getAnswerBox3()));
        view.getAnswerBox4().setOnMouseClicked(e -> startTask(view.getAnswerBox4()));
        rootView.getStage().setOnCloseRequest(e -> calculateResultOnClose());
    }

    private void startTask(AnswerBox answerBox) {
        if(!threadRunning) {
            Runnable runnable = () -> answerBoxClicked(answerBox);
            Thread backgroundThread = new Thread(runnable);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
        }
        threadRunning = true;
    }

    private void answerBoxClicked(AnswerBox answerBox){
        try {
            Platform.runLater(() -> view.setChoiceColor(answerBox));
            Thread.sleep(600);    // change to 1000 later
            if(answerBox.getSolution() instanceof CorrectSolution) {
                correctAnswers++;
                Platform.runLater(() -> view.setGreenColor(answerBox));
            }
            else {
                wrongAnswers++;
                Platform.runLater(() -> view.setRedColor(answerBox));
            }
            Thread.sleep(600);    // change to 1000 later
            Platform.runLater(() -> view.setDefaultColor(answerBox));
            Platform.runLater(this::loadTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadRunning = false;
    }


    private void fillTaskList(){
        List<Task> dbList = SelectedQuiz.getQuiz().getTaskList();
        taskList = new ArrayList<>();
        taskList.addAll(dbList);
        taskNumber = taskList.size();
    }

    private void loadTask(){
        if(!taskList.isEmpty()) {
            view.getImgView().setImage(null);
            view.getMediaView().setMediaPlayer(null);
            view.getMediaView().setFitHeight(0);

            int taskIndex = new Random().nextInt(taskList.size());

            Task task = taskList.get(taskIndex);

            if(task.getFileName() != null) {
                String filePathBase = new FilePathBaseDao().getFilePathBase().toString();
                File file = new File(filePathBase + "\\" + task.getFileName());
                if (file.exists()) {
                    String mimeType = null;

                    try {
                        mimeType = Files.probeContentType(file.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String[] type = mimeType.split("/");
                    if (type[0].equals("image"))
                        view.getImgView().setImage(new Image(file.toURI().toString()));
                    else if (type[0].equals("video")) {
                        MediaPlayer player = new MediaPlayer(new Media(file.toURI().toString()));
                        view.getMediaView().setFitHeight(200);
                        view.getMediaView().setMediaPlayer(player);
                        player.play();
                    }

                }
                else
                    view.getImgView().setImage(new Image("file_not_found.png"));
            }



            if(task.getQuestion() != null)
                view.getLbQuestion().setText(task.getQuestion());

            List<AnswerBox> answerBoxList = createAnswerBoxList();
            List<Solution> solutionList = createSolutionList(task);
            int bound = answerBoxList.size();

            for(AnswerBox box : answerBoxList){
                int index = (new Random().nextInt(bound) + 1) - 1;
                Solution sol = solutionList.get(index);
                box.setSolution(sol);
                solutionList.remove(sol);
                bound--;
            }
            taskList.remove(taskList.get(taskIndex));
        }
        else {
            saveResults(correctAnswers, wrongAnswers);
        }
    }


    private void saveResults(int correctAnswers, int wrongAnswers){
        Student student = (Student) LoggedInUtil.getLoggedInUser();

        Quiz quiz = SelectedQuiz.getQuiz();

        Result result = new Result(correctAnswers, wrongAnswers, student, quiz.getName(), quiz.getTeacherOwner());

        new ResultDao().insertResult(result);

        setGameEnd();
    }

    private void calculateResultOnClose(){
        wrongAnswers = taskNumber - correctAnswers;

        saveResults(correctAnswers, wrongAnswers);

        setGameEnd();

        LoggedInUtil.deleteLoggedInStatus();
        HibernateUtil.shutDown();
        System.exit(0);
    }

    private void setGameEnd(){
        rootView.setEndMiddle(taskNumber, correctAnswers, wrongAnswers);
    }

    private List<AnswerBox> createAnswerBoxList(){
        List<AnswerBox> list = new ArrayList<>();
        list.add(view.getAnswerBox1());
        list.add(view.getAnswerBox2());
        list.add(view.getAnswerBox3());
        list.add(view.getAnswerBox4());
        return list;
    }

    private List<Solution> createSolutionList(Task task){
        List<Solution> list = new ArrayList<>();
        list.add(task.getSolution1());
        list.add(task.getSolution2());
        list.add(task.getSolution3());
        list.add(task.getSolution4());
        return list;
    }

}
package view.teacherAccess.quizOverview.taskOverview;

import helpClasses.CheckString;
import helpClasses.LoadFromFile;
import helpClasses.SelectedQuiz;
import javafx.scene.control.RadioButton;
import model.*;
import view.teacherAccess.root.TeacherAccessRootView;

import java.io.File;

public class UpdateTaskController {

    private AddTaskView view;

    private TeacherAccessRootView rootView;

    private Task task;

    public UpdateTaskController(AddTaskView view, TeacherAccessRootView rootView, Task task) {
        this.view = view;
        this.rootView = rootView;
        this.task = task;
        loadValues();
        setActions();
    }

    private void loadValues(){
        view.getBtnAddTask().setText("Potvrdit editaci");
        view.getLbTitle().setText("Upravit otázku");

        String fileName = task.getFileName();
        String question = task.getQuestion();
        Solution solution1 = task.getSolution1();
        Solution solution2 = task.getSolution2();
        Solution solution3 = task.getSolution3();
        Solution solution4 = task.getSolution4();

        if(fileName != null){
            view.getAllFilesComboBox().setValue(fileName);
            view.getQuestionField().getField().setText(question);
        }
        else
            view.getQuestionField().getField().setText(question);

        view.getSolution1Field().getTextField().setText(solution1.getAnswer());
        view.getSolution2Field().getTextField().setText(solution2.getAnswer());
        view.getSolution3Field().getTextField().setText(solution3.getAnswer());
        view.getSolution4Field().getTextField().setText(solution4.getAnswer());

        if(solution1 instanceof CorrectSolution) {
            view.getSolution1Field().getRadioButtonCorrect().setSelected(true);
        }
        else {
            view.getSolution1Field().getRadioButtonWrong().setSelected(true);
        }

        if(solution2 instanceof CorrectSolution) {
            view.getSolution2Field().getRadioButtonCorrect().setSelected(true);
        }
        else {
            view.getSolution2Field().getRadioButtonWrong().setSelected(true);
        }

        if(solution3 instanceof CorrectSolution) {
            view.getSolution3Field().getRadioButtonCorrect().setSelected(true);
        }
        else {
            view.getSolution3Field().getRadioButtonWrong().setSelected(true);
        }

        if(solution4 instanceof CorrectSolution) {
            view.getSolution4Field().getRadioButtonCorrect().setSelected(true);
        }
        else {
            view.getSolution4Field().getRadioButtonWrong().setSelected(true);
        }
    }

    private void setActions(){
        view.getBtnUploadFile().setOnAction(e -> uploadFileAction());
        view.getBtnAddTask().setOnAction(e -> updateTaskAction());
        view.getBtnBack().setOnAction(e -> backAction());
    }

    private void uploadFileAction() {
        LoadFromFile.uploadFile(rootView, view);
    }

    private void updateTaskAction(){
        String fileName =  ((File) view.getAllFilesComboBox().getSelectionModel().getSelectedItem()).getName();
        String question = view.getQuestionField().getField().getText();
        String answer1 = view.getSolution1Field().getTextField().getText();
        String answer2 = view.getSolution2Field().getTextField().getText();
        String answer3 = view.getSolution3Field().getTextField().getText();
        String answer4 = view.getSolution4Field().getTextField().getText();

        Solution solution1, solution2, solution3, solution4;

        view.getLbWarning().setVisible(true);

        if (CheckString.isNotBlank(fileName) && CheckString.isNotBlank(answer1) && CheckString.isNotBlank(answer2) &&
                CheckString.isNotBlank(answer3) && CheckString.isNotBlank(answer4)) {

            solution1 = createSolution(answer1, view.getSolution1Field().getRadioButtonCorrect());
            solution2 = createSolution(answer2, view.getSolution2Field().getRadioButtonCorrect());
            solution3 = createSolution(answer3, view.getSolution3Field().getRadioButtonCorrect());
            solution4 = createSolution(answer4, view.getSolution4Field().getRadioButtonCorrect());

            if(checkSolutions(solution1, solution2, solution3, solution4)){
                Quiz quiz = SelectedQuiz.getQuiz();
                quiz.getTaskList().remove(task);
                new QuizDao().updateQuiz(quiz);

                task = new Task(fileName, question, solution1, solution2, solution3, solution4);

                uploadTask(task, quiz);
            }

            else
                view.getLbWarning().setText("Otázka musí mít alespoň 1 správné řešení");

        }
        else if(CheckString.isNotBlank(question) && CheckString.isNotBlank(answer1) && CheckString.isNotBlank(answer2) &&
                CheckString.isNotBlank(answer3) && CheckString.isNotBlank(answer4)){

            solution1 = createSolution(answer1, view.getSolution1Field().getRadioButtonCorrect());
            solution2 = createSolution(answer2, view.getSolution2Field().getRadioButtonCorrect());
            solution3 = createSolution(answer3, view.getSolution3Field().getRadioButtonCorrect());
            solution4 = createSolution(answer4, view.getSolution4Field().getRadioButtonCorrect());

            if(checkSolutions(solution1, solution2, solution3, solution4)){
                Quiz quiz = SelectedQuiz.getQuiz();
                quiz.getTaskList().remove(task);
                new QuizDao().updateQuiz(quiz);

                task = new Task(fileName, question, solution1, solution2, solution3, solution4);

                uploadTask(task, quiz);
            }
        }
        else{
            view.getLbWarning().setText("Vyplňte všechny údaje");
        }
        view.getLbWarning().setVisible(true);

    }

    private void uploadTask(Task task, Quiz quiz){
        new TaskDao().insertTask(task);

        quiz.getTaskList().add(task);
        new QuizDao().updateQuiz(quiz);

        view.getLbWarning().setText("Otázka vložena.");

        view.getQuestionField().getField().clear();
        view.getQuestionField().getField().clear();
        view.getSolution1Field().getTextField().clear();
        view.getSolution2Field().getTextField().clear();
        view.getSolution3Field().getTextField().clear();
        view.getSolution4Field().getTextField().clear();
    }

    private boolean checkSolutions(Solution... solutions){
        for (Solution s: solutions) {
            if(s instanceof CorrectSolution){
                return true;
            }
        }
        return false;
    }

    private Solution createSolution(String text, RadioButton rb){
        if(rb.isSelected())
            return new CorrectSolution(text);
        else
            return new WrongSolution(text);
    }


    private void backAction(){
        rootView.setTaskOverviewMiddle();
    }
}

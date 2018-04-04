package view.teacherAccess.quizOverview.taskOverview;

import helpClasses.CheckString;
import helpClasses.LoadFromFile;
import helpClasses.SelectedQuiz;
import javafx.scene.control.RadioButton;
import model.*;
import view.teacherAccess.root.TeacherAccessRootView;

import java.io.File;

public class AddTaskController {

    private AddTaskView view;

    private TeacherAccessRootView rootView;

    public AddTaskController(AddTaskView view, TeacherAccessRootView rootView) {
        System.out.println("aaa");
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnUploadFile().setOnAction(e -> uploadFileAction());
        view.getBtnAddTask().setOnAction(e -> addTaskAction());
        view.getBtnBack().setOnAction(e -> backAction());
    }

    private void uploadFileAction() {
        LoadFromFile.uploadFile(rootView, view);
    }

    private void addTaskAction() {
        String noFile = view.getAllFilesComboBox().getEmptyFile().getName();
        String fileName =  ((File) view.getAllFilesComboBox().getSelectionModel().getSelectedItem()).getName();
        String question = view.getQuestionField().getField().getText();
        String answer1 = view.getSolution1Field().getTextField().getText();
        String answer2 = view.getSolution2Field().getTextField().getText();
        String answer3 = view.getSolution3Field().getTextField().getText();
        String answer4 = view.getSolution4Field().getTextField().getText();

        Solution solution1, solution2, solution3, solution4;

        Task task;

        view.getLbWarning().setVisible(true);



        if(fileName.equals(noFile))
            fileName = null;

        if (CheckString.isNotBlank(fileName) && CheckString.isNotBlank(answer1) && CheckString.isNotBlank(answer2) &&
                CheckString.isNotBlank(answer3) && CheckString.isNotBlank(answer4)) {

            solution1 = createSolution(answer1, view.getSolution1Field().getRadioButtonCorrect());
            solution2 = createSolution(answer2, view.getSolution2Field().getRadioButtonCorrect());
            solution3 = createSolution(answer3, view.getSolution3Field().getRadioButtonCorrect());
            solution4 = createSolution(answer4, view.getSolution4Field().getRadioButtonCorrect());

            if(checkSolutions(solution1, solution2, solution3, solution4)){
                Quiz quiz = SelectedQuiz.getQuiz();

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

                task = new Task(fileName, question, solution1, solution2, solution3, solution4);

                uploadTask(task, quiz);
            }
            else
                view.getLbWarning().setText("Otázka musí mít alespoň 1 správné řešení");

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

        view.getAllFilesComboBox().setValue(view.getAllFilesComboBox().getEmptyFile());
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

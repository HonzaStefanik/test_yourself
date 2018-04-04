package view.studentAccess.game;


import helpClasses.SelectedQuiz;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Quiz;
import model.QuizDao;
import model.Teacher;
import view.studentAccess.root.StudentAccessRootView;

import java.util.List;

public class SelectQuizController {

    private SelectQuizView view;

    private StudentAccessRootView rootView;

    public SelectQuizController(SelectQuizView view, StudentAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnPlay().setOnAction(e -> playAction());
        view.getBtnBack().setOnAction(e -> backAction());
        rootView.getScene().setOnMouseClicked(e -> view.getLbWarning().setVisible(false));
        teacherComboBoxListener();
    }

    private void playAction(){
        Quiz selectedQuiz = (Quiz) view.getQuizComboBox().getSelectionModel().getSelectedItem();
        SelectedQuiz.setQuiz(selectedQuiz);
        if(selectedQuiz != null && selectedQuiz.getTaskList().size() > 0){
            rootView.setQuizGameMiddle();
        }
        else{
            view.getLbWarning().setText("Zvolte kvíz");
            view.getLbWarning().setVisible(true);
        }

    }


    private void teacherComboBoxListener(){
        view.getTeacherComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Teacher>() {
            @Override
            public void changed(ObservableValue<? extends Teacher> arg0, Teacher oldValue, Teacher newValue) {

                view.getQuizComboBox().getSelectionModel().clearSelection();

                if(newValue == view.getAllTeachersChoice()){
                    List<Quiz> quizList = new QuizDao().getAllQuizzes();
                    view.getQuizComboBox().removeEmptyQuizzesAndSetItems(quizList);
                }
                else {
                    Teacher selectedTeacher = (Teacher) view.getTeacherComboBox().getSelectionModel().getSelectedItem();
                    System.out.println(selectedTeacher.toString());
                    List<Quiz> quizList = selectedTeacher.getQuizList();
                    view.getQuizComboBox().removeEmptyQuizzesAndSetItems(quizList);
                }
            }
        });
    }

    private void backAction(){
        rootView.setMenuMiddle();
    }
}

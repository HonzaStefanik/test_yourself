package view.teacherAccess.quizOverview;

import helpClasses.CheckString;
import helpClasses.LoggedInUtil;
import helpClasses.SelectedQuiz;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Quiz;
import model.QuizDao;
import model.Teacher;
import model.TeacherDao;
import view.teacherAccess.root.TeacherAccessRootView;

import java.util.List;
import java.util.Optional;

public class QuizOverviewController {

    private QuizOverviewView view;

    TeacherAccessRootView rootView;

    public QuizOverviewController(QuizOverviewView view, TeacherAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnDeleteQuiz().setOnAction(e -> deleteQuizAction());
        view.getBtnBack().setOnAction(e -> backAction());
        view.getBtnAddQuiz().setOnAction(e -> addQuizAction());
        view.getBtnQuizDetail().setOnAction(e -> quizDetailAction());
        rootView.setOnMouseClicked(e -> view.getLbWarning().setVisible(false));
    }


    private void quizDetailAction(){
        Quiz selectedQuiz = (Quiz) view.getQuizTableView().getSelectionModel().getSelectedItem();

        if(selectedQuiz != null) {
            SelectedQuiz.setQuiz(selectedQuiz);
            rootView.setTaskOverviewMiddle();
        }
    }

    private void addQuizAction(){
        String newQuizName = view.getNewQuizTextField().getText();

        if (CheckString.isBlank(newQuizName)) {
            view.getLbWarning().setText("Zadejte jméno kvízu");
            view.getLbWarning().setVisible(true);
            return;
        }

        Quiz quiz = new QuizDao().getQuizByName(newQuizName);
        Teacher quizOwner = (Teacher) LoggedInUtil.getLoggedInUser();


        if (quiz == null) {
            Teacher teacher = (Teacher) LoggedInUtil.getLoggedInUser();

            Quiz newQuiz = new Quiz(newQuizName, teacher);
            new QuizDao().insertQuiz(newQuiz);

            quizOwner.addQuiz(newQuiz);
            new TeacherDao().updateTeacher(quizOwner);

            view.getQuizTableView().getItems().add(newQuiz);

            view.getNewQuizTextField().clear();
            view.getLbWarning().setText("Kvíz byl vytvořen");
            view.getLbWarning().setVisible(true);
            return;
        }
        else{
            view.getLbWarning().setText("Kvíz s tímto jménem již existuje");
            view.getLbWarning().setVisible(true);
        }
    }

    private void deleteQuizAction(){
        List<Quiz> selectedQuizzes, allQuizzes;

        Teacher t = (Teacher) LoggedInUtil.getLoggedInUser();

        allQuizzes = t.getQuizList();

        selectedQuizzes = view.getQuizTableView().getSelectionModel().getSelectedItems();

        for (Quiz q : selectedQuizzes) {
            if(createAlert(q))
                allQuizzes.remove(q);
            else return;
        }

        t.setQuizList(allQuizzes);
        new TeacherDao().updateTeacher(t);

        for(Quiz q :selectedQuizzes){
            new QuizDao().deleteQuiz(q);
        }


        view.getQuizTableView().setItems(FXCollections.observableArrayList(t.getQuizList()));
    }

    private boolean createAlert(Quiz q){
        if(q.getTaskList().size() > 0){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Upozornění");
        alert.setHeaderText("Kvíz "+ q.getName() +" obsahuje otázky");
        alert.setContentText("Přejete si smazat kvíz se všemi otázkami?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            return true;
        else
            return false;
        }
        else
            return true;
    }

    private void backAction() {
        rootView.setMenuMiddle();
    }
}

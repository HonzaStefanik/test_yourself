package view.teacherAccess.quizOverview.taskOverview;

import helpClasses.SelectedQuiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Quiz;
import model.QuizDao;
import model.Task;
import model.TaskDao;
import view.teacherAccess.root.TeacherAccessRootView;

public class TaskOverviewController {

    private TaskOverviewView view;

    private TeacherAccessRootView rootView;

    public TaskOverviewController(TaskOverviewView view, TeacherAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnAddPictureTask().setOnAction(e -> addPictureTask());
        view.getBtnDeleteTask().setOnAction(e -> deleteTask());
        view.getBtnEditTask().setOnAction(e -> editTask());
        view.getBtnBack().setOnAction(e -> backAction());
    }


    private void addPictureTask(){
        rootView.setAddPictureTaskMiddle();
    }

    private void editTask(){
        Task t = (Task) view.getTaskTableView().getSelectionModel().getSelectedItem();
        if(t != null)
            rootView.setUpdateTaskMiddle(t);
    }

    private void deleteTask(){
        Quiz q = SelectedQuiz.getQuiz();

        ObservableList<Task> selectedTasks = view.getTaskTableView().getSelectionModel().getSelectedItems();

        for (Task t : selectedTasks) {
            q.getTaskList().remove(t);
        }

        new QuizDao().updateQuiz(q);
        view.getTaskTableView().setItems(FXCollections.observableArrayList(q.getTaskList()));
        view.getTaskTableView().refresh();
    }
    private void backAction(){
        SelectedQuiz.setQuiz(null);
        rootView.setQuizOverviewMiddle();
    }
}

package view.teacherAccess.quizOverview;


import helpClasses.CheckString;
import helpClasses.LoggedInUtil;
import helpClasses.SelectedQuiz;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Quiz;
import model.QuizDao;
import model.Teacher;
import model.TeacherDao;

import java.util.List;

public class QuizTableView extends TableView {

    private TableColumn<Quiz, String> nameColumn;

    private TableColumn<Quiz, String> availabilityColumn;

    private TableColumn<Quiz, Integer> taskCountColumn;

    QuizTableView() {
        createTable();
        onEditAction();
    }

    private void createTable() {
        setEditable(true);
        setPrefWidth(500);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        nameColumn = new TableColumn<>("Název");
        availabilityColumn = new TableColumn<>("Zpřístupněn");
        taskCountColumn = new TableColumn<>("Počet otázek");

        getColumns().addAll(nameColumn, availabilityColumn, taskCountColumn);

        double tableWidth = getPrefWidth();

        nameColumn.setPrefWidth(tableWidth * 0.4 - 1);
        nameColumn.setCellValueFactory(cdf -> cdf.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setEditable(true);

        availabilityColumn.setPrefWidth(tableWidth * 0.3 - 1);
        availabilityColumn.setCellValueFactory(cdf -> cdf.getValue().availableProperty());
        availabilityColumn.setCellFactory(cdf -> new AvailabilityChoiceBoxCell());


        taskCountColumn.setPrefWidth(tableWidth * 0.3);
        taskCountColumn.setCellValueFactory(cdf -> {
            int taskCount = cdf.getValue().getTaskList().size();
            ObservableValue<Integer> observableTaskCount = new ReadOnlyObjectWrapper<>(taskCount);
            return observableTaskCount;
        });

        Teacher teacher = (Teacher) LoggedInUtil.getLoggedInUser();

        List<Quiz> list = teacher.getQuizList();

        ObservableList<Quiz> observableList = FXCollections.observableArrayList(list);

        setItems(observableList);

    }

    private void onEditAction(){
        nameColumn.setOnEditCommit(this::updateCol);
    }

    private void updateCol(TableColumn.CellEditEvent<Quiz, String> col) {
        String newValue = col.getNewValue();

        if (CheckString.isNotBlank(newValue) && checkQuizName(newValue)) {
            Quiz quiz = (Quiz) getSelectionModel().getSelectedItem();
            quiz.setName(newValue);
            new QuizDao().updateQuiz(quiz);

        } else {
            col.getTableView().refresh();
        }
    }

    private boolean checkQuizName(String name){
        Quiz quiz = new QuizDao().getQuizByName(name);
        if (quiz == null)
                return true;
        else
            return false;
    }

}

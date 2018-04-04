package view.teacherAccess.quizOverview.taskOverview;

import helpClasses.CheckString;
import helpClasses.SelectedQuiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import model.*;

import java.util.List;

public class TaskTableView extends TableView {

    private TableColumn<Task, String> questionColumn;

    private TableColumn<Task, Solution> firstSolution, secondSolution, thirdSolution, fourthSolution;

    public TaskTableView() {
        createTable();
        onEditAction();
    }

    private void createTable(){
        setEditable(true);
        setPrefWidth(1000);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        questionColumn = new TableColumn<>("Otázka");
        firstSolution = new TableColumn<>("1. řešení");
        secondSolution = new TableColumn<>("2. řešení");
        thirdSolution = new TableColumn<>("3. řešení");
        fourthSolution = new TableColumn<>("4. řešení");

        getColumns().addAll(questionColumn, firstSolution, secondSolution, thirdSolution, fourthSolution);

        int columnCount = getColumns().size();
        double columnSize = Math.floor(getPrefWidth() / columnCount);

        questionColumn.setPrefWidth(columnSize - 1);
        questionColumn.setCellValueFactory(cdf -> cdf.getValue().taskProperty());
        questionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        questionColumn.setEditable(false);

        firstSolution.setPrefWidth(columnSize - 1);
        firstSolution.setCellValueFactory(cdf -> cdf.getValue().solution1Property());
        firstSolution.setCellFactory(column -> new SolutionCell());
        firstSolution.setEditable(true);


        secondSolution.setPrefWidth(columnSize - 1);
        secondSolution.setCellValueFactory(cdf -> cdf.getValue().solution2Property());
        secondSolution.setCellFactory(column -> new SolutionCell());
        secondSolution.setEditable(true);

        thirdSolution.setPrefWidth(columnSize - 1);
        thirdSolution.setCellValueFactory(cdf -> cdf.getValue().solution3Property());
        thirdSolution.setCellFactory(column -> new SolutionCell());
        thirdSolution.setEditable(true);

        fourthSolution.setPrefWidth(columnSize - 1);
        fourthSolution.setCellValueFactory(cdf -> cdf.getValue().solution4Property());
        fourthSolution.setCellFactory(column -> new SolutionCell());
        fourthSolution.setEditable(true);

        List<Task> list = SelectedQuiz.getQuiz().getTaskList();

        ObservableList<Task> observableList = FXCollections.observableArrayList(list);

        setItems(observableList);

    }

    private void onEditAction(){
        questionColumn.setOnEditCommit(this::updateCol);
    }

    private void updateCol(TableColumn.CellEditEvent<Task, String> col) {
        String newValue = col.getNewValue();

        if (CheckString.isNotBlank(newValue)) {
            Task task = (Task) getSelectionModel().getSelectedItem();
            task.setQuestion(newValue);
            new TaskDao().updateTask(task);
            refresh();

        } else {
            refresh();
        }
    }
}

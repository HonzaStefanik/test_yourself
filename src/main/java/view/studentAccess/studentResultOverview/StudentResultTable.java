package view.studentAccess.studentResultOverview;

import helpClasses.LoggedInUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Result;
import model.ResultDao;
import model.Student;
import model.Teacher;

import java.text.DecimalFormat;
import java.util.List;

public class StudentResultTable extends TableView {

    private TableColumn<Result, String> quizColumn, successRate;

    private TableColumn<Result, Teacher> teacherColumn;

    private TableColumn<Result, Integer> correctAmount, wrongAmount;

    private List<Result> allResultsList;

    public StudentResultTable() {
        setEditable(false);
        setPrefWidth(800);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        quizColumn = new TableColumn<>("Kvíz");
        teacherColumn = new TableColumn<>("Učitel");
        correctAmount = new TableColumn<>("Správné odpovědi");
        wrongAmount = new TableColumn<>("Špatné odpovědi");
        successRate = new TableColumn<>("Úspěšnost");

        getColumns().addAll(quizColumn, teacherColumn,correctAmount, wrongAmount, successRate);

        double tableWidth = getPrefWidth();

        quizColumn.setPrefWidth(tableWidth * 0.25);
        quizColumn.setCellValueFactory(cdf -> cdf.getValue().quizNameProperty());

        teacherColumn.setPrefWidth(tableWidth * 0.25);
        teacherColumn.setCellValueFactory(cdf -> new ReadOnlyObjectWrapper<>(cdf.getValue().getTeacher()));

        correctAmount.setPrefWidth(tableWidth * 0.166);
        correctAmount.setCellValueFactory(cdf -> {
            int taskCount = cdf.getValue().getCorrectAnswers();
            ObservableValue<Integer> observableTaskCount = new ReadOnlyObjectWrapper<>(taskCount);
            return observableTaskCount;
        });

        wrongAmount.setPrefWidth(tableWidth * 0.166);
        wrongAmount.setCellValueFactory(cdf -> {
            int taskCount = cdf.getValue().getWrongAnswers();
            ObservableValue<Integer> observableTaskCount = new ReadOnlyObjectWrapper<>(taskCount);
            return observableTaskCount;
        });

        successRate.setPrefWidth(tableWidth * 0.167 - 2);
        successRate.setCellValueFactory(cdf -> {
            double correctAnswers = cdf.getValue().getCorrectAnswers();
            double taskNumber = cdf.getValue().getWrongAnswers() + correctAnswers;
            double successRate = (correctAnswers / taskNumber) * 100;

            DecimalFormat f = new DecimalFormat("##.##");
            String successRateString = f.format(successRate) + "%";

            ObservableValue<String> observableTaskCount = new ReadOnlyObjectWrapper<>(successRateString);
            return observableTaskCount;
        });

        Student student = (Student) LoggedInUtil.getLoggedInUser();
        allResultsList = new ResultDao().getResultsByStudent(student.getUser_id());

        ObservableList<Result> observableList = FXCollections.observableArrayList(allResultsList);

        setItems(observableList);
    }

    public List<Result> getAllResultsList() {
        return allResultsList;
    }
}

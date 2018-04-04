package view.teacherAccess.resultOverview;

import helpClasses.LoggedInUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import model.*;

import java.text.DecimalFormat;
import java.util.List;

public class TeacherResultTable extends TableView {

    private TableColumn<Result, String> studentColumn,  quizColumn, successRate;

    private TableColumn<Result, Integer> correctAmount, wrongAmount;

    private List<Result> allResultsList;

    public TeacherResultTable() {
        setEditable(false);
        setPrefWidth(900);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        studentColumn = new TableColumn<>("Student");
        quizColumn = new TableColumn<>("Kvíz");
        correctAmount = new TableColumn<>("Správné odpovědi");
        wrongAmount = new TableColumn<>("Špatné odpovědi");
        successRate = new TableColumn<>("Úspěšnost");


        TextField nameFilterField = new TextField();

        getColumns().addAll(studentColumn, quizColumn, correctAmount, wrongAmount, successRate);

        double tableWidth = getPrefWidth();
        System.out.println(tableWidth);


        studentColumn.setPrefWidth(tableWidth * 0.3 -1);
        studentColumn.setCellValueFactory(cdf -> cdf.getValue().studentWithClassProperty());

        quizColumn.setPrefWidth(tableWidth * 0.25-1);
        quizColumn.setCellValueFactory(cdf -> cdf.getValue().quizNameProperty());

        correctAmount.setPrefWidth(tableWidth * 0.15);
        correctAmount.setCellValueFactory(cdf -> {
            int taskCount = cdf.getValue().getCorrectAnswers();
            ObservableValue<Integer> observableTaskCount = new ReadOnlyObjectWrapper<>(taskCount);
            return observableTaskCount;
        });

        wrongAmount.setPrefWidth(tableWidth * 0.15);
        wrongAmount.setCellValueFactory(cdf -> {
            int taskCount = cdf.getValue().getWrongAnswers();
            ObservableValue<Integer> observableTaskCount = new ReadOnlyObjectWrapper<>(taskCount);
            return observableTaskCount;
        });

        successRate.setPrefWidth(tableWidth * 0.15);
        successRate.setCellValueFactory(cdf -> {
            double correctAnswers = cdf.getValue().getCorrectAnswers();
            double taskNumber = cdf.getValue().getWrongAnswers() + correctAnswers;
            double successRate = (correctAnswers / taskNumber) * 100;

            DecimalFormat f = new DecimalFormat("##.##");
            String successRateString = f.format(successRate) + "%";

            ObservableValue<String> observableTaskCount = new ReadOnlyObjectWrapper<>(successRateString);
            return observableTaskCount;
        });

        Teacher teacher = (Teacher) LoggedInUtil.getLoggedInUser();

        allResultsList = new ResultDao().getResultsByTeacher(teacher.getUser_id());

        System.out.println(allResultsList.size());

        setAllResults();

    }


    public void setAllResults(){
        ObservableList<Result> observableList = FXCollections.observableArrayList(allResultsList);
        setItems(observableList);
    }

    public List<Result> getAllResultsList() {
        return allResultsList;
    }
}

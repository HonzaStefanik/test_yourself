package view.adminAccess.teacherOverview;

import helpClasses.CheckString;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Teacher;
import model.TeacherDao;

import java.util.List;

public class TeacherTableView extends TableView {

    private TableColumn<Teacher, String> nameColumn, surnameColumn, nickColumn;

    public TeacherTableView() {
        createTable();
        onEditAction();
    }

    private void createTable(){
        setEditable(true);
        setPrefWidth(500);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        nameColumn = new TableColumn<>("Jméno");
        surnameColumn = new TableColumn<>("Příjmení");
        nickColumn = new TableColumn<>("Nick");

        getColumns().addAll(nameColumn, surnameColumn, nickColumn);

        int columnCount = getColumns().size();
        double columnSize = Math.floor(getPrefWidth() / columnCount);

        nameColumn.setPrefWidth(columnSize);
        nameColumn.setCellValueFactory(cdf -> cdf.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setEditable(true);

        surnameColumn.setPrefWidth(columnSize);
        surnameColumn.setCellValueFactory(cdf -> cdf.getValue().surnameProperty());
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setEditable(true);

        nickColumn.setPrefWidth(columnSize);
        nickColumn.setCellValueFactory(cdf -> cdf.getValue().nickProperty());
        nickColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nickColumn.setEditable(true);


        List<Teacher> list = new TeacherDao().getAllTeachers();
        ObservableList<Teacher> observableList = FXCollections.observableArrayList(list);

        setItems(observableList);
    }

    private void onEditAction(){
        nameColumn.setOnEditCommit(this::updateCol);
        surnameColumn.setOnEditCommit(this::updateCol);
        nickColumn.setOnEditCommit(this::updateCol);
    }

    private void updateCol(TableColumn.CellEditEvent<Teacher, String> col) {
        String newValue = col.getNewValue();

        if (CheckString.isNotBlank(newValue)) {
            Teacher teacher = (Teacher) getSelectionModel().getSelectedItem();
            TableColumn<Teacher, String> sourceCol = col.getTableColumn();

            if(sourceCol.equals(nameColumn)) {
                teacher.setName(newValue);
            }
            else if(sourceCol.equals(surnameColumn)) {
                teacher.setSurname(newValue);
            }
            else if(sourceCol.equals(nickColumn)) {
                teacher.setNick(newValue);
            }
            new TeacherDao().updateTeacher(teacher);
        } else {
            col.getTableView().refresh();
        }
    }
}
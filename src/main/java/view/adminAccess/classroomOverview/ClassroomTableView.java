package view.adminAccess.classroomOverview;

import helpClasses.CheckString;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Classroom;
import model.ClassroomDao;

import java.util.List;

public class ClassroomTableView extends TableView {

    private TableColumn<Classroom, String> nameColumn, yearColumn;

    public ClassroomTableView() {
        createTable();
        onEditAction();
    }

    private void createTable(){
        setEditable(true);
        setPrefWidth(500);
        getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        nameColumn = new TableColumn<>("Jméno");
        yearColumn = new TableColumn<>("Rok");

        getColumns().addAll(nameColumn, yearColumn);

        int columnCount = getColumns().size();
        double columnSize = Math.floor(getPrefWidth() / columnCount);


        nameColumn.setPrefWidth(columnSize-1);
        nameColumn.setCellValueFactory(cdf -> cdf.getValue().classroomNameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setEditable(true);

        yearColumn.setPrefWidth(columnSize-1);
        yearColumn.setCellValueFactory(cdf -> cdf.getValue().classroomYearProperty());
        yearColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        yearColumn.setEditable(true);

        List<Classroom> list = new ClassroomDao().getAllClassrooms();

        ObservableList<Classroom> observableList = FXCollections.observableArrayList(list);

        setItems(observableList);
    }

    private void onEditAction(){
        nameColumn.setOnEditCommit(this::updateCol);
        yearColumn.setOnEditCommit(this::updateCol);
    }

    private void updateCol(TableColumn.CellEditEvent<Classroom, String> col) {
        String newValue = col.getNewValue();

        if (CheckString.isNotBlank(newValue)) {
            Classroom classroom = (Classroom) getSelectionModel().getSelectedItem();
            TableColumn<Classroom, String> sourceCol = col.getTableColumn();

            if (sourceCol.equals(nameColumn)) {
                classroom.setClassroomName(newValue);
            } else if (sourceCol.equals(yearColumn)) {
                classroom.setClassroomYear(newValue);
            }

            new ClassroomDao().updateClassroom(classroom);
        } else {
            col.getTableView().refresh();
        }
    }
}

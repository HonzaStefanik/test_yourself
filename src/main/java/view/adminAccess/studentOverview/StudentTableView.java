package view.adminAccess.studentOverview;

import helpClasses.CheckString;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import model.*;

import java.util.List;

public class StudentTableView extends TableView {

    private TableColumn<Student, String> nameColumn, surnameColumn, nickColumn;

    private TableColumn<Student, Classroom> classroomColumn;

    private List<Student> studentList;

    private ClassroomChoiceBoxCell classroomChoiceBoxCell;

    public StudentTableView() {
        createTable();
        onEditAction();
    }

    private void createTable(){
        setEditable(true);
        setPrefWidth(750);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        nameColumn = new TableColumn<>("Jméno");
        surnameColumn = new TableColumn<>("Příjmení");
        nickColumn = new TableColumn<>("Nick");
        classroomColumn = new TableColumn<>("Třída");

        getColumns().addAll(nameColumn, surnameColumn, nickColumn, classroomColumn);

        int columnCount = getColumns().size();
        double columnSize = Math.floor(getPrefWidth() / columnCount);

        nameColumn.setPrefWidth(columnSize - 1);
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


        List<Classroom> classroomList = new ClassroomDao().getAllClassrooms();
        ObservableList<Classroom> classroomObservableList = FXCollections.observableArrayList(classroomList);



        classroomColumn.setPrefWidth(columnSize);
        classroomColumn.setCellValueFactory(cdf -> cdf.getValue().classroomProperty());
        classroomColumn.setCellFactory(cdf -> classroomChoiceBoxCell = new ClassroomChoiceBoxCell(classroomObservableList));
        classroomColumn.setEditable(true);


        studentList = new StudentDao().getAllStudents();

        ObservableList<Student> observableList = FXCollections.observableArrayList(studentList);

        setItems(observableList);
    }

    private void onEditAction(){
        nameColumn.setOnEditCommit(this::updateStudentCol);
        surnameColumn.setOnEditCommit(this::updateStudentCol);
        nickColumn.setOnEditCommit(this::updateStudentCol);
    }

    private void updateStudentCol(TableColumn.CellEditEvent<Student, String> col) {
        String newValue = col.getNewValue();

        if (CheckString.isNotBlank(newValue)) {
            Student student =  (Student) getSelectionModel().getSelectedItem();
            TableColumn<Student, String> sourceCol = col.getTableColumn();

            if(sourceCol.equals(nameColumn)) {
                student.setName(newValue);
            }
            else if(sourceCol.equals(surnameColumn)) {
                student.setSurname(newValue);
            }
            else if(sourceCol.equals(nickColumn)) {
                student.setNick(newValue);
            }
            new StudentDao().updateStudent(student);
        } else {
            col.getTableView().refresh();
        }
    }

    public ClassroomChoiceBoxCell getClassroomChoiceBoxCell() {
        return classroomChoiceBoxCell;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}


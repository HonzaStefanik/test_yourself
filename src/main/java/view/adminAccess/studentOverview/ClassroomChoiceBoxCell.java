package view.adminAccess.studentOverview;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import model.Classroom;
import model.Student;
import model.StudentDao;


public class ClassroomChoiceBoxCell extends TableCell<Student, Classroom> {

    private ChoiceBox<Classroom> classroomChoiceBox;

    public ClassroomChoiceBoxCell(ObservableList<Classroom> classroomObservableList) {
        classroomChoiceBox = new ChoiceBox<>();
        classroomChoiceBox.setItems(classroomObservableList);

        classroomChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && newValue != getItem()) {
                updateItem(newValue, false);
                commitEdit(newValue);
            }
        });
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(classroomChoiceBox);
    }

    @Override
    public void commitEdit(Classroom value) {
        super.commitEdit(value);
        Student student = (Student) getTableRow().getItem();
        student.setClassroom(value);
        new StudentDao().updateStudent(student);
    }

    @Override
    public void updateItem(Classroom item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
            classroomChoiceBox.setValue(item);
            setGraphic(classroomChoiceBox);
        }
    }

    public void addClassroom(Classroom classroom){
        classroomChoiceBox.getItems().add(classroom);
    }
}
package view.controlElements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Classroom;
import model.Student;
import model.StudentDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentComboBox extends ComboBox {

    private List<Student> studentList;

    private Student allStudentsChoice;

    public StudentComboBox(Student student) {

        allStudentsChoice = student;

        setAllStudents();

        setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> arg0) {
                return new ListCell<Student>() {
                    @Override
                    protected void updateItem(Student item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            String studentText = item.toString();
                            setText(studentText);
                        }
                    }
                };
            }
        });
    }

    public void setAllStudents() {
        studentList = new StudentDao().getAllStudents();
        studentList.add(0, allStudentsChoice);
        setItems(FXCollections.observableArrayList(studentList));
        setValue(allStudentsChoice);
    }

    public void setStudentsByClassroom(Classroom classroom) {
        List<Student> list = new ArrayList<>();
        list.addAll(studentList);

        for (Iterator<Student> iterator = list.iterator(); iterator.hasNext(); ) {
            Student s = iterator.next();
            if(!(s.getClassroom().equals(classroom))){
                iterator.remove();
            }
        }
        list.add(0, allStudentsChoice);
        setValue(allStudentsChoice);
        setItems(FXCollections.observableArrayList(list));
    }

    public Student getAllStudentsChoice() {
        return allStudentsChoice;
    }
}

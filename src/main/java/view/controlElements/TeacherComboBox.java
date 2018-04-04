package view.controlElements;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Teacher;
import model.TeacherDao;

import java.util.List;

public class TeacherComboBox extends ComboBox {

    public TeacherComboBox() {
        List<Teacher> teacherList = new TeacherDao().getAllTeachers();
        setItems(FXCollections.observableArrayList(teacherList));

        setCellFactory(new Callback<ListView<Teacher>, ListCell<Teacher>>() {
            @Override
            public ListCell<Teacher> call(ListView<Teacher> arg0) {
                return new ListCell<Teacher>() {
                    @Override
                    protected void updateItem(Teacher item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            String teacherText = item.toString();
                            setText(teacherText);
                        }
                    }
                };
            }
        });
    }

}

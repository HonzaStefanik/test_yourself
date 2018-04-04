package view.controlElements;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Classroom;
import model.ClassroomDao;

import java.util.List;

public class ClassroomComboBox extends ComboBox {

    public ClassroomComboBox() {
        List<Classroom> classroomList = new ClassroomDao().getAllClassrooms();
        setItems(FXCollections.observableArrayList(classroomList));

        setCellFactory(new Callback<ListView<Classroom>, ListCell<Classroom>>() {
            @Override
            public ListCell<Classroom> call(ListView<Classroom> arg0) {
                return new ListCell<Classroom>() {
                    @Override
                    protected void updateItem(Classroom item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            String classroomText = item.toString();
                            setText(classroomText);
                        }
                    }
                };
            }
        });
    }

}
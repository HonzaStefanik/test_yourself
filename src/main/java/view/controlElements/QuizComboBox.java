package view.controlElements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Quiz;
import model.QuizDao;

import java.util.Iterator;
import java.util.List;

public class QuizComboBox extends ComboBox {

    public QuizComboBox() {
        List<Quiz> quizList = new QuizDao().getAllQuizzes();
        removeEmptyQuizzesAndSetItems(quizList);

        setCellFactory(new Callback<ListView<Quiz>, ListCell<Quiz>>() {
            @Override
            public ListCell<Quiz> call(ListView<Quiz> arg0) {
                return new ListCell<Quiz>() {
                    @Override
                    protected void updateItem(Quiz item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            String quizText = item.toString();
                            setText(quizText);
                        }
                    }
                };
            }
        });
    }

    public void removeEmptyQuizzesAndSetItems(List<Quiz> quizList){
        ObservableList<Quiz> observableList = FXCollections.observableArrayList(quizList);

        for(Iterator<Quiz> iterator = observableList.iterator(); iterator.hasNext();){
            Quiz q = iterator.next();
            if(q.getTaskList().isEmpty() || !(q.isAvailable())){
                iterator.remove();
            }
        }
        setItems(observableList);
    }

}
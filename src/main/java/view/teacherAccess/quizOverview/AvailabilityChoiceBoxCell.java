package view.teacherAccess.quizOverview;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import model.Quiz;
import model.QuizDao;


public class AvailabilityChoiceBoxCell extends TableCell<Quiz, String> {

    private ChoiceBox<String> availabilityChoiceBox;

    public AvailabilityChoiceBoxCell() {
        availabilityChoiceBox = new ChoiceBox<>();
        availabilityChoiceBox.getItems().add("Ano");
        availabilityChoiceBox.getItems().add("Ne");

        availabilityChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && newValue != getItem()) {
                updateItem(newValue, false);
                commitEdit(newValue);
            }
        });

    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(availabilityChoiceBox);
    }

    @Override
    public void commitEdit(String newValue) {
        super.commitEdit(newValue);
        Quiz quiz = (Quiz) getTableRow().getItem();

        if(newValue.equals("Ano")) {
            quiz.setAvailable(true);
        }
        else
            quiz.setAvailable(false);
        new QuizDao().updateQuiz(quiz);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        }
        else {
            availabilityChoiceBox.setValue(item);
            setGraphic(availabilityChoiceBox);
        }
    }
}

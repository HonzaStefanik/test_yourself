package model;

import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CorrectSolution extends Solution {

    public CorrectSolution() {
    }

    public CorrectSolution(String answer) {
        super.answerProperty().set(answer);
    }

    public CorrectSolution(String answer, String description) {
        super.answerProperty().set(answer);
    }


}

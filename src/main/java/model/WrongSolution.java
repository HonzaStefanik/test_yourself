package model;

import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WrongSolution extends Solution {

    public WrongSolution() {
    }

    public WrongSolution(String answer) {
        super.answerProperty().set(answer);
    }

    public WrongSolution(String answer, String description) {
        super.answerProperty().set(answer);
    }

}

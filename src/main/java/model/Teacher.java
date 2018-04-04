package model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher extends User {

    private List<Quiz> quizList;

    public Teacher() {
    }

    public Teacher(String name, String surname, String nick, String password) {
        super(name, surname, nick, password);
        quizList = new ArrayList<>();
    }

    @OneToMany(targetEntity = Quiz.class, cascade = CascadeType.REMOVE)
    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Transient
    public String toString() {
        return getName() + " " + getSurname();
    }

    @Transient
    public void addQuiz(Quiz q){
        quizList.add(q);
    }
}
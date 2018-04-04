package model;

import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Result {

    private int result_id, correctAnswers, wrongAnswers;

    private Student student;

    private SimpleStringProperty quizName = new SimpleStringProperty();

    private Teacher teacher;

    public Result() {
    }

    public Result(int correctAnswers, int wrongAnswers, Student student, String quizName, Teacher teacher) {
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.student = student;
        this.quizName.set(quizName);
        this.teacher = teacher;
    }

    @Column(nullable = false)
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Column(nullable = false)
    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    @Id
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue(generator="id")
    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    @ManyToOne(targetEntity = Student.class)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Column(nullable = false)
    public String getQuizName() {
        return quizName.get();
    }

    public SimpleStringProperty quizNameProperty() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName.set(quizName);
    }

    @ManyToOne(targetEntity = Teacher.class)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Transient
    public SimpleStringProperty studentWithClassProperty(){
        String studentWithClass = getStudent().getName() + " " + getStudent().getSurname() + " - " + getStudent().getClassroom().toString();
        return new SimpleStringProperty(studentWithClass);
    }
}

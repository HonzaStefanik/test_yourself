package model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Student extends User{

    private SimpleObjectProperty<Classroom> classroom;

    public Student() {
    }

    public Student(String name, String surname, String nick, String password, Classroom studentClassroom) {
        super(name, surname, nick, password);
        this.classroom = new SimpleObjectProperty<>(studentClassroom);
    }

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    public Classroom getClassroom() {
        return classroom.get();
    }

    public SimpleObjectProperty<Classroom> classroomProperty() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = new SimpleObjectProperty<>(classroom);
    }

    @Transient
    public String toString(){
        return getName() + " " + getNick() + " " + getSurname();
    }
}
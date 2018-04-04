package model;

import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

    private SimpleStringProperty name = new SimpleStringProperty();

    private List<Task> taskList;

    private Teacher teacherOwner;

    private boolean available;

    private int quiz_id;

    public Quiz() {
        taskList = new ArrayList<>();
        available = false;
    }

    public Quiz(String name, Teacher teacherOwner) {
        this.name.set(name);
        this.teacherOwner = teacherOwner;
        taskList = new ArrayList<>();
        available = false;
    }

    @Column(nullable = false)
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }


    @OneToMany(targetEntity = Task.class, orphanRemoval = true, cascade = {javax.persistence.CascadeType.REMOVE})
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Column(nullable = false)
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @ManyToOne(targetEntity = Teacher.class)
    public Teacher getTeacherOwner() {
        return teacherOwner;
    }

    public void setTeacherOwner(Teacher teacherOwner) {
        this.teacherOwner = teacherOwner;
    }

    @Id
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue(generator="id")
    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    @Transient
    public SimpleStringProperty availableProperty(){
        if(available)
            return new SimpleStringProperty("Ano");
        else
            return new SimpleStringProperty("Ne");
    }

    @Transient
    public String toString(){
        return getName();
    }
}
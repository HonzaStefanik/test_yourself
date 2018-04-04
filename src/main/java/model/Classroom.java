package model;

import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Classroom {

    private SimpleStringProperty classroomName = new SimpleStringProperty();
    private SimpleStringProperty classroomYear = new SimpleStringProperty();

    private int classroom_id;

    public Classroom() {
    }

    public Classroom(String name, String year) {
        classroomName.set(name);
        classroomYear.set(year);
    }


    @Column(nullable = false)
    public String getClassroomName() {
        return classroomName.get();
    }

    public SimpleStringProperty classroomNameProperty() {
        return classroomName;
    }

    public void setClassroomName(String name) {
        this.classroomName.set(name);
    }

    @Column(nullable = false)
    public String getClassroomYear() {
        return classroomYear.get();
    }

    public SimpleStringProperty classroomYearProperty() {
        return classroomYear;
    }

    public void setClassroomYear(String year) {
        this.classroomYear.set(year);
    }

    @Id
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue(generator="id")
    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

    @Transient
    @Override
    public String toString(){
        return getClassroomName() + " " + getClassroomYear();
    }
}

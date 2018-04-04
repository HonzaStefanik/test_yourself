package model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Task {

    private SimpleStringProperty fileName = new SimpleStringProperty();
    private SimpleStringProperty question = new SimpleStringProperty();

    private SimpleObjectProperty<Solution> solution1, solution2, solution3, solution4;

    private int task_id;

    public Task(String question, Solution solution1, Solution solution2, Solution solution3, Solution solution4) {
        this.question.set(question);
        this.solution1 = new SimpleObjectProperty<>(solution1);
        this.solution2 = new SimpleObjectProperty<>(solution2);
        this.solution3 = new SimpleObjectProperty<>(solution3);
        this.solution4 = new SimpleObjectProperty<>(solution4);
    }


    public Task(String fileName, String question, Solution solution1, Solution solution2, Solution solution3, Solution solution4) {
        this.fileName.set(fileName);
        this.question.set(question);
        this.solution1 = new SimpleObjectProperty<>(solution1);
        this.solution2 = new SimpleObjectProperty<>(solution2);
        this.solution3 = new SimpleObjectProperty<>(solution3);
        this.solution4 = new SimpleObjectProperty<>(solution4);
    }

    public Task() {}

    @Column
    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    @OneToOne(targetEntity = Solution.class, cascade = {CascadeType.ALL})
    public Solution getSolution1() {
        return solution1.get();
    }

    public SimpleObjectProperty<Solution> solution1Property() {
        return solution1;
    }

    public void setSolution1(Solution solution1) {
        this.solution1 = new SimpleObjectProperty<>(solution1);
    }

    @OneToOne(targetEntity = Solution.class, cascade = {CascadeType.ALL})
    public Solution getSolution2() {
        return solution2.get();
    }

    public SimpleObjectProperty<Solution> solution2Property() {
        return solution2;
    }

    public void setSolution2(Solution solution2) {
        this.solution2 = new SimpleObjectProperty<>(solution2);
    }

    @OneToOne(targetEntity = Solution.class, cascade = {CascadeType.ALL})
    public Solution getSolution3() {
        return solution3.get();
    }

    public SimpleObjectProperty<Solution> solution3Property() {
        return solution3;
    }

    public void setSolution3(Solution solution3) {
        this.solution3 = new SimpleObjectProperty<>(solution3);
    }

    @OneToOne(targetEntity = Solution.class, cascade = {CascadeType.ALL})
    public Solution getSolution4() {
        return solution4.get();
    }

    public SimpleObjectProperty<Solution> solution4Property() {
        return solution4;
    }

    public void setSolution4(Solution solution4) {
        this.solution4 = new SimpleObjectProperty<>(solution4);
    }

    @Id
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue(generator="id")
    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    @Column
    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    @Transient
    public SimpleStringProperty taskProperty(){
        if(getFileName() == null){
            return questionProperty();
        }
        else {
            return fileNameProperty();

        }
    }
}

package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class FilePathBase {

    private String filePath;

    private int id;

    public FilePathBase() {
    }

    public FilePathBase(String path) {
        this.filePath = path;
    }

    @Column(nullable = false)
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    @Id
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue(generator="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Transient
    @Override
    public String toString(){
        return filePath;
    }

}

package model;

import helpClasses.PasswordUtil;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();
    private SimpleStringProperty nick = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();

    private int user_id;

    public User() {
    }

    public User(String name, String surname, String nick, String password) {
        nameProperty().set(name);
        surnameProperty().set(surname);
        nickProperty().set(nick);
        passwordProperty().set(PasswordUtil.encryptPassword(password));
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

    @Column(nullable = false)
    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    @Column(unique=true, nullable = false)
    public String getNick() {
        return nick.get();
    }

    public SimpleStringProperty nickProperty() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick.set(nick);
    }

    @Column(nullable = false)
    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Id
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue(generator="id")
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

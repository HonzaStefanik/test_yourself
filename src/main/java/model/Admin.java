package model;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin() {
    }

    public Admin(String name, String surname, String nick, String password) {
        super(name, surname, nick, password);
    }
}

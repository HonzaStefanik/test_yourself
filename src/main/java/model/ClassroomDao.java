package model;

import database.HibernateUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

public class ClassroomDao {

    public void insertClassroom(Classroom c) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(c);
        transaction.commit();
    }
    public Classroom getClassById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Classroom.class);
        Classroom c = (Classroom) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        return c;
    }
    public List<Classroom> getAllClassrooms() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        return session.createCriteria(Classroom.class).list();
    }

    public boolean deleteClassroom(Classroom c) {
        List<Student> studentList = new StudentDao().getAllStudents();
        for(Student s : studentList){
            Classroom studentClassroom = s.getClassroom();
            if(studentClassroom.toString().equals(c.toString())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Upozornění");
                alert.setHeaderText("Třída "+ c.toString() +" obsahuje studenty");
                alert.setContentText("Přejete si smazat třídu se všemi studenty?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    new StudentDao().deleteStudentByClassroom(c.getClassroom_id());
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    Transaction transaction = session.beginTransaction();
                    session.clear();
                    session.delete(c);
                    session.flush();
                    transaction.commit();
                    return true;
                }
                else
                    return false;
            }
        }
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(c);
        session.flush();
        transaction.commit();

        return true;
    }

    public void updateClassroom(Classroom classroom){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classroom updatedClassroom = classroom;
        session.update(updatedClassroom);
        transaction.commit();
    }



}

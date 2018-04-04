package model;

import helpClasses.LoggedInUtil;
import helpClasses.PasswordUtil;
import database.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StudentDao {

    public void insertStudent(Student s) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(s);
        transaction.commit();
    }

    public Student getStudentByNick(String nick) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        Student s = (Student) criteria.add(Restrictions.eq("nick", nick)).uniqueResult();
        return s;
    }

    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        return session.createCriteria(Student.class).list();
    }

    public void deleteStudent(Student s) {
        new ResultDao().deleteResultByStudent(s, true);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(s);
        session.flush();
        transaction.commit();
    }


    public void updateStudent(Student student){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
    }

    public void deleteStudentByClassroom(int classroom_id){
        List<Student> studentList = getAllStudents();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        for(Student s : studentList){
            int classroomId = s.getClassroom().getClassroom_id();
            if(classroomId == classroom_id){
                new ResultDao().deleteResultByStudent(s,false);
                session.clear();
                session.delete(s);
                session.flush();
            }
        }
        transaction.commit();
    }
}


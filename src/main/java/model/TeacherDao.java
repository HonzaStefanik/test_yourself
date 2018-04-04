package model;

import database.HibernateUtil;
import helpClasses.LoggedInUtil;
import helpClasses.PasswordUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TeacherDao {

    public void insertTeacher(Teacher t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(t);
        transaction.commit();
    }
    public Teacher getTeacherById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Teacher.class);
        Teacher t = (Teacher) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        return t;
    }
    public Teacher getTeacherByNick(String nick) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Teacher.class);
        Teacher t = (Teacher) criteria.add(Restrictions.eq("nick", nick)).uniqueResult();
        return t;
    }

    public List<Teacher> getAllTeachers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        return session.createCriteria(Teacher.class).list();
    }
    public void deleteTeacher(Teacher t) {
        new ResultDao().deleteResultByTeacher(t);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(t);
        session.flush();
        transaction.commit();
    }


    public void updateTeacher(Teacher teacher){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(teacher);
        session.flush();
        transaction.commit();
    }
}

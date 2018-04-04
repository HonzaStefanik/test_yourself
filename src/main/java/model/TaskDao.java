package model;

import database.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class TaskDao {

    public void insertTask(Task t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(t);
        transaction.commit();
    }
    public Task getTaskById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Task.class);
        Task t = (Task) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        return t;
    }
    public List<Task> getAllTasks() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        return session.createCriteria(Task.class).list();
    }
    public void deleteTask(Task t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(t);
        session.flush();
        transaction.commit();
    }

    public void updateTask(Task task){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(task);
        session.flush();
        transaction.commit();
    }
}

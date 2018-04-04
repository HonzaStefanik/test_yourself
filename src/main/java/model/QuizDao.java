package model;

import database.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class QuizDao {

    public void insertQuiz(Quiz q) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(q);
        transaction.commit();
    }
    public Quiz getQuizById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Quiz.class);
        Quiz q = (Quiz) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        return q;
    }
    public List<Quiz> getAllQuizzes() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        return session.createCriteria(Quiz.class).list();
    }

    public Quiz getQuizByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Quiz.class);
        Quiz q = (Quiz) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        return q;
    }


    public void deleteQuiz(Quiz q) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.delete(q);
        session.flush();
        transaction.commit();
    }

    public void updateQuiz(Quiz quiz){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(quiz);
        session.flush();
        transaction.commit();
    }
}

package model;

import database.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ResultDao {

    public void insertResult(Result r) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.save(r);
        session.flush();
        transaction.commit();
    }
    public Result getResultById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Result.class);
        Result s = (Result) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        return s;
    }
    public List<Result> getAllResults() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        return session.createCriteria(Result.class).list();
    }
    public void deleteResult(Result r) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(r);
        transaction.commit();
    }

    public void deleteResultByTeacher(Teacher t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Result> resultList = session.createCriteria(Result.class).list();
        for (Result r: resultList) {
            if(r.getTeacher().equals(t))
                session.delete(r);
        }
        transaction.commit();
    }

    public void deleteResultByStudent(Student s, boolean close) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Result> resultList = session.createCriteria(Result.class).list();
        for (Result r: resultList) {
            if(r.getStudent().equals(s))
                session.delete(r);
        }
        if(close)
            transaction.commit();
    }


    public List<Result> getResultsByStudent(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String select = "Select result from Result result Join Fetch result.student student Where student.user_id = " + id;
        Query query = session.createQuery(select);
        return query.list();
    }

    public List<Result> getResultsByTeacher(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String select = "Select result from Result result Join Fetch result.teacher teacher Where teacher.user_id = " + id;
        Query query = session.createQuery(select);
        List<Result> list = query.list();
        return list;
    }

}

package model;

import database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SolutionDao {

    public void updateCorrectSolution(CorrectSolution solution){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(solution);
        session.flush();
        transaction.commit();
    }

    public void updateWrongSolution(WrongSolution solution){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        session.update(solution);
        session.flush();
        transaction.commit();
    }



}

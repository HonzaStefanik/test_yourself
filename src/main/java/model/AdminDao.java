package model;

import helpClasses.LoggedInUtil;
import helpClasses.PasswordUtil;
import database.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AdminDao {

    public void insertAdmin(Admin a) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria =  HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Admin.class);
        Long number = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

        if(number == 0) {
            session.saveOrUpdate(a);
            transaction.commit();
        }
        else {
            System.out.println("There already is an admin account in the system");
            transaction.rollback();
        }

    }

    public void updateAdmin(Admin a){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(a);
        transaction.commit();
    }

    public Admin getAdminByNick(String nick) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Admin.class);
        Admin a = (Admin) criteria.add(Restrictions.eq("nick", nick)).uniqueResult();
        return a;
    }

}

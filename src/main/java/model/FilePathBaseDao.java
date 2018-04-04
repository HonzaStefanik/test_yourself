package model;


import database.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class FilePathBaseDao {

    public void insertFilePathBase(FilePathBase path) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria =  HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(FilePathBase.class);
        Long number = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

        if(number == 0) {
            session.saveOrUpdate(path);
            transaction.commit();
        }
        else {
            System.out.println("There already is a file path in the system");
            transaction.rollback();
        }

    }

    public void updateFilePathBase(String path){
        FilePathBase filePathBase = getFilePathBase();
        filePathBase.setFilePath(path);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(filePathBase);
        transaction.commit();
    }

    public FilePathBase getFilePathBase() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(FilePathBase.class);
        FilePathBase path = (FilePathBase) criteria.add(Restrictions.eq("id", 1)).uniqueResult();
        return path;
    }
}

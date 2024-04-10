package me.sterus.lab3web.dao;

import me.sterus.lab3web.Hit;
import me.sterus.lab3web.database.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HitDaoImpl implements HitDao{
    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }
    @Override
    public void saveHit(Hit hit){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //TODO Убрать транзакции
        Transaction transaction = session.beginTransaction();
        //TODO save? persist?
        session.save(hit);
        transaction.commit();
        session.close();
    }
    @Override
    public List<Hit> getAll(){
        //Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //Transaction transaction = session.beginTransaction();
        List<Hit> locals = (List<Hit>) session.createQuery("from Hit").list();
        //transaction.commit();
        //session.close();
        return locals;
    }
    @Override
    public void closeSession(){
        session.close();
    }
}

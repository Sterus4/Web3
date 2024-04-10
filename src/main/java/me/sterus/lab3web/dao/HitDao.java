package me.sterus.lab3web.dao;

import me.sterus.lab3web.database.HibernateSessionFactoryUtil;
import me.sterus.lab3web.Hit;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface HitDao {
    public void setSession(Session session);

    public void saveHit(Hit hit);

    public List<Hit> getAll();
    public void closeSession();
}

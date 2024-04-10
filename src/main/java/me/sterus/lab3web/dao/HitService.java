package me.sterus.lab3web.dao;

import me.sterus.lab3web.Hit;
import me.sterus.lab3web.database.HibernateSessionFactoryUtil;

import java.util.List;

public class HitService {
    private HitDao hitDao;
    public HitService(){
        hitDao = new HitDaoImpl();
        try{
            hitDao.setSession(HibernateSessionFactoryUtil.getSessionFactory().openSession());
        } catch (Exception e){
            System.out.println("Невозможно Подключиться к базе данных: " + e.getMessage());
        }
    }

    public void saveHit(Hit hit){
        hitDao.saveHit(hit);
    }

    public List<Hit> getAll(){
        return hitDao.getAll();
    }
    public void close(){
        hitDao.closeSession();
    }

}

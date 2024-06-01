package me.sterus.lab3web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

//TODO создать таблицу
@Entity
@Table(name = "hits")
@ApplicationScoped
@Named("localHit")
public class Hit {
    @Column(name = "xcoordinate")
    private Double xCoordinate = 0d;
    @Column(name = "ycoordinate")
    private Double yCoordinate = 0d;
    @Column(name = "radius")
    private Double radius = 3d;
    @Column(name = "shot")
    private boolean shot;
    //TODO date
    @Column(name = "nowdate")
    private String nowDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //TODO set session id
    @Column(name = "sessionid")
    private String sessionId;

    public int getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public Hit(){}

    public Hit(Double xCoordinate, Double yCoordinate, Double radius, boolean shot, String nowDate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.radius = radius;
        this.shot = shot;
        this.nowDate = nowDate;
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

}

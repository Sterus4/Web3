package me.sterus.lab3web.jmx;

import me.sterus.lab3web.Hit;

public interface TotalDotsMBean {
    int getTotalDots();
    void increaseTotalDots();

    int getHitDots();
    void increaseHitDots(Hit hit);

}

package me.sterus.lab3web.jmx;

import me.sterus.lab3web.Hit;

import java.util.List;

public class PercentHit implements PercentHitMBean{
    private final List<Hit> hits;
    private double hitPercent = 0d;
    public PercentHit(List<Hit> hits) {
        this.hits = hits;
    }

    @Override
    public void setHitPercent() {
        hitPercent = (double) hits.stream().filter(i -> !i.isShot()).count() / hits.size();
    }

    @Override
    public double getHitPercent() {
        setHitPercent();
        return hitPercent;
    }
}

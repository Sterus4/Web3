package me.sterus.lab3web.jmx;

import me.sterus.lab3web.Hit;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.NotificationBroadcasterSupport;
import java.util.List;

public class TotalDots extends NotificationBroadcasterSupport implements TotalDotsMBean {

    private int totalDots;
    private int hitDots;
    private int missCount;
    private int seqCounter;
    private List<Hit> hits;
    public TotalDots(List<Hit> hits){
        this.hits = hits;
    }

    @Override
    public int getTotalDots() {
        totalDots = hits.size();
        return totalDots;
    }


    @Override
    public void increaseTotalDots() {
        totalDots = hits.size();
    }


    @Override
    public int getHitDots() {
        return hitDots;
    }

    @Override
    public void increaseHitDots(Hit localHit) {
        hitDots = (int) hits.stream().filter(i -> !i.isShot()).count();
        missCount = localHit.isShot() ? 0 : missCount + 1;
        if (missCount == 3){
            missCount = 0;
            sendNotification(new AttributeChangeNotification(this,
                    seqCounter++,
                    System.currentTimeMillis(),
                    "Пользователь промахнулся 3 раза подряд",
                    "missCount",
                    "long",
                    2, 3));
        }
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        return super.getNotificationInfo();
    }
}

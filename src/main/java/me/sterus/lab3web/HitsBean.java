package me.sterus.lab3web;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import me.sterus.lab3web.dao.HitService;
import me.sterus.lab3web.jmx.JMXMonitor;
import me.sterus.lab3web.jmx.PercentHit;
import me.sterus.lab3web.jmx.TotalDots;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("hitsBean")
@ApplicationScoped
public class HitsBean implements Serializable {
    private List<Hit> hits = new ArrayList<>();
    private HitService hitService = new HitService();
    private final TotalDots totalDotsMBean;
    private final PercentHit percentHit;

    @Inject
    private Hit localHit;

    public List<Hit> getHits() {
        return hits;
    }

    public HitsBean() {
        totalDotsMBean = new TotalDots(hits);
        percentHit = new PercentHit(hits);
        try {
            JMXMonitor.configure(totalDotsMBean, percentHit);
        } catch (MalformedObjectNameException e) {
            throw new RuntimeException(e);
        } catch (NotCompliantMBeanException e) {
            throw new RuntimeException(e);
        } catch (InstanceAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkHit(){
        localHit.setShot(check(localHit));
        Hit newHit = new Hit(localHit.getxCoordinate(), localHit.getyCoordinate(), localHit.getRadius(), localHit.isShot(), new Date().toString());
        newHit.setSessionId(FacesContext.getCurrentInstance().getExternalContext().getSessionId(true));
        hits.add(newHit);
        totalDotsMBean.increaseHitDots(newHit);
        totalDotsMBean.increaseTotalDots();
        percentHit.setHitPercent();
        //saveToDataBase(newHit);
    }
    public void getAll(){
        List<Hit> localList = getAllHits();
        if (localList != null){
            hits.clear();
            hits.addAll(localList);
            totalDotsMBean.increaseTotalDots();
            percentHit.setHitPercent();

        }
    }
    boolean check(Hit local){
        var x = local.getxCoordinate();
        var y = local.getyCoordinate();
        var r = local.getRadius();
        return (x <= 0 && y >= 0 && y <= x + r/2) || (x <= 0 && y <= 0 && x >= -r && y >= -r/2) || (x >= 0 && y <= 0 && x * x + y * y <= r * r / 4);
    }

    private List<Hit> getAllHits(){
        try {
            return hitService.getAll();
        } catch (Exception e){
            e.printStackTrace();
            FacesMessage errorMessage = new FacesMessage("Не удалось получить данные из базы данных");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIComponent uiComponent = uiViewRoot.findComponent("get_all_results_form:get_all_results_button");
            String clientId = uiComponent.getClientId(facesContext);
            facesContext.addMessage(clientId, errorMessage);
        }
        return null;
    }

    private void saveToDataBase(Hit newHit){
        try{
            hitService.saveHit(newHit);
        } catch (Exception e){
            FacesMessage errorMessage = new FacesMessage("Не удалось сохранить результат в базе данных");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIComponent uiComponent = uiViewRoot.findComponent("mainForm:submit_button");
            String clientId = uiComponent.getClientId(facesContext);
            facesContext.addMessage(clientId, errorMessage);
        }
    }

    @PreDestroy
    void flush(){
        hitService.close();
    }
}




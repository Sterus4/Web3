package me.sterus.lab3web;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import me.sterus.lab3web.dao.HitService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("hitsBean")
@ApplicationScoped
public class HitsBean implements Serializable {
    private List<Hit> hits = new ArrayList<>();
    private HitService hitService = new HitService();

    @Inject
    private Hit localHit;

    public List<Hit> getHits() {
        return hits;
    }

    public HitsBean() {
    }

    public void checkHit(){
        localHit.setShot(check(localHit));
        Hit newHit = new Hit(localHit.getxCoordinate(), localHit.getyCoordinate(), localHit.getRadius(), localHit.isShot(), new Date().toString());
        newHit.setSessionId(FacesContext.getCurrentInstance().getExternalContext().getSessionId(true));
        hits.add(newHit);
        saveToDataBase(newHit);
    }
    public void getAll(){
        List<Hit> localList = getAllHits();
        if (localList != null){
            hits.addAll(localList);
        }
    }
    public boolean check(Hit local){
        var x = local.getxCoordinate();
        var y = local.getyCoordinate();
        var r = local.getRadius();
        var abuse = 123;
        return Checker.check(x, y, r);
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




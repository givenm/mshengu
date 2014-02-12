/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.views;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.forms.SiteUnitDetailsForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.models.SiteUnitsDetailsBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views.SiteSiteUnitTab;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/**
 *
 * @author Ferox
 */
public class SiteUnitUnitDetailsTab extends VerticalLayout  {

    private final MshenguMain main;
    private final SiteUnitDetailsForm form;
    public SiteUnitUnitDetailsTab(MshenguMain app) {
        super();
        main = app;
        form = new SiteUnitDetailsForm();
        setSizeFull();
        addComponent(form);
//        addListeners();
        setReadFormProperties();
    }



    private void getHome() {
        main.content.setSecondComponent(new SiteSiteUnitTab(main, "LANDING"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }


    private SiteUnitsDetailsBean getBean(SiteUnit siteUnit) {
        SiteUnitsDetailsBean bean = new SiteUnitsDetailsBean();

        bean.setUnitTypeId(siteUnit.getUnitTypeId());
        bean.setUnitId(siteUnit.getUnitId());
        bean.setDescription(siteUnit.getDescription());
        bean.setOperationalStatus(siteUnit.getOperationalStatusName());
        bean.setUnitId(siteUnit.getUnitId());

        UnitLocationLifeCycle unitLocationLifeCycle = SiteUnitFacade.getSiteUnitService().getUnitCurrentLocation(siteUnit.getId());
        if (unitLocationLifeCycle != null) {
            bean.setDateofAction(unitLocationLifeCycle.getDateofAction());
            bean.setLatitude(unitLocationLifeCycle.getLatitude());
            bean.setLongitude(unitLocationLifeCycle.getLongitude());
//                bean.set unityLogs(unitServiceLogList)
//                bean.set parentId(getParentId());
        }
        bean.setId(siteUnit.getId());
        return bean;
    }

    public void loadSiteUnitDetails(String siteUnitId) {
        final SiteUnit siteUnit = SiteUnitFacade.getSiteUnitService().findById(siteUnitId);
        if (!StringUtils.isEmpty(siteUnit)) {
            form.binder.setItemDataSource(new BeanItem<>(getBean(siteUnit)));
            setReadFormProperties();
        }else{
             form.binder.setItemDataSource(new BeanItem<>(new SiteUnitsDetailsBean()));
            setReadFormProperties();
        }
    }
}
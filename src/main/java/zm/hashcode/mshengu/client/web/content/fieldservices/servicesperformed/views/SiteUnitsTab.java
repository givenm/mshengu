/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.app.facade.products.UnitTypeFacade;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.UnitMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.forms.SiteUnitDetailsForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.models.SiteUnitsDetailsBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.tables.SiteUnitTable;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author Ferox
 */
public class SiteUnitsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final SiteUnitDetailsForm form;
    private final SiteUnitTable table;

    public SiteUnitsTab(MshenguMain app) {
        super();
        main = app;
        form = new SiteUnitDetailsForm();
        table = new SiteUnitTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final SiteUnit siteUnit = SiteUnitFacade.getSiteUnitService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(siteUnit)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            SiteUnitFacade.getSiteUnitService().persist(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            SiteUnitFacade.getSiteUnitService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        SiteUnitFacade.getSiteUnitService().delete(getEntity(binder));
        getHome();
    }

    private void getHome() {
        main.content.setSecondComponent(new UnitMenu(main, "LANDING"));
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

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private SiteUnit getEntity(FieldGroup binder) {



        List<UnitLocationLifeCycle> unitLifeCycleList = new ArrayList<>();
        List<UnitServiceLog> unitServiceLogList = new ArrayList<>();
        SiteUnit existingSiteUnit;

        final SiteUnitsDetailsBean siteUnitBean = ((BeanItem<SiteUnitsDetailsBean>) binder.getItemDataSource()).getBean();

        UnitType unitType = UnitTypeFacade.getUnitTypeService().findById(siteUnitBean.getUnitTypeId());

        if (siteUnitBean.getId() != null) {

            existingSiteUnit = SiteUnitFacade.getSiteUnitService().findById(siteUnitBean.getId());
            unitLifeCycleList.addAll(existingSiteUnit.getUnitLocationLifeCycle());
            unitServiceLogList.addAll(existingSiteUnit.getUnityLogs());
        }

        final Status status = StatusFacade.getStatusService().findById(siteUnitBean.getOperationalStatus());

        final SiteUnit siteUnit = new SiteUnit.Builder(unitType)
                .description(siteUnitBean.getDescription())
                .operationalStatus(status)
                .unitId(siteUnitBean.getUnitId())
                .unitLocationLifeCycle(unitLifeCycleList)
                .unityLogs(unitServiceLogList)
                //                .parentId(getParentId())
                .id(siteUnitBean.getId())
                .build();


        return siteUnit;

    }

    private SiteUnitsDetailsBean getBean(SiteUnit siteUnit) {
        SiteUnitsDetailsBean bean = new SiteUnitsDetailsBean();

        bean.setUnitTypeId(siteUnit.getUnitTypeId());
        bean.setUnitId(siteUnit.getUnitId());
        bean.setDescription(siteUnit.getDescription());
        bean.setOperationalStatus(siteUnit.getOperationalStatusId());
        bean.setUnitId(siteUnit.getUnitId());

        UnitLocationLifeCycle unitLocationLifeCycle = SiteUnitFacade.getSiteUnitService().getUnitCurrentLocation(siteUnit.getId());
        if (unitLocationLifeCycle != null) {
            bean.setDateofAction(unitLocationLifeCycle.getDateofAction());
            bean.setLatitude(unitLocationLifeCycle.getLatitude());
            bean.setLongitude(unitLocationLifeCycle.getLongitude());
        }

        bean.setId(siteUnit.getId());
        return bean;
    }
}
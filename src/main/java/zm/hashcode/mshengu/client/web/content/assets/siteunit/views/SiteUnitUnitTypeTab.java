/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.products.UnitTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.UnitMenu;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.forms.SiteUniUnitTypeForm;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.models.SiteUnitUnitTypeBean;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.tables.SiteUnitUnitTypeTable;
import zm.hashcode.mshengu.domain.products.UnitType;

/**
 *
 * @author Ferox
 */
public class SiteUnitUnitTypeTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final SiteUniUnitTypeForm form;
    private final SiteUnitUnitTypeTable table;

    public SiteUnitUnitTypeTab(MshenguMain app) {
        main = app;
        form = new SiteUniUnitTypeForm();
        table = new SiteUnitUnitTypeTable(main);
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
            final UnitType unitTypeUnit = UnitTypeFacade.getUnitTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(unitTypeUnit)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            UnitTypeFacade.getUnitTypeService().persist(getEntity(binder));
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
            UnitTypeFacade.getUnitTypeService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        UnitTypeFacade.getUnitTypeService().delete(getEntity(binder));
        getHome();
    }

    

    private void getHome() {
        main.content.setSecondComponent(new UnitMenu(main, "TYPE"));
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

    private UnitType getEntity(FieldGroup binder) {


        UnitType existingUnitType;

        final SiteUnitUnitTypeBean unitTypeBean = ((BeanItem<SiteUnitUnitTypeBean>) binder.getItemDataSource()).getBean();


        if (unitTypeBean.getId() != null) {

            existingUnitType = UnitTypeFacade.getUnitTypeService().findById(unitTypeBean.getId());
        }

        final UnitType unitType = new UnitType.Builder(unitTypeBean.getName())
                .unitPrice(unitTypeBean.getUnitPrice())
                .id(unitTypeBean.getId())
                .build();


        return unitType;

    }

    private SiteUnitUnitTypeBean getBean(UnitType unitType) {
        SiteUnitUnitTypeBean bean = new SiteUnitUnitTypeBean();
        bean.setId(unitType.getId());
        bean.setName(unitType.getName());
        bean.setUnitPrice(unitType.getUnitPrice());
        return bean;
    }
}
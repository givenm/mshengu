/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.views;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.location.LocationTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.locations.LocationMenu;
import zm.hashcode.mshengu.client.web.content.setup.locations.forms.LocationTypeForm;
import zm.hashcode.mshengu.client.web.content.setup.locations.model.LocationTypeBean;
import zm.hashcode.mshengu.client.web.content.setup.locations.tables.LocationTypeTable;
import zm.hashcode.mshengu.domain.ui.location.LocationType;

/**
 *
 * @author Ferox
 */
public class LocationTypeTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final LocationTypeForm form;
    private final LocationTypeTable table;

    public LocationTypeTab(MshenguMain app) {
        main = app;
        form = new LocationTypeForm();
        table = new LocationTypeTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(ClickEvent event) {
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
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final LocationType locationType = LocationTypeFacade.getLocationTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(locationType)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            LocationTypeFacade.getLocationTypeService().persist(getEntity(binder));
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
            LocationTypeFacade.getLocationTypeService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        LocationTypeFacade.getLocationTypeService().delete(getEntity(binder));
        getHome();
    }

    private LocationType getEntity(FieldGroup binder) {
        final LocationTypeBean locationTypeBean = ((BeanItem<LocationTypeBean>) binder.getItemDataSource()).getBean();
        final LocationType locationType = new LocationType.Builder(locationTypeBean.getName())
                .code(locationTypeBean.getCode())
                .id(locationTypeBean.getId())
                .build();

        return locationType;

    }

    private void getHome() {
        main.content.setSecondComponent(new LocationMenu(main, "LOCATION_TYPE"));
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
        //Buttons Behaviou
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((ClickListener) this);
        form.edit.addClickListener((ClickListener) this);
        form.cancel.addClickListener((ClickListener) this);
        form.update.addClickListener((ClickListener) this);
        form.delete.addClickListener((ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((ValueChangeListener) this);
    }

    private LocationTypeBean getBean(LocationType locationType) {
        LocationTypeBean bean = new LocationTypeBean();
        bean.setName(locationType.getName());
        bean.setId(locationType.getId());
        bean.setCode(locationType.getCode());
        return bean;
    }
}

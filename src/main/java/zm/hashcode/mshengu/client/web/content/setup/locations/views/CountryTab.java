/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.views;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.location.LocationFacade;
import zm.hashcode.mshengu.app.facade.ui.location.LocationTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.locations.LocationMenu;
import zm.hashcode.mshengu.client.web.content.setup.locations.forms.CountryForm;
import zm.hashcode.mshengu.client.web.content.setup.locations.model.CountryBean;
import zm.hashcode.mshengu.client.web.content.setup.locations.tables.CountryTable;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.domain.ui.location.LocationType;

/**
 *
 * @author Ferox
 */
public class CountryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {
     
    
    private final MshenguMain main;
    private final CountryForm form;
    private final CountryTable table;

    public CountryTab(MshenguMain app) {
        main = app;
        form = new CountryForm();
        table = new CountryTable(main);
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
            final Location location = LocationFacade.getLocationService().findById(table.getValue().toString());
            final CountryBean locationBean = getCountryBean(location);
            form.binder.setItemDataSource(new BeanItem<>(locationBean));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();

            LocationFacade.getLocationService().persist(getEntity(binder));
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
            LocationFacade.getLocationService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        LocationFacade.getLocationService().delete(getEntity(binder));
        getHome();
    }

    private Location getEntity(FieldGroup binder) {
        Location location = getLocation(((BeanItem<CountryBean>) binder.getItemDataSource()).getBean());
        return location;

    }

    private void getHome() {
        main.content.setSecondComponent(new LocationMenu(main, "LANDING"));
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
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private Location getLocation(CountryBean bean) {
        LocationType locationType = LocationTypeFacade.getLocationTypeService().findByName("COUNTRY");
        Location parent = null;
        
        Location location = new Location.Builder(bean.getName())
                .latitude(bean.getLatitude())
                .longitude(bean.getLongitude())
                .locationType(locationType)
                .parent(parent)
                .id(bean.getId())
                .build();
        return location;
    }

    private CountryBean getCountryBean(Location location) {
        CountryBean locationBean = new CountryBean();
        locationBean.setId(location.getId());
        locationBean.setLatitude(location.getLatitude());
        locationBean.setLocationType(location.getLocationTypeId());
        locationBean.setLongitude(location.getLongitude());
        locationBean.setName(location.getName());
        locationBean.setParent(location.getParentLocationId());
        return locationBean;
    }

}

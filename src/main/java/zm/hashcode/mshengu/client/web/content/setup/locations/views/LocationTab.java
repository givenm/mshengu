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
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.ui.location.LocationFacade;
import zm.hashcode.mshengu.app.facade.ui.location.LocationTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.locations.LocationMenu;
import zm.hashcode.mshengu.client.web.content.setup.locations.forms.LocationForm;
import zm.hashcode.mshengu.client.web.content.setup.locations.model.LocationBean;
import zm.hashcode.mshengu.client.web.content.setup.locations.tables.LocationTable;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.domain.ui.location.LocationType;

/**
 *
 * @author Ferox
 */
public class LocationTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final LocationForm form;
    private final LocationTable table;
 
    public LocationTab(MshenguMain app) {
        main = app;
        form = new LocationForm();
        table = new LocationTable(main);
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
            final LocationBean locationBean = getLocationBean(location);
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
        Location location = getLocation(((BeanItem<LocationBean>) binder.getItemDataSource()).getBean());
        System.out.println("THE ID is " + location.getId());
        return location;

    }

    private void getHome() {
        main.content.setSecondComponent(new LocationMenu(main, "LOCATION"));
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

    private Location getLocation(LocationBean bean) {
        LocationType lt = LocationTypeFacade.getLocationTypeService().findById(bean.getLocationType());
        Location parent = null;
        if (!StringUtils.isEmpty(bean.getParent())) {
            parent = LocationFacade.getLocationService().findById(bean.getParent());
        }
        Location location = new Location.Builder(bean.getName())
                .code(bean.getCode())
                .latitude(bean.getLatitude())
                .longitude(bean.getLongitude())
                .locationType(lt)
                .parent(parent)
                .id(bean.getId())
                .build();
        return location;
    }

    private LocationBean getLocationBean(Location location) {
        LocationBean locationBean = new LocationBean();
        locationBean.setId(location.getId());
        locationBean.setCode(location.getCode());
        locationBean.setLatitude(location.getLatitude());
        locationBean.setLocationType(location.getLocationTypeId());
        locationBean.setLongitude(location.getLongitude());
        locationBean.setName(location.getName());
        locationBean.setParent(location.getParentLocationId());
        return locationBean;
    }


}

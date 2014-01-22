/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.fleet.FuelAndOilPriceFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.DailyDieselTrackerMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms.FuelAndOilPriceForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models.FuelAndOilPriceBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables.FuelAndOilPriceTable;
import zm.hashcode.mshengu.domain.fleet.FuelAndOilPrice;

/**
 *
 * @author geek
 */
public class FuelAndOilPriceTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final FuelAndOilPriceForm form;
    private final FuelAndOilPriceTable table;
    private static String trucKiD;

    public FuelAndOilPriceTab(MshenguMain app) {
        main = app;
        form = new FuelAndOilPriceForm();
        table = new FuelAndOilPriceTable(main);
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
            final FuelAndOilPrice fuelAndOilPrice = FuelAndOilPriceFacade.getFuelAndOilPriceService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(fuelAndOilPrice)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            FuelAndOilPrice fuelAndOilPrice = getNewEntity(binder);
            FuelAndOilPriceFacade.getFuelAndOilPriceService().persist(fuelAndOilPrice);
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
            FuelAndOilPrice fuelAndOilPrice = getUpdatedEntity(binder);
            FuelAndOilPriceFacade.getFuelAndOilPriceService().merge(fuelAndOilPrice);

            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        FuelAndOilPriceFacade.getFuelAndOilPriceService().delete(getUpdatedEntity(binder));
        getHome();
    }

    private FuelAndOilPrice getNewEntity(FieldGroup binder) {
        DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
        final FuelAndOilPriceBean entityBean = ((BeanItem<FuelAndOilPriceBean>) binder.getItemDataSource()).getBean();

        final FuelAndOilPrice fuelAndOilPrice = new FuelAndOilPrice.Builder(formatHelper.resetTimeOfDate(new Date()))
                .engineOilEffectDate(entityBean.getEngineOilEffectDate())
                .engineOilPrice(entityBean.getEngineOilPrice())
                .fuelEffectDate(entityBean.getFuelEffectDate())
                .fuelPrice(entityBean.getFuelPrice())
                .build();

        return fuelAndOilPrice;
    }

    private FuelAndOilPrice getUpdatedEntity(FieldGroup binder) {
        DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
        final FuelAndOilPriceBean entityBean = ((BeanItem<FuelAndOilPriceBean>) binder.getItemDataSource()).getBean();

        final FuelAndOilPrice fuelAndOilPrice = new FuelAndOilPrice.Builder(formatHelper.resetTimeOfDate(entityBean.getEntryDate()))
                .engineOilEffectDate(entityBean.getEngineOilEffectDate())
                .engineOilPrice(entityBean.getEngineOilPrice())
                .fuelEffectDate(entityBean.getFuelEffectDate())
                .fuelPrice(entityBean.getFuelPrice())
                .id(entityBean.getId())
                .build();

        return fuelAndOilPrice;
    }

    private void getHome() {
        main.content.setSecondComponent(new DailyDieselTrackerMenu(main, "DIESEL_PRICE"));
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

    private FuelAndOilPriceBean getBean(FuelAndOilPrice entity) {
        FuelAndOilPriceBean bean = new FuelAndOilPriceBean();
        bean.setId(entity.getId());
        bean.setEngineOilEffectDate(entity.getEngineOilEffectDate());
        bean.setEngineOilPrice(entity.getEngineOilPrice());
        bean.setEntryDate(entity.getEntryDate());
        bean.setFuelEffectDate(entity.getFuelEffectDate());
        bean.setFuelPrice(entity.getFuelPrice());
        return bean;
    }
}

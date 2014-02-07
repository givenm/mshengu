/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.forms.OperatingCostForm;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.models.OperatingCostBean;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.tables.OperatingCostTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.FleetMaintenanceMenu;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Ferox
 */
public class OperatingCostsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final OperatingCostForm form;
    private final OperatingCostTable table;

    public OperatingCostsTab(MshenguMain app) {
        main = app;
        form = new OperatingCostForm();
        table = new OperatingCostTable(main);
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
            final OperatingCost operatingCosts = OperatingCostFacade.getOperatingCostService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(operatingCosts)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            OperatingCostFacade.getOperatingCostService().persist(getEntity(binder));
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
            OperatingCostFacade.getOperatingCostService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        OperatingCostFacade.getOperatingCostService().delete(getEntity(binder));
        getHome();
    }

    private OperatingCost getEntity(FieldGroup binder) {
        final OperatingCostBean operatingCostsBean = ((BeanItem<OperatingCostBean>) binder.getItemDataSource()).getBean();
        final Person driver = PersonFacade.getPersonService().findById(operatingCostsBean.getDriverId());

        final OperatingCost operatingCosts = new OperatingCost.Builder(new Date())
                .fuelCost(operatingCostsBean.getFuelCost())
                .fuelLitres(operatingCostsBean.getFuelLitres())
                .oilCost(operatingCostsBean.getOilCost())
                .oilLitres(operatingCostsBean.getOilLitres())
                .speedometer(operatingCostsBean.getSpeedometer())
                .slipNo(operatingCostsBean.getSlipNo())
                .driver(driver)
                .id(operatingCostsBean.getId())
                .build();

        return operatingCosts;


    }

    private void getHome() {
        main.content.setSecondComponent(new FleetMaintenanceMenu(main, "FUEL"));
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

    private OperatingCostBean getBean(OperatingCost operatingCosts) {
        OperatingCostBean bean = new OperatingCostBean();
        bean.setSlipNo(operatingCosts.getSlipNo());
        bean.setFuelCost(operatingCosts.getFuelCost());
        bean.setFuelLitres(operatingCosts.getFuelLitres());
        bean.setOilCost(operatingCosts.getOilCost());
        bean.setOilLitres(operatingCosts.getOilLitres());
        bean.setSpeedometer(operatingCosts.getSpeedometer());
        bean.setTransactionDate(operatingCosts.getTransactionDate());
        bean.setDriverId(operatingCosts.getDriverId());
        bean.setId(operatingCosts.getId());
        return bean;
    }
}

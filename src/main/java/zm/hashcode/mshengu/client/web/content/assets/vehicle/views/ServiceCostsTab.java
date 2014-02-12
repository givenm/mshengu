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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceCategoryFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceCostFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.forms.ServiceCostForm;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.models.ServiceCostBean;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.tables.ServiceCostTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.FleetMaintenanceMenu;
import zm.hashcode.mshengu.domain.fleet.ServiceCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Ferox
 */
public class ServiceCostsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceCostForm form;
    private final ServiceCostTable table;
    private static String trucKiD;

    public ServiceCostsTab(MshenguMain app) {
        main = app;
        form = new ServiceCostForm();
        table = new ServiceCostTable(main);
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
            final ServiceCost serviceCosts = ServiceCostFacade.getServiceCostService().findById(table.getValue().toString());

            // Find Truck that has this operatingCosts
            List<Truck> trucks = TruckFacade.getTruckService().findAll();
            for (Truck truck : trucks) {
                List<ServiceCost> serviceCostList = truck.getServiceCosts();
                for (ServiceCost serviceCostt : serviceCostList) {
                    if (serviceCostt.getId().equals(serviceCosts.getId())) {
                        trucKiD = truck.getId();
                        break;
                    }
                }
            }

            form.binder.setItemDataSource(new BeanItem<>(getBean(serviceCosts)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ServiceCost serviceCost = getNewEntity(binder);
            ServiceCostFacade.getServiceCostService().persist(serviceCost);

            final Truck truck = TruckFacade.getTruckService().findById(((BeanItem<ServiceCostBean>) binder.getItemDataSource()).getBean().getTruckId());
            List<ServiceCost> serviceCostList = new ArrayList<>();
            serviceCostList.addAll(truck.getServiceCosts());
            serviceCostList.add(serviceCost);

            Truck updatedTruck = new Truck.Builder(truck.getNumberPlate())
                    .operatingCosts(truck.getOperatingCosts())
                    .serviceCosts(serviceCostList)
                    .trackerGPS(truck.getTrackerGPS())
                    .brand(truck.getBrand())
                    .category(truck.getCategory())
                    .radioSerialNumber(truck.getRadioSerialNumber())
                    .dateOfExpire(truck.getDateOfExpire())
                    .description(truck.getDescription())
                    .driver(truck.getDriver())
                    .engineNo(truck.getEngineNo())
                    .isActive(truck.isIsActive())
                    .vehicleNumber(truck.getVehicleNumber())
                    .model(truck.getModel())
                    .vehicleCost(truck.getVehicleCost())
                    .registerYear(truck.getRegisterYear())
                    .routes(truck.getRoutes())
                    .tare(truck.getTare())
                    .vinNo(truck.getVinNo())
                    .id(truck.getId())
                    .build();
            TruckFacade.getTruckService().merge(updatedTruck);

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
            ServiceCost serviceCost = getUpdatedEntity(binder);
            ServiceCostFacade.getServiceCostService().merge(serviceCost);

            // Truck assigned to might change, so delete this operatingCost entry for current Truck (see "trucKiD" variable) and reassigned again. Eish
            // Reason of Domain Model
            Truck instanceTruck = TruckFacade.getTruckService().findById(trucKiD);
            List<ServiceCost> serviceCosts = instanceTruck.getServiceCosts();
            List<ServiceCost> updatedServiceCosts = new ArrayList<>();
            for (ServiceCost serviceCostt : serviceCosts) {
                if (!serviceCostt.getId().equals(((BeanItem<ServiceCostBean>) binder.getItemDataSource()).getBean().getId())) {
                    updatedServiceCosts.add(serviceCostt);
                }
            }

            Truck updatedTruck = new Truck.Builder(instanceTruck.getNumberPlate())
                    .operatingCosts(instanceTruck.getOperatingCosts())
                    .serviceCosts(updatedServiceCosts)
                    .trackerGPS(instanceTruck.getTrackerGPS())
                    .brand(instanceTruck.getBrand())
                    .category(instanceTruck.getCategory())
                    .radioSerialNumber(instanceTruck.getRadioSerialNumber())
                    .dateOfExpire(instanceTruck.getDateOfExpire())
                    .description(instanceTruck.getDescription())
                    .driver(instanceTruck.getDriver())
                    .engineNo(instanceTruck.getEngineNo())
                    .isActive(instanceTruck.isIsActive())
                    .vehicleNumber(instanceTruck.getVehicleNumber())
                    .model(instanceTruck.getModel())
                    .vehicleCost(instanceTruck.getVehicleCost())
                    .registerYear(instanceTruck.getRegisterYear())
                    .routes(instanceTruck.getRoutes())
                    .tare(instanceTruck.getTare())
                    .vinNo(instanceTruck.getVinNo())
                    .id(instanceTruck.getId())
                    .build();
            TruckFacade.getTruckService().merge(updatedTruck);


            // now Perform the Update
            final Truck truck = TruckFacade.getTruckService().findById(((BeanItem<ServiceCostBean>) binder.getItemDataSource()).getBean().getTruckId());
            List<ServiceCost> serviceCostList = new ArrayList<>();
            serviceCostList.addAll(truck.getServiceCosts());
            serviceCostList.add(serviceCost);

            Truck updateTruck = new Truck.Builder(truck.getNumberPlate())
                    .operatingCosts(truck.getOperatingCosts())
                    .serviceCosts(serviceCostList)
                    .trackerGPS(truck.getTrackerGPS())
                    .brand(truck.getBrand())
                    .category(truck.getCategory())
                    .radioSerialNumber(truck.getRadioSerialNumber())
                    .dateOfExpire(truck.getDateOfExpire())
                    .description(truck.getDescription())
                    .driver(truck.getDriver())
                    .engineNo(truck.getEngineNo())
                    .isActive(truck.isIsActive())
                    .vehicleNumber(truck.getVehicleNumber())
                    .model(truck.getModel())
                    .vehicleCost(truck.getVehicleCost())
                    .registerYear(truck.getRegisterYear())
                    .routes(truck.getRoutes())
                    .tare(truck.getTare())
                    .vinNo(truck.getVinNo())
                    .id(truck.getId())
                    .build();
            TruckFacade.getTruckService().merge(updateTruck);


            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {

        // Remove Service Cost entry for Truck
        Truck instanceTruck = TruckFacade.getTruckService().findById(trucKiD);
        List<ServiceCost> serviceCosts = instanceTruck.getServiceCosts();
        List<ServiceCost> updatedServiceCosts = new ArrayList<>();
        for (ServiceCost serviceCostt : serviceCosts) {
            if (!serviceCostt.getId().equals(((BeanItem<ServiceCostBean>) binder.getItemDataSource()).getBean().getId())) {
                updatedServiceCosts.add(serviceCostt);
            }
        }

        Truck updatedTruck = new Truck.Builder(instanceTruck.getNumberPlate())
                .operatingCosts(instanceTruck.getOperatingCosts())
                .serviceCosts(updatedServiceCosts)
                .trackerGPS(instanceTruck.getTrackerGPS())
                .brand(instanceTruck.getBrand())
                .category(instanceTruck.getCategory())
                .radioSerialNumber(instanceTruck.getRadioSerialNumber())
                .dateOfExpire(instanceTruck.getDateOfExpire())
                .description(instanceTruck.getDescription())
                .driver(instanceTruck.getDriver())
                .engineNo(instanceTruck.getEngineNo())
                .isActive(instanceTruck.isIsActive())
                .vehicleNumber(instanceTruck.getVehicleNumber())
                .model(instanceTruck.getModel())
                .vehicleCost(instanceTruck.getVehicleCost())
                .registerYear(instanceTruck.getRegisterYear())
                .routes(instanceTruck.getRoutes())
                .tare(instanceTruck.getTare())
                .vinNo(instanceTruck.getVinNo())
                .id(instanceTruck.getId())
                .build();
        TruckFacade.getTruckService().merge(updatedTruck);


        // Remove Service Cost
        ServiceCostFacade.getServiceCostService().delete(getUpdatedEntity(binder));
        getHome();
    }

    private ServiceCost getNewEntity(FieldGroup binder) {

        final ServiceCostBean serviceCostsBean = ((BeanItem<ServiceCostBean>) binder.getItemDataSource()).getBean();
        final ServiceProvider serviceProvider = ServiceProviderFacade.getServiceProviderService().findById(serviceCostsBean.getServiceProviderId());
        final ServiceCategory serviceCategory = ServiceCategoryFacade.getServiceCategoryService().findById(serviceCostsBean.getServiceCategoryId());

        final ServiceCost serviceCosts = new ServiceCost.Builder(new Date())
                .serviceCategory(serviceCategory)
                .serviceProvider(serviceProvider)
                .slipNo(serviceCostsBean.getSlipNo())
                .serviceTotalCost(serviceCostsBean.getServiceTotalCost())
                .comment(serviceCostsBean.getComment())
                .build();

        return serviceCosts;
    }

    private ServiceCost getUpdatedEntity(FieldGroup binder) {

        final ServiceCostBean serviceCostsBean = ((BeanItem<ServiceCostBean>) binder.getItemDataSource()).getBean();
        final ServiceProvider serviceProvider = ServiceProviderFacade.getServiceProviderService().findById(serviceCostsBean.getServiceProviderId());
        final ServiceCategory serviceCategory = ServiceCategoryFacade.getServiceCategoryService().findById(serviceCostsBean.getServiceCategoryId());

        final ServiceCost serviceCosts = new ServiceCost.Builder(new Date())
                .serviceCategory(serviceCategory)
                .serviceProvider(serviceProvider)
                .slipNo(serviceCostsBean.getSlipNo())
                .serviceTotalCost(serviceCostsBean.getServiceTotalCost())
                .comment(serviceCostsBean.getComment())
                .id(serviceCostsBean.getId())
                .build();

        return serviceCosts;
    }

    private void getHome() {
        main.content.setSecondComponent(new FleetMaintenanceMenu(main, "LANDING"));
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

    private ServiceCostBean getBean(ServiceCost serviceCosts) {
        ServiceCostBean bean = new ServiceCostBean();
        bean.setSlipNo(serviceCosts.getSlipNo());
        bean.setServiceCategoryId(serviceCosts.getServiceCategoryId());
        bean.setServiceProviderId(serviceCosts.getServiceProviderId());
        bean.setTransactionDate(serviceCosts.getTransactionDate());
        bean.setServiceTotalCost(serviceCosts.getServiceTotalCost());
        bean.setComment(serviceCosts.getComment());
        bean.setTruckId(trucKiD);
        bean.setId(serviceCosts.getId());
        return bean;
    }
}

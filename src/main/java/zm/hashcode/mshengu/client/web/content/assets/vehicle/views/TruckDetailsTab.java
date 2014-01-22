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
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckCategoryFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.FleetMenu;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.forms.TruckForm;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.models.TruckBean;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.tables.TruckTable;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.ServiceCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public class TruckDetailsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final TruckForm form;
    private final TruckTable table;

    public TruckDetailsTab(MshenguMain app) {
        main = app;
        form = new TruckForm();
        table = new TruckTable(main, this);
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
            final Truck truck = TruckFacade.getTruckService().findById(table.getValue().toString());

            form.categoryId.removeValueChangeListener((Property.ValueChangeListener) this);
            form.binder.setItemDataSource(new BeanItem<>(getBean(truck)));
            form.categoryId.addValueChangeListener((Property.ValueChangeListener) this);
            setReadFormProperties();
        } else if (property == form.categoryId) {
            if (form.categoryId.getValue() != null) {
                final TruckCategory category = TruckCategoryFacade.getTruckCategoryService().findById(form.categoryId.getValue().toString());
                String nextVehicleNumber = getNextVehicleNumber(category);
                form.setNextVehicleNumber(nextVehicleNumber);
            }
        }



    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            TruckFacade.getTruckService().persist(getEntity(binder));
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
            TruckFacade.getTruckService().merge(getUpdatedEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        TruckFacade.getTruckService().delete(getEntity(binder));
        getHome();
    }

    private void updateTruckCategory(TruckCategory truckCategoryIn) {
        if (truckCategoryIn != null) {
            final TruckCategory truckCategory = new TruckCategory.Builder(truckCategoryIn.getCategoryName())
                    .id(truckCategoryIn.getId())
                    .namingCode(truckCategoryIn.getNamingCode())
                    .value(truckCategoryIn.getValue() + 1)
                    .build();

            TruckCategoryFacade.getTruckCategoryService().merge(truckCategory);
        }
    }

    private Truck getEntity(FieldGroup binder) {
        final TruckBean truckBean = ((BeanItem<TruckBean>) binder.getItemDataSource()).getBean();
        final TruckCategory truckCategory = TruckCategoryFacade.getTruckCategoryService().findById(truckBean.getCategoryId());
        Person driver = null;
        if (truckBean.getDriverId() != null) {
            driver = PersonFacade.getPersonService().findById(truckBean.getDriverId());
        }

        updateTruckCategory(truckCategory);

        final Truck truck = new Truck.Builder(truckBean.getNumberPlate())
                .trackerGPS(truckBean.getTrackerGPS())
                .brand(truckBean.getBrand())
                .category(truckCategory)
                .startMileage(truckBean.getStartMileage())
                .radioSerialNumber(truckBean.getRadioSerialNumber())
                .dateOfExpire(truckBean.getDateOfExpire())
                .description(truckBean.getDescription())
                .driver(driver)
                .engineNo(truckBean.getEngineNo())
                .isActive(truckBean.isIsActive())
                .vehicleNumber(truckBean.getVehicleNumber())
                .model(truckBean.getModel())
                .vehicleCost(truckBean.getVehicleCost())
                .registerYear(truckBean.getRegisterYear())
                .tare(truckBean.getTare())
                .vinNo(truckBean.getVinNo())
                .manufacturingSpec(truckBean.getManufacturingSpec())
                .build();
        return truck;
    }

    private Truck getUpdatedEntity(FieldGroup binder) {

        final TruckBean truckBean = ((BeanItem<TruckBean>) binder.getItemDataSource()).getBean();
        final Truck existingTruck = TruckFacade.getTruckService().findById(truckBean.getId());

        List<Site> routes = new ArrayList<>(); //existingTruck.getRoutes();
        List<OperatingCost> operatingCosts = new ArrayList<>();
        List<ServiceCost> serviceCosts = new ArrayList<>();
        routes.addAll(existingTruck.getRoutes());
        operatingCosts.addAll(existingTruck.getOperatingCosts());
        serviceCosts.addAll(existingTruck.getServiceCosts());


        final TruckCategory truckCategory = TruckCategoryFacade.getTruckCategoryService().findById(truckBean.getCategoryId());
//        updateTruckCategory(truckCategory);
        Person driver = null;
        if (truckBean.getDriverId() != null) {
            driver = PersonFacade.getPersonService().findById(truckBean.getDriverId());
        }

        final Truck truck = new Truck.Builder(truckBean.getNumberPlate())
                .trackerGPS(truckBean.getTrackerGPS())
                .brand(truckBean.getBrand())
                .category(truckCategory)
                .startMileage(truckBean.getStartMileage())
                .radioSerialNumber(truckBean.getRadioSerialNumber())
                .dateOfExpire(truckBean.getDateOfExpire())
                .description(truckBean.getDescription())
                .driver(driver)
                .engineNo(truckBean.getEngineNo())
                .isActive(truckBean.isIsActive())
                //                .vehicleNumber(truckBean.getVehicleNumber())
                .vehicleNumber(existingTruck.getVehicleNumber())
                .model(truckBean.getModel())
                .vehicleCost(truckBean.getVehicleCost())
                .registerYear(truckBean.getRegisterYear())
                .tare(truckBean.getTare())
                .vinNo(truckBean.getVinNo())
                .operatingCosts(operatingCosts)
                .routes(routes)
                .serviceCosts(serviceCosts).manufacturingSpec(truckBean.getManufacturingSpec())
                .id(truckBean.getId())
                .build();
        return truck;
    }

    private void getHome() {
        main.content.setSecondComponent(new FleetMenu(main, "LANDING"));
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

        form.categoryId.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private TruckBean getBean(Truck truck) {
        TruckBean bean = new TruckBean();
        bean.setNumberPlate(truck.getNumberPlate());
        bean.setTrackerGPS(truck.getTrackerGPS());
        bean.setBrand(truck.getBrand());
        bean.setCategoryId(truck.getCategoryId());
        bean.setRadioSerialNumber(truck.getRadioSerialNumber());
        bean.setDateOfExpire(truck.getDateOfExpire());
        bean.setDescription(truck.getDescription());
        bean.setDriverId(truck.getDriverId());
        bean.setEngineNo(truck.getEngineNo());
        bean.setIsActive(truck.isIsActive());
        bean.setVehicleNumber(truck.getVehicleNumber());
        bean.setModel(truck.getModel());
        bean.setVehicleCost(truck.getVehicleCost());
        bean.setPaymentDate(truck.getPaymentDate());
        bean.setReceiptNo(truck.getReceiptNo());
        bean.setRegisterYear(truck.getRegisterYear());
        bean.setTare(truck.getTare());
        bean.setVinNo(truck.getVinNo());
        bean.setId(truck.getId());
        bean.setStartMileage(truck.getStartMileage());
        bean.setManufacturingSpec(truck.getManufacturingSpec());
//        bean.setOperatingSpec(truck.getOperatingSpec());
//               bean.setOperationalAllowance(truck.getOperationalAllowance());
//        bean.setFuelSpec(truck.getFuelSpec());
        return bean;
    }

    public String getNextVehicleNumber(TruckCategory category) {
        if (category != null) {
            String code = category.getNamingCode() + "-";
            if (category.getValue() < 10) {
                code = code + "0";
            }
            return code + category.getValue();
        } else {
            return null;
        }
    }
}

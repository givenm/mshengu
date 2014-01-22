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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.DailyDieselTrackerMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms.DailyInputsForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models.DailyInputsBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables.DailyInputsTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author geek
 */
public class DailyInputsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final DailyInputsForm form;
    private final DailyInputsTable table;
    private static String trucKiD;
    private TrackerUtil trackerUtil = new TrackerUtil();
    public static Date filteredTransactionDate = null;
    public static String filteredTruckId;

    public DailyInputsTab(MshenguMain app) {
        main = app;
        form = new DailyInputsForm();
        table = new DailyInputsTable(main);
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
            final OperatingCost operatingCost = OperatingCostFacade.getOperatingCostService().findById(table.getValue().toString());

            // Find Truck that has this operatingCosts
            List<Truck> trucks = TruckFacade.getTruckService().findAll();
            for (Truck truck : trucks) {
                List<OperatingCost> operatingCostList = truck.getOperatingCosts();
                for (OperatingCost operatingCostt : operatingCostList) {
                    if (operatingCostt.getId().equals(operatingCost.getId())) {
                        trucKiD = truck.getId();
                        break;
                    }
                }
            }
            form.transactionDate.setReadOnly(false);
            form.randPerLitreCalc.setReadOnly(false);
            form.randPerLitreCalc.setValue("");
            form.binder.setItemDataSource(new BeanItem<>(getBean(operatingCost)));
            form.transactionDate.setReadOnly(true);
            form.randPerLitreCalc.setReadOnly(true);
            setReadFormProperties();
        } else if (property == form.truckId) {
            Truck truck = TruckFacade.getTruckService().findById(form.truckId.getValue().toString());
            form.driverId.setValue(truck.getDriver().getId());
        } else if (property == form.fuelLitres) {

            BigDecimal fuelLitres = new BigDecimal(form.fuelLitres.getValue().toString()); // .replaceAll(",", "")
            fuelLitres.setScale(2, RoundingMode.HALF_UP);
            try {
                BigDecimal fuelCost = new BigDecimal(form.fuelCost.getValue().toString());
                form.randPerLitreCalc.setReadOnly(false);
                form.randPerLitreCalc.setValue(fuelCost.divide(fuelLitres, 2, RoundingMode.HALF_UP).toString());
                form.randPerLitreCalc.setReadOnly(true);
            } catch (Exception ex) {
            }
        } else if (property == form.fuelCost) {
            BigDecimal fuelCost = new BigDecimal(form.fuelCost.getValue().toString());
            try {
                BigDecimal fuelLitres = new BigDecimal(form.fuelLitres.getValue().toString());
                fuelLitres.setScale(2, RoundingMode.HALF_UP);
                form.randPerLitreCalc.setReadOnly(false);
                form.randPerLitreCalc.setValue(fuelCost.divide(fuelLitres, 2, RoundingMode.HALF_UP).toString()); //toString
                form.randPerLitreCalc.setReadOnly(true);
            } catch (Exception ex) {
            }
        } else if (property == form.filterTransactionDate) {
            filteredTransactionDate = form.filterTransactionDate.getValue();
            if (form.filterTruckId.getValue() != null) {
                Truck truck = TruckFacade.getTruckService().findById(filteredTruckId);
                List<OperatingCost> dailyInputList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckByMonth(truck, filteredTransactionDate);
//                if (!dailyInputList.isEmpty()) {
                populateDailyInputsTable(dailyInputList, truck);
//                }
            }
        } else if (property == form.filterTruckId) {
            filteredTruckId = form.filterTruckId.getValue().toString();
            if (form.filterTransactionDate.getValue() != null) {
                Truck truck = TruckFacade.getTruckService().findById(filteredTruckId);
                List<OperatingCost> dailyInputList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckByMonth(truck, filteredTransactionDate);
//                if (!dailyInputList.isEmpty()) {
                populateDailyInputsTable(dailyInputList, truck);
//                }
            }
        }

    }

    private void populateDailyInputsTable(List<OperatingCost> dailyInputList, Truck truck) {
        // Remove Table's ValueChangeListener, add contents to Table then Add  Table's ValueChangeListener
        table.removeValueChangeListener((Property.ValueChangeListener) this);
        table.populateDailyInputTable(dailyInputList, truck);
        table.addValueChangeListener((Property.ValueChangeListener) this);

    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            final Truck truck = TruckFacade.getTruckService().findById(((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTruckId());
            Date transactionDate = ((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTransactionDate();
            // Check if a record for current date exists, if true asks for record to be edited
            if (trackerUtil.isThereDuplicateDailyInput(truck, transactionDate)) {
                Notification.show("A Record for today has been ADDED<br/>Duplicates are not allowed. Consider editing this record.", Notification.Type.TRAY_NOTIFICATION);
            } else {

                Integer previousClosingMileage = trackerUtil.getPreviousDailyInputClosingMileage(truck, transactionDate, transactionDate);
//                Notification.show("Current closing mileage: " + previousClosingMileage + " for " + truck.getVehicleNumber(), Notification.Type.HUMANIZED_MESSAGE);
                if (previousClosingMileage < (((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getSpeedometer())
                        || previousClosingMileage == (((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getSpeedometer())) {
                    OperatingCost operatingCost = getNewEntity(binder);
                    OperatingCostFacade.getOperatingCostService().persist(operatingCost);

                    updateTruckOperatingCost(operatingCost, binder);

                    getHome();
                    Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
                } else {
                    Notification.show("Current closing mileage is LOWER than OR SAME as previous closing mileage! : " + previousClosingMileage, Notification.Type.ERROR_MESSAGE);
                }
            }
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            final Truck truck = TruckFacade.getTruckService().findById(((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTruckId());
            Date transactionDate = ((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTransactionDate();
            Integer previousClosingMileage = trackerUtil.getPreviousDailyInputClosingMileage(truck, new Date(), transactionDate);
//        Notification.show("Current closing mileage: " + previousClosingMileage + " for " + truck.getVehicleNumber(), Notification.Type.HUMANIZED_MESSAGE);
            if (previousClosingMileage < (((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getSpeedometer())
                    || previousClosingMileage == (((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getSpeedometer())) {
                // We can also test for mileages the exist after this date entry in case current mileage clashes
                OperatingCost operatingCost = getUpdatedEntity(binder);
                OperatingCostFacade.getOperatingCostService().merge(operatingCost);
//                deleteTruckOperatingCost(binder);
//                updateTruckOperatingCost(operatingCost, binder);
                getHome();
                Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);

            } else {
                Notification.show("Current closing mileage is LOWER or EQUALS previous closing mileage! : " + previousClosingMileage, Notification.Type.ERROR_MESSAGE);
            }
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }

    }

    private void deleteForm(FieldGroup binder) {
        deleteTruckOperatingCost(binder);
        // Now delete theh Operating cost
        OperatingCostFacade.getOperatingCostService().delete(getUpdatedEntity(binder));
        getHome();
    }

    private OperatingCost getNewEntity(FieldGroup binder) {
        //final  OperatingCost cust = new OperatingCost.Builder(binder.getItemDataSource().getItemProperty("name")).
        final DailyInputsBean dailyInputBean = ((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean();
        final Person driver = PersonFacade.getPersonService().findById(dailyInputBean.getDriverId());   //////////////////////////////////////////////////////

        final OperatingCost operatingCosts = new OperatingCost.Builder(dailyInputBean.getTransactionDate()) // new Date()
                .fuelCost(dailyInputBean.getFuelCost())
                .fuelLitres(dailyInputBean.getFuelLitres())
                .oilCost(dailyInputBean.getOilCost())
                .oilLitres(dailyInputBean.getOilLitres())
                .speedometer(dailyInputBean.getSpeedometer())
                .slipNo(dailyInputBean.getSlipNo())
                .randPerLitre(dailyInputBean.getRandPerLitre())
                .driver(driver)
                .build();

        return operatingCosts;
    }

    private OperatingCost getUpdatedEntity(FieldGroup binder) {
        final DailyInputsBean dailyInputBean = ((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean();
        final Person driver = PersonFacade.getPersonService().findById(dailyInputBean.getDriverId());   //////////////////////////////////////////////////////

        final OperatingCost operatingCosts = new OperatingCost.Builder(dailyInputBean.getTransactionDate())
                .fuelCost(dailyInputBean.getFuelCost())
                .fuelLitres(dailyInputBean.getFuelLitres())
                .oilCost(dailyInputBean.getOilCost())
                .oilLitres(dailyInputBean.getOilLitres())
                .speedometer(dailyInputBean.getSpeedometer())
                .slipNo(dailyInputBean.getSlipNo())
                .driver(driver)
                .randPerLitre(dailyInputBean.getRandPerLitre())
                .id(dailyInputBean.getId())
                .build();

        return operatingCosts;
    }

    private void getHome() {
        main.content.setSecondComponent(new DailyDieselTrackerMenu(main, "DAILY_INPUTS"));
    }

    private void updateTruckOperatingCost(OperatingCost operatingCost, FieldGroup binder) {
        final Truck truck = TruckFacade.getTruckService().findById(((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTruckId());
        List<OperatingCost> operatingCostList = new ArrayList<>();
        operatingCostList.addAll(truck.getOperatingCosts());
        operatingCostList.add(operatingCost);

        Truck updatedTruck = new Truck.Builder(truck.getNumberPlate())
                .operatingCosts(operatingCostList)
                .serviceCosts(truck.getServiceCosts())
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
                .manufacturingSpec(truck.getManufacturingSpec())
                .operatingSpec(truck.getOperatingSpec())
                .operationalAllowance(truck.getOperationalAllowance())
                .startMileage(truck.getStartMileage())
                .fuelSpec(truck.getFuelSpec())
                .id(truck.getId())
                .build();
        TruckFacade.getTruckService().merge(updatedTruck);
    }

    private void deleteTruckOperatingCost(FieldGroup binder) {
        // Truck assigned to might change, so delete this operatingCost entry for current Truck (see "trucKiD" variable) and reassigned again. Eish
        // Reason of Domain Model
        Truck instanceTruck = TruckFacade.getTruckService().findById(trucKiD);
        List<OperatingCost> operatingCosts = instanceTruck.getOperatingCosts();
        List<OperatingCost> updatedOperatingCosts = new ArrayList<>();
        for (OperatingCost operatingCostt : operatingCosts) {
            if (!operatingCostt.getId().equals(((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getId())) {
                updatedOperatingCosts.add(operatingCostt);
            }
        }

        Truck updatedTruck = new Truck.Builder(instanceTruck.getNumberPlate())
                .operatingCosts(updatedOperatingCosts)
                .serviceCosts(instanceTruck.getServiceCosts())
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
                .manufacturingSpec(instanceTruck.getManufacturingSpec())
                .operatingSpec(instanceTruck.getOperatingSpec())
                .operationalAllowance(instanceTruck.getOperationalAllowance())
                .startMileage(instanceTruck.getStartMileage())
                .fuelSpec(instanceTruck.getFuelSpec())
                .id(instanceTruck.getId())
                .build();
        TruckFacade.getTruckService().merge(updatedTruck);
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
        //
        form.truckId.addValueChangeListener((Property.ValueChangeListener) this);
        form.fuelLitres.addValueChangeListener((Property.ValueChangeListener) this);
        form.fuelCost.addValueChangeListener((Property.ValueChangeListener) this);
        form.filterTransactionDate.addValueChangeListener((Property.ValueChangeListener) this);
        form.filterTruckId.addValueChangeListener((Property.ValueChangeListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private DailyInputsBean getBean(OperatingCost operatingCosts) {
        DailyInputsBean bean = new DailyInputsBean();
        bean.setSlipNo(operatingCosts.getSlipNo());
        bean.setFuelCost(operatingCosts.getFuelCost());
        bean.setFuelLitres(operatingCosts.getFuelLitres());
        bean.setOilCost(operatingCosts.getOilCost());
        bean.setOilLitres(operatingCosts.getOilLitres());
        bean.setSpeedometer(operatingCosts.getSpeedometer());
        bean.setTransactionDate(operatingCosts.getTransactionDate());
        bean.setDriverId(operatingCosts.getDriverId());
        bean.setTruckId(trucKiD);
        bean.setId(operatingCosts.getId());
        bean.setRandPerLitre(operatingCosts.getRandPerLitre());

        return bean;
    }
}

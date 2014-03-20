/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.validation.OnSubmitValidationHelper;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
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
    private final TrackerUtil trackerUtil = new TrackerUtil();
    final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    public static Date filteredTransactionDate = null;
    public static String filteredTruckId;
    public static Date formTransactionDate = null;

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
            trucKiD = operatingCost.getTruckId();

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
            String vehicleNumber = truck.getVehicleNumber();
            if (vehicleNumber.substring(0, 3).equals("MMV") || vehicleNumber.substring(0, 3).equals("MOV")) {
                form.speedometer.setRequired(false);
            } else {
                form.speedometer = UIValidatorHelper.setRequiredTextField(form.speedometer, "Closing Mileage");
            }
        } else if (property == form.fuelLitres) {
            BigDecimal fuelLitres = new BigDecimal(form.fuelLitres.getValue().toString()); // .replaceAll(",", "")
            fuelLitres = fuelLitres.setScale(2, BigDecimal.ROUND_HALF_UP);
            try {
                BigDecimal fuelCost = new BigDecimal(form.fuelCost.getValue().toString());
                form.randPerLitreCalc.setReadOnly(false);
                form.randPerLitreCalc.setValue(fuelCost.divide(fuelLitres, 2, BigDecimal.ROUND_HALF_UP).toString());
                form.randPerLitreCalc.setReadOnly(true);
            } catch (Exception ex) {
            }
        } else if (property == form.fuelCost) {
            BigDecimal fuelCost = new BigDecimal(form.fuelCost.getValue().toString());
            try {
                BigDecimal fuelLitres = new BigDecimal(form.fuelLitres.getValue().toString());
                fuelLitres = fuelLitres.setScale(2, BigDecimal.ROUND_HALF_UP);
                form.randPerLitreCalc.setReadOnly(false);
                form.randPerLitreCalc.setValue(fuelCost.divide(fuelLitres, 2, BigDecimal.ROUND_HALF_UP).toString()); //toString
                form.randPerLitreCalc.setReadOnly(true);
            } catch (Exception ex) {
            }
        } else if (property == form.filterTransactionDate) {
            filteredTransactionDate = form.filterTransactionDate.getValue();
            try {
                filteredTruckId = form.filterTruckId.getValue().toString();
            } catch (java.lang.NullPointerException ex) {
            }
            if (filteredTruckId != null) {
                getPopulateTableAndReset();
            }
        } else if (property == form.filterTruckId) {
            filteredTruckId = form.filterTruckId.getValue().toString();
            try {
                filteredTransactionDate = form.filterTransactionDate.getValue();
            } catch (java.lang.NullPointerException ex) {
            }
            if (filteredTransactionDate != null) {
                getPopulateTableAndReset();
            }
        } else if (property == form.transactionDate) {
            formTransactionDate = form.transactionDate.getValue();
            if (formTransactionDate != null) {
                if (!(formTransactionDate.before(resetToDayEnd(new Date())) || formTransactionDate.compareTo(resetToDayEnd(new Date())) == 0)) {
                    Notification.show("Error. Select any date before Current date. No future entries allowed", Notification.Type.ERROR_MESSAGE);
                }
            }
        }
    }

    private void getPopulateTableAndReset() {
        Truck truck = TruckFacade.getTruckService().findById(filteredTruckId);
        List<OperatingCost> dailyInputList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckByMonth(truck, filteredTransactionDate);
        populateDailyInputsTable(dailyInputList, truck);
        resetDailyInputBean();
    }

    private void resetDailyInputBean() {
        //
        form.fuelCost.setReadOnly(false);
        form.fuelLitres.setReadOnly(false);
        form.oilCost.setReadOnly(false);
        form.oilLitres.setReadOnly(false);
        form.randPerLitreCalc.setReadOnly(false);
        form.slipNo.setReadOnly(false);
        form.speedometer.setReadOnly(false);
        form.transactionDate.setReadOnly(false);
        form.truckId.setReadOnly(false);
        form.driverId.setReadOnly(false);

        form.truckId.removeValueChangeListener((Property.ValueChangeListener) this);

        form.fuelCost.setValue("0.00");
        form.fuelLitres.setValue("0");
        form.oilCost.setValue("0.00");
        form.oilLitres.setValue("0");
        form.randPerLitreCalc.setValue("0.00");
        form.slipNo.setValue(null);
        form.speedometer.setValue("0");
        form.transactionDate.setValue(new Date());
        form.truckId.setValue(null);
        form.driverId.setValue(null);

//        form.fuelCost.setReadOnly(true);
//        form.fuelLitres.setReadOnly(true);
//        form.oilCost.setReadOnly(true);
//        form.oilLitres.setReadOnly(true);
//        form.randPerLitreCalc.setReadOnly(true);
//        form.slipNo.setReadOnly(true);
//        form.speedometer.setReadOnly(true);
        //
        form.truckId.addValueChangeListener((Property.ValueChangeListener) this);
//        form.fuelLitres.addValueChangeListener((Property.ValueChangeListener) this);
//        form.fuelCost.addValueChangeListener((Property.ValueChangeListener) this);

    }

    private void populateDailyInputsTable(List<OperatingCost> dailyInputList, Truck truck) {
        // Remove Table's ValueChangeListener, add contents to Table then Add  Table's ValueChangeListener
        table.removeValueChangeListener((Property.ValueChangeListener) this);
        table.populateDailyInputTable(dailyInputList, truck);
        table.addValueChangeListener((Property.ValueChangeListener) this);

        filteredTransactionDate = null;
        filteredTruckId = null;
    }

    private void saveForm(FieldGroup binder) {
        if (formTransactionDate.before(resetToDayEnd(new Date())) || formTransactionDate.compareTo(resetToDayEnd(new Date())) == 0) {

            try {
                binder.commit();
                final Truck truck = TruckFacade.getTruckService().findById(((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTruckId());
                Date transactionDate = ((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTransactionDate();
                // Check if a record for current date exists, if true asks for record to be edited
                if (trackerUtil.isThereDuplicateDailyInput(truck, transactionDate)) {
                    Notification.show("A Record matching this date has been ADDED. Duplicates not allowed. Consider editing this record.", Notification.Type.TRAY_NOTIFICATION);
                } else {
                    // TEST FOR MOV AND MMV Trucks. Allow non-checking for Mileage ?????????????????????????????????????????????????????????????????????/
                    Integer previousClosingMileage = trackerUtil.getPreviousDailyInputClosingMileage(truck, transactionDate, transactionDate);
                    String vehicleNumber = form.truckId.getItemCaption(((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTruckId());
                    if ((truncate(vehicleNumber, 3).equals("MOV") || truncate(vehicleNumber, 3).equals("MMV"))) {
                        previousClosingMileage = new Integer("-1");
                    }
//                    Notification.show("Current closing mileage: " + previousClosingMileage + " for " + truck.getVehicleNumber(), Notification.Type.HUMANIZED_MESSAGE);
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
                Collection<Field<?>> fields = binder.getFields();
                OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
                helper.doValidation();
                Notification.show("Please Correct Red Colored Inputs!", Notification.Type.TRAY_NOTIFICATION);
            }
        } else {
            Notification.show("Error. Select any date before Current date. No future entries allowed", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        if (formTransactionDate.before(resetToDayEnd(new Date())) || formTransactionDate.compareTo(resetToDayEnd(new Date())) == 0) {
            try {
                binder.commit();
                final Truck truck = TruckFacade.getTruckService().findById(((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTruckId());
                Date transactionDate = ((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTransactionDate();
                Integer previousClosingMileage = trackerUtil.getPreviousDailyInputClosingMileage(truck, new Date(), transactionDate);
                // TEST FOR MOV AND MMV Trucks. Allow non-checking for Mileage ?????????????????????????????????????????????????????????????????????/
                String vehicleNumber = form.truckId.getItemCaption(((BeanItem<DailyInputsBean>) binder.getItemDataSource()).getBean().getTruckId());
                if ((truncate(vehicleNumber, 3).equals("MOV") || truncate(vehicleNumber, 3).equals("MMV"))) {
                    previousClosingMileage = new Integer("-1");
                }
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
                Collection<Field<?>> fields = binder.getFields();
                OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
                helper.doValidation();
                Notification.show("Please Correct Red Colored Inputs!", Notification.Type.TRAY_NOTIFICATION);
            }
        } else {
            Notification.show("Error. Select any date before Current date. No future entries allowed", Notification.Type.TRAY_NOTIFICATION);
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
                .truckId(dailyInputBean.getTruckId())
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
                .truckId(dailyInputBean.getTruckId())
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
        form.transactionDate.addValueChangeListener((Property.ValueChangeListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private DailyInputsBean getBean(OperatingCost operatingCosts) {
        DailyInputsBean bean = new DailyInputsBean();

        bean.setDriverId(operatingCosts.getDriverId());
        bean.setFuelCost(operatingCosts.getFuelCost());
        bean.setFuelLitres(operatingCosts.getFuelLitres());
        bean.setId(operatingCosts.getId());
        bean.setOilCost(operatingCosts.getOilCost());
        bean.setOilLitres(operatingCosts.getOilLitres());
        bean.setRandPerLitre(operatingCosts.getRandPerLitre());
        bean.setSlipNo(operatingCosts.getSlipNo());
        bean.setSpeedometer(operatingCosts.getSpeedometer());
        bean.setTransactionDate(operatingCosts.getTransactionDate());
        bean.setTruckId(operatingCosts.getTruckId());// not trucKiD;


        return bean;
    }

    public static String truncate(String value, int length) {
        if (value != null && value.length() > length) {
            value = value.substring(0, length);
        }
        return value;
    }

    public Date resetMonthToFirstDay(Date date) {
        return dateTimeFormatHelper.resetTimeAndMonthStart(date);
    }

    public Date resetMonthToLastDay(Date date) {
        return dateTimeFormatHelper.resetTimeAndMonthEnd(date);
    }

    /*
     * reset the time of a Date to 23:59:59:999 and resets the day to the
     * last second of the day
     *
     * @param dateIn Date
     * @return Date
     */
    public Date resetToDayEnd(Date inDate) {
        //
        Calendar cal = Calendar.getInstance();
        cal.setTime(inDate);

        // Set time fields to last hour:minute:second:millisecond
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();

    }
}

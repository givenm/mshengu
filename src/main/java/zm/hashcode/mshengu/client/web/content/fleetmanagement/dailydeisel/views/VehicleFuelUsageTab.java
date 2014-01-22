/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import java.math.RoundingMode;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.DailyDieselTrackerMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms.VehicleFuelUsageForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables.VehicleFuelUsageTable;

/**
 *
 * @author geek
 */
public class VehicleFuelUsageTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    public final VehicleFuelUsageForm form;
    private VehicleFuelUsageTable table;
    private static String trucKiD;

    public VehicleFuelUsageTab(MshenguMain app) {
        main = app;
        form = new VehicleFuelUsageForm();
        table = new VehicleFuelUsageTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
//        if (source == form.save) {
//            saveForm(form.binder);
//        } else if (source == form.edit) {
//            setEditFormProperties();
//        } else if (source == form.cancel) {
//            getHome();
//        } else if (source == form.update) {
//            saveEditedForm(form.binder);
//        } else if (source == form.delete) {
//            deleteForm(form.binder);
//        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
//            final OperatingCost operatingCost = OperatingCostFacade.getOperatingCostService().findById(table.getValue().toString());
//
//            // Find Truck that has this operatingCosts
//            List<Truck> trucks = TruckFacade.getTruckService().findAll();
//            for (Truck truck : trucks) {
//                List<OperatingCost> operatingCostList = truck.getOperatingCosts();
//                for (OperatingCost operatingCostt : operatingCostList) {
//                    if (operatingCostt.getId().equals(operatingCost.getId())) {
//                        trucKiD = truck.getId();
//                        break;
//                    }
//                }
//            }
//
//            form.binder.setItemDataSource(new BeanItem<>(getBean(operatingCost)));
//            setReadFormProperties();
        } else if (property == form.transactionDate) {
//            Truck truck = TruckFacade.getTruckService().findById(form.truckId.getValue().toString());
//            form.driverId.setValue(truck.getDriver().getId());
            table.removeAllItems();
            if (form.truckId.getValue().equals("all")) {
                table.loadVehiclceFuelUsageData(form.transactionDate.getValue());
                form.mtdActAverage.setReadOnly(false);
                form.mtdActAverage.setValue(table.mtdActAverageCalc.setScale(2, RoundingMode.HALF_UP) + "");
                form.mtdActAverage.setReadOnly(true);
            } else {
                table.loadVehiclceFuelUsageData(form.transactionDate.getValue(), form.truckId.getValue().toString());
                form.mtdActAverage.setReadOnly(false);
                form.mtdActAverage.setValue("");
                form.mtdActAverage.setReadOnly(true);
            }

        } else if (property == form.truckId) {
            if (form.transactionDate.getValue() != null) {
                table.removeAllItems();
                if (form.truckId.getValue().equals("all")) {
                    table.loadVehiclceFuelUsageData(form.transactionDate.getValue());
                    form.mtdActAverage.setReadOnly(false);
                    form.mtdActAverage.setValue(table.mtdActAverageCalc.setScale(2, RoundingMode.HALF_UP) + "");
                    form.mtdActAverage.setReadOnly(true);
                } else {
                    table.loadVehiclceFuelUsageData(form.transactionDate.getValue(), form.truckId.getValue().toString());
                    form.mtdActAverage.setReadOnly(false);
                    form.mtdActAverage.setValue("");
                    form.mtdActAverage.setReadOnly(true);
                }
            }
        }
    }

//    private void saveForm(FieldGroup binder) {
//        try {
//            binder.commit();
//            OperatingCost operatingCost = getNewEntity(binder);
//            OperatingCostFacade.getOperatingCostService().persist(operatingCost);
//
//            updateTruckOperatingCost(operatingCost, binder);
//
//            getHome();
//            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
//        } catch (FieldGroup.CommitException e) {
//            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
//            getHome();
//        }
//    }
//
//    private void saveEditedForm(FieldGroup binder) {
//        try {
//            binder.commit();
//            OperatingCost operatingCost = getUpdatedEntity(binder);
//            OperatingCostFacade.getOperatingCostService().merge(operatingCost);
//
//            deleteTruckOperatingCost(binder);
//            updateTruckOperatingCost(operatingCost, binder);
//
//            getHome();
//            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
//        } catch (FieldGroup.CommitException e) {
//            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
//            getHome();
//        }
//    }
//
//    private void deleteForm(FieldGroup binder) {
//        deleteTruckOperatingCost(binder);
//
//        OperatingCostFacade.getOperatingCostService().delete(getUpdatedEntity(binder));
//        getHome();
//    }
//    private OperatingCost getNewEntity(FieldGroup binder) {
//        //final  OperatingCost cust = new OperatingCost.Builder(binder.getItemDataSource().getItemProperty("name")).
//        final DailyDieselTrackerBean dailyDieselBean = ((BeanItem<DailyDieselTrackerBean>) binder.getItemDataSource()).getBean();
//        final Person driver = PersonFacade.getPersonService().findById(dailyDieselBean.getDriverId());   //////////////////////////////////////////////////////
//
//        final OperatingCost operatingCosts = new OperatingCost.Builder(new Date())
//                .fuelCost(dailyDieselBean.getFuelCost())
//                .fuelLitres(dailyDieselBean.getFuelLitres())
//                .oilCost(dailyDieselBean.getOilCost())
//                .oilLitres(dailyDieselBean.getOilLitres())
//                .speedometer(dailyDieselBean.getSpeedometer())
//                .slipNo(dailyDieselBean.getSlipNo())
//                .randPerLitre(dailyDieselBean.getRandPerLitre())
//                .driver(driver)
//                .build();
//
//        return operatingCosts;
//    }
//
//    private OperatingCost getUpdatedEntity(FieldGroup binder) {
//        final DailyDieselTrackerBean dailyDieselBean = ((BeanItem<DailyDieselTrackerBean>) binder.getItemDataSource()).getBean();
//        final Person driver = PersonFacade.getPersonService().findById(dailyDieselBean.getDriverId());   //////////////////////////////////////////////////////
//
//        final OperatingCost operatingCosts = new OperatingCost.Builder(new Date())
//                .fuelCost(dailyDieselBean.getFuelCost())
//                .fuelLitres(dailyDieselBean.getFuelLitres())
//                .oilCost(dailyDieselBean.getOilCost())
//                .oilLitres(dailyDieselBean.getOilLitres())
//                .speedometer(dailyDieselBean.getSpeedometer())
//                .slipNo(dailyDieselBean.getSlipNo())
//                .driver(driver)
//                .randPerLitre(dailyDieselBean.getRandPerLitre())
//                .id(dailyDieselBean.getId())
//                .build();
//
//        return operatingCosts;
//    }
    private void getHome() {
        main.content.setSecondComponent(new DailyDieselTrackerMenu(main, "LANDING"));
    }
//    private void updateTruckOperatingCost(OperatingCost operatingCost, FieldGroup binder) {
//        final Truck truck = TruckFacade.getTruckService().findById(((BeanItem<DailyDieselTrackerBean>) binder.getItemDataSource()).getBean().getTruckId());
//        List<OperatingCost> operatingCostList = new ArrayList<>();
//        operatingCostList.addAll(truck.getOperatingCosts());
//        operatingCostList.add(operatingCost);
//
//        Truck updatedTruck = new Truck.Builder(truck.getNumberPlate())
//                .operatingCosts(operatingCostList)
//                .serviceCosts(truck.getServiceCosts())
//                .authority(truck.getAuthority())
//                .brand(truck.getBrand())
//                .category(truck.getCategory())
//                .controlNo(truck.getControlNo())
//                .dateOfExpire(truck.getDateOfExpire())
//                .description(truck.getDescription())
//                .driver(truck.getDriver())
//                .engineNo(truck.getEngineNo())
//                .isActive(truck.isIsActive())
//                .licenceNo(truck.getLicenceNo())
//                .model(truck.getModel())
//                .paymentAmount(truck.getPaymentAmount())
//                .paymentDate(truck.getPaymentDate())
//                .paymentMethod(truck.getPaymentMethod())
//                .receiptNo(truck.getReceiptNo())
//                .registerYear(truck.getRegisterYear())
//                .routes(truck.getRoutes())
//                .tare(truck.getTare())
//                .vinNo(truck.getVinNo())
//                .id(truck.getId())
//                .build();
//        TruckFacade.getTruckService().merge(updatedTruck);
//    }
//
//    private void deleteTruckOperatingCost(FieldGroup binder) {
//        // Truck assigned to might change, so delete this operatingCost entry for current Truck (see "trucKiD" variable) and reassigned again. Eish
//        // Reason of Domain Model
//        Truck instanceTruck = TruckFacade.getTruckService().findById(trucKiD);
//        List<OperatingCost> operatingCosts = instanceTruck.getOperatingCosts();
//        List<OperatingCost> updatedOperatingCosts = new ArrayList<>();
//        for (OperatingCost operatingCostt : operatingCosts) {
//            if (!operatingCostt.getId().equals(((BeanItem<DailyDieselTrackerBean>) binder.getItemDataSource()).getBean().getId())) {
//                updatedOperatingCosts.add(operatingCostt);
//            }
//        }
//
//        Truck updatedTruck = new Truck.Builder(instanceTruck.getNumberPlate())
//                .operatingCosts(updatedOperatingCosts)
//                .serviceCosts(instanceTruck.getServiceCosts())
//                .authority(instanceTruck.getAuthority())
//                .brand(instanceTruck.getBrand())
//                .category(instanceTruck.getCategory())
//                .controlNo(instanceTruck.getControlNo())
//                .dateOfExpire(instanceTruck.getDateOfExpire())
//                .description(instanceTruck.getDescription())
//                .driver(instanceTruck.getDriver())
//                .engineNo(instanceTruck.getEngineNo())
//                .isActive(instanceTruck.isIsActive())
//                .licenceNo(instanceTruck.getLicenceNo())
//                .model(instanceTruck.getModel())
//                .paymentAmount(instanceTruck.getPaymentAmount())
//                .paymentDate(instanceTruck.getPaymentDate())
//                .paymentMethod(instanceTruck.getPaymentMethod())
//                .receiptNo(instanceTruck.getReceiptNo())
//                .registerYear(instanceTruck.getRegisterYear())
//                .routes(instanceTruck.getRoutes())
//                .tare(instanceTruck.getTare())
//                .vinNo(instanceTruck.getVinNo())
//                .id(instanceTruck.getId())
//                .build();
//        TruckFacade.getTruckService().merge(updatedTruck);
//    }
//
//    private void setEditFormProperties() {
////        form.binder.setReadOnly(false);
////        form.save.setVisible(false);
////        form.edit.setVisible(false);
////        form.cancel.setVisible(true);
////        form.delete.setVisible(false);
////        form.update.setVisible(true);
//    }
//
//    private void setReadFormProperties() {
////        form.binder.setReadOnly(true);
////        //Buttons Behaviour
////        form.save.setVisible(false);
////        form.edit.setVisible(true);
////        form.cancel.setVisible(true);
////        form.delete.setVisible(true);
////        form.update.setVisible(false);
//    }

    private void addListeners() {
        //Register Listeners
        form.transactionDate.addValueChangeListener((Property.ValueChangeListener) this);
        form.truckId.addValueChangeListener((Property.ValueChangeListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
//    private DailyDieselTrackerBean getBean(OperatingCost operatingCosts) {
//        DailyDieselTrackerBean bean = new DailyDieselTrackerBean();
//        bean.setSlipNo(operatingCosts.getSlipNo());
//        bean.setFuelCost(operatingCosts.getFuelCost());
//        bean.setFuelLitres(operatingCosts.getFuelLitres());
//        bean.setOilCost(operatingCosts.getOilCost());
//        bean.setOilLitres(operatingCosts.getOilLitres());
//        bean.setSpeedometer(operatingCosts.getSpeedometer());
//        bean.setTransactionDate(operatingCosts.getTransactionDate());
//        bean.setDriverId(operatingCosts.getDriver().getId());
//        bean.setTruckId(trucKiD);
//        bean.setId(operatingCosts.getId());
//        bean.setRandPerLitre(operatingCosts.getRandPerLitre());
//
//        return bean;
//    }
}

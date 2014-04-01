/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms.DailyTrackerForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables.DailyTrackerTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author geek
 */
public class DailyTrackerTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    public final DailyTrackerForm form;
    private final DailyTrackerTable table;
    private static String trucKiD;
    // Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));
    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private final TrackerUtil trackerUtil = new TrackerUtil();

    public DailyTrackerTab(MshenguMain app) {
        main = app;
        form = new DailyTrackerForm();
        table = new DailyTrackerTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    public Date calendarTenMonthsBackward(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -10);
        return calendar.getTime();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
        } else if (property == form.transactionDate) {
            form.grid.removeComponent(2, 2);
            form.grid.addComponent(new Label(), 2, 2);
            //
            form.targetSpec.setReadOnly(false);
            form.manufacturerSpec.setReadOnly(false);
            form.operatingSpec.setReadOnly(false);
            form.MTD.setReadOnly(false);
            form.driverId.setReadOnly(false);

            //
            try {
                Date transactDate = form.transactionDate.getValue();
                Truck truck = TruckFacade.getTruckService().findById(form.truckId.getValue().toString());
                List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckBetweenTwoDates(truck, calendarTenMonthsBackward(transactDate), dateTimeFormatHelper.resetTimeAndMonthEnd(transactDate));
                trackerUtil.setOperatingCostList(truckOperatingCostList);
                table.removeAllItems();
                table.loadDailyTrackerData(form.transactionDate.getValue(), truck);

                form.driverId.setValue(truck.getDriver().getId());
                BigDecimal RandPerLitre = trackerUtil.getHighestRandPerLiter(/*truck.getOperatingCosts(),*/transactDate);
                if (!(truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MMV") || truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MOV"))) {
                    BigDecimal OperatingSpec = (BigDecimal.valueOf(truck.getManufacturingSpec() / 100)).multiply(RandPerLitre);
                    form.manufacturerSpec.setValue("" + (truck.getManufacturingSpec() / 100));
                    form.operatingSpec.setValue(OperatingSpec.setScale(2, BigDecimal.ROUND_HALF_UP).toString()); // ?????????????????????????????????????????// ?????????????????????????????????????????
                    BigDecimal operatingAllowance = trackerUtil.getOperationalAllowance();

                    form.targetSpec.setValue(OperatingSpec.add(operatingAllowance).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    form.grid.removeComponent(2, 2);
                    form.grid.addComponent(DailyTrackerTable.monthRatingFlagImage, 2, 2);
                    form.MTD.setValue(df.format(Double.parseDouble(DailyTrackerTable.randsPerKilometreCalc.toString())));
                }
            } catch (java.lang.NullPointerException ex) {
                Notification.show("Error. Enter a Valid Date for Filter Transaction Date.", Notification.Type.ERROR_MESSAGE);
            }

            form.targetSpec.setReadOnly(true);
            form.manufacturerSpec.setReadOnly(true);
            form.operatingSpec.setReadOnly(true);
            form.MTD.setReadOnly(true);
            form.driverId.setReadOnly(true);
        } else if (property == form.truckId) {
            form.grid.removeComponent(2, 2);
            form.grid.addComponent(new Label(), 2, 2);

            form.targetSpec.setReadOnly(false);
            form.manufacturerSpec.setReadOnly(false);
            form.operatingSpec.setReadOnly(false);
            form.MTD.setReadOnly(false);
            form.driverId.setReadOnly(false);
//
            Truck truck = TruckFacade.getTruckService().findById(form.truckId.getValue().toString());

            form.driverId.setValue(truck.getDriverId());
            try {
                Date transactDate = form.transactionDate.getValue();
                List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckBetweenTwoDates(truck, calendarTenMonthsBackward(transactDate), dateTimeFormatHelper.resetTimeAndMonthEnd(transactDate));
                trackerUtil.setOperatingCostList(truckOperatingCostList);
                BigDecimal RandPerLitre = trackerUtil.getHighestRandPerLiter(/*truck.getOperatingCosts(), */transactDate);
                BigDecimal OperatingSpec = (BigDecimal.valueOf(truck.getManufacturingSpec() / 100)).multiply(RandPerLitre);
                BigDecimal operatingAllowance = BigDecimal.ZERO;
                if (!(truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MMV") || truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MOV"))) {
                    form.manufacturerSpec.setValue("" + (truck.getManufacturingSpec() / 100)); // ?????????????????????????????????????????
                    form.operatingSpec.setValue(OperatingSpec.setScale(2, BigDecimal.ROUND_HALF_UP).toString()); // ?????????????????????????????????????????// ?????????????????????????????????????????
                    operatingAllowance = trackerUtil.getOperationalAllowance();
                    form.targetSpec.setValue(OperatingSpec.add(operatingAllowance).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                } else {
                    form.manufacturerSpec.setValue("0.00");
                    form.operatingSpec.setValue("0.00");
                    form.targetSpec.setValue("0.00");
                }

                table.removeAllItems();
                table.loadDailyTrackerData(transactDate, truck);
//                form.targetSpec.setValue(OperatingSpec.add(operatingAllowance).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                form.MTD.setValue(df.format(Double.parseDouble(DailyTrackerTable.randsPerKilometreCalc.toString())));
//                form.monthlyFlag.setValue(" <img src=\"" + DailyTrackerTable.monthRatingFlag + "\"/>");
                form.grid.removeComponent(2, 2);
                form.grid.addComponent(DailyTrackerTable.monthRatingFlagImage, 2, 2);
            } catch (java.lang.NullPointerException ex) {
                Notification.show("Error. Enter a Valid Date for Filter Transaction Date.", Notification.Type.ERROR_MESSAGE);
            }

            form.targetSpec.setReadOnly(true);
            form.manufacturerSpec.setReadOnly(true);
            form.operatingSpec.setReadOnly(true);
            form.MTD.setReadOnly(true);
            form.driverId.setReadOnly(true);
        }

    }

    public static String truncate(String value, int length) {
        if (value != null && value.length() > length) {
            value = value.substring(0, length);
        }
        return value;
    }
    //

    private void addListeners() {
        //Register Button Listeners
        form.transactionDate.addValueChangeListener((Property.ValueChangeListener) this);
        form.truckId.addValueChangeListener((Property.ValueChangeListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
}

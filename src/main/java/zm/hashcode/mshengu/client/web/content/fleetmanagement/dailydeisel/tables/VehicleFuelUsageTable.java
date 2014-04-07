/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.flagImages.FlagImage;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.DailyDieselTrackerMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.VehicleFuelUsageData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views.DailyTrackerTab;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author geek
 */
public class VehicleFuelUsageTable extends Table {

    private final MshenguMain main;
    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private final TrackerUtil trackerUtil = new TrackerUtil();
    private final FlagImage flagImage = new FlagImage();
    private VehicleFuelUsageData vehicleFuelUsageData;
    public static List<VehicleFuelUsageData> vehicleFuelUsageDataList = new ArrayList<>();
    public static BigDecimal mtdActAverageCalc = new BigDecimal("0.00");
//    public static int msvTrucksCounter = 0;
    private static Integer monthMileageTotal = 0; //  += fleetFuelUtil.calculateMonthMileageTotal(truckMonthOperatingCostList, truck);
    private static BigDecimal monthFuelCostTotal = BigDecimal.ZERO; //  = monthTotal.add(operatingCost.getFuelCost());
    // Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public VehicleFuelUsageTable(MshenguMain main) {
        this.main = main;
        setSizeFull();
        addContainerProperty("Month/Year", String.class, null);
        addContainerProperty("Truck", Button.class, null);
        addContainerProperty("Number Plate", String.class, null);
        addContainerProperty("Driver", String.class, null);
        addContainerProperty("Fuel Total (R)", String.class, null);        //  Fuel Total(R)
        addContainerProperty("Mileage Total (Km)", Integer.class, null);  //  Mileage Total (Km)
        addContainerProperty("Target", String.class, null);
        addContainerProperty("MTD Act.", String.class, null); // FOR Flagging value
        addContainerProperty("Rating", Embedded.class, null); // FOR Flagging
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

        // Alignments
        setColumnAlignment("Fuel Total(R)", Table.Align.RIGHT);
        setColumnAlignment("Mileage Total (Km)", Table.Align.RIGHT);
        setColumnAlignment("Target", Table.Align.RIGHT);
        setColumnAlignment("MTD Act.", Table.Align.RIGHT);
        setColumnAlignment("Rating", Table.Align.CENTER);
        // Column Sizing
        setColumnWidth("Number Plate", 105);
        setColumnWidth("Fuel Total(R)", 100);
        setColumnWidth("Mileage Total (Km)", 125);
        setColumnWidth("Target", 65);
        setColumnWidth("MTD Act.", 65);
        setColumnWidth("Rating", 50);
    }

    public void loadVehiclceFuelUsageData(Date date) {
        mtdActAverageCalc = BigDecimal.ZERO;
        trackerUtil.setQueriedDate(date);
        // Add Data Columns
        List<Truck> truckList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        vehicleFuelUsageDataList.clear();
        for (Truck truck : truckList) {
            List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckBetweenTwoDates(truck, calendarTenMonthsBackward(date), dateTimeFormatHelper.resetTimeAndMonthEnd(date));
            Collections.sort(truckOperatingCostList, OperatingCost.AscOrderDateAscOrderTruckIdComparator);
            trackerUtil.setOperatingCostList(truckOperatingCostList, date);
            List<OperatingCost> truckCurrentMonthOperatingCostList = trackerUtil.getQueriedMonthOperatingCostList(date);

            if (!truckCurrentMonthOperatingCostList.isEmpty()) {
                BigDecimal lastRandPerLitre = trackerUtil.getHighestRandPerLiter(date);
                final Button truckNumberButton = new Button(truck.getVehicleNumber());
                truckNumberButton.setData(truck.getId() + "#" + date);
                truckNumberButton.setStyleName(Reindeer.BUTTON_LINK);
                truckNumberButton.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        switchToDailyTracker(truckNumberButton.getData());
                    }
                });

                addToVehicleFuelUsageList(date, truck, truckCurrentMonthOperatingCostList, lastRandPerLitre);

                addItem(new Object[]{
                    dateTimeFormatHelper.getMonthYearMonthAsMediumString(date.toString()),
                    truckNumberButton,
                    truck.getNumberPlate(),
                    truck.getDriver().getFirstname() + " " + truck.getDriver().getLastname(),
                    df.format(Double.parseDouble(trackerUtil.sumOfFuelCostCalculation(truckCurrentMonthOperatingCostList).toString())),
                    trackerUtil.doMileageCalculation(truckCurrentMonthOperatingCostList, truck),
                    df.format(Double.parseDouble(trackerUtil.getTarget(trackerUtil.getFuelSpecRandPerKilometre(BigDecimal.valueOf(truck.getManufacturingSpec()), lastRandPerLitre), trackerUtil.getOperationalAllowance()).toString())),
                    df.format(Double.parseDouble(trackerUtil.getMtdAct(truckCurrentMonthOperatingCostList, truck).toString())),
                    flagImage.determineFuelUsageFlag(trackerUtil.getMtdAct(truckCurrentMonthOperatingCostList, truck))
                }, truck.getId());
                // BRAND NEW BRAND NEW BRAND NEW BRAND NEW BRAND NEW BRAND NEW
                if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV")) {
                    monthMileageTotal += trackerUtil.doMileageCalculation(truckCurrentMonthOperatingCostList, truck);
                    monthFuelCostTotal = monthFuelCostTotal.add(trackerUtil.sumOfFuelCostCalculation(truckCurrentMonthOperatingCostList));
//                    System.out.println("Truck= " + truck.getVehicleNumber() + " MonthLY mILEage= " + trackerUtil.doMileageCalculation(truckCurrentMonthOperatingCostList, truck) + ", FUEL SUM= " + trackerUtil.sumOfFuelCostCalculation(truckCurrentMonthOperatingCostList));
                }
                // BRAND NEW BRAND NEW BRAND NEW BRAND NEW BRAND NEW BRAND NEW
            }
        }
        performMtdActAverageCalc();
    }

    public Date calendarTenMonthsBackward(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -10);
        return calendar.getTime();
    }

    public void loadVehiclceFuelUsageData(Date date, String truckId) {
        mtdActAverageCalc = BigDecimal.ZERO;
        trackerUtil.setQueriedDate(date);
        // Add Data Columns
        List<Truck> truckList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        vehicleFuelUsageDataList.clear();
        for (Truck truck : truckList) {
            if (truck.getId().equals(truckId)) {
                List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckBetweenTwoDates(truck, calendarTenMonthsBackward(date), dateTimeFormatHelper.resetTimeAndMonthEnd(date));
                Collections.sort(truckOperatingCostList, OperatingCost.AscOrderDateAscOrderTruckIdComparator);
                trackerUtil.setOperatingCostList(truckOperatingCostList, date);
                List<OperatingCost> truckCurrentMonthOperatingCostList = trackerUtil.getQueriedMonthOperatingCostList(date);

                if (!truckCurrentMonthOperatingCostList.isEmpty()) {
                    BigDecimal lastRandPerLitre = trackerUtil.getHighestRandPerLiter(date);
                    final Button truckNumberButton = new Button(truck.getVehicleNumber());
                    truckNumberButton.setData(truck.getId() + "#" + date);
                    truckNumberButton.setStyleName(Reindeer.BUTTON_LINK);
                    truckNumberButton.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            switchToDailyTracker(truckNumberButton.getData());
                        }
                    });

                    addToVehicleFuelUsageList(date, truck, truckCurrentMonthOperatingCostList, lastRandPerLitre);

                    addItem(new Object[]{
                        dateTimeFormatHelper.getMonthYearMonthAsMediumString(date.toString()),
                        truckNumberButton,
                        truck.getNumberPlate(),
                        truck.getDriver().getFirstname() + " " + truck.getDriver().getLastname(),
                        df.format(Double.parseDouble(trackerUtil.sumOfFuelCostCalculation(truckCurrentMonthOperatingCostList).toString())),
                        trackerUtil.doMileageCalculation(truckCurrentMonthOperatingCostList, truck),
                        df.format(Double.parseDouble(trackerUtil.getTarget(trackerUtil.getFuelSpecRandPerKilometre(BigDecimal.valueOf(truck.getManufacturingSpec()), lastRandPerLitre), trackerUtil.getOperationalAllowance()).toString())),
                        df.format(Double.parseDouble(trackerUtil.getMtdAct(truckCurrentMonthOperatingCostList, truck).toString())),
                        flagImage.determineFuelUsageFlag(trackerUtil.getMtdAct(truckCurrentMonthOperatingCostList, truck))
                    }, truck.getId());

                    // BRAND NEW BRAND NEW BRAND NEW BRAND NEW BRAND NEW BRAND NEW
                    if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV")) {
                        monthMileageTotal += trackerUtil.doMileageCalculation(truckCurrentMonthOperatingCostList, truck);
                        monthFuelCostTotal = monthFuelCostTotal.add(trackerUtil.sumOfFuelCostCalculation(truckCurrentMonthOperatingCostList));
                    }
                    // BRAND NEW BRAND NEW BRAND NEW BRAND NEW BRAND NEW BRAND NEW

                } else {
                    Notification.show("No records were found matching specified Date and Truck!", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        }
        performMtdActAverageCalc();
    }

    private void switchToDailyTracker(Object object) {
        DailyDieselTrackerMenu dailyDieselTrackerMenu = new DailyDieselTrackerMenu(main, "DAILY_TRACKER");
        main.content.setSecondComponent(dailyDieselTrackerMenu);
        //
        SimpleDateFormat simpleFormat = (SimpleDateFormat) DateFormat.getDateInstance();
        simpleFormat.applyPattern("EEE MMM dd HH:mm:ss zzz yyyy"); //  new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); // // "Mon Sep 30 00:00:00 SAST 2013"
        Date datee = null;

        StringTokenizer stringTokenizer = new StringTokenizer(object.toString(), "#");
        String truckId = stringTokenizer.nextElement().toString();
        try {
            datee = simpleFormat.parse(stringTokenizer.nextElement().toString());
//            Notification.show("DATE I RECEIVED FROM CLICKED LINK IS: " + truckId + "<br/>" + datee, Notification.Type.TRAY_NOTIFICATION);
            dailyDieselTrackerMenu.getDailyTrackerTab().form.transactionDate.setValue(datee);
            dailyDieselTrackerMenu.getVehicleFuelUsage().form.transactionDate.setValue(datee);
            dailyDieselTrackerMenu.getDailyTrackerTab().form.truckId.setValue(truckId);

        } catch (ParseException ex) {
            Logger.getLogger(DailyTrackerTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToVehicleFuelUsageList(Date date, Truck truck, List<OperatingCost> queriedMonthOperatingCostList, BigDecimal lastRandPerLitre) {
        vehicleFuelUsageData = new VehicleFuelUsageData();
        vehicleFuelUsageData.setDate(dateTimeFormatHelper.getMonthYearMonthAsMediumString(date.toString()));
        vehicleFuelUsageData.setDriverName(truck.getDriver().getFirstname() + " " + truck.getDriver().getLastname());
//        vehicleFuelUsageData.setFuelSpec(Double.parseDouble(trackerUtil.getFuelSpecRandPerKilometre(BigDecimal.valueOf(truck.getManufacturingSpec()), lastRandPerLitre).toString()));
        vehicleFuelUsageData.setTotalFuelCost(trackerUtil.sumOfFuelCostCalculation(queriedMonthOperatingCostList));
        vehicleFuelUsageData.setTotalMileage(trackerUtil.doMileageCalculation(queriedMonthOperatingCostList, truck));
        vehicleFuelUsageData.setMtdAct(Double.parseDouble(trackerUtil.getMtdAct(queriedMonthOperatingCostList, truck).toString()));
//        if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV")) {
//            msvTrucksCounter++;...
//            mtdActAverageCalc = mtdActAverageCalc.add(new BigDecimal(vehicleFuelUsageData.getMtdAct().toString()));
//        }
//        vehicleFuelUsageData.setOperationalAllowance(Double.parseDouble(trackerUtil.getOperationalAllowance().toString()));
        vehicleFuelUsageData.setTarget(Double.parseDouble(trackerUtil.getTarget(trackerUtil.getFuelSpecRandPerKilometre(BigDecimal.valueOf(truck.getManufacturingSpec()), lastRandPerLitre), trackerUtil.getOperationalAllowance()).toString()));
        vehicleFuelUsageData.setTruckId(truck.getId());
        vehicleFuelUsageData.setTruckNumber(truck.getVehicleNumber());
        vehicleFuelUsageData.setTruckPlateNumber(truck.getNumberPlate());
        //
        vehicleFuelUsageDataList.add(vehicleFuelUsageData);
    }

    private void performMtdActAverageCalc() {
        try {
            if (!vehicleFuelUsageDataList.isEmpty()) {
                System.out.println("monthFuelCostTotal (" + monthFuelCostTotal + ") / monthMileageTotal (" + monthMileageTotal + ")");
                mtdActAverageCalc = monthFuelCostTotal.divide(new BigDecimal(monthMileageTotal + ""), 2, BigDecimal.ROUND_HALF_UP);
            }
        } catch (ArithmeticException a) {
            System.out.println("monthMileageTotal (" + monthMileageTotal + ") / monthFuelCostTotal (" + monthFuelCostTotal + ") = Attemping to DIVIDE by ZERO Exception Caught");
            monthMileageTotal = 0;
            monthFuelCostTotal = BigDecimal.ZERO;
        }

        monthMileageTotal = 0;
        monthFuelCostTotal = BigDecimal.ZERO;
    }

    public static String truncate(String value, int length) {
        if (value != null && value.length() > length) {
            value = value.substring(0, length);
        }
        return value;
    }

}

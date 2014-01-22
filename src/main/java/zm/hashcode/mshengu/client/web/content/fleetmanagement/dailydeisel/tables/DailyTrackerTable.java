/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.DailyTrackerTableData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author geek
 */
public class DailyTrackerTable extends Table {

    private final MshenguMain main;
    // Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("#0.00", new DecimalFormatSymbols(locale));
    //
    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private final TrackerUtil trackerUtil = new TrackerUtil();
    // List of DATA in tables
    public static List<DailyTrackerTableData> dailyTrackerTableDataList = new ArrayList<>();
    public static List<DailyTrackerTableData> dailyTrackerTableDataListFinal = new ArrayList<>();
    public Date date;
    public Truck truck;
    //SubTotaling
    public static BigDecimal amountSum = BigDecimal.ZERO;
    public static Double litresSum = 0.0;
    public static Integer tripSum = 0;
    public static BigDecimal randsPerLiterCalc = BigDecimal.ZERO;
    public static Integer closingMileageCalc = 0;
    public static BigDecimal randsPerKilometreCalc = BigDecimal.ZERO;
    public static Embedded monthRatingFlag; // USE the randsPerKilometreCalc above
    public static Double litresPerKilometerCalc = 0.0;
    public static Image monthRatingFlagImage;

    //
    public DailyTrackerTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Date", String.class, null);// Retrieved from DB - OperatingCost
        addContainerProperty("Amount (R)", String.class, null);// Retrieved from DB - OperatingCost
        addContainerProperty("Litres", String.class, null);// Retrieved from DB - OperatingCost
        addContainerProperty("Rands/Ltr", String.class, null); // Calc
        addContainerProperty("Closing Mileage", Integer.class, null); // Retrieved from DB - OperatingCost
        addContainerProperty("Trip", Integer.class, null);// Calc
        addContainerProperty("Rands/Km", String.class, null);// Calc
        addContainerProperty("Rating", Embedded.class, null); // FOR Flagging // Calc
        addContainerProperty("Litres/Km", String.class, null); // Calc
        addContainerProperty("Driver", String.class, null); // Retrieved from DB - OperatingCost

//        // Set Table Footers
//        setFooterVisible(true);
        //
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(false);
        // Send changes in selection immediately to server.
        setImmediate(true);

        // Alignments
        setColumnAlignment("Amount (R)", Table.Align.RIGHT);
        setColumnAlignment("Litres", Table.Align.RIGHT);
        setColumnAlignment("Rands/Ltr", Table.Align.RIGHT);
        setColumnAlignment("Closing Mileage", Table.Align.RIGHT);
        setColumnAlignment("Trip", Table.Align.RIGHT);
        setColumnAlignment("Rands/Km", Table.Align.RIGHT);
        setColumnAlignment("Litres/Km", Table.Align.RIGHT);
        setColumnAlignment("Rating", Table.Align.CENTER);

        // Column Sizing
        setColumnWidth("Closing Mileage", 105);
        setColumnWidth("Rands/Ltr", 65);
        setColumnWidth("Rating", 50);
        setColumnWidth("Litres/Km", 65);
        setColumnWidth("Amount (R)", 105);
        setColumnWidth("Rands/Km", 70);
    }

    public void loadDailyTrackerData(Date date, Truck truck) {
        clearSubtotals();
        this.date = date;
        this.truck = truck;
        trackerUtil.setQueriedDate(date);

        // Add Data Columns
        trackerUtil.setOperatingCostList(truck.getOperatingCosts());
        List<OperatingCost> queriedMonthOperatingCostList = trackerUtil.getQueriedMonthOperatingCostList(truck.getOperatingCosts(), date);

// -- =============================================================================================================================
        if (!queriedMonthOperatingCostList.isEmpty()) {
            dailyTrackerTableDataList.clear();
            dailyTrackerTableDataListFinal.clear();

            //Add OperatingCost Entries for Days with and without Data to the dailyTrackerTableDataList
            // Create a calendar object and set year and month
            Calendar dateCal = Calendar.getInstance();
            dateCal.set(Integer.parseInt(dateTimeFormatHelper.getYearNumber(date)), Integer.parseInt(dateTimeFormatHelper.getMonthNumber(date)), 1);
            int daysCount = dateCal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i <= daysCount; i++) {
                boolean dateMatch = false;
                Date dateCalDate = dateTimeFormatHelper.resetTimeOfDate(dateTimeFormatHelper.getDate(i, dateCal.get(Calendar.MONTH), dateCal.get(Calendar.YEAR)));
                Date transactDate;
                // int dailyTrackerIndex = -1;
                for (OperatingCost operatingCost : queriedMonthOperatingCostList) {
                    transactDate = dateTimeFormatHelper.resetTimeOfDate(operatingCost.getTransactionDate());
//                    System.out.println("\n\nMonth Day Count = " + daysCount + " cALENDAR Date = " + dateCalDate + " COMPARED TO Transact Date = " + transactDate + "\n\n");
                    if (dateCalDate.equals(transactDate)) {
                        DailyTrackerTableData dailyTrackerTableData = new DailyTrackerTableData();
                        dailyTrackerTableData.setId(operatingCost.getId());
                        dailyTrackerTableData.setTransactionDate(dateTimeFormatHelper.getYearMonthDay(operatingCost.getTransactionDate()));
                        dailyTrackerTableData.setTransactDate(dateTimeFormatHelper.resetTimeOfDate(operatingCost.getTransactionDate()));
                        dailyTrackerTableData.setAmount(operatingCost.getFuelCost());
                        dailyTrackerTableData.setLitres(operatingCost.getFuelLitres());
                        dailyTrackerTableData.setRandsPerLiter(trackerUtil.getRandPerLitre(operatingCost.getFuelCost(), operatingCost.getFuelLitres()));
                        dailyTrackerTableData.setClosingMileage(operatingCost.getSpeedometer());
                        dailyTrackerTableData.setTrip(Integer.valueOf("0"));
                        dailyTrackerTableData.setRandsPerKilometer(new BigDecimal("0.00"));
                        dailyTrackerTableData.setRating(new Embedded());
                        dailyTrackerTableData.setLitresPerKilometer(Double.valueOf("0.00"));

                        dailyTrackerTableData.setDriverId(operatingCost.getDriverId());
                        dailyTrackerTableData.setDriverName(operatingCost.getDriver().getFirstname() + " " + truck.getDriver().getLastname());
                        dailyTrackerTableData.setTruckId(truck.getId());
                        dailyTrackerTableData.setTruckPlateNumber(truck.getNumberPlate());

                        dailyTrackerTableDataList.add(dailyTrackerTableData);
                        dateMatch = true;
                        break;
                    }
                }
///////////////////////////////////////////////////////////////
                if (!dateMatch) {
                    // Add new item with ZEROS or BLANKS to the ArrayList
                    DailyTrackerTableData dailyTrackerTableData = new DailyTrackerTableData();
                    dailyTrackerTableData.setId(i + "");
                    dailyTrackerTableData.setTransactionDate(dateTimeFormatHelper.getYearMonthDay(dateCalDate));
                    dailyTrackerTableData.setTransactDate(dateCalDate);
                    dailyTrackerTableData.setAmount(new BigDecimal("0.00"));
                    dailyTrackerTableData.setLitres(Double.valueOf("0.00"));
                    dailyTrackerTableData.setRandsPerLiter(new BigDecimal("0.00"));
                    if (i == 1) {
                        dailyTrackerTableData.setClosingMileage(trackerUtil.calculatePreviousMonthClosingMileage(truck));
                    } else {
                        dailyTrackerTableData.setClosingMileage(dailyTrackerTableDataList.get(dailyTrackerTableDataList.size() - 1).getClosingMileage());
                    }
                    dailyTrackerTableData.setTrip(Integer.valueOf("0"));
                    dailyTrackerTableData.setRandsPerKilometer(new BigDecimal("0.00"));
                    dailyTrackerTableData.setRating(new Embedded());
                    dailyTrackerTableData.setLitresPerKilometer(Double.valueOf("0.00"));

                    dailyTrackerTableData.setDriverId("");
                    dailyTrackerTableData.setDriverName("---");
                    dailyTrackerTableData.setTruckId("");
                    dailyTrackerTableData.setTruckPlateNumber("");

                    dailyTrackerTableDataList.add(dailyTrackerTableData);
                }
                //increment the date in Calendar by one day
                dateCal.add(Calendar.DAY_OF_MONTH, 1);
            }
// -- =============================================================================================================================
            for (DailyTrackerTableData dailyTrackerTableData : dailyTrackerTableDataList) {
                dailyTrackerTableDataListFinal.add(setDailyTrackerTableDataFinal(dailyTrackerTableDataList, dailyTrackerTableData));
            }

            // Calculation for SubTotals
            performSubtotals(queriedMonthOperatingCostList);

            // Add SubTotal Row to Table
            addSubtotalRow();

            for (DailyTrackerTableData dailyTrackerTableData : dailyTrackerTableDataListFinal) {
//             Write data at index to Table
                addItem(new Object[]{
                    dateTimeFormatHelper.getDayMonthYear(dailyTrackerTableData.getTransactDate()),
                    //                    operatingCost.getFuelLitres() == null ? "" : df.format((Double) operatingCost.getFuelLitres()),

                    dailyTrackerTableData.getAmount() == null ? "" : df.format(Double.parseDouble(dailyTrackerTableData.getAmount().toString())),
                    dailyTrackerTableData.getLitres() == null ? "" : df.format((Double) dailyTrackerTableData.getLitres()),
                    dailyTrackerTableData.getRandsPerLiter() == null ? "" : df.format(Double.parseDouble(dailyTrackerTableData.getRandsPerLiter().toString())),
                    dailyTrackerTableData.getClosingMileage(),
                    dailyTrackerTableData.getTrip(),
                    dailyTrackerTableData.getRandsPerKilometer() == null ? "" : df.format(Double.parseDouble(dailyTrackerTableData.getRandsPerKilometer().toString())),
                    dailyTrackerTableData.getRating(),
                    dailyTrackerTableData.getLitresPerKilometer() == null ? "" : df.format((Double) dailyTrackerTableData.getLitresPerKilometer()),
                    dailyTrackerTableData.getDriverName()
                }, dailyTrackerTableData.getId());
            }

            // perform Subtotal Row Styling to Yellow
            performRowStyling();
//            setColumnWidth("Amount (R)", 70);
//            setColumnWidth("Rands/Ltr", 60);
//            setColumnWidth("Closing Mileage", 100);

        } else {
            monthRatingFlagImage = null; // Form won't show previouse flag image
            Notification.show("No records were found matching specified Date!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

//    private DailyTrackerTableData setDailyTrackerTableData(List<OperatingCost> queriedMonthOperatingCostList, OperatingCost operatingCost) {
//        DailyTrackerTableData dailyTrackerTableData = new DailyTrackerTableData();
//        dailyTrackerTableData.setId(operatingCost.getId());
//        dailyTrackerTableData.setTransactionDate(dateTimeFormatHelper.getYearMonthDay(operatingCost.getTransactionDate()));
//        dailyTrackerTableData.setTransactDate(operatingCost.getTransactionDate());
//        dailyTrackerTableData.setAmount(operatingCost.getFuelCost());
//        dailyTrackerTableData.setLitres(operatingCost.getFuelLitres());
//        dailyTrackerTableData.setRandsPerLiter(trackerUtil.getRandPerLitre(operatingCost.getFuelCost(), operatingCost.getFuelLitres()));
//        dailyTrackerTableData.setClosingMileage(operatingCost.getSpeedometer());
//        dailyTrackerTableData.setTrip(trackerUtil.calculateOperatingCostTrip(queriedMonthOperatingCostList, operatingCost, truck));
//        dailyTrackerTableData.setRandsPerKilometer(trackerUtil.getRandPerKilometre(operatingCost.getFuelCost(), trackerUtil.calculateOperatingCostTrip(queriedMonthOperatingCostList, operatingCost, truck)));
//        dailyTrackerTableData.setRating(trackerUtil.determineFlag(trackerUtil.getRandPerKilometre(operatingCost.getFuelCost(), trackerUtil.calculateOperatingCostTrip(queriedMonthOperatingCostList, operatingCost, truck))));
//        dailyTrackerTableData.setLitresPerKilometer(trackerUtil.getLitrePerKilometer(operatingCost.getFuelLitres(), trackerUtil.calculateOperatingCostTrip(queriedMonthOperatingCostList, operatingCost, truck)));
//        dailyTrackerTableData.setDriverId(operatingCost.getDriver().getId());
//        dailyTrackerTableData.setDriverName(operatingCost.getDriver().getFirstname() + " " + truck.getDriver().getLastname());
//        dailyTrackerTableData.setTruckId(truck.getId());
//        dailyTrackerTableData.setTruckPlateNumber(truck.getNumberPlate());
//        return dailyTrackerTableData;
//    }
    private DailyTrackerTableData setDailyTrackerTableDataFinal(List<DailyTrackerTableData> dailyTrackerTableDataList, DailyTrackerTableData dailyTrackerData) {
        DailyTrackerTableData dailyTrackerTableData = new DailyTrackerTableData();
        dailyTrackerTableData.setId(dailyTrackerData.getId());
        dailyTrackerTableData.setTransactionDate(dailyTrackerData.getTransactionDate());
        dailyTrackerTableData.setTransactDate(dailyTrackerData.getTransactDate());
        dailyTrackerTableData.setAmount(dailyTrackerData.getAmount());
        dailyTrackerTableData.setLitres(dailyTrackerData.getLitres());
//        dailyTrackerTableData.setRandsPerLiter(trackerUtil.getRandPerLitre(dailyTrackerData.getAmount(), dailyTrackerData.getLitres()));
        dailyTrackerTableData.setRandsPerLiter(dailyTrackerData.getRandsPerLiter());
        dailyTrackerTableData.setClosingMileage(dailyTrackerData.getClosingMileage());
        dailyTrackerTableData.setTrip(trackerUtil.calculateTrip(dailyTrackerTableDataList, dailyTrackerData, truck));
        dailyTrackerTableData.setRandsPerKilometer(trackerUtil.getRandPerKilometre(dailyTrackerData.getAmount(), trackerUtil.calculateTrip(dailyTrackerTableDataList, dailyTrackerData, truck)));
        dailyTrackerTableData.setRating(trackerUtil.determineFlag(trackerUtil.getRandPerKilometre(dailyTrackerData.getAmount(), trackerUtil.calculateTrip(dailyTrackerTableDataList, dailyTrackerData, truck))));
        dailyTrackerTableData.setLitresPerKilometer(trackerUtil.getLitrePerKilometer(dailyTrackerData.getLitres(), trackerUtil.calculateTrip(dailyTrackerTableDataList, dailyTrackerData, truck)));
        dailyTrackerTableData.setDriverId(dailyTrackerData.getDriverId());
        dailyTrackerTableData.setDriverName(dailyTrackerData.getDriverName());
        dailyTrackerTableData.setTruckId(truck.getId());
        dailyTrackerTableData.setTruckPlateNumber(truck.getNumberPlate());
// Subtotaling
        amountSum = amountSum.add(dailyTrackerData.getAmount());
        litresSum += dailyTrackerData.getLitres();
        tripSum += trackerUtil.calculateTrip(dailyTrackerTableDataList, dailyTrackerData, truck);
//
        return dailyTrackerTableData;
    }

    public void clearSubtotals() {
        //SubTotaling
        amountSum = BigDecimal.ZERO;
        litresSum = 0.0;
        tripSum = 0;
        randsPerLiterCalc = BigDecimal.ZERO;
        closingMileageCalc = 0;
        randsPerKilometreCalc = BigDecimal.ZERO;
        monthRatingFlag = null; // USE the randsPerKilometreCalc above
        litresPerKilometerCalc = 0.0;
    }

    public void performSubtotals(List<OperatingCost> queriedMonthOperatingCostList) {
        // Calculation for SubTotals
        randsPerLiterCalc = amountSum.divide(new BigDecimal(litresSum), 2, RoundingMode.HALF_UP);
        closingMileageCalc = trackerUtil.doMileageCalculation(queriedMonthOperatingCostList, truck);
        //
        if (amountSum.compareTo(BigDecimal.ZERO) > 0 && closingMileageCalc > 0) {
            randsPerKilometreCalc = amountSum.divide(new BigDecimal(closingMileageCalc), 2, RoundingMode.HALF_UP);
//                randsPerKilometreCalc.setScale(2, RoundingMode.HALF_UP);
        } else {
            try {
                randsPerKilometreCalc = amountSum.divide(new BigDecimal(tripSum), 2, RoundingMode.HALF_UP);
            } catch (ArithmeticException ae) {
                System.out.println("ArithmeticException occured!  Attempt to divide by ZERO");
//                System.out.println(ae.printStackTrace()+"");
                randsPerKilometreCalc = new BigDecimal("0.00");
            }
        }
        //
        monthRatingFlag = trackerUtil.determineFlag(randsPerKilometreCalc);
        // Get flag to show in Layout in FOrm
        monthRatingFlagImage = trackerUtil.determineImageFlag(randsPerKilometreCalc);

        if (litresSum > 0 && closingMileageCalc > 0) {
            litresPerKilometerCalc = litresSum / closingMileageCalc;
        } else {
            try {
                litresPerKilometerCalc = litresSum / tripSum;
            } catch (ArithmeticException ae) {
                System.out.println("ArithmeticException occured! Attempt to divide by ZERO");
                litresPerKilometerCalc = 0.00;
            }

        }
    }

    public void addSubtotalRow() {
        addItem(new Object[]{
            "MTD Total",
            df.format(Double.parseDouble(amountSum.toString())),
            df.format((Double) trackerUtil.round(litresSum, 2)),
            df.format(Double.parseDouble(randsPerLiterCalc.toString())),
            closingMileageCalc,
            tripSum,
            df.format(Double.parseDouble(randsPerKilometreCalc.toString())),
            monthRatingFlag,
            df.format((Double) trackerUtil.round(litresPerKilometerCalc, 2)),
            truck.getDriver().getFirstname() + " " + truck.getDriver().getLastname()
        }, "subtotal");
    }

    public void performRowStyling() {
        // cell style generator
        setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {
                if (propertyId != null) {
                    return null; // Do not set individual cell styles
                }
                String rowId = ((String) itemId).toString();
                if (rowId.equals("subtotal")) {
                    return "yellowrow";
                }
                return null;
            }
        });

    }
}

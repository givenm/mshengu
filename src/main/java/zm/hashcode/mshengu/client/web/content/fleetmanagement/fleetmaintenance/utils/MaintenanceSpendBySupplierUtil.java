/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.MaintenanceSpendBySupplierFacade;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendBySupplier;
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Colin
 */
public class MaintenanceSpendBySupplierUtil implements Serializable {

    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
//    private TrackerUtil trackerUtil = new TrackerUtil();
    private static Date startDate = null;
    private static Date endDate = null;
    public static BigDecimal grandTotalMaintenanceSpend = BigDecimal.ZERO;
//    public static BigDecimal grandTotalMonthlySpend = BigDecimal.ZERO;
    private static List<ServiceProvider> fleetServiceProviderList = new ArrayList<>();
    private static List<TotalMaintenanceSpendBySupplier> totalMaintenanceSpendBySupplierList = new ArrayList<>();
    private static List<MaintenanceSpendBySupplier> maintenanceSpendBySupplierList = new ArrayList<>();
    // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
    private final Date liveDataStartDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 0, 2014)); // 1, 0, 2014    Test Live ON LOCAL: 1, 11, 2013 // 10 = Nov, 11 = Dec, 0 = Jan
    // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
    private final Date staticDataStartDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 7, 2012));
    private final Date staticDataEndDate = this.resetMonthToLastDay(dateTimeFormatHelper.getDate(31, 10, 2013));   // 10 = Nov, 11 = Dec

    public void findFleetServiceProviderList() {
        if (fleetServiceProviderList.isEmpty()) {
            fleetServiceProviderList = ServiceProviderFacade.getServiceProviderService().getVehicleMaintenanceServiceProvders();// ????????????????????????????????????????????????
        }
    }

    /**
     * pass the current date and the amount of months to be calculated backward
     * and the start date and end date will be set
     *
     * @param endDate Date
     * @param dateRange int
     *
     */
    public void determineDateRange(Date endDate, int dateRange) {
        findFleetServiceProviderList();
        // Range is calculated as ending with Month before current month,
        // and counting into the past number of months specified in dateRange
        Calendar calendarEndDate = Calendar.getInstance();
        Calendar calendarStartDate = Calendar.getInstance();

        calendarEndDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));
        calendarEndDate.add(Calendar.MONTH, -1);
        this.endDate = resetMonthToLastDay(calendarEndDate.getTime());

        calendarStartDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));
        calendarStartDate.add(Calendar.MONTH, -dateRange);
        startDate = resetMonthToFirstDay(calendarStartDate.getTime());
//
//        System.out.println("StartDate: " + startDate + " | EndDate: " + this.endDate);
    }

    public Date resetMonthToFirstDay(Date date) {
        final Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateTimeFormatHelper.resetTimeAndMonthStart(date));
        calendarDate.set(Calendar.DAY_OF_MONTH, 1); // ! reset to 1ST of Month
        return calendarDate.getTime();
    }

    public Date resetMonthToLastDay(Date date) {
        final Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(date));
        calendarDate.set(Calendar.DAY_OF_MONTH, calendarDate.getActualMaximum(Calendar.DAY_OF_MONTH)); // ! reset to LAST of Month (28,29,30,31)
        return calendarDate.getTime();
    }

    public List<MaintenanceSpendBySupplier> findMaintenanceSpendBySupplierBetweenTwoDates(Date startDate, Date endDate) {
        findFleetServiceProviderList();
        maintenanceSpendBySupplierList.clear();
        if (endDate.before(staticDataEndDate) || endDate.compareTo(staticDataEndDate) == 0) {
            return MaintenanceSpendBySupplierFacade.getMaintenanceSpendBySupplierService().getMaintenanceSpendBetweenTwoDates(startDate, endDate);

//            maintenanceSpendBySupplierList.addAll(MaintenanceSpendBySupplierFacade.getMaintenanceSpendBySupplierService().getMaintenanceSpendBetweenTwoDates(startDate, endDate));
//            Integer counter = new Integer("0");
//            // Add Zero entries for Months without Static Data
//            Calendar calendar = Calendar.getInstance();
//            for (calendar.setTime(startDate); calendar.getTime().before(endDate) || calendar.getTime().compareTo(endDate) == 0; calendar.add(Calendar.MONTH, 1)) {
//                boolean found = false;
//                for (Truck truck : serviceTrucks) {
//                    for (MaintenanceSpendBySupplier maintenanceSpendBySupplier : maintenanceSpendBySupplierList) {
//                        if (resetMonthToFirstDay(maintenanceSpendBySupplier.getTransactionDate()).compareTo(resetMonthToFirstDay(calendar.getTime())) == 0) {
//                            if (maintenanceSpendBySupplier.getTruckId().equals(truck.getId())) {
//                                found = true;
//                                break;
//                            }
//                        }
//                    }
//                    if (!found) {
//                        // Build ZERO Entry AnnualDataFleetMaintenanceCost for current Truck for current Month
//                        counter++;
//                        maintenanceSpendBySupplierList.add(buildAnnualDataFleetMaintenanceCostList(counter, truck, calendar, BigDecimal.ZERO));
//                    }
//                }
//            }
//            return maintenanceSpendBySupplierList;
        } else {
            if (startDate.before(staticDataEndDate) || startDate.compareTo(staticDataEndDate) == 0) {
                performStaticAndLiveDataHarvesting(startDate, endDate);
            } else {
                performLiveDataHarvesting(startDate, endDate);
            }
////            for (MaintenanceSpendBySupplier maintenanceSpendBySupplier : maintenanceSpendBySupplierList) {
////                System.out.println("SPEND_BY_SUPPLIER, Date= " + maintenanceSpendBySupplier.getTransactionDate() + " | Cost= " + maintenanceSpendBySupplier.getMaintenanceCost() + " | SupplierId= " + maintenanceSpendBySupplier.getSupplierId() + " | TruckId= " + maintenanceSpendBySupplier.getTruckId());
////            }
            return maintenanceSpendBySupplierList;
        }
    }

    private void performStaticAndLiveDataHarvesting(Date startDate, Date endDate) {
        Integer counter = new Integer("0");
        maintenanceSpendBySupplierList.addAll(MaintenanceSpendBySupplierFacade.getMaintenanceSpendBySupplierService().getMaintenanceSpendBetweenTwoDates(startDate, staticDataEndDate));
//        // Add Zero entries for Months without Static Data
//        Calendar calendar = Calendar.getInstance();
//        for (calendar.setTime(startDate); calendar.getTime().before(staticDataEndDate) || calendar.getTime().compareTo(staticDataEndDate) == 0; calendar.add(Calendar.MONTH, 1)) {
//            boolean found = false;
//            for (Truck truck : serviceTrucks) {
//                for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : maintenanceCostList) {
//                    if (resetMonthToFirstDay(annualDataFleetMaintenanceCost.getTransactionMonth()).compareTo(resetMonthToFirstDay(calendar.getTime())) == 0) {
//                        if (annualDataFleetMaintenanceCost.getTruckId().equals(truck.getId())) {
//                            found = true;
//                            break;
//                        }
//                    }
//                }
//                if (!found) {
//                    // Build ZERO Entry AnnualDataFleetMaintenanceCost for current Truck for current Month
//                    counter++;
//                    maintenanceCostList.add(buildAnnualDataFleetMaintenanceCostList(counter, truck, calendar, BigDecimal.ZERO));
//                }
//            }
//        }

        // get MaintenanceCost live data from Dec 1st 2013 till EndDate from Request domain
        // Aggregate the request list and Add to maintenanceCostList (i.e. Monthly summary per Truck throughout the date range)

        // Date Loop OR Calendar Loop from startDate till endDate
        Calendar startCalendar = Calendar.getInstance();
        for (startCalendar.setTime(liveDataStartDate); startCalendar.getTime().before(endDate) || startCalendar.getTime().compareTo(endDate) == 0; startCalendar.add(Calendar.MONTH, 1)) {
            for (ServiceProvider serviceProvider : fleetServiceProviderList) {
                List<Request> requestList = RequestFacade.getRequestService().getTransactedRequestsByServiceProviderByMonth(serviceProvider, startCalendar.getTime());
                if (!requestList.isEmpty()) {
                    for (Request request : requestList) {
                        // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
                        counter++;
                        if (request.getServiceProvider().getVatNum() != null) {
                            // REMOVE VAT CHARGES FROM TOTAL
                            maintenanceSpendBySupplierList.add(buildMaintenanceSpendBySupplierList(counter, serviceProvider, request.getTruckId(), startCalendar, getTotalExcludingVAT(request.getTotal())));
                        } else {
                            maintenanceSpendBySupplierList.add(buildMaintenanceSpendBySupplierList(counter, serviceProvider, request.getTruckId(), startCalendar, request.getTotal()));
                        }
                    }
                }
//                else {
//                    // Build ZERO Entry AnnualDataFleetMaintenanceCost for current Truck for current Month
//                    counter++;
//                    maintenanceSpendBySupplierList.add(buildMaintenanceSpendBySupplierList(counter, serviceProvider, startCalendar, BigDecimal.ZERO));
////                    System.out.println("No Entry For Request From live Data for this Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(startCalendar.getTime().toString()) + " | Truck= " + truck.getVehicleNumber());
////                    System.out.println("--==--");
//                }
            }
        }


    }

    private void performLiveDataHarvesting(Date startDate, Date endDate) {
        // Aggregate the request list and Add to maintenanceCostList (i.e. Monthly summary per Truck throughout the date range)
        Integer counter = new Integer("0");
        // Date Loop OR Calendar Loop from startDate till endDate
        Calendar startCalendar = Calendar.getInstance();
        for (startCalendar.setTime(startDate); startCalendar.getTime().before(endDate) || startCalendar.getTime().compareTo(endDate) == 0; startCalendar.add(Calendar.MONTH, 1)) {
            for (ServiceProvider serviceProvider : fleetServiceProviderList) {
                List<Request> requestList = RequestFacade.getRequestService().getTransactedRequestsByServiceProviderByMonth(serviceProvider, startCalendar.getTime());
                if (!requestList.isEmpty()) {
                    for (Request request : requestList) {
                        // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
                        counter++;
                        if (request.getServiceProvider().getVatNum() != null) {
                            // REMOVE VAT CHARGES FROM TOTAL
                            maintenanceSpendBySupplierList.add(buildMaintenanceSpendBySupplierList(counter, serviceProvider, request.getTruckId(), startCalendar, getTotalExcludingVAT(request.getTotal())));
                        } else {
                            maintenanceSpendBySupplierList.add(buildMaintenanceSpendBySupplierList(counter, serviceProvider, request.getTruckId(), startCalendar, request.getTotal()));
                        }
                    }
                }
//                else {
//                    // Build ZERO Entry AnnualDataFleetMaintenanceCost for current Truck for current Month
//                    counter++;
//                    maintenanceSpendBySupplierList.add(buildMaintenanceSpendBySupplierList(counter, serviceProvider, startCalendar, BigDecimal.ZERO));
////                    System.out.println("No Entry For Request From live Data for this Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(startCalendar.getTime().toString()) + " | Truck= " + truck.getVehicleNumber());
////                    System.out.println("--==--");
//                }
            }
        }
    }

    private MaintenanceSpendBySupplier buildMaintenanceSpendBySupplierList(int counter, ServiceProvider serviceProvider, String truckId, Calendar startCalendar, BigDecimal monthlyCost) {
        // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
        final MaintenanceSpendBySupplier maintenanceSpendBySupplier = new MaintenanceSpendBySupplier.Builder(startCalendar.getTime())
                .id(counter + "")
                .maintenanceCost(monthlyCost)
                .supplierId(serviceProvider.getId())
                .truckId(truckId)
                .build();

        return maintenanceSpendBySupplier;
    }

    public BigDecimal getTotalExcludingVAT(BigDecimal totalWithVAT) {
        BigDecimal VATValue = new BigDecimal("0.14").multiply(totalWithVAT);
        VATValue.setScale(2, BigDecimal.ROUND_UP);

        totalWithVAT = totalWithVAT.subtract(VATValue);
        return totalWithVAT.setScale(2, BigDecimal.ROUND_UP);
    }

    public List<TotalMaintenanceSpendBySupplier> buildTotalMaintenanceSpendBySupplier(List<MaintenanceSpendBySupplier> maintenanceSpendBySupplierList) {
        Collections.sort(maintenanceSpendBySupplierList, MaintenanceSpendBySupplier.AscOrderSupplierComparator);
        totalMaintenanceSpendBySupplierList.clear();
        BigDecimal total = BigDecimal.ZERO;
        String currentSupplierId = maintenanceSpendBySupplierList.get(0).getSupplierId();
        int counter = 0;
        for (MaintenanceSpendBySupplier maintenanceSpendBySupplier : maintenanceSpendBySupplierList) {
            if (maintenanceSpendBySupplier.getSupplierId().equals(currentSupplierId)) {
                total = total.add(maintenanceSpendBySupplier.getMaintenanceCost());

                // Test for Last item in List and SubTotal
                if (maintenanceSpendBySupplierList.indexOf(maintenanceSpendBySupplier) == maintenanceSpendBySupplierList.size() - 1) {
                    counter++;
                    performSubTotal(total, maintenanceSpendBySupplierList, maintenanceSpendBySupplier, counter);
                }

            } else {
                // Subtotal
                counter++;
                performSubTotal(total, maintenanceSpendBySupplierList, maintenanceSpendBySupplier, counter);
                //Reset
                total = maintenanceSpendBySupplier.getMaintenanceCost();
                currentSupplierId = maintenanceSpendBySupplier.getSupplierId();
            }
        }
        calculatePercentage();
        return totalMaintenanceSpendBySupplierList;
    }

    private void performSubTotal(BigDecimal total, List<MaintenanceSpendBySupplier> maintenanceSpendBySupplierList, MaintenanceSpendBySupplier maintenanceSpendBySupplier, int counter) {
        // Subtotal
        total.setScale(2, BigDecimal.ROUND_UP);
        grandTotalMaintenanceSpend = grandTotalMaintenanceSpend.add(total);
        // Build TotalMaintenanceSpendBySupplier and add to ArrayList
        int currentIndex = maintenanceSpendBySupplierList.indexOf(maintenanceSpendBySupplier);
        totalMaintenanceSpendBySupplierList.add(buildTotalMaintenanceSpendBySupplierObject(counter, total, maintenanceSpendBySupplierList.get(currentIndex - 1)));

    }

    private TotalMaintenanceSpendBySupplier buildTotalMaintenanceSpendBySupplierObject(int counter, BigDecimal total, MaintenanceSpendBySupplier maintenanceSpendBySupplier) {
        ServiceProvider currentServiceProvider = null;
        for (ServiceProvider serviceProvder : fleetServiceProviderList) {
            if (serviceProvder.getId().equals(maintenanceSpendBySupplier.getSupplierId())) {
                currentServiceProvider = serviceProvder;  //serviceProvder.isVehicleMaintenance()
                break;
            }
        }
        TotalMaintenanceSpendBySupplier totalMaintenanceSpendBySupplier = new TotalMaintenanceSpendBySupplier();
        totalMaintenanceSpendBySupplier.setId(counter + "");
//        totalMaintenanceSpendBySupplier.setMonth();
//        totalMaintenanceSpendBySupplier.setSpendPercentage();
        totalMaintenanceSpendBySupplier.setSupplierName(currentServiceProvider.getName());
        totalMaintenanceSpendBySupplier.setTotal(total);
        totalMaintenanceSpendBySupplier.setVendorNumber(currentServiceProvider.getVendorNumber());
        return totalMaintenanceSpendBySupplier;
    }

    private void calculatePercentage() {
        grandTotalMaintenanceSpend = roundBigDecimal(grandTotalMaintenanceSpend, 2);
        for (TotalMaintenanceSpendBySupplier totalMaintenanceSpendBySupplier : totalMaintenanceSpendBySupplierList) {
            BigDecimal percentage = totalMaintenanceSpendBySupplier.getTotal().divide(grandTotalMaintenanceSpend, 2, RoundingMode.HALF_UP); // BigDecimal.ROUND_UP
            percentage = percentage.multiply(new BigDecimal("100"));
            percentage = roundBigDecimal(percentage, 0);
            //
            totalMaintenanceSpendBySupplier.setSpendPercentage(percentage);
        }
    }

    public BigDecimal roundBigDecimal(BigDecimal value, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException();
        }
        return value.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }
}

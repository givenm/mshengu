/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.procurement.AnnualDataFleetMaintenanceCostFacade;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Colin
 */
public class MaintenanceCostUtil implements Serializable {

    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
    private final List<AnnualDataFleetMaintenanceCost> maintenanceCostList = new ArrayList<>();
    private final Date liveDataStartDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 0, 2014)); // 1, 0, 2014    Test Live ON LOCAL: 1, 11, 2013 // 10 = Nov, 11 = Dec, 0 = Jan
    // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
    private final Date staticDataStartDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 7, 2012));
    private final Date staticDataEndDate = this.resetMonthToLastDay(dateTimeFormatHelper.getDate(31, 11, 2013)); // 31, 11, 2013    Test Live ON LOCAL: 31, 10, 2013   // 10 = Nov, 11 = Dec
    private static List<Truck> serviceTrucks;

    /**
     * Retrieves a List of AnnualDataFleetMaintenanceCost between a specified
     * start date and and end date range
     *
     * @param startDate Date
     * @param endDate Date
     * @return List AnnualDataFleetMaintenanceCost
     */
    public List<AnnualDataFleetMaintenanceCost> findMaintenanceCostBetweenTwoDates(Date startDate, Date endDate, List<Truck> serviceTrucks) {
        MaintenanceCostUtil.serviceTrucks = serviceTrucks;
        maintenanceCostList.clear();
        if (endDate.before(staticDataEndDate) || endDate.compareTo(staticDataEndDate) == 0) {
            maintenanceCostList.addAll(AnnualDataFleetMaintenanceCostFacade.getAnnualDataFleetMaintenanceCostService().getAnnualDataCostBetweenTwoDates(startDate, endDate));
            Integer counter = new Integer("0");
            // Add Zero entries for Months without Static Data
            Calendar calendar = Calendar.getInstance();
            for (calendar.setTime(startDate); calendar.getTime().before(endDate) || calendar.getTime().compareTo(endDate) == 0; calendar.add(Calendar.MONTH, 1)) {
                boolean found = false;
                for (Truck truck : serviceTrucks) {
                    for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : maintenanceCostList) {
                        if (resetMonthToFirstDay(annualDataFleetMaintenanceCost.getTransactionMonth()).compareTo(resetMonthToFirstDay(calendar.getTime())) == 0) {
                            if (annualDataFleetMaintenanceCost.getTruckId().equals(truck.getId())) {
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        // Build ZERO Entry AnnualDataFleetMaintenanceCost for current Truck for current Month
                        counter++;
                        maintenanceCostList.add(buildAnnualDataFleetMaintenanceCostList(counter, truck, calendar, BigDecimal.ZERO));
                    }
                }
            }
            return maintenanceCostList;
        } else {
            if (startDate.before(staticDataEndDate) || startDate.compareTo(staticDataEndDate) == 0) {
                performStaticAndLiveDataHarvesting(startDate, endDate);
            } else {
                performLiveDataHarvesting(startDate, endDate);
            }
        }
        return maintenanceCostList;
    }

    private void performStaticAndLiveDataHarvesting(Date startDate, Date endDate) {
        Integer counter = new Integer("0");
        maintenanceCostList.addAll(AnnualDataFleetMaintenanceCostFacade.getAnnualDataFleetMaintenanceCostService().getAnnualDataCostBetweenTwoDates(startDate, staticDataEndDate));
        // Add Zero entries for Months without Static Data
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(startDate); calendar.getTime().before(staticDataEndDate) || calendar.getTime().compareTo(staticDataEndDate) == 0; calendar.add(Calendar.MONTH, 1)) {
            boolean found = false;
            for (Truck truck : serviceTrucks) {
                for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : maintenanceCostList) {
                    if (resetMonthToFirstDay(annualDataFleetMaintenanceCost.getTransactionMonth()).compareTo(resetMonthToFirstDay(calendar.getTime())) == 0) {
                        if (annualDataFleetMaintenanceCost.getTruckId().equals(truck.getId())) {
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    // Build ZERO Entry AnnualDataFleetMaintenanceCost for current Truck for current Month
                    counter++;
                    maintenanceCostList.add(buildAnnualDataFleetMaintenanceCostList(counter, truck, calendar, BigDecimal.ZERO));
                }
            }
        }
        // get MaintenanceCost live data from Dec 1st 2013 till EndDate from Request domain
        // Aggregate the request list and Add to maintenanceCostList (i.e. Monthly summary per Truck throughout the date range)

        // Date Loop OR Calendar Loop from startDate till endDate
        Calendar startCalendar = Calendar.getInstance();
        for (startCalendar.setTime(liveDataStartDate); startCalendar.getTime().before(endDate) || startCalendar.getTime().compareTo(endDate) == 0; startCalendar.add(Calendar.MONTH, 1)) {
            for (Truck truck : serviceTrucks) {
                BigDecimal accumulatedTotal = BigDecimal.ZERO;
                List<Request> requestList = RequestFacade.getRequestService().getTransactedRequestsByTruckByMonth(truck, startCalendar.getTime());
                if (!requestList.isEmpty()) {
                    for (Request request : requestList) {
                        if (request.getServiceProvider().getVatNum() != null) {
                            // REMOVE VAT CHARGES FROM TOTAL
                            accumulatedTotal = accumulatedTotal.add(getTotalExcludingVAT(request.getTotal()));
                        } else {
                            accumulatedTotal = accumulatedTotal.add(request.getTotal());
                        }
                    }
                    // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
                    counter++;
                    maintenanceCostList.add(buildAnnualDataFleetMaintenanceCostList(counter, truck, startCalendar, accumulatedTotal));
                } else {
                    // Build ZERO Entry AnnualDataFleetMaintenanceCost for current Truck for current Month
                    counter++;
                    maintenanceCostList.add(buildAnnualDataFleetMaintenanceCostList(counter, truck, startCalendar, BigDecimal.ZERO));
//                    System.out.println("No Entry For Request From live Data for this Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(startCalendar.getTime().toString()) + " | Truck= " + truck.getVehicleNumber());
//                    System.out.println("--==--");
                }
            }
        }
    }

    private void performLiveDataHarvesting(Date startDate, Date endDate) {
        // Aggregate the request list and Add to maintenanceCostList (i.e. Monthly summary per Truck throughout the date range)
        Integer counter = new Integer("0");
        // Date Loop OR Calendar Loop from startDate till endDate
        Calendar startCalendar = Calendar.getInstance();
        for (startCalendar.setTime(startDate); startCalendar.getTime().before(endDate) || startCalendar.getTime().compareTo(endDate) == 0; startCalendar.add(Calendar.MONTH, 1)) {
            for (Truck truck : serviceTrucks) {
                BigDecimal accumulatedTotal = BigDecimal.ZERO;
                List<Request> requestList = RequestFacade.getRequestService().getTransactedRequestsByTruckByMonth(truck, startCalendar.getTime());
                if (!requestList.isEmpty()) {
                    for (Request request : requestList) {
                        if (request.getServiceProvider().getVatNum() != null) {
                            // REMOVE VAT CHARGES FROM TOTAL
                            accumulatedTotal = accumulatedTotal.add(getTotalExcludingVAT(request.getTotal()));
                        } else {
                            accumulatedTotal = accumulatedTotal.add(request.getTotal());
                        }
                    }
                    // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
                    counter++;
                    maintenanceCostList.add(buildAnnualDataFleetMaintenanceCostList(counter, truck, startCalendar, accumulatedTotal));
                } else {
                    // Build ZERO Entry AnnualDataFleetMaintenanceCost for current Truck for current Month
                    counter++;
                    maintenanceCostList.add(buildAnnualDataFleetMaintenanceCostList(counter, truck, startCalendar, BigDecimal.ZERO));
//                    System.out.println("No Entry For Request From live Data for this Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(startCalendar.getTime().toString()) + " | Truck= " + truck.getVehicleNumber());
//                    System.out.println("--==--");
                }
            }
        }
    }

    private AnnualDataFleetMaintenanceCost buildAnnualDataFleetMaintenanceCostList(int counter, Truck truck, Calendar startCalendar, BigDecimal monthlyCost) {
        // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
        final AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost = new AnnualDataFleetMaintenanceCost.Builder(startCalendar.getTime())
                .driverPersonId(truck.getDriver().getId())
                .id(counter + "")
                .monthlyMaintenanceCost(monthlyCost)
                .truckId(truck.getId())
                .build();

        return annualDataFleetMaintenanceCost;
    }

    public Date resetMonthToFirstDay(Date date) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateTimeFormatHelper.resetTimeAndMonthStart(date));
        calendarDate.set(Calendar.DAY_OF_MONTH, 1); // ! reset to 1ST of Month
        return calendarDate.getTime();
    }

    public Date resetMonthToLastDay(Date date) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(date));
        calendarDate.set(Calendar.DAY_OF_MONTH, calendarDate.getActualMaximum(Calendar.DAY_OF_MONTH)); // ! reset to LAST of Month (28,29,30,31)
        return calendarDate.getTime();
    }

    public BigDecimal getTotalExcludingVAT(BigDecimal totalWithVAT) {
        BigDecimal VATValue = new BigDecimal("0.14").multiply(totalWithVAT);
        VATValue.setScale(2, BigDecimal.ROUND_UP);

        totalWithVAT = totalWithVAT.subtract(VATValue);
        return totalWithVAT.setScale(2, BigDecimal.ROUND_UP);
    }
}

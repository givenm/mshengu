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
import static zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.FleetMaintenanceUtil.serviceTrucks;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Colin
 */
public class MaintenanceCostUtil implements Serializable {

    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private final Date liveDataBeginDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 0, 2014)); // 1, 0, 2014    Test Live: 1, 11, 2013 // 10 = Nov, 11 = Dec, 0 = Jan
    // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
    private final Date staticDataEndDate = this.resetMonthToFirstDay(dateTimeFormatHelper.getDate(31, 11, 2013)); // 31, 11, 2013    Test Live: 31, 10, 2013   // 10 = Nov, 11 = Dec
    private final List<AnnualDataFleetMaintenanceCost> maintenanceCostList = new ArrayList<>();

    /**
     * Retrieves a List of AnnualDataFleetMaintenanceCost between a specified
     * start date and and end date range
     *
     * @param startDate Date
     * @param endDate Date
     * @return List AnnualDataFleetMaintenanceCost
     */
    public List<AnnualDataFleetMaintenanceCost> findMaintenanceCostBetweenTwoDates(Date startDate, Date endDate) {
        Date liveDataEndDate = this.resetMonthToLastDay(endDate);
        Date newEndDate = resetMonthToLastDay(endDate);
        // if endDate is before Dec 1st, 2013, ELSE Collect live data
        if (newEndDate.before(staticDataEndDate) || newEndDate.equals(staticDataEndDate)) {
            return AnnualDataFleetMaintenanceCostFacade.getAnnualDataFleetMaintenanceCostService().getMonthlyMaintenanceCostBtnTwoDates(startDate, endDate);
        } else {
            if (startDate.before(staticDataEndDate)) {

                // get MaintenanceCost static Data
                maintenanceCostList.addAll(AnnualDataFleetMaintenanceCostFacade.getAnnualDataFleetMaintenanceCostService().getMonthlyMaintenanceCostBtnTwoDates(startDate, staticDataEndDate));

                // get MaintenanceCost live data from Dec 1st 2013 till EndDate from Request domain
                List<Request> requestList = RequestFacade.getRequestService().getTransactedRequestsBtnTwoDates(liveDataBeginDate, liveDataEndDate);
                // Aggregate the request list and Add to maintenanceCostList (i.e. Monthly summary per Truck throughout the date range)
                if (requestList.size() > 0) {
                    serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
                    Integer counter = new Integer("0");
                    // Date Loop OR Calendar Loop from startDate till endDate
                    Calendar startCalendar = Calendar.getInstance();
                    for (startCalendar.setTime(liveDataBeginDate); startCalendar.getTime().before(liveDataEndDate); startCalendar.add(Calendar.MONTH, 1)) {
                        Calendar endOfMonth = Calendar.getInstance();
                        endOfMonth.setTime(this.resetMonthToLastDay(startCalendar.getTime()));
                        for (Truck truck : serviceTrucks) {
                            BigDecimal accumulatedTotal = BigDecimal.ZERO;
                            for (Request request : requestList) {
                                try {
                                    if (truck.getId().equals(request.getTruck().getId())
                                            && (request.getDeliveryDate().compareTo(startCalendar.getTime()) == 0
                                            || request.getDeliveryDate().compareTo(endOfMonth.getTime()) == 0
                                            || (request.getDeliveryDate().after(startCalendar.getTime()) && request.getDeliveryDate().before(endOfMonth.getTime())))) {
                                        // Accumulate the Total for each Request for current Truck for current Month
                                        accumulatedTotal = accumulatedTotal.add(request.getTotal());
                                    }
                                } catch (java.lang.NullPointerException ex) {
                                }
                            }
                            // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
                            counter++;
                            final AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost = new AnnualDataFleetMaintenanceCost.Builder(startCalendar.getTime())
                                    .driverPersonId(truck.getDriver().getId())
                                    .id(counter + "")
                                    .monthlyMaintenanceCost(accumulatedTotal)
                                    .truckId(truck.getId())
                                    .build();

                            // ADD to maintenanceCostList List
                            maintenanceCostList.add(annualDataFleetMaintenanceCost);
                        }
                    }
                }
            } else {
                // get all MaintenanceCost from StartDate till EndDate from Request domain (i.e. live data)
                // Aggregate the request list and Add to maintenanceCostList  (i.e. Monthly summary per Truck throughout the date range)
                // get MaintenanceCost live data from Dec 1st 2013 till EndDate from Request domain
                List<Request> requestList = RequestFacade.getRequestService().getTransactedRequestsBtnTwoDates(startDate, endDate);
                // Aggregate the request list and Add to maintenanceCostList (i.e. Monthly summary per Truck throughout the date range)
                if (requestList.size() > 0) {
                    serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
                    Integer counter = new Integer("0");
                    // Date Loop OR Calendar Loop from startDate till endDate
                    Calendar startCalendar = Calendar.getInstance();
                    for (startCalendar.setTime(this.resetMonthToFirstDay(liveDataBeginDate)); startCalendar.getTime().before(this.resetMonthToLastDay(endDate)); startCalendar.add(Calendar.MONTH, 1)) {
                        Calendar endOfMonth = Calendar.getInstance();
                        endOfMonth.setTime(this.resetMonthToLastDay(startCalendar.getTime()));
                        for (Truck truck : serviceTrucks) {
                            BigDecimal accumulatedTotal = BigDecimal.ZERO;
                            for (Request request : requestList) {
                                if (truck.getId().equals(request.getTruck().getId())
                                        && (request.getDeliveryDate().compareTo(startCalendar.getTime()) == 0
                                        || request.getDeliveryDate().compareTo(endOfMonth.getTime()) == 0
                                        || (request.getDeliveryDate().after(startCalendar.getTime()) && request.getDeliveryDate().before(endOfMonth.getTime())))) {
                                    // Accumulate the Total for each Request for current Truck for current Month
                                    accumulatedTotal = accumulatedTotal.add(request.getTotal());
                                }
                            }
                            // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
                            counter++;
                            final AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost = new AnnualDataFleetMaintenanceCost.Builder(startCalendar.getTime())
                                    .driverPersonId(truck.getDriver().getId())
                                    .id(counter + "")
                                    .monthlyMaintenanceCost(accumulatedTotal)
                                    .truckId(truck.getId())
                                    .build();

                            // ADD to maintenanceCostList List
                            maintenanceCostList.add(annualDataFleetMaintenanceCost);
                        }
                    }
                }
            }
        }
        return maintenanceCostList;
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
}

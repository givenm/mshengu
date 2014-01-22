/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import java.util.Date;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.domain.products.SiteServiceLogCompletionStatusEnum;
import zm.hashcode.mshengu.domain.products.SiteServiceLogServiceStatusEnum;
import zm.hashcode.mshengu.domain.products.SiteServiceLogStatusEnum;

/**
 *
 * @author Ferox
 */
@Service
public class SiteServiceLogsStatusHelper {

    private String status;
    private String serviceStatus;
    private String completionStatus;
    private DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();

    public void setServiceLogsStatus(boolean isNextDayAVistDay, int totalNumberOfUnits, int totalNumberOfUnitsServiced) {
        if (isNextDayAVistDay) {
            getClosedStatus(totalNumberOfUnits, totalNumberOfUnitsServiced);
        } else {
            getOpenStatus(totalNumberOfUnits, totalNumberOfUnitsServiced);
        }

    }
    /*
     * NEXT DAY IS NOT A VISIT DAY
     */

    private void getOpenStatus(int totalNumberOfUnits, int totalNumberOfUnitsServiced) {
        if (totalNumberOfUnitsServiced > totalNumberOfUnits) {
            setCompletionStatus(SiteServiceLogCompletionStatusEnum.INCORRECT.name());
            setServiceStatus(SiteServiceLogServiceStatusEnum.SERVICED.name());
            setStatus(SiteServiceLogStatusEnum.NEEDS_REVIEW.name());
        } else {
            if (totalNumberOfUnitsServiced == 0) {

                setCompletionStatus(SiteServiceLogCompletionStatusEnum.NOT_STARTED.name());
                setServiceStatus(SiteServiceLogServiceStatusEnum.PENDING.name());
                setStatus(SiteServiceLogStatusEnum.OPEN.name());

            } else if (totalNumberOfUnitsServiced == totalNumberOfUnits) {

                setCompletionStatus(SiteServiceLogCompletionStatusEnum.COMPLETE.name());
                setServiceStatus(SiteServiceLogServiceStatusEnum.SERVICED.name());
                setStatus(SiteServiceLogStatusEnum.CLOSED.name());

            } else {
                setCompletionStatus(SiteServiceLogCompletionStatusEnum.INCOMPLETE.name());
                setServiceStatus(SiteServiceLogServiceStatusEnum.SERVICED.name());
                setStatus(SiteServiceLogStatusEnum.OPEN.name());
            }
        }
    }

    /*
     * NEXT VISIT DAY IS A VIST DAY
     */
    private void getClosedStatus(int totalNumberOfUnits, int totalNumberOfUnitsServiced) {
        if (totalNumberOfUnitsServiced > totalNumberOfUnits) {
            setCompletionStatus(SiteServiceLogCompletionStatusEnum.INCORRECT.name());
            setServiceStatus(SiteServiceLogServiceStatusEnum.SERVICED.name());
            setStatus(SiteServiceLogStatusEnum.NEEDS_REVIEW.name());
        } else {
            if (totalNumberOfUnitsServiced == 0) {

                setCompletionStatus(SiteServiceLogCompletionStatusEnum.NOT_STARTED.name());
                setServiceStatus(SiteServiceLogServiceStatusEnum.NOT_SERVICED.name());
                setStatus(SiteServiceLogStatusEnum.CLOSED.name());

            } else if (totalNumberOfUnitsServiced == totalNumberOfUnits) {

                setCompletionStatus(SiteServiceLogCompletionStatusEnum.COMPLETE.name());
                setServiceStatus(SiteServiceLogServiceStatusEnum.SERVICED.name());
                setStatus(SiteServiceLogStatusEnum.CLOSED.name());

            } else {
                setCompletionStatus(SiteServiceLogCompletionStatusEnum.INCOMPLETE.name());
                setServiceStatus(SiteServiceLogServiceStatusEnum.SERVICED.name());
                setStatus(SiteServiceLogStatusEnum.CLOSED.name());
            }
        }
    }

    public String getSiteServiceLogServiceStatus(SiteServiceLog siteServiceLog) {
        if (siteServiceLog != null) {
            if (SiteServiceLogCompletionStatusEnum.NOT_STARTED.name().equalsIgnoreCase(siteServiceLog.getCompletionStatus())) {
                return SiteServiceLogServiceStatusEnum.NOT_SERVICED.name();
            } else {
                return SiteServiceLogServiceStatusEnum.NOT_SERVICED.name();
            }
        } else {
            return SiteServiceLogServiceStatusEnum.NOT_SERVICED.name();
        }
    }

    public boolean isNexDayAVistDay(SiteServiceContractLifeCycle siteServiceContractLifeCycle, int dayOfWeekTomorrow) {
        boolean isVisitDay = true;

        if (siteServiceContractLifeCycle != null) {

            switch (dayOfWeekTomorrow) {
                case 1:
                    isVisitDay = siteServiceContractLifeCycle.isSunday();
                    break;
                case 2:
                    isVisitDay = siteServiceContractLifeCycle.isMonday();
                    break;
                case 3:
                    isVisitDay = siteServiceContractLifeCycle.isTuesday();
                    break;
                case 4:
                    isVisitDay = siteServiceContractLifeCycle.isWednesday();
                    break;
                case 5:
                    isVisitDay = siteServiceContractLifeCycle.isThursday();
                    break;
                case 6:
                    isVisitDay = siteServiceContractLifeCycle.isFriday();
                    break;
                case 7:
                    isVisitDay = siteServiceContractLifeCycle.isSaturday();
                    break;
                default:
                    isVisitDay = true;
                    break;

            }
        }

        System.out.println("Tomorrow Is " + dtfwh.getDayOfWeekTomorrowStr());
        System.out.println("Is Tomorrow Next Visit Day " + isVisitDay);
        System.out.println("Calendar.DATE " + dtfwh.getDateTodayStr());
        return isVisitDay;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    private void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the serviceStatus
     */
    public String getServiceStatus() {
        return serviceStatus;
    }

    /**
     * @param serviceStatus the serviceStatus to set
     */
    private void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    /**
     * @return the completionStatus
     */
    public String getCompletionStatus() {
        return completionStatus;
    }

    /**
     * @param completionStatus the completionStatus to set
     */
    private void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setDate(Date date) {
        dtfwh.setDate(date);
        dtfwh.resetDayOfWeek();
    }
}
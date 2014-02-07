/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;

/**
 *
 * @author Luckbliss
 */
public interface SiteServiceLogService {

    public List<SiteServiceLog> findAll();

    public void persist(SiteServiceLog siteServiceLog);

    public void merge(SiteServiceLog siteServiceLog);

    public SiteServiceLog findById(String id);

    public void delete(SiteServiceLog siteServiceLog);

    /**
     *
     * @param siteName
     * @param statusMessage WITHIN or User Away From UNIT
     * @param serviceDateStart
     * @param serviceDateEnd
     * @return long count
     *
     * The query in this method lacks a criteria, it should filter distinct unit
     * id's cos a unit should not be serviced more than once in a day.
     */
    public long getTotalUnitsServiced(String siteName, String statusMessage, Date serviceDateStart, Date serviceDateEnd);

    /**
     *
     * @param date
     * @return 
     * @returns all open service logs that have been logged before Monday of the
     * current week (according to the date passed)
     */
    public List<SiteServiceLog> getOutdatedOpenLogs(Date date);

    public List<SiteServiceLog> getSiteServiceLogsException(String siteName, Date startDate, Date endDate, String serviceStatus);

    public List<SiteServiceLog> getAllSiteServiceLogs(String siteName, Date startDate, Date endDate);
    
    public List<SiteServiceLog> getAllServiceLogsException(Date startDate, Date endDate, String serviceStatus);
    
    public List<SiteServiceLog> getAllServiceLogs(Date startDate, Date endDate);

}

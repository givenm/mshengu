/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices;

import java.util.List;
import zm.hashcode.mshengu.domain.products.LogSiteEvents;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author boniface
 */
public interface LogSiteEventService {
    
    public List<LogSiteEvents> findAll();
    
    
//    public Site findBySiteId(String SiteId);
//     public Site findBySiteName(String siteName);

    public boolean areLogsEven(String siteId);

    public String firstUnitLogEvent(LogSiteEvents logSiteEvents);

    public String lastUnitLogEvent(LogSiteEvents logSiteEvents);

    public String getLatestUnitIDWithStartAction(String truckId, String siteId);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices;

import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luckbliss
 */
@Service
public interface LogSheduledSiteServices {

    public void createTodaysSiteServicesLogs(Date date);    
    public void createTodaysSiteServicesLogs2(Date date);

    public void updateOpensSiteServicesLogs(Date date);

    public void closeOutdatedSiteServicesLogs(Date date);
}

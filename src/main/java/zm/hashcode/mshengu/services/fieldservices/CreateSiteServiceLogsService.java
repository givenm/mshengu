/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices;

import java.util.Date;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;

/**
 *
 * @author Ferox
 */
@Service
public interface CreateSiteServiceLogsService {

    public void createSiteServiceLog(Site site, Date date);
    
    public void updateSiteServiceLog(Site site, Date date);
    
     public void closeOutdatedSiteService(SiteServiceLog siteServiceLog, Date date);
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 *
 * @author Luckbliss
 */
public interface SiteService {

    public List<Site> findAll();

    public void persist(Site site);

    public void merge(Site site);

    public Site findById(String id);

    public void delete(Site site);

    public Site findByName(String name);

    public List<Site> findByName(String name, String blankString);

    public SiteServiceContractLifeCycle getSitetCurrentContract(String id);

    public String geoPlotSite(Site site, String latitude, String longitude);

    public List<SiteUnit> findAllSiteUnit(String siteName);

    public List<Site> findAllWithVisitOnMonday();

    public List<Site> findAllWithVisitOnTuesday();

    public List<Site> findAllWithVisitOnWednesday();

    public List<Site> findAllWithVisitOnThursday();

    public List<Site> findAllWithVisitOnFriday();

    public List<Site> findAllWithVisitOnSaturday();

    public List<Site> findAllWithVisitOnSunday();

    public List<Site> findAllWithVisitToday(Date date);

    public List<Site> findAllWithLastLogOpen();

    public List<Site> findAllWithLastLogClosed();

    public List<Site> findAllWithLastLogNeedsReview();
         

}
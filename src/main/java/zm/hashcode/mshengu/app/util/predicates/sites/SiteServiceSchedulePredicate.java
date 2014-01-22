package zm.hashcode.mshengu.app.util.predicates.sites;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;

/**
 *
 * @author boniface
 */
public class SiteServiceSchedulePredicate implements Predicate<Site> {

//    private String visitaDay;
//
//    public SiteServiceSchedulePredicate(String visitaDay) {
//        this.visitaDay = visitaDay;
//    }

    @Override
    public boolean apply(Site site) {
       if (site != null) {
            return getLatestServiceContract(site.getLastSiteServiceContractLifeCycle());
        }
        return false;
    }


    private boolean getLatestServiceContract(SiteServiceContractLifeCycle siteServiceContractLifeCycle) {
        if (siteServiceContractLifeCycle != null) {
            return siteServiceContractLifeCycle.isMonday();
        }
        return false;
    }
}

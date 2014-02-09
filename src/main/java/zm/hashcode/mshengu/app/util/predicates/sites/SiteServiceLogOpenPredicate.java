package zm.hashcode.mshengu.app.util.predicates.sites;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.domain.products.SiteServiceLogStatusEnum;

/**
 *
 * @author ferox
 */
public class SiteServiceLogOpenPredicate implements Predicate<Site> {

    @Override
    public boolean apply(Site site) {
        if (getLatestServiceLog(site)) {
            return true;
        }
        return false;
    }

    private boolean getLatestServiceLog(Site site) {
        if (site != null) {
            return isServiceLogOpen(site.getLastSiteServiceLog());
        }
        return false;
    }

    private boolean isServiceLogOpen(SiteServiceLog siteServiceLog) {
        if (siteServiceLog != null) {
            if (siteServiceLog.getStatus().equalsIgnoreCase(SiteServiceLogStatusEnum.OPEN.name())) {
                return true;
            }
        }
        return false;
    }
}

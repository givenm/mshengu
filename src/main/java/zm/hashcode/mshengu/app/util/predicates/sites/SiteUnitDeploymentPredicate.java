package zm.hashcode.mshengu.app.util.predicates.sites;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/**
 *
 * @author boniface
 */
public class SiteUnitDeploymentPredicate implements Predicate<SiteUnit> {

    private String parentId;

    public SiteUnitDeploymentPredicate(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean apply(SiteUnit siteUnit) {
        if (parentId.equalsIgnoreCase(getSiteUnit(siteUnit))) {
            return true;
        }
        return false;
    }

    private String getSiteUnit(SiteUnit siteUnit) {
        if (siteUnit != null) {
            return getLatestDeployment(siteUnit.getLatestDeployment());
        }
        return null;

    }

    private String getLatestDeployment(UnitLocationLifeCycle unitLocationLifeCycle) {
        if (unitLocationLifeCycle != null) {
            return unitLocationLifeCycle.getSiteName();
        }
        return null;
    }
}

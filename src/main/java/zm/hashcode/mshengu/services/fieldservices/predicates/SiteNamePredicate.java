/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.predicates;

import com.google.common.base.Predicate;
import java.util.List;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 *
 * @author boniface
 */
public class SiteNamePredicate implements Predicate<SiteUnit> {

    private final List<Site> sites;

    public SiteNamePredicate(List<Site> sites) {
        this.sites = sites;
    }

    @Override
    public boolean apply(SiteUnit unit) {
        boolean siteFound = false;
        for (Site site : sites) {
            if (site.getName().equalsIgnoreCase(unit.getSiteName())) {
                System.out.println(" THE SITE IS  " + site.getName() + " UNIT HAS A SITE " + unit.getSiteName());
                siteFound = true;
            }
        }
        return siteFound;
    }
}

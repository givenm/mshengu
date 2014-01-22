/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices;

import java.util.List;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 *
 * @author boniface
 */
public interface ServiceSiteUnits {

    String serviceFirstUnit(UnitServiceResource resource);

    List<SiteUnit> getUnitsFromSite(String unitId);

    String serviceLastUnit(UnitServiceResource resource);

    Site getSiteForUnit(String unitID);

    SiteUnit getFirstUnit(String truckId, String siteId);

    List<SiteUnit> getSiteUnitsRange(SiteUnit first, SiteUnit finish);

    void serviceBulkUnits(List<SiteUnit> units, UnitServiceResource resource, String message);
}

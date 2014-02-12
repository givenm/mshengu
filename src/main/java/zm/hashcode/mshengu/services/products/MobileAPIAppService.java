/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products;

import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.client.rest.api.resources.UnitDeliveryResource;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Ferox
 */
@Service
public interface MobileAPIAppService {

    public String deployUnitToSite(UnitDeliveryResource unitDelivery);

    /**
     *
     * @param unitService
     * @param statusMessasge
     */
    public void createUnitServiceLog(UnitServiceResource unitService, String statusMessasge);

    /**
     *
     * @param unitService
     * @return
     */
    public String checkCoordinates(UnitServiceResource unitService);

    public String bulkTagUnitsToWharehouse(UnitType unitType, Sequence unitIdSequence, int quantity, Site site);
}

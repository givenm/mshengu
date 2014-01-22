/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices;

import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 *
 * @author boniface
 */
public interface ServiceUnit {

    void serviceunit(UnitServiceResource unitService, SiteUnit unit, String statusMessasge);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.services.fieldservices.ServiceUnit;

/**
 *
 * @author boniface
 */
public class UnitServcingTask extends RecursiveAction {

    private final ServiceUnit serviceUnit;
    private final List<SiteUnit> units;
    private final UnitServiceResource unitService;
    private final String message;

    public UnitServcingTask(List<SiteUnit> units, UnitServiceResource unitService, String message, ServiceUnit serviceUnit) {
        this.units = units;
        this.unitService = unitService;
        this.message = message;
        this.serviceUnit = serviceUnit;

    }

    @Override
    protected void compute() {
        for (SiteUnit siteUnit : units) {
            serviceUnit.serviceunit(unitService, siteUnit, message);
        }
    }
}

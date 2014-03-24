/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;
import zm.hashcode.mshengu.services.fieldservices.ServiceUnit;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.products.SiteUnitService;
import zm.hashcode.mshengu.services.products.UnitServiceLogService;

/**
 *
 * @author boniface
 */
@Service
public class ServiceUnitimpl implements ServiceUnit {

    @Autowired
    private SiteUnitService siteUnitService;
    @Autowired
    private UnitServiceLogService unitServiceLogService;
    @Autowired 
    private TruckService  truckService;

    @Override
    public void serviceunit(UnitServiceResource unitService, SiteUnit unit, String statusMessasge) {
        final Map<String, Boolean> task = unitService.getServices();
        final boolean pumpOut = task.get("pumpOut");
        final boolean washBucket = task.get("washBucket");
        final boolean suctionOut = task.get("suctionOut");
        final boolean scrubFloor = task.get("scrubFloor");
        final boolean rechargeBacket = task.get("rechargeBacket");
        final boolean cleanPerimeter = task.get("cleanPerimeter");
        final String incidents = unitService.getIncident().trim();

        UnitServiceLog unitServiceLog = new UnitServiceLog.Builder(new Date())
                .serviceTime(new Date())
                .pumpOut(pumpOut)
                .washBucket(washBucket)
                .suctionOut(suctionOut)
                .scrubFloor(scrubFloor)
                .rechargeBacket(rechargeBacket)
                .cleanPerimeter(cleanPerimeter)
                .statusMessage(statusMessasge)
                .incident(incidents)
                .siteName(unit.getSiteName())
                .servicedBy(getTruck(unitService.getTruckId()))
                .build();
        unitServiceLogService.persist(unitServiceLog);
        addServiceLogToSite(unit, unitServiceLog);
    }

    private void addServiceLogToSite(SiteUnit siteUnit, UnitServiceLog unitServiceLog) {
        List<UnitServiceLog> newUnitServiceLogs = new ArrayList<>();
        List<UnitServiceLog> unitServiceLogList = siteUnit.getUnityLogs();
        newUnitServiceLogs.addAll(unitServiceLogList);
        newUnitServiceLogs.add(unitServiceLog);
        SiteUnit updatedUniteSite = new SiteUnit.Builder(siteUnit.getUnitType())
                .siteUnit(siteUnit)
                .unityLogs(newUnitServiceLogs)
                .build();
        siteUnitService.merge(updatedUniteSite);
    }
    
    private Truck getTruck(String truckId){
        Truck truck = truckService.findById(truckId);
        return truck;        
    }
}

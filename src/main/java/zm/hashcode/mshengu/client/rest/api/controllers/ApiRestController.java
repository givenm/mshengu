/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.rest.api.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zm.hashcode.mshengu.client.rest.api.resources.SiteReource;
import zm.hashcode.mshengu.client.rest.api.resources.UnitDeliveryResource;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteServiceContractLifeCycleService;
import zm.hashcode.mshengu.services.products.SiteUnitService;
import zm.hashcode.mshengu.services.products.UnitLocationLifeCycleService;
import zm.hashcode.mshengu.services.products.UnitServiceLogService;
import zm.hashcode.mshengu.services.ui.util.RoleService;

/**
 *
 * @author boniface
 */
@Controller
@RequestMapping("apideprecated")
public class ApiRestController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private SiteUnitService siteUnitService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private SiteServiceContractLifeCycleService siteServiceContractLifeCycleService;
    @Autowired
    private UnitLocationLifeCycleService unitLocationLifeCycleService;
    @Autowired
    private UnitServiceLogService unitServiceLogService;
//SiteServiceContractLifeCycle

    @RequestMapping("sites/{size}")
    @ResponseBody
    public List<SiteReource> getSites(@PathVariable String size) {

        int siteSize = Integer.parseInt(size);

        List<SiteReource> sitesReources = new ArrayList<>();
        List<Site> sites = siteService.findAll();
        if (sites.size() != siteSize) {
            for (Site site : sites) {
                SiteReource sr = new SiteReource();
                sr.setId(site.getId());
                sr.setName(site.getName());
                sitesReources.add(sr);
            }
        }
        return sitesReources;
    }

    @RequestMapping(value = "tagunit", method = RequestMethod.POST)
    @ResponseBody
    public String tagUnitLocation(@Valid @RequestBody UnitDeliveryResource unitDelivery) {
        final String unitId = unitDelivery.getUnitId();
        final String siteName = unitDelivery.getSiteId();
        final String latitude = unitDelivery.getLatitude();
        final String longitude = unitDelivery.getLongitude();


        UnitLocationLifeCycle unitLifeCycle = persistUnitLocationLifeCycle(latitude, longitude);

        SiteUnit updatedSiteUnit = updatedSiteUnit(unitId, unitLifeCycle);

        SiteServiceContractLifeCycle newSiteServiceContractLifeCycle = updateSiteServiceContractLifeCycle(siteName, updatedSiteUnit);

        updateSite(siteName, newSiteServiceContractLifeCycle);


        return "ok";
    }

    @RequestMapping(value = "serviceunit", method = RequestMethod.POST)
    @ResponseBody
    public String submitService(@Valid @RequestBody UnitServiceResource unitService) {
        String isValid = checkCoordinates(unitService);
        createUnitServiceLog(unitService, isValid);
        unitService.setStatusMessage(isValid);
        return unitService.getStatusMessage();
    }

    /**
     *
     * @param unitService
     * @param statusMessasge
     */
    private void createUnitServiceLog(UnitServiceResource unitService, String statusMessasge) {
        final Map<String, Boolean> task = unitService.getServices();
        final String unitId = unitService.getUnitId();
        final boolean pumpOut = task.get("pumpOut");
        final boolean washBucket = task.get("washBucket");
        final boolean suctionOut = task.get("suctionOut");
        final boolean scrubFloor = task.get("scrubFloor");
        final boolean rechargeBacket = task.get("rechargeBacket");
        final boolean cleanPerimeter = task.get("cleanPerimeter");
        final String incidents = unitService.getIncident();



        UnitServiceLog unitServiceLog = null;

        if ("WITHIN".equals(checkCoordinates(unitService))) {
            unitServiceLog = new UnitServiceLog.Builder(new Date())
                    .serviceTime(new Date())
                    .pumpOut(pumpOut)
                    .washBucket(washBucket)
                    .suctionOut(suctionOut)
                    .scrubFloor(scrubFloor)
                    .rechargeBacket(rechargeBacket)
                    .cleanPerimeter(cleanPerimeter)
                    .statusMessage(statusMessasge)
                    .incident(incidents)
                    .build();
            unitServiceLogService.persist(unitServiceLog);
        } else {
            unitServiceLog = new UnitServiceLog.Builder(new Date())
                    .serviceTime(new Date())
                    .statusMessage("User Away From UNIT")
                    .build();
            unitServiceLogService.persist(unitServiceLog);

        }

        SiteUnit siteUnit = siteUnitService.findByUnitId(unitId);
        List<UnitServiceLog> newUnitServiceLogs = new ArrayList<>();


        List<UnitServiceLog> unitServiceLogList = siteUnit.getUnityLogs();
        newUnitServiceLogs.addAll(unitServiceLogList);
        newUnitServiceLogs.add(unitServiceLog);

        SiteUnit updatedUniteSite = new SiteUnit.Builder(siteUnit.getUnitType())
                .siteUnit(siteUnit)
                .unityLogs(newUnitServiceLogs)
                .build();

        //SAVE UNIT
        siteUnitService.merge(updatedUniteSite);

    }

    /**
     *
     * @param unitService
     * @return
     */
    private String checkCoordinates(UnitServiceResource unitService) {

        return siteUnitService.checkCoordinates(unitService);
    }

    /**
     * Save a new SiteUnitLocationLifeCycle
     *
     * @param latitude
     * @param longitude
     * @return siteUnitLocationLifeCycle
     */
    private UnitLocationLifeCycle persistUnitLocationLifeCycle(String latitude, String longitude) {

        UnitLocationLifeCycle unitLifeCycle = new UnitLocationLifeCycle.Builder(new Date())
                .latitude(latitude)
                .longitude(longitude)
                .build();

        unitLocationLifeCycleService.persist(unitLifeCycle);
        return unitLifeCycle;
    }

    /**
     * Adds the unitLocationLifeCycle to the siteUnit and updates it
     *
     * @param unitId
     * @param unitLocationLifeCycle
     * @return updatedSiteUnit
     */
    private SiteUnit updatedSiteUnit(String unitId, UnitLocationLifeCycle unitLocationLifeCycle) {
        SiteUnit siteUnit = siteUnitService.findByUnitId(unitId);

        List<UnitLocationLifeCycle> unitLocationLifeCycleList = siteUnit.getUnitLocationLifeCycle();
        unitLocationLifeCycleList.add(unitLocationLifeCycle);
        SiteUnit updatedSiteUnit = new SiteUnit.Builder(siteUnit.getUnitType())
                .siteUnit(siteUnit)
                //                .deployed(true)
                .unitLocationLifeCycle(unitLocationLifeCycleList)
                .build();
        //SAVE UNIT
        siteUnitService.merge(updatedSiteUnit);
        return updatedSiteUnit;
    }

    /**
     * 1st Finds the current SiteServiceContractLifeCycle for a specific Site
     * 2nd creates a new SiteServiceContractLifeCycle based on the latest one
     * 3rd adds the new site and updates the number of units
     *
     * @param siteName
     * @param updatedSiteUnit
     * @return newSiteServiceContractLifeCycle
     */
    private SiteServiceContractLifeCycle updateSiteServiceContractLifeCycle(String siteName, SiteUnit updatedSiteUnit) {
        Site site = siteService.findByName(siteName);


        SiteServiceContractLifeCycle siteServiceContractLifeCycle = siteService.getSitetCurrentContract(site.getId());

        Set<SiteUnit> siteUnits = new HashSet<>();
        Set<SiteUnit> currentUnitsAtThisSite = siteServiceContractLifeCycle.getSiteUnit();

        siteUnits.add(updatedSiteUnit);
        siteUnits.addAll(currentUnitsAtThisSite);

        int numberOfunits = siteUnits.size();

        SiteServiceContractLifeCycle newSiteServiceContractLifeCycle = new SiteServiceContractLifeCycle.Builder(new Date())
                .siteServiceContractLifeCycle(siteServiceContractLifeCycle)
                .numberOfUnits(numberOfunits)
                .siteUnit(siteUnits)
                .build();

        siteServiceContractLifeCycleService.persist(newSiteServiceContractLifeCycle);

        return newSiteServiceContractLifeCycle;
    }

    private void updateSite(String siteName, SiteServiceContractLifeCycle newSiteServiceContractLifeCycle) {
        Site site = siteService.findByName(siteName);

        Set<SiteServiceContractLifeCycle> siteServiceContractLifeCycleList = new HashSet<>();
        Set<SiteServiceContractLifeCycle> currentSiteServiceContractLifeCycle = site.getSiteServiceContractLifeCycle();

        siteServiceContractLifeCycleList.add(newSiteServiceContractLifeCycle);
        siteServiceContractLifeCycleList.addAll(currentSiteServiceContractLifeCycle);


        Site newSite = new Site.Builder(site.getName())
                .site(site)
                .siteServiceContractLifeCycle(siteServiceContractLifeCycleList)
                .build();

        siteService.merge(newSite);
    }
}

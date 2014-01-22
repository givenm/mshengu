/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Ferox
 */
public class APITEST extends AppTest{
    /*private SiteRepository siteRepository;
    private SiteUnitRepository siteUnitRepository;
    private UnitLocationLifeCycleRepository  unitLocationLifeCycleRepository;
    private UnitServiceLogRepository unitServiceLogRepository;
    private String id;
    
//    @Test
    public UnitDeliveryResource tagUnitLocation() {
        UnitDeliveryResource unitDelivery = new UnitDeliveryResource();
        unitDelivery.setLatitude("10");
        unitDelivery.setLongitude("12");        
        unitDelivery.setSiteId("Nach");
        unitDelivery.setUnitId("unit123");
        saveLifeCycle(unitDelivery);
        return unitDelivery;
    }
    
    
    public UnitServiceResource submitService(@Valid @RequestBody UnitServiceResource unitService) {
        String isValid = checkCoordinates(unitService);
        createUnitServiceLog(unitService, isValid);
        unitService.setStatusMessage(isValid);
        return unitService;
    }
    
    private void saveLifeCycle(UnitDeliveryResource unitDelivery) {
        siteRepository = ctx.getBean(SiteRepository.class);
        siteUnitRepository = ctx.getBean(SiteUnitRepository.class);
        unitLocationLifeCycleRepository =ctx.getBean(UnitLocationLifeCycleRepository.class);
        final String unitId = unitDelivery.getUnitId();
        final String siteName = unitDelivery.getSiteId();
        final String latitude = unitDelivery.getLatitude();
        final String longitude = unitDelivery.getLongitude();
        
        UnitLocationLifeCycle unitLifeCycle = new UnitLocationLifeCycle.Builder(new Date())
                .latitude(latitude)
                .longitude(longitude)
                .build();


        //SAVE UNIT LOCATION LIFE CYCLE
        unitLocationLifeCycleRepository.save(unitLifeCycle);
//        UnitLocationLifeCycleFacade.getUnitLocationLifeCycleService().persist(unitLifeCycle);

    
        SiteUnit siteUnit = siteUnitRepository.findUnitId(unitId);
//        SiteUnit siteUnit = SiteUnitFacade.getSiteUnitService().findById(unitId);
        
        
        List<UnitLocationLifeCycle> unitLocationLifeCycleList = siteUnit.getUnitLocationLifeCycle();
        unitLocationLifeCycleList.add(unitLifeCycle);
        
        SiteUnit siteUnite = new SiteUnit.Builder(siteUnit.getUnitType())
                .siteUnit(siteUnit)
                .unitLocationLifeCycle(unitLocationLifeCycleList)
                .build();


        //SAVE UNIT 
        siteUnitRepository.save(siteUnite);
//        SiteUnitFacade.getSiteUnitService().merge(siteUnite);


      
         Site site = siteRepository.findByName(siteName);
//        Site site = SiteFacade.getSiteService().findByName(siteName);
        
        Set<SiteUnit> siteList = site.getSiteUnit();
        siteList.add(siteUnit);
        
        SiteFacade.getSiteService().merge(site);


        //SITE 
        Site newSite = new Site.Builder(site.getName())
                .site(site)
                .siteUnit(siteList)
                .build();
        
        siteRepository.save(newSite);
//        SiteFacade.getSiteService().merge(newSite);
        
    }
    
    private void createUnitServiceLog(UnitServiceResource unitService, String statusMessasge) {
        
        siteRepository = ctx.getBean(SiteRepository.class);
        siteUnitRepository = ctx.getBean(SiteUnitRepository.class);
        unitLocationLifeCycleRepository =ctx.getBean(UnitLocationLifeCycleRepository.class);
        unitServiceLogRepository =ctx.getBean(UnitServiceLogRepository.class);
        
        final Map<String, Boolean> task = unitService.getServices();
        final String unitId = "unit123";
        final boolean pumpOut = true;
        final boolean washBucket = true;
        final boolean suctionOut = true;
        final boolean scrubFloor = false;
        final boolean rechargeBacket = true;
        final boolean cleanPerimeter = false;
        final String incidents = "No incidents";
        final String  latitude= "10";
        final String  longitude ="12";  
        
        
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
                .build();
        unitServiceLogRepository.save(unitServiceLog);
//        UnitServiceLogFacade.getUnityServiceLogService().persist(unitServiceLog);
        
        SiteUnit siteUnit = siteUnitRepository.findOne(unitId);
//        SiteUnit siteUnit = SiteUnitFacade.getSiteUnitService().findById(unitId);
        List<UnitServiceLog> unitServiceLogList = siteUnit.getUnityLogs();
        unitServiceLogList.add(unitServiceLog);
        
        SiteUnit siteUnite = new SiteUnit.Builder(siteUnit.getUnitType())
                .siteUnit(siteUnit)
                .unityLogs(unitServiceLogList)
                .build();



        //SAVE UNIT 
        siteUnitRepository.save(siteUnite);
//        SiteUnitFacade.getSiteUnitService().merge(siteUnite);
        
    }
    
    private String checkCoordinates(UnitServiceResource unitService) {

        //SiteUnit siteUnit = SiteUnitFacade.getSiteUnitService().findById(unitId);
//        UnitLocationLifeCycle unitLocationLifeCycle = SiteUnitFacade.getSiteUnitService().getUnitCurrentLocation(unitId);
//        siteUnit.getUnitLocationLifeCycle().
        return null;
    }
    
    
    /*
     *   public void attachUnit(){
          Location location = LocationFacade.getLocationService().findById(siteBean.getLocationId());

        if (siteBean.getId() != null) {

            existingSite = SiteFacade.getSiteService().findById(siteBean.getId());
            siteContractList = existingSite.getSiteServiceContract();
            siteUnitList = existingSite.getSiteUnit();
        }

        if (siteBean.getParentId() == null) {
            setParentId(getCustomerId());
        } else {
            setParentId(siteBean.getParentId());
        }

        final Site site = new Site.Builder(siteBean.getName())
                .streetAddress(siteBean.getAddressDescription())
                .postalCode(siteBean.getPostalCode())
                .location(location)
                .status(siteBean.getStatus())
                .siteServiceContract(siteContractList)
                .siteUnit(siteUnitList)
                .parentId(getParentId())
                .id(siteBean.getId())
                .build();


        return site;
    }
     */
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.products;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.repository.products.SiteRepository;
import zm.hashcode.mshengu.repository.products.SiteServiceContractLifeCycleRepository;
import zm.hashcode.mshengu.repository.products.SiteUnitRepository;
import zm.hashcode.mshengu.repository.products.UnitLocationLifeCycleRepository;
import zm.hashcode.mshengu.repository.products.UnitServiceLogRepository;
import zm.hashcode.mshengu.repository.products.UnitTypeRepository;
import zm.hashcode.mshengu.repository.ui.util.StatusRepository;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Ferox
 */
public class SiteChildObjectTest extends AppTest {

    private SiteRepository siteRepository;
    private SiteUnitRepository siteUnitRepository;
    private UnitLocationLifeCycleRepository unitLocationLifeCycleRepository;
    private UnitServiceLogRepository unitServiceLogRepository;
    private UnitTypeRepository unitTypeRepository;
    private SiteServiceContractLifeCycleRepository siteServiceContractLifeCycleRepository;
    private StatusRepository statusRepository;
    private String id;

//    @Test
    public void saveSiteWithChildObjects() {

        siteRepository = ctx.getBean(SiteRepository.class);
        siteUnitRepository = ctx.getBean(SiteUnitRepository.class);
        unitLocationLifeCycleRepository = ctx.getBean(UnitLocationLifeCycleRepository.class);
        unitServiceLogRepository = ctx.getBean(UnitServiceLogRepository.class);
        unitTypeRepository = ctx.getBean(UnitTypeRepository.class);
        siteServiceContractLifeCycleRepository = ctx.getBean(SiteServiceContractLifeCycleRepository.class);


        final boolean pumpOut = true;
        final boolean washBucket = true;
        final boolean suctionOut = true;
        final boolean scrubFloor = false;
        final boolean rechargeBacket = true;
        final boolean cleanPerimeter = false;
        final String incidents = "No incidents";
        final String latitude = "11";
        final String longitude = "21";
        final String latitude2 = "12";
        final String longitude2 = "22";

        UnitServiceLog unitServiceLog = new UnitServiceLog.Builder(new Date())
                .serviceTime(new Date())
                .pumpOut(pumpOut)
                .washBucket(washBucket)
                .suctionOut(suctionOut)
                .scrubFloor(scrubFloor)
                .rechargeBacket(rechargeBacket)
                .cleanPerimeter(cleanPerimeter)
                .statusMessage("within perimeter")
                .incident(incidents)
                .build();

        UnitServiceLog unitServiceLog2 = new UnitServiceLog.Builder(new Date())
                .serviceTime(new Date())
                .pumpOut(pumpOut)
                .washBucket(!washBucket)
                .suctionOut(!suctionOut)
                .scrubFloor(scrubFloor)
                .rechargeBacket(!rechargeBacket)
                .cleanPerimeter(cleanPerimeter)
                .statusMessage("ouside perimeter")
                .incident(incidents)
                .build();

        unitServiceLogRepository.save(unitServiceLog);
        unitServiceLogRepository.save(unitServiceLog2);

        List<UnitServiceLog> unitServiceLogList = new ArrayList<>();
        unitServiceLogList.add(unitServiceLog);
        unitServiceLogList.add(unitServiceLog2);

        UnitLocationLifeCycle unitLifeCycle = new UnitLocationLifeCycle.Builder(new Date())
                .latitude(latitude)
                .longitude(longitude)
                .build();

        UnitLocationLifeCycle unitLifeCycle2 = new UnitLocationLifeCycle.Builder(new Date())
                .latitude(latitude2)
                .longitude(longitude2)
                .build();

        UnitLocationLifeCycle unitLifeCycle3 = new UnitLocationLifeCycle.Builder(new Date())
                .latitude(latitude)
                .longitude(longitude2)
                .build();

        UnitLocationLifeCycle unitLifeCycle4 = new UnitLocationLifeCycle.Builder(new Date())
                .latitude(latitude2)
                .longitude(longitude)
                .build();

        unitLocationLifeCycleRepository.save(unitLifeCycle);
        unitLocationLifeCycleRepository.save(unitLifeCycle2);
        unitLocationLifeCycleRepository.save(unitLifeCycle3);
        unitLocationLifeCycleRepository.save(unitLifeCycle4);


        List<UnitLocationLifeCycle> unitLocationLifeCycleList = new ArrayList<>();
        unitLocationLifeCycleList.add(unitLifeCycle);
        unitLocationLifeCycleList.add(unitLifeCycle2);
        unitLocationLifeCycleList.add(unitLifeCycle3);
        unitLocationLifeCycleList.add(unitLifeCycle4);

        Site site = siteRepository.findByName("Howard Center");
        List<SiteServiceContractLifeCycle> contractLifeCycleList = new ArrayList(site.getSiteServiceContractLifeCycle());
        Set<SiteServiceContractLifeCycle> siteServiceContractLifeCycleList = new HashSet<>();
        int index = contractLifeCycleList.size() - 1;

        Set<SiteUnit> siteUnitList = new HashSet<>();
        for (int a = 10; a <= 15; a++) {

            SiteServiceContractLifeCycle contractLifeCycle = contractLifeCycleList.get(index);

            siteServiceContractLifeCycleList.addAll(site.getSiteServiceContractLifeCycle());

            final UnitType unitType = unitTypeRepository.findOne("5223b5cfb28f22358991dd66");
            final Status status =  statusRepository.findByName("Deployed");

            
            for (int i = 1; i <= 7; i++) {
                SiteUnit siteUnite = new SiteUnit.Builder(unitType)
                        .unitId("Howard " + (a + i))
                        .description("Howard Center Unit No." + (a + i))
                        .operationalStatus(status)
                        .unitLocationLifeCycle(unitLocationLifeCycleList)
                        .unityLogs(unitServiceLogList)
                        .build();

                siteUnitRepository.save(siteUnite);
                siteUnitList.add(siteUnite);
            }

            SiteServiceContractLifeCycle newSericeContractLifeCycle = new SiteServiceContractLifeCycle.Builder(new Date())
                    .siteServiceContractLifeCycle(contractLifeCycle)
                    .siteUnit(siteUnitList)
                    .build();

            siteServiceContractLifeCycleRepository.save(newSericeContractLifeCycle);
            siteServiceContractLifeCycleList.add(newSericeContractLifeCycle);

            Site newSite = new Site.Builder(site.getName())
                    .site(site)
                    .siteServiceContractLifeCycle(siteServiceContractLifeCycleList)
                    .build();

            siteRepository.save(newSite);

        }

    }
}

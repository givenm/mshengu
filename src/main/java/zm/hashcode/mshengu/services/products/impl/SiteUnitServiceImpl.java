/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products.impl;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.repository.products.SiteUnitRepository;
import zm.hashcode.mshengu.repository.products.UnitLocationLifeCycleRepository;
import zm.hashcode.mshengu.services.products.SiteUnitService;

/**
 *
 * @author Luckbliss
 */
@Service
public class SiteUnitServiceImpl implements SiteUnitService {

    @Autowired
    private SiteUnitRepository repository;
    @Autowired
    private UnitLocationLifeCycleRepository cycleRepository;

    @Override
    @Cacheable("siteUnits")
    public List<SiteUnit> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByUnitID()));
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "siteUnits", allEntries = true)
    })
    public void persist(SiteUnit siteUnit) {

        repository.save(siteUnit);

    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "siteUnits", allEntries = true)
    })
    public void merge(SiteUnit siteUnit) {
        if (siteUnit.getId() != null) {
            repository.save(siteUnit);
        }
    }

    @Override
    public SiteUnit findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "siteUnits", allEntries = true)
    })
    public void delete(SiteUnit siteUnit) {
        repository.delete(siteUnit);
    }

    private Sort sortByDeployment() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "dateofAction"));
    }

    private Sort sortByUnitID() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "unitId"));
    }

    @Override
    public UnitLocationLifeCycle getUnitCurrentLocation(String id) {
        SiteUnit siteUnit = findById(id);
        List<UnitLocationLifeCycle> unitLocationLifeCycles = siteUnit.getUnitLocationLifeCycle();

        return getLatestLifeCycle(unitLocationLifeCycles);
    }

    private UnitLocationLifeCycle getLatestLifeCycle(List<UnitLocationLifeCycle> unitLocationLifeCycles) {

        Ordering<UnitLocationLifeCycle> ordering;
        ordering = Ordering.natural().nullsLast().onResultOf(new Function<UnitLocationLifeCycle, Long>() {
            @Override
            public Long apply(UnitLocationLifeCycle unitLocationLifeCycle) {
                return unitLocationLifeCycle.getDateofAction().getTime();
            }
        });

        List<UnitLocationLifeCycle> sortedList = ordering.immutableSortedCopy(unitLocationLifeCycles).reverse();
        if (sortedList.isEmpty()) {
            UnitLocationLifeCycle unitLocationLifeCycleNull = null;
            return unitLocationLifeCycleNull;
        } else {
            return sortedList.get(0);
        }
    }

    @Override
    public String checkCoordinates(UnitServiceResource unitService) {
        String message;

        try {
            SiteUnit siteUnit = repository.findByUnitId(unitService.getUnitId().trim());
            UnitLocationLifeCycle cycle = getUnitCurrentLocation(siteUnit.getId().trim());

            Point point1 = new Point(Double.parseDouble(unitService.getLatitude()), Double.parseDouble(unitService.getLongitude()));
            Point point2 = new Point(Double.parseDouble(cycle.getLatitude()), Double.parseDouble(cycle.getLongitude()));

            Distance distance = new Distance(0.2, Metrics.KILOMETERS);
            Distance dist = new Distance(distance(point1, point2) / 10, Metrics.KILOMETERS);

            if (distance.getValue() > dist.getValue()) {
                message = "WITHIN";
            } else {
                message = "OUT";
            }
        } catch (Exception exception) {
            message = "EXCEPTION";
        }


        return message;
    }

    private double distance(Point p, Point point) {
        double lat1 = p.getX();
        double lng1 = p.getY();
        double lat2 = point.getX();
        double lng2 = point.getY();
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    @Override
    public SiteUnit findByUnitId(String unitId) {
        return repository.findByUnitId(unitId);
    }
}

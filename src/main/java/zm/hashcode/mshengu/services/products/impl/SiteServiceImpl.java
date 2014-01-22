/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products.impl;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Ordering;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceLogClosedPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceLogNeedsReviewPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceLogOpenPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceScheduleFridayPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceScheduleMondayPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceScheduleSaturdayPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceScheduleSundayPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceScheduleThursdayPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceScheduleTuesdayPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteServiceScheduleWednesdayPredicate;
import zm.hashcode.mshengu.app.util.predicates.sites.SiteUnitDeploymentPredicate;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.repository.products.SiteRepository;
import zm.hashcode.mshengu.repository.products.SiteUnitRepository;
import zm.hashcode.mshengu.services.products.SiteService;

/**
 *
 * @author Luckbliss
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository repository;
    @Autowired
    private SiteUnitRepository siteUnitrepository;

    @Override
    @Cacheable("sites")
    public List<Site> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "sites", allEntries = true)
    })
    public void persist(Site site) {

        repository.save(site);

    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "sites", allEntries = true)
    })
    public void merge(Site site) {
        if (site.getId() != null) {
            repository.save(site);
        }
    }

    @Override
    public Site findById(String id) {
        return repository.findOne(id);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "sites", allEntries = true)
    })
    public void delete(Site site) {
        repository.delete(site);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public Site findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Site> findByName(String name, String blankString) {
        return repository.findByName(name, "name");
    }

    @Override
    public SiteServiceContractLifeCycle getSitetCurrentContract(String id) {
        Site site = repository.findOne(id);
        Set<SiteServiceContractLifeCycle> siteServiceContractLifeCycles = site.getSiteServiceContractLifeCycle();

        return getLatestLifeCycle(siteServiceContractLifeCycles);
    }

    private SiteServiceContractLifeCycle getLatestLifeCycle(Set<SiteServiceContractLifeCycle> siteServiceContractLifeCycles) {

        Ordering<SiteServiceContractLifeCycle> ordering;
        ordering = Ordering.natural().nullsLast().onResultOf(new Function<SiteServiceContractLifeCycle, Long>() {
            @Override
            public Long apply(SiteServiceContractLifeCycle siteServiceContractLifeCycle) {
                return siteServiceContractLifeCycle.getDateofAction().getTime();
            }
        });

        List<SiteServiceContractLifeCycle> sortedList = ordering.immutableSortedCopy(siteServiceContractLifeCycles).reverse();
        if (sortedList.isEmpty()) {
            SiteServiceContractLifeCycle siteServiceContractLifeCycleNull = null;
            return siteServiceContractLifeCycleNull;
        } else {
            return sortedList.get(0);
        }
    }

    @Override
    public String geoPlotSite(Site site, String latitude, String longitude) {
        String response = "OK";
        Location location = site.getLocation();
        Location updateLocation = new Location.Builder(site.getName())
                .location(location)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        Site updatedSite = new Site.Builder(site.getName())
                .site(site)
                .location(updateLocation)
                .build();
        merge(updatedSite);
        return response;

    }

    @Override
    public List<SiteUnit> findAllSiteUnit(String siteName) {
        List<SiteUnit> siteUnitList = (List<SiteUnit>) siteUnitrepository.findAll();
        Collection<SiteUnit> siteUnitsFilteredList = Collections2.filter(siteUnitList, new SiteUnitDeploymentPredicate(siteName));
        return ImmutableList.copyOf(siteUnitsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithVisitOnMonday() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceScheduleMondayPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithVisitOnTuesday() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceScheduleTuesdayPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithVisitOnWednesday() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceScheduleWednesdayPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    ; 
    @Override
    public List<Site> findAllWithVisitOnThursday() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceScheduleThursdayPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithVisitOnFriday() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceScheduleFridayPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithVisitOnSaturday() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceScheduleSaturdayPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithVisitOnSunday() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceScheduleSundayPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithLastLogOpen() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceLogOpenPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithLastLogClosed() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceLogClosedPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithLastLogNeedsReview() {
        List<Site> siteList = (List<Site>) repository.findAll();
        Collection<Site> sitesFilteredList = Collections2.filter(siteList, new SiteServiceLogNeedsReviewPredicate());
        return ImmutableList.copyOf(sitesFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Site> findAllWithVisitToday(Date date) {

        Calendar calendar = Calendar.getInstance();
//        Date date = new Date();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        List<Site> siteList;
        switch (dayOfWeek) {
            case 1:
                siteList = findAllWithVisitOnSunday();
                break;
            case 2:
                siteList = findAllWithVisitOnMonday();
                break;
            case 3:
                siteList = findAllWithVisitOnTuesday();
                break;
            case 4:
                siteList = findAllWithVisitOnWednesday();
                break;
            case 5:
                siteList = findAllWithVisitOnThursday();
                break;
            case 6:
                siteList = findAllWithVisitOnFriday();
                break;
            case 7:
                siteList = findAllWithVisitOnSaturday();
                break;
            default:
                siteList = findAll();
                break;

        }
        System.out.println("Calendar.DAY_OF_WEEK " + Calendar.DAY_OF_WEEK);
        System.out.println("Calendar.DATE " + calendar.getTime());
        return siteList;
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet.impl;

import com.google.common.collect.Collections2;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.truck.ServiceTrucksPredicate;
import zm.hashcode.mshengu.app.util.predicates.truck.SiteNameTruckPredicate;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.repository.fleet.TruckRepository;
import zm.hashcode.mshengu.services.fleet.TruckService;

/**
 *
 * @author Luckbliss
 */
@Service
public class TruckServiceImpl implements TruckService {

    @Autowired
    private TruckRepository repository;

    @Override
    public List<Truck> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(Truck truck) {

        repository.save(truck);

    }

    @Override
    public void merge(Truck truck) {
        if (truck.getId() != null) {
            repository.save(truck);
        }
    }

    @Override
    public Truck findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Truck truck) {
        repository.delete(truck);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "vehicleNumber"));
    }

    @Override
    public Truck findByVehicleNumber(String vehicleNumber) {
        return repository.findByVehicleNumber(vehicleNumber);

//        List<Truck> allTrucks = findAll();
//        for (Truck truck : allTrucks) {
//            if (truck.getVehicleNumber().toLowerCase().equals(vehicleNumber.toLowerCase())) {
//                return truck;
//            }
//        }
//        return null;
    }

    @Override
    public List<Truck> findAllServiceAndUtilityVehicles() {
        List<Truck> truckList = findAll();
        Collection<Truck> trucks = Collections2.filter(truckList, new ServiceTrucksPredicate());
//        Collections.sort(trucks);
        return ImmutableList.copyOf(trucks);
    }

    @Override
    public Truck findBySiteName(String siteName) {
        List<Truck> truckList = (List<Truck>) repository.findAll();
        Collection<Truck> truckFilteredList = Collections2.filter(truckList, new SiteNameTruckPredicate(siteName));
        Iterator<Truck> truckIterator = truckFilteredList.iterator();
        return truckIterator.hasNext() ? truckIterator.next() : null;
//        return Iterator truckFilteredList.iterator().next();
//        return ImmutableList.copyOf();

    }
}

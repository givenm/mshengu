/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.fleet.Impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.repository.fleet.TruckRepositoryCustom;

/**
 *
 * @author Colin
 */
public class TruckRepositoryImpl implements TruckRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public Truck findByVehicleNumber(String vehicleNumber) {

        Query truckQuery = new Query();
        truckQuery.addCriteria(
                Criteria.where("vehicleNumber").is(vehicleNumber));

        return mongoOperation.findOne(truckQuery, Truck.class);

    }
}

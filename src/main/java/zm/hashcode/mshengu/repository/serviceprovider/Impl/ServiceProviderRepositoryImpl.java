/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.serviceprovider.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.repository.serviceprovider.ServiceProviderRepositoryCustom;

/**
 *
 * @author Colin
 */
public class ServiceProviderRepositoryImpl implements ServiceProviderRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public ServiceProvider findByVendorNumber(String vendorNumber) { // vendorNumber
        // Example 1
//        Query serviceProviderQuery = new Query();
//        serviceProviderQuery.addCriteria(
//                Criteria.where("vendorNumber").is(vendorNumber) /*
//                 * .andOperator(Criteria.where("transactionDate").gte(from),
//                 Criteria.where("transactionDate").lte(to))
//                 */);

        // Example 2
//        Query serviceProviderQuery = new Query(Criteria.where("vendorNumber").is(vendorNumber)); // Same as Above

        Query serviceProviderQuery = new Query();
        serviceProviderQuery.addCriteria(
                Criteria.where("vendorNumber").is(vendorNumber));

        return mongoOperation.findOne(serviceProviderQuery, ServiceProvider.class);
    }
}

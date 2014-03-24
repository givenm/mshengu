/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.conf;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author boniface
 */
@Configuration
@EnableCaching
@ComponentScan("zm.hashcode.mshengu.services")
public class CacheConfig {

    @Bean
    public SimpleCacheManager cacheManager() {

        SimpleCacheManager cacheManager = new SimpleCacheManager();  
        List<Cache> caches = new ArrayList<>();
        caches.add(defaultBean().getObject());
        caches.add(persons().getObject());
        caches.add(staff().getObject());
        caches.add(siteUnits().getObject());
        caches.add(sites().getObject());
        caches.add(drivers().getObject());
        caches.add(requestors().getObject());
        caches.add(unitLocationLifeCycle().getObject());

        //request objects
        caches.add(requests().getObject());
        caches.add(pendingRequests().getObject());
        caches.add(disApprovedRequests().getObject());
        caches.add(approvedRequests().getObject());
        caches.add(approvedRequestsBySupplier().getObject());
        caches.add(misMatchStatus().getObject());
        caches.add(processedRequestsWithInvoiceNumber().getObject());
        caches.add(serviceProviderProcessedRequestsWithInvoiceNumber().getObject());
        caches.add(processedRequestsWithPaymentDate().getObject());
        caches.add(serviceProviderProcessedRequestsWithPaymentDate().getObject());
        caches.add(processedRequestsByCostCentreType().getObject());
        
        //request for quote objects
        caches.add(requestForQuotes().getObject());

        cacheManager.setCaches(caches);

        return cacheManager;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean defaultBean() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("default");
        return cacheFactoryBean;

    }

    //person beans
    @Bean
    public ConcurrentMapCacheFactoryBean persons() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("persons");
        return cacheFactoryBean;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean staff() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("staff");
        return cacheFactoryBean;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean drivers() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("drivers");
        return cacheFactoryBean;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean requestors() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("requestors");
        return cacheFactoryBean;
    }

    //sites beans
    @Bean
    public ConcurrentMapCacheFactoryBean sites() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("sites");
        return cacheFactoryBean;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean siteUnits() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("siteUnits");
        return cacheFactoryBean;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean unitLocationLifeCycle() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("unitLocationLifeCycle");
        return cacheFactoryBean;
    }

    //requests beans
    @Bean
    public ConcurrentMapCacheFactoryBean requests() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("requests");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean pendingRequests() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("pendingRequests");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean disApprovedRequests() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("disApprovedRequests");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean approvedRequests() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("approvedRequests");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean approvedRequestsBySupplier() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("approvedRequestsBySupplier");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean misMatchStatus() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("misMatchStatus");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean processedRequestsWithInvoiceNumber() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("processedRequestsWithInvoiceNumber");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean serviceProviderProcessedRequestsWithInvoiceNumber() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("serviceProviderProcessedRequestsWithInvoiceNumber");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean processedRequestsWithPaymentDate() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("processedRequestsWithPaymentDate");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean serviceProviderProcessedRequestsWithPaymentDate() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("serviceProviderProcessedRequestsWithPaymentDate");
        return cacheFactoryBean;
    }
    @Bean
    public ConcurrentMapCacheFactoryBean processedRequestsByCostCentreType() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("processedRequestsByCostCentreType");
        return cacheFactoryBean;
    }

    //request for quote
    @Bean
    public ConcurrentMapCacheFactoryBean requestForQuotes() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("requestForQuotes");
        return cacheFactoryBean;
    }
}

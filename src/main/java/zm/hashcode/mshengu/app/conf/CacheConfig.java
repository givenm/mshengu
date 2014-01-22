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
        cacheManager.setCaches(caches);

        return cacheManager;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean defaultBean() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("default");
        return cacheFactoryBean;

    }

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
}

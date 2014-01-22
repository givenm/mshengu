/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.fleet;

import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.repository.serviceprovider.ServiceProviderRepository;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class AddServiceProviders extends AppTest {

    private ServiceProviderRepository providerRepository;

//    @Test
    public void testCreate() {
        providerRepository = ctx.getBean(ServiceProviderRepository.class);
        ServiceProvider provider1 = new ServiceProvider.Builder("serviceprovider1").build();
        providerRepository.save(provider1);
        ServiceProvider provider2 = new ServiceProvider.Builder("serviceprovider2").build();
        providerRepository.save(provider2);
        ServiceProvider provider3 = new ServiceProvider.Builder("ServiceProvider3").build();
        providerRepository.save(provider3);
        ServiceProvider provider4 = new ServiceProvider.Builder("ServiceProvider4").build();
        providerRepository.save(provider4);
        Assert.assertEquals(provider1.getName(), "serviceprovider1");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.phone.sites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.test.phone.models.SiteReource;

/**
 *
 * @author hashcode
 */
public class GetSitesTest {

    private final String URL = "http://localhost:8084/mshengu/";

    public GetSitesTest() {

    }

    @Test(enabled = false)
    private void TestReadSites() {
        final String url = URL + "sites" + "/" + 1;
        List<SiteReource> sites = new ArrayList<>();
        
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Add the Gson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<SiteReource[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, SiteReource[].class);
        SiteReource[] events = responseEntity.getBody();

        for (SiteReource event : events) {
            sites.add(event);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}

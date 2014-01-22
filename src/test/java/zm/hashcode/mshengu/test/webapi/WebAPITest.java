/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.webapi;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.rest.api.resources.PublicContact;
import zm.hashcode.mshengu.client.rest.api.resources.PublicIncident;
import zm.hashcode.mshengu.client.rest.api.resources.PublicRequestAQuote;
import zm.hashcode.mshengu.client.rest.api.resources.PublicVendorRegistration;
import zm.hashcode.mshengu.services.external.WebAppService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class WebAPITest extends AppTest {

    @Autowired
    private WebAppService webAppService;
//    @Autowired
//    private SiteService siteService;
    private String latitude = "33.9317";
    private String longitude = "18.5128";
    private String siteId = "RR Section";
    private String unitId = "MTU-000004"; //MTU-000279

//    @Test
    public void testDateFormat() {
        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        String dateout = dateTimeFormatHelper.getFullFormateddate(new Date());
        System.out.println("Formated date ==>" + dateout);

    }

//    @Test
    public void submitPublicIncident() {
        webAppService = ctx.getBean(WebAppService.class);
        PublicIncident publicIncident = createPublicIncident();
        webAppService.incidentReport(publicIncident);

    }

//    @Test
    public void submitPublicVendorRegistration() {
        webAppService = ctx.getBean(WebAppService.class);
        PublicVendorRegistration publicVendorRegistration = createPublicVendorRegistration();
        webAppService.vendoRegistration(publicVendorRegistration);
    }

//    @Test
    public void submitPublicContact() {
        webAppService = ctx.getBean(WebAppService.class);
        PublicContact publicContact = createPublicContact();
        webAppService.contactUs(publicContact);
    }

//    @Test
    public void submitPublicRequestAQuote() {
        webAppService = ctx.getBean(WebAppService.class);
        PublicRequestAQuote requestAQuote = createPublicRequestAQuote();
        webAppService.requestForQuote(requestAQuote);
    }

    private PublicContact createPublicContact() {
        PublicContact publicContact = new PublicContact();

        publicContact.setCompany("Welwits");
        publicContact.setEmail("peterd@marginmentor.co.za");
        publicContact.setFaxNumber("NA");
        publicContact.setHearAboutUs("TED Talk");
        publicContact.setMessage("I'm really really sleepy :s");
        publicContact.setContactPersonFirstname("Fernado");
        publicContact.setContactPersonLastname("Neto");
        publicContact.setPhone("0761809890");
        return publicContact;
    }

    private PublicIncident createPublicIncident() {
        PublicIncident publicIncident = new PublicIncident();
        publicIncident.setClientName("Ngola It");
        publicIncident.setContactNumber("00244 923695140");
        publicIncident.setContactPerson("Melany Queta");
        publicIncident.setIncidentType("Vandalims");
        publicIncident.setRemarks("Oh! Snap");
        publicIncident.setSite("Kosovo2");
        publicIncident.setSuburb("Bosnia");
        publicIncident.setToiletType("Basic");
        return publicIncident;
    }

    private PublicVendorRegistration createPublicVendorRegistration() {
        PublicVendorRegistration publicVendorRegistration = new PublicVendorRegistration();
        publicVendorRegistration.setAccountNumber("07884445665");
        publicVendorRegistration.setAddressLine2("District Six Apartments");
        publicVendorRegistration.setAddressLine2("69 Sir lowry Road");
        publicVendorRegistration.setBank("Standard Bank");
        publicVendorRegistration.setBranchCode("Yadley Street");
        publicVendorRegistration.setChiefExecutiveFirstname("Malink");
        publicVendorRegistration.setChiefExecutiveLastname("Johns");
        publicVendorRegistration.setCity("Cape Town");
        publicVendorRegistration.setCompanyName("Rexat Icon");
        publicVendorRegistration.setCompanyType("Private");
        publicVendorRegistration.setCompanyRegistrationnumber("E88885L");
        publicVendorRegistration.setContactPersonFirstname("Fernando");
        publicVendorRegistration.setContactPersonLastname("Neto");
        publicVendorRegistration.setEmail("feroxneto@gmail.com");
        publicVendorRegistration.setFaxNumber("0215581999");
        publicVendorRegistration.setMobileNumber("076622852");
        publicVendorRegistration.setPostalCode(new Long("8001"));
        publicVendorRegistration.setTelephoneNumber("0215581990");
        publicVendorRegistration.setVatRegistrationNumber("VAT885LL");
        publicVendorRegistration.setVendorCategory("Supplier"); /*Case sensitive*/
        publicVendorRegistration.setWebSite("http://www.marginmentor.co.za");
        publicVendorRegistration.setYearEstablishment(2014);
        return publicVendorRegistration;
    }

    private PublicRequestAQuote createPublicRequestAQuote() {
        PublicRequestAQuote requestAQuote = new PublicRequestAQuote();
        requestAQuote.setBillingAddress("69 Kent St, Cape Town");
        requestAQuote.setCollectionDate(new Date());
        requestAQuote.setComment("Testing a comment for PublicRequestAQuote");
        requestAQuote.setCompanyNameNonRequired("Margin Mentor");
        requestAQuote.setContactNumber("0218853654");
        requestAQuote.setContactPersonFirstname("Peter");
        requestAQuote.setContactPersonLastname("Davids");
        requestAQuote.setDaysRental(18);
        requestAQuote.setDeliveryAddress("69 Kent St, Cape Town");
        requestAQuote.setDeliveryDate(new Date());
        requestAQuote.setEmail("feroxneto@gmail.com");
        requestAQuote.setEventDate(new Date());
        requestAQuote.setEventName("Shinning stars event");
        requestAQuote.setEventType("Contract");
        requestAQuote.setFaxNumber("0213365521");
        requestAQuote.setNumberOfJanitors(5);
        requestAQuote.setNumberOfToiletRolls(200);
        requestAQuote.setQuantityRequired1(20);
        requestAQuote.setQuantityRequired2(25);
        requestAQuote.setQuantityRequired3(15);
        requestAQuote.setServiceFrequencyMon(true);
        requestAQuote.setServiceFrequencyTue(false);
        requestAQuote.setServiceFrequencyWed(true);
        requestAQuote.setServiceFrequencyThur(false);
        requestAQuote.setServiceFrequencyFri(true);
        requestAQuote.setServiceFrequencySat(false);
        requestAQuote.setServiceFrequencySun(false);
        requestAQuote.setTelephoneNumberNonRequired("0214582546");
        requestAQuote.setToiletsRequired1("Basic Atlas");
        requestAQuote.setToiletsRequired2("Standard Non-Flush");
        requestAQuote.setToiletsRequired3("Executive Flush & Hand Basin");
        requestAQuote.setVatRegistrationNumberUnrequired("VAT55LL89");
        return null;
    }
}
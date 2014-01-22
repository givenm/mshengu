/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.rest.api.controllers;

import java.io.IOException;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zm.hashcode.mshengu.client.rest.api.resources.PublicContact;
import zm.hashcode.mshengu.client.rest.api.resources.PublicIncident;
import zm.hashcode.mshengu.client.rest.api.resources.PublicRequestAQuote;
import zm.hashcode.mshengu.client.rest.api.resources.PublicResponseToRFQ;
import zm.hashcode.mshengu.client.rest.api.resources.PublicVendorRegistration;
import zm.hashcode.mshengu.services.external.impl.WebAppServiceImpl;

/**
 *
 * @author given
 */
@Controller
@RequestMapping("web")
public class ApiRestControllerWeb {
    
    @Autowired
    private WebAppServiceImpl webAppService;
    

    @RequestMapping(value = "contact", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public String contact(@ModelAttribute PublicContact contact) throws EmailException {
        webAppService.contactUs(contact);
        return "Received..";
    }

    @RequestMapping(value = "incident", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public String incident(@ModelAttribute PublicIncident incident) throws IOException {
        webAppService.incidentReport(incident);
        return "Received..";
    }

    @RequestMapping(value = "vendoregistration", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public String vendoRegistration(@ModelAttribute PublicVendorRegistration vendorRegistration) {
        webAppService.vendoRegistration(vendorRegistration);
        return "Received..";
    }

    @RequestMapping(value = "responsetorfq", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public String responseToRFQ(@ModelAttribute PublicResponseToRFQ responseToRFQ) {
        webAppService.responseToRFQ(responseToRFQ);
        return "Received..";
    }

    @RequestMapping(value = "requestaquote", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public String incomingRFQ(@ModelAttribute PublicRequestAQuote requestAQuote) {
        webAppService.requestForQuote(requestAQuote);
        return "Received..";
    }
    
    @RequestMapping(value = "checkrfqnumber/{rfqNumber}", method = RequestMethod.GET)
    @ResponseBody
    public String checkRfqNumber(@PathVariable String rfqNumber) {
        System.out.println("Checking RFQ #: " + rfqNumber);
        boolean exists = webAppService.checkRfqNumber(rfqNumber);        
        return exists + ""; 
    }
}

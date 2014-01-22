/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.external;

import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.client.rest.api.resources.PublicContact;
import zm.hashcode.mshengu.client.rest.api.resources.PublicIncident;
import zm.hashcode.mshengu.client.rest.api.resources.PublicRequestAQuote;
import zm.hashcode.mshengu.client.rest.api.resources.PublicResponseToRFQ;
import zm.hashcode.mshengu.client.rest.api.resources.PublicVendorRegistration;

/**
 *
 * @author Ferox
 */
@Service
public interface WebAppService {

    /**
     * 
     * @param contact 
     */
    public void contactUs(PublicContact contact);
    
    /**
     * 
     * @param incident 
     */
    public void incidentReport(PublicIncident incident);
    
    /**
     * 
     * @param responseToRFQ 
     */
    public void responseToRFQ(PublicResponseToRFQ responseToRFQ);
    
       /**
        * 
        * @param requestAQuote 
        */     
    public void requestForQuote(PublicRequestAQuote requestAQuote);
    
    /**
     * 
     * @param vendorRegistration 
     */
    public void vendoRegistration(PublicVendorRegistration vendorRegistration);

    public boolean checkRfqNumber(String rfqNumber);
}
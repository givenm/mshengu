/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.external.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.util.SendEmailHelper;
import zm.hashcode.mshengu.client.rest.api.resources.PublicContact;
import zm.hashcode.mshengu.client.rest.api.resources.PublicIncident;
import zm.hashcode.mshengu.client.rest.api.resources.PublicRequestAQuote;
import zm.hashcode.mshengu.client.rest.api.resources.PublicResponseToRFQ;
import zm.hashcode.mshengu.client.rest.api.resources.PublicVendorRegistration;
import zm.hashcode.mshengu.domain.external.ContactUS;
import zm.hashcode.mshengu.domain.external.IncomingRFQ;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.incident.Incident;
import zm.hashcode.mshengu.domain.incident.IncidentType;
import zm.hashcode.mshengu.domain.incident.UserAction;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;
import zm.hashcode.mshengu.domain.procurement.ResponseToRFQ;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;
import zm.hashcode.mshengu.domain.ui.util.Sequence;
import zm.hashcode.mshengu.services.external.ContactUSService;
import zm.hashcode.mshengu.services.external.IncomingRFQService;
import zm.hashcode.mshengu.services.external.MailNotificationsService;
import zm.hashcode.mshengu.services.external.WebAppService;
import zm.hashcode.mshengu.services.incident.IncidentService;
import zm.hashcode.mshengu.services.incident.IncidentTypeService;
import zm.hashcode.mshengu.services.people.ContactPersonService;
import zm.hashcode.mshengu.services.procurement.RequestForQuoteService;
import zm.hashcode.mshengu.services.procurement.RequestPurchaseItemService;
import zm.hashcode.mshengu.services.procurement.ResponseToRFQService;
import zm.hashcode.mshengu.services.products.UnitTypeService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderCategoryService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderService;
import zm.hashcode.mshengu.services.ui.util.SequenceService;

/**
 *
 * @author Ferox
 */
@Service
public class WebAppServiceImpl implements WebAppService {

    private SendEmailHelper sendEmailHelper = new SendEmailHelper();
//    private UtilMethods utilMethods = new UtilMethods();
    @Autowired
    private MailNotificationsService mailNotificationService;
    @Autowired
    private ContactUSService contactUSService;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private IncidentService incidentService;
    @Autowired
    private IncidentTypeService incidentTypeService;
    @Autowired
    private IncomingRFQService incomingRFQService;
    @Autowired
    private UnitTypeService unitTypeService;
    @Autowired
    private ServiceProviderCategoryService serviceProviderCategoryService;
    @Autowired
    private ContactPersonService contactPersonService;
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    RequestPurchaseItemService requestPurchaseItemService;
    @Autowired
    ResponseToRFQService responseToRFQService;
    @Autowired
    RequestForQuoteService requestForQuoteService;

    /**
     * 
     * @param publicContact 
     */
    @Override
    public void contactUs(PublicContact publicContact) {

        final MailNotifications mailNotifications = mailNotificationService.findByName("CONTACT_NOTIFICATION");

        final String refNumber = getRefNumber(mailNotifications);

        final ContactUS contactUS = new ContactUS.Builder(new Date())
                .closed(false)
                .company(publicContact.getCompany())
                .email(publicContact.getEmail())
                .faxNumber(publicContact.getFaxNumber())
                .hearAboutUs(publicContact.getHearAboutUs())
                .mailNotifications(mailNotifications)
                .message(publicContact.getMessage())
                .contactPersonFirstname(publicContact.getContactPersonFirstname())
                .contactPersonLastname(publicContact.getContactPersonLastname())
                .phone(publicContact.getPhone())
                .refNumber(refNumber)
                //                .status(null)
                //                .status(status)
                //                .id(publicContact.getId())
                .build();

        contactUSService.persist(contactUS);
        sendEmailHelper.sedContactUsEmail(contactUS);
        sendEmailHelper.feedbackEmail(new Date(), contactUS.getEmail(), contactUS.getRefNumber(), contactUS.getMessage());

//
//        System.out.println("API TEST --  contactUs");
//        System.out.println("API TEST --  contactUs " + publicContact.getCompany());
//        System.out.println("API TEST --  contactUs " + contactUS.getCompany());
    }

    /**
     * 
     * @param publicIncident 
     */
    @Override
    public void incidentReport(PublicIncident publicIncident) {

        final IncidentType incidentType = incidentTypeService.findByName(publicIncident.getIncidentType());
        final UnitType unitType = unitTypeService.findByName(publicIncident.getToiletType());
        final MailNotifications mailNotifications = mailNotificationService.findByName("INCIDENT_NOTIFICATION");

        final String refNumber = getRefNumber(mailNotifications);
//        final ServiceProvider  serviceProvider =  ServiceProviderFacade.getServiceProviderService().findById(publicIncident.getServiceProvider());
//        final Status status =  StatusFacade.getStatusService().findById(publicIncident.getStatus());
        Set<UserAction> userActionList = new HashSet<>();

        final Incident incident = new Incident.Builder(new Date())
                .incidentType(incidentType)
                .closed(false)
                .comment(publicIncident.getRemarks())
                .contactPerson(publicIncident.getContactPerson())
                .contactNumber(publicIncident.getContactNumber())
                .customer(publicIncident.getClientName())
                .userAction(userActionList)
                .mailNotifications(mailNotifications)
                //                .
                .toiletType(unitType)
                //                .serviceProvider(serviceProvider)
                .site(publicIncident.getSite())
                .suburb(publicIncident.getSuburb())
                //                .status(status)
                //                .id(publicIncident.getId())
                .refNumber(refNumber)
                .build();

        incidentService.persist(incident);
        sendEmailHelper.sendIncidentReportEmail(incident);
//        sendEmailHelper.feedbackEmail(incident.getContactPerson(), incident.getEmail(), incident.getRefNumber(), incident.getMessage());
//        System.out.println("API TEST --  incidentReport");
//        System.out.println("API TEST --  incidentReport" + publicIncident.getClientName());
//        System.out.println("API TEST --  incidentReport" + incident.getCustomer());
    }

    /**
     * 
     * @param publicResponseToRFQ 
     */
    @Override
    public void responseToRFQ(PublicResponseToRFQ publicResponseToRFQ) {

        Set<RequestPurchaseItem> itemList = saveRequestItems(publicResponseToRFQ);
        
        ResponseToRFQ responseToRFQ = saveResponseToRFQ(publicResponseToRFQ, itemList);
        
        List<ResponseToRFQ> respondToRFQList = new ArrayList<>();

        RequestForQuote requestForQuote = requestForQuoteService.findByRfqNumber(publicResponseToRFQ.getRfqNumber());
        if (requestForQuote != null) {
            if (requestForQuote.getResponseToRFQ() != null) {
                respondToRFQList.addAll(requestForQuote.getResponseToRFQ());
            }
            respondToRFQList.add(responseToRFQ);

            RequestForQuote quote = new RequestForQuote.Builder(requestForQuote.getPerson())
                    .request(requestForQuote)
                    .responseToRFQ(respondToRFQList)
                    .id(requestForQuote.getId())
                    .build();
            requestForQuoteService.merge(quote);
            sendEmailHelper.feedbackEmail2(new Date(), publicResponseToRFQ.getEmail(), responseToRFQ.getRefNumber());
        } else {
            //Notify Stuff that the there is a response to an unidentified qoute
            //Send a feedback email saying that we recieved the submission but the qoute is unundentified.
            sendEmailHelper.feedbackEmail2(new Date(), publicResponseToRFQ.getEmail(), responseToRFQ.getRefNumber());
        }

    }
    
    
    private ResponseToRFQ saveResponseToRFQ(PublicResponseToRFQ publicResponseToRFQ, Set<RequestPurchaseItem> itemList) {
        
        final MailNotifications mailNotifications = mailNotificationService.findByName("RESPOND_RFQ");
        
         ResponseToRFQ responseToRFQ = new ResponseToRFQ.Builder(publicResponseToRFQ.getCompanyName())
                .email(publicResponseToRFQ.getEmail())
                .companyType(publicResponseToRFQ.getCompanyType())
                .yearEstablishment(publicResponseToRFQ.getYearEstablishment())
                .chiefExecutive(publicResponseToRFQ.getChiefExecutive())
                .registeredForVat(publicResponseToRFQ.hasVat())
                .vatRegistrationNumber(publicResponseToRFQ.getVatRegistrationNumber())
                .webSite(publicResponseToRFQ.getWebSite())
                .items(itemList)
                .validityOfQuote(publicResponseToRFQ.getValidityOfQuote())
                 .paymentTerms(publicResponseToRFQ.getPaymentTerms())
                .refNumber(getRefNumber(mailNotifications))
                .build();
        responseToRFQService.persist(responseToRFQ);
        
        return  responseToRFQ;
        
    }   
    
    
    private Set<RequestPurchaseItem> saveRequestItems(PublicResponseToRFQ publicResponseToRFQ) {

        Set<RequestPurchaseItem> itemList = new HashSet<>();
//        System.out.println(publicResponseToRFQ.getItem().size() + "");

        for (int i = 0; i < publicResponseToRFQ.getItem().size(); i++) {

//            System.out.println("index" + i);
            if (publicResponseToRFQ.getQty().get(i) == null) {
                break;
            } else {
                RequestPurchaseItem item = new RequestPurchaseItem.Builder(publicResponseToRFQ.getQty().get(i).toString())
                        .itemDescription(publicResponseToRFQ.getItem().get(i))
                        .itemNumber(publicResponseToRFQ.getItemNumber().get(i))
                        .subTotal(new BigDecimal(publicResponseToRFQ.getSubTotal().get(i).toString()))
                        .unit(publicResponseToRFQ.getUnit().get(i).toString())
                        .unitPrice(new BigDecimal(publicResponseToRFQ.getUnitPrice().get(i).toString()))
                        .build();
                requestPurchaseItemService.persist(item);
                itemList.add(item);
            }
        }
        
        return itemList;
    }

    /**
     * 
     * @param publicRequestAQuote 
     */
    @Override
    public void requestForQuote(PublicRequestAQuote publicRequestAQuote) {

        /*Use the correct name for mailnoifications below*/
        final MailNotifications mailNotifications = mailNotificationService.findByName("REQUEST_TOILET_HIRE_NOTIFCATION");
        Set<UserAction> userActions = new HashSet<>();



        /*The Entity ServiceProvider has missing variables. Please consult the PublicVendorRegistration bean. */
        /*NB: The ServiceProviderBean (Under web/procurement/vendors/models) has details are the same with the website and could be used*/
        final IncomingRFQ incomingRFQ = new IncomingRFQ.Builder(new Date())
                .refNumber(getRefNumber(mailNotifications)) /*This field does not exist on the website form*/
                .billingAddress(publicRequestAQuote.getBillingAddress())
                .closed(false) /*what should be the value for clsed?*/
                .collectionDate(publicRequestAQuote.getCollectionDate()).comment(publicRequestAQuote.getComment())
                .companyName(publicRequestAQuote.getCompanyNameNonRequired())
                .contactPersonFirstname(publicRequestAQuote.getContactPersonFirstname())
                .contactPersonLastname(publicRequestAQuote.getContactPersonLastname())
                .daysRental(publicRequestAQuote.getDaysRental())
                .deliveryAddress(publicRequestAQuote.getDeliveryAddress())
                .deliveryDate(publicRequestAQuote.getDeliveryDate())
                .email(publicRequestAQuote.getEmail())
                .eventDate(publicRequestAQuote.getEventDate())
                .eventName(publicRequestAQuote.getEventName())
                .eventType(publicRequestAQuote.getEventType())
                .mailNotifications(mailNotifications)
                .numberOfJanitors(publicRequestAQuote.getNumberOfJanitors())
                .numberOfToiletRolls(publicRequestAQuote.getNumberOfToiletRolls())
                .quantityRequired1(publicRequestAQuote.getQuantityRequired1())
                .quantityRequired2(publicRequestAQuote.getQuantityRequired2())
                .quantityRequired3(publicRequestAQuote.getQuantityRequired3())
                .userAction(userActions)
                .vatNumber(publicRequestAQuote.getVatRegistrationNumberUnrequired())
//                .quantityRequired4(2) /*This field does not exist. there only 3 quantities in Request a quote*/                
                
                .telephoneNumber(publicRequestAQuote.getTelephoneNumberNonRequired())
                .toiletsRequired1(publicRequestAQuote.getToiletsRequired1())
                .toiletsRequired2(publicRequestAQuote.getToiletsRequired2())
                .toiletsRequired3(publicRequestAQuote.getToiletsRequired3())
//                .toiletsRequired4("This field does not exist on the website form") //*This is does not exist on the website form*/
                
                .monday(publicRequestAQuote.isServiceFrequencyMon())
                .tuesday(publicRequestAQuote.isServiceFrequencyTue())
                .wednesday(publicRequestAQuote.isServiceFrequencyWed())
                .thursday(publicRequestAQuote.isServiceFrequencyThur())
                .friday(publicRequestAQuote.isServiceFrequencyFri())
                .saturday(publicRequestAQuote.isServiceFrequencySat())
                .sunday(publicRequestAQuote.isServiceFrequencySun())
                .build();



        incomingRFQService.persist(incomingRFQ);
//        System.out.println("API TEST --  requestForQuote");
//        System.out.println("API TEST --  requestForQuote " + publicRequestAQuote.getContactPerson());
        sendEmailHelper.icomingQuoteRequest(incomingRFQ);
        sendEmailHelper.feedbackEmail(new Date(), incomingRFQ.getEmail(), incomingRFQ.getRefNumber(), publicRequestAQuote.toString());
    }

    
    @Override
    public void vendoRegistration(PublicVendorRegistration publicVendorRegistration) {
        
        final MailNotifications mailNotifications = mailNotificationService.findByName("VENDOR_NOTIFICATION");
        final ServiceProviderCategory serviceProviderCategory = serviceProviderCategoryService.findByCategoryName(publicVendorRegistration.getVendorCategory());
        /*Finding the contact person will not work because of the input box with name and surname in one box*/
      
        ContactPerson contactPerson = saveContactPerson(publicVendorRegistration);
        
        Set<ServiceProviderProduct> serviceProviderProducts = new HashSet<>();

        /*The Entity ServiceProvider has missing variables. Please consult the PublicVendorRegistration bean. */
        /*NB: The ServiceProviderBean (Under web/procurement/vendors/models) has details are the same with the website and could be used*/
        final ServiceProvider provider = new ServiceProvider.Builder(publicVendorRegistration.getCompanyName())
                .accountNumber(publicVendorRegistration.getAccountNumber())
                .bankName(publicVendorRegistration.getBank())
                .branchCode(publicVendorRegistration.getBranchCode())
                .contactPerson(contactPerson)
                .firstNameChiefExec(publicVendorRegistration.getChiefExecutiveFirstname()) /*this will not work bcoz there is one input box for name & surname*/
                .lastNameChiefExec(publicVendorRegistration.getChiefExecutiveLastname())
                .legalForm(publicVendorRegistration.getCompanyType())
                .registeredForVat(publicVendorRegistration.hasVat())
                .registrationNum(publicVendorRegistration.getCompanyRegistrationnumber())
                .serviceProviderCategory(serviceProviderCategory)
                .serviceProviderProduct(serviceProviderProducts)
                .preferedVendor(false)
                .active(false) /*what should be the value for status?*/
                .vatNum(publicVendorRegistration.getCompanyRegistrationnumber())
                .vehicleMaintenance(false) /*what should be the value for vehicle maintenance?*/// -->FALSE
                .website(publicVendorRegistration.getWebSite())
                .yearsOfBusiness(publicVendorRegistration.getYearEstablishment()) /*Fernando: you said you wanted the date as Date type but here you want a String*/
                .mailNotifications(mailNotifications)
                .vendorNumber(getRefNumber(mailNotifications))
                .build();



        serviceProviderService.persist(provider);
        sendEmailHelper.feedbackEmail2(new Date(), publicVendorRegistration.getEmail(), provider.getVendorNumber());
    }
    
    private ContactPerson saveContactPerson(PublicVendorRegistration publicVendorRegistration) {
          ContactPerson contactPerson = contactPersonService.findByEmail(publicVendorRegistration.getEmail());
        if (contactPerson == null) {
            contactPerson = new ContactPerson.Builder(publicVendorRegistration.getContactPersonFirstname(), publicVendorRegistration.getContactPersonLastname())
                    .address1(publicVendorRegistration.getAddressLine1())
                    .address2(publicVendorRegistration.getAddressLine2())
                    .city(publicVendorRegistration.getCity())
                    .code(publicVendorRegistration.getPostalCode().toString())
                    .emailAddress(publicVendorRegistration.getEmail())
                    .faxNumber(publicVendorRegistration.getFaxNumber())
                    .mainNumber(publicVendorRegistration.getTelephoneNumber())
                    .otherNumber(publicVendorRegistration.getMobileNumber())
                    .build();

            contactPersonService.persist(contactPerson);
        }
        return contactPerson;
    }

    private String formatNotificationNumber(int refNumber) {
        //100000
        if (refNumber < 10) {
            return "00000" + refNumber; //000001-000009
        } else if (refNumber < 100) {
            return "0000" + refNumber;  //000010-000099
        } else if (refNumber < 1000) {
            return "000" + refNumber;   //000100-000999
        } else if (refNumber < 10000) {
            return "00" + refNumber;    //001000-009999
        } else if (refNumber < 100000) {
            return "0" + refNumber;     //010000-099999
        } else {
            return "" + refNumber;
        }
    }

    private void updateSequence(Sequence notificationSequence) {

//        sequenceService = ctx.getBean(sequenceService.class);
//        SequenceFacade
        int sequenceValue = notificationSequence.getValue() + 1;

        if (!StringUtils.isEmpty(notificationSequence)) {
            Sequence newUnitIdSequence = new Sequence.Builder(notificationSequence.getName())
                    .sequenceType(notificationSequence.getSequenceType())
                    .namingCode(notificationSequence.getNamingCode())
                    .value(sequenceValue)
                    .id(notificationSequence.getId())
                    .build();
            sequenceService.merge(newUnitIdSequence);
        }
    }
//    String refNumber = "";
//    Sequence sequence = getSequence(mailNotifications);

    private String getRefNumber(MailNotifications mailNotifications) {

        String refNumber = "EN_MSH" + new Date().toString();

        if (mailNotifications != null) {
            if (mailNotifications.getSequence() != null) {
                Sequence sequence = mailNotifications.getSequence();
                String counter = formatNotificationNumber(sequence.getValue());
                refNumber = sequence.getNamingCode() + "-" + counter;// formatNotificationNumber(sequence.getValue());; //get formated refnumber
                updateSequence(sequence); // update sequence
                return refNumber;
            } else {
                return refNumber;
            }
        } else {
            return refNumber;
        }
    }
    
    @Override
    public boolean checkRfqNumber(String rfqNumber) {
        boolean found = false;
        RequestForQuote rfq = requestForQuoteService.findByRfqNumber(rfqNumber);
        if (rfq != null) {
            found = true;
        }
        System.out.println("Found: " + found);
        return found;
    }
}

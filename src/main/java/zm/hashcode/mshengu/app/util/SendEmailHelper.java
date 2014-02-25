/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util;

import com.vaadin.ui.Notification;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.activation.DataSource;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.util.email.ComposeEmail;
import zm.hashcode.mshengu.app.util.email.EmailUtil;
import zm.hashcode.mshengu.client.rest.api.resources.PublicResponseToRFQ;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;
import zm.hashcode.mshengu.domain.external.ContactUS;
import zm.hashcode.mshengu.domain.external.IncomingRFQ;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.incident.Incident;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Ferox
 */
public class SendEmailHelper {

    final private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();

    /**
     * 
     * @param contactUS 
     */
    public void sedContactUsEmail(ContactUS contactUS) {

        if (contactUS != null) {
            String subject = "Contact Request  " + contactUS.getRefNumber() + " has been logged in the Mshengu KMIS.";
            String subject2 = "A contact request  " + contactUS.getRefNumber() + " has been logged in the Mshengu KMIS.";
            String messageBody = new StringBuilder()
                    .append("\n\n")
                    .append(subject2).append("\n")
                    .append("\n\n")
                    .append("Contact Request Number : ").append(contactUS.getRefNumber()).append("\n\n")
                    .append("Contact Request Date : ").append(formatHelper.getFullFormateddate(contactUS.getDateOfAction())).append("\n\n")
                    .append("Contact Person : ").append(contactUS.getContactPersonFirstname()).append(" ").append(contactUS.getContactPersonLastname()).append("\n\n")
                    .append("Contact Number : ").append(contactUS.getPhone()).append("\n\n")
                    .append("Email : ").append(contactUS.getEmail()).append("\n\n")
                    .append("Request Details : ").append(contactUS.getMessage()).append("\n")
                    .append("\n\n")
                    .append("\n")
                    .append("To attend to this contact request, please log on to the Mshengu  KMIS,\n")
                    .append("using the following web address : www.mshengutoilethire.co.za \n")
                    .append("\n\n")
                    .append("\n")
                    //                    .append("Please logon to the following site to access the System: www.mshengutoilethire.co.za")
                    .toString();

            sendEmail(contactUS.getMailNotifications(), subject, messageBody);

        } else {
            Notification.show("Email Not Sent, Notification Type Not Set!", Notification.Type.ERROR_MESSAGE);
        }
    }

    /**
     *
     * @param incident
     */
    public void sendIncidentReportEmail(Incident incident) {
        if (incident != null) {

            String subject = "Incident Report " + incident.getRefNumber() + " has been logged in the Mshengu KMIS.";
            String subject2 = "An incident" + incident.getRefNumber() + " has been logged in the Mshengu KMIS.";

            String messageBody = new StringBuilder()
                    .append("\n\n")
                    .append(subject2).append("\n")
                    .append("\n\n")
                    .append("Incident Reference Number : ").append(incident.getRefNumber()).append("\n\n")
                    .append("Incident Date : ").append(formatHelper.getFullFormateddate(incident.getActionDate())).append("\n\n")
                    .append("Contact Person : ").append(incident.getContactPerson()).append("\n\n")
                    .append("Contact Number : ").append(incident.getContactNumber()).append("\n\n")
                    //                    .append("Email : ").append("").append("\n\n")
                    .append("Incident Details : ").append(incident.getComment()).append("\n\n")
                    .append("\n")
                    .append("\n\n")
                    .append("To attend to this incident report, please log on to the Mshengu  KMIS,\n")
                    .append("using the following web address : www.mshengutoilethire.co.za \n")
                    .append("\n\n")
                    .append("\n")
                    //                    .append("Please logon to the following site to access the System: www.mshengutoilethire.co.za")
                    .toString();

            sendEmail(incident.getMailNotifications(), subject, messageBody);

        } else {
            Notification.show("Email Not Sent, Notification Type Not Set!", Notification.Type.ERROR_MESSAGE);
        }
    }

    public void sendToSupplier(DataSource file, String vendorEmail, String ordernumber, String content) {
        if (file != null) {

            String subject = content + " " + ordernumber;

            ComposeEmail email = new ComposeEmail();
            List<String> addressesTo = new ArrayList<>();
            addressesTo.add(vendorEmail);

            email.setFrom("customerservice@mshengutoilethire.co.za");
            email.setPassword("Mth@cust123");
            email.setAddressesTo(addressesTo);
            email.setSubject(subject);
            email.setEmailBody("");
            EmailUtil.sendWithAttachments(email, file, ordernumber);

        } else {
            Notification.show("Email Not Sent, Notification attachment not found!", Notification.Type.ERROR_MESSAGE);
        }
    }
    
    public void sendToNewCustomer(DataSource file, String customerEmail, String subject, String nameOfFile) {
        if (file != null) {

            String subj = subject;

            ComposeEmail email = new ComposeEmail();
            List<String> addressesTo = new ArrayList<>();
            addressesTo.add(customerEmail);

            email.setFrom("customerservice@mshengutoilethire.co.za");
            email.setPassword("Mth@cust123");
            email.setAddressesTo(addressesTo);
            email.setSubject(subj);
            email.setEmailBody("");
            EmailUtil.sendWithAttachments(email, file, nameOfFile);

        } else {
            Notification.show("Email Not Sent, Notification attachment not found!", Notification.Type.ERROR_MESSAGE);
        }
    }

    /**
     *
     * @param responseToRFQ
     */
//    @Override
    public void sendResponseToRFQEmail(PublicResponseToRFQ responseToRFQ) {
    }

    /**
     *
     * @param incomingRFQ
     */
    public void sendRequestForQuoteEmail(IncomingRFQ incomingRFQ) {
    }

    /**
     *
     * @param serviceProvider
     * @param mailNotifications
     */
    public void vendoRegistration(ServiceProvider serviceProvider, MailNotifications mailNotifications) {
        if (serviceProvider != null) {

            String subject = "Vendor " + serviceProvider.getVendorNumber() + " has been logged in the Mshengu KMIS.";
            String subject2 = "Vendor" + serviceProvider.getVendorNumber() + " has been logged in the Mshengu KMIS.";

            String messageBody = new StringBuilder()
                    .append("\n\n")
                    .append(subject2).append("\n")
                    .append("\n\n")
                    .append("Vendor Reference Number : ").append(serviceProvider.getVendorNumber()).append("\n\n")
                    //                    .append("Vendor Date : ").append(formatHelper.getFullFormateddate(new Date())).append("\n\n")
                    .append("Contact Person : ").append(serviceProvider.getContactPersonName()).append("\n\n")
                    .append("Contact Number : ").append(serviceProvider.getContactPersonMainNumber()).append("\n\n")
                    //                    .append("Email : ").append("").append("\n\n")
                    .append("Vendor Details : ").append(serviceProvider.getName()).append("\n\n")
                    .append("\n")
                    .append("\n\n")
                    .append("To attend to this incident report, please log on to the Mshengu  KMIS,\n")
                    .append("using the following web address : www.mshengutoilethire.co.za \n")
                    .append("\n\n")
                    .append("\n")
                    //                    .append("Please logon to the following site to access the System: www.mshengutoilethire.co.za")
                    .toString();

            sendEmail(mailNotifications, subject, messageBody);

        } else {
            Notification.show("Email Not Sent, Notification Type Not Set!", Notification.Type.ERROR_MESSAGE);
        }
    }

        public void icomingQuoteRequest(IncomingRFQ incomingRFQ) {
        if (incomingRFQ != null) {
//            String siteName = getSiteName(incomingRFQ.getSiteId());
            String customerName = incomingRFQ.getCompanyName();

            String subject = "Request for Hire " + incomingRFQ.getRefNumber()+ " has been logged in the Mshengu KMIS.";
            String subject2 = "A request for hire " + incomingRFQ.getRefNumber() + " has been logged in the Mshengu KMIS.";

            String messageBody = new StringBuilder()
                    .append("\n\n")
                    .append(subject2).append("\n")
                    .append("\n\n")
                    .append("Request Reference Number : ").append(incomingRFQ.getRefNumber()).append("\n")
                    .append("Request Date : ").append(formatHelper.getFullFormateddateNoTime(incomingRFQ.getDateOfAction())).append("\n")
                    .append("Contact Person : ").append(incomingRFQ.getContactPersonFirstname()).append(" ").append(incomingRFQ.getContactPersonLastname()).append("\n")
                    .append("Contact Number : ").append(incomingRFQ.getContactNumber()).append("\n")
                    .append("Cell Number : ").append(incomingRFQ.getTelephoneNumber()).append("\n")
                    .append("Email : ").append(incomingRFQ.getEmail()).append("\n\n")
                    //                    .append("Email : ").append("").append("\n\n")
                    .append("----- Request Details -----").append("\n")
                    .append("Customer Name : ").append(customerName).append("\n")
                    .append("Event Name : ").append(incomingRFQ.getEventName()).append("\n")
                    .append("Event Type : ").append(incomingRFQ.getEventType()).append("\n")
                    .append("Event Date : ").append(incomingRFQ.getEventDate()).append("\n")
                    .append("Delivery Date : ").append(formatHelper.getFullFormateddateNoTime(incomingRFQ.getDeliveryDate())).append("\n")
                    .append("Collection Date : ").append(formatHelper.getFullFormateddateNoTime(incomingRFQ.getCollectionDate())).append("\n\n")
                    .append("Comments  : ").append(incomingRFQ.getComment()).append("\n")
                    .append("\n")
                    .append("\n\n")
                    .append("To attend to this  request for hire, please log on to the Mshengu  KMIS,\n")
                    .append("using the following web address : www.mshengutoilethire.co.za \n")
                    .append("\n\n")
                    .append("\n")
                    //                    .append("Please logon to the following site to access the System: www.mshengutoilethire.co.za")
                    .toString();

            sendEmail(incomingRFQ.getMailNotifications(), subject, messageBody);

        } else {
            Notification.show("Email Not Sent, Notification Type Not Set!", Notification.Type.ERROR_MESSAGE);
        }
    }
        /**
         * 
         * @param serviceRequest 
         */
    public void serviceRequest(ServiceRequest serviceRequest) {
        if (serviceRequest != null) {
            String siteName = getSiteName(serviceRequest.getSiteId());
            String customerName = getCustomerName(serviceRequest.getCustomerId());

            String subject = "Service Request " + serviceRequest.getRefNumber() + " has been logged in the Mshengu KMIS.";
            String subject2 = "A service request " + serviceRequest.getRefNumber() + " has been logged in the Mshengu KMIS.";

            String messageBody = new StringBuilder()
                    .append("\n\n")
                    .append(subject2).append("\n")
                    .append("\n\n")
                    .append("Request Reference Number : ").append(serviceRequest.getRefNumber()).append("\n")
                    .append("Request Date : ").append(formatHelper.getFullFormateddateNoTime(serviceRequest.getRequestDate())).append("\n")
                    .append("Contact Person : ").append(serviceRequest.getContactPersonName()).append("\n")
                    .append("Contact Number : ").append(serviceRequest.getContactPersonMainNumber()).append("\n")
                    .append("Cell Number : ").append(serviceRequest.getContactPersonAlternativeNumber()).append("\n")
                    .append("Email : ").append(serviceRequest.getContactPersonEmail()).append("\n\n")
                    //                    .append("Email : ").append("").append("\n\n")
                    .append("----- Request Details -----").append("\n")
                    .append("Customer Name : ").append(customerName).append("\n")
                    .append("Site Name : ").append(siteName).append("\n")
                    .append("Service Request Type : ").append(serviceRequest.getServiceRequestTypeName()).append("\n")
                    .append("Delivery Date : ").append(formatHelper.getFullFormateddateNoTime(serviceRequest.getDeliveryDate())).append("\n")
                    .append("Service Request Time : ").append(serviceRequest.getDeliveryTime()).append("\n")
                    .append("Collection Date : ").append(formatHelper.getFullFormateddateNoTime(serviceRequest.getCollectionDate())).append("\n\n")
                    .append(serviceRequest.getUnitsRequired())
                    .append("\n")
                    .append("\n\n")
                    .append("To attend to this service request , please log on to the Mshengu  KMIS,\n")
                    .append("using the following web address : www.mshengutoilethire.co.za \n")
                    .append("\n\n")
                    .append("\n")
                    //                    .append("Please logon to the following site to access the System: www.mshengutoilethire.co.za")
                    .toString();

            sendEmail(serviceRequest.getMailNotifications(), subject, messageBody);

        } else {
            Notification.show("Email Not Sent, Notification Type Not Set!", Notification.Type.ERROR_MESSAGE);
        }
    }

    private void sendEmail(MailNotifications mailNotifications, String subject, String messageBody) {
        if (mailNotifications != null) {
            if (mailNotifications.getEmailList().size() > 0) {
                ComposeEmail email = new ComposeEmail();
                List<String> addressesTo = new ArrayList<>();
                addressesTo.add("customerservice@mshengutoilethire.co.za");

                email.setFrom("customerservice@mshengutoilethire.co.za");
                email.setPassword("Mth@cust123");
                email.setAddressesTo(addressesTo);
                email.setAddressesCC(mailNotifications.getEmailList());
                email.setSubject(subject);
                email.setEmailBody(messageBody);
                EmailUtil.sendSimpleEmail(email);
            } else {
//                Notification.show("Email Not Sent, No Recipient Address Found!", Notification.Type.ERROR_MESSAGE);
            }
        } else {
//            Notification.show("Email Not Sent, Notification Type Not Set!", Notification.Type.ERROR_MESSAGE);
        }
    }

    public void feedbackEmail(Date dateIn, String customerEmail, String refNumber, String query) {
//        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
//        String formatedDate = dateTimeFormatHelper.getFullFormateddate(dateIn);

        String subject = "Reply to your Query " + refNumber + " - Mshengu Toilet Hire.";
        String messageBody = new StringBuilder()
                .append("\n\n")
                .append("Dear Vauled Customer").append("\n\n")
                .append("Thank you for your query dated ").append(formatHelper.getFullFormateddate(dateIn)).append(" with subject : \" ").append(query).append(" \"\n\n\n")
                .append("Your query will be attended to by our sales consultants within 24 hours.").append("\n\n")
                .append("Your reference number for this query is ").append(refNumber).append("\n\n")
                .append("Please do NOT reply to this message as it is sent from an unattended mailbox..").append("\n\n\n")
                .append("Kind Regards").append("\n\n")
                .append("Mshengu Toilet Hire").append("\n\n")
                .append("\n")
                .append("Mshengu Customer Service").append("\n")
                .append("E-Mail : ").append("customerservice@mshengutoilethire.co.za").append("\n")
                .toString();

        ComposeEmail email = new ComposeEmail();
        List<String> addressesTo = new ArrayList<>();
        addressesTo.add(customerEmail);

        email.setFrom("customerservice@mshengutoilethire.co.za");
        email.setPassword("Mth@cust123");
        email.setAddressesTo(addressesTo);
        email.setSubject(subject);
        email.setEmailBody(messageBody);
        EmailUtil.sendSimpleEmail(email);

    }

    public void feedbackEmail2(Date dateIn, String customerEmail, String refNumber) {
//        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
//        String formatedDate = dateTimeFormatHelper.getFullFormateddate(dateIn);

        String subject = "Reply to your Query " + refNumber + " - Mshengu Toilet Hire.";
        String messageBody = new StringBuilder()
                .append("\n\n")
                .append("Dear Vauled Customer").append("\n\n")
                .append("Your quey with reference number ").append(refNumber).append(" has been logged into our system").append("\n\n\n")
                .append("Thank You for your submission").append("\n\n\n")
                .append("Please do NOT reply to this message as it is sent from an unattended mailbox..").append("\n\n\n")
                .append("Kind Regards").append("\n\n")
                .append("Mshengu Toilet Hire").append("\n\n")
                .append("\n")
                .append("Mshengu Customer Service").append("\n")
                .append("E-Mail : ").append("customerservice@mshengutoilethire.co.za").append("\n")
                .toString();

        ComposeEmail email = new ComposeEmail();
        List<String> addressesTo = new ArrayList<>();
        addressesTo.add(customerEmail);

        email.setFrom("customerservice@mshengutoilethire.co.za");
        email.setPassword("Mth@cust123");
        email.setAddressesTo(addressesTo);
        email.setSubject(subject);
        email.setEmailBody(messageBody);
        EmailUtil.sendSimpleEmail(email);

    }

    private String getSiteName(String siteId) {
        if (siteId != null) {
            Site site = SiteFacade.getSiteService().findById(siteId);
            if (site != null) {
                return site.getName();
            }
        }
        return "";

    }

    private String getCustomerName(String customerId) {
        if (customerId != null) {
            Customer customer = CustomerFacade.getCustomerService().findById(customerId);
            if (customer != null) {
                return customer.getName();
            }
        }
        return "";

    }
}

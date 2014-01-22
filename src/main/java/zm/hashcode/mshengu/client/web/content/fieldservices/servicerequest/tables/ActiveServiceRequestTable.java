/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.facade.customer.ServiceRequestFacade;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.views.ActiveServiceRequestTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.views.ServiceRequestFollowUpTab;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public class ActiveServiceRequestTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public ActiveServiceRequestTable(final MshenguMain main, final ActiveServiceRequestTab tab) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Ref Number", String.class, null);
        addContainerProperty("Request Date", String.class, null);
        addContainerProperty("Customer", String.class, null);
        addContainerProperty("Site", String.class, null);
        addContainerProperty("Contact Person", String.class, null);
        addContainerProperty("Contact Number", String.class, null);
//        addContainerProperty("Cell Number", String.class, null);
//        addContainerProperty("Email", String.class, null);
        addContainerProperty("Request Type", String.class, null);
        addContainerProperty("Status ", String.class, null);
        addContainerProperty("Follow Up", Button.class, null);

        // Add Data Columns
        List<ServiceRequest> serviceRequestList = ServiceRequestFacade.getServiceRequestService().findAllOpen();
        for (ServiceRequest serviceRequest : serviceRequestList) {
            if (!serviceRequest.isClosed()) {
                Button followUpButton = new Button("Follow up");
                followUpButton.setStyleName(Reindeer.BUTTON_LINK);
                followUpButton.setData(serviceRequest.getId());
                followUpButton.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        String incidentId = (String) event.getButton().getData();
                        ServiceRequestFollowUpTab form = new ServiceRequestFollowUpTab(main, incidentId);
                        tab.removeAllComponents();
                        tab.addComponent(form);
                    }
                });
                String customerName = getCustomerName(serviceRequest.getCustomerId());
                String siteName = getSiteName(serviceRequest.getSiteId());
                addItem(new Object[]{serviceRequest.getRefNumber(),
                            formatHelper.getDayMonthYear(serviceRequest.getRequestDate()),
                            customerName,
                            siteName,
                            serviceRequest.getContactPersonName(),
                            serviceRequest.getContactPersonMainNumber(),
                            //                    serviceRequest.getContactPersonAlternativeNumber(),
                            //                    serviceRequest.getContactPersonEmail(),
                            //                    formatHelper.getDayMonthYear(serviceRequest.getDeliveryDate()),
                                                    serviceRequest.getServiceRequestTypeName(),
                            serviceRequest.getLastUserActionStatusName(), //                        iconHelper.getCheckOrBlank(serviceRequest.isClosed()),
                            followUpButton,}, serviceRequest.getId());
            }
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }

    private String getCustomerName(String customerId) {
        Customer customer = CustomerFacade.getCustomerService().findById(customerId);
        if (customer != null) {
            return customer.getName();
        } else {
            return "";
        }
    }

    private String getSiteName(String siteId) {
        if (siteId != null) {
            Site site = SiteFacade.getSiteService().findById(siteId);
            if (site != null) {
                return site.getName();
            } else {
                return "";
            }
        }else{
            return "";
        }
    }
}
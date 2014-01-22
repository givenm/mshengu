/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.customer.ServiceRequestTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.customer.ServiceRequestType;

/**
 *
 * @author Ferox
 */
public class ServiceRequestTypeTable extends Table {

    private final MshenguMain main;

    public ServiceRequestTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Service Request Type", String.class, null);


        // Add Data Columns
        List<ServiceRequestType> serviceRequestTypeList = ServiceRequestTypeFacade.getServiceRequestTypeService().findAll();
        for (ServiceRequestType serviceRequestType : serviceRequestTypeList) {
            addItem(new Object[]{serviceRequestType.getName(),}, serviceRequestType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
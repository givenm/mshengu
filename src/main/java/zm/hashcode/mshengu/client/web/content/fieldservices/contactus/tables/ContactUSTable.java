/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.contactus.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import zm.hashcode.mshengu.app.facade.external.ContactUSFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.views.ActiveContactUSTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.views.ContactUSFollowUpTab;
import zm.hashcode.mshengu.domain.external.ContactUS;

/**
 *
 * @author Ferox
 */
public class ContactUSTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public ContactUSTable(final MshenguMain main, final ActiveContactUSTab tab) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Ref Number", String.class, null);
        addContainerProperty("Contact Date", String.class, null);
//        addContainerProperty("Contact Time", String.class, null);
        addContainerProperty("Firstname", String.class, null);
        addContainerProperty("Lastname", String.class, null);
        addContainerProperty("Company", String.class, null);
        addContainerProperty("Contact Number", String.class, null);
        addContainerProperty("Email", String.class, null);
//        addContainerProperty("Fax ", String.class, null);
        addContainerProperty("Status ", String.class, null);
        addContainerProperty("Follow Up ", Button.class, null);
//        addContainerProperty("Closed ", Embedded.class, null);

        // Add Data Columns
        List<ContactUS> contactUSList = ContactUSFacade.getContactUSService().findAllOpen();
        for (ContactUS contactUS : contactUSList) {
                  Button followUpButton = new Button("Follow up");
                followUpButton.setStyleName(Reindeer.BUTTON_LINK);
                followUpButton.setData(contactUS.getId());
                followUpButton.addClickListener(new Button.ClickListener() {  
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        String contactUSId = (String) event.getButton().getData();
                        ContactUSFollowUpTab form = new ContactUSFollowUpTab(main, contactUSId);
                        tab.removeAllComponents();
                        tab.addComponent(form);
                    }
                });
                addItem(new Object[]{contactUS.getRefNumber(),
                            formatHelper.getDayMonthYear(contactUS.getDateOfAction()),
                            //                        formatHelper.getHourMinute(contactUS.getDateOfAction()),
                            contactUS.getContactPersonFirstname(),
                            contactUS.getContactPersonLastname(),
                            contactUS.getCompany(),
                            contactUS.getPhone(),
                            contactUS.getEmail(),
                            //                        contactUS.getFaxNumber(),
                            contactUS.getLastUserActionStatusName(), //                        iconHelper.getCheckOrBlank(contactUS.isClosed()),
                            followUpButton,
                        }, contactUS.getId());
            
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
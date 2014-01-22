/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.incidents.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import zm.hashcode.mshengu.app.facade.incident.IncidentFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.views.ActiveIncidentsTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.views.IncidentFollowUpTab;
import zm.hashcode.mshengu.domain.incident.Incident;

/**
 *
 * @author Ferox
 */
public class IncidentTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private UITableIconHelper iconHelper = new UITableIconHelper();
    private InnactiveIncidentTable tab;
    private final ActiveIncidentsTab activeIncidentsTab;

    public IncidentTable(final MshenguMain main, final ActiveIncidentsTab tab) {
        this.main = main;
        this.activeIncidentsTab = tab;
        setSizeFull();

        addContainerProperty("Ref Number", String.class, null);
        addContainerProperty("Reported On", String.class, null);
//        addContainerProperty("Report Time", String.class, null);
        addContainerProperty("Customer", String.class, null);
        addContainerProperty("Contact Person", String.class, null);
        addContainerProperty("Contact Number", String.class, null);
        addContainerProperty("Site", String.class, null);
        addContainerProperty("Incident Type", String.class, null);
        addContainerProperty("Staus ", String.class, null);
        addContainerProperty("Follow Up", Button.class, null);
//        addContainerProperty("Closed ", Embedded.class, null);

        // Add Data Columns
        List<Incident> incidentList = IncidentFacade.getIncidentService().findAllOpen();
        for (final Incident incident : incidentList) {

            Button followUpButton = new Button("Follow up");
            followUpButton.setStyleName(Reindeer.BUTTON_LINK);
            followUpButton.setData(incident.getId());
            followUpButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    String incidentId = (String) event.getButton().getData();
                    IncidentFollowUpTab form = new IncidentFollowUpTab(main, incidentId );
                    activeIncidentsTab.removeAllComponents();
                    activeIncidentsTab.addComponent(form);
                    
                }
            });

            addItem(new Object[]{incident.getRefNumber(),
                formatHelper.getDayMonthYear(incident.getActionDate()),
                //                        formatHelper.getHourMinute(incident.getActionDate()),
                incident.getCustomer(),
                incident.getContactPerson(),
                incident.getContactNumber(),
                incident.getSite(),
                incident.getIncidentTypeName(),
                incident.getLastUserActionStatusName(),
                followUpButton//                        iconHelper.getCheckOrBlank(incident.isClosed()),
            }, incident.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
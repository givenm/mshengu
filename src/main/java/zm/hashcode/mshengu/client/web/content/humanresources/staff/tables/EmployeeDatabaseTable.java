/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.EmployeeDatabaseTab;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.tabs.EmployeeDetails;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class EmployeeDatabaseTable extends Table {

    private MshenguMain main;
    private UITableIconHelper iconHelper = new UITableIconHelper();
    private EmployeeDatabaseTab tab;

    public EmployeeDatabaseTable(final MshenguMain main, final EmployeeDatabaseTab tab) {
        tab.removeAllComponents();
        this.main = main;
        this.tab = tab;
        setSizeFull();

        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("Position", String.class, null);
        addContainerProperty("Cell Number", String.class, null);
        addContainerProperty("Enabled", Embedded.class, null);
        addContainerProperty("Profile", Button.class, null);


        List<Person> personlist = PersonFacade.getPersonService().findAllStaff();
        loadTable(personlist);
        // Allow selecting items from the table.

        performRowStyling();
        tab.addComponent(this);

        setNullSelectionAllowed(false);
//
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void performRowStyling() {
        // cell style generator
        setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {
                if (propertyId != null) {
                    return null; // Do not set individual cell styles
                }
                String rowId = ((String) itemId).toString();
                Person person = PersonFacade.getPersonService().findById(rowId);
                EmployeeDetail employeeDetail = person.getEmployeeDetails();
                if (employeeDetail.getTerminated() != null) {
                    return "yellowrow";
                }
                return null;
            }
        });

    }

    public final void loadTable(List<Person> personlist) {
        for (Person person : personlist) {
            Button showDetails = new Button("Details");
            showDetails.setData(person.getId());
            showDetails.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {

                    Person person = PersonFacade.getPersonService().findById((String) event.getButton().getData());
                    tab.contentPanel.removeAllComponents();
                    tab.headerBar.removeAllComponents();
                    tab.contentPanel.addComponent(new EmployeeDetails(main, person));

                }
            });

            showDetails.setStyleName(Reindeer.BUTTON_LINK);
            String position = "";
            String mainNumber = "";
            if (!StringUtils.isEmpty(person.getEmployeeDetails())) {
                EmployeeDetail employeeDetails = person.getEmployeeDetails();
                position = employeeDetails.getJobPositionName();
                mainNumber = employeeDetails.getMainNumber();
            }

            addItem(new Object[]{
                person.getFirstname(),
                person.getLastname(),
                position,
                mainNumber,
                iconHelper.getCheckOrCross(person.isEnable(), 16),
                showDetails,}, person.getId());
        }
    }
}

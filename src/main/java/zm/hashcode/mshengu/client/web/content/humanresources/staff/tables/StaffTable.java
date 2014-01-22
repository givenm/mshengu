/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.tables;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import java.util.List;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class StaffTable extends Table {

    private final MshenguMain main;
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public StaffTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("Position", String.class, null);
        addContainerProperty("Cell Number", String.class, null);
        addContainerProperty("Employment Status", String.class, null);
        addContainerProperty("Drives Company Car", Embedded.class, null);


        List<Person> personlist = PersonFacade.getPersonService().findAllStaff();
        for (Person person : personlist) {
            String position = "";
            String mainNumber = "";
            String empStatus = "";
            boolean drivesCompanyCar = false;
            if (!StringUtils.isEmpty(person.getEmployeeDetails())) {

                EmployeeDetail employeeDetails = person.getEmployeeDetails();
                position = employeeDetails.getJobPositionName();
                mainNumber = employeeDetails.getMainNumber();
                if (employeeDetails.getEmploymentStatusName() != null) {
                    empStatus = employeeDetails.getEmploymentStatusName();
                } else {
                    empStatus = employeeDetails.getTerminated();
                }

                drivesCompanyCar = employeeDetails.isDrivesCompanyCar();
            }

            addItem(new Object[]{
                person.getFirstname(),
                person.getLastname(),
                position,
                mainNumber,
                empStatus,
                iconHelper.getCheckOrBlank(drivesCompanyCar)}, person.getId());
        }
        performRowStyling();
        // Allow selecting items from the table.
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
}

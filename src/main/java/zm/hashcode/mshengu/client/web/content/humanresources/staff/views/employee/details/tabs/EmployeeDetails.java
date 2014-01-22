/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.tabs;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.forms.DemographicsWindow;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class EmployeeDetails extends VerticalLayout {

    private final MshenguMain main;
    private TabSheet tab;
    public final VerticalLayout demoLayout = new VerticalLayout();

    public EmployeeDetails(final MshenguMain main, Person person) {
        setSizeFull();
        this.main = main;
        demoLayout.addComponent(new DemographicsWindow(main, person, demoLayout));
        addComponent(demoLayout);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.Menu;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.StaffContactPersonTab;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.StaffTab;
import zm.hashcode.mshengu.client.web.content.humanresources.nationality.views.CountryTab;
import zm.hashcode.mshengu.client.web.content.humanresources.jobposition.views.JobPositionTab;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.EmployeeDatabaseTab;
import zm.hashcode.mshengu.client.web.content.setup.users.views.ResetTab;

/**
 *
 * @author boniface
 */
public class HRMenu extends Menu {

    private final MshenguMain main;
    private final TabSheet tab;

    public HRMenu(MshenguMain app, String selectedTab) {
        super(app, selectedTab);
        main = app;
        final VerticalLayout employeeDatabaseTab = new VerticalLayout();
        employeeDatabaseTab.setMargin(true);
        employeeDatabaseTab.addComponent(new EmployeeDatabaseTab(main));

        final VerticalLayout staffTab = new VerticalLayout();
        staffTab.setMargin(true);
        staffTab.addComponent(new StaffTab(main));

        final VerticalLayout staffContact = new VerticalLayout();
        staffContact.setMargin(true);
        staffContact.addComponent(new StaffContactPersonTab(main));

        final VerticalLayout resetTab = new VerticalLayout();
        resetTab.setMargin(true);
        resetTab.addComponent(new ResetTab(main));

        VerticalLayout countryTab = new VerticalLayout();
        countryTab.setMargin(true);
        countryTab.addComponent(new CountryTab(main));

        VerticalLayout jobPositionTab = new VerticalLayout();
        jobPositionTab.setMargin(true);
        jobPositionTab.addComponent(new JobPositionTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(employeeDatabaseTab, "Employee Database", null);
        tab.addTab(staffTab, "Add Employee", null);
        tab.addTab(staffContact, "Employee Next of Kin", null);
        tab.addTab(countryTab, "Setup Nationality", null);
        tab.addTab(jobPositionTab, "Setup Position", null);


        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(employeeDatabaseTab);
                break;
            case "MANAGE_EMP":
                tab.setSelectedTab(staffTab);
                break;
            case "CONTACT_PERSON":
                tab.setSelectedTab(staffContact);
                break;
            case "COUNTRY":
                tab.setSelectedTab(countryTab);
                break;
            case "OCCUPATIONS":
                tab.setSelectedTab(jobPositionTab);
                break;
        }

        addComponent(tab);

    }
}

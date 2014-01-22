/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.HRMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.tables.EmployeeDatabaseTable;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Ferox
 */
public final class EmployeeDatabaseTab extends VerticalLayout implements
        Button.ClickListener {

    private final MshenguMain main;
    private final EmployeeDatabaseTable table;
    public final HorizontalLayout headerBar = new HorizontalLayout();
    public final VerticalLayout contentPanel = new VerticalLayout();
    private final TextField peopleSearchBox = new TextField("Search an Employee By First Name");

    public EmployeeDatabaseTab(MshenguMain app) {
        main = app;
        contentPanel.setSizeFull();
        table = new EmployeeDatabaseTable(main, this);
        headerBar.setSizeFull();
        headerBar.addComponent(peopleSearchBox);
        headerBar.setComponentAlignment(peopleSearchBox, Alignment.MIDDLE_LEFT);

        peopleSearchBox.setWidth("400px");
        addComponent(headerBar);
        addComponent(new Label("<HR/>", ContentMode.HTML));
        contentPanel.removeAllComponents();
        contentPanel.addComponent(table);
        addComponent(contentPanel);

        peopleSearchBox.addShortcutListener(new ShortcutListener("Search an Employee By First Name", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                if (target == peopleSearchBox) {
                    if (!peopleSearchBox.getValue().isEmpty()) {
                        List<Person> personlist = PersonFacade.getPersonService().findAllStaff();
                        List<Person> list = new ArrayList<>();
                        for (Person person : personlist) {
                            if (person.getFirstname().toLowerCase().contains(peopleSearchBox.getValue().toLowerCase())) {
                                list.add(person);
                            }
                        }
                        table.removeAllItems();
                        table.loadTable(list);
                    } else {
                        List<Person> personlist = PersonFacade.getPersonService().findAllStaff();
                        table.loadTable(personlist);
                    }
                }
            }
        });
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

    }

    private void getHome() {
        main.content.setSecondComponent(new HRMenu(main, "LANDING"));
    }
}

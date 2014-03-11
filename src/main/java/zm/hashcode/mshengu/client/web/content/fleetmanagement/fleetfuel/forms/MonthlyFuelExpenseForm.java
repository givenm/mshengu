/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.forms;

import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 *
 * @author Colin
 */
public class MonthlyFuelExpenseForm extends FormLayout {

    public DateField startDate;
    public DateField endDate;

    public MonthlyFuelExpenseForm() {
        // UIComponent
        startDate = new DateField("Start Date");
        startDate.setWidth(150, Sizeable.Unit.PIXELS);
        startDate.setImmediate(true);
        startDate.setDateFormat("MMM-yyyy");

        endDate = new DateField("End Date");
        endDate.setWidth(150, Sizeable.Unit.PIXELS);
        endDate.setImmediate(true);
        endDate.setDateFormat("MMM-yyyy");

        Panel datePanel = new Panel("Custom Date Range Query");
//        datePanel.setSizeUndefined();
        datePanel.setWidth(300, Sizeable.Unit.PIXELS);
//        datePanel.addStyleName("default");

        FormLayout layout = new FormLayout();

        layout.addComponent(startDate);
        layout.addComponent(endDate);
//        content.setSizeUndefined(); // Shrink to fit
        layout.setMargin(true);

        datePanel.setContent(layout);

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(datePanel, 0, 0, 0, 1);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 2, 3, 2);

        addComponent(grid);

    }
}

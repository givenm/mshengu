/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.forms;

import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;

/**
 *
 * @author ColinWa
 */
public class DashBoardForm extends FormLayout {

    public DateField startDate;
    public DateField endDate;
    public OptionGroup optionGroup = new OptionGroup();

    public DashBoardForm() {
        // UIComponent
        startDate = new DateField("Start Date");
        startDate.setWidth(150, Sizeable.Unit.PIXELS);
        startDate.setImmediate(true);
        startDate.setDateFormat("MMM-yyyy");

        endDate = new DateField("End Date");
        endDate.setWidth(150, Sizeable.Unit.PIXELS);
        endDate.setImmediate(true);
        endDate.setDateFormat("MMM-yyyy");

        optionGroup.addItem("3-Monthly");
        optionGroup.addItem("6-Monthly");
        optionGroup.addItem("12-Monthly");
        optionGroup.setStyleName("optiongrouphorizontal");
        optionGroup.setImmediate(true);

        Panel periodicPanel = new Panel("Periodic");
//        datePanel.setSizeUndefined();
        periodicPanel.setWidth(400, Sizeable.Unit.PIXELS);
//        periodicPanel.addStyleName("default");



        Panel datePanel = new Panel("Custom");
//        datePanel.setSizeUndefined();
        datePanel.setWidth(300, Sizeable.Unit.PIXELS);
//        datePanel.addStyleName("default");
        FormLayout content = new FormLayout();


        content.addComponent(startDate);
        content.addComponent(endDate);
//        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(true);

        datePanel.setContent(content);
        periodicPanel.setContent(optionGroup);

        //// Use the multiple selection mode.
        //optiongroup.setMultiSelect(false);

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(datePanel, 0, 0, 0, 1);
        grid.addComponent(periodicPanel, 1, 0, 3, 0);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);

        addComponent(grid);
    }
}

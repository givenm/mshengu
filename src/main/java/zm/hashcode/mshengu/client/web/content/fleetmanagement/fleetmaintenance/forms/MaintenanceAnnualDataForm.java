/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.forms;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 *
 * @author Colin
 */
public class MaintenanceAnnualDataForm extends FormLayout {

    public Button generateButton = new Button("Show Annual Data");

    public MaintenanceAnnualDataForm() {
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();

        GridLayout grid = new GridLayout(3, 2);
        grid.setSizeFull();

        grid.addComponent(buttons, 0, 0, 2, 0);
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 1, 2, 1);
        addComponent(grid);
    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
//        generateButton.setSizeFull();
        generateButton.setStyleName("default");
        buttons.addComponent(generateButton);

        return buttons;
    }
}

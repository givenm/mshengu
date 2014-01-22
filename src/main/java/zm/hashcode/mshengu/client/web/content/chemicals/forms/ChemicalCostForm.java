/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.chemicals.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import zm.hashcode.mshengu.client.web.content.chemicals.models.ChemicalCostBean;

/**
 *
 * @author geek
 */
public class ChemicalCostForm extends FormLayout {

    private final ChemicalCostBean bean;
    public final BeanItem<ChemicalCostBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
    //
    public DateField startDate;
    public DateField endDate;

    public ChemicalCostForm() {
        bean = new ChemicalCostBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        startDate = new DateField("Start Date");
        startDate.setWidth(250, Sizeable.Unit.PIXELS);
        startDate.setImmediate(true);
        startDate.setDateFormat("MMM-yyyy");

        endDate = new DateField("End Date");
        endDate.setWidth(250, Sizeable.Unit.PIXELS);
        endDate.setImmediate(true);
        endDate.setDateFormat("MMM-yyyy");

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(startDate, 0, 0);
        grid.addComponent(endDate, 1, 0);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);

    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        edit.setSizeFull();
        cancel.setSizeFull();
        update.setSizeFull();
        delete.setSizeFull();

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }
}

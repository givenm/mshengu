/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.clientlogin.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.client.web.content.setup.clientlogin.models.UnitServiceLogBean2;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;

/**
 *
 * @author Ferox
 */
public class UnitServiceLogForm2 extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final UnitServiceLogBean2 bean;
    public final BeanItem<UnitServiceLogBean2> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public UnitServiceLogForm2() {
        bean = new UnitServiceLogBean2();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent;
        DateField serviceDate = UIComponent.getDateField("Service Date :", "serviceDate", UnitServiceLogBean2.class, binder);
        DateField serviceTime = UIComponent.getDateField("Service Time :", "serviceTime", UnitServiceLogBean2.class, binder);

        TextField statusMessage = UIComponent.getTextField("Status Message:", "statusMessage", UnitServiceLogBean2.class, binder);
        ComboBox servicedBy = UIComboBox.getDriversComboBox("Longitude", "servicedBy", UnitServiceLogBean2.class, binder);
        TextField pumpOutText = UIComponent.getTextField("Pump Out:", "pumpOutText", UnitServiceLogBean2.class, binder);
        TextField suctionOutText = UIComponent.getTextField("Suction Out:", "suctionOutText", UnitServiceLogBean2.class, binder);
        TextArea washBucketText = UIComponent.getTextArea("Wash Bucket:", "washBucketText", UnitServiceLogBean2.class, binder);
        TextField scrubFloorText = UIComponent.getTextField("Scrub Floor:", "scrubFloorText", UnitServiceLogBean2.class, binder);
        TextArea rechargeBacketText = UIComponent.getTextArea("Recharge Backet:", "rechargeBacketText", UnitServiceLogBean2.class, binder);
        TextField cleanPerimeterText = UIComponent.getTextField("Clean Perimeter:", "cleanPerimeterText", UnitServiceLogBean2.class, binder);
        TextField incident = UIComponent.getTextField("Incident:", "incident", UnitServiceLogBean2.class, binder);
        
        GridLayout grid = new GridLayout(4, 10);

        grid.setSizeFull();

        grid.addComponent(serviceDate, 0, 0);
        grid.addComponent(serviceTime, 1, 0);
        grid.addComponent(servicedBy, 2, 0);

        
        grid.addComponent(pumpOutText, 0, 1);
        grid.addComponent(washBucketText, 1, 1);
        grid.addComponent(suctionOutText, 2, 1);
        
        grid.addComponent(scrubFloorText, 0, 2);
        grid.addComponent(rechargeBacketText, 1, 2);
        grid.addComponent(cleanPerimeterText, 2, 2);
        
        grid.addComponent(statusMessage, 0, 3);
        grid.addComponent(incident, 1, 3);
        // grid.addComponent(, 2, 1);

        
        grid.addComponent(
                new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
//        grid.addComponent(buttons,  0, 5, 2, 5);

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.forms;

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
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.models.SiteUnitSiteUnitServiceLogBean;

/**
 *
 * @author Ferox
 */
public class SiteUnitSiteUnitServiceLogForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final SiteUnitSiteUnitServiceLogBean bean;
    public final BeanItem<SiteUnitSiteUnitServiceLogBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public SiteUnitSiteUnitServiceLogForm() {
        bean = new SiteUnitSiteUnitServiceLogBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent;
        DateField serviceDate = UIComponent.getDateField("Service Date :", "serviceDate", SiteUnitSiteUnitServiceLogBean.class, binder);
        DateField serviceTime = UIComponent.getDateField("Service Time :", "serviceTime", SiteUnitSiteUnitServiceLogBean.class, binder);

        TextField statusMessage = UIComponent.getTextField("Status Message:", "statusMessage", SiteUnitSiteUnitServiceLogBean.class, binder);
        ComboBox servicedBy = UIComboBox.getDriversComboBox("Longitude", "servicedBy", SiteUnitSiteUnitServiceLogBean.class, binder);
        TextField pumpOutText = UIComponent.getTextField("Pump Out:", "pumpOutText", SiteUnitSiteUnitServiceLogBean.class, binder);
        TextField suctionOutText = UIComponent.getTextField("Suction Out:", "suctionOutText", SiteUnitSiteUnitServiceLogBean.class, binder);
        TextArea washBucketText = UIComponent.getTextArea("Wash Bucket:", "washBucketText", SiteUnitSiteUnitServiceLogBean.class, binder);
        TextField scrubFloorText = UIComponent.getTextField("Scrub Floor:", "scrubFloorText", SiteUnitSiteUnitServiceLogBean.class, binder);
        TextArea rechargeBacketText = UIComponent.getTextArea("Recharge Backet:", "rechargeBacketText", SiteUnitSiteUnitServiceLogBean.class, binder);
        TextField cleanPerimeterText = UIComponent.getTextField("Clean Perimeter:", "cleanPerimeterText", SiteUnitSiteUnitServiceLogBean.class, binder);
        TextField incident = UIComponent.getTextField("Incident:", "incident", SiteUnitSiteUnitServiceLogBean.class, binder);
        
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
        
        save.setStyleName("default");
        edit.setStyleName("default");
        cancel.setStyleName("default");
        update.setStyleName("default");
        delete.setStyleName("default");
        

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }
}

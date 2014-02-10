/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.models.SiteUnitLocationLifeCycleBean;

/**
 *
 * @author Ferox
 */
public class SiteUnitLyfeCycleForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private final SiteUnitLocationLifeCycleBean bean;
    public final BeanItem<SiteUnitLocationLifeCycleBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public SiteUnitLyfeCycleForm() {
        bean = new SiteUnitLocationLifeCycleBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        DateField dateofAction = UIComponent.getDateField("Date of Action :", "dateofAction", SiteUnitLocationLifeCycleBean.class, binder);
        TextField latitude = UIComponent.getTextField("Latitude", "latitude", SiteUnitLocationLifeCycleBean.class, binder);
        TextField longitude = UIComponent.getTextField("Longitude", "longitude", SiteUnitLocationLifeCycleBean.class, binder);


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(dateofAction, 0, 0);
        grid.addComponent(latitude, 1, 0);
        grid.addComponent(longitude, 2, 0);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
//        grid.addComponent(buttons, 0, 5, 2, 5);

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

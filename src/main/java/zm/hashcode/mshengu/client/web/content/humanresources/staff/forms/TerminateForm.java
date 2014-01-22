/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.forms;

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
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.models.StaffDetailsBean;

/**
 *
 * @author Luckbliss
 */
public class TerminateForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private UIComponentHelper UIComponent = new UIComponentHelper();
    private final StaffDetailsBean bean = new StaffDetailsBean();
    public final BeanItem<StaffDetailsBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    // Define Buttons
    public Button save = new Button("Save");
    public Button cancel = new Button("Cancel");
    public ComboBox terminateReason = null;
    public DateField endDate = null;

    public TerminateForm() {
        terminateReason = UIComboBox.getTerminateReasonsAndCodes("Terminating Reason: ", "terminateReason", StaffDetailsBean.class, binder);
        endDate = UIComponent.getDateField("Leave End Date :", "endDate", StaffDetailsBean.class, binder);

        GridLayout grid = new GridLayout(3, 10);
        grid.setSizeFull();

        grid.addComponent(terminateReason, 0, 0);
        grid.addComponent(endDate, 1, 0);

        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();

        grid.addComponent(new Label("<br>", ContentMode.HTML), 0, 1, 2, 1);
        grid.addComponent(buttons, 0, 2, 2, 2);

        addComponent(grid);
    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        cancel.setSizeFull();
        buttons.addComponent(save);
        buttons.addComponent(cancel);
        return buttons;
    }
}

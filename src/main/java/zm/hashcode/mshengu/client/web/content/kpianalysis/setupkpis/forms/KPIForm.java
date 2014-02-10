/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.models.KPIBean;
import zm.hashcode.mshengu.domain.kpianalysis.KPA;

/**
 *
 * @author Luckbliss
 */
public class KPIForm extends FormLayout {

    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final KPIBean bean = new KPIBean();
    public final BeanItem<KPIBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public TextField name;
    private MshenguMain main;
    public Button edit = new Button("Edit KPA Title");
    public Button update = new Button("Update KPA Title");
    public Button cancel = new Button("Cancel");
    public Label message = new Label("Non-financial metrics have been defined for this KPA");
    public String msg = message.getValue();

    public KPIForm(MshenguMain main, KPA kpi) {
        setSizeFull();
        this.main = main;

        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();

        name = UIComponent.getTextField("KPA Title: ", "name", KPIBean.class, binder);
        name.setValue(kpiName(kpi));
        name.setReadOnly(true);

        GridLayout layout = new GridLayout(3, 10);
        layout.setSizeFull();


        layout.addComponent(message, 0, 0);

        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1, 2, 1);

        layout.addComponent(name, 0, 2);

        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3, 2, 3);

//        layout.addComponent(buttons, 0, 4, 2, 4);

        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 5, 2, 5);

        addComponent(layout);

        setVisibleToFalse();
    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        edit.setSizeFull();
        update.setSizeFull();
        cancel.setSizeFull();

        buttons.addComponent(edit);
        buttons.addComponent(update);
        buttons.addComponent(cancel);
        return buttons;
    }

    private void setVisibleToFalse() {
        update.setVisible(false);
    }

    private String kpiName(KPA kpi) {
        return kpi.getName();
    }
}

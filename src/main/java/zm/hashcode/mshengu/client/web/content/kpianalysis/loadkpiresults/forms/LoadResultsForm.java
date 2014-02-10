/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.models.LoadResultsBean;

/**
 *
 * @author Luckbliss
 */
public class LoadResultsForm extends FormLayout {

    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final LoadResultsBean bean = new LoadResultsBean();
    public final BeanItem<LoadResultsBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public DateField fromdate = new DateField();
    public DateField todate = new DateField();
    public Label from = new Label("From:");
    public Label to = new Label("To:");

    public LoadResultsForm() {
        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 10);
        gridlayout.setSizeFull();
        fromdate = UIComponent.getDateField("Enter Start Date: ", "fromdate", LoadResultsBean.class, binder);
        todate = UIComponent.getDateField("Enter End Date: ", "todate", LoadResultsBean.class, binder);

        gridlayout.addComponent(from, 0, 0);
        gridlayout.addComponent(fromdate, 1, 0);

        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);

        gridlayout.addComponent(to, 0, 2);
        gridlayout.addComponent(todate, 1, 2);
        
        addComponent(gridlayout);
    }
}

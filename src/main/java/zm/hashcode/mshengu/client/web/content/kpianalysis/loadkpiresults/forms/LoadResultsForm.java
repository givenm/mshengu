/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.models.LoadResultsBean;

/**
 *
 * @author Luckbliss
 */
public class LoadResultsForm extends FormLayout {

    public UIComboBoxHelper UICombobox = new UIComboBoxHelper();
    public final LoadResultsBean bean = new LoadResultsBean();
    public final BeanItem<LoadResultsBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public ComboBox frommonth = new ComboBox();
    public ComboBox tomonth = new ComboBox();
    public ComboBox toyear = new ComboBox();
    public ComboBox fromyear = new ComboBox();
    public Label from = new Label("From:");
    public Label to = new Label("To:");

    public LoadResultsForm() {
        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 10);
        gridlayout.setSizeFull();
        frommonth = UICombobox.getMonthComboBox("Month: ", "frommonth", LoadResultsBean.class, binder);
        tomonth = UICombobox.getMonthComboBox("Month: ", "tomonth", LoadResultsBean.class, binder);
        
        fromyear = UICombobox.getYearComboBox("Year: ", "fromyear", LoadResultsBean.class, binder);
        toyear = UICombobox.getYearComboBox("Year: ", "toyear", LoadResultsBean.class, binder);

        gridlayout.addComponent(from, 0, 0);
        gridlayout.addComponent(frommonth, 1, 0);
        gridlayout.addComponent(fromyear, 2, 0);

        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        
        gridlayout.addComponent(to, 0, 2);
        gridlayout.addComponent(tomonth, 1, 2);
        gridlayout.addComponent(toyear, 2, 2);
        
        addComponent(gridlayout);
    }
}

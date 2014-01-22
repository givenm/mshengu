/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.models.InvoiceBean;

/**
 *
 * @author Luckbliss
 */
public class InvoicesForm extends FormLayout {

    public UIComboBoxHelper UICombobox = new UIComboBoxHelper();
    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final InvoiceBean bean = new InvoiceBean();
    public final BeanItem<InvoiceBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public ComboBox supplier = new ComboBox();
    public ComboBox month = new ComboBox();
    public ComboBox year = new ComboBox();
    public Label mtdTotal = new Label("MTD Total: R ");
    public String total = mtdTotal.getValue();

    public InvoicesForm() {
        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 10);
        gridlayout.setSizeFull();
        supplier = UICombobox.getSuppliersComboBox("Select Supplier: ", "supplier", InvoiceBean.class, binder);
        month = UICombobox.getMonthComboBox("Month: ", "month", InvoiceBean.class, binder);
        year = UICombobox.getYearComboBox("Year: ", "year", InvoiceBean.class, binder);

        gridlayout.addComponent(supplier, 0, 0);
        gridlayout.addComponent(month, 1, 0);
        gridlayout.addComponent(year, 2, 0);
        
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);

        gridlayout.addComponent(mtdTotal, 0, 2);

        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3);

        addComponent(gridlayout);
    }
}

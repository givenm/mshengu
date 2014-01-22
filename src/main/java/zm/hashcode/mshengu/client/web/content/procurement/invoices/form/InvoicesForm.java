/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.models.InvoiceBean;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.table.InvoiceTable;

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

    public InvoicesForm() {
        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 10);
        gridlayout.setSizeFull();
        ComboBox supplier = UICombobox.getSuppliersComboBox("Select Supplier: ", "supplier", InvoiceBean.class, binder);
        ComboBox month = UICombobox.getMonthComboBox("Month: ", "month", InvoiceBean.class, binder);
        ComboBox year = UICombobox.getYearComboBox("Year: ", "year", InvoiceBean.class, binder);

        gridlayout.addComponent(supplier, 0, 0);
        gridlayout.addComponent(month, 1, 0);
        gridlayout.addComponent(year, 2, 0);

        InvoiceTable table = new InvoiceTable();

        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        gridlayout.addComponent(table, 0, 2, 2, 2);

        TextField amountDue = UIComponent.getTextField("Enter amount due: ", "amountDue", InvoiceBean.class, binder);

        Button check = new Button("Compare Amounts");
        check.setSizeFull();
        
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3);
        gridlayout.addComponent(amountDue, 0, 4);
        gridlayout.addComponent(check, 1, 4, 2, 4);

        addComponent(gridlayout);
    }
}

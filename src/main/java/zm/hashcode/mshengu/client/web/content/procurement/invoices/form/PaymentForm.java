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
import com.vaadin.ui.Table;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.models.InvoiceBean;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.table.PaymentTable;

/**
 *
 * @author Luckbliss
 */
public class PaymentForm extends FormLayout {

    public UIComboBoxHelper UICombobox = new UIComboBoxHelper();
    public final InvoiceBean bean = new InvoiceBean();
    public final BeanItem<InvoiceBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);

    public PaymentForm() {
        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 10);
        gridlayout.setSizeFull();
        ComboBox month = UICombobox.getMonthComboBox("Month: ", "month", InvoiceBean.class, binder);
        ComboBox year = UICombobox.getYearComboBox("Year: ", "year", InvoiceBean.class, binder);

        gridlayout.addComponent(month, 0, 0);
        gridlayout.addComponent(year, 1, 0);

        PaymentTable table = new PaymentTable();

        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        gridlayout.addComponent(table, 0, 2, 2, 2);

        addComponent(gridlayout);
    }
}

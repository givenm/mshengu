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
import java.text.SimpleDateFormat;
import java.util.Date;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.models.InvoiceBean;

/**
 *
 * @author Luckbliss
 */
public class CostCentreForm extends FormLayout {

    public UIComboBoxHelper UICombobox = new UIComboBoxHelper();
    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final InvoiceBean bean = new InvoiceBean();
    public final BeanItem<InvoiceBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public ComboBox costcentre = new ComboBox();
    public ComboBox month = new ComboBox();
    public ComboBox year = new ComboBox();
    public Label mtdTotal = new Label("MTD Total Outstanding: R ");
    public Label currentdate = new Label("Current Month");
    public String total = mtdTotal.getValue();

    public CostCentreForm() {
        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 10);
        gridlayout.setSizeFull();
        costcentre = UICombobox.getCostCentreType("Cost Centre Type: ", "supplier", InvoiceBean.class, binder);
        month = UICombobox.getMonthComboBox("Month: ", "month", InvoiceBean.class, binder);
        year = UICombobox.getYearComboBox("Year: ", "year", InvoiceBean.class, binder);

        gridlayout.addComponent(costcentre, 0, 0);
        gridlayout.addComponent(month, 1, 0);
        gridlayout.addComponent(year, 2, 0);

        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);

        gridlayout.addComponent(mtdTotal, 0, 2);
        
        String datemonth = new SimpleDateFormat("MMMM").format(new Date());
        String dateyear = new SimpleDateFormat("YYYY").format(new Date());
        
        currentdate.setValue(datemonth + " " + dateyear);
        currentdate.setStyleName("errorstyle");
        
        gridlayout.addComponent(currentdate, 2, 2);

        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3);

        addComponent(gridlayout);
    }
}
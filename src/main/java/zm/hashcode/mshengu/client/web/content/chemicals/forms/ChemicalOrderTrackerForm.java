/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.chemicals.forms;

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
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.chemicals.models.ChemicalOrderTrackerBean;

/**
 *
 * @author geek
 */
public class ChemicalOrderTrackerForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final ChemicalOrderTrackerBean bean;
    public final BeanItem<ChemicalOrderTrackerBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
    //
    public ComboBox productId;
    public ComboBox productCategoryId;
    public ComboBox supplierId;

    public ChemicalOrderTrackerForm() {
        bean = new ChemicalOrderTrackerBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        DateField transactionDate = UIComponent.getDateField("Transaction Date", "transactionDate", ChemicalOrderTrackerBean.class, binder);
        TextField invoiceNumber = UIComponent.getTextField("Invoice Number:", "invoiceNumber", ChemicalOrderTrackerBean.class, binder);
//        productId = UIComboBox.getProductComboBox("Product:", "productId", ChemicalOrderTrackerBean.class, binder);
//        productCategoryId = UIComboBox.getProductCategoryComboBox("Product Category:", "productCategoryId", ChemicalOrderTrackerBean.class, binder);
//        supplierId = UIComboBox.getSupplierComboBox("Supplier:", "supplierId", ChemicalOrderTrackerBean.class, binder);
        TextField qtyOrdered = UIComponent.getTextField("Oil Litres:", "oilLitres", ChemicalOrderTrackerBean.class, binder);

        TextField volume = UIComponent.getTextField("Volume :", "volume", ChemicalOrderTrackerBean.class, binder);
        TextField unitPrice = UIComponent.getTextField("Unit Price (excl. VAT) :", "unitPrice", ChemicalOrderTrackerBean.class, binder);
        TextField totalPrice = UIComponent.getBigDecimalTextField("Total Price (excl. VAT) :", "totalPrice", ChemicalOrderTrackerBean.class, binder);

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(transactionDate, 0, 0);
        grid.addComponent(invoiceNumber, 1, 0);

        grid.addComponent(productId, 0, 1);
        grid.addComponent(productCategoryId, 1, 1);
        grid.addComponent(supplierId, 2, 1);

        grid.addComponent(qtyOrdered, 0, 2);
        grid.addComponent(volume, 1, 2);
        grid.addComponent(unitPrice, 2, 2);

        grid.addComponent(totalPrice, 0, 3);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);

    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        edit.setSizeFull();
        cancel.setSizeFull();
        update.setSizeFull();
        delete.setSizeFull();

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }
}

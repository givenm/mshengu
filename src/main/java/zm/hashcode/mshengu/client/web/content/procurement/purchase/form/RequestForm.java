/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.form;

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
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.app.util.SequenceHelper;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.models.RequestBean;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Luckbliss
 */
public class RequestForm extends FormLayout {

    private MshenguMain main;
    public final RequestBean bean = new RequestBean();
    public final BeanItem<RequestBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public Button save = new Button("Add Item To Request");
    public Button approval = new Button("Send Request For Approval");
    public UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    public UIComponentHelper UIComponent = new UIComponentHelper();
    public ComboBox name = new ComboBox();
    public ComboBox itemDescription = new ComboBox();
    public TextField address = new TextField();
    public TextField postalCode = new TextField();
    public TextField number = new TextField();
    public TextField itemNumber = new TextField();
    public TextField unit = new TextField();
    public TextField volume = new TextField();
    public TextField unitPrice = new TextField();
    public TextField subTotal = new TextField();
    public TextField quantity = new TextField();
    public TextField id = new TextField();
    public ComboBox personRequesting = new ComboBox();
    public ComboBox costCentre = new ComboBox();
    public ComboBox costCategory = new ComboBox();
    public ComboBox itemCategory = new ComboBox();
    public TextField ordernumber = new TextField();
    public TextField vat = new TextField();
    public TextField total = new TextField();
    public GridLayout itemPurchaseLayout = new GridLayout(3, 9);
    public TextField description = new TextField();
    private SequenceHelper sequenceHelper = new SequenceHelper();

    public RequestForm(final MshenguMain main) {
        this.main = main;

        setSizeFull();
        GridLayout generalPanel = new GridLayout(3, 10);
        generalPanel.setSizeFull();
        
//        Sequence sequence = SequenceFacade.getSequenceListService().findByName("PURCHASE_REQUEST");
//        String orderNum  = sequenceHelper.getSequenceInitialNumber(sequence);
        Label requesterInfo = new Label("Requester Information");        
        requesterInfo.addStyleName("h4");
        ordernumber = UIComponent.getTextField("Purchase Order Number:", "orderNumber", RequestBean.class, binder);
//        ordernumber.setValue(orderNum);
        personRequesting = UIComboBox.getRequestingPersonComboBox("Person Requesting Item(s):", "requestingPerson", RequestBean.class, binder);
        costCentre = UIComboBox.getCostCentreType("Cost Centre Type:", "costCentre", RequestBean.class, binder);
        costCategory = UIComboBox.getCostCentreCategoryType("Cost Category Type:", "costCategory", RequestBean.class, binder);
        itemCategory = UIComboBox.getCostCentreCategoryType("Item Category Type:", "itemCategory", RequestBean.class, binder);
        TextArea deliveryInstructions = UIComponent.getTextArea("Delivery Instructions:", "deliveryInstructions", RequestBean.class, binder);
        DateField orderDate = UIComponent.getDateField("Delivery Date:", "orderDate", RequestBean.class, binder);
        generalPanel.addComponent(requesterInfo, 0, 0);
        generalPanel.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        generalPanel.addComponent(ordernumber, 0, 2);
        generalPanel.addComponent(personRequesting, 1, 2);
        generalPanel.addComponent(costCentre, 0, 3);
        generalPanel.addComponent(costCategory, 1, 3);
        generalPanel.addComponent(itemCategory, 2, 3);
        deliveryInstructions.setSizeFull();
        generalPanel.addComponent(deliveryInstructions, 0, 4, 1, 4);
        generalPanel.addComponent(orderDate, 0, 5);

        GridLayout vendorPanel = new GridLayout(3, 5);
        vendorPanel.setSizeFull();
        Label vendorInfo = new Label("Vendor Information");
        vendorInfo.addStyleName("h4");
        name = UIComboBox.getVendorsComboBox("Vendor:", "companyName", RequestBean.class, binder);
        address = UIComponent.getTextField("Address:", "address", RequestBean.class, binder);
        postalCode = UIComponent.getTextField("Suburb/Postal Code:", "postalCode", RequestBean.class, binder);
        number = UIComponent.getTextField("Phone Number:", "phoneNumber", RequestBean.class, binder);

        vendorPanel.addComponent(new Label("<br>", ContentMode.HTML), 1, 0);
        vendorPanel.addComponent(vendorInfo, 0, 1);
        vendorPanel.addComponent(new Label("<br>", ContentMode.HTML), 0, 2);
        vendorPanel.addComponent(name, 0, 3);
        vendorPanel.addComponent(address, 1, 3);
        vendorPanel.addComponent(postalCode, 2, 3);
        vendorPanel.addComponent(number, 0, 4);

        GridLayout layout = new GridLayout(1, 2);
        layout.setSizeFull();
        layout.addComponent(generalPanel, 0, 0);
        layout.addComponent(vendorPanel, 0, 1);
        Panel panel = new Panel();
        panel.setContent(layout);
        panel.setSizeFull();

        itemPurchaseLayout.setSizeFull();

        Label details = new Label("Items Information");        
        details.addStyleName("h4");
        description = UIComponent.getTextField("Item Description:", "description", RequestBean.class, binder);
        itemDescription = UIComboBox.getProductDescriptionComboBox("Item Description:", "itemDescription", RequestBean.class, binder);
        itemNumber = UIComponent.getTextField("Item Number:", "itemNumber", RequestBean.class, binder);
        quantity = UIComponent.getTextField("Quantity:", "quantity", RequestBean.class, binder);
        unit = UIComponent.getTextField("Unit:", "unit", RequestBean.class, binder);
        volume = UIComponent.getTextField("Volume:", "volume", RequestBean.class, binder);
        unitPrice = UIComponent.getBigDecimalTextField("Unit Price:", "unitPrice", RequestBean.class, binder);
        subTotal = UIComponent.getBigDecimalTextField("Sub Total: R", "subTotal", RequestBean.class, binder);
        vat = UIComponent.getTextField("VAT (%)", "vat", RequestBean.class, binder);
        vat.setValue("14");
        total = UIComponent.getBigDecimalTextField("Total: R", "total", RequestBean.class, binder);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        save.setSizeFull();
        buttons.addComponent(save);
        approval.setVisible(false);
        approval.setSizeFull();
        buttons.addComponent(approval);

        itemPurchaseLayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 0);
        itemPurchaseLayout.addComponent(details, 0, 1);
        itemPurchaseLayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 2);
        itemPurchaseLayout.addComponent(itemDescription, 0, 3);
        itemPurchaseLayout.addComponent(itemNumber, 1, 3);
        itemPurchaseLayout.addComponent(unitPrice, 2, 3);

        itemPurchaseLayout.addComponent(unit, 0, 4);
        itemPurchaseLayout.addComponent(volume, 1, 4);
        itemPurchaseLayout.addComponent(quantity, 2, 4);

        itemPurchaseLayout.addComponent(subTotal, 0, 5);
        itemPurchaseLayout.addComponent(vat, 1, 5);

        itemPurchaseLayout.addComponent(total, 2, 5);
        itemPurchaseLayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 6);
        itemPurchaseLayout.addComponent(buttons, 0, 7, 2, 7);
        itemPurchaseLayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 8);
        setReadOnly();
        addComponent(panel);
        addComponent(itemPurchaseLayout);
    }

    private void setReadOnly() {
        ordernumber.setReadOnly(true);
        address.setReadOnly(true);
        postalCode.setReadOnly(true);
        number.setReadOnly(true);
        itemNumber.setReadOnly(true);
        unit.setReadOnly(true);
        volume.setReadOnly(true);
        unitPrice.setReadOnly(true);
        subTotal.setReadOnly(true);
        vat.setReadOnly(true);
        total.setReadOnly(true);
    }
}

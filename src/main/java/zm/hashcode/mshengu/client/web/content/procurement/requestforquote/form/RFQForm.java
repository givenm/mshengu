/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form;

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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.app.util.SequenceHelper;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.models.RequestBean;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Luckbliss
 */
public class RFQForm extends FormLayout {

    public UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final RequestBean bean = new RequestBean();
    public final BeanItem<RequestBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public TextField rfqNumber = new TextField();
    public ComboBox personRequesting = new ComboBox();
    public TextField companyName = new TextField();
    public GridLayout itemPurchaseLayout = new GridLayout(3, 10);
    public Button save = new Button("Add Item To Request");
    public Button approval = new Button("Send RFQ");
    public TextField itemDescription = new TextField();
    public TextField unit = new TextField();
    public TextField volume = new TextField();
    public TextField quantity = new TextField();
    public TextArea instructions = new TextArea();
    public Label details = new Label("Items Information");
    private SequenceHelper helper = new SequenceHelper();
    public Label errorMessage;

    public RFQForm(final MshenguMain main) {
        setSizeFull();
        GridLayout grid = new GridLayout(3, 4);
        grid.setSizeFull();
        DateField deliveryDate = UIComponent.getDateField("Date:", "deliveryDate", RequestBean.class, binder);
        deliveryDate = UIValidatorHelper.setRequiredDateField(deliveryDate, "Date");
        
        rfqNumber = UIComponent.getTextField("RFQ Number:", "rfqNumber", RequestBean.class, binder);
        rfqNumber = UIValidatorHelper.setRequiredTextField(rfqNumber, "RFQ Number");

        Sequence sequence = SequenceFacade.getSequenceListService().findByName("MSHENGU_RFQ");
        rfqNumber.setValue(helper.getSequenceInitialNumber(sequence));
        rfqNumber.setReadOnly(true);
        
        personRequesting = UIComboBox.getRequestingPersonComboBox("Person Requesting Quote:", "requestingPerson", RequestBean.class, binder);
        personRequesting = UIValidatorHelper.setRequiredComboBox(personRequesting, "Person Requesting Quote");
        
        companyName = UIComponent.getTextField("Company:", "companyName", RequestBean.class, binder);
        companyName.setValue("Mshengu Toilet Hire");
        companyName.setReadOnly(true);
        companyName = UIValidatorHelper.setRequiredTextField(companyName, "Company");
        
        instructions = UIComponent.getTextArea("Notes/Instructions:", "deliveryInstructions", RequestBean.class, binder);
        instructions = UIValidatorHelper.setRequiredTextArea(instructions, "Notes/Instructions");

        DateField closingDate = UIComponent.getDateField("Closing Date:", "closingDate", RequestBean.class, binder);
        closingDate = UIValidatorHelper.setRequiredDateField(closingDate, "Closing Date");

        errorMessage = UIComponent.getErrorLabel();
        
        grid.addComponent(errorMessage, 0, 0, 1, 0);
        
        grid.addComponent(deliveryDate, 0, 1);
        grid.addComponent(rfqNumber, 1, 1);

        grid.addComponent(personRequesting, 0, 2);
        grid.addComponent(companyName, 1, 2);

        grid.addComponent(instructions, 0, 3);
        grid.addComponent(closingDate, 1, 3);

        itemPurchaseLayout.setSizeFull();
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        save.setSizeFull();
        buttons.addComponent(save);
        approval.setVisible(false);
        approval.setSizeFull();
        buttons.addComponent(approval);

        details = new Label("Items Information");
        
        itemDescription = UIComponent.getTextField("Item Description:", "itemDescription", RequestBean.class, binder);
        itemDescription = UIValidatorHelper.setRequiredTextField(itemDescription, "Item Description");
        
        quantity = UIComponent.getTextField("Quantity:", "quantity", RequestBean.class, binder);
        quantity = UIValidatorHelper.setRequiredTextField(quantity, "Quantity");
        
        unit = UIComponent.getTextField("Unit:", "unit", RequestBean.class, binder);
        
        volume = UIComponent.getTextField("Volume:", "volume", RequestBean.class, binder);

        itemPurchaseLayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        itemPurchaseLayout.addComponent(details, 0, 2);
        itemPurchaseLayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3);

        itemPurchaseLayout.addComponent(itemDescription, 0, 4);
        itemPurchaseLayout.addComponent(quantity, 1, 4);

        itemPurchaseLayout.addComponent(unit, 0, 5);
        itemPurchaseLayout.addComponent(volume, 1, 5);


        itemPurchaseLayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 6);
        itemPurchaseLayout.addComponent(buttons, 0, 8, 2, 8);
        itemPurchaseLayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 9);

        addComponent(grid);
        addComponent(itemPurchaseLayout);
    }
}

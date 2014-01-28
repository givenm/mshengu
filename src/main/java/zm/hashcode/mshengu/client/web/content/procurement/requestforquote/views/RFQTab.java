/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.HashSet;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.facade.procurement.RequestForQuoteFacade;
import zm.hashcode.mshengu.app.facade.procurement.RequestPurchaseItemFacade;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.app.util.SequenceHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.models.RequestBean;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.RFQMenu;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.RFQForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.SendQuotePDFForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.table.QuoteItemsTable;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Luckbliss
 */
public class RFQTab extends VerticalLayout implements
        Button.ClickListener {

    private RFQForm form;
    private QuoteItemsTable table;
    private MshenguMain main;
    private SendQuotePDFForm pDFForm;
    private SequenceHelper helper = new SequenceHelper();

    public RFQTab(MshenguMain main) {
        setSizeFull();
        form = new RFQForm(main);
        table = new QuoteItemsTable(main);
        this.main = main;
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            if (!form.quantity.getValue().isEmpty()) {
                addItemsToTable(form.binder);
            } else {
                Notification.show("Add All Item Values!", Notification.Type.TRAY_NOTIFICATION);
            }
        } else if (source == form.approval) {
            sendRequest(form.binder);
        } else if (source == pDFForm.back) {
            getHome();
        } else if (source == pDFForm.email) {
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new RFQMenu(main, "LANDING"));
    }

    private void sendRequest(FieldGroup binder) {
        try {
            setReadOnlyFalse();
            binder.commit();
            RequestForQuote request = getRequestForQuoteEntity(binder);
            RequestForQuoteFacade.getRequestForQuoteService().persist(request);
            Sequence sequence = SequenceFacade.getSequenceListService().findByName("MSHENGU_RFQ");
            helper.getRefNumber(sequence);
            setReadOnlyFalse();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
            main.content.setSecondComponent(new RFQMenu(main, "LIST_RFQ"));
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.approval.addClickListener((Button.ClickListener) this);
    }

    private void addItemsToTable(FieldGroup binder) {
        try {
            binder.commit();
            RequestPurchaseItem requestPurchaseItem = getEntity(binder);
            RequestPurchaseItemFacade.getRequestPurchaseItemService().persist(requestPurchaseItem);
            table.loadTable(requestPurchaseItem, form);
            form.approval.setVisible(true);
            resetValues();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void resetValues() {
        form.itemDescription.setValue("");
        form.quantity.setValue("");
        form.unit.setValue("");
        form.volume.setValue("");
    }

    private RequestPurchaseItem getEntity(FieldGroup binder) {
        RequestPurchaseItem requestPurchaseItem;
        RequestBean bean = ((BeanItem<RequestBean>) binder.getItemDataSource()).getBean();
        requestPurchaseItem = new RequestPurchaseItem.Builder(bean.getQuantity())
                .itemDescription(bean.getItemDescription())
                .unit(bean.getUnit())
                .volume(bean.getVolume())
                .build();

        return requestPurchaseItem;
    }

    private RequestForQuote getRequestForQuoteEntity(FieldGroup binder) {
        RequestBean bean = ((BeanItem<RequestBean>) binder.getItemDataSource()).getBean();
        Set<RequestPurchaseItem> items = new HashSet<>();
        for (Object id : table.getItemIds()) {
            RequestPurchaseItem item = RequestPurchaseItemFacade.getRequestPurchaseItemService().findById(id.toString());
            items.add(item);
        }
        Person person = PersonFacade.getPersonService().findById(bean.getRequestingPerson());
        RequestForQuote request = new RequestForQuote.Builder(person)
                .account(bean.getCompanyName())
                .items(items)
                .deliveryInstructions(bean.getDeliveryInstructions())
                .rfqNumber(bean.getRfqNumber())
                .deliveryDate(bean.getDeliveryDate())
                .closingDate(bean.getClosingDate())
                .build();
        return request;
    }

    private void setReadOnlyFalse() {
        form.rfqNumber.setReadOnly(false);
        form.companyName.setReadOnly(false);
    }

    private void setReadOnlyTrue() {
        form.rfqNumber.setReadOnly(true);
        form.companyName.setReadOnly(true);
    }
}

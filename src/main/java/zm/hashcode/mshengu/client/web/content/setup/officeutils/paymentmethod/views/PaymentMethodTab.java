/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.paymentmethod.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.util.PaymentMethodFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.paymentmethod.forms.PaymentMethodForm;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.paymentmethod.models.PaymentMethodBean;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.paymentmethod.tables.PaymentMethodTable;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;

/**
 *
 * @author Ferox
 */
public class PaymentMethodTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final PaymentMethodForm form;
    private final PaymentMethodTable table;

    public PaymentMethodTab(MshenguMain app) {
        main = app;
        form = new PaymentMethodForm();
        table = new PaymentMethodTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final PaymentMethod truckCategory = PaymentMethodFacade.getPaymentMethodListService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PaymentMethodFacade.getPaymentMethodListService().persist(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            PaymentMethodFacade.getPaymentMethodListService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        PaymentMethodFacade.getPaymentMethodListService().delete(getEntity(binder));
        getHome();
    }

    private PaymentMethod getEntity(FieldGroup binder) {
        //final  PaymentMethod cust = new PaymentMethod.Builder(binder.getItemDataSource().getItemProperty("name")).
        
        final PaymentMethodBean paymentMethodBean = ((BeanItem<PaymentMethodBean>) binder.getItemDataSource()).getBean();


        final PaymentMethod paymentMethod = new PaymentMethod.Builder(paymentMethodBean.getName())
                .id(paymentMethodBean.getId())
                .build();

        return paymentMethod;


    }

    private void getHome() {
        main.content.setSecondComponent(new OfficeUtilsMenu(main, "PAYMENT"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private PaymentMethodBean getBean(PaymentMethod paymentMethod) {
        PaymentMethodBean bean = new PaymentMethodBean();
        bean.setName(paymentMethod.getPaymentMethod());
        bean.setId(paymentMethod.getId());
        return bean;
    }


}

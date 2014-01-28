/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.incidents.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Collection;
import zm.hashcode.mshengu.app.facade.incident.IncidentActionStatusFacade;
import zm.hashcode.mshengu.app.util.validation.OnSubmitValidationHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.IncidentMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.forms.IncidentActionStatusForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.models.IncidentActionStatusBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.tables.IncidentActionStatusTable;
import zm.hashcode.mshengu.domain.incident.IncidentActionStatus;

/**
 *
 * @author Ferox
 */
public class IncidentActionStatusTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final IncidentActionStatusForm form;
    private final IncidentActionStatusTable table;

    public IncidentActionStatusTab(MshenguMain app) {
        main = app;
        form = new IncidentActionStatusForm();
        table = new IncidentActionStatusTable(main);
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
            final IncidentActionStatus truckCategory = IncidentActionStatusFacade.getIncidentActionStatusService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            IncidentActionStatusFacade.getIncidentActionStatusService().persist(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Collection<Field<?>> fields = binder.getFields();
            OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
            helper.doValidation();
            Notification.show("Please Correct Red Colored Inputs!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            IncidentActionStatusFacade.getIncidentActionStatusService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Collection<Field<?>> fields = binder.getFields();
            OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
            helper.doValidation();
            Notification.show("Please Correct Red Colored Inputs!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void deleteForm(FieldGroup binder) {
        IncidentActionStatusFacade.getIncidentActionStatusService().delete(getEntity(binder));
        getHome();
    }

    private IncidentActionStatus getEntity(FieldGroup binder) {
        final IncidentActionStatusBean incidentActionStatusBean = ((BeanItem<IncidentActionStatusBean>) binder.getItemDataSource()).getBean();
        final IncidentActionStatus paymentMethod = new IncidentActionStatus.Builder(incidentActionStatusBean.getName())
                .id(incidentActionStatusBean.getId())
                .build();

        return paymentMethod;
    }

    private void getHome() {
        main.content.setSecondComponent(new IncidentMenu(main, "ACTION_STATUS"));
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

    private IncidentActionStatusBean getBean(IncidentActionStatus incidentActionStatus) {
        IncidentActionStatusBean bean = new IncidentActionStatusBean();
        bean.setName(incidentActionStatus.getName());
        bean.setId(incidentActionStatus.getId());
        return bean;
    }
}

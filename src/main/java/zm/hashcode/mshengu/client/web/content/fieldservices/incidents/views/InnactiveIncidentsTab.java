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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.app.facade.incident.IncidentFacade;
import zm.hashcode.mshengu.app.facade.incident.IncidentTypeFacade;
import zm.hashcode.mshengu.app.facade.products.UnitTypeFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.app.util.SendEmailHelper;
import zm.hashcode.mshengu.app.util.UtilMethods;
import zm.hashcode.mshengu.app.util.validation.OnSubmitValidationHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.IncidentMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.forms.InnactiveIncidentForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.models.IncidentBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.tables.InnactiveIncidentTable;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.incident.Incident;
import zm.hashcode.mshengu.domain.incident.IncidentType;
import zm.hashcode.mshengu.domain.incident.UserAction;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Ferox
 */
public class InnactiveIncidentsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final InnactiveIncidentForm form;
    private final InnactiveIncidentTable table;
    private UtilMethods utilMethods = new UtilMethods();
    private SendEmailHelper sendEmailHelper = new SendEmailHelper();

    public InnactiveIncidentsTab(MshenguMain app) {
        main = app;
        form = new InnactiveIncidentForm();
        table = new InnactiveIncidentTable(main, this);
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
            final Incident incident = IncidentFacade.getIncidentService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(incident)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            Incident incident = getEntity(binder, "ADD");
            IncidentFacade.getIncidentService().persist(incident);
            sendEmailHelper.sendIncidentReportEmail(incident);
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
            IncidentFacade.getIncidentService().merge(getUpdateEntity(binder));
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
        IncidentFacade.getIncidentService().delete(getEntity(binder, "DELETE"));
        getHome();
    }

    private Incident getUpdateEntity(FieldGroup binder) {
        //final  Incident cust = new Incident.Builder(binder.getItemDataSource().getItemProperty("name")).

        final IncidentBean incidentBean = ((BeanItem<IncidentBean>) binder.getItemDataSource()).getBean();

        final IncidentType incidentType = IncidentTypeFacade.getIncidentTypeService().findById(incidentBean.getIncidentType());
        final UnitType unitType = UnitTypeFacade.getUnitTypeService().findById(incidentBean.getToiletType());
        final MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(incidentBean.getMailNotifications());
        final Incident existentIncident = IncidentFacade.getIncidentService().findById(incidentBean.getId());

        ServiceProvider serviceProvider = null;
        if (incidentBean.getServiceProvider() != null) {
            serviceProvider = ServiceProviderFacade.getServiceProviderService().findById(incidentBean.getServiceProvider());
        }

//existentIncident
        final Incident incident = new Incident.Builder(incidentBean.getActionDate())
                .incidentType(incidentType)
                .refNumber(incidentBean.getRefNumber())
                .closed(incidentBean.isClosed())
                .comment(incidentBean.getComment())
                .contactNumber(incidentBean.getContactNumber())
                .contactPerson(incidentBean.getContactPerson())
                .customer(incidentBean.getCustomer())
                .userAction(existentIncident.getUserAction())
                .mailNotifications(mailNotifications)
                .serviceProvider(serviceProvider)
                .site(incidentBean.getSite())
                .suburb(incidentBean.getSuburb())
                .email(incidentBean.getEmail())
                .toiletType(unitType)
                .id(incidentBean.getId())
                .build();

        return incident;


    }

    private Incident getEntity(FieldGroup binder, String action) {
        //final  Incident cust = new Incident.Builder(binder.getItemDataSource().getItemProperty("name")).

        final IncidentBean incidentBean = ((BeanItem<IncidentBean>) binder.getItemDataSource()).getBean();

        final IncidentType incidentType = IncidentTypeFacade.getIncidentTypeService().findById(incidentBean.getIncidentType());
        final UnitType unitType = UnitTypeFacade.getUnitTypeService().findById(incidentBean.getToiletType());
        final MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(incidentBean.getMailNotifications());

        ServiceProvider serviceProvider = null;
        if (incidentBean.getServiceProvider() != null) {
            serviceProvider = ServiceProviderFacade.getServiceProviderService().findById(incidentBean.getServiceProvider());
        }

        String refNumber = "";
        if (action.equalsIgnoreCase("ADD")) {
            refNumber = utilMethods.getRefNumber(mailNotifications);
        } else {
            refNumber = incidentBean.getRefNumber();
        }


        Set<UserAction> userActionList = new HashSet<>();
        final Incident incident = new Incident.Builder(new Date())
                .incidentType(incidentType)
                .refNumber(refNumber)
                .closed(incidentBean.isClosed())
                .comment(incidentBean.getComment())
                .contactNumber(incidentBean.getContactNumber())
                .contactPerson(incidentBean.getContactPerson())
                .customer(incidentBean.getCustomer())
                .userAction(userActionList)
                .mailNotifications(mailNotifications)
                .serviceProvider(serviceProvider)
                .site(incidentBean.getSite())
                .suburb(incidentBean.getSuburb())
                .email(incidentBean.getEmail())
                .toiletType(unitType)
                .id(incidentBean.getId())
                .build();

        return incident;


    }

    private void getHome() {
        main.content.setSecondComponent(new IncidentMenu(main, "CLOSED"));
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

    private IncidentBean getBean(Incident incident) {

        IncidentBean bean = new IncidentBean();
        bean.setActionDate(incident.getActionDate());
        bean.setClosed(incident.isClosed());
        bean.setComment(incident.getComment());
        bean.setContactNumber(incident.getContactNumber());
        bean.setContactPerson(incident.getContactPerson());
        bean.setCustomer(incident.getCustomer());
        bean.setRefNumber(incident.getRefNumber());
        bean.setIncidentType(incident.getIncidentTypeId());
        bean.setMailNotifications(incident.getMailNotificationsId());
        bean.setServiceProvider(incident.getServiceProviderId());
        bean.setSite(incident.getSite());
        bean.setEmail(incident.getEmail());
        bean.setStatus(incident.getStatusId());
        bean.setSuburb(incident.getSuburb());
        bean.setToiletType(incident.getToiletTypeId());
        bean.setId(incident.getId());
        return bean;
    }
}

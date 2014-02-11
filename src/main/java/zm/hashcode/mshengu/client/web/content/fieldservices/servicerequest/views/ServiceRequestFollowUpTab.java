/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.views;

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
import org.springframework.dao.DuplicateKeyException;
import zm.hashcode.mshengu.app.facade.customer.ServiceRequestFacade;
import zm.hashcode.mshengu.app.facade.incident.UserActionFacade;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.app.security.GetUserCredentials;
import zm.hashcode.mshengu.app.util.validation.OnSubmitValidationHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.ServiceRequestMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.forms.ServiceRequestFollowUpFormTwo;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.models.ServiceRequestFollowUpBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.tables.ServiceRequestFollowUpTable;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;
import zm.hashcode.mshengu.domain.incident.UserAction;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author Luckbliss
 */
public class ServiceRequestFollowUpTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceRequestFollowUpFormTwo form;
    private final ServiceRequestFollowUpTable table;
    private final String serviceRequestId;

    public ServiceRequestFollowUpTab(MshenguMain app, final String id) {
        serviceRequestId = id;
        this.main = app;
        form = new ServiceRequestFollowUpFormTwo(app);
        table = new ServiceRequestFollowUpTable(app, serviceRequestId);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
//        if (source == form.save) {
//            saveServiceRequestFollowUp();
//        } else if (source == form.edit) {
//        } else if (source == form.cancel) {
//        } else if (source == form.update) {
//            saveServiceRequestFollowUp();
//        } else if (source == form.delete) {
//        } else if (source == form.back) {
//            getHome();
//        }

        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            resetForm();
        } else if (source == form.update) {
            saveForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        } else if (source == form.back) {
            getHome();
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final UserAction userAction = UserActionFacade.getUserActionService().findById(table.getValue().toString());
//            final ServiceRequestFollowUpBean followUpBean = getBean(userAction);
            form.setReadOnly(false);
            form.binder.setItemDataSource(new BeanItem<>(getBean(userAction)));
            setReadFormProperties();
        }
    }

    private void deleteForm(FieldGroup binder) {
        UserActionFacade.getUserActionService().delete(getUserAction(binder));
        getHome();
    }

    public void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            UserAction action = getUserAction(binder);
            String serviceRequestIdin = returnServiceRequestString(action);
            table.removeValueChangeListener((Property.ValueChangeListener) this);
            table.loadUserActions(serviceRequestIdin);
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Collection<Field<?>> fields = binder.getFields();
            OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
            helper.doValidation();
            Notification.show("Please Correct Red Colored Inputs!", Notification.Type.TRAY_NOTIFICATION); 
        } catch (DuplicateKeyException dp) {
            Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private String returnServiceRequestString(UserAction action) {
        UserActionFacade.getUserActionService().persist(action);
        ServiceRequest serviceRequest = ServiceRequestFacade.getServiceRequestService().findById(serviceRequestId);
        Set<UserAction> list = new HashSet<>();
        if (serviceRequest.getUserAction() != null) {
            list.addAll(serviceRequest.getUserAction());
        }
        list.add(action);
        ServiceRequest newServiceRequest = new ServiceRequest.Builder(serviceRequest.getDateofAction())
                .serviceRequest(serviceRequest)
                .userAction(list)
                .id(serviceRequestId)
                .build();
        ServiceRequestFacade.getServiceRequestService().merge(newServiceRequest);
        return newServiceRequest.getId();
    }

    private UserAction getUserAction(FieldGroup binder) {
        final ServiceRequestFollowUpBean bean = ((BeanItem<ServiceRequestFollowUpBean>) binder.getItemDataSource()).getBean();
        Status status = StatusFacade.getStatusService().findById(bean.getStatus());
        Person user = new GetUserCredentials().getLoggedInPerson();
        UserAction action = new UserAction.Builder(new Date())
                .comment(bean.getComment())
                .UserActionStatus(status)
                .resolvedDate(bean.getResolvedDate())
                .qualityAssuranceDate(bean.getQualityAssuranceDate())
                .staff(user)
                .build();
        return action;
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        form.back.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
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

    private void getHome() {
        main.content.setSecondComponent(new ServiceRequestMenu(main, "LANDING"));
    }

    public void resetForm() {
        form.binder.setItemDataSource(new BeanItem<>(new ServiceRequestFollowUpBean()));
    }

    private ServiceRequestFollowUpBean getBean(UserAction userAction) {
        ServiceRequestFollowUpBean followUpBean = new ServiceRequestFollowUpBean();
        followUpBean.setActionDate(userAction.getActionDate());
        followUpBean.setComment(userAction.getComment());
        followUpBean.setQualityAssuranceDate(userAction.getQualityAssuranceDate());
        followUpBean.setResolvedDate(userAction.getResolvedDate());
        followUpBean.setStaffId(userAction.getUserActionStatusId());
        followUpBean.setStatus(userAction.getUserActionStatusId());
        followUpBean.setId(userAction.getId());
        return followUpBean;
    }
}

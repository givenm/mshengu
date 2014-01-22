/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.incidents.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.dao.DuplicateKeyException;
import zm.hashcode.mshengu.app.facade.incident.IncidentFacade;
import zm.hashcode.mshengu.app.facade.incident.UserActionFacade;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.app.security.GetUserCredentials;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.IncidentMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.forms.IncidentFollowUpForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.models.IncidentFollowUpBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.tables.IncidentFollowUpTable;
import zm.hashcode.mshengu.domain.incident.Incident;
import zm.hashcode.mshengu.domain.incident.UserAction;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author given
 */
public class InnactiveIncidentFollowUpTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final IncidentFollowUpForm form;
    private final IncidentFollowUpTable table;
    private final String incidentId;

    public InnactiveIncidentFollowUpTab(MshenguMain app, final String id) {
        incidentId = id;
        this.main = app;
        form = new IncidentFollowUpForm(app);
        table = new IncidentFollowUpTable(app, incidentId);
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
//            final IncidentFollowUpBean followUpBean = getBean(userAction);
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
            String incidentIdin = returnIncidentString(action);
            table.removeValueChangeListener((Property.ValueChangeListener) this);
            table.loadUserActions(incidentIdin);
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        } catch (DuplicateKeyException dp) {
            Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private String returnIncidentString(UserAction action) {
        UserActionFacade.getUserActionService().persist(action);
        Incident incident = IncidentFacade.getIncidentService().findById(incidentId);
        Set<UserAction> list = new HashSet<>();
        if (incident.getUserAction() != null) {
            list.addAll(incident.getUserAction());
        }
        list.add(action);
        Incident newIncident = new Incident.Builder(incident.getActionDate())
                .incident(incident)
                .userAction(list)
                .id(incidentId)
                .build();
        IncidentFacade.getIncidentService().merge(newIncident);
        return newIncident.getId();
    }

    private UserAction getUserAction(FieldGroup binder) {
        final IncidentFollowUpBean bean = ((BeanItem<IncidentFollowUpBean>) binder.getItemDataSource()).getBean();
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
        main.content.setSecondComponent(new IncidentMenu(main, "CLOSED"));
    }

    public void resetForm() {
        form.binder.setItemDataSource(new BeanItem<>(new IncidentFollowUpBean()));
    }

    private IncidentFollowUpBean getBean(UserAction userAction) {
        IncidentFollowUpBean followUpBean = new IncidentFollowUpBean();
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

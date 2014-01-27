/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.HashSet;
import java.util.Set;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.people.ContactPersonFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.HRMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.forms.StaffContactPersonForm;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.models.StaffContactPersonBean;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.tables.StaffContactPersonTable;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Ferox
 */
public class StaffContactPersonTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final StaffContactPersonForm form;
    private final StaffContactPersonTable table;
    private String parentId;

    public StaffContactPersonTab(MshenguMain app) {
        main = app;
        form = new StaffContactPersonForm();
        table = new StaffContactPersonTable(main);
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
        if (property == form.staffId) {
            if (form.staffId.getValue().toString() != null) {
                setParentId(form.staffId.getValue().toString());
            }
        } else if (property == table) {
            final ContactPerson contactPerson = ContactPersonFacade.getContactPersonService().findById(table.getValue().toString());
            final StaffContactPersonBean bean = getBean(contactPerson);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ContactPerson contactPerson = getEntity(binder);
            ContactPersonFacade.getContactPersonService().persist(contactPerson);
            updateStaff(contactPerson); 
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
            ContactPerson contactPerson = getEntity(binder);
            ContactPersonFacade.getContactPersonService().merge(contactPerson);
            updateStaff(contactPerson); 
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
//        CustomerFacade.getCustomerService().delete(getEntity(binder));
        Notification.show("Deleting customers is not allowed!", Notification.Type.TRAY_NOTIFICATION);
        getHome();
    }

    private ContactPerson getEntity(FieldGroup binder) {

        final StaffContactPersonBean staffContactPersonBean = ((BeanItem<StaffContactPersonBean>) binder.getItemDataSource()).getBean();
        final ContactPerson contactPerson = new ContactPerson.Builder(staffContactPersonBean.getFirstName(), staffContactPersonBean.getLastName())
                .address1(staffContactPersonBean.getAddress())
                .emailAddress(staffContactPersonBean.getEmailAddress())
                .mainNumber(staffContactPersonBean.getMainNumber())
                .otherNumber(staffContactPersonBean.getOtherNumber())
                .position(staffContactPersonBean.getPosition())
                .id(staffContactPersonBean.getContactPersonId())
                .build();
        return contactPerson;
    }

    private void updateStaff(ContactPerson contactPerson) {

        Set<ContactPerson> contactPersonList = new HashSet<>();
        try {
            if (!StringUtils.isEmpty(getParentId())) {
                Person updateStaff = PersonFacade.getPersonService().findById(getParentId());
                if (!StringUtils.isEmpty(updateStaff)) {

                    if (!StringUtils.isEmpty(updateStaff.getContactPerson())) {
                        contactPersonList.addAll(updateStaff.getContactPerson());
                    }

                    contactPersonList.add(contactPerson);
                    Person newPerson = new Person.Builder(updateStaff.getLastname())
                            .person(updateStaff)
                            .contactPerson(contactPersonList)
                            .id(updateStaff.getId())
                            .build();

                    PersonFacade.getPersonService().merge(newPerson);
                } else {

                    Notification.show("Error assosiating contact person with Staff (Staff Not Found)!", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        } catch (Exception e) {
            Notification.show("Error assosiating contact person with Staff!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new HRMenu(main, "CONTACT_PERSON"));
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
        form.staffId.addValueChangeListener((Property.ValueChangeListener) this);
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
//staffContactPersonBean

    private StaffContactPersonBean getBean(ContactPerson contactPerson) {
        StaffContactPersonBean bean = new StaffContactPersonBean();
        bean.setContactPersonId(contactPerson.getId());
        bean.setAddress(contactPerson.getAddress1());
        bean.setEmailAddress(contactPerson.getEmailAddress());
        bean.setFirstName(contactPerson.getFirstName());
        bean.setLastName(contactPerson.getLastName());
        bean.setMainNumber(contactPerson.getMainNumber());
        bean.setOtherNumber(contactPerson.getOtherNumber());
        bean.setPosition(contactPerson.getPosition());
        bean.setParentId(getParentId());
        return bean;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
        loadContactPerson(parentId);
    }

    public void loadContactPerson(String parentId) {
        if (!StringUtils.isEmpty(getParentId())) {
            table.removeValueChangeListener(this);
            table.loadContactPerson(parentId);
            table.addValueChangeListener((Property.ValueChangeListener) this);
        }
    }
}

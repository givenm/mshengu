/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.site.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Collection;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.people.ContactPersonFacade;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.util.validation.OnSubmitValidationHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.ServiceSchedulingMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.forms.SiteContactPersonForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.models.SiteContactPersonBean;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public class SiteContactPersonDetailsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final SiteContactPersonForm form;
    private String parentId;

    public SiteContactPersonDetailsTab(MshenguMain app) {
        main = app;
        form = new SiteContactPersonForm();
        setSizeFull();
        addComponent(form);
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
            saveForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ContactPersonFacade.getContactPersonService().merge(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Collection<Field<?>> fields = binder.getFields();
            OnSubmitValidationHelper helper = new OnSubmitValidationHelper(fields, form.errorMessage);
            helper.doValidation();
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            if (!StringUtils.isEmpty(getParentId())) {
                binder.commit();
                ContactPerson contactPerson = getEntity(binder);
                ContactPersonFacade.getContactPersonService().merge(contactPerson);
                updateSite(contactPerson);
                getHome();
                Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
            } else {
                Notification.show("Please Select Site!", Notification.Type.TRAY_NOTIFICATION);
            }
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

        final SiteContactPersonBean customerBean = ((BeanItem<SiteContactPersonBean>) binder.getItemDataSource()).getBean();

        final ContactPerson contactPerson = new ContactPerson.Builder(customerBean.getFirstName(), customerBean.getLastName())
                .address1(customerBean.getAddress())
                .emailAddress(customerBean.getEmailAddress())
                .mainNumber(customerBean.getMainNumber())
                .otherNumber(customerBean.getOtherNumber())
                .position(customerBean.getPosition())
                .id(customerBean.getId())
                .build();
        return contactPerson;


    }

    private void updateSite(ContactPerson contactPerson) {

        try {
            if (!StringUtils.isEmpty(getParentId())) {
                Site site = SiteFacade.getSiteService().findById(getParentId());
                if (!StringUtils.isEmpty(site)) {
                    Site newSite = new Site.Builder(site.getName())
                            .contactPerson(contactPerson)
                            .build();

                    SiteFacade.getSiteService().merge(newSite);
                } else {

                    Notification.show("Error assosiating site with customer (Site Not Found)!", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        } catch (Exception e) {
            Notification.show("Error assosiating site with customer!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new ServiceSchedulingMenu(main, "CONTACT_PERSON"));
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
    }

    private SiteContactPersonBean getBean(ContactPerson contactPerson) {
        SiteContactPersonBean bean = new SiteContactPersonBean();
        bean.setId(contactPerson.getId());
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
        loadSiteContactPerson();
    }

    public void loadSiteContactPerson() {
        if (!StringUtils.isEmpty(getParentId())) {
            final Site site = SiteFacade.getSiteService().findById(getParentId());
            if (!StringUtils.isEmpty(site)) {
                if (!StringUtils.isEmpty(site.getContactPerson())) {
                    form.binder.setItemDataSource(new BeanItem<>(getBean(site.getContactPerson())));
                    setReadFormProperties();
                }
            }
        }
    }
}

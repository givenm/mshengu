/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.views;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.dao.DuplicateKeyException;
import zm.hashcode.mshengu.app.facade.customer.EmployeeDetailFacade;
import zm.hashcode.mshengu.app.facade.people.ContactPersonFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.facade.ui.location.AddressFacade;
import zm.hashcode.mshengu.app.facade.ui.util.CountryFacade;
import zm.hashcode.mshengu.app.facade.ui.util.JobPositionFacade;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.app.facade.ui.util.TerminateFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.HRMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.forms.StaffDetailsForm;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.forms.TerminateForm;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.models.StaffDetailsBean;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.tables.StaffTable;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.location.Address;
import zm.hashcode.mshengu.domain.ui.location.Contact;
import zm.hashcode.mshengu.domain.ui.util.Country;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;
import zm.hashcode.mshengu.domain.ui.util.Role;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.domain.ui.util.Terminate;

/**
 *
 * @author Ferox
 */
public final class StaffTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final StaffDetailsForm form;
    private final StaffTable table;
    private final TerminateForm terminateForm;
    private String personId;

    public StaffTab(MshenguMain app) {
        main = app;
        form = new StaffDetailsForm();
        table = new StaffTable(main);
        terminateForm = new TerminateForm();
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.terminate) {
            terminateForm(form.binder);
        } else if (source == terminateForm.save) {
            if (terminateForm.endDate.getValue() != null && terminateForm.terminateReason.getValue() != null) {
                saveTerminateForm(terminateForm.binder);
            } else {
                Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            }

        } else if (source == terminateForm.cancel) {
            getHome();
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Person person = PersonFacade.getPersonService().findById(table.getValue().toString());
            final StaffDetailsBean bean = getBean(person);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            setReadFormProperties();
            personId = table.getValue().toString();
            if (bean.getTerminateDate() != null) {
                setVisibleTrue();
                setReadOnlyFalse();
                form.terminateReason.setValue(bean.getTerminateReason());
                form.terminateCode.setValue(bean.getTerminateCode());
                form.terminateDate.setValue(bean.getTerminateDate());
                setReadOnlyTrue();
                form.setButtonsVisibleFalse();
            } else {
                setVisibleFalse();
            }
        }
    }

    private void setVisibleTrue() {
        form.terminateReason.setVisible(true);
        form.terminateCode.setVisible(true);
        form.terminateDate.setVisible(true);
    }

    private void setVisibleFalse() {
        form.terminateReason.setVisible(false);
        form.terminateCode.setVisible(false);
        form.terminateDate.setVisible(false);
    }

    private void setReadOnlyFalse() {
        form.terminateReason.setReadOnly(false);
        form.terminateCode.setReadOnly(false);
        form.terminateDate.setReadOnly(false);
    }

    private void setReadOnlyTrue() {
        form.terminateReason.setReadOnly(true);
        form.terminateCode.setReadOnly(true);
        form.terminateDate.setReadOnly(true);
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PersonFacade.getPersonService().persist(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        } catch (DuplicateKeyException dp) {
            dp.printStackTrace();
            Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveTerminateForm(FieldGroup binder) {
        try {
            binder.commit();
            PersonFacade.getPersonService().merge(getTerminateEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        } catch (DuplicateKeyException dp) {
            dp.printStackTrace();
            Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            PersonFacade.getPersonService().merge(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        } catch (DuplicateKeyException dp) {
            dp.printStackTrace();
            Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void terminateForm(FieldGroup binder) {
        this.removeAllComponents();
        addComponent(terminateForm);
    }

    private Person getNewEntity(FieldGroup binder) {
        final StaffDetailsBean bean = ((BeanItem<StaffDetailsBean>) binder.getItemDataSource()).getBean();
        Set<Role> role = new HashSet<>();
        Set<Contact> contact = new HashSet<>();
        Set<ContactPerson> contactPerson = new HashSet<>();

        Address address = saveAddress(bean, "ADD");
        EmployeeDetail employeeDetail = saveEmployeeDetail(bean, "ADD");

        final Person person = new Person.Builder(bean.getLastname())
                .firstname(bean.getFirstname())
                .othername(bean.getOthername())
                .dateofbirth(bean.getDateofbirth())
                .enable(bean.isEnabled())
                .username(bean.getEmployeeNumber())
                .address(address)
                .user(false)
                .employeeDetails(employeeDetail)
                .role(role)
                .contactPerson(contactPerson)
                .contact(contact)
                .requestor(bean.isRequestor())
                .build();
        return person;
    }

    private Person getUpdateEntity(FieldGroup binder) {
        final StaffDetailsBean bean = ((BeanItem<StaffDetailsBean>) binder.getItemDataSource()).getBean();
        Person staffPerson = PersonFacade.getPersonService().findById(bean.getId());

        Address address = saveAddress(bean, "UPDATE");
        EmployeeDetail employeeDetail = saveEmployeeDetail(bean, "UPDATE");

        final Person person = new Person.Builder(bean.getLastname())
                .person(staffPerson)
                .firstname(bean.getFirstname())
                .othername(bean.getOthername())
                .dateofbirth(bean.getDateofbirth())
                .enable(bean.isEnabled())
                .username(bean.getEmployeeNumber())
                .address(address)
                .user(false)
                .employeeDetails(employeeDetail)
                .id(bean.getId())
                .requestor(bean.isRequestor())
                .build();
        return person;
    }

    private Person getTerminateEntity(FieldGroup binder) {
        final StaffDetailsBean bean = ((BeanItem<StaffDetailsBean>) binder.getItemDataSource()).getBean();
        Person staffPerson = PersonFacade.getPersonService().findById(personId);

        EmployeeDetail employeeDetail = staffPerson.getEmployeeDetails();

        Terminate terminate = TerminateFacade.getTerminateService().findById(bean.getTerminateReason());
        EmployeeDetail mewEmployeeDetail = new EmployeeDetail.Builder(employeeDetail.getIdNumber())
                .EmployeeDetails(employeeDetail)
                .drivesCompanyCar(false)
                .endDate(bean.getEndDate())
                .terminate(terminate)
                .employementStatus(null)
                .terminated("Terminated")
                .build();
        EmployeeDetailFacade.getEmployeeDetailService().merge(mewEmployeeDetail);

        final Person person = new Person.Builder(staffPerson.getLastname())
                .person(staffPerson)
                .employeeDetails(mewEmployeeDetail)
                .build();
        return person;
    }

    private void getHome() {
        main.content.setSecondComponent(new HRMenu(main, "MANAGE_EMP"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.terminate.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviou
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.terminate.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((ClickListener) this);
        form.edit.addClickListener((ClickListener) this);
        form.cancel.addClickListener((ClickListener) this);
        form.update.addClickListener((ClickListener) this);
        form.terminate.addClickListener((ClickListener) this);
        terminateForm.save.addClickListener((ClickListener) this);
        terminateForm.cancel.addClickListener((ClickListener) this);
        table.addValueChangeListener((ValueChangeListener) this);
    }

    public StaffDetailsBean getBean(Person staffPerson) {

        StaffDetailsBean bean = new StaffDetailsBean();
        bean.setFirstname(staffPerson.getFirstname());
        bean.setLastname(staffPerson.getLastname());
        bean.setOthername(staffPerson.getOthername());
        bean.setDateofbirth(staffPerson.getDateofbirth());
        bean.setEnabled(staffPerson.isEnable());
        bean.setAddressId(staffPerson.getAddressId());
        bean.setPostalCode(staffPerson.getAddressPostalCode());
        bean.setStreetAddress(staffPerson.getAddressStreetAddress());

        if (staffPerson.getEmployeeDetails() != null) {
            EmployeeDetail employeeDetail = staffPerson.getEmployeeDetails();

            bean.setIdNumber(employeeDetail.getIdNumber());
            bean.setEmail(employeeDetail.getEmail());
            bean.setCountryId(employeeDetail.getCountryId());
            bean.setJobPositionId(employeeDetail.getJobPositionId());
            bean.setMainNumber(employeeDetail.getMainNumber());
            bean.setOtherNumber(employeeDetail.getOtherNumber());
            bean.setPermitExpire(employeeDetail.getPermitExpire());
            bean.setDriversLicenceNo(employeeDetail.getDriversLicenceNo());
            bean.setDriversLicenceExpireDate(employeeDetail.getDriversLicenceExpireDate());
            bean.setPdpExpireDate(employeeDetail.getPdpExpireDate());
            bean.setStartDate(employeeDetail.getStartDate());
            bean.setEndDate(employeeDetail.getEndDate());
            bean.setEmployeeDetailId(employeeDetail.getId());
            bean.setEmployeeNumber(employeeDetail.getEmployeeNumber());
            bean.setDrivesCompanyCar(employeeDetail.isDrivesCompanyCar());
            bean.setLeaveEndDate(employeeDetail.getLeaveEndDate());
            bean.setLeaveStartDate(employeeDetail.getLeaveStartDate());
            bean.setEmployementStatusId(employeeDetail.getEmploymentStatusId());
            if (employeeDetail.getTerminate() != null) {
                bean.setTerminateCode(employeeDetail.getTerminate().getCode());
                bean.setTerminateReason(employeeDetail.getTerminate().getReason());
                bean.setTerminateDate(getEndDate(employeeDetail.getEndDate()));
            }
        }
        bean.setRequestor(staffPerson.isRequestor());
        bean.setId((staffPerson.getId()));
        return bean;
    }

    private String getEndDate(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }

    private Address saveAddress(StaffDetailsBean bean, String action) {
        Address address = new Address.Builder(bean.getStreetAddress())
                .postalCode(bean.getPostalCode())
                .id(bean.getAddressId())
                .build();

        if (action.equalsIgnoreCase("ADD")) {
            AddressFacade.getAddressService().persist(address);
        } else if (action.equalsIgnoreCase("UPDATE")) {
            AddressFacade.getAddressService().merge(address);
        }
        return address;
    }

    private ContactPerson saveContactPerson(String name, String surname) {

        final ContactPerson contactPerson = new ContactPerson.Builder(name, surname)
                .build();

        ContactPersonFacade.getContactPersonService().persist(contactPerson);
        return contactPerson;
    }

    private EmployeeDetail saveEmployeeDetail(StaffDetailsBean bean, String action) {

        final JobPosition jobPosition = JobPositionFacade.getJobPositionService().findById(bean.getJobPositionId());
        final Country country = CountryFacade.getCountryService().findById(bean.getCountryId());
        final Status employementStatus = StatusFacade.getStatusService().findById(bean.getEmployementStatusId());

        /*       
         private boolean drivesCompanyCar;
         private Status employementStatus;
         private Date leaveEndDate;
         private Date leaveStartDate;*/
        EmployeeDetail employeeDetail = new EmployeeDetail.Builder(bean.getIdNumber())
                .email(bean.getEmail())
                .country(country)
                .jobPosition(jobPosition)
                .employementStatus(employementStatus)
                .mainNumber(bean.getMainNumber())
                .otherNumber(bean.getOtherNumber())
                .permitExpire(bean.getPermitExpire())
                .driversLicenceNo(bean.getDriversLicenceNo())
                .driversLicenceExpireDate(bean.getDriversLicenceExpireDate())
                .pdpExpireDate(bean.getPdpExpireDate())
                .startDate(bean.getStartDate())
                .drivesCompanyCar(bean.isDrivesCompanyCar())
                .leaveEndDate(bean.getLeaveEndDate())
                .leaveStartDate(bean.getLeaveStartDate())
                .employeeNumber(bean.getEmployeeNumber())
                .id(bean.getEmployeeDetailId())
                .build();

        if (action.equalsIgnoreCase("ADD")) {
            EmployeeDetailFacade.getEmployeeDetailService().persist(employeeDetail);
        } else if (action.equalsIgnoreCase("UPDATE")) {
            EmployeeDetailFacade.getEmployeeDetailService().merge(employeeDetail);
        }
        return employeeDetail;
    }
}

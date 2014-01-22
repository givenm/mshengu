/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.nationality.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.util.CountryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.nationality.form.CountryForm;
import zm.hashcode.mshengu.client.web.content.humanresources.nationality.models.CountryBean;
import zm.hashcode.mshengu.client.web.content.humanresources.nationality.table.CountryTable;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.HRMenu;
import zm.hashcode.mshengu.domain.ui.util.Country;

/**
 *
 * @author Ferox
 */
public class CountryTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final CountryForm form;
    private final CountryTable table;

    public CountryTab(MshenguMain app) {
        main = app;
        form = new CountryForm();
        table = new CountryTable(main);
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
            final Country truckCategory = CountryFacade.getCountryService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            CountryFacade.getCountryService().persist(getEntity(binder));
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
            CountryFacade.getCountryService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        CountryFacade.getCountryService().delete(getEntity(binder));
        getHome();
    }

    private Country getEntity(FieldGroup binder) {
        //final  Country cust = new Country.Builder(binder.getItemDataSource().getItemProperty("name")).

        final CountryBean countryBean = ((BeanItem<CountryBean>) binder.getItemDataSource()).getBean();


        final Country country = new Country.Builder(countryBean.getName())
                .nationality(countryBean.getNationality())
                .id(countryBean.getId())
                .build();

        return country;


    }

    private void getHome() {
        main.content.setSecondComponent(new HRMenu(main, "COUNTRY"));
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

    private CountryBean getBean(Country country) {
        CountryBean bean = new CountryBean();
        bean.setName(country.getName());
        bean.setNationality(country.getNationality());
        bean.setId(country.getId());
        return bean;
    }
}

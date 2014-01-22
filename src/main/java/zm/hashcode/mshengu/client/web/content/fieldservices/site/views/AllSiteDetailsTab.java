/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.site.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.ServiceSchedulingMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.forms.AllSitesDetailsForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.tables.AllSitesTable;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public class AllSiteDetailsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final AllSitesDetailsForm form;
    private final AllSitesTable table;
    private String parentId = null;

    public AllSiteDetailsTab(MshenguMain app) {
        main = app;
        form = new AllSitesDetailsForm();
        table = new AllSitesTable(main);
        form.setTotalNumberOfServices(table.getTotalNumberOfSites(), table.getTotalNumberOfUnits(), table.getWeeklyServices(), table.getMonthlyServices());
        setCaption("Site Units");
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
//            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
//            saveEditedForm(form.binder);
        } else if (source == form.delete) {
//            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Site site = SiteFacade.getSiteService().findById(table.getValue().toString());
//            form.binder.setItemDataSource(new BeanItem<>(getBean(site)));
//            setReadFormProperties();
        } else if (property == form.regionId) {
            if (form.regionId.getValue().toString() != null) {
                String parentRegion = form.regionId.getValue().toString();
                if (form.isReadOnly()) {
                    form.setReadOnly(false);
                    form.loadSuburbs(parentRegion);
                    form.setReadOnly(true);
                } else {
                    form.loadSuburbs(parentRegion);
                }
            }
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new ServiceSchedulingMenu(main, "LANDING"));
//        loadTable();
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
        form.regionId.addValueChangeListener((Property.ValueChangeListener) this);
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
//        loadTable();
        form.setTotalNumberOfServices(table.getTotalNumberOfSites(),  table.getTotalNumberOfUnits(), table.getWeeklyServices(), table.getMonthlyServices());

    }
}

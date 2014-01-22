/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.chemicals.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.chemicals.DashboardChemicalsMenu;
import zm.hashcode.mshengu.client.web.content.chemicals.forms.ChemicalCostForm;
import zm.hashcode.mshengu.client.web.content.chemicals.models.ChemicalCostBean;
import zm.hashcode.mshengu.client.web.content.chemicals.tables.ChemicalCostTable;
import zm.hashcode.mshengu.domain.chemical.ChemicalCost;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 *
 * @author Ferox
 */
public class ChemicalCostTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ChemicalCostForm form;
    private final ChemicalCostTable table;
    private static String productId;

    public ChemicalCostTab(MshenguMain app) {
        main = app;
        form = new ChemicalCostForm();
        table = new ChemicalCostTable(main);
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
        if (property == form.startDate) {
        } else if (property == form.endDate) {
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new DashboardChemicalsMenu(main, "CHEM_COST"));
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
        //
        form.startDate.addValueChangeListener((Property.ValueChangeListener) this);
        form.endDate.addValueChangeListener((Property.ValueChangeListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private ChemicalCostBean getBean(ChemicalCost entity) {
        ChemicalCostBean bean = new ChemicalCostBean();
        bean.setTransactionDate(entity.getTransactionDate());
        bean.setInvoiceNumber(entity.getInvoiceNumber());
        bean.setProductCategoryId(getServiceProviderProductCategoryId(entity.getServiceProviderProduct()));
        bean.setProductId(entity.getServiceProviderProductId());
        bean.setQtyOrdered(entity.getQuantityOrdered());

        // Get the SUpplier of Selected Product
        List<ServiceProvider> serviceProviderList = ServiceProviderFacade.getServiceProviderService().findAll();
        for (ServiceProvider serviceProvider : serviceProviderList) {
            boolean found = false;
            for (ServiceProviderProduct serviceProviderProductt : serviceProvider.getServiceProviderProduct()) {
                if (serviceProviderProductt.getId().equals(productId)) {
                    bean.setSupplierId(serviceProvider.getId());
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        bean.setTotalPrice(entity.getTotalPrice());
        bean.setUnitPrice(entity.getServiceProviderProductPrice());
        bean.setVolume(entity.getVolume());
        bean.setId(entity.getId());
        return bean;
    }

    private String getServiceProviderProductCategoryId(ServiceProviderProduct serviceProviderProduct) {
        if (serviceProviderProduct != null) {
            return serviceProviderProduct.getServiceProviderProductCategoryId();
        } else {
            return null;
        }
    }
}

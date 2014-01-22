/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.suppliers.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderProductFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.SupplierMenu;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.models.ServiceProviderProductBean;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.table.ServiceProviderProductTable;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 * @author Ferox
 *
 */
public class ServiceProviderProductDatabaseTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceProviderProductTable table;
    private String supplierId;

    public ServiceProviderProductDatabaseTab(MshenguMain app) {
        main = app;
        table = new ServiceProviderProductTable(main);
        setSizeFull();
//        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final ServiceProviderProduct ServiceProviderProduct = ServiceProviderProductFacade.getServiceProviderProductService().findById(table.getValue().toString());
            //        form.binder.setItemDataSource(new BeanItem<>(getBean(ServiceProviderProduct)));
            //setReadFormProperties();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new SupplierMenu(main, "LANDING"));
    }

    private void addListeners() {
        //Register Button Listeners
//        form.save.addClickListener((Button.ClickListener) this);
//        form.edit.addClickListener((Button.ClickListener) this);
//        form.cancel.addClickListener((Button.ClickListener) this);
//        form.update.addClickListener((Button.ClickListener) this);
//        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private ServiceProviderProductBean getBean(ServiceProviderProduct serviceProviderProduct) {
        ServiceProviderProductBean bean = new ServiceProviderProductBean();
        bean.setName(serviceProviderProduct.getProductName());
        bean.setId(serviceProviderProduct.getId());
        bean.setPrice(serviceProviderProduct.getPrice());
        bean.setProductCategoryId(serviceProviderProduct.getProductCategory().getId());
        return bean;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
        table.removeValueChangeListener(this);
        table.loadProducts(supplierId);//Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
}

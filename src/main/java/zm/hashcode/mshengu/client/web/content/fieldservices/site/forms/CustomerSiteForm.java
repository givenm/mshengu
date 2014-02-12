/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.site.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import java.util.List;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.client.web.content.setup.user.util.CustomerSiteUnitBean;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.products.Site;

/**
 * I
 *
 * @author Ferox
 */
public class CustomerSiteForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final CustomerSiteUnitBean bean;
    public final BeanItem<CustomerSiteUnitBean> item;
    public final FieldGroup binder;
    public ComboBox comboBoxSelectContractType;;
    public ComboBox comboBoxSelectSite;
    public ComboBox comboBoxSelectCustomer;

    public CustomerSiteForm() {
        bean = new CustomerSiteUnitBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);


        Label heading = new Label("SET UP SERVICE SCHEDULE");
        heading.setSizeUndefined();
        heading.addStyleName("h4");
        
        comboBoxSelectContractType = UIComboBox.getContractTypeComboBox("Select Hire Type", "contractType", CustomerSiteUnitBean.class, binder);

        comboBoxSelectSite = new ComboBox("Select Site");
        comboBoxSelectCustomer = new ComboBox("Select Customer");
        comboBoxSelectSite.setImmediate(true);
        comboBoxSelectCustomer.setImmediate(true);
        GridLayout grid = new GridLayout(4, 10);

        grid.setSizeFull();

        grid.addComponent(heading, 1, 0);
        grid.addComponent(comboBoxSelectContractType, 0, 1);
        grid.addComponent(comboBoxSelectCustomer, 1, 1);
        grid.addComponent(comboBoxSelectSite, 2, 1);


        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        addComponent(grid);
    }

    public void loadCustomerSites(String customerId) {
        Customer customer = CustomerFacade.getCustomerService().findById(customerId);
        comboBoxSelectSite.removeAllItems();
        if (customer != null) {
            if (customer.getSites() != null) {
                for (Site site : customer.getSites()) {
                    comboBoxSelectSite.addItem(site.getId());
                    comboBoxSelectSite.setItemCaption(site.getId(), site.getName());
                }
            }
        }
    }

    public void loadCustomersByContract(String contractType) {
        comboBoxSelectCustomer.removeAllItems();
        List<Customer> customerList = CustomerFacade.getCustomerService().findByContractType(contractType);
        for (Customer customer : customerList) {
            comboBoxSelectCustomer.addItem(customer.getId());
            comboBoxSelectCustomer.setItemCaption(customer.getId(), customer.getName());
        }
    }
}

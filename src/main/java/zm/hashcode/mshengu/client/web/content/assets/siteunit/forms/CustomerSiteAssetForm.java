/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.util.List;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.setup.user.util.CustomerSiteUnitBean;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.products.Site;

/**
 * I
 *
 * @author Ferox
 */
public class CustomerSiteAssetForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private UIComponentHelper UIComponent = new UIComponentHelper();
    private final CustomerSiteUnitBean bean;
    public final BeanItem<CustomerSiteUnitBean> item;
    public final FieldGroup binder;
    public ComboBox comboBoxSelectContractType;
    public ComboBox comboBoxSelectSite;
    public ComboBox comboBoxSelectCustomer;
    private TextField qtyAsServiceSchedule;
    private TextField qtyDeployed;
    private TextField unitIdRange;
    private TextField customerTotal;

    public CustomerSiteAssetForm() {
        bean = new CustomerSiteUnitBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);


//        Label heading = new Label("SET UP SERVICE SCHEDULE");
//        heading.setSizeUndefined();
//        heading.addStyleName("h4");

        comboBoxSelectContractType = UIComboBox.getContractTypeComboBox("Select Hire Type", "contractType", CustomerSiteUnitBean.class, binder);
        comboBoxSelectContractType.setStyleName("default");

        comboBoxSelectSite = new ComboBox("Select Site");
        comboBoxSelectSite.setImmediate(true);
        comboBoxSelectSite.setStyleName("default");

        comboBoxSelectCustomer = new ComboBox("Select Customer");
        comboBoxSelectCustomer.setStyleName("default");
        comboBoxSelectCustomer.setImmediate(true);

        qtyAsServiceSchedule = UIComponent.getTextField("Quantity Planed", "qtyAsServiceSchedule", CustomerSiteUnitBean.class, binder);
        qtyDeployed = UIComponent.getTextField("Quantity Deployed", "qtyDeployed", CustomerSiteUnitBean.class, binder);
        unitIdRange = UIComponent.getTextField("Units Id Range", "unitIdRange", CustomerSiteUnitBean.class, binder);
        customerTotal = UIComponent.getTextField("", "customerTotal", CustomerSiteUnitBean.class, binder);



        GridLayout grid = new GridLayout(4, 10);

        grid.setSizeFull();

        grid.addComponent(comboBoxSelectContractType, 0, 0);
        grid.addComponent(comboBoxSelectCustomer, 1, 0);
        grid.addComponent(comboBoxSelectSite, 2, 0);

        grid.addComponent(qtyAsServiceSchedule, 0, 1);
        grid.addComponent(qtyDeployed, 1, 1);
        grid.addComponent(unitIdRange, 2, 1);
        grid.addComponent(customerTotal, 3, 1);


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

    public void setSiteTotals(String plannedQty, String deployedQty, String unitRange, int custTotal) {
        setReadOnlyState(false);
        qtyAsServiceSchedule.setValue(plannedQty);
        qtyDeployed.setValue(deployedQty);
        unitIdRange.setValue(unitRange);
        customerTotal.setValue("" + custTotal);
        setReadOnlyState(true);


    }

    private void setReadOnlyState(boolean state) {
        qtyAsServiceSchedule.setReadOnly(state);
        qtyDeployed.setReadOnly(state);
        unitIdRange.setReadOnly(state);
        customerTotal.setReadOnly(state);
    }
}

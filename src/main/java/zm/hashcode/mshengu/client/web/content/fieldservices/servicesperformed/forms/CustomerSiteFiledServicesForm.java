/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.content.setup.user.util.CustomerSiteUnitBean;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.products.Site;

/**
 * I
 *
 * @author Ferox
 */
public class CustomerSiteFiledServicesForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final DateTimeFormatWeeklyHelper dtfwh;
    private final CustomerSiteUnitBean bean;
    public final BeanItem<CustomerSiteUnitBean> item;
    public final FieldGroup binder;
    public ComboBox comboBoxSelectContractType;
    public Button btnLoadLogs = new Button("Load Service Performed Logs");
    public ComboBox comboBoxSelectSite;
    public ComboBox comboBoxSelectCustomer;
    public DateField startDate;
    public DateField endDate;
    public Date date;
    public Label errorMessage;

    public CustomerSiteFiledServicesForm() {
        bean = new CustomerSiteUnitBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);

        this.dtfwh = new DateTimeFormatWeeklyHelper();
        date = new Date();
//        dtfwh.setDate(new Date());
        dtfwh.setDate(date);
        Label heading = new Label("Services Performed");
        heading.setSizeUndefined();
        heading.addStyleName("h4");

        comboBoxSelectContractType = UIComboBox.getContractTypeComboBox("Select Hire Type", "contractType", CustomerSiteUnitBean.class, binder);
        comboBoxSelectContractType = UIValidatorHelper.setRequiredComboBox(comboBoxSelectContractType, "Select Hire Type");
        comboBoxSelectSite = new ComboBox("Select Site");
        comboBoxSelectCustomer = new ComboBox("Select Customer");
        startDate = UIComponent.getDateField("Start Date", "startDate", CustomerSiteUnitBean.class, binder);
        endDate = UIComponent.getDateField("End Date", "endDate", CustomerSiteUnitBean.class, binder);
        comboBoxSelectSite.setImmediate(true);
        comboBoxSelectCustomer.setImmediate(true);
        
        
        
        startDate.setValue(dtfwh.resetTimeAndMonthStart(date));
        endDate.setValue(dtfwh.resetTimeAndMonthEnd(date));

        errorMessage = UIComponent.getErrorLabel();

        GridLayout grid = new GridLayout(4, 10);

        grid.setSizeFull();

        grid.addComponent(errorMessage, 1, 0, 2, 0);

        grid.addComponent(heading, 1, 1);
        grid.addComponent(comboBoxSelectContractType, 0, 2);
        grid.addComponent(comboBoxSelectCustomer, 1, 2);
        grid.addComponent(comboBoxSelectSite, 2, 2);
        grid.addComponent(startDate, 0, 3);
        grid.addComponent(endDate, 1, 3);
//        grid.addComponent(btnLoadLogs, 1,4, 2, 4);
        grid.setMargin(true);
        grid.setSpacing(true);
//        grid.set
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 5, 2, 5);
        grid.addComponent(getButtons(), 0, 6, 2, 6);
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
    

    

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();

        btnLoadLogs.setSizeFull();        
        btnLoadLogs.setStyleName("default");      

        buttons.addComponent(btnLoadLogs);
        return buttons;
    }
}

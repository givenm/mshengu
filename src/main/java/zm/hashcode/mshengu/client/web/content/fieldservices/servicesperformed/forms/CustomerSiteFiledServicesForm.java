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
import java.util.Formatter;
import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.customer.ContractTypeFacade;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.facade.products.SiteServiceLogFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.content.setup.user.util.CustomerSiteUnitBean;
import zm.hashcode.mshengu.domain.customer.ContractType;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;

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

    private final Label totalSitesValue;
    private final Label totalUnitsValue;
    private final Label totalCompletionRateValue;
    private final Label totalServicesValue;
    private final Label totalScheduledServicesValue;
    private final Label totalMissedServicesValue;
    private final Label siteServiceLogValue;
    private final Label toDateValue;
    private final Label fromDateValue;
    /* global variables*/
    private int totalSitesServicedGlobal = 0;
    private int totalUnitsServicedGlobal = 0;
    private int totalUnitsServiceMissedGlobal = 0;
    private int totalServicesPerformedGlobal = 0;
    private int totalMissedServicesGlobal = 0;
    private int scheduledServicesGlobal = 0;
    private double serviceCompletionRateGlobal = 0;

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

        Label siteServiceLogLabel = new Label("SITE SERVICE LOG:");
        siteServiceLogLabel.setStyleName("labelBlue");

        siteServiceLogValue = new Label("");

        Label fromDateLabel = new Label("From:");
        fromDateLabel.setStyleName("labelBlue");

        fromDateValue = new Label("");

        Label toDateLabel = new Label("To:");
        toDateLabel.setStyleName("labelBlue");

        toDateValue = new Label("");

        GridLayout gridTotals1 = new GridLayout(7, 4);
        gridTotals1.setSizeFull();
        gridTotals1.addComponent(siteServiceLogLabel, 0, 0);
        gridTotals1.addComponent(siteServiceLogValue, 1, 0, 2, 0);
        gridTotals1.addComponent(fromDateLabel, 3, 0);
        gridTotals1.addComponent(fromDateValue, 4, 0);
        gridTotals1.addComponent(toDateLabel, 5, 0);
        gridTotals1.addComponent(toDateValue, 6, 0);

        Label totalSitesLabel = new Label("Total Sites Serviced:");
        totalSitesLabel.setStyleName("extramarginLabel labelBlue");

        totalSitesValue = new Label("");
        totalSitesValue.setStyleName("extramarginLabel");

        Label totalUnitsLabel = new Label("Total Units Serviced:");
        totalUnitsLabel.setStyleName("extramarginLabel labelBlue");

        totalUnitsValue = new Label("");
        totalUnitsValue.setStyleName("extramarginLabel");

        Label totalServicesLabel = new Label("Total Services Performed:");
        totalServicesLabel.setStyleName("extramarginLabel labelBlue");

        totalServicesValue = new Label("");
        totalServicesValue.setStyleName("extramarginLabel");

        Label totalScheduledServicesLabel = new Label("Scheduled Services:");
        totalScheduledServicesLabel.setStyleName("extramarginLabel labelBlue");

        totalScheduledServicesValue = new Label("");
        totalScheduledServicesValue.setStyleName("extramarginLabel");

        Label totalMissedServicesLabel = new Label("Missed Services:");
        totalMissedServicesLabel.setStyleName("extramarginLabel labelBlue");

        totalMissedServicesValue = new Label("");
        totalMissedServicesValue.setStyleName("extramarginLabel");

        Label totalCompletionRateLabel = new Label("Service Completion Rate:");
        totalCompletionRateLabel.setStyleName("extramarginLabel labelBlue");

        totalCompletionRateValue = new Label("");
        totalCompletionRateValue.setStyleName("extramarginLabel");

        GridLayout gridTotals2 = new GridLayout(6, 4);

        gridTotals2.setSizeFull();
        gridTotals2.addComponent(totalSitesLabel, 0, 0);
        gridTotals2.addComponent(totalSitesValue, 1, 0);
        gridTotals2.addComponent(totalUnitsLabel, 2, 0);
        gridTotals2.addComponent(totalUnitsValue, 3, 0);
        gridTotals2.addComponent(totalServicesLabel, 4, 0);
        gridTotals2.addComponent(totalServicesValue, 5, 0);

        gridTotals2.addComponent(totalScheduledServicesLabel, 0, 1);
        gridTotals2.addComponent(totalScheduledServicesValue, 1, 1);
        gridTotals2.addComponent(totalMissedServicesLabel, 2, 1);
        gridTotals2.addComponent(totalMissedServicesValue, 3, 1);
        gridTotals2.addComponent(totalCompletionRateLabel, 4, 1);
        gridTotals2.addComponent(totalCompletionRateValue, 5, 1);

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
        grid.addComponent(getButtons(), 2, 3);
        grid.setMargin(true);
        grid.setSpacing(true);
//        grid.set
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 5, 2, 5);
        grid.addComponent(gridTotals1, 0, 6, 2, 6);
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 7, 2, 7);
        grid.addComponent(gridTotals2, 0, 8, 2, 8);
        addComponent(grid);
    }

    public void loadCustomerSites(String customerId) {
        Customer customer = CustomerFacade.getCustomerService().findById(customerId);
        comboBoxSelectSite.removeAllItems();
//         omboBoxSelectSite.setItemCaption("All", "All");
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
        comboBoxSelectCustomer.addItem("All");
        comboBoxSelectCustomer.setItemCaption("All", "All");
        List<Customer> customerList = CustomerFacade.getCustomerService().findByContractType(contractType);
        for (Customer customer : customerList) {
            comboBoxSelectCustomer.addItem(customer.getId());
            comboBoxSelectCustomer.setItemCaption(customer.getId(), customer.getName());
        }
    }

    public void displayTotals(String siteName, Date start, Date end, String optionalContractType) {
        int totalSitesServiced = 0;
        int totalUnitsServiced = 0;
        int totalUnitsNotServiced = 0;
        int totalServicesPerformed = 0;
        int totalServicesNotPerformed = 0;
        int scheduledServices = 0;
        int missedServices = 0;
        float serviceCompletionRate;
        List<SiteServiceLog> siteServiceLogs = null;
        if (siteName.equals("All")) {
            ContractType contractType = ContractTypeFacade.getContractTypeService().findById(optionalContractType);

            List<Customer> customers = CustomerFacade.getCustomerService().findByContractType(contractType.getType());
            for (Customer customer : customers) {
                Set<Site> sites = customer.getSites();
                if (sites != null) {
                    for (Site site : sites) {
//                        siteServiceLogs = SiteServiceLogFacade.getSiteServiceLogService().getAllSiteServiceLogs(site.getName(), start, end);
//                        Set<SiteServiceLog> siteServiceLog = site.getSiteServiceLog();
                        calculateSiteTotals(siteServiceLogs, totalUnitsServiced, totalUnitsNotServiced, missedServices, totalServicesPerformed, site.getName(), start, end);
                    }
                }

            }

        } else {
            calculateSiteTotals(siteServiceLogs, totalUnitsServiced, totalUnitsNotServiced, missedServices, totalServicesPerformed, siteName, start, end);
            Site site = SiteFacade.getSiteService().findById(comboBoxSelectSite.getValue().toString());
            siteServiceLogValue.setValue(site.getName());
        }
        fromDateValue.setValue(getDate(start));
        toDateValue.setValue(getDate(end));

        totalUnitsValue.setValue(totalUnitsServicedGlobal + "");
        totalServicesValue.setValue(totalServicesPerformedGlobal + "");
        totalMissedServicesValue.setValue(totalMissedServicesGlobal + "");
        totalScheduledServicesValue.setValue(scheduledServicesGlobal + "");
        serviceCompletionRateGlobal = (totalServicesPerformedGlobal / Float.parseFloat(scheduledServicesGlobal + "")) * 100;
        totalCompletionRateValue.setValue(String.format("%.2f", serviceCompletionRateGlobal));

        totalUnitsServicedGlobal = 0;
        totalUnitsServiceMissedGlobal = 0;
        totalServicesPerformedGlobal = 0;
        totalMissedServicesGlobal = 0;
        scheduledServicesGlobal = 0;
        serviceCompletionRateGlobal = 0.0;

    }

    private void calculateSiteTotals(List<SiteServiceLog> siteServiceLogs, int totalUnitsServiced, int totalUnitsNotServiced,
            int missedServices, int totalServicesPerformed, String siteName, Date start, Date end) {

        siteServiceLogs = SiteServiceLogFacade.getSiteServiceLogService().getAllSiteServiceLogs(siteName, start, end);

        if (siteServiceLogs != null) {
            for (SiteServiceLog log : siteServiceLogs) {
                int planned = log.getNumberOfUnitsNotServiced() + log.getNumberOfUnitsServiced();
                totalUnitsServiced += log.getNumberOfUnitsServiced();

                totalUnitsNotServiced += log.getNumberOfUnitsNotServiced();

//                scheduledServicesGlobal += log.
                if (log.getCompletionStatus().equalsIgnoreCase("NOT_STARTED")) {
                    missedServices += 1;
                } else {
                    totalServicesPerformed += 1;
                }

            }
        }
        totalSitesServicedGlobal += 0;
        totalUnitsServicedGlobal += totalUnitsServiced;
        totalUnitsServiceMissedGlobal += totalUnitsNotServiced;
        totalServicesPerformedGlobal += totalServicesPerformed;
        totalMissedServicesGlobal += missedServices;
        scheduledServicesGlobal += missedServices + totalServicesPerformed;

    }

    /*start calculateGlobalTotals*/
    public void calculateTotals(Date startDate, Date endDate) {
        List<Site> sites = SiteFacade.getSiteService().findAll();
        for (Site site : sites) {
            calculateSiteTotals(site.getId(), site.getName(), site.getLastSiteServiceContractLifeCycle(), startDate, endDate);

        }

        //Service Completion Rate = //this will depend on how you calculate the scheduledServicesPerSite;
    } /*end calculateGlobalTotals*/



    /*start calculateTotalsPerSite*/
    public void calculateSiteTotals(String siteId, String siteName, SiteServiceContractLifeCycle lastContractLifeCycle, Date startDate, Date endDate) {
        int totalUnitsServicedPerSite = 0;
        int totalUnitsServiceMissedLocal = 0;
        int totalServicesPerformedPerSite = 0;
        int totalMissedServicesPersite = 0;
        int scheduledServicesPerSite = 0;

        List<SiteServiceLog> siteServiceLogsList = SiteServiceLogFacade.getSiteServiceLogService().getAllSiteServiceLogs(siteId, startDate, endDate);
        for (SiteServiceLog siteServiceLog : siteServiceLogsList) {
            totalUnitsServicedPerSite += siteServiceLog.getNumberOfUnitsServiced();
            totalUnitsServiceMissedLocal += siteServiceLog.getNumberOfUnitsNotServiced();
            if (siteServiceLog.getCompletionStatus().equalsIgnoreCase("NOT_STARTED")) {
                totalMissedServicesPersite += 1;
            } else {
                totalServicesPerformedPerSite += 1;
            }
            scheduledServicesPerSite = calculateScheduledServices(lastContractLifeCycle, startDate, endDate);

        }

        totalSitesServicedGlobal += 1;
        totalUnitsServicedGlobal = totalUnitsServicedPerSite;
        totalUnitsServiceMissedGlobal = totalMissedServicesPersite;
        totalServicesPerformedGlobal = totalServicesPerformedPerSite;
        totalMissedServicesGlobal = totalMissedServicesPersite;
        scheduledServicesGlobal = scheduledServicesPerSite;

        totalUnitsValue.setValue(totalUnitsServicedPerSite + "");
        totalMissedServicesValue.setValue(totalMissedServicesPersite + "");
    }
    /*end calculateTotalsPerSite*/


    /*start calculateScheduledServices*/
    public int calculateScheduledServices(SiteServiceContractLifeCycle lastContractLifeCycles, Date tartDate, Date endDate) {
//        totalVisitsExpected = (use lastContractLifeCycles  and the dates to calculate how many times this site should be visited from startDate to endDate
//            )
//      
//    if scheduledServicesPerSite        {
//            is 
//        }
//        the number of times this site should be serviced then(a)   scheduledServicesPerSite = totalVisitsExpected
//    else if scheduledServicesPerSite        {
//                    is 
//                }
//        the sum of all expected toilet unit services then(b
//        ) scheduledServicesPerSite = totalVisitsExpected * lastContractLifeCycles.getExpectedNumberOfUnits();

//        return scheduledServicesPerSite;
        return 0;

    }
    /*end calculateScheduledServices*/

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();

        btnLoadLogs.setSizeFull();
        btnLoadLogs.setStyleName("default extramargin");

        buttons.addComponent(btnLoadLogs);
        return buttons;
    }

    private String getDate(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }
}

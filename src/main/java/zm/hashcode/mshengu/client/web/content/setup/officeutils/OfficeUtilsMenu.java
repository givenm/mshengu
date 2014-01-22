/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.contracttype.views.ContractTypeTab;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.views.CostCentreTypeTab;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.paymentmethod.views.PaymentMethodTab;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.status.views.StatusTab;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.status.views.StatusTypeTab;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.terminate.views.TerminatingTab;
import zm.hashcode.mshengu.client.web.content.setup.sequence.views.SequenceTab;
import zm.hashcode.mshengu.client.web.content.setup.sequence.views.SequenceTypeTab;

/**
 *
 * @author Ferox
 */
public class OfficeUtilsMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public OfficeUtilsMenu(MshenguMain app, String selectedTab) {
        main = app;

        VerticalLayout contractTypeTab = new VerticalLayout();
        contractTypeTab.setMargin(true);
        contractTypeTab.addComponent(new ContractTypeTab(main));

        VerticalLayout paymentMethodTab = new VerticalLayout();
        paymentMethodTab.setMargin(true);
        paymentMethodTab.addComponent(new PaymentMethodTab(main));

        VerticalLayout sequenceTypeTab = new VerticalLayout();
        sequenceTypeTab.setMargin(true);
        sequenceTypeTab.addComponent(new SequenceTypeTab(main));

        VerticalLayout sequenceTab = new VerticalLayout();
        sequenceTab.setMargin(true);
        sequenceTab.addComponent(new SequenceTab(main));

        VerticalLayout statusTypeTab = new VerticalLayout();
        statusTypeTab.setMargin(true);
        statusTypeTab.addComponent(new StatusTypeTab(main));

        VerticalLayout statusTab = new VerticalLayout();
        statusTab.setMargin(true);
        statusTab.addComponent(new StatusTab(main));

        VerticalLayout terminateTab = new VerticalLayout();
        terminateTab.setMargin(true);
        terminateTab.addComponent(new TerminatingTab(main));

        VerticalLayout costCentreTab = new VerticalLayout();
        costCentreTab.setMargin(true);
        costCentreTab.addComponent(new CostCentreTypeTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(contractTypeTab, "Contract Type", null);
        tab.addTab(paymentMethodTab, "Payment Method", null);
        tab.addTab(sequenceTab, "Sequence", null);
        tab.addTab(sequenceTypeTab, "Sequences Type", null);
        tab.addTab(statusTab, "Status", null);
        tab.addTab(statusTypeTab, "Status Type", null);
        tab.addTab(terminateTab, "Terminate Type", null);
        tab.addTab(costCentreTab, "Cost Centre Setup", null);
        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(contractTypeTab);
                break;
            case "PAYMENT":
                tab.setSelectedTab(paymentMethodTab);
                break;
            case "SEQUENCE":
                tab.setSelectedTab(sequenceTab);
                break;
            case "SEQUENCE_TYPE":
                tab.setSelectedTab(sequenceTypeTab);
                break;
            case "STATUS":
                tab.setSelectedTab(statusTab);
                break;
            case "STATUS_TYPE":
                tab.setSelectedTab(statusTypeTab);
                break;
            case "TERMINATE_TYPE":
                tab.setSelectedTab(terminateTab);
                break;
            case "COSTCENTRE_TYPE":
                tab.setSelectedTab(costCentreTab);
                break;
        }
        addComponent(tab);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author boniface
 */
public class Sidebar extends Accordion implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final String KPI_ANALYSIS_MENU = "KPI ANALYSIS";
    public static final String FIELD_SERVICES_MENU = "FIELD SERVICES";
    public static final String FLEET_MENU = "FLEET MANAGEMENT";
    public static final String CHEMICAL_USAGE = "CHEMICAL USAGE";
    public static final String ASSET_MANAGEMENT = "ASSET MANAGEMENT";
    public static final String PROCUREMENT_MENU = "PROCUREMENT";
    public static final String HUMAN_RESOURCES = "HUMAN RESOURCES";
    public static final String HR_MENU = "HUMAN RESOURCES";
    public static final String BILLING = "BILLING";
    public static final String UTILS = "UTILITIES ";
    public static final String PASSWORD = "CHANGE PASSWORD ";

    public Sidebar(MshenguMain app) {
        setSizeFull();
        setHeight("350px");
        this.main = app;



        //Configure KPI Menu
        VerticalLayout kipMenu = new VerticalLayout();
        KPITree kpiTree = new KPITree(main);
//        KPIMenu kpiTree  = new KPIMenu(main, "LANDING");
        kipMenu.addComponent(kpiTree);

//        kipMenu. addValueChangeListener((ItemClickEvent.ItemClickListener) this);

        //FIELD SERVICES
        VerticalLayout fieldServicesMenu = new VerticalLayout();
        FieldServicesTree fieldServicesTree = new FieldServicesTree(main);
        fieldServicesMenu.addComponent(fieldServicesTree);

        //Configure Customers Management
//        VerticalLayout customerMenu = new VerticalLayout();
//        CustomersTree CustomersTree = new CustomersTree(main);
//        customerMenu.addComponent(CustomersTree);



        //Configure Fleet Management
        VerticalLayout fleetMenu = new VerticalLayout();
        FleetTree fleetTree = new FleetTree(main);
        fleetMenu.addComponent(fleetTree);


//        //Configure Manage People Menu
        VerticalLayout chemicalsMenu = new VerticalLayout();
        ChemicalTree chemicalTree = new ChemicalTree(main);
        chemicalsMenu.addComponent(chemicalTree);


        // Configure Manage Assets
        VerticalLayout assetMenu = new VerticalLayout();
        AssetTree assetTree = new AssetTree(main);
        assetMenu.addComponent(assetTree);



//
//        //Configure Procuremente Menu
        VerticalLayout procurementMenu = new VerticalLayout();
        ProcurementTree procurementTree = new ProcurementTree(main);
        procurementMenu.addComponent(procurementTree);


        //Configure Chemical Usage
        VerticalLayout humanResourcesMenu = new VerticalLayout();
        HumanResourcesTree humanResourcesTree = new HumanResourcesTree(main);
        humanResourcesMenu.addComponent(humanResourcesTree);

        VerticalLayout billingMenu = new VerticalLayout();
        BillingTree billingTree = new BillingTree(main);
        billingMenu.addComponent(billingTree);

        VerticalLayout setupMenu = new VerticalLayout();
        SetupTree setupTree = new SetupTree(main);
        setupMenu.addComponent(setupTree);

        VerticalLayout changePasswordMenu = new VerticalLayout();
        PasswordTree passwordTree = new PasswordTree(main);
        changePasswordMenu.addComponent(passwordTree);

        // Add the components as tabs in the Accordion.

        addTab(kipMenu, KPI_ANALYSIS_MENU, null);
        addTab(fieldServicesMenu, FIELD_SERVICES_MENU, null);
        addTab(fleetMenu, FLEET_MENU, null);
        addTab(chemicalsMenu, CHEMICAL_USAGE, null);

        addTab(assetMenu, ASSET_MANAGEMENT, null);
        addTab(procurementMenu, PROCUREMENT_MENU, null);
        addTab(humanResourcesMenu, HR_MENU, null);
        addTab(billingMenu, BILLING, null);
        addTab(setupMenu, UTILS, null);
        addTab(passwordTree, PASSWORD, null);

    }

    @Override
    public void itemClick(ItemClickEvent event) {
    }
}

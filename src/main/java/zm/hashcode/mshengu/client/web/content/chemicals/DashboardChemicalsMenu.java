/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.chemicals;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.chemicals.views.CaprichemTab;
import zm.hashcode.mshengu.client.web.content.chemicals.views.ChemicalCostTab;
import zm.hashcode.mshengu.client.web.content.chemicals.views.ChemicalsExecutiveDashboardTab;
import zm.hashcode.mshengu.client.web.content.chemicals.views.DynachemTab;
import zm.hashcode.mshengu.client.web.content.chemicals.views.GabochemTab;

/**
 *
 * @author ColinWa
 */
public class DashboardChemicalsMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private ChemicalsExecutiveDashboardTab executiveDashboardTab;
    private ChemicalCostTab chemicalCostTab;
    private CaprichemTab caprichemTab;
    private DynachemTab dynachemTab;
    private GabochemTab gabochemTab;
//    private CostComparisonTab costComparisonTab;

    public DashboardChemicalsMenu(MshenguMain app, String selectedTab) {
        main = app;

        executiveDashboardTab = new ChemicalsExecutiveDashboardTab(main);
        chemicalCostTab = new ChemicalCostTab(main);
        caprichemTab = new CaprichemTab(main);
        dynachemTab = new DynachemTab(main);
        gabochemTab = new GabochemTab(main);
//        costComparisonTab = new CostComparisonTab(main);


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(executiveDashboardTab, "Executive Dashboard", null);
        tab.addTab(chemicalCostTab, "Total Chemical Cost", null);
        tab.addTab(caprichemTab, "Caprichem Consolidated", null);
        tab.addTab(dynachemTab, "Dynachem Consolidated", null);
        tab.addTab(gabochemTab, "Gabochem Consolidated", null);
//        tab.addTab(costComparisonTab, "Cost Comparison", null);
        switch (selectedTab) {
            case "EXECUTIVE":
                tab.setSelectedTab(executiveDashboardTab);
                break;
            case "CHEM_COST":
                tab.setSelectedTab(chemicalCostTab);
                break;
            case "CAPRICHEM":
                tab.setSelectedTab(caprichemTab);
                break;
            case "DYNACHEM":
                tab.setSelectedTab(dynachemTab);
                break;
            case "GABOCHEM":
                tab.setSelectedTab(gabochemTab);
                break;

//            case "COMPARISON":
//                tab.setSelectedTab(costComparisonTab);
//                break;
        }

        addComponent(tab);
    }
}

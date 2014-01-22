/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.chemicals.DashboardChemicalsMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.FleetMaintenanceMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.FleetFuelMenu;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;

/**
 *
 * @author boniface
 */
public class KPITree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object KPI_ANALYSIS = "KPI ANALISIS";
    public static final Object FLEET_MENU = "Fleet Maintenance Analysis";
    public static final Object FUEL_MENU = "Petrol Usage Analysis";
    public static final Object CHEMICALS_MENU = "Chemicals Usage Analysis";
    private static final String LANDING_TAB = "LANDING";

    public KPITree(MshenguMain main) {
        this.main = main;
        addItem(KPI_ANALYSIS);
//        addItem(FLEET_MENU);
//        addItem(FUEL_MENU);
//        addItem(CHEMICALS_MENU);
//        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
        kpiAnalysisDasboard();
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (KPI_ANALYSIS.equals(itemId)) {
                kpiAnalysisDasboard();
            }else if (FLEET_MENU.equals(itemId)) {
                fleetAnalysisDasboard();
            }else if (FUEL_MENU.equals(itemId)) {
                fuelAnalysisDasboard();
            }else if (CHEMICALS_MENU.equals(itemId)) {
                chemicalsDasboard();
            }
        }
    }

    private void kpiAnalysisDasboard() {
        main.content.setSecondComponent(new KPIMenu(main, LANDING_TAB));

    }
     private void fleetAnalysisDasboard() {
        main.content.setSecondComponent(new FleetMaintenanceMenu(main, LANDING_TAB));

    }
      private void fuelAnalysisDasboard() {
        main.content.setSecondComponent(new FleetFuelMenu(main, LANDING_TAB));

    }
       private void chemicalsDasboard() {
        main.content.setSecondComponent(new DashboardChemicalsMenu(main, LANDING_TAB));

    }
}

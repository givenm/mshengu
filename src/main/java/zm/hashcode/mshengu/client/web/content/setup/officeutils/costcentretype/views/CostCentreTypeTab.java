/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.views;

/**
 *
 * @author Luckbliss
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Ferox
 */
public class CostCentreTypeTab extends VerticalLayout  {

    private final MshenguMain main;
    private final SetupCostCentreTab tab;

    public CostCentreTypeTab(MshenguMain app) {
        main = app;
        tab = new SetupCostCentreTab(main, "LANDING");
        setSizeFull();
        addComponent(tab);
    }
}

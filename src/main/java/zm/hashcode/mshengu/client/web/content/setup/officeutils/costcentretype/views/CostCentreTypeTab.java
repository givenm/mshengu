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

    private final MshenguMain app;
    private final SetupCostCentreTab tab;

    public CostCentreTypeTab(MshenguMain app) {
        this.app = app;
        tab = new SetupCostCentreTab(app, "LANDING");
        setSizeFull();
        addComponent(tab);
    }
}

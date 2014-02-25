/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Colin
 */
public class FuelExecutiveDashboardChartUI extends FormLayout {

    public VerticalLayout chartVerticalLayout = new VerticalLayout();

    public FuelExecutiveDashboardChartUI(MshenguMain main) {
        chartVerticalLayout.setSizeFull();
        addComponent(chartVerticalLayout);
    }
}

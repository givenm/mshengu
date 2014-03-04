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
public class ServiceFleetDashboardChartUI extends FormLayout {

    public VerticalLayout chartVerticalLayout = new VerticalLayout();

    public ServiceFleetDashboardChartUI(MshenguMain main) {
//        chartVerticalLayout.setSizeFull();
        setMargin(true);
        setSizeUndefined();
        addComponent(chartVerticalLayout);
    }
}

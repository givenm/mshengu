/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Colin
 */
public class MonthlyFuelExpenseUI extends HorizontalLayout {

    public VerticalLayout tableVerticalLayout = new VerticalLayout();
    public VerticalLayout chartVerticalLayout = new VerticalLayout();

    public MonthlyFuelExpenseUI(MshenguMain main) {
        setMargin(true);
        setSpacing(true);
        setSizeUndefined();

        tableVerticalLayout.setSizeUndefined();
        chartVerticalLayout.setSizeUndefined();
        addComponent(tableVerticalLayout);
        addComponent(chartVerticalLayout);
    }
}

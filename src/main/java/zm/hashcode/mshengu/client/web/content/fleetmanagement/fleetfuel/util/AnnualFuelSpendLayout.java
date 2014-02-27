/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;

/**
 *
 * @author Colin
 */
public class AnnualFuelSpendLayout extends VerticalLayout {

    public VerticalLayout getFuelSpendLayout(BigDecimal annualTotalFuelSpend, BigDecimal serviceTotalFuelSpend,
            BigDecimal operationalTotalFuelSpend, BigDecimal nonOperationalTotalFuelSpend,
            BigDecimal serviceFuelSpendPercentage, BigDecimal operationalFuelSpendPercentage, BigDecimal nonOperationalFuelSpendPercentage) {
        //=====================
        HorizontalLayout annualTotalHorizontalLayout = new HorizontalLayout();
//        annualTotalHorizontalLayout.setSizeFull();
        Label totalLabel = new Label("Total");
        totalLabel.setWidth(110, Unit.PIXELS);
//        totalLabel.setStyleName("dashboardLabel");

        Label totalAmountLabel = new Label(annualTotalFuelSpend + "");
        totalAmountLabel.setWidth(100, Unit.PIXELS);
        totalAmountLabel.setStyleName("dashboardLabel");

        annualTotalHorizontalLayout.addComponent(totalLabel);
        annualTotalHorizontalLayout.addComponent(totalAmountLabel);
        //        annualTotalHorizontalLayout.setComponentAlignment(totalLabel, Alignment.MIDDLE_LEFT);
        annualTotalHorizontalLayout.setSizeUndefined();
        //=====================
        HorizontalLayout serviceTotalHorizontalLayout = new HorizontalLayout();
//        serviceTotalHorizontalLayout.setSizeFull();

        Label serviceTotalLabel = new Label("Service");
        serviceTotalLabel.setWidth(110, Unit.PIXELS);
//        serviceTotalLabel.setStyleName("dashboardLabel");

        Label serviceAmountLabel = new Label(serviceTotalFuelSpend + "");
        serviceAmountLabel.setWidth(100, Unit.PIXELS);
        serviceAmountLabel.setStyleName("dashboardLabel");

        Label servicePercentageLabel = new Label(serviceFuelSpendPercentage + "%");
        servicePercentageLabel.setWidth(55, Unit.PIXELS);
        servicePercentageLabel.setStyleName("dashboardPercentageLabel");

        serviceTotalHorizontalLayout.addComponent(serviceTotalLabel);
        serviceTotalHorizontalLayout.addComponent(serviceAmountLabel);
        serviceTotalHorizontalLayout.addComponent(servicePercentageLabel);
//        serviceTotalHorizontalLayout.setComponentAlignment(serviceTotalLabel, Alignment.MIDDLE_LEFT);
//        serviceTotalHorizontalLayout.setComponentAlignment(serviceAmountLabel, Alignment.MIDDLE_CENTER);
//        serviceTotalHorizontalLayout.setComponentAlignment(servicePercentageLabel, Alignment.MIDDLE_RIGHT);
        serviceTotalHorizontalLayout.setSizeUndefined();
        //=====================
        HorizontalLayout operationalTotalHorizontalLayout = new HorizontalLayout();

        Label operationalTotalLabel = new Label("Operational");
        operationalTotalLabel.setWidth(110, Unit.PIXELS);
//        totalLabel.setStyleName("dashboardLabel");

        Label operationalAmountLabel = new Label(operationalTotalFuelSpend + "");
        operationalAmountLabel.setWidth(100, Unit.PIXELS);
        operationalAmountLabel.setStyleName("dashboardLabel");

        Label operationalPercentageLabel = new Label(operationalFuelSpendPercentage + "%");
        operationalPercentageLabel.setWidth(55, Unit.PIXELS);
        operationalPercentageLabel.setStyleName("dashboardPercentageLabel");

        operationalTotalHorizontalLayout.addComponent(operationalTotalLabel);
        operationalTotalHorizontalLayout.addComponent(operationalAmountLabel);
        operationalTotalHorizontalLayout.addComponent(operationalPercentageLabel);
        operationalTotalHorizontalLayout.setSizeUndefined();

        //=====================
        HorizontalLayout nonOperationalTotalHorizontalLayout = new HorizontalLayout();

        Label nonOperationalTotalLabel = new Label("Non-Operational");
        nonOperationalTotalLabel.setWidth(110, Unit.PIXELS);
//        totalLabel.setStyleName("dashboardLabel");

        Label nonOperationalAmountLabel = new Label(nonOperationalTotalFuelSpend + "");
        nonOperationalAmountLabel.setWidth(100, Unit.PIXELS);
        nonOperationalAmountLabel.setStyleName("dashboardLabel");

        Label nonOperationalPercentageLabel = new Label(nonOperationalFuelSpendPercentage + "%");
        nonOperationalPercentageLabel.setWidth(55, Unit.PIXELS);
        nonOperationalPercentageLabel.setStyleName("dashboardPercentageLabel");

        nonOperationalTotalHorizontalLayout.addComponent(nonOperationalTotalLabel);
        nonOperationalTotalHorizontalLayout.addComponent(nonOperationalAmountLabel);
        nonOperationalTotalHorizontalLayout.addComponent(nonOperationalPercentageLabel);
//        serviceTotalHorizontalLayout.setComponentAlignment(serviceTotalLabel, Alignment.MIDDLE_LEFT);
//        serviceTotalHorizontalLayout.setComponentAlignment(serviceAmountLabel, Alignment.MIDDLE_CENTER);
//        serviceTotalHorizontalLayout.setComponentAlignment(servicePercentageLabel, Alignment.MIDDLE_RIGHT);
        nonOperationalTotalHorizontalLayout.setSizeUndefined();
        //====================
        addComponent(annualTotalHorizontalLayout);
        addComponent(serviceTotalHorizontalLayout);
        addComponent(operationalTotalHorizontalLayout);
        addComponent(nonOperationalTotalHorizontalLayout);
        // These are IMPORTANT, else Panels will have Scrollbars
        setMargin(true);
        setSizeUndefined();

        return this;
    }
}

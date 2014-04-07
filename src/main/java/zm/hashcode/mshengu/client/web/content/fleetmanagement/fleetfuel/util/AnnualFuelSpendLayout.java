/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author Colin
 */
public class AnnualFuelSpendLayout extends VerticalLayout {

    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public VerticalLayout getFuelSpendLayout(BigDecimal annualTotalFuelSpend, BigDecimal serviceTotalFuelSpend, BigDecimal utilityTotalFuelSpend,
            BigDecimal operationalTotalFuelSpend, BigDecimal nonOperationalTotalFuelSpend, BigDecimal serviceFuelSpendPercentage, BigDecimal utilityFuelSpendPercentage,
            BigDecimal operationalFuelSpendPercentage, BigDecimal nonOperationalFuelSpendPercentage) {

        //=====================
        Label totalLabel = new Label("Total");
        totalLabel.setWidth(115, Unit.PIXELS);
//        totalLabel.setStyleName("dashboardLabel");

        Label totalAmountLabel = new Label(df.format(Double.parseDouble(annualTotalFuelSpend.toString())));
        totalAmountLabel.setWidth(100, Unit.PIXELS);
        totalAmountLabel.setStyleName("dashboardLabel");

        HorizontalLayout annualTotalHorizontalLayout = new HorizontalLayout();
//        annualTotalHorizontalLayout.setSizeFull();
        annualTotalHorizontalLayout.addComponent(totalLabel);
        annualTotalHorizontalLayout.addComponent(totalAmountLabel);
        //        annualTotalHorizontalLayout.setComponentAlignment(totalLabel, Alignment.MIDDLE_LEFT);
        annualTotalHorizontalLayout.setSizeUndefined();

        //=====================
        Label serviceTotalLabel = new Label("Service");
        serviceTotalLabel.setWidth(115, Unit.PIXELS);
//        serviceTotalLabel.setStyleName("dashboardLabel");

        Label serviceAmountLabel = new Label(df.format(Double.parseDouble(serviceTotalFuelSpend.toString())));
        serviceAmountLabel.setWidth(100, Unit.PIXELS);
        serviceAmountLabel.setStyleName("dashboardLabel");

        Label servicePercentageLabel = new Label(serviceFuelSpendPercentage + "%");
        servicePercentageLabel.setWidth(55, Unit.PIXELS);
        servicePercentageLabel.setStyleName("dashboardPercentageLabel");

        HorizontalLayout serviceTotalHorizontalLayout = new HorizontalLayout();
//        serviceTotalHorizontalLayout.setSizeFull();
        serviceTotalHorizontalLayout.addComponent(serviceTotalLabel);
        serviceTotalHorizontalLayout.addComponent(serviceAmountLabel);
        serviceTotalHorizontalLayout.addComponent(servicePercentageLabel);
//        serviceTotalHorizontalLayout.setComponentAlignment(serviceTotalLabel, Alignment.MIDDLE_LEFT);
//        serviceTotalHorizontalLayout.setComponentAlignment(serviceAmountLabel, Alignment.MIDDLE_CENTER);
//        serviceTotalHorizontalLayout.setComponentAlignment(servicePercentageLabel, Alignment.MIDDLE_RIGHT);
        serviceTotalHorizontalLayout.setSizeUndefined();

//============================================================================================================
        Label utilityTotalLabel = new Label("Utility");
        utilityTotalLabel.setWidth(115, Unit.PIXELS);
//        serviceTotalLabel.setStyleName("dashboardLabel");

        Label utilityAmountLabel = new Label(df.format(Double.parseDouble(utilityTotalFuelSpend.toString())));
        utilityAmountLabel.setWidth(100, Unit.PIXELS);
        utilityAmountLabel.setStyleName("dashboardLabel");

        Label utilityPercentageLabel = new Label(utilityFuelSpendPercentage + "%");
        utilityPercentageLabel.setWidth(55, Unit.PIXELS);
        utilityPercentageLabel.setStyleName("dashboardPercentageLabel");

        HorizontalLayout utilityTotalHorizontalLayout = new HorizontalLayout();
//        serviceTotalHorizontalLayout.setSizeFull();
        utilityTotalHorizontalLayout.addComponent(utilityTotalLabel);
        utilityTotalHorizontalLayout.addComponent(utilityAmountLabel);
        utilityTotalHorizontalLayout.addComponent(utilityPercentageLabel);
//        utilityTotalHorizontalLayout.setComponentAlignment(serviceTotalLabel, Alignment.MIDDLE_LEFT);
//        utilityTotalHorizontalLayout.setComponentAlignment(serviceAmountLabel, Alignment.MIDDLE_CENTER);
//        utilityTotalHorizontalLayout.setComponentAlignment(servicePercentageLabel, Alignment.MIDDLE_RIGHT);
        utilityTotalHorizontalLayout.setSizeUndefined();

//============================================================================================================
        Label operationalTotalLabel = new Label("Operational");
        operationalTotalLabel.setWidth(115, Unit.PIXELS);
//        totalLabel.setStyleName("dashboardLabel");

        Label operationalAmountLabel = new Label(df.format(Double.parseDouble(operationalTotalFuelSpend.toString())));//
        operationalAmountLabel.setWidth(100, Unit.PIXELS);
        operationalAmountLabel.setStyleName("dashboardLabel");

        Label operationalPercentageLabel = new Label(operationalFuelSpendPercentage + "%");
        operationalPercentageLabel.setWidth(55, Unit.PIXELS);
        operationalPercentageLabel.setStyleName("dashboardPercentageLabel");

        HorizontalLayout operationalTotalHorizontalLayout = new HorizontalLayout();
        operationalTotalHorizontalLayout.addComponent(operationalTotalLabel);
        operationalTotalHorizontalLayout.addComponent(operationalAmountLabel);
        operationalTotalHorizontalLayout.addComponent(operationalPercentageLabel);
        operationalTotalHorizontalLayout.setSizeUndefined();

        //=====================
        HorizontalLayout nonOperationalTotalHorizontalLayout = new HorizontalLayout();

        Label nonOperationalTotalLabel = new Label("Non-Operational");
        nonOperationalTotalLabel.setWidth(115, Unit.PIXELS);
//        totalLabel.setStyleName("dashboardLabel");

        Label nonOperationalAmountLabel = new Label(df.format(Double.parseDouble(nonOperationalTotalFuelSpend.toString())));
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

        //=========================================================================================================
        addComponent(annualTotalHorizontalLayout);
        addComponent(serviceTotalHorizontalLayout);
        addComponent(utilityTotalHorizontalLayout);
        addComponent(operationalTotalHorizontalLayout);
        addComponent(nonOperationalTotalHorizontalLayout);
        // These are IMPORTANT, else Panels will have Scrollbars
        setMargin(true);
        setSizeUndefined();

        return this;
    }
}

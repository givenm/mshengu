/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.tables;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;

/**
 *
 * @author Ferox
 */
public class OperatingCostTable extends Table {

    private final MshenguMain main;
    
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    public OperatingCostTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Slip Number", String.class, null);
        addContainerProperty("Transaction Date", String.class, null);
        addContainerProperty("Diver", String.class, null);
        addContainerProperty("Speedometere", Float.class, null);
        addContainerProperty("Fuel Litres", Float.class, null);
        addContainerProperty("Fuel Cost", BigDecimal.class, null);
        addContainerProperty("Oil Litres", Float.class, null);
        addContainerProperty("Oil Cost", BigDecimal.class, null);
        addContainerProperty("Other Cost", BigDecimal.class, null);
        

        // Add Data Columns
        List<OperatingCost> operatingCostList = OperatingCostFacade.getOperatingCostService().findAll();
        for (OperatingCost operatingCost : operatingCostList) {
            addItem(new Object[]{operatingCost.getSlipNo(),
                                formatHelper.getYearMonthDay(operatingCost.getTransactionDate()),
                                operatingCost.getDriver().getLastname(),
                                operatingCost.getSpeedometer(),
                                operatingCost.getFuelLitres(),
                                operatingCost.getFuelCost(),
                                operatingCost.getOilLitres(),
                                operatingCost.getOilCost(),
                                operatingCost.getRandPerLitre()
                                }, operatingCost.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }


    }
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables;

import com.vaadin.ui.Table;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author geek
 */
public class DailyInputsTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
// Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public DailyInputsTable(MshenguMain main) {
        this.main = main;
        setSizeFull();
        //
        addContainerProperty("Date", String.class, null);
        addContainerProperty("Truck", String.class, null);
        addContainerProperty("Slip Number", String.class, null);
        addContainerProperty("Mileage", Integer.class, null);
        addContainerProperty("Driver", String.class, null);
        addContainerProperty("Fuel Litres", String.class, null);
        addContainerProperty("Fuel Cost", String.class, null);
        addContainerProperty("Rand/Litre", String.class, null);
        addContainerProperty("Oil Litres", String.class, null);
        addContainerProperty("Oil Cost", String.class, null);

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        // Alignments
        setColumnAlignment("Fuel Litres", Table.ALIGN_RIGHT);
        setColumnAlignment("Fuel Cost", Table.ALIGN_RIGHT);
        setColumnAlignment("Rand/Litre", Table.ALIGN_RIGHT);
        setColumnAlignment("Oil Litres", Table.ALIGN_RIGHT);
        setColumnAlignment("Oil Cost", Table.ALIGN_RIGHT);

    }

    public void populateDailyInputTable(List<OperatingCost> operatingCostList, Truck truck) {
        this.removeAllItems();
        for (OperatingCost operatingCost : operatingCostList) {
            System.out.println(formatHelper.getYearMonthDay(operatingCost.getTransactionDate()) + " | " + truck.getVehicleNumber() + " | " + operatingCost.getSpeedometer());
            addItem(new Object[]{
                formatHelper.getYearMonthDay(operatingCost.getTransactionDate()),
                truck.getVehicleNumber() + " (" + truck.getNumberPlate() + ")",
                operatingCost.getSlipNo() == null ? "" : operatingCost.getSlipNo(),
                operatingCost.getSpeedometer() == null ? "" : operatingCost.getSpeedometer(),
                operatingCost.getDriver().getLastname() == null ? null : operatingCost.getDriver().getLastname(),
                operatingCost.getFuelLitres() == null ? "" : df.format((Double) operatingCost.getFuelLitres()),
                operatingCost.getFuelCost() == null ? "" : df.format(Double.parseDouble(operatingCost.getFuelCost().toString())),
                operatingCost.getRandPerLitre() == null ? "" : df.format(Double.parseDouble(operatingCost.getRandPerLitre().toString())),
                operatingCost.getOilLitres() == null ? "" : df.format((Double) operatingCost.getOilLitres()),
                operatingCost.getOilCost() == null ? "" : df.format(Double.parseDouble(operatingCost.getOilCost().toString()))
            }, operatingCost.getId());
        }
    }
}

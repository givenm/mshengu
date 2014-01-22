/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.FuelAndOilPriceFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.FuelAndOilPrice;

/**
 *
 * @author geek
 */
public class FuelAndOilPriceTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();

    public FuelAndOilPriceTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Entry Date", String.class, null);
        addContainerProperty("Fuel Price", BigDecimal.class, null);
        addContainerProperty("Fuel Effect Date", Date.class, null);
        addContainerProperty("Engine Oil Price", BigDecimal.class, null);
        addContainerProperty("engine Oil Effect Date", Date.class, null);

        // Add Data Columns
        List<FuelAndOilPrice> fuelAndOilPriceList = FuelAndOilPriceFacade.getFuelAndOilPriceService().findAll();
        for (FuelAndOilPrice FuelAndOilPrice : fuelAndOilPriceList) {
            addItem(new Object[]{
                formatHelper.getYearMonthDay(FuelAndOilPrice.getEntryDate()),
                FuelAndOilPrice.getFuelPrice(),
                FuelAndOilPrice.getFuelEffectDate(),
                FuelAndOilPrice.getEngineOilPrice(),
                FuelAndOilPrice.getEngineOilEffectDate()},
                    FuelAndOilPrice.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

    }
}

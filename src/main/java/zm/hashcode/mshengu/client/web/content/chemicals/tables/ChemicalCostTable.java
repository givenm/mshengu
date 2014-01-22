/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.chemicals.tables;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ChemicalCost.ChemicalCostFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.chemical.ChemicalCost;

/**
 *
 * @author geek
 */
public class ChemicalCostTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();

    public ChemicalCostTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Transaction Date", String.class, null);
        addContainerProperty("Invoice Number", String.class, null);
        addContainerProperty("Product", String.class, null);
        addContainerProperty("Quantity Ordered", Integer.class, null);
        addContainerProperty("Volume", String.class, null);
        addContainerProperty("Unit Price", BigDecimal.class, null);
        addContainerProperty("Total Price", BigDecimal.class, null);

        // Add Data Columns
        List<ChemicalCost> chemicalCostList = ChemicalCostFacade.getChemicalCostService().findAll();

        for (ChemicalCost chemicalCost : chemicalCostList) {
            addItem(new Object[]{
                formatHelper.getYearMonthDay(chemicalCost.getTransactionDate()),
                chemicalCost.getInvoiceNumber(),
                chemicalCost.getServiceProviderProductName(),
                chemicalCost.getQuantityOrdered(),
                chemicalCost.getVolume() + "",
                chemicalCost.getServiceProviderProductPrice(),
                chemicalCost.getTotalPrice(),}, chemicalCost.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

    }
}

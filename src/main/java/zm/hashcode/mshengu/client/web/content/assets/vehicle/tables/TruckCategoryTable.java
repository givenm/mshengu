/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckCategoryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;

/**
 *
 * @author Ferox
 */
public class TruckCategoryTable extends Table {

    private final MshenguMain main;

    public TruckCategoryTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Vehicle Category", String.class, null);
        addContainerProperty("Vehicle Naming Code ", String.class, null);
        addContainerProperty("Vehicle Value", Integer.class, null);
        

        // Add Data Columns
        List<TruckCategory> truckCategoryList = TruckCategoryFacade.getTruckCategoryService().findAll();
        for (TruckCategory truckCategory : truckCategoryList) {
            addItem(new Object[]{truckCategory.getCategoryName(),
                        truckCategory.getNamingCode(),
                        truckCategory.getValue(),}, truckCategory.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }


    }
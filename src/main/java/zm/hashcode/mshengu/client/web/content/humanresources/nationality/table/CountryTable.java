/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.nationality.table;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.CountryFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.Country;

/**
 *
 * @author Ferox
 */
public class CountryTable extends Table {

    private final MshenguMain main;

    public CountryTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Country Name", String.class, null);
        addContainerProperty("Nationality", String.class, null);

        // Add Data Columns
        List<Country> countryList = CountryFacade.getCountryService().findAll();
        for (Country country : countryList) {
            addItem(new Object[]{country.getName(),
                        country.getNationality(),}, country.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
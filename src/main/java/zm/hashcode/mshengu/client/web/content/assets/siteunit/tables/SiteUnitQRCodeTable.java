/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.tables;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Ferox
 */
public class SiteUnitQRCodeTable extends Table {

    private final MshenguMain main;

    public SiteUnitQRCodeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Unit ID", String.class, null);
        addContainerProperty("QR Code", Embedded.class, null);


        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }

    public void generateQRCodeFromUnitToUnit(int startUnitNumber, int endUnitNumber, String unitNamingCode) {

        for (int i = startUnitNumber; i <= endUnitNumber; i++) {
            String unitName = unitNamingCode + "-" + formatUnitNumber(i); 
            
//            String path = "/Users/Ferox/qrcodes/"+unitName+ ".PNG";
                        String path = "qrcodes/"+unitName+ ".PNG";

            Embedded qrCode = new Embedded("", new ThemeResource(path));

            addItem(new Object[]{
                        unitName,
                        qrCode
                    }, unitName);

        }


    }

    private String formatUnitNumber(int unitId) {
        //100000
        if (unitId < 10) {
            return "00000" + unitId; //000001-000009
        } else if (unitId < 100) {
            return "0000" + unitId;  //000010-000099
        } else if (unitId < 1000) {
            return "000" + unitId;   //000100-000999
        } else if (unitId < 10000) {
            return "00" + unitId;    //001000-009999
        } else if (unitId < 100000) {
            return "0" + unitId;     //010000-099999
        } else {
            return "" + unitId;
        }
    }
}
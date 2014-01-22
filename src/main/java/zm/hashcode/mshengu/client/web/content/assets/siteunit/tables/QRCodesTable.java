/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.tables;
 
import com.vaadin.ui.Table;
import fi.jasoft.qrcode.QRCode;
import java.util.List;
import net.glxn.qrgen.image.ImageType;
import org.vaadin.haijian.PdfExporter;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.UnitsQRCodesTab;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 *
 * @author boniface
 */
public class QRCodesTable extends Table {

    private MshenguMain main;
    private UnitsQRCodesTab tab;

    public QRCodesTable(final MshenguMain main, final UnitsQRCodesTab tab) {
        tab.removeAllComponents();
        this.main = main;
        this.tab = tab;
        setSizeFull();
        addContainerProperty("Unit Name", String.class, null);
        addContainerProperty("QRCODE", QRCode.class, null);
        setNullSelectionAllowed(false);

        // Allow selecting items from the table.

        tab.addComponent(this);
        PdfExporter export = new PdfExporter(this);

        tab.addComponent(export);

//        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
//        setI
//        setRowHeaderMode(RowHeaderMode.EXPLICIT_DEFAULTS_ID);
//        setColumnHeaderMode(ColumnHeaderMode.EXPLICIT_DEFAULTS_ID);
        setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//        set
      
    }

    public final void loadToiletUnits(List<SiteUnit> unitList) {
        int a = 0;
        for (SiteUnit unit : unitList) {
                      QRCode qrcode = new QRCode(unit.getUnitId());
////            cd.
            qrcode.setWidth("200px");
            qrcode.setHeight("200px");
            qrcode.setId(unit.getUnitId());
            qrcode.setValue(unit.getUnitId());
            qrcode.setCaption(unit.getUnitId());
//            cd.set
//            cd.set
            addItem(new Object[]{
                        unit.getUnitId(),
                        qrcode,
                    }, unit.getUnitId());
        }
    }
}

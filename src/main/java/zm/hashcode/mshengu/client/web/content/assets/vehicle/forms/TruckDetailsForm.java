/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.forms;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadFinishedHandler;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStateWindow;
import java.io.InputStream;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.FleetMenu;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.tables.TruckUploadTable;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Luckbliss
 */
public class TruckDetailsForm extends GridLayout {

    private MshenguMain main;

    public TruckDetailsForm(MshenguMain main, Truck truck) {
        this.main = main;
        setColumns(7);
        setRows(15);
        setSizeFull();

        Button button = new Button("Back");
        button.setSizeFull();
        
        button.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    getHome();
                }
            });
        //titles
        addComponent(new Label("Vehicle Number : "), 0, 0);

        //values
        addComponent(new Label(truck.getVehicleNumber()), 1, 0);

        addComponent(new Label("<hr/>", ContentMode.HTML), 0, 1, 6, 1);
        //titles
        addComponent(new Label("NumberPlate : "), 0, 2);

        //values
        addComponent(new Label(truck.getNumberPlate()), 1, 2);
////
//         //titles
        addComponent(new Label("Category : "), 2, 2);
        addComponent(new Label(truck.getCategory().getCategoryName()), 3, 2);
//
//
//         //titles
        addComponent(new Label("Driver : "), 4, 2);

//        //values
        addComponent(new Label(truck.getDriverName()), 5, 2);


        addComponent(new Label("<hr/>", ContentMode.HTML), 0, 7, 6, 7);

        final TruckUploadTable table = new TruckUploadTable(main, truck);
        table.setSizeFull();

        UploadFinishedHandler handler = new UploadFinishedHandler() {
            @Override
            public void handleFile(InputStream in, String fileName, String mimeType, long length) {
                table.loadTable(in, fileName, mimeType);
            }
        };

        UploadStateWindow window = new UploadStateWindow();

        // For Single Upload
        MultiFileUpload fileUpload = new MultiFileUpload(handler, window, false);
        fileUpload.setPanelCaption("Single File Upload");
        fileUpload.getSmartUpload().setUploadButtonCaptions("Upload Vehicle Files..", "");
        fileUpload.setStyleName("default");
        fileUpload.setSizeFull();

        addComponent(new Label("<br>", ContentMode.HTML), 0, 8, 6, 8);
        addComponent(fileUpload, 0, 9, 2, 9);
        addComponent(button, 3, 9, 4, 9); 
        addComponent(new Label("<br>", ContentMode.HTML), 0, 10, 6, 10);
        addComponent(table, 0, 11, 6, 11);
    }
    
    private void getHome() {
        main.content.setSecondComponent(new FleetMenu(main, "LANDING"));
    }
}

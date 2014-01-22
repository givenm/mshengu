/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.tables;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.customer.EmployeeDetailFacade;
import zm.hashcode.mshengu.app.facade.documents.FileStorageFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.util.files.PersonFilesgeUtil;
import zm.hashcode.mshengu.app.util.files.ProfileImageUtil;
import zm.hashcode.mshengu.app.util.files.TruckImageUtil;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.services.documents.FileStorageService;

/**
 *
 * @author Luckbliss
 */
public class TruckUploadTable extends Table {

    private final MshenguMain main;
    private final Truck truck;
    private FileStorageService storageService = FileStorageFacade.getFileStorageService();

    public TruckUploadTable(final MshenguMain main, final Truck truck) {
        this.main = main;
        this.truck = truck;

        addContainerProperty("File Name", String.class, null);
        addContainerProperty("Download", Button.class, null);
        addContainerProperty("Delete", Button.class, null);

        if (truck.getFiles() != null) {
            for (final String fileId : truck.getFiles()) {
                final Button download = new Button("Download");
                download.setStyleName(Reindeer.BUTTON_LINK);
                download.setData(fileId);
                download.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        PersonFilesgeUtil personFilesgeUtil = new PersonFilesgeUtil(fileId);
                        StreamResource.StreamSource imageSource = personFilesgeUtil;
                        StreamResource resource = new StreamResource(imageSource, personFilesgeUtil.getFileName());
                        FileDownloader fileDownloader = new FileDownloader(resource);
                        fileDownloader.extend(download);
                    }
                });
                Button delete = new Button("Delete");
                delete.setStyleName(Reindeer.BUTTON_LINK);
                delete.setData(fileId);
                delete.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        deleteFile((String) event.getButton().getData());
                        removeItem((String) event.getButton().getData());
                    }
                });

                addItem(new Object[]{storageService.getById(fileId).getFilename(), download, delete,}, fileId);
            }
        }

        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void loadTable(InputStream in, String fileName, String mimeType) {
        String fileId = storageService.save(in, mimeType, fileName);

        Set<String> files = new HashSet<>();
        if (truck.getFiles() != null) {
            files = truck.getFiles();
        }
        files.add(fileId);
        Truck newDetail = getTruckDetail(truck, files);
        TruckFacade.getTruckService().merge(newDetail);

        for (String filesId : newDetail.getFiles()) {
            Button download = new Button("Download");
            download.setStyleName(Reindeer.BUTTON_LINK);
            download.setData(filesId);

            PersonFilesgeUtil personFilesgeUtil = new PersonFilesgeUtil(fileId);
            StreamResource.StreamSource imageSource = personFilesgeUtil;
            StreamResource resource = new StreamResource(imageSource, personFilesgeUtil.getFileName());
            FileDownloader fileDownloader = new FileDownloader(resource);
            fileDownloader.extend(download);


            Button delete = new Button("Delete");
            delete.setStyleName(Reindeer.BUTTON_LINK);
            delete.setData(filesId);
            delete.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    deleteFile((String) event.getButton().getData());
                    removeItem((String) event.getButton().getData());
                }
            });

            addItem(new Object[]{storageService.getById(filesId).getFilename(), download, delete,}, filesId);
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
        setSelectable(true);
        //Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void deleteFile(String filename) {
        Set<String> files = truck.getFiles();
        files.remove(filename);
        Truck newDetail = getTruckDetail(truck, files);
        TruckFacade.getTruckService().merge(newDetail);
    }

    public Truck getTruckDetail(Truck detail, Set<String> files) {
        Truck newDetail = new Truck.Builder(detail.getNumberPlate())
                .truck(truck)
                .files(files)
                .build();
        return newDetail;
    }

    public Person getPerson(Person p, EmployeeDetail newDetail) {
        Person newPerson = new Person.Builder(p.getLastname())
                .person(p)
                .employeeDetails(newDetail)
                .build();
        return newPerson;
    }
}

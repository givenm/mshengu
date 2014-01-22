/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.tables;

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
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.files.PersonFilesgeUtil;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.services.documents.FileStorageService;

/**
 *
 * @author boniface
 */
public class PersonUploadTable extends Table {

    private final MshenguMain main;
    private final Person person;
    private FileStorageService storageService = FileStorageFacade.getFileStorageService();
    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();

    public PersonUploadTable(final MshenguMain main, final Person person, final VerticalLayout content) {
        this.main = main;
        this.person = person;

        addContainerProperty("File Name", String.class, null);
        addContainerProperty("Download", Button.class, null);
        addContainerProperty("Delete", Button.class, null);

        EmployeeDetail detail = person.getEmployeeDetails();
        if (detail.getFiles() != null) {
            for (final String fileId : detail.getFiles()) {
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
    
    public void addDataToButtons(){
        
    }

    public void loadTable(InputStream in, String fileName, String mimeType) {
//        String uploadDate = dateTimeFormatHelper.getDayMonthYear(new Date());
        String fileId = storageService.save(in, mimeType, fileName);
        Set<String> files = updateEmpDetails(fileId);


        for (String filesId : files) {

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
        EmployeeDetail detail = person.getEmployeeDetails();
        Set<String> files = new HashSet<>();
        files.addAll(detail.getFiles());
        files.remove(filename);

        EmployeeDetail newDetail = getEmployeeDetail(detail, files);
        EmployeeDetailFacade.getEmployeeDetailService().merge(newDetail);
        Person newPerson = getPerson(person, newDetail);
        PersonFacade.getPersonService().merge(newPerson);
    }

    public EmployeeDetail getEmployeeDetail(EmployeeDetail detail, Set<String> files) {
        EmployeeDetail newDetail = new EmployeeDetail.Builder(detail.getId())
                .EmployeeDetails(detail)
                .files(files)
                .build();
        return newDetail;
    }

    private Set<String> updateEmployeeDetails(String fileId) {
        EmployeeDetail detail = person.getEmployeeDetails();
        Set<String> files = new HashSet<>();
        if (detail.getFiles() != null) {
            files = detail.getFiles();
        }
        files.add(fileId);
        EmployeeDetail newDetail = getEmployeeDetail(detail, files);
        EmployeeDetailFacade.getEmployeeDetailService().merge(newDetail);
        Person newPerson = getPerson(person, newDetail);
        PersonFacade.getPersonService().merge(newPerson);

        return newDetail.getFiles();
    }

    public Person getPerson(Person p, EmployeeDetail newDetail) {
        Person newPerson = new Person.Builder(p.getLastname())
                .person(p)
                .employeeDetails(newDetail)
                .build();
        return newPerson;
    }

    private Set<String> updateEmpDetails(String fileId) {
        EmployeeDetail detail = person.getEmployeeDetails();
        Set<String> files = new HashSet<>();
        if (detail.getFiles() != null) {
            files = detail.getFiles();
        }
        files.add(fileId);
        EmployeeDetail newDetail = getEmployeeDetail(detail, files);
        EmployeeDetailFacade.getEmployeeDetailService().merge(newDetail);
        Person newPerson = getPerson(person, newDetail);
        PersonFacade.getPersonService().merge(newPerson);
        return newDetail.getFiles();
    }
}

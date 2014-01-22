/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.forms;

import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadFinishedHandler;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStateWindow;
import java.io.InputStream;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.customer.EmployeeDetailFacade;
import zm.hashcode.mshengu.app.facade.documents.FileStorageFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.facade.ui.util.CountryFacade;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.files.ProfileImageUtil;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.HRMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.tables.PersonUploadTable;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.location.Address;
import zm.hashcode.mshengu.domain.ui.util.Country;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.services.documents.FileStorageService;

/**
 *
 * @author boniface
 */
public class DemographicsWindow extends GridLayout {

    private final VerticalLayout demoLayout;
    private final Person person;
    private final MshenguMain main;
    private FileStorageService storageService = FileStorageFacade.getFileStorageService();
    private UploadFinishedHandler imageHandler;
    private UploadStateWindow imageWindow;
    private MultiFileUpload imageUpload;
    private final PersonUploadTable table;
    private UploadFinishedHandler fileHandler;
    private UploadStateWindow fileWindow;
    private MultiFileUpload fileUpload;
    private Button backtoMainPage;

    public DemographicsWindow(final MshenguMain main, final Person person, final VerticalLayout demoLayout) {
        this.demoLayout = demoLayout;
        this.person = person;
        this.main = main;
        setColumns(7);
        setRows(10);
        setSizeFull();
        StreamResource.StreamSource imageSource = new ProfileImageUtil(person);
        StreamResource resource = new StreamResource(imageSource, getProfileImage(person.getEmployeeDetails()));

        final Image image = new Image(null, resource);
        image.setHeight("150");
        image.setWidth("150");
        addComponent(image, 0, 0, 0, 2);
//
//        //titles
        addComponent(new Label("First Name: "), 1, 0);
        addComponent(new Label("Last Name: "), 1, 1);
        addComponent(new Label("Date of Birth:"), 1, 2);
//        //values
        addComponent(new Label(person.getFirstname()), 2, 0);
        addComponent(new Label(person.getLastname()), 2, 1);
        addComponent(new Label(getDateofBirth(person.getDateofbirth())), 2, 2);
//
//         //titles
        addComponent(new Label("Position: "), 3, 0);
        addComponent(new Label("Employee Number:"), 3, 1);
        addComponent(new Label("Employee Status:"), 3, 2);
//        //values
        addComponent(new Label(getPosition(person.getEmployeeDetails())), 4, 0);
        addComponent(new Label(person.getEmployeeDetails().getEmployeeNumber()), 4, 1);
        addComponent(new Label(getEmploymentStatus(person.getEmployeeDetails())), 4, 2);
//
//
//         //titles
        addComponent(new Label("Nationality: "), 5, 0);
        addComponent(new Label("Years of Service :"), 5, 1);
        addComponent(new Label("Cell Number: "), 5, 2);

//        //values
        addComponent(new Label(nationality(person.getEmployeeDetails())), 6, 0);
        addComponent(new Label(getYearsOfService(person.getEmployeeDetails())), 6, 1);
        addComponent(new Label(getMainNumber(person.getEmployeeDetails())), 6, 2);

        imageUpload();

        table = new PersonUploadTable(main, person, demoLayout);
        table.setSizeFull();

        fileUpload();

        backButtonFunction();

        addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 6, 6);
        addComponent(imageUpload, 0, 7);
        addComponent(fileUpload, 1, 7, 2, 7);
        addComponent(backtoMainPage, 3, 7, 5, 7);
        addComponent(new Label("<hr/>", ContentMode.HTML), 0, 8, 6, 8);
        addComponent(table, 0, 9, 6, 9);
    }

    private void backButtonFunction() {
        backtoMainPage = new Button("Back to Main Page");
        backtoMainPage.setSizeFull();
        backtoMainPage.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                main.content.setSecondComponent(new HRMenu(main, "LANDING"));
            }
        });
    }

    private void imageUpload() {
        imageHandler = new UploadFinishedHandler() {
            @Override
            public void handleFile(InputStream in, String fileName, String mimeType, long length) {
                String profileImageId = storageService.save(in, mimeType, fileName);
                EmployeeDetail detail = new EmployeeDetail.Builder(person.getEmployeeDetailsIdNumber())
                        .EmployeeDetails(person.getEmployeeDetails())
                        .profileImage(profileImageId)
                        .build();
                EmployeeDetailFacade.getEmployeeDetailService().merge(detail);
                getHome();
            }
        };

        imageWindow = new UploadStateWindow();

        imageUpload = new MultiFileUpload(imageHandler, imageWindow, false);
        imageUpload.setPanelCaption("Single File Upload");
        imageUpload.getSmartUpload().setUploadButtonCaptions("Upload  Employee Picture", "");
        imageUpload.setStyleName("default");
        imageUpload.setSizeFull();
    }

    private void fileUpload() {
        fileHandler = new UploadFinishedHandler() {
            @Override
            public void handleFile(InputStream in, String fileName, String mimeType, long length) {
                table.loadTable(in, fileName, mimeType);
                getHome();
            }
        };
//
        fileWindow = new UploadStateWindow();
//         For Single Upload
        fileUpload = new MultiFileUpload(fileHandler, fileWindow, false);
        fileUpload.setPanelCaption("Single File Upload");
        fileUpload.getSmartUpload().setUploadButtonCaptions("Upload Employee Files..", "");
        fileUpload.setStyleName("default");
        fileUpload.setSizeFull();
    }

    private void getHome() {
        Person updated = PersonFacade.getPersonService().findById(person.getId());
        demoLayout.removeAllComponents();
        demoLayout.addComponent((new DemographicsWindow(main, updated, demoLayout)));
    }

    private String getPosition(EmployeeDetail employeeDetails) {
        if (employeeDetails != null) {
            return employeePostion(employeeDetails.getJobPosition());
        }
        return null;

    }

    private String getAddress(Address address) {
        if (address != null) {
            return address.getStreetAddress();
        }
        return null;
    }

    private String getMainNumber(EmployeeDetail employeeDetails) {
        if (employeeDetails != null) {
            return employeeDetails.getMainNumber();
        }
        return null;
    }

    private String getEmploymentStatus(EmployeeDetail employeeDetails) {
        if (employeeDetails.getEmploymentStatusId() != null) {
            Status status = StatusFacade.getStatusService().findById(employeeDetails.getEmploymentStatusId());
            return status.getName();
        } else {
            return employeeDetails.getTerminated();
        }
    }

    private String getYearsOfService(EmployeeDetail employeeDetails) {
        if (employeeDetails.getStartDate() != null) {
            return (new Date().getYear() - employeeDetails.getStartDate().getYear()) + "";
        }
        return null;
    }

    private String getDateofBirth(Date dateofbirth) {
        if (dateofbirth != null) {
            return new DateTimeFormatHelper().getDayMonthYear(dateofbirth);
        }
        return null;
    }

    private String employeePostion(JobPosition jobPosition) {
        if (jobPosition != null) {
            return jobPosition.getName();
        }
        return null;
    }

    private String nationality(EmployeeDetail employeeDetails) {
        if (employeeDetails != null) {
            Country country = CountryFacade.getCountryService().findById(employeeDetails.getCountryId());
            if (country != null) {
                return country.getNationality();
            }
            return null;
        }
        return null;
    }

    private String getProfileImage(EmployeeDetail detail) {
        if (detail.getProfileImage() != null) {
            return detail.getProfileImage();
        } else {
            return null;
        }
    }
}

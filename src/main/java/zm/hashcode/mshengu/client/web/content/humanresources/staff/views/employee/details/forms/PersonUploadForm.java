/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadFinishedHandler;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStateWindow;
import java.io.InputStream;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.model.PersonUploadBean;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.views.employee.details.tables.PersonUploadTable;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author geek
 */
public class PersonUploadForm extends FormLayout {

    private final PersonUploadBean bean;
    public final BeanItem<PersonUploadBean> item;
    public final FieldGroup binder;
//    public ComboBox cityCombo = new ComboBox();
    // Define Buttons
    private final Person person;
    private final MshenguMain main;
    private final VerticalLayout content;
    private PersonUploadTable table;

    public PersonUploadForm(MshenguMain main, final Person person, VerticalLayout contentLayout) {
        setSizeFull();
        this.person = person;
        this.main = main;
        this.content = contentLayout;
        table = new PersonUploadTable(main, person, content);

        UploadFinishedHandler handler = new UploadFinishedHandler() {
            @Override
            public void handleFile(InputStream in, String fileName, String mimeType, long length) {
                table.loadTable(in, fileName, mimeType);
                getHome();
            }
        };

        UploadStateWindow window = new UploadStateWindow();

        // For Single Upload
        MultiFileUpload fileUpload = new MultiFileUpload(handler, window, false);
        fileUpload.setPanelCaption("Single File Upload");
        fileUpload.getSmartUpload().setUploadButtonCaptions("Upload Employee Files..", "");
        fileUpload.setStyleName("default");
        fileUpload.setSizeFull();

        bean = new PersonUploadBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);

        // Determines which properties are shown

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(fileUpload, 0, 0, 0, 3);
        addComponent(grid);

    }

    private void getHome() {
        content.removeAllComponents();
        table.setSizeFull();
        content.addComponent(table);
    }
}

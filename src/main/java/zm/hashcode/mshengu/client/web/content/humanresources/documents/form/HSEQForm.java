/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.documents.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.models.DocumentBean;

/**
 *
 * @author Luckbliss
 */
public class HSEQForm extends FormLayout{

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final DocumentBean bean;
    public final BeanItem<DocumentBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
//    Upload upload = new Upload("Upload it here:", null);
    private ImageUploader receiver = new ImageUploader();
    final Embedded image = new Embedded("Uploaded Image");
    private ComboBox uploadedFiles = null;

    public HSEQForm() {
        bean = new DocumentBean();
        item = new BeanItem<DocumentBean>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);
        edit.setVisible(false);

        // UIComponent
        TextField name = UIComponent.getTextField("Document Name:", "name", DocumentBean.class, binder);
        ComboBox category = UIComboBox.getDocumentComboBox("Document Category:", "category", DocumentBean.class, binder);
        TextArea description = UIComponent.getTextArea("Document Description:", "description", DocumentBean.class, binder);
        TextField url = UIComponent.getTextField("URL:", "url", DocumentBean.class, binder);
        uploadedFiles = UIComboBox.getUploadComboBox("Your Uploaded Files", "upload", DocumentBean.class, binder);
// Create the upload with a caption and set receiver later
        Upload upload = new Upload("Upload Image Here", receiver);
        upload.setButtonCaption("Start Upload");
        upload.addSucceededListener(receiver);

        GridLayout grid = new GridLayout(3, 3);
        grid.setSizeFull();


        grid.addComponent(name, 0, 0);
        grid.addComponent(category, 1, 0);
        grid.addComponent(url, 2, 0);

        grid.addComponent(description, 0, 1);
        grid.addComponent(upload, 1, 1);
        
        uploadedFiles.setVisible(false);
        grid.addComponent(uploadedFiles);

//        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 2, 1, 1);
        grid.addComponent(buttons, 0, 2, 2, 2);
        addComponent(grid);


    }

    private class ImageUploader implements Receiver, SucceededListener {

        public File file;

        @Override
        public OutputStream receiveUpload(String filename,
                String mimeType) {
            // Create upload stream
            FileOutputStream fos = null; // Stream to write to
            try {
                // Open the file for writing.
                file = new File("C:/Users/Luckbliss/Documents/NetBeansProjects/mshengu/mshengu/src/main/java/zm/hashcode/mshengu/client/web/content/humanresources/documents/models/uploads/" + filename);
                fos = new FileOutputStream(file);
                uploadedFiles.setVisible(true);
                uploadedFiles.addItem(filename);
            } catch (final java.io.FileNotFoundException e) {
                new Notification("Could not open file<br/>",
                        e.getMessage(),
                        Notification.Type.ERROR_MESSAGE)
                        .show(Page.getCurrent());
                return null;
            }
            return fos; // Return the output stream to write to
        }

        @Override
        public void uploadSucceeded(SucceededEvent event) {
        }
    };

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        edit.setSizeFull();
        cancel.setSizeFull();
        update.setSizeFull();
        delete.setSizeFull();

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }
}

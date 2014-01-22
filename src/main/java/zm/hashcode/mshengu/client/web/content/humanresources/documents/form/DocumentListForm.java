/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.documents.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.models.DocumentBean;

/**
 *
 * @author Luckbliss
 */
public class DocumentListForm extends FormLayout {

    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final DocumentBean bean;
    public final BeanItem<DocumentBean> item;
    public final FieldGroup binder;
    public Button find = new Button("Find");

    public DocumentListForm() {
        bean = new DocumentBean();
        item = new BeanItem<DocumentBean>(bean);
        binder = new FieldGroup(item);

        // UIComponent
        ComboBox category = UIComboBox.getAllDocumentComboBox("Document Category:", "category", DocumentBean.class, binder);

        GridLayout layout = new GridLayout(1, 2);
        layout.setSizeFull();

        layout.addComponent(category, 0, 0);
        layout.addComponent(find, 0, 1);

        addComponent(layout);
    }
}

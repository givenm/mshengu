/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.documents.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zm.hashcode.mshengu.app.facade.documents.DocumentsFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.HSEQMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.form.DocumentListForm;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.models.DocumentBean;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.tables.DocumentListTable;
import zm.hashcode.mshengu.domain.documents.Documents;

/**
 *
 * @author Luckbliss
 */
public class DocumentListTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final DocumentListForm form;
    private DocumentListTable table;
    private String category = "";

    public DocumentListTab(MshenguMain app) {
        main = app;
        form = new DocumentListForm();
        table = new DocumentListTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    private void addListeners() {
        form.find.addClickListener((Button.ClickListener) this);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.find) {
            category = getString(form.binder);
            loadTable();
        }
    }

    public String getString(FieldGroup binder) {
        try {
            binder.commit();
            final DocumentBean bean = ((BeanItem<DocumentBean>) binder.getItemDataSource()).getBean();
            return bean.getCategory();
        } catch (FieldGroup.CommitException ex) {
            Logger.getLogger(DocumentListTab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
//        final Property property = event.getProperty();
//        if (property == table) {
//            final DocumentBean bean = ((BeanItem<DocumentBean>) form.binder.getItemDataSource()).getBean();
//            List<Documents> documents = DocumentsFacade.getDocumentsService().findAll();
//            List<Documents> ds = new ArrayList<>();
//            for (Documents d : documents) {
//                if (d.getCategory().equalsIgnoreCase(bean.getCategory())) {
//                    form.binder.setItemDataSource(new BeanItem<>(getBean(d)));
//                }
//            }
//        }
    }

    private DocumentBean getBean(Documents documents) {
        DocumentBean bean = new DocumentBean();
        bean.setName(documents.getName());
        bean.setId(documents.getId());
        bean.setCategory(documents.getCategory());
        bean.setDescription(documents.getDescription());
        bean.setUpload(null);
        bean.setUrl(documents.getUrl());
        return bean;
    }

    private void getHome() {
        main.content.setSecondComponent(new HSEQMenu(main, "DOCUMENT_LIST"));
    }

    private void loadTable() {
        table.removeValueChangeListener((Property.ValueChangeListener) this);
        table.removeAllItems();

        List<Documents> documents = DocumentsFacade.getDocumentsService().findAll();
        List<Documents> list = new ArrayList<>();
        if (category.equalsIgnoreCase("All") || category.equalsIgnoreCase("")) {
            list = documents;
        } else {
            for (Documents d : documents) {
                if (d.getCategory() != null) {
                    if (d.getCategory().equalsIgnoreCase(category)) {
                        list.add(d);
                    }
                }
            }
        }
        if (documents != null) {
            table.loadDocumentCategories(list);
        }
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
}
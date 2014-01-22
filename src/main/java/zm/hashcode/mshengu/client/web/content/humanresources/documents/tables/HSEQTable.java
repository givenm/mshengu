/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.documents.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.documents.DocumentsFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.documents.Documents;

/**
 *
 * @author Luckbliss
 */
public class HSEQTable extends Table {

    private final MshenguMain main;

    public HSEQTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Document Name", String.class, null);
        addContainerProperty("Document Category", String.class, null);
        addContainerProperty("Document Description", String.class, null);
        addContainerProperty("Files", String.class, null);
        addContainerProperty("URL", String.class, null);

        List<Documents> documentsList = DocumentsFacade.getDocumentsService().findAll();
        for (Documents documents : documentsList) {
            String cDocumentName = "N/A";
            String cDocumentCategory = "N/A";
            String cDocumentDescription = "N/A";
            String cFile = "N/A";
            String cUrl = "N/A";
            if (documents.getName() != null) {
                cDocumentName = documents.getName();
                cDocumentCategory = documents.getCategory();
                cDocumentDescription = documents.getDescription();
                cFile = "Nothing yet";
                cUrl = documents.getUrl();
            }
            addItem(new Object[]{cDocumentName,
                cDocumentCategory,
                cDocumentDescription,
                cFile,
                cUrl,}, documents.getId());
        }

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}

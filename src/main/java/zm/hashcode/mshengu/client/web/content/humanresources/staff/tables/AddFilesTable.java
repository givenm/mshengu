/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Luckbliss
 */
public class AddFilesTable extends Table {

    private MshenguMain main;
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public AddFilesTable(final MshenguMain main) {
        this.main = main;
        this.setHeight("600");
        setSizeFull();

        addContainerProperty("File Name", String.class, null);
        addContainerProperty("Details", Button.class, null);

        setNullSelectionAllowed(false);
//
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void loadTable(String filename, String fileId) {
        Button showDetails = new Button("Remove");
        showDetails.setStyleName(Reindeer.BUTTON_LINK);
        showDetails.setData(fileId);
        showDetails.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
//                JOptionPane.showMessageDialog(null, "delete");
            }
        });

        addItem(new Object[]{filename,
            showDetails,}, fileId);
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
        setSelectable(true);
        //Send changes in selection immediately to server.
        setImmediate(true);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.policies.tables;

import com.vaadin.ui.Table;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Luckbliss
 */
public class PoliciesTable extends Table {

    private final MshenguMain main;

    public PoliciesTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.users.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.RoleFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.Role;


/**
 *
 * @author boniface
 */
public final class RoleTable extends Table {

    private final MshenguMain main;

    public RoleTable(MshenguMain main) {
        this.main = main;
        setSizeFull();
        addContainerProperty("Role Name", String.class, null);
        addContainerProperty("Description", String.class, null);
        List<Role> roles = RoleFacade.getRoleService().findAll();
        for (Role role : roles) {
            addItem(new Object[]{role.getRolename(),
                                 role.getDescription()
                                }, role.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
//
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}

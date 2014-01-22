/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.HSEQMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.policies.PoliciesRegulationsMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.HRMenu;
import zm.hashcode.mshengu.client.web.content.setup.users.UsersMenu;
import static zm.hashcode.mshengu.client.web.siderbar.HumanResourcesTree.HR_MANAGEMENT;
import static zm.hashcode.mshengu.client.web.siderbar.HumanResourcesTree.HSEQ;
import static zm.hashcode.mshengu.client.web.siderbar.HumanResourcesTree.MANAGE_USERS;
import static zm.hashcode.mshengu.client.web.siderbar.HumanResourcesTree.POLICIES_REGULATIONS;

/**
 *
 * @author boniface
 */
public class HumanResourcesTree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object HR_MANAGEMENT = "Human Resources";
    public static final Object HSEQ = "Health Safety Environment Quality";
    public static final Object POLICIES_REGULATIONS = "Policies/Regulations";
    public static final Object MANAGE_USERS = "Users Management";
    private static final String LANDING_TAB = "LANDING";

    public HumanResourcesTree(MshenguMain main) {
        this.main = main;
        addItem(HR_MANAGEMENT);
//        addItem(HSEQ);
//        addItem(POLICIES_REGULATIONS);
//        addItem(MANAGE_USERS);

        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (MANAGE_USERS.equals(itemId)) {
                manageUsersView();
            } else if (HR_MANAGEMENT.equals(itemId)) {
                manageStaff();
            } else if (HSEQ.equals(itemId)) {
                manageHSEG();
            } else if (POLICIES_REGULATIONS.equals(itemId)) {
                managePolicies();
            } 
        }
    }

    private void manageUsersView() {
        main.content.setSecondComponent(new UsersMenu(main, LANDING_TAB));
    }

    private void manageStaff() {
        main.content.setSecondComponent(new HRMenu(main, LANDING_TAB));

    }

    private void manageHSEG() {
        main.content.setSecondComponent(new HSEQMenu(main, LANDING_TAB));

    }

    private void managePolicies() {
        main.content.setSecondComponent(new PoliciesRegulationsMenu(main, LANDING_TAB));
    }
}

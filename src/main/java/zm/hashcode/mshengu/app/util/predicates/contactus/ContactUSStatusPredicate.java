package zm.hashcode.mshengu.app.util.predicates.contactus;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.external.ContactUS;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author boniface
 */
public class ContactUSStatusPredicate implements Predicate<ContactUS> {

    private String contactUSStatus;

    public ContactUSStatusPredicate(String contactUSStatus) {
        this.contactUSStatus = contactUSStatus;
    }

    @Override
    public boolean apply(ContactUS contactUS) {
        if (contactUSStatus.equalsIgnoreCase(getContactUS(contactUS))) {
            return true;
        }
        return false;
    }

    private String getContactUS(ContactUS contactUS) {
        if (contactUS != null) {
                return getUserActionStatus(contactUS.getLastUserAction());
        }
        return null;

    }
    
        private String getUserActionStatus(UserAction  userAction) {
        if (userAction != null) {
            return userAction.getUserActionStatusName();
        }
        return null;

    }
   
}

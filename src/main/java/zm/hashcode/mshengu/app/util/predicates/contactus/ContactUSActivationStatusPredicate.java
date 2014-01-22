package zm.hashcode.mshengu.app.util.predicates.contactus;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.external.ContactUS;

/**
 *
 * @author boniface
 */
public class ContactUSActivationStatusPredicate implements Predicate<ContactUS> {

    private boolean contactUSStatus;
    
    public ContactUSActivationStatusPredicate(boolean contactUSStatus){
        this.contactUSStatus =  contactUSStatus;
    }
    
    @Override
    public boolean apply(ContactUS contactUS) {
        if (contactUSStatus == getContactUS(contactUS)) {
            return true;
        }
        return false;
    }

    private boolean getContactUS(ContactUS contactUS) {
        if (contactUS != null) {
            return contactUS.isClosed();
        }
        return false;

    }
    
}

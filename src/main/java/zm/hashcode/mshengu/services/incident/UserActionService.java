/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.incident;

import java.util.List;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author Ferox
 */
public interface UserActionService {

    public List<UserAction> findAll();

    public void persist(UserAction userAction);

    public void merge(UserAction userAction);

    public UserAction findById(String id);

    public void delete(UserAction userAction);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.util.Role;

/**
 *
 * @author lucky
 */
public interface RoleService {
    public List<Role> findAll();
    public void persist(Role rolesList);
    public void merge(Role rolesList);
    public Role findById(String id);
    public void delete(Role rolesList);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet;

import java.util.List;
import zm.hashcode.mshengu.domain.fleet.OperationalAllowance;

/**
 *
 * @author geek
 */
public interface OperationalAllowanceService {

    public List<OperationalAllowance> findAll();

    public void persist(OperationalAllowance operationalAllowance);

    public void merge(OperationalAllowance operationalAllowance);

    public OperationalAllowance findById(String id);

    public void delete(OperationalAllowance operationalAllowance);
}

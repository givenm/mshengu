/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.position;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.position.DepartureReason;

/**
 *
 * @author lucky
 */
public interface DepartureReasonService {
    public List<DepartureReason> findAll();
    public void persist(DepartureReason departureReason);
    public void merge(DepartureReason departureReason);
    public DepartureReason findById(String id);
    public void delete(DepartureReason departureReason);
}

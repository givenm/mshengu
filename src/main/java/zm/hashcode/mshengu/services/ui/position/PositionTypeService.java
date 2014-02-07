/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.position;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.position.PositionType;

/**
 *
 * @author lucky
 */
public interface PositionTypeService {
    public List<PositionType> findAll();
    public void persist(PositionType positionType);
    public void merge(PositionType positionType);
    public PositionType findById(String id);
    public void delete(PositionType positionType);
}

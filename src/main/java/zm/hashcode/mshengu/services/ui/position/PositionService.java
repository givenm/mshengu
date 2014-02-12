/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.position;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.position.Position;

/**
 *
 * @author lucky
 */
public interface PositionService {
    public List<Position> findAll();
    public void persist(Position position);
    public void merge(Position position);
    public Position findById(String id);
    public void delete(Position position);
}

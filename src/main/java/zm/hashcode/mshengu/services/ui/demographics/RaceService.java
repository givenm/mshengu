/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.demographics.Race;

/**
 *
 * @author lucky
 */
public interface RaceService {
    public List<Race> findAll();
    public void persist(Race raceList);
    public void merge(Race raceList);
    public Race findById(String id);
    public void delete(Race raceList);
}

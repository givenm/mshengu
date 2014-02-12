/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Ferox
 */
public interface SequenceService {
    public List<Sequence> findAll();
    public void persist(Sequence sequence);
    public void merge(Sequence sequence);
    public Sequence findById(String id);
    public void delete(Sequence sequence);
    public Sequence findByName(String name);
}

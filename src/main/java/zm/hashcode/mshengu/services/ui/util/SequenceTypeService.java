/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.SequenceType;

/**
 *
 * @author Ferox
 */
public interface SequenceTypeService {
    public List<SequenceType> findAll();
    public void persist(SequenceType sequenceTypeList);
    public void merge(SequenceType sequenceTypeList);
    public SequenceType findById(String id);
    public void delete(SequenceType sequenceTypeList);
    public SequenceType findByName(String name);
}

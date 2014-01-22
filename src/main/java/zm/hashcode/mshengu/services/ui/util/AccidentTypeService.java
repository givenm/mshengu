/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.AccidentType;

/**
 *
 * @author lucky
 */
public interface AccidentTypeService {
    public List<AccidentType> findAll();
    public void persist(AccidentType accidentTypeList);
    public void merge(AccidentType accidentTypeList);
    public AccidentType findById(String id);
    public void delete(AccidentType accidentTypeList);
}

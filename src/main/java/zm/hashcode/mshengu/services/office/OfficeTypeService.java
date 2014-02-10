/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.office;

import java.util.List;
import zm.hashcode.mshengu.domain.office.OfficeType;

/**
 *
 * @author lucky
 */
public interface OfficeTypeService {
    public List<OfficeType> findAll();
    public void persist(OfficeType officeType);
    public void merge(OfficeType officeType);
    public OfficeType findById(String id);
    public void delete(OfficeType officeType);
}

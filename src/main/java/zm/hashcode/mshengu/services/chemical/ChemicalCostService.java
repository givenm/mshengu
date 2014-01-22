/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.chemical;

import java.util.List;
import zm.hashcode.mshengu.domain.chemical.ChemicalCost;

/**
 *
 * @author geek
 */
public interface ChemicalCostService {

    public List<ChemicalCost> findAll();

    public void persist(ChemicalCost entity);

    public void merge(ChemicalCost entity);

    public ChemicalCost findById(String id);

    public void delete(ChemicalCost entity);
}

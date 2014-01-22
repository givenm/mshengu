/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet;

import java.util.List;
import zm.hashcode.mshengu.domain.fleet.AnnualDataFleetFuel;

/**
 *
 * @author ColinWa
 */
public interface AnnualDataFleetFuelService {

    public List<AnnualDataFleetFuel> findAll();

    public void persist(AnnualDataFleetFuel annualData);

    public void merge(AnnualDataFleetFuel annualData);

    public AnnualDataFleetFuel findById(String id);

    public void delete(AnnualDataFleetFuel annualData);
}

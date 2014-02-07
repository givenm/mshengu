/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;

/**
 *
 * @author Colin
 */
public interface AnnualDataFleetMaintenanceMileageRepository extends PagingAndSortingRepository<AnnualDataFleetMaintenanceMileage, String>, AnnualDataFleetMaintenanceMileageRepositoryCustom {
}

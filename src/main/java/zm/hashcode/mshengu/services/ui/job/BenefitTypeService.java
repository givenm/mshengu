/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.job;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.job.BenefitType;

/**
 *
 * @author lucky
 */
public interface BenefitTypeService {
    public List<BenefitType> findAll();
    public void persist(BenefitType benefitType);
    public void merge(BenefitType benefitType);
    public BenefitType findById(String id);
    public void delete(BenefitType benefitType);
}

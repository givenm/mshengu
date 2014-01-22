/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.job;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.job.BenefitFrequency;

/**
 *
 * @author lucky
 */
public interface BenefitFrequencyService {

    public List<BenefitFrequency> findAll();

    public void persist(BenefitFrequency benefitFrequency);

    public void merge(BenefitFrequency benefitFrequency);

    public BenefitFrequency findById(String id);

    public void delete(BenefitFrequency benefitFrequency);
}

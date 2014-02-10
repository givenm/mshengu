/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.job;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.job.SalaryGrade;

/**
 *
 * @author lucky
 */
public interface SalaryGradeService {
    public List<SalaryGrade> findAll();
    public void persist(SalaryGrade salaryGrade);
    public void merge(SalaryGrade salaryGrade);
    public SalaryGrade findById(String id);
    public void delete(SalaryGrade salaryGrade);
}

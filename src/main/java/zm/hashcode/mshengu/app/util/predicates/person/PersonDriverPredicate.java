package zm.hashcode.mshengu.app.util.predicates.person;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;

/**
 *
 * @author boniface
 */
public class PersonDriverPredicate implements Predicate<Person> {

    /**
     *
     * @param person
     * @return
     */
    @Override    
     public boolean apply(Person person) {
        if ("DRIVER".equalsIgnoreCase(getCheckIfStaff(person))) {
            return true;
        }
        return false;
    }
     
     private String getCheckIfStaff(Person person) {
        if (person.getEmployeeDetails() != null) {
            return getPositionName(person.getEmployeeDetails());
        }
        return null;
    }
     private String getPositionName(EmployeeDetail employeeDetail) {
        if (employeeDetail.getJobPosition() != null) {
            return getJobPosition(employeeDetail.getJobPosition());
        }
        return null;
    }
     
     private String getJobPosition(JobPosition jobPosition) {
        if (jobPosition != null) {
            return jobPosition.getName();
        }
        return null;
    }

}

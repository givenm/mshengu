/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.predicates.person;

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Luckbliss
 */
public class PersonDrivesCompanyCarPredicate implements Predicate<Person> {

    
     @Override
    public boolean apply(Person person) {
        if (true == isEmployee(person)) {
            return true;
        }
        return false;
    }

    private boolean isEmployee(Person person) {
        if (person != null) {
            return drivesCompanyCar(person.getEmployeeDetails());
        }
        return false;
    }
    
    
        private boolean drivesCompanyCar(EmployeeDetail employeeDetail) {
        if (employeeDetail != null) {
            return employeeDetail.isDrivesCompanyCar();
        }
        return false;
    }
}

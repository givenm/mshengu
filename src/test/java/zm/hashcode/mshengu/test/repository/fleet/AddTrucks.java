/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.fleet;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import junit.framework.Assert;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.security.PasswordEncrypt;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;
import zm.hashcode.mshengu.domain.ui.util.Role;
import zm.hashcode.mshengu.repository.fleet.TruckCategoryRepository;
import zm.hashcode.mshengu.repository.fleet.TruckRepository;
import zm.hashcode.mshengu.repository.people.PersonRepository;
import zm.hashcode.mshengu.repository.ui.util.PaymentMethodRepository;
import zm.hashcode.mshengu.repository.ui.util.RoleRepository;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author lucky
 */
public class AddTrucks extends AppTest {

    private TruckRepository truckRepository;
    private TruckCategoryRepository categoryRepository;
    private PersonRepository personRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private RoleRepository roleRepository;

//    @Test
    public void testCreate() {
        truckRepository = ctx.getBean(TruckRepository.class);
        categoryRepository = ctx.getBean(TruckCategoryRepository.class);
        personRepository = ctx.getBean(PersonRepository.class);
        paymentMethodRepository = ctx.getBean(PaymentMethodRepository.class);
        roleRepository = ctx.getBean(RoleRepository.class);

        //Truck1
        TruckCategory category1 = new TruckCategory.Builder("truckcategory1").build();
        categoryRepository.save(category1);
        TruckCategory category = categoryRepository.findOne(category1.getId());
        Role role1 = new Role.Builder("ROLE_ADMIN").description("System Administrator").build();
        roleRepository.save(role1);
        Role role = roleRepository.findOne(role1.getId());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        String password = PasswordEncrypt.encrypt("vincent");
        Person driver1 = new Person.Builder("Vincent")
                .username("vincent")
                .password(password)
                .role(roles)
                .enable(true)
                .build();
        personRepository.save(driver1);
        Person person = personRepository.findOne(driver1.getId());
        PaymentMethod method1 = new PaymentMethod.Builder("paymentmethod1").build();
        paymentMethodRepository.save(method1);
        Truck truck1 = new Truck.Builder("CA 744 851")
                .category(category)
                .driver(person)
                .vehicleCost(new BigDecimal("2000.00"))
                .build();
        truckRepository.save(truck1);

        //Truck2
        TruckCategory category2 = new TruckCategory.Builder("truckcategory2").build();
        categoryRepository.save(category2);
        category = categoryRepository.findOne(category1.getId());
        Role role2 = new Role.Builder("ROLE_ADMIN").description("System Administrator").build();
        roleRepository.save(role2);
        role = roleRepository.findOne(role2.getId());
        roles = new HashSet<>();
        roles.add(role);
        password = PasswordEncrypt.encrypt("isaac");
        Person driver2 = new Person.Builder("Isaac")
                .username("isaac")
                .password(password)
                .role(roles)
                .enable(true)
                .build();
        personRepository.save(driver1);
        person = personRepository.findOne(driver1.getId());
        PaymentMethod method2 = new PaymentMethod.Builder("paymentmethod2").build();
        paymentMethodRepository.save(method2);
        Truck truck2 = new Truck.Builder("CA 182 874")
                .category(category)
                .driver(person)
                .vehicleCost(new BigDecimal("2000.00"))
                .build();
        truckRepository.save(truck2);

        //Truck3
        TruckCategory category3 = new TruckCategory.Builder("truckcategory3").build();
        categoryRepository.save(category3);
        category = categoryRepository.findOne(category1.getId());
        Role role3 = new Role.Builder("ROLE_ADMIN").description("System Administrator").build();
        roleRepository.save(role3);
        role = roleRepository.findOne(role3.getId());
        roles = new HashSet<>();
        roles.add(role);
        password = PasswordEncrypt.encrypt("mcoy");
        Person driver3 = new Person.Builder("Mcoy")
                .username("mcoy")
                .password(password)
                .role(roles)
                .enable(true)
                .build();
        personRepository.save(driver1);
        person = personRepository.findOne(driver1.getId());
        PaymentMethod method3 = new PaymentMethod.Builder("paymentmethod3").build();
        paymentMethodRepository.save(method3);
        Truck truck3 = new Truck.Builder("CA 481 048")
                .category(category)
                .driver(person)
                .vehicleCost(new BigDecimal("2000.00"))
                .build();
        truckRepository.save(truck3);

        //Truck4
        TruckCategory category4 = new TruckCategory.Builder("truckcategory4").build();
        categoryRepository.save(category4);
        category = categoryRepository.findOne(category1.getId());
        Role role4 = new Role.Builder("ROLE_ADMIN").description("System Administrator").build();
        roleRepository.save(role4);
        role = roleRepository.findOne(role4.getId());
        roles = new HashSet<>();
        roles.add(role);
        password = PasswordEncrypt.encrypt("calin");
        Person driver4 = new Person.Builder("Calin")
                .username("calin")
                .password(password)
                .role(roles)
                .enable(true)
                .build();
        personRepository.save(driver1);
        person = personRepository.findOne(driver1.getId());
        PaymentMethod method4 = new PaymentMethod.Builder("paymentmethod4").build();
        paymentMethodRepository.save(method4);
        Truck truck4 = new Truck.Builder("CA 441 849")
                .category(category)
                .driver(person)
                .vehicleCost(new BigDecimal("2000.00"))
                .build();
        truckRepository.save(truck4);

        Assert.assertEquals(category1.getCategoryName(), "truckcategory1");
    }
}

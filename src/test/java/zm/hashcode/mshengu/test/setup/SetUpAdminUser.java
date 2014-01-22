/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.setup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import zm.hashcode.mshengu.app.security.PasswordEncrypt;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.domain.ui.util.Role;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.ui.location.LocationService;
import zm.hashcode.mshengu.services.ui.util.RoleService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class SetUpAdminUser extends AppTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PersonService personService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private LocationService locationService;
    private String roleId;

//    @Test
    public void findSite() {
        siteService = ctx.getBean(SiteService.class);
        List<Site> siteList = siteService.findByName("Bellville", "");
//       siteList.subList(fromIndex, toIndex)
        if (siteList != null) {
            System.out.println("Site Count = " + siteList.size());
            int x = 1;


            for (Site site : siteList) {

                System.out.println("Site Name : " + site.getName());
                System.out.println("Suburb : " + site.getLocationName());
                System.out.println("Region : " + getParentLocationName(site.getLocation()));
                System.out.println("Parent id : " + site.getParentId());
                System.out.println("X: : " + x);
            }
        }

    }

//       @Test
    public void findLocation() {
        locationService = ctx.getBean(LocationService.class);
        List<Location> locationList = locationService.findByName("Bellville", "");
//       siteList.subList(fromIndex, toIndex)
        if (locationList != null) {
            System.out.println("Site Count = " + locationList.size());
            int x = 1;


            for (Location location : locationList) {

                System.out.println("location Name : " + location.getName());
                System.out.println("Type : " + location.getLocationTypeName());
                System.out.println("Region : " + location.getParentLocationName());
//            System.out.println("Parent id : " + site.getParentId());
                System.out.println("X: : " + x);
                x++;
            }
        }

    }

    private String getParentLocationName(Location location) {
        if (location != null) {
            return location.getParentLocationName();
        } else {
            return "N/A";
        }
    }

//    @Test
    public void createRole() {
        roleService = ctx.getBean(RoleService.class);
        Role role = new Role.Builder("ROLE_ADMIN").description("System Administrator").build();
        roleService.persist(role);
        roleId = role.getId();
    }

//    @Test(dependsOnMethods = {"createRole"})
    public void craeteUser() {
        roleService = ctx.getBean(RoleService.class);
        personService = ctx.getBean(PersonService.class);

        Role role = roleService.findById(roleId);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        String password = PasswordEncrypt.encrypt("admin");
        Person user = new Person.Builder("Admin")
                .username("admin")
                .password(password)
                .role(roles)
                .enable(true)
                .firstname("Administrator")
                .build();

        personService.persist(user);
    }
}

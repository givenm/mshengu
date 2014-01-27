/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.customer;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.customer.Contract;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.services.customer.ContractService;
import zm.hashcode.mshengu.services.customer.CustomerService;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.ui.util.RoleService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class CustomerContractTest extends AppTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PersonService personService;
    @Autowired
    private SiteService siteService;
    @Autowired
    CustomerService customerService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ContractService contractService;
    private String roleId;

//    @Test
    public void customerContracts() {
        customerService = ctx.getBean(CustomerService.class);
        List<Customer> customerList = customerService.findAll();
//       siteList.subList(fromIndex, toIndex)
        if (customerList != null) {
            int count = customerList.size();
            System.out.println("Customer Count = " + count);
            int x = 1;


            for (Customer customer : customerList) {
                System.out.println("\n=============Site no " + x + "/" + count + "==================");
                System.out.println("Customer Name : " + customer.getName());


                Set<Contract> contractList = customer.getContracts();

                if (contractList != null) {
                    for (Contract contract : contractList) {
                        System.out.println("Contract Date DaOf action " + contract.getDateofAction());
                        System.out.println("Contract Start Date " + contract.getStartDate());
                        System.out.println("Contract  Update Date " + contract.getDateofUpdate());
                        System.out.println("Contract  Type " + contract.getContractTypeName());
                    }
                }
                System.out.println("Last Contract Type" + customer.getLastContactTypeName());
                System.out.println("Last Contract Date of Action Date " + customer.getLastContactDateOfAction());
                x++;
            }
        }

    }

//    @Test
    public void updateCustomerContract() {
        contractService = ctx.getBean(ContractService.class);
        mongoTemplate = ctx.getBean(MongoTemplate.class);

        Query query = new Query(Criteria
                .where("dateofAction").is(null));
//                .andOperator(
//                Criteria.where("serviceDate").gte(serviceDateStart),
//                Criteria.where("serviceDate").lte(serviceDateEnd),
//                Criteria.where("statusMessage").is(statusMessage)));
//        query.with(new Sort(Sort.Direction.DESC, "date")); WITHIN
//        long count = mongoTemplate.find(query, Contract.class, "contract");
        List<Contract> contractList = mongoTemplate.find(query, Contract.class, "contract");

        if (contractList != null) {
            for (Contract contract : contractList) {
                System.out.println("Contract  Parent ID  " + contract.getParentId());
                System.out.println("Contract Date Da Of action (before) " + contract.getDateofAction());
                System.out.println("Contract Start Date " + contract.getStartDate());
                System.out.println("Contract  Update Date " + contract.getDateofUpdate());
                System.out.println("Contract  Type " + contract.getContractTypeName());

                Contract newContrac = new Contract.Builder(new Date())
                        .contract(contract)
                        .build();

                contractService.merge(newContrac);
                System.out.println("Contract Date Da Of action (After) " + newContrac.getDateofAction());

            }
        }


    }

//    @Test
    public void checkCustomer() {
        customerService = ctx.getBean(CustomerService.class);
        List<Customer> customerList = customerService.findAll();
//       siteList.subList(fromIndex, toIndex)
        int aggTotalCust = 0;
        int aggtotalSIte = 0;
        int calcTotalCust = 0;
        int calcTotalSite = 0;

        if (customerList != null) {
            int countCust = customerList.size();
            aggTotalCust = countCust;
            System.out.println("Customer Count = " + countCust);
            int xCust = 1;


            for (Customer customer : customerList) {
                calcTotalCust++;
                System.out.println("\n=============Customer no " + xCust + " of " + countCust + "==================");
                System.out.println("Customer Name : " + customer.getName());
//       siteList.subList(fromIndex, toIndex)

                Set<Site> siteList = customer.getSites();

                if (siteList != null) {
                    int countSite = siteList.size();
                    aggtotalSIte += countSite;


                    int xSite = 1;


                    for (Site site : siteList) {
                        calcTotalSite++;
                        System.out.println("Site no " + xSite + "/" + countSite + " ==>  " + site.getName());//  " || has " + site.getLastSiteServiceContractLifeCycle().getNumberOfUnits() + "out of ");
//                        System.out.println("Site Name : " );
//
//         
                        xSite++;
                    }
//                                        System.out.println("Customer Site Count = " + countSite);
                    xCust++;
                    System.out.println("\nCustomer Total Sites " + countSite);
                } else {
                    System.out.println("Site == NULL");
                    System.out.println("Customer Total Sites " + 0);
                }
            }
            System.out.println("\n===============================\nFinal Totals");
            System.out.println("Agg Customer Total " + aggTotalCust);
            System.out.println("Agg Site Total " + aggtotalSIte);
//            System.out.println("Calc Customer Total " + calcTotalCust);
//            System.out.println("Calc Site Total " + calcTotalSite);
//                System.out.println("Contract  Type " + site.getContractTypeName());
        }
    }
//    Contract contract = contractService.findById("5282f26f823063df45045c53");
//    Contract newContrac = new Contract.Builder(new Date())
//            .contract(contract)
//            .build();
//}
}

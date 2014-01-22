/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.List;
import org.joda.time.DateTime;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.services.products.UnitLocationLifeCycleService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class UnitLocationLifeCycleTest extends AppTest {

    private UnitLocationLifeCycleService service;

//    @Test
    private void createUnitsCycles() {
        service = ctx.getBean(UnitLocationLifeCycleService.class);
        DateTime dt1 = new DateTime(2005, 5, 26, 12, 0, 0, 0);
        DateTime dt2 = new DateTime(2010, 7, 16, 12, 0, 0, 0);
        DateTime dt3 = new DateTime(2006, 3, 16, 12, 0, 0, 0);
        DateTime dt4 = new DateTime(2012, 4, 13, 12, 0, 0, 0);
        DateTime dt5 = new DateTime(2007, 6, 23, 12, 0, 0, 0);
        UnitLocationLifeCycle a1 = new UnitLocationLifeCycle.Builder(dt1.toDate()).latitude("1").longitude("2").build();
        UnitLocationLifeCycle a2 = new UnitLocationLifeCycle.Builder(dt2.toDate()).latitude("2").longitude("3").build();
        UnitLocationLifeCycle a3 = new UnitLocationLifeCycle.Builder(dt3.toDate()).latitude("4").longitude("5").build();
        UnitLocationLifeCycle a4 = new UnitLocationLifeCycle.Builder(dt4.toDate()).latitude("6").longitude("7").build();
        UnitLocationLifeCycle a5 = new UnitLocationLifeCycle.Builder(dt5.toDate()).latitude("8").longitude("2").build();

        service.persist(a1);
        service.persist(a2);
        service.persist(a3);
        service.persist(a4);
        service.persist(a5);

    }

//    @Test
    private void getSortedList() {
        service = ctx.getBean(UnitLocationLifeCycleService.class);
        List<UnitLocationLifeCycle> a = service.findAllSorted();
        for (UnitLocationLifeCycle u : a) {
            System.out.println(" " + u.getDateofAction());

        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author boniface
 */

@ContextConfiguration(classes = TestConfig.class)
public class AppTest {

    public static ApplicationContext ctx;
    public AppTest() {
    }
    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(TestConfig.class);
    }
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    @BeforeMethod
    public void setUpMethod() throws Exception {
 
    }
    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}

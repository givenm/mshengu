/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.documents.FileStorageService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class DocumentsPackagesTest extends AppTest {

    /**
     * Documents: FileStorageService
     */
    @Autowired
    private FileStorageService domainClassToTest01;   // Service

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(FileStorageService.class);
        domainClassToTest01.getByFilename("404");
    }
}

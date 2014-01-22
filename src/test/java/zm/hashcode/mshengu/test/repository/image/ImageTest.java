/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.repository.image;

import com.mongodb.gridfs.GridFSDBFile;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.documents.FileStorageService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author lucky
 */
public class ImageTest extends AppTest {

    private FileStorageService repository;
    private String fileId;

//    @Test
    public void testCreate() {
        repository = ctx.getBean(FileStorageService.class);

        GridFSDBFile id = repository.getByFilename("lt.jpg");
        System.out.println(" THE ID IS " + id);


    }
}

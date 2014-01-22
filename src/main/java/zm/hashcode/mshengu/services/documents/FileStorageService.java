/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.documents;

import com.mongodb.gridfs.GridFSDBFile;
import java.io.InputStream;

/**
 *
 * @author Luckbliss
 */
public interface FileStorageService {

    String save(InputStream inputStream, String contentType, String filename);

    GridFSDBFile getById(String id);

    GridFSDBFile getByFilename(String filename);

    void removeById(String id);
}

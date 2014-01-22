/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.files;

import com.mongodb.gridfs.GridFSDBFile;
import com.vaadin.server.StreamResource;
import java.io.InputStream;
import zm.hashcode.mshengu.app.facade.documents.FileStorageFacade;
import zm.hashcode.mshengu.services.documents.FileStorageService;

/**
 *
 * @author boniface
 */
public class PersonFilesgeUtil implements StreamResource.StreamSource {

    private FileStorageService storageService = FileStorageFacade.getFileStorageService();
    private final String fileId;
    private GridFSDBFile gridfile;

    public PersonFilesgeUtil(String fileId) {
        this.fileId = fileId;
        gridfile = storageService.getById(fileId);
    }

    @Override
    public InputStream getStream() {
        if (fileId != null) {
//            gridfile = storageService.getById(fileId);
//            gridfile.
            if (gridfile != null) {
                return gridfile.getInputStream();
            }
            return null;
        } else {
            return null;
        }
    }

    public String getFileName() {
        if (gridfile != null) {
            return gridfile.getFilename();
        }
        return null;
    }
}

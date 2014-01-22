/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.files;

import com.mongodb.gridfs.GridFSDBFile;
import com.vaadin.server.StreamResource;
import java.io.InputStream;
import zm.hashcode.mshengu.app.facade.documents.FileStorageFacade;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.services.documents.FileStorageService;

/**
 *
 * @author boniface
 */
public class ProfileImageUtil implements StreamResource.StreamSource {

    private FileStorageService storageService = FileStorageFacade.getFileStorageService();
    private final Person person;

    public ProfileImageUtil(Person person) {
        this.person = person;
    }

    @Override
    public InputStream getStream() {
        EmployeeDetail detail = person.getEmployeeDetails();
        if (detail != null) {
            GridFSDBFile gridfile = storageService.getById(detail.getProfileImage());
            if (gridfile != null) {
                return gridfile.getInputStream();
            }
            return null;
        } else {
            return null;
        }
    }
}

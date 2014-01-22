/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.documents;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.documents.FileStorageService;

/**
 *
 * @author Luckbliss
 */
public class FileStorageFacade {

    private final static SpringContext ctx = new SpringContext();

    public static FileStorageService getFileStorageService() {
        return ctx.getBean(FileStorageService.class);
    }
}

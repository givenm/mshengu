/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.documents;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.documents.DocumentsService;

/**
 *
 * @author Luckbliss
 */
public class DocumentsFacade {

    private final static SpringContext ctx = new SpringContext();

    public static DocumentsService getDocumentsService() {
        return ctx.getBean(DocumentsService.class);
    }
}

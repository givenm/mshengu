/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.job;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.job.JobClassificationService;

/**
 *
 * @author lucky
 */
public class JobClassificationFacade {
    private final static SpringContext ctx = new SpringContext();
     public static JobClassificationService getJobClassificationService(){
         return ctx.getBean(JobClassificationService.class);
     }  
}

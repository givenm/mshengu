/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.job;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.job.SalaryGradeService;

/**
 *
 * @author lucky
 */
public class SalaryGradeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static SalaryGradeService getSalaryGradeService(){
         return ctx.getBean(SalaryGradeService.class);
     }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author boniface
 */
@Service
public class ScheduleExecution {
    
    /**
     
        * * * * * command to be executed
        - - - - -
        | | | | |
        | | | | ----- Day of week (0 - 7) (Sunday=0 or 7)
        | | | ------- Month (1 - 12)
        | | --------- Day of month (1 - 31)
        | ----------- Hour (0 - 23)
        ------------- Minute (0 - 59)
     
      */

    @Scheduled(cron = "* 15 21-23 * * MON-SAT")// Run 15 Minutes Past every Hour between 21 hrs and 23 hours from Monday to Saturday
    public void runthisCode() {
        // Code to be execute duering Schedule

    }

}

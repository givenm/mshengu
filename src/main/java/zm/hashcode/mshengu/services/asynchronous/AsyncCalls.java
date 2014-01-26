/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zm.hashcode.mshengu.services.asynchronous;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author boniface
 */
@Service
public class AsyncCalls {
    @Async
    void doSomething(String s) {
    // this will be executed asynchronously
}
}

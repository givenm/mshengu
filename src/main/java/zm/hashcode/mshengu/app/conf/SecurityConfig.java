/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 *
 * @author boniface
 */
@Configuration
@ImportResource("classpath*:*applicationContext-security.xml")
public class SecurityConfig {
    @Bean
    public DelegatingFilterProxy springSecurityFilterChain() {
    	DelegatingFilterProxy filterProxy =  new DelegatingFilterProxy();
        return filterProxy;
    }	
}

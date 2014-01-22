/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.Terminate;

/**
 *
 * @author Luckbliss
 */
public interface TerminateService {

    public List<Terminate> findAll();

    public void persist(Terminate terminate);

    public void merge(Terminate terminate);

    public Terminate findById(String id);

    public void delete(Terminate terminate);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.List;
import zm.hashcode.mshengu.domain.procurement.PurchaseOrderNumber;

/**
 *
 * @author Luckbliss
 */
public interface PurchaseOrderNumberService {

    public List<PurchaseOrderNumber> findAll();

    public void persist(PurchaseOrderNumber purchaseOrderNumber);

    public void merge(PurchaseOrderNumber purchaseOrderNumber);

    public PurchaseOrderNumber findById(String id);

    public void delete(PurchaseOrderNumber purchaseOrderNumber);
}

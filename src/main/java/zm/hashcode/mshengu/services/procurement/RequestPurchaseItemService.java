/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.List;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

/**
 *
 * @author Luckbliss
 */
public interface RequestPurchaseItemService {

    public List<RequestPurchaseItem> findAll();

    public void persist(RequestPurchaseItem requestPurchaseItem);

    public void merge(RequestPurchaseItem requestPurchaseItem);

    public RequestPurchaseItem findById(String id);

    public void delete(RequestPurchaseItem requestPurchaseItem);
}

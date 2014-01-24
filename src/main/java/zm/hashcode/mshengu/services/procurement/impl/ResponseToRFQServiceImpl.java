/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.procurement.ResponseToRFQ;
import zm.hashcode.mshengu.repository.procurement.ResponseToRFQRepository;
import zm.hashcode.mshengu.services.procurement.ResponseToRFQService;
//import zm.hashcode.mshengu.services.procurement.ResponseToRFQService;

/**
 *
 * @author Luckbliss
 */
@Service
public class ResponseToRFQServiceImpl implements ResponseToRFQService {

    @Autowired
    private ResponseToRFQRepository repository;

    @Override
    public List<ResponseToRFQ> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByOrderNumber()));
    }

    @Override
    public void persist(ResponseToRFQ request) {
        repository.save(request);

    }

    @Override
    public void merge(ResponseToRFQ request) {
        if (request.getId() != null) {
            repository.save(request);
        }
    }

    @Override
    public ResponseToRFQ findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ResponseToRFQ request) {
        repository.delete(request);
    }

    private Sort sortByOrderNumber() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "companyName"));
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet.impl;

import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.FuelAndOilPrice;
import zm.hashcode.mshengu.repository.fleet.FuelAndOilPriceRepository;
import zm.hashcode.mshengu.services.fleet.FuelAndOilPriceService;

/**
 *
 * @author geek
 */
@Service
public class FuelAndOilPriceServiceImpl implements FuelAndOilPriceService {

    @Autowired
    private FuelAndOilPriceRepository repository;

    @Override
    public List<FuelAndOilPrice> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByDate()));
    }

    @Override
    public void persist(FuelAndOilPrice value) {
        repository.save(value);
    }

    @Override
    public void merge(FuelAndOilPrice value) {
        if (value.getId() != null) {
            repository.save(value);
        }
    }

    @Override
    public FuelAndOilPrice findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(FuelAndOilPrice value) {
        repository.delete(value);
    }

    private Sort sortByDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "entryDate"));
    }

    @Override
    public BigDecimal getCurrentFuelPrice() {
        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        List<FuelAndOilPrice> fuelAndOilPriceCollection = findAll();
        Date todayDate = dateTimeFormatHelper.resetTimeOfDate(new Date());

        for (FuelAndOilPrice fuelAndOilPrice : fuelAndOilPriceCollection) {
            if (todayDate.after(dateTimeFormatHelper.resetTimeOfDate(fuelAndOilPrice.getFuelEffectDate()))
                    || todayDate.equals(dateTimeFormatHelper.resetTimeOfDate(fuelAndOilPrice.getFuelEffectDate()))) {
                return fuelAndOilPrice.getFuelPrice();
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getCurrentEngineOilPrice() {
        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        List<FuelAndOilPrice> fuelAndOilPriceCollection = findAll();
        Date todayDate = dateTimeFormatHelper.resetTimeOfDate(new Date());

        for (FuelAndOilPrice fuelAndOilPrice : fuelAndOilPriceCollection) {
            if (todayDate.after(dateTimeFormatHelper.resetTimeOfDate(fuelAndOilPrice.getEngineOilEffectDate()))
                    || todayDate.equals(dateTimeFormatHelper.resetTimeOfDate(fuelAndOilPrice.getEngineOilEffectDate()))) {
                return fuelAndOilPrice.getEngineOilPrice();
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public FuelAndOilPrice getCurrentFuelAndOilPriceEntity() {
        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        List<FuelAndOilPrice> fuelAndOilPriceCollection = findAll();
        Date todayDate = dateTimeFormatHelper.resetTimeOfDate(new Date());

        for (FuelAndOilPrice fuelAndOilPrice : fuelAndOilPriceCollection) {
            if (todayDate.after(dateTimeFormatHelper.resetTimeOfDate(fuelAndOilPrice.getFuelEffectDate()))
                    || todayDate.equals(dateTimeFormatHelper.resetTimeOfDate(fuelAndOilPrice.getFuelEffectDate()))) {
                return fuelAndOilPrice;
            }
        }
        return null;
    }
}

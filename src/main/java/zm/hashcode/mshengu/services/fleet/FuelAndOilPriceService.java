/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet;

import java.math.BigDecimal;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.FuelAndOilPrice;

/**
 *
 * @author geek
 */
public interface FuelAndOilPriceService /*extends Services<FuelAndOilPrice,String> */ {

    public List<FuelAndOilPrice> findAll();

    public void persist(FuelAndOilPrice fuelAndOilPrice);

    public void merge(FuelAndOilPrice fuelAndOilPrice);

    public FuelAndOilPrice findById(String id);

    public void delete(FuelAndOilPrice fuelAndOilPrice);

    public BigDecimal getCurrentFuelPrice();

    public BigDecimal getCurrentEngineOilPrice();

    public FuelAndOilPrice getCurrentFuelAndOilPriceEntity();
}

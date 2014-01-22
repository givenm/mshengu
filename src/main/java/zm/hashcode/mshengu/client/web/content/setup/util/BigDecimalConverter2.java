/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.util;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.Converter.ConversionException;
import java.math.BigDecimal;
import java.util.Locale;

/**
 *
 * @author boniface
 */
public class BigDecimalConverter2 {//implements Converter<String, BigDecimal> {

  //  @Override
    public BigDecimal convertToModel(String value, Locale locale)
            throws ConversionException {
        return new BigDecimal(value);
    }

  //  @Override
    public String convertToPresentation(BigDecimal value,
            Locale locale) throws ConversionException {
        String string = null;
        if (value != null) {
            string = value.toEngineeringString();
        }
        return string;
    }
 //   @Override
    public Class<BigDecimal> getModelType() {
        return BigDecimal.class;
    }
   // @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}



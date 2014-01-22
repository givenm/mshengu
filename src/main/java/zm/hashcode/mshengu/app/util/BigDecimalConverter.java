/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.Converter.ConversionException;
import java.math.BigDecimal;
import java.util.Locale;

/**
 *
 * @author boniface
 */
public class BigDecimalConverter implements Converter<String, BigDecimal> {

    @Override
    public BigDecimal convertToModel(String value, Class<? extends BigDecimal> targetType, Locale locale) throws ConversionException {
        try {
            return new BigDecimal(value.toString());
        } catch (NullPointerException | NumberFormatException exc) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public String convertToPresentation(BigDecimal value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        String string = null;
        if (value != null) {
            string = value.toEngineeringString();
        }
        return string;
    }

    @Override
    public Class<BigDecimal> getModelType() {
        return BigDecimal.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}

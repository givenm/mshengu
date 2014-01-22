/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zm.hashcode.mshengu.app.util.validation;

import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;

/**
 *
 * @author given
 */
public class MobileNumberValidator implements Validator {
    @Override
    public void validate(Object value) throws InvalidValueException {
        String text = (String) value;
        
        if (!text.matches("\\d*")) {
            throw new InvalidValueException    ("Just numbers allowed");
        }
        
        if (text.length() < 4) {
            throw new InvalidValueException("Too few numbers");
        } else if (text.length() > 4) {
            throw new InvalidValueException ("Too many numbers");
        }
    }
}

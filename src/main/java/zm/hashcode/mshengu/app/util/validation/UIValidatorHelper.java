/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.validation;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.io.Serializable;

/**
 * RegexpValidator
 *
 * @author given
 */
public class UIValidatorHelper implements Serializable {

    public static Validator postalCodeValidator() { //[1-9] is for first number range and [0-9] for other numbers. {3} is for 4 numbers.
        return new RegexpValidator("[1-9][0-9]{3}", "Postal code must be 4 digits only as xxxx"); // Postal code that must be digits).
    }

    public static Validator yearValidator() { //[1-9] is for first number range and [0-9] for other numbers. {3} is for 4 numbers.
        return new RegexpValidator("[1-9][0-9]{3}", "Year must be 4 digits only as xxxx"); // Postal code that must be digits).
    }

    public static Validator phoneNumberValidator() {
        return new RegexpValidator("(\\d{3} )?\\d{3} \\d{4}", "Phone number must have digits with format xxx xxx xxxx");
    }

    public static Validator faxNumberValidator() {
        return new RegexpValidator("(\\d{3} )?\\d{3} \\d{4}", "Fax number must have digits with format xxx xxx xxxx");
    }

    public static Validator mobileNumberValidator() {
        return new RegexpValidator("(\\d{3} )?\\d{3} \\d{4}", "Mobile number must have digits with format xxx xxx xxxx");
    }

    public static Validator emailValidator() {
        return new EmailValidator("Please enter a valid email address");
    }

    public static TextField setRequiredTextField(TextField field, String labelName) {
        field.setRequired(true);
        field.setRequiredError(labelName + " is required.");
        return field;
    }

    public static ComboBox setRequiredComboBox(ComboBox field, String labelName) {
        field.setRequired(true);
        field.setRequiredError(labelName + " must have a selected option!");
        return field;
    }

    public static TextArea setRequiredTextArea(TextArea field, String labelName) {
        field.setRequired(true);
        field.setRequiredError(labelName + " is required.");
        return field;
    }

    public static DateField setRequiredDateField(DateField field, String labelName) {
        field.setRequired(true);
        field.setRequiredError(labelName + " is required.");
        return field;
    }

}

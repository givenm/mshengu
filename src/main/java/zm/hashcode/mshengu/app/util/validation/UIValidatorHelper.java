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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.io.Serializable;

/**
 *
 * @author given
 */
public class UIValidatorHelper implements Serializable {

    public static Validator postalCodeValidation() {
        return new RegexpValidator("[0-9][0-9]{3}", "Postal code must be 4 digits only"); // Postal code that must be digits).
    }

    public static Validator phoneNumberValidation() {
        return new RegexpValidator("[0-9][0-9]{9}", "Phone number must be 10 digits only"); // Postal code that must be digits).
    }

    public static Validator mobileNumberValidation() {
        return new RegexpValidator("[0-9][0-9]{9}", "Mobile number must be 10 digits only"); // Postal code that must be digits).
    }

    public static Validator emailValidation() {
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
}

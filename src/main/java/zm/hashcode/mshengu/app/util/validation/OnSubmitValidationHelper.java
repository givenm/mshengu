/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.validation;

import com.vaadin.data.Validator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.util.Collection;

/**
 *
 * @author given
 */
public class OnSubmitValidationHelper {

    private final Collection<Field<?>> fields;
    private Label errorLabel;

    public OnSubmitValidationHelper(Collection<Field<?>> fields, Label errorLabel) {
        this.fields = fields;
        this.errorLabel = errorLabel;
    }

    public void doValidation() {
        TextField textField = new TextField();
        TextArea textArea = new TextArea();
        DateField dateField = new DateField();
        ComboBox comboBox = new ComboBox();
        for (Field o : fields) {

            String currentMessage = "";
            try {
                if (o instanceof TextField) {
                    textField = (TextField) o;
                    textField.validate();
                } else if (o instanceof TextArea) {
                    textArea = (TextArea) o;
                    textArea.validate();
                } else if (o instanceof DateField) {
                    dateField = (DateField) o;
                    dateField.validate();
                } else if (o instanceof ComboBox) {
                    comboBox = (ComboBox) o;
                    comboBox.validate();
                }

            } catch (Validator.InvalidValueException x) { //works with vaadin required
                currentMessage = x.getMessage();
                if (o instanceof TextField) {
                    textField.setStyleName("invalid");
                } else if (o instanceof TextArea) {
                    textArea.setStyleName("invalid");
                } else if (o instanceof DateField) {
                    dateField.setStyleName("invalid");
                } else if (o instanceof ComboBox) {
                    comboBox.setStyleName("invalid");
                }

            } finally {
                if (o instanceof TextField) {
                    textField.addFocusListener(new LabelErrorMessageManipulator(textField, errorLabel, currentMessage)); //custom focus handler for displaying error message on a labe when you focus on an errored Textfield                       
                } else if (o instanceof TextArea) {
                    textArea.addFocusListener(new LabelErrorMessageManipulator(textArea, errorLabel, currentMessage)); //custom focus handler for displaying error message on a labe when you focus on an errored Textfield
                } else if (o instanceof DateField) {
                    dateField.addFocusListener(new LabelErrorMessageManipulator(dateField, errorLabel, currentMessage)); //custom focus handler for displaying error message on a labe when you focus on an errored Textfield                
                } else if (o instanceof ComboBox) {
                    comboBox.addFocusListener(new LabelErrorMessageManipulator(comboBox, errorLabel, currentMessage)); //custom focus handler for displaying error message on a labe when you focus on an errored Textfield
                }
            }

            if (o instanceof TextField) {
                textField.addBlurListener(new LabelErrorMessageManipulator(textField, errorLabel, currentMessage)); //custom blur handler for displaying error message on a labe when you blur on an errored Textfield                       
            } else if (o instanceof TextArea) {
                textArea.addBlurListener(new LabelErrorMessageManipulator(textArea, errorLabel, currentMessage)); //custom blur handler for displaying error message on a labe when you blur on an errored Textfield
            } else if (o instanceof DateField) {
                dateField.addBlurListener(new LabelErrorMessageManipulator(dateField, errorLabel, currentMessage)); //custom blur handler for displaying error message on a labe when you blur on an errored Textfield
            } else if (o instanceof ComboBox) {
                comboBox.addBlurListener(new LabelErrorMessageManipulator(comboBox, errorLabel, currentMessage)); //custom blur handler for displaying error message on a labe when you blur on an errored Textfield
            }
        }

    }
}

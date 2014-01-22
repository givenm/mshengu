/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.validation;

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
        for (Field o : fields) {
            TextField textField = new TextField();
            TextArea textArea = new TextArea();
            String currentMessage = "";
            try {
                if (o instanceof TextField) {
                    textField = (TextField) o;
                    textField.validate();
                } else if (o instanceof TextArea) {
                    textArea = (TextArea) o;
                    textArea.validate();
                }

            } catch (Exception x) { //works with vaadin required
                currentMessage = x.getMessage();
                if (o instanceof TextField) {
                    textField.setStyleName("invalid");
                } else if (o instanceof TextArea) {
                    textArea.setStyleName("invalid");
                }

            } finally {
                if (o instanceof TextField) {
                    textField.addFocusListener(new LabelErrorMessageManipulator(textField, errorLabel, currentMessage)); //custom focus handler for displaying error message on a labe when you focus on an errored Textfield                       
                } else if (o instanceof TextArea) {
                    textArea.addFocusListener(new LabelErrorMessageManipulator(textArea, errorLabel, currentMessage)); //custom focus handler for displaying error message on a labe when you focus on an errored Textfield
                }

            }
            if (o instanceof TextField) {
                textField.addBlurListener(new LabelErrorMessageManipulator(textField, errorLabel, currentMessage)); //custom focus handler for displaying error message on a labe when you focus on an errored Textfield                       
            } else if (o instanceof TextArea) {
                textArea.addBlurListener(new LabelErrorMessageManipulator(textArea, errorLabel, currentMessage)); //custom focus handler for displaying error message on a labe when you focus on an errored Textfield
            }
        }

    }
}

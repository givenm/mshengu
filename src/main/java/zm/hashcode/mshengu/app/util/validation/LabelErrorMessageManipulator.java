/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.validation;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author given
 */
public class LabelErrorMessageManipulator implements FocusListener, BlurListener {

    private String errorMessage;
    private Label errorLabel;
    private Field errorField;

    public LabelErrorMessageManipulator(Field field, Label errorLabel, String errorMessage) {
        initialize(field, errorLabel, errorMessage);
    }

    private void initialize(Field field, Label errorLabel, String errorMessage) {
        this.errorLabel = errorLabel;
        this.errorField = field;
        this.errorMessage = "Validation: " + errorMessage;
    }

    @Override
    public void focus(FieldEvents.FocusEvent event) {
        if (this.errorField.getStyleName().contains("invalid")) {
            try {
                this.errorLabel.setValue(this.errorMessage);
                this.errorField.validate(); ///revalidate
                this.errorField.removeStyleName("invalid");
            } catch (Exception e) {
                reInit("Validation: " + e.getMessage());
                this.errorLabel.setValue(this.errorMessage);
                this.errorField.setStyleName("invalid");
            }
        } else {
            this.errorLabel.setValue("");
        }
    }

    @Override
    public void blur(FieldEvents.BlurEvent event) {
        try {
            this.errorLabel.setValue(this.errorMessage);
            this.errorField.validate(); ///revalidate
            this.errorField.removeStyleName("invalid");
        } catch (Exception e) {
            reInit("Validation: " + e.getMessage());
            this.errorLabel.setValue(this.errorMessage);
            this.errorField.setStyleName("invalid");
        }
        this.errorLabel.setValue("");
    }

    private void reInit(String error) {
        this.errorMessage = error;
    }

}

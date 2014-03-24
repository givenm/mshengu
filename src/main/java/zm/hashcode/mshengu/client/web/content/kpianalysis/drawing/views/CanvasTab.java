/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.CanvasMenu;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.form.CanvasForm;

/**
 *
 * @author Luckbliss
 */
public class CanvasTab extends VerticalLayout implements Property.ValueChangeListener {

    private final MshenguMain main;
    private CanvasForm form;

    public CanvasTab(MshenguMain app) {
        main = app;
        form = new CanvasForm(app);
        form.insertAllcanvasComponents();
        setSizeFull();
        addComponent(form);
        addListeners();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.month) {
            getValues();
        } else if (property == form.year) {
            getValues();
        }
    }

    private void getValues() {
        if (form.month.getValue() != null && form.year.getValue() != null) {
            form.canvas.clear();
            form.insertAllcanvasComponents();
        } else {
            Notification.show("Enter all values", Notification.Type.TRAY_NOTIFICATION);

        }
    }

    private void addListeners() {
        //Register Button Listeners
        form.month.addValueChangeListener((Property.ValueChangeListener) this);
        form.year.addValueChangeListener((Property.ValueChangeListener) this);
    }
}

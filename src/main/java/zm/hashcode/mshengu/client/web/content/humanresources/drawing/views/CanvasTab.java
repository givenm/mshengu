/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.drawing.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.drawing.CanvasMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.drawing.form.CanvasForm;

/**
 *
 * @author Luckbliss
 */
public class CanvasTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final CanvasForm form;
    
    public CanvasTab(MshenguMain app) {
        main = app;
        form = new CanvasForm();
        setSizeFull();
        addComponent(form);
    }

    private void getHome() {
        main.content.setSecondComponent(new CanvasMenu(main, "LANDING"));
    }
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.policies.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.policies.PoliciesRegulationsMenu;
import zm.hashcode.mshengu.client.web.content.humanresources.policies.form.PoliciesForm;
import zm.hashcode.mshengu.client.web.content.humanresources.policies.tables.PoliciesTable;

/**
 *
 * @author Luckbliss
 */
public class PoliciesTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final PoliciesForm form;
    private final PoliciesTable table;

    public PoliciesTab(MshenguMain app) {
        main = app;
        form = new PoliciesForm();
        table = new PoliciesTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    private void addListeners() {
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getHome() {
        main.content.setSecondComponent(new PoliciesRegulationsMenu(main, "LANDING"));
    }
}
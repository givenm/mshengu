/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class Menu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public Menu() {
    }
    
    public Menu(MshenguMain app, String selectedTab) {
        main = app;
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
    }

    public MshenguMain getMain() {
        return main;
    }

    public TabSheet getTab() {
        return tab;
    }
    
}
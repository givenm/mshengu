/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.panel;

import com.vaadin.ui.Panel;

/**
 *
 * @author Colin
 */
public class PanelEfficiency extends Panel {

    public PanelEfficiency(String caption) {
        setCaption(caption);
        setStyleName("panelEfficiency");
        setSizeUndefined(); // Shrink to fit content
    }

    public PanelEfficiency() {
        setStyleName("panelEfficiency");
        setSizeUndefined(); // Shrink to fit content
    }
}

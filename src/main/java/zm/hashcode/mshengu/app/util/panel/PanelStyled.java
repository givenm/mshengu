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
public class PanelStyled extends Panel {

    public PanelStyled(String caption) {
        setCaption(caption);
        setWidth("100%");
        setHeight("100%");
        setStyleName("panelorange");
        setSizeUndefined(); // Shrink to fit content
    }

    public PanelStyled() {
//        setCaption(caption);
        setWidth("100%");
        setHeight("100%");
        setStyleName("panelorange");
        setSizeUndefined(); // Shrink to fit content
    }
}

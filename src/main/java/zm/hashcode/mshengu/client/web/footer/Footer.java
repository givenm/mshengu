/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.footer;

import com.vaadin.ui.HorizontalLayout;
import java.awt.FlowLayout;

/**
 *
 * @author boniface
 */
public class Footer extends HorizontalLayout{
    
    private final FlowLayout flow = new FlowLayout();
    public Footer() {
        setHeight(90, Unit.PIXELS);
        setWidth(100,Unit.PERCENTAGE);
        
    }
}

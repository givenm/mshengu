/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.forms;

import com.vaadin.ui.VerticalLayout;
import java.awt.Polygon;
import org.vaadin.hezamu.canvas.Canvas;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author boniface
 */
public class LandingPage extends VerticalLayout {

    private Canvas canvas = new Canvas();
    private final MshenguMain main;

    public LandingPage(MshenguMain main) {
        this.main = main;


        canvas.beginPath();
        canvas.moveTo(75, 50);
        canvas.lineTo(125, 50);
        canvas.lineTo(150, 93);
        canvas.lineTo(125, 136);
        canvas.lineTo(75, 136);
        canvas.lineTo(50, 93);
        canvas.lineTo(75, 50);
        canvas.setStrokeStyle("#0000ff");
        canvas.stroke();
        addComponent(canvas);


    }
}

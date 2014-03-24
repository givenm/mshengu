/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import java.util.Random;
import org.vaadin.hezamu.canvas.Canvas;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.models.LoadResultsBean;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.models.InvoiceBean;

/**
 *
 * @author Luckbliss
 */
public class CanvasForm extends FormLayout {

    public UIComboBoxHelper UICombobox = new UIComboBoxHelper();
    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final InvoiceBean bean = new InvoiceBean();
    public final BeanItem<InvoiceBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public ComboBox month = new ComboBox();
    public ComboBox year = new ComboBox();
    private final MshenguMain main;
    private Canvas canvas = new Canvas();
    //Big Pentagon
    private String topRightRectangleColorBigPentagon;
    private String topLeftRectangleColorBigPentagon;
    private String bottomRightRectangleColorBigPentagon;
    private String bottomLeftRectangleColorBigPentagon;
    private String bottomRectangleColorBigPentagon;
    //Component
    private String topCircleColorComponent;
    private String topRightCircleColorComponent;
    private String topLeftCircleColorComponent;
    private String bottomLeftCircleColorComponent;
    private String bottomRightCircleColorComponent;

    public CanvasForm(MshenguMain main) {
        this.main = main;

        GridLayout gridlayout = new GridLayout(3, 10);
        gridlayout.setSizeFull();

        month = UICombobox.getMonthComboBox("Month: ", "month", InvoiceBean.class, binder);
        year = UICombobox.getYearComboBox("Year: ", "year", InvoiceBean.class, binder);

        gridlayout.addComponent(month, 0, 0);
        gridlayout.addComponent(year, 1, 0);

        canvas.setSizeFull();
        canvas.setGlobalAlpha(0.9);

        insertAllcanvasComponents();

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setHeight("700");
        layout.addComponent(canvas);

        addComponent(gridlayout);
        addComponent(new Label("<br>", ContentMode.HTML));
        addComponent(new Label("<br>", ContentMode.HTML));
        addComponent(layout);
    }

    private void drawBigPentagonItems() {
        //Big pentagon
        canvas.saveContext();
        canvas.beginPath();
        canvas.moveTo(400, 200);
        canvas.lineTo(500, 260);
        canvas.lineTo(460, 360);
        canvas.lineTo(340, 360);
        canvas.lineTo(300, 260);
        canvas.closePath();
        canvas.stroke();
        canvas.restoreContext();

        //Top Right rectangle
        canvas.saveContext();
        canvas.beginPath();
        canvas.moveTo(402, 204);
        canvas.lineTo(493, 260);
        canvas.lineTo(473, 269);
        canvas.lineTo(402, 228);
        canvas.setFillStyle(topRightRectangleColorBigPentagon);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Top Left rectangle
        canvas.saveContext();
        canvas.beginPath();
        canvas.moveTo(398, 204);
        canvas.lineTo(306, 260);
        canvas.lineTo(326, 269);
        canvas.lineTo(398, 228);
        canvas.setFillStyle(topLeftRectangleColorBigPentagon);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Bottom right rectangle
        canvas.saveContext();
        canvas.beginPath();
        canvas.moveTo(495, 264);
        canvas.lineTo(458, 356);
        canvas.lineTo(446, 341);
        canvas.lineTo(472.5, 274);
        canvas.setFillStyle(bottomRightRectangleColorBigPentagon);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Bottom left rectangle
        canvas.saveContext();
        canvas.beginPath();
        canvas.moveTo(305, 264);
        canvas.lineTo(342, 356);
        canvas.lineTo(354, 341);
        canvas.lineTo(329, 274);
        canvas.setFillStyle(bottomLeftRectangleColorBigPentagon);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Bottom rectangle
        canvas.saveContext();
        canvas.beginPath();
        canvas.moveTo(357, 343);
        canvas.lineTo(444, 343);
        canvas.lineTo(454, 357);
        canvas.lineTo(346, 357);
        canvas.setFillStyle(bottomRectangleColorBigPentagon);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();
    }

    private void drawSmallPentagonItems() {
        //Small Pentagon
        canvas.beginPath();
        canvas.moveTo(400, 230);
        canvas.lineTo(470, 270);
        canvas.lineTo(443, 340);
        canvas.lineTo(357, 340);
        canvas.lineTo(330, 270);
        canvas.closePath();
        canvas.stroke();
        canvas.restoreContext();
    }

    private void drawComponentCircles() {
        //Component 1 - Field Services
        canvas.beginPath();
        canvas.arc(250, 100, 45, 0, (float) (2 * Math.PI), true);
        canvas.setFillStyle(199, 200, 202);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Component 2 - Fleet Management
        canvas.beginPath();
        canvas.arc(550, 100, 45, 0, 2 * Math.PI, false);
        canvas.setFillStyle(199, 200, 202);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Component 3 - Chemical Usage
        canvas.beginPath();
        canvas.arc(650, 350, 45, 0, 2 * Math.PI, false);
        canvas.setFillStyle(199, 200, 202);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Component 4 - Inventory Management
        canvas.beginPath();
        canvas.arc(400, 520, 45, 0, 2 * Math.PI, false);
        canvas.setFillStyle(199, 200, 202);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Component 5 - Procurement
        canvas.beginPath();
        canvas.arc(150, 350, 45, 0, 2 * Math.PI, false);
        canvas.setFillStyle(199, 200, 202);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();
    }

    //Component 1 - Field Services
    private void drawComponent1Items() {
        //Top circle - No Services Completed
        canvas.beginPath();
        canvas.arc(255, 27, 12, 0, 2 * Math.PI, false);
        canvas.setFillStyle(topCircleColorComponent);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //No Services Completed Joining Lines
        //Left
        canvas.beginPath();
        canvas.moveTo(200, 24);
        canvas.lineTo(244, 24);
        canvas.closePath();
        canvas.stroke();

        //Right
        canvas.beginPath();
        canvas.moveTo(266, 24);
        canvas.lineTo(310, 24);
        canvas.closePath();
        canvas.stroke();

        //Top right circle - Productivity
        canvas.beginPath();
        canvas.arc(330, 80, 12, 0, 2 * Math.PI, false);
        canvas.setFillStyle(topRightCircleColorComponent);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Productivity Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(346, 130);
        canvas.lineTo(333, 91);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(325, 69);
        canvas.lineTo(310, 24);
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle - No. Services Not Completed
        canvas.beginPath();
        canvas.arc(295, 165, 12, 0, 2 * Math.PI, false);
        canvas.setFillStyle(bottomRightCircleColorComponent);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //No. Services Not Completed Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(252, 202);
        canvas.lineTo(288, 174);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(307, 160);
        canvas.lineTo(346, 130);
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle (No Services Not Completed) line arrow to big pentagon
        canvas.beginPath();
        canvas.moveTo(303, 175);
        canvas.lineTo(345, 220);
        canvas.closePath();
        canvas.stroke();

        canvas.beginPath();
        canvas.moveTo(350, 216);
        canvas.lineTo(340, 224);
        canvas.lineTo(350, 226);
        canvas.setFillStyle("black");
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle - Uncompleted Services
        canvas.beginPath();
        canvas.arc(200, 165, 12, 0, 2 * Math.PI, false);
        canvas.setFillStyle(bottomLeftCircleColorComponent);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Uncompleted Services Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(209, 174);
        canvas.lineTo(252, 202);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(189, 160);
        canvas.lineTo(145, 130);
        canvas.closePath();
        canvas.stroke();

        //Top left circle - Services per Vehicle
        canvas.beginPath();
        canvas.arc(170, 80, 12, 0, 2 * Math.PI, false);
        canvas.setFillStyle(topLeftCircleColorComponent);
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Services per Vehicle Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(145, 130);
        canvas.lineTo(166, 91);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(176, 70);
        canvas.lineTo(200, 24);
        canvas.closePath();
        canvas.stroke();
    }

    //Component 2 - Fleet Management
    private void drawComponent2Items() {
        //Top circle - Fuel Efficiency
        canvas.beginPath();
        canvas.arc(555, 27, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Fuel Efficiency Joining Lines
        //Left
        canvas.beginPath();
        canvas.moveTo(499, 24);
        canvas.lineTo(543, 24);
        canvas.closePath();
        canvas.stroke();

        //Right
        canvas.beginPath();
        canvas.moveTo(566, 24);
        canvas.lineTo(610, 24);
        canvas.closePath();
        canvas.stroke();

        //Top right circle - Fuel per Toilet Serviced
        canvas.beginPath();
        canvas.arc(630, 80, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Fuel per Toilet Serviced Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(646, 130);
        canvas.lineTo(633, 91);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(626, 69);
        canvas.lineTo(610, 24);
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle - Fleet Maintenance
        canvas.beginPath();
        canvas.arc(595, 165, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Fleet Maintenance Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(552, 202);
        canvas.lineTo(588, 174);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(607, 160);
        canvas.lineTo(646, 130);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle - Maintenance per Toilet Serviced
        canvas.beginPath();
        canvas.arc(500, 165, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Maintenance per Toilet Serviced Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(552, 202);
        canvas.lineTo(509, 174);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(490, 160);
        canvas.lineTo(445, 130);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle (Maintenance per toilet serviced) line arrow to big pentagon
        canvas.beginPath();
        canvas.moveTo(494, 175);
        canvas.lineTo(452, 220);
        canvas.closePath();
        canvas.stroke();

        canvas.beginPath();
        canvas.moveTo(448, 216);
        canvas.lineTo(458, 224);
        canvas.lineTo(448, 226);
        canvas.setFillStyle("black");
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Top left circle - Combined Efficiency
        canvas.beginPath();
        canvas.arc(470, 80, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Combined Efficiency Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(445, 130);
        canvas.lineTo(465, 91);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(476, 70);
        canvas.lineTo(499, 24);
        canvas.closePath();
        canvas.stroke();
    }

    //Component 3 - Chemical Usage
    private void drawComponent3Items() {
        //Top circle in chemical usage
        canvas.beginPath();
        canvas.arc(660, 272, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top  circle in chemical usage Joining Lines
        //Left
        canvas.beginPath();
        canvas.moveTo(601, 274);
        canvas.lineTo(649, 274);
        canvas.closePath();
        canvas.stroke();

        //Right
        canvas.beginPath();
        canvas.moveTo(672, 274);
        canvas.lineTo(719, 274);
        canvas.closePath();
        canvas.stroke();

        //Top right circle in chemical usage
        canvas.beginPath();
        canvas.arc(735, 332, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top right circle in chemical usage Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(747, 385);
        canvas.lineTo(736, 344);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(731, 322);
        canvas.lineTo(719, 274);
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle in chemical usage
        canvas.beginPath();
        canvas.arc(695, 425, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle in chemical usage Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(649, 462);
        canvas.lineTo(686, 434);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(705, 420);
        canvas.lineTo(747, 385);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle in chemical usage
        canvas.beginPath();
        canvas.arc(600, 425, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle in chemical usage Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(649, 462);
        canvas.lineTo(609, 434);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(590, 420);
        canvas.lineTo(540, 385);
        canvas.closePath();
        canvas.stroke();

        //Top left circle in chemical usage
        canvas.beginPath();
        canvas.arc(567, 332, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top left circle in chemical usage Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(540, 385);
        canvas.lineTo(563, 344);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(575, 322);
        canvas.lineTo(601, 274);
        canvas.closePath();
        canvas.stroke();

        //Top left circle in chemical usage line arrow to big pentagon
        canvas.beginPath();
        canvas.moveTo(554, 332);
        canvas.lineTo(492, 315);
        canvas.closePath();
        canvas.stroke();

        canvas.beginPath();
        canvas.moveTo(494, 308);
        canvas.lineTo(485, 311);
        canvas.lineTo(490, 321);
        canvas.setFillStyle("black");
        canvas.fill();
        canvas.closePath();
        canvas.stroke();
    }

    //Component 4 - Inventory Management
    private void drawComponent4Items() {
        //Top circle in Inventory Management
        canvas.beginPath();
        canvas.arc(402, 437, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top circle in Inventory Management line arrow to big pentagon
        canvas.beginPath();
        canvas.moveTo(402, 425);
        canvas.lineTo(402, 370);
        canvas.closePath();
        canvas.stroke();

        canvas.beginPath();
        canvas.moveTo(395, 370);
        canvas.lineTo(402, 363);
        canvas.lineTo(409, 370);
        canvas.setFillStyle("black");
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Top circle in Inventory Management Joining Lines
        //Left
        canvas.beginPath();
        canvas.moveTo(345, 440);
        canvas.lineTo(390, 440);
        canvas.closePath();
        canvas.stroke();

        //Right
        canvas.beginPath();
        canvas.moveTo(413, 440);
        canvas.lineTo(469, 440);
        canvas.closePath();
        canvas.stroke();

        //Top right circle in Inventory Management
        canvas.beginPath();
        canvas.arc(485, 500, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top right circle in Inventory Management Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(503, 554);
        canvas.lineTo(489, 512);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(483, 489);
        canvas.lineTo(469, 440);
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle in Inventory Management
        canvas.beginPath();
        canvas.arc(445, 593, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle in Inventory Management Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(397, 631);
        canvas.lineTo(438, 603);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(458, 589);
        canvas.lineTo(503, 554);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle in Inventory Management
        canvas.beginPath();
        canvas.arc(350, 595, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle in Inventory Management Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(397, 631);
        canvas.lineTo(358, 604);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(338, 589);
        canvas.lineTo(283, 553);
        canvas.closePath();
        canvas.stroke();

        //Top left circle in Inventory Management
        canvas.beginPath();
        canvas.arc(315, 500, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top left circle in Inventory Management Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(283, 553);
        canvas.lineTo(307, 511);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(317, 490);
        canvas.lineTo(345, 440);
        canvas.closePath();
        canvas.stroke();
    }

    //Component 5 - Procurement
    private void drawComponent5Items() {
        //Top circle in procurement
        canvas.beginPath();
        canvas.arc(155, 272, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top  circle in procurement Joining Lines
        //Left
        canvas.beginPath();
        canvas.moveTo(101, 274);
        canvas.lineTo(142, 274);
        canvas.closePath();
        canvas.stroke();

        //Right
        canvas.beginPath();
        canvas.moveTo(167, 274);
        canvas.lineTo(219, 274);
        canvas.closePath();
        canvas.stroke();

        //Top right circle in procurement
        canvas.beginPath();
        canvas.arc(234, 332, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top right circle in procurement Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(247, 385);
        canvas.lineTo(236, 344);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(231, 322);
        canvas.lineTo(219, 274);
        canvas.closePath();
        canvas.stroke();

        //Top right circle in procurement line arrow to big pentagon
        canvas.beginPath();
        canvas.moveTo(247, 332);
        canvas.lineTo(309, 315);
        canvas.closePath();
        canvas.stroke();

        canvas.beginPath();
        canvas.moveTo(307, 308);
        canvas.lineTo(316, 311);
        canvas.lineTo(312, 321);
        canvas.setFillStyle("black");
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle in procurement
        canvas.beginPath();
        canvas.arc(195, 425, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Bottom right circle in procurement Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(151, 462);
        canvas.lineTo(188, 434);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(205, 420);
        canvas.lineTo(247, 385);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle in procurement
        canvas.beginPath();
        canvas.arc(102, 425, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle in procurement Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(151, 462);
        canvas.lineTo(111, 434);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(90, 420);
        canvas.lineTo(40, 385);
        canvas.closePath();
        canvas.stroke();

        //Top left circle in procurement
        canvas.beginPath();
        canvas.arc(68, 332, 12, 0, 2 * Math.PI, false);
        canvas.closePath();
        canvas.stroke();

        //Top left circle in procurement Joining Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(40, 385);
        canvas.lineTo(63, 344);
        canvas.closePath();
        canvas.stroke();

        //Above
        canvas.beginPath();
        canvas.moveTo(75, 322);
        canvas.lineTo(101, 274);
        canvas.closePath();
        canvas.stroke();
    }

    //Component 1
    private void addTextToComponent1() {
        setTextFont();
        canvas.fillText("Field", 237, 90, 100);
        canvas.fillText("Services", 225, 105, 100);
        canvas.fillText("(Contract)", 222, 120, 100);
        addTextToSmallCirclesComponent1();
    }

    private void addTextToSmallCirclesComponent1() {
        setSmallTextFont();
        canvas.fillText("No. Services Completed", 197, 10, 150);

        canvas.fillText("Completed", 330, 50, 100);
        canvas.fillText("Percentage", 330, 65, 100);

        canvas.fillText("No. Services", 313, 167, 100);
        canvas.fillText("Not Completed", 313, 182, 100);

        canvas.fillText("Uncompleted", 117, 167, 100);
        canvas.fillText("Percentage", 117, 182, 100);

        canvas.fillText("Unit", 115, 50, 100);
        canvas.fillText("Deployment", 115, 65, 100);
    }

    //Component 2
    private void addTextToComponent2() {
        setTextFont();
        canvas.fillText("Field", 537, 90, 100);
        canvas.fillText("Services", 525, 105, 100);
        canvas.fillText("(Private)", 526, 120, 100);
        addTextToSmallCirclesComponent2();
    }

    private void addTextToSmallCirclesComponent2() {
        setSmallTextFont();
        canvas.fillText("No. Services Completed", 497, 10, 150);

        canvas.fillText("Completed", 631, 50, 100);
        canvas.fillText("Percentage", 631, 65, 100);

        canvas.fillText("No. Services", 613, 167, 100);
        canvas.fillText("Not Completed", 613, 182, 100);

        canvas.fillText("Uncompleted", 417, 167, 100);
        canvas.fillText("Percentage", 417, 182, 100);

        canvas.fillText("Private", 415, 50, 100);
        canvas.fillText("Contribution", 415, 65, 100);
    }

    //Component 3
    private void addTextToComponent3() {
        setTextFont();
        canvas.fillText("Fuel", 638, 345, 100);
        canvas.fillText("Management", 614, 360, 100);
        addTextToSmallCirclesComponent3();
    }

    private void addTextToSmallCirclesComponent3() {
        setSmallTextFont();
        canvas.fillText("Fuel Efficiency", 625, 255, 150);

        canvas.fillText("Spend per", 735, 302, 100);
        canvas.fillText("Service", 735, 317, 100);

        canvas.fillText("Spend per", 710, 430, 100);
        canvas.fillText("Unit", 710, 445, 100);

        canvas.fillText("Vehicles Above", 507, 430, 100);
        canvas.fillText("Specifications", 507, 445, 100);

        canvas.fillText("Fuel", 540, 302, 100);
        canvas.fillText("Spend", 540, 317, 100);
    }

    //Component 4
    private void addTextToComponent4() {
        setTextFont();
        canvas.fillText("Maintenance", 363, 517, 100);
        canvas.fillText("Management", 363, 532, 100);
        addTextToSmallCirclesComponent4();
    }

    private void addTextToSmallCirclesComponent4() {
        setSmallTextFont();
        canvas.fillText("Maintenance   Efficiency", 337, 420, 150);

        canvas.fillText("Spend per", 500, 515, 100);
        canvas.fillText("Service", 500, 530, 100);

        canvas.fillText("Spend per", 462, 605, 100);
        canvas.fillText("Unit", 462, 620, 100);

        canvas.fillText("Vehicles Above", 255, 605, 100);
        canvas.fillText("Specifications", 255, 620, 100);

        canvas.fillText("Maintenance", 235, 515, 100);
        canvas.fillText("Spend", 235, 530, 100);
    }

    //Component 5
    private void addTextToComponent5() {
        setTextFont();
        canvas.fillText("Chemical", 123, 345, 100);
        canvas.fillText("Usage", 131, 360, 100);
        addTextToSmallCirclesComponent5();
    }

    private void addTextToSmallCirclesComponent5() {
        setSmallTextFont();
        canvas.fillText("Cost per Service", 115, 255, 150);

        canvas.fillText("Sanitizer per", 235, 302, 100);
        canvas.fillText("Service", 235, 317, 100);

        canvas.fillText("Deodorize per", 210, 430, 100);
        canvas.fillText("Unit", 210, 445, 100);

        canvas.fillText("Spend per", 35, 430, 100);
        canvas.fillText("Unit", 35, 445, 100);

        canvas.fillText("Chemical", 35, 302, 100);
        canvas.fillText("Spend", 35, 317, 100);
    }

    //Small Pentagon
    private void addTextToSmallPentagon() {
        Random random = new Random();
        int value = random.nextInt(5);
        String text = "Z";
        if (value == 1) {
            text = "A";
        } else if (value == 2) {
            text = "B";
        } else if (value == 3) {
            text = "C";
        } else if (value == 4) {
            text = "D";
        } else if (value == 5) {
            text = "E";
        }
        canvas.setFont("normal bold 55px sans-serif");
        canvas.setFillStyle("black");
        canvas.fillText(text, 380, 310, 100);
    }

    private void setTextFont() {
        canvas.setFont("normal bold 12px sans-serif");
        canvas.setFillStyle("black");
    }

    private void setSmallTextFont() {
        canvas.setFont("normal 11px sans-serif");
        canvas.setFillStyle("black");
    }

    private void addColorToItemsInBigPentagon() {
        Random random = new Random();
        int value = random.nextInt(5);
        if (value == 1) {
            topRightRectangleColorBigPentagon = "green";
            topLeftRectangleColorBigPentagon = "red";
            bottomRightRectangleColorBigPentagon = "yellow";
            bottomLeftRectangleColorBigPentagon = "green";
            bottomRectangleColorBigPentagon = "green";
        } else if (value == 2) {
            topRightRectangleColorBigPentagon = "red";
            topLeftRectangleColorBigPentagon = "green";
            bottomRightRectangleColorBigPentagon = "green";
            bottomLeftRectangleColorBigPentagon = "red";
            bottomRectangleColorBigPentagon = "yellow";
        } else if (value == 3) {
            topRightRectangleColorBigPentagon = "yellow";
            topLeftRectangleColorBigPentagon = "red";
            bottomRightRectangleColorBigPentagon = "green";
            bottomLeftRectangleColorBigPentagon = "red";
            bottomRectangleColorBigPentagon = "yellow";
        } else if (value == 4) {
            topRightRectangleColorBigPentagon = "red";
            topLeftRectangleColorBigPentagon = "green";
            bottomRightRectangleColorBigPentagon = "yellow";
            bottomLeftRectangleColorBigPentagon = "yellow";
            bottomRectangleColorBigPentagon = "red";
        } else if (value == 5) {
            topRightRectangleColorBigPentagon = "green";
            topLeftRectangleColorBigPentagon = "yellow";
            bottomRightRectangleColorBigPentagon = "green";
            bottomLeftRectangleColorBigPentagon = "red";
            bottomRectangleColorBigPentagon = "green";
        } else if (value == 0) {
            topRightRectangleColorBigPentagon = "yellow";
            topLeftRectangleColorBigPentagon = "red";
            bottomRightRectangleColorBigPentagon = "green";
            bottomLeftRectangleColorBigPentagon = "yellow";
            bottomRectangleColorBigPentagon = "green";
        }
    }

    private void addColorToItemsComponent() {
        Random random = new Random();
        int value = random.nextInt(5);
        if (value == 1) {
            topCircleColorComponent = "green";
            topRightCircleColorComponent = "red";
            topLeftCircleColorComponent = "green";
            bottomLeftCircleColorComponent = "red";
            bottomRightCircleColorComponent = "green";
        } else if (value == 2) {
            topCircleColorComponent = "red";
            topRightCircleColorComponent = "green";
            topLeftCircleColorComponent = "red";
            bottomLeftCircleColorComponent = "green";
            bottomRightCircleColorComponent = "red";
        } else if (value == 3) {
            topCircleColorComponent = "red";
            topRightCircleColorComponent = "red";
            topLeftCircleColorComponent = "green";
            bottomLeftCircleColorComponent = "green";
            bottomRightCircleColorComponent = "red";
        } else if (value == 4) {
            topCircleColorComponent = "green";
            topRightCircleColorComponent = "green";
            topLeftCircleColorComponent = "green";
            bottomLeftCircleColorComponent = "green";
            bottomRightCircleColorComponent = "green";
        } else if (value == 5) {
            topCircleColorComponent = "red";
            topRightCircleColorComponent = "red";
            topLeftCircleColorComponent = "red";
            bottomLeftCircleColorComponent = "red";
            bottomRightCircleColorComponent = "red";
        } else if (value == 0) {
            topCircleColorComponent = "red";
            topRightCircleColorComponent = "red";
            topLeftCircleColorComponent = "green";
            bottomLeftCircleColorComponent = "red";
            bottomRightCircleColorComponent = "red";
        }
    }

    private void insertAllcanvasComponents() {
        addColorToItemsInBigPentagon();
        drawBigPentagonItems();

        drawSmallPentagonItems();
        addTextToSmallPentagon();

        drawComponentCircles();

        addColorToItemsComponent();
        drawComponent1Items();
        addTextToComponent1();

        drawComponent2Items();
        addTextToComponent2();

        drawComponent3Items();
        addTextToComponent3();

        drawComponent4Items();
        addTextToComponent4();

        drawComponent5Items();
        addTextToComponent5();
    }
}

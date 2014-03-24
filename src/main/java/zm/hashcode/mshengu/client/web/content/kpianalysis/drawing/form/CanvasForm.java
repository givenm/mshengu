/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.vaadin.hezamu.canvas.Canvas;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models.ChemicalUsageService;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models.FieldServicesContractService;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models.FieldServicesPrivateService;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models.FuelManagementService;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models.MaintenanceService;
import zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models.SmallPentagonTextService;
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
    public Canvas canvas = new Canvas();
    private FieldServicesContractService contractService = new FieldServicesContractService();
    private FieldServicesPrivateService privateService = new FieldServicesPrivateService();
    private FuelManagementService managementService = new FuelManagementService();
    private MaintenanceService maintenanceService = new MaintenanceService();
    private ChemicalUsageService chemicalUsageService = new ChemicalUsageService();
    private SmallPentagonTextService textService = new SmallPentagonTextService();

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
        try {
            canvas.setFillStyle(contractService.computeRectangleColorInBigPentagon(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(contractService.computeRectangleColorInBigPentagon("", ""));
        }
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
        try {
            canvas.setFillStyle(privateService.computeRectangleColorInBigPentagon(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(privateService.computeRectangleColorInBigPentagon("", ""));
        }
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
        try {
            canvas.setFillStyle(managementService.computeRectangleColorInBigPentagon(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(managementService.computeRectangleColorInBigPentagon("", ""));
        }
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
        try {
            canvas.setFillStyle(chemicalUsageService.computeRectangleColorInBigPentagon(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(chemicalUsageService.computeRectangleColorInBigPentagon("", ""));
        }
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
        try {
            canvas.setFillStyle(maintenanceService.computeRectangleColorInBigPentagon(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(maintenanceService.computeRectangleColorInBigPentagon("", ""));
        }
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
        //No Services Completed - Line
        canvas.beginPath();
        canvas.moveTo(200, 24);
        canvas.lineTo(310, 24);
        canvas.closePath();
        canvas.stroke();

        //Top circle
        canvas.beginPath();
        canvas.arc(255, 27, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(contractService.computeNoServicesCompleted(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(contractService.computeNoServicesCompleted("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Completed Percentage Line
        canvas.beginPath();
        canvas.moveTo(346, 130);
        canvas.lineTo(310, 24);
        canvas.closePath();
        canvas.stroke();

        //Top right circle 
        canvas.beginPath();
        canvas.arc(330, 80, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(contractService.computeCompletedPercentage(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(contractService.computeCompletedPercentage("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //No. Services Not Completed - Line
        canvas.beginPath();
        canvas.moveTo(252, 202);
        canvas.lineTo(346, 130);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom right circle
        canvas.beginPath();
        canvas.arc(295, 165, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(contractService.computeNoServicesNotCompleted(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(contractService.computeNoServicesNotCompleted("", ""));
        }
        canvas.fill();
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

        //Uncompleted Percentage - Line
        canvas.beginPath();
        canvas.moveTo(145, 130);
        canvas.lineTo(252, 202);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom left circle
        canvas.beginPath();
        canvas.arc(200, 165, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(contractService.computeUncompletedPercentage(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(contractService.computeUncompletedPercentage("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Unit Deployment - Lines
        canvas.beginPath();
        canvas.moveTo(145, 130);
        canvas.lineTo(200, 24);
        canvas.closePath();
        canvas.stroke();

        //Top left circle
        canvas.beginPath();
        canvas.arc(170, 80, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(contractService.computeUnitDeployment(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(contractService.computeUnitDeployment("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();
    }

    //Component 2 - Fleet Management
    private void drawComponent2Items() {
        //No Services Completed - Lines
        canvas.beginPath();
        canvas.moveTo(499, 24);
        canvas.lineTo(610, 24);
        canvas.closePath();
        canvas.stroke();
        
        //Top circle 
        canvas.beginPath();
        canvas.arc(555, 27, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(privateService.computeNoServicesCompleted(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(privateService.computeNoServicesCompleted("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Completed Percentage - Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(646, 130);
        canvas.lineTo(610, 24);
        canvas.closePath();
        canvas.stroke();
        
        //Top right circle
        canvas.beginPath();
        canvas.arc(630, 80, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(privateService.computeCompletedPercentage(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(privateService.computeCompletedPercentage("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        
        //No Services Not Completed - Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(552, 202);
        canvas.lineTo(646, 130);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom right circle
        canvas.beginPath();
        canvas.arc(595, 165, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(privateService.computeNoServicesNotCompleted(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(privateService.computeNoServicesNotCompleted("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Uncompleted Percentage - Lines
        canvas.beginPath();
        canvas.moveTo(552, 202);
        canvas.lineTo(445, 130);
        canvas.closePath();
        canvas.stroke();

        //Bottom left circle - 
        canvas.beginPath();
        canvas.arc(500, 165, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(privateService.computeUncompletedPercentage(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(privateService.computeUncompletedPercentage("", ""));
        }
        canvas.fill();
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

        
        //Private Contribution - Lines
        //Below
        canvas.beginPath();
        canvas.moveTo(445, 130);
        canvas.lineTo(499, 24);
        canvas.closePath();
        canvas.stroke();
        
        //Top left circle
        canvas.beginPath();
        canvas.arc(470, 80, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(privateService.computePrivateContribution(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(privateService.computePrivateContribution("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();
    }

    //Component 3 - Fuel Management
    private void drawComponent3Items() {
        //Fuel Efficiency - Line
        //Left
        canvas.beginPath();
        canvas.moveTo(601, 274);
        canvas.lineTo(719, 274);
        canvas.closePath();
        canvas.stroke();
        
        //Top circle
        canvas.beginPath();
        canvas.arc(660, 272, 12, 0, 2 * Math.PI, false);  
        try {
            canvas.setFillStyle(managementService.computeFuelEfficiency(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(managementService.computeFuelEfficiency("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Spend per service - Line
        canvas.beginPath();
        canvas.moveTo(747, 385);
        canvas.lineTo(719, 274);
        canvas.closePath();
        canvas.stroke();
        
        //Top right circle
        canvas.beginPath();
        canvas.arc(735, 332, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(managementService.computeSpendPerService(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(managementService.computeSpendPerService("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Spend per unit - Line
        canvas.beginPath();
        canvas.moveTo(649, 462);
        canvas.lineTo(747, 385);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom right circle
        canvas.beginPath();
        canvas.arc(695, 425, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(managementService.computeSpendPerUnit(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(managementService.computeSpendPerUnit("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Vehicles aboe specifications - Lines
        canvas.beginPath();
        canvas.moveTo(649, 462);
        canvas.lineTo(540, 385);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom left circle
        canvas.beginPath();
        canvas.arc(600, 425, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(managementService.computeVehiclesAboveSpecifications(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(managementService.computeVehiclesAboveSpecifications("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Fuel Spend - Lines
        canvas.beginPath();
        canvas.moveTo(540, 385);
        canvas.lineTo(601, 274);
        canvas.closePath();
        canvas.stroke();
        
        //Top left circle
        canvas.beginPath();
        canvas.arc(567, 332, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(managementService.computeFuelSpend(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(managementService.computeFuelSpend("", ""));
        }
        canvas.fill();
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

    //Component 4 - Maintenance Management
    private void drawComponent4Items() {
        //Maintenance Efficiency - Lines
        canvas.beginPath();
        canvas.moveTo(345, 440);
        canvas.lineTo(469, 440);
        canvas.closePath();
        canvas.stroke();
        
        //Top circle
        canvas.beginPath();
        canvas.arc(402, 437, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(maintenanceService.computeMaintenanceEfficiency(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(maintenanceService.computeMaintenanceEfficiency("", ""));
        }
        canvas.fill();
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

        
        //Spend per service Lines
        canvas.beginPath();
        canvas.moveTo(503, 554);
        canvas.lineTo(469, 440);
        canvas.closePath();
        canvas.stroke();
        
        //Top right circle
        canvas.beginPath();
        canvas.arc(485, 500, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(maintenanceService.computeSpendPerService(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(maintenanceService.computeSpendPerService("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Spend per unit - Lines
        canvas.beginPath();
        canvas.moveTo(397, 631);
        canvas.lineTo(503, 554);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom right circle
        canvas.beginPath();
        canvas.arc(445, 593, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(maintenanceService.computeSpendPerUnit(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(maintenanceService.computeSpendPerUnit("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();;

        //Vehicles above specifications - Line
        canvas.beginPath();
        canvas.moveTo(397, 631);
        canvas.lineTo(283, 553);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom left circle
        canvas.beginPath();
        canvas.arc(350, 595, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(maintenanceService.computeVehiclesAboveSpecifications(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(maintenanceService.computeVehiclesAboveSpecifications("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        //Maintenance Spend - Line
        canvas.beginPath();
        canvas.moveTo(283, 553);
        canvas.lineTo(345, 440);
        canvas.closePath();
        canvas.stroke();
        
        //Top left circle
        canvas.beginPath();
        canvas.arc(315, 500, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(maintenanceService.computeMaintenanceSpend(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(maintenanceService.computeMaintenanceSpend("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();
    }

    //Component 5 - Chemical Usage
    private void drawComponent5Items() {
        //Cost per service - Lines
        canvas.beginPath();
        canvas.moveTo(101, 274);
        canvas.lineTo(219, 274);
        canvas.closePath();
        canvas.stroke();  
        
        //Top circle
        canvas.beginPath();
        canvas.arc(155, 272, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(chemicalUsageService.computeCostPerService(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(chemicalUsageService.computeCostPerService("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        
        //Sanitizer per service Lines
        canvas.beginPath();
        canvas.moveTo(247, 385);
        canvas.lineTo(219, 274);
        canvas.closePath();
        canvas.stroke();
        
        //Top right circle
        canvas.beginPath();
        canvas.arc(234, 332, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(chemicalUsageService.computeSanitizerPerService(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(chemicalUsageService.computeSanitizerPerService("", ""));
        }
        canvas.fill();
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

        
        //Deodorizer per unit - Lines
        canvas.beginPath();
        canvas.moveTo(151, 462);
        canvas.lineTo(247, 385);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom right circle
        canvas.beginPath();
        canvas.arc(195, 425, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(chemicalUsageService.computeDeodirizePerUnit(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(chemicalUsageService.computeDeodirizePerUnit("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        
        //Spend per unit - Lines
        canvas.beginPath();
        canvas.moveTo(151, 462);
        canvas.lineTo(40, 385);
        canvas.closePath();
        canvas.stroke();
        
        //Bottom left circle
        canvas.beginPath();
        canvas.arc(102, 425, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(chemicalUsageService.computeSpendPerUnit(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(chemicalUsageService.computeSpendPerUnit("", ""));
        }
        canvas.fill();
        canvas.closePath();
        canvas.stroke();

        
        //Chemical spend - Line
        canvas.beginPath();
        canvas.moveTo(40, 385);
        canvas.lineTo(101, 274);
        canvas.closePath();
        canvas.stroke();
        
        //Top left circle
        canvas.beginPath();
        canvas.arc(68, 332, 12, 0, 2 * Math.PI, false);
        try {
            canvas.setFillStyle(chemicalUsageService.computeChemicalSpend(month.getValue().toString(), year.getValue().toString()));
        } catch (NullPointerException exception) {
            canvas.setFillStyle(chemicalUsageService.computeChemicalSpend("", ""));
        }
        canvas.fill();
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
        canvas.setFont("normal bold 55px sans-serif");
        canvas.setFillStyle("black");
        try {
            canvas.fillText(textService.addText(month.getValue().toString(), year.getValue().toString()), 380, 310, 100);
        } catch (NullPointerException exception) {
            canvas.fillText(textService.addText("", ""), 380, 310, 100);
        }
    }

    private void setTextFont() {
        canvas.setFont("normal bold 12px sans-serif");
        canvas.setFillStyle("black");
    }

    private void setSmallTextFont() {
        canvas.setFont("normal 11px sans-serif");
        canvas.setFillStyle("black");
    }

    public void insertAllcanvasComponents() {
        drawBigPentagonItems();

        drawSmallPentagonItems();
        addTextToSmallPentagon();

        drawComponentCircles();

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.models;

import com.vaadin.ui.Notification;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import fr.opensagres.xdocreport.converter.Options;
import java.io.ByteArrayOutputStream;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderProductFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;

/**
 *
 * @author Luckbliss
 */
public class PurchaseControl {

    public ByteArrayOutputStream processFormDataToPDF(Request request) {
        try {
            Purchase purchase = new Purchase();
            purchase.setCostCentre(returnCostCentre(request.getCostCentreType()));
            purchase.setDate(getDate(request.getOrderDate()));
            purchase.setFirstName(request.getPerson().getFirstname());
            purchase.setLastName(request.getPerson().getLastname());
            purchase.setOrderNumber(request.getOrderNumber());
            purchase.setTotal(request.getTotal());
            purchase.setVendor(request.getServiceProviderName());
            purchase.setInstructions(request.getDeliveryInstructions());

            List<PurchaseItem> items = new ArrayList<>();

            for (RequestPurchaseItem item : request.getRequestPurchaseItems()) {
                PurchaseItem purchaseItem = new PurchaseItem();
                if (item.getItemDescription() != null) {
                    purchaseItem.setDescription(item.getItemDescription());
                    purchaseItem.setNumber(item.getItemNumber());
                    purchaseItem.setPrice(item.getUnitPrice().toString());
                    purchaseItem.setQuantity(item.getQuantity());
                    purchaseItem.setUnit(item.getUnit());

                } else {
                    ServiceProviderProduct product = ServiceProviderProductFacade.getServiceProviderProductService().findById(item.getServiceProviderProductId());
                    purchaseItem.setDescription(product.getProductName());
                    purchaseItem.setNumber(product.getItemNumber());
                    DecimalFormat f = new DecimalFormat("### ###.00");
                    purchaseItem.setPrice(f.format(product.getPrice()));
                    purchaseItem.setQuantity(item.getQuantity());
                    purchaseItem.setUnit(product.getUnit());
                }
                items.add(purchaseItem);
            }

            // Open the docx Template
            String fileName = "purchase.docx";
            URL url = this.getClass().getResource("/procurementpdf/");
            System.out.println("getResource() PATH " + url.getPath());
            FileInputStream in = new FileInputStream(url.getFile() + fileName);

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            IContext context = report.createContext();
            context.put("purchase", purchase);
            context.put("items", items);

            // 3) Generate pdf report by merging Java model with the Docx
            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
//            OutputStream out = new FileOutputStream(new File("C:\\Users\\Boniface\\Documents\\" + request.getOrderNumber() + ".pdf"));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            report.convert(context, options, os);

            try {
                // return to be displayed on screen
//                FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Boniface\\Documents\\" + request.getOrderNumber() + ".pdf"));
                return os;

            } catch (Exception e) {
                System.out.println("\n\n" + e.getMessage());
//            throw new XDocConverterException( e );
                Notification.show("Could not create the PDF file!", Notification.Type.TRAY_NOTIFICATION);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getDate(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }

    private String returnCostCentre(CostCentreType costCentre) {
        if (costCentre != null) {
            return costCentre.getName();
        }
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.models;

import com.vaadin.ui.Notification;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

/**
 *
 * @author Luckbliss
 */
public class QuoteControl {

    public ByteArrayOutputStream processFormDataToPDF(RequestForQuote request) {
        try {
            Quote quote = new Quote();
            quote.setRfqNumber(request.getRfqNumber());
            quote.setDate(getDate(request.getDeliveryDate()));
            quote.setFirstName(request.getPerson().getFirstname());
            quote.setLastName(request.getPerson().getLastname());
            quote.setInstructions(request.getDeliveryInstructions());
            quote.setMshengu(request.getAccount());

            List<QuoteItem> items = new ArrayList<>();

            for (RequestPurchaseItem item : request.getItems()) {
                QuoteItem quoteItem = new QuoteItem();
                if (item.getItemDescription() != null) {
                    quoteItem.setDescription(item.getItemDescription());
                    quoteItem.setQuantity(item.getQuantity());
                    if (item.getVolume() != null) {
                        quoteItem.setVolume(item.getVolume());
                    } else {
                        quoteItem.setVolume("");
                    }
                    if (item.getUnit() != null) {
                        quoteItem.setUnit(item.getUnit());
                    } else {
                        quoteItem.setUnit("");
                    }
                }
                items.add(quoteItem);
            }

            // Open the docx Template
            String fileName = "quote.docx";
            URL url = this.getClass().getResource("/procurementpdf/");
            System.out.println("getResource() PATH " + url.getPath());
            FileInputStream in = new FileInputStream(url.getFile() + fileName);

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            IContext context = report.createContext();
            context.put("quote", quote);
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
}

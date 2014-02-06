/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.form;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JOptionPane;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.SendEmailHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.PurchaseMenu;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.models.PurchaseControl;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.ApprovedRequestsTab;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class SendPurchasePDFForm extends FormLayout {

    private PurchaseControl control = new PurchaseControl();
    private ApprovedRequestsTab tab = null;
    private static Embedded embedded = null;
    private static StreamResource streamResource = null;
    private static ByteArrayInputStream byteArrInputStream = null;
    private SendEmailHelper emailHelper = new SendEmailHelper();
    private final MshenguMain main;

    public SendPurchasePDFForm(MshenguMain main, final Request request, final ApprovedRequestsTab tab) {
        this.main = main;
        this.tab = tab;
        streamResource = new StreamResource(createStreamResource(request), "Cost Centre" + "_" + request.getServiceProviderName() + "_Order No." + request.getOrderNumber() + ".pdf");
        embedded = new Embedded();
        embedded.setType(Embedded.TYPE_BROWSER);
        embedded.setHeight("500");
        embedded.setWidth("1000");
        embedded.setMimeType("application/pdf");
        embedded.setSource(streamResource);
//        embedded.setImmediate(true);

        addComponent(embedded);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        Button back = new Button("Back To List");
        back.setSizeFull();
        Button email = new Button("E-mail Purchase Order");
        email.setSizeFull();
        buttons.addComponent(back);
        buttons.addComponent(email);
        addComponent(buttons);

        setSizeFull();

        back.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                tab.clearTab();
            }
        });

        email.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
//                if (!request.isEmailstatus()) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(control.processFormDataToPDF(request).toByteArray());
                try {
                    DataSource source = new ByteArrayDataSource(byteArrayInputStream, "application/pdf");
                    emailHelper.sendToSupplier(source, request.getServiceProviderEmail(), request.getOrderNumber(), "Mshengu Purchase Order");
                    Request newRequest = new Request.Builder(request.getPerson())
                            .request(request)
                            .emailstatus(true)
                            .build();
                    RequestFacade.getRequestService().merge(newRequest);
                    getHome();
                    Notification.show("Email sent to vendor contact person!", Notification.Type.TRAY_NOTIFICATION);
                } catch (IOException ex) {
                    Logger.getLogger(SendPurchasePDFForm.class.getName()).log(Level.SEVERE, null, ex);
                }
//                } else {
//                    getHome();
//                    Notification.show("Email already sent to vendor contact person!", Notification.Type.TRAY_NOTIFICATION);
//                }
            }
        });
    }

    private void getHome() {
        main.content.setSecondComponent(new PurchaseMenu(main, "APPROVED_REQUESTS"));
    }

    private StreamSource createStreamResource(final Request request) {

        StreamSource streamSource = new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
//                return control.processFormDataToPDF(request);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(control.processFormDataToPDF(request).toByteArray());
                byteArrInputStream = byteArrayInputStream;
                return byteArrayInputStream;
//                InputStream fis = new ByteArrayInputStream(os.toByteArray());
            }
        };
        return streamSource;
    }
}

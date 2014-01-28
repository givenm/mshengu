/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JOptionPane;
import zm.hashcode.mshengu.app.util.SendEmailHelper;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.SendPurchasePDFForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.RFQMenu;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.models.QuoteBean;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.models.QuoteControl;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;

/**
 *
 * @author Luckbliss
 */
public class SendQuotePDFForm extends FormLayout {

    public Button back = new Button("Cancel");
    public Button email = new Button("E-mail RFQ");
    private MshenguMain main;
    private static Embedded embedded = null;
    private static StreamResource streamResource = null;
    private static ByteArrayInputStream byteArrInputStream = null;
    private QuoteControl control = new QuoteControl();
    private SendEmailHelper emailHelper = new SendEmailHelper();
    public UIComponentHelper UIComponent = new UIComponentHelper();
    public final QuoteBean bean = new QuoteBean();
    public final BeanItem<QuoteBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    private TextField sendemail = new TextField();

    public SendQuotePDFForm(final RequestForQuote request, final MshenguMain main) {
        sendemail = UIComponent.getTextField("Email Address:", "email", QuoteBean.class, binder);
        this.main = main;
        setSizeFull();
        streamResource = new StreamResource(createStreamResource(request), request.getRfqNumber() + ".pdf");
        embedded = new Embedded();
        embedded.setType(Embedded.TYPE_BROWSER);
        embedded.setHeight("500");
        embedded.setWidth("1000");
        embedded.setMimeType("application/pdf");
        embedded.setSource(streamResource);
//        embedded.setImmediate(true);

        GridLayout layout = new GridLayout(3, 4);
        layout.addComponent(sendemail);
        addComponent(embedded);
        addComponent(new Label("<br>", ContentMode.HTML));
        addComponent(layout);
        addComponent(new Label("<br>", ContentMode.HTML));

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        back.setSizeFull();
        email.setSizeFull();
        buttons.addComponent(back);
        buttons.addComponent(email);

        addComponent(buttons);

        back.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getHome();
            }
        });

        email.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(control.processFormDataToPDF(request).toByteArray());
                try {
                    binder.commit();
                    QuoteBean bean = ((BeanItem<QuoteBean>) binder.getItemDataSource()).getBean();
                    DataSource sendsource = new ByteArrayDataSource(byteArrayInputStream, "application/pdf");
                    emailHelper.sendToSupplier(sendsource, bean.getEmail(), request.getRfqNumber(), "Mshengu Request For Quote");
                    Notification.show("Email sent to" + bean.getEmail(), Notification.Type.TRAY_NOTIFICATION);
                    getHome();
                } catch (IOException ex) {
                    Logger.getLogger(SendPurchasePDFForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FieldGroup.CommitException ex) {
                    Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        });
    }

    private void getHome() {
        main.content.setSecondComponent(new RFQMenu(main, "LIST_RFQ"));
    }

    private StreamResource.StreamSource createStreamResource(final RequestForQuote request) {

        StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                byte byteArray[] = control.processFormDataToPDF(request).toByteArray();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
//                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(control.processFormDataToPDF(request).toByteArray());
                byteArrInputStream = byteArrayInputStream;
                return byteArrayInputStream;
//                InputStream fis = new ByteArrayInputStream(os.toByteArray());
            }
        };
        return streamSource;
    }
}

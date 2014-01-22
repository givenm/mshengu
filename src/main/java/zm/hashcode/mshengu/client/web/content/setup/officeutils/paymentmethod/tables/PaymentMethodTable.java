/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.paymentmethod.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.PaymentMethodFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;

/**
 *
 * @author Ferox
 */
public class PaymentMethodTable extends Table {

    private final MshenguMain main;

    public PaymentMethodTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Payment Method", String.class, null);
        

        // Add Data Columns
        List<PaymentMethod> paymentMethodList = PaymentMethodFacade.getPaymentMethodListService().findAll();
        for (PaymentMethod paymentMethod : paymentMethodList) {
            addItem(new Object[]{paymentMethod.getPaymentMethod(),
                                }, paymentMethod.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }


    }
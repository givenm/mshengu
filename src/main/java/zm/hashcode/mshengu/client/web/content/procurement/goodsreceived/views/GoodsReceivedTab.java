/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.form.GoodsReceivedForm;

/**
 *
 * @author Luckbliss
 */
public class GoodsReceivedTab extends VerticalLayout {

    private GoodsReceivedForm form;
    private MshenguMain main;

    public GoodsReceivedTab(MshenguMain main) {
        setSizeFull();
        form = new GoodsReceivedForm(main);
        this.main = main;
        addComponent(form);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.forms.TargetForm;

/**
 *
 * @author Luckbliss
 */
public class TargetsTab extends VerticalLayout {

    private final MshenguMain main;
    private final TargetForm form;

    public TargetsTab(MshenguMain app) {
        main = app;
        form = new TargetForm(main, this);
        setSizeFull();
        addComponent(form);
    }
}
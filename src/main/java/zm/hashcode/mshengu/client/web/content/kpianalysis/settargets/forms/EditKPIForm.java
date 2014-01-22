/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIItemFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;
import zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.models.TargetBean;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.models.KPIItemBean;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;

/**
 *
 * @author Luckbliss
 */
public class EditKPIForm extends FormLayout {

   public UIComponentHelper UIComponent = new UIComponentHelper();
    public UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    public final TargetBean bean = new TargetBean();
    public final BeanItem<TargetBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public TextField editTargets;
    public ComboBox weighting;
    private MshenguMain main;
    public Button edit = new Button("Edit KPI");
    public Button update = new Button("Update KPI");
    public Button cancel = new Button("Cancel");
    private KPIItem kPIItem;

    public EditKPIForm(MshenguMain main, KPIItem kPIItem) {
        this.kPIItem = kPIItem;
        setSizeFull();
        this.main = main;
        
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        
        editTargets = UIComponent.getTextField("Edit Targets: ", "editTargets", TargetBean.class, binder);
        editTargets.setValue(kPIItem.getEditTargets());
        
        weighting = UIComboBox.getWeightingComboBox("Weighting:", "weighting", TargetBean.class, binder);
        weighting.setValue(kPIItem.getWeighting());
        
        GridLayout layout = new GridLayout(3, 10);
        layout.setSizeFull();

        layout.addComponent(editTargets, 0, 0);        
        layout.addComponent(weighting, 1, 0);

        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 2, 2, 2);

        layout.addComponent(buttons, 0, 3, 2, 3);
        
        addComponent(layout);
        
        setVisibleToFalse();
        setReadOnlyToTrue();

        clickEdit();
        clickUpdate();
        clickCancel();
    }
    
    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        edit.setSizeFull();
        update.setSizeFull();
        cancel.setSizeFull();

        buttons.addComponent(edit);
        buttons.addComponent(update);
        buttons.addComponent(cancel);
        return buttons;
    }
    
    private void setVisibleToFalse() {
        update.setVisible(false);
    }

    private void setVisibleToTrue() {
        update.setVisible(true);
        edit.setVisible(false);
    }

    private void setReadOnlyToTrue() {
        editTargets.setReadOnly(true);
        weighting.setReadOnly(true);
    }

    private void setReadOnlyToFalse() {
        editTargets.setReadOnly(false);
        weighting.setReadOnly(false);
    }
    
    private void saveForm() {
        try {
            binder.commit();
            KPIItemFacade.getKPIItemService().merge(getEntity());
            getHome();
            Notification.show("Record Updated!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private KPIItem getEntity() {
        final TargetBean bean = ((BeanItem<TargetBean>) binder.getItemDataSource()).getBean();
        final KPIItem newkpiitem = new KPIItem.Builder(kPIItem.getShortDescription())
                .kpiitem(kPIItem)
                .editTargets(bean.getEditTargets())
                .weighting(bean.getWeighting())
                .build();
        return newkpiitem;
    }

    private void clickEdit() {
        edit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                setReadOnlyToFalse();
                setVisibleToTrue();
            }
        });
    }

    private void clickUpdate() {
        update.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                saveForm();
            }
        });
    }
    
    private void clickCancel() {
        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getHome();
            }
        });
    }
    
    private void getHome() {
        main.content.setSecondComponent(new KPIMenu(main, "TARGET"));
    }
}

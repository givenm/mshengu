/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.forms;

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
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.models.KPIItemBean;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;

/**
 *
 * @author Luckbliss
 */
public class KPIItemForm extends FormLayout {

    public UIComponentHelper UIComponent = new UIComponentHelper();
    public UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    public final KPIItemBean bean = new KPIItemBean();
    public final BeanItem<KPIItemBean> item = new BeanItem<>(bean);
    public final FieldGroup binder = new FieldGroup(item);
    public TextField shortDescription;
    public TextField detailedDescription;
    public ComboBox uom;
    public ComboBox measureType;
    private MshenguMain main;
    public Button edit = new Button("Edit KPI");
    public Button update = new Button("Update KPI");
    public Button cancel = new Button("Cancel");
    private final KPIItem kPIItem;

    public KPIItemForm(MshenguMain main, KPIItem kPIItem, String kpa) {
        setSizeFull();
        this.main = main;
        this.kPIItem = kPIItem;

        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        
        Label name = new Label();
        name.setValue(kpa);

        shortDescription = UIComponent.getTextField("Short Description: ", "shortDescription", KPIItemBean.class, binder);
        shortDescription.setValue(kPIItem.getShortDescription());

        detailedDescription = UIComponent.getTextField("Detailed Description: ", "detailedDescription", KPIItemBean.class, binder);
        detailedDescription.setValue(kPIItem.getDetailedDescription());

        uom = UIComboBox.getUOMComboBox("UOM:", "uom", KPIItemBean.class, binder);
        uom.setValue(kPIItem.getUom());

        measureType = UIComboBox.getMeaureTypeomboBox("Measure Type: ", "measureTYpe", KPIItemBean.class, binder);
        measureType.setValue(kPIItem.getMeasureType());

        Label kpinumber = new Label();
        kpinumber.setValue("Editing KPI " + kPIItem.getKpiNumber());
        
        GridLayout layout = new GridLayout(3, 10);
        layout.setSizeFull();

        layout.addComponent(name, 0, 0);
        
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1, 2, 1);
        
        layout.addComponent(shortDescription, 0, 2);
        layout.addComponent(detailedDescription, 1, 2);

        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3, 2, 3);

        layout.addComponent(uom, 0, 4);
        layout.addComponent(measureType, 1, 4);

        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 5, 2, 5);


        layout.addComponent(buttons, 0, 6, 2, 6);

        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 7, 2, 7);

        layout.addComponent(kpinumber, 0, 8, 2, 8);
        
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 9, 2, 9);
        
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
        shortDescription.setReadOnly(true);
        detailedDescription.setReadOnly(true);
        uom.setReadOnly(true);
        measureType.setReadOnly(true);
    }

    private void setReadOnlyToFalse() {
        shortDescription.setReadOnly(false);
        detailedDescription.setReadOnly(false);
        uom.setReadOnly(false);
        measureType.setReadOnly(false);
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
        final KPIItemBean bean = ((BeanItem<KPIItemBean>) binder.getItemDataSource()).getBean();
        final KPIItem newkpiitem = new KPIItem.Builder(bean.getShortDescription())
                .kpiitem(kPIItem)
                .detailedDescription(bean.getDetailedDescription())
                .measureType(bean.getMeasureTYpe())
                .uom(bean.getUom())
                .build();
        return newkpiitem;
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
        main.content.setSecondComponent(new KPIMenu(main, "SETUP"));
    }
}

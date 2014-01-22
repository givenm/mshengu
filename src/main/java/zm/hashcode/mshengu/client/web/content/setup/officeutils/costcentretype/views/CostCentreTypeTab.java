/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.views;

/**
 *
 * @author Luckbliss
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.customer.ContractTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.contracttype.forms.ContractTypeForm;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.contracttype.models.ContractTypeBean;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.contracttype.tables.ContractTypeTable;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.form.CostCentreTypeForm;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.tables.CostCentreTypeTable;
import zm.hashcode.mshengu.domain.customer.ContractType;

/**
 *
 * @author Ferox
 */
public class CostCentreTypeTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final CostCentreTypeForm form;
    private final CostCentreTypeTable table;

    public CostCentreTypeTab(MshenguMain app) {
        main = app;
        form = new CostCentreTypeForm();
        table = new CostCentreTypeTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final ContractType contractType = ContractTypeFacade.getContractTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(contractType)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ContractTypeFacade.getContractTypeService().persist(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            ContractTypeFacade.getContractTypeService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        ContractTypeFacade.getContractTypeService().delete(getEntity(binder));
        getHome();
    }

    private ContractType getEntity(FieldGroup binder) {
        //final  ContractType cust = new ContractType.Builder(binder.getItemDataSource().getItemProperty("name")).

        final ContractTypeBean contractTypeBean = ((BeanItem<ContractTypeBean>) binder.getItemDataSource()).getBean();


        final ContractType contractType = new ContractType.Builder(contractTypeBean.getType())
                .id(contractTypeBean.getId())
                .build();

        return contractType;


    }

    private void getHome() {
        main.content.setSecondComponent(new OfficeUtilsMenu(main, "LANDING"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private ContractTypeBean getBean(ContractType contractType) {
        ContractTypeBean bean = new ContractTypeBean();
        bean.setType(contractType.getType());
        bean.setId(contractType.getId());
        return bean;
    }
}

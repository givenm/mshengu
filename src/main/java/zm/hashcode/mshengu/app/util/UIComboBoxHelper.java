/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util;

import com.google.common.collect.Collections2;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.ComboBox;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Collection;
import java.util.List;
import zm.hashcode.mshengu.app.facade.customer.ContractTypeFacade;
import zm.hashcode.mshengu.app.facade.customer.CustomerFacade;
import zm.hashcode.mshengu.app.facade.customer.ServiceRequestTypeFacade;
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckCategoryFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.incident.IncidentTypeFacade;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.facade.products.UnitTypeFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceCategoryFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderCategoryFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderProductCategoryFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderProductFacade;
import zm.hashcode.mshengu.app.facade.ui.location.LocationFacade;
import zm.hashcode.mshengu.app.facade.ui.location.LocationTypeFacade;
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreTypeFacade;
import zm.hashcode.mshengu.app.facade.ui.util.CountryFacade;
import zm.hashcode.mshengu.app.facade.ui.util.JobPositionFacade;
import zm.hashcode.mshengu.app.facade.ui.util.PaymentMethodFacade;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceTypeFacade;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.app.facade.ui.util.StatusTypeFacade;
import zm.hashcode.mshengu.app.facade.ui.util.TerminateFacade;
import zm.hashcode.mshengu.app.util.predicates.sequence.NotificationSequencePredicate;
import zm.hashcode.mshengu.app.util.predicates.sequence.ToiletSequencePredicate;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.models.RequestBean;
import zm.hashcode.mshengu.domain.customer.ContractType;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.customer.ServiceRequestType;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;
import zm.hashcode.mshengu.domain.incident.IncidentType;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProductCategory;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.domain.ui.location.LocationType;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;
import zm.hashcode.mshengu.domain.ui.util.Country;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;
import zm.hashcode.mshengu.domain.ui.util.Sequence;
import zm.hashcode.mshengu.domain.ui.util.SequenceType;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.domain.ui.util.StatusType;
import zm.hashcode.mshengu.domain.ui.util.Terminate;

/**
 *
 * @author Ferox
 */
public class UIComboBoxHelper<T> implements Serializable {

    public ComboBox getUploadComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getEmptyComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getCustomerComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Customer> customers = CustomerFacade.getCustomerService().findAll();
//
        //      Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Customer customer : customers) {
            comboBox.addItem(customer.getId());
            comboBox.setItemCaption(customer.getId(), customer.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getLocationComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Location> locations = LocationFacade.getLocationService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Location location : locations) {
            comboBox.addItem(location.getId());
            comboBox.setItemCaption(location.getId(), location.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getContractTypeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ContractType> contractTypes = ContractTypeFacade.getContractTypeService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ContractType contractType : contractTypes) {
            comboBox.addItem(contractType.getId());
            comboBox.setItemCaption(contractType.getId(), contractType.getType());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getDriversComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Person> drivers = PersonFacade.getPersonService().findAllDrivers();

//        personList = (List<Person>) repository.findAll();
//        Collection<Person> personsFilteredList = Collections2.filter(personList, new PersonDriverPredicate());

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Person driver : drivers) {
            comboBox.addItem(driver.getId());
            comboBox.setItemCaption(driver.getId(), driver.getFirstname() + " " + driver.getLastname());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getVehicleDriversComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Person> vehicleDriversList = PersonFacade.getPersonService().findAllDrivingCompanyCars();

//        personList = (List<Person>) repository.findAll();
//        Collection<Person> personsFilteredList = Collections2.filter(personList, new PersonDriverPredicate());

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Person vehicleDriver : vehicleDriversList) {
            comboBox.addItem(vehicleDriver.getId());
            comboBox.setItemCaption(vehicleDriver.getId(), vehicleDriver.getFirstname() + " " + vehicleDriver.getLastname());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getStaffComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Person> staff = PersonFacade.getPersonService().findAllStaff();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Person driver : staff) {
            comboBox.addItem(driver.getId());
            comboBox.setItemCaption(driver.getId(), driver.getFirstname() + " " + driver.getLastname());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getSuppliersComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProvider> serviceProviderList = ServiceProviderFacade.getServiceProviderService().findAllSuppliers();
        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceProvider serviceProvider : serviceProviderList) {
            comboBox.addItem(serviceProvider.getId());
            comboBox.setItemCaption(serviceProvider.getId(), serviceProvider.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    /**
     * returns SUPPLIERS, SUB CONTRUCTORS, SERVICE PROVIDEFRS
     *
     * @param fieldText
     * @param fieldName
     * @param fieldClass
     * @param binder
     * @return
     */
    public ComboBox getVendorsComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProvider> serviceProviderList = ServiceProviderFacade.getServiceProviderService().findAll();
        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceProvider serviceProvider : serviceProviderList) {
            comboBox.addItem(serviceProvider.getId());
            comboBox.setItemCaption(serviceProvider.getId(), serviceProvider.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getProcurementVendorsComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProvider> serviceProviderList = ServiceProviderFacade.getServiceProviderService().findAll();
        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        comboBox.addItem("all");
        comboBox.setItemCaption("all", "All");
        for (ServiceProvider serviceProvider : serviceProviderList) {
            comboBox.addItem(serviceProvider.getId());
            comboBox.setItemCaption(serviceProvider.getId(), serviceProvider.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getPreferedVendorsComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProvider> serviceProviderList = ServiceProviderFacade.getServiceProviderService().findAllPreferedVendors();
        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceProvider serviceProvider : serviceProviderList) {
            comboBox.addItem(serviceProvider.getId());
            comboBox.setItemCaption(serviceProvider.getId(), serviceProvider.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getServiceProviderComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProvider> serviceProviderList = ServiceProviderFacade.getServiceProviderService().findAllServiceProvider();
        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceProvider serviceProvider : serviceProviderList) {
            comboBox.addItem(serviceProvider.getId());
            comboBox.setItemCaption(serviceProvider.getId(), serviceProvider.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getSubcontractorsComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProvider> serviceProviderList = ServiceProviderFacade.getServiceProviderService().findAllSubcontractors();
        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceProvider serviceProvider : serviceProviderList) {
            comboBox.addItem(serviceProvider.getId());
            comboBox.setItemCaption(serviceProvider.getId(), serviceProvider.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getServiceCategoryComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceCategory> serviceCategoryList = ServiceCategoryFacade.getServiceCategoryService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceCategory serviceCategory : serviceCategoryList) {
            comboBox.addItem(serviceCategory.getId());
            comboBox.setItemCaption(serviceCategory.getId(), serviceCategory.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getServiceProviderCategoryComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProviderCategory> serviceProviderCategoryList = ServiceProviderCategoryFacade.getServiceProviderCategoryService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceProviderCategory serviceProviderCategory : serviceProviderCategoryList) {
            comboBox.addItem(serviceProviderCategory.getId());
            comboBox.setItemCaption(serviceProviderCategory.getId(), serviceProviderCategory.getCategoryName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getLegalFormComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addItem("Closed Corporation");
        comboBox.setItemCaption("Closed Corporation", "Closed Corporation");
        comboBox.addItem("Partnership");
        comboBox.setItemCaption("Partnership", "Partnership");
        comboBox.addItem("Private Company");
        comboBox.setItemCaption("Private Company", "Private Company");
        comboBox.addItem("Public Company");
        comboBox.setItemCaption("Public Company", "Public Company");
        comboBox.addItem("Sole Proprietorship");
        comboBox.setItemCaption("Sole Proprietorship", "Sole Proprietorship");
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getServiceProviderProductCategoryComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProviderProductCategory> ServiceProviderProductCategoryList = ServiceProviderProductCategoryFacade.getServiceProviderProductCategoryService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceProviderProductCategory ServiceProviderProductCategory : ServiceProviderProductCategoryList) {
            comboBox.addItem(ServiceProviderProductCategory.getId());
            comboBox.setItemCaption(ServiceProviderProductCategory.getId(), ServiceProviderProductCategory.getCategoryName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getServiceProviderProductComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProviderProduct> serviceProviderProductList = ServiceProviderProductFacade.getServiceProviderProductService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (ServiceProviderProduct serviceProviderProduct : serviceProviderProductList) {
            comboBox.addItem(serviceProviderProduct.getId());
            comboBox.setItemCaption(serviceProviderProduct.getId(), serviceProviderProduct.getProductName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getTruckCategoryComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<TruckCategory> truckCategoryList = TruckCategoryFacade.getTruckCategoryService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (TruckCategory truckCategory : truckCategoryList) {
            comboBox.addItem(truckCategory.getId());
            comboBox.setItemCaption(truckCategory.getId(), truckCategory.getCategoryName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getCostCentreComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Truck> truckList = TruckFacade.getTruckService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Truck truck : truckList) {
            String truckName = truck.getVehicleNumber() + " (" + truck.getNumberPlate() + ")";
            comboBox.addItem(truck.getId());
            comboBox.setItemCaption(truck.getId(), truckName);
        }
        comboBox.addItem("Chemicals");
        comboBox.setItemCaption(null, "Chemicals");
        comboBox.addItem("Other");
        comboBox.setItemCaption(null, "Other");
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getPaymentMethodComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<PaymentMethod> paymentMethodList = PaymentMethodFacade.getPaymentMethodListService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (PaymentMethod paymentMethod : paymentMethodList) {
            comboBox.addItem(paymentMethod.getId());
            comboBox.setItemCaption(paymentMethod.getId(), paymentMethod.getPaymentMethod());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getLocationTypeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<LocationType> locationTypeList = LocationTypeFacade.getLocationTypeService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (LocationType locationType : locationTypeList) {
            comboBox.addItem(locationType.getId());
            comboBox.setItemCaption(locationType.getId(), locationType.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getCountryLocationComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Location> locationList = LocationFacade.getLocationService().findAllCountries();

        for (Location location : locationList) {
            comboBox.addItem(location.getId());
            comboBox.setItemCaption(location.getId(), location.getName());
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getProvinceLocationComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Location> locationList = LocationFacade.getLocationService().findAllProvinces();

        for (Location location : locationList) {
            comboBox.addItem(location.getId());
            comboBox.setItemCaption(location.getId(), location.getName());
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getCityLocationComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Location> locationList = LocationFacade.getLocationService().findAllCities();

        for (Location location : locationList) {
            comboBox.addItem(location.getId());
            comboBox.setItemCaption(location.getId(), location.getName());
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getRegionsLocationComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Location> locationList = LocationFacade.getLocationService().findAllRegions();

        for (Location location : locationList) {
            comboBox.addItem(location.getId());
            comboBox.setItemCaption(location.getId(), location.getName());
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getSuburbsLocationComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Location> locationList = LocationFacade.getLocationService().findAllSuburbs();

        for (Location location : locationList) {
            comboBox.addItem(location.getId());
            comboBox.setItemCaption(location.getId(), location.getName());
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getUnitTypeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<UnitType> unitTypeList = UnitTypeFacade.getUnitTypeService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (UnitType unitType : unitTypeList) {
            comboBox.addItem(unitType.getId());
            comboBox.setItemCaption(unitType.getId(), unitType.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getUnitNameCodeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Sequence> sequenceList = SequenceFacade.getSequenceListService().findAll();

        Collection<Sequence> sequenceFiltered = Collections2.filter(sequenceList, new ToiletSequencePredicate());
        for (Sequence sequence : sequenceFiltered) {
            comboBox.addItem(sequence.getId());
            comboBox.setItemCaption(sequence.getId(), sequence.getNamingCode());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getMailNotificationComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<MailNotifications> notificationsList = MailNotificationsFacade.getMailNotificationsService().findAll();

        for (MailNotifications mailNotifications : notificationsList) {
            comboBox.addItem(mailNotifications.getId());
            comboBox.setItemCaption(mailNotifications.getId(), mailNotifications.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getNotificationNameCodeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Sequence> sequenceList = SequenceFacade.getSequenceListService().findAll();

        Collection<Sequence> sequenceFiltered = Collections2.filter(sequenceList, new NotificationSequencePredicate());
        for (Sequence sequence : sequenceFiltered) {
            comboBox.addItem(sequence.getId());
            comboBox.setItemCaption(sequence.getId(), sequence.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getSequenceTypeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<SequenceType> sequenceTypeList = SequenceTypeFacade.getSequenceTypeListService().findAll();

//          Collection<Sequence> sequenceFiltered = Collections2.filter(sequenceTypeList, new ToiletSequencePredicate());
        for (SequenceType sequenceType : sequenceTypeList) {
            comboBox.addItem(sequenceType.getId());
            comboBox.setItemCaption(sequenceType.getId(), sequenceType.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getStatusTypeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<StatusType> statusTypeList = StatusTypeFacade.getStatusTypeService().findAll();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (StatusType statusType : statusTypeList) {
            comboBox.addItem(statusType.getId());
            comboBox.setItemCaption(statusType.getId(), statusType.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getUnitOperationalStatusComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Status> statusList = StatusFacade.getStatusService().findUnitOperationalStatus();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (Status status : statusList) {
            comboBox.addItem(status.getId());
            comboBox.setItemCaption(status.getId(), status.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getEmploymentStatusomboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Status> statusList = StatusFacade.getStatusService().findEmploymentStatus();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (Status status : statusList) {
            comboBox.addItem(status.getId());
            comboBox.setItemCaption(status.getId(), status.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getCostCentreType(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<CostCentreType> list = CostCentreTypeFacade.getCostCentreTypeService().findAll();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        if (list != null) {
            for (CostCentreType type : list) {
                comboBox.addItem(type.getId());
                comboBox.setItemCaption(type.getId(), type.getName());
            }
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getAllCostCentreType(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<CostCentreType> list = CostCentreTypeFacade.getCostCentreTypeService().findAll();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        if (list != null) {
            comboBox.addItem("all");
            comboBox.setItemCaption("all", "All");
            for (CostCentreType type : list) {
                comboBox.addItem(type.getId());
                comboBox.setItemCaption(type.getId(), type.getName());
            }
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getCostCentreCategoryType(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getTerminateReasonsAndCodes(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Terminate> list = TerminateFacade.getTerminateService().findAll();
        for (Terminate terminate : list) {
            comboBox.addItem(terminate.getId());
            comboBox.setItemCaption(terminate.getId(), terminate.getReason());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getIncidentStatusComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Status> statusList = StatusFacade.getStatusService().findIncidentStatus();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (Status status : statusList) {
            comboBox.addItem(status.getId());
            comboBox.setItemCaption(status.getId(), status.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getServiceRequesttatusComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Status> statusList = StatusFacade.getStatusService().findServiceRequestStatus();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (Status status : statusList) {
            comboBox.addItem(status.getId());
            comboBox.setItemCaption(status.getId(), status.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getContactUSStatusComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Status> statusList = StatusFacade.getStatusService().findContactUSStatus();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (Status status : statusList) {
            comboBox.addItem(status.getId());
            comboBox.setItemCaption(status.getId(), status.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getIncidentTypeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<IncidentType> incidentTypeList = IncidentTypeFacade.getIncidentTypeService().findAll();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (IncidentType incidentType : incidentTypeList) {
            comboBox.addItem(incidentType.getId());
            comboBox.setItemCaption(incidentType.getId(), incidentType.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getServiceRequestTypeComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceRequestType> serviceRequestTypeList = ServiceRequestTypeFacade.getServiceRequestTypeService().findAll();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (ServiceRequestType serviceRequestType : serviceRequestTypeList) {
            comboBox.addItem(serviceRequestType.getId());
            comboBox.setItemCaption(serviceRequestType.getId(), serviceRequestType.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getActivationStatusomboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Status> statusList = StatusFacade.getStatusService().findActivationStatus();

//          Collection<Status> statusFiltered = Collections2.filter(statusTypeList, new ToiletStatusPredicate());
        for (Status status : statusList) {
            comboBox.addItem(status.getId());
            comboBox.setItemCaption(status.getId(), status.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getMeaureTypeomboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);

        comboBox.addItem("A");
        comboBox.setItemCaption("A", "(A) Above Target Favorable");
        comboBox.addItem("B");
        comboBox.setItemCaption("B", "(B) Below Target Favorable");
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getJobPositionComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<JobPosition> JobPositionList = JobPositionFacade.getJobPositionService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (JobPosition JobPosition : JobPositionList) {
            comboBox.addItem(JobPosition.getId());
            comboBox.setItemCaption(JobPosition.getId(), JobPosition.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getCountryComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Country> CountryList = CountryFacade.getCountryService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Country Country : CountryList) {
            comboBox.addItem(Country.getId());
            comboBox.setItemCaption(Country.getId(), Country.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getNationalityComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Country> CountryList = CountryFacade.getCountryService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Country Country : CountryList) {
            comboBox.addItem(Country.getId());
            comboBox.setItemCaption(Country.getId(), Country.getNationality());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getVehicleComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Truck> truckList = TruckFacade.getTruckService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Truck truck : truckList) {
            String truckName = truck.getVehicleNumber() + " - (" + truck.getNumberPlate() + ")";
            comboBox.addItem(truck.getId());
            comboBox.setItemCaption(truck.getId(), truckName);
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getServiceAndUtilityVehicles(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Truck> truckList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Truck truck : truckList) {
            String truckName = truck.getVehicleNumber() + " - (" + truck.getNumberPlate() + ")";
            comboBox.addItem(truck.getId());
            comboBox.setItemCaption(truck.getId(), truckName);
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getVehicleNumberComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Truck> truckList = TruckFacade.getTruckService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Truck truck : truckList) {
            String truckName = truck.getBrand() + " " + truck.getModel() + " (" + truck.getNumberPlate() + ")";
            comboBox.addItem(truck.getId());
            comboBox.setItemCaption(truck.getId(), truckName);
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getDocumentComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addItem("Health");
        comboBox.addItem("Safety");
        comboBox.addItem("Environment");
        comboBox.addItem("Quality");
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getAllDocumentComboBox(String fieldText, String fieldName, Class<T> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addItem("Health");
        comboBox.addItem("Safety");
        comboBox.addItem("Environment");
        comboBox.addItem("Quality");
        comboBox.addItem("All");
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    /* private ComboBox getEspecialityFeild(String label, String field) {
     ComboBox comboBox = new ComboBox(label);
     //        List<Degree> degreeList = EducationFacade.getDegreeModelService().findAll();
     ////        Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
     //        for (Degree degree : degreeList) {
     //            comboBox.addItem(degree.getId());
     //            comboBox.setItemCaption(degree.getId(), degree.getDegreeName());
     //        }
     //        comboBox.addValidator(new BeanValidator(JobAdvertSearchBean.class, field));
     //        comboBox.setImmediate(true);
     //        comboBox.setWidth(250, Unit.PIXELS);
     binder.bind(comboBox, field);
     return comboBox;
     }

     private ComboBox getCityCombox(String label, String field) {
     ComboBox comboBox = new ComboBox(label);
     List<Location> locations = LocationFacade.getLocationModelService().findAll();

     Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
     for (Location location : cities) {
     comboBox.addItem(location.getId());
     comboBox.setItemCaption(location.getId(), location.getName());
     }
     comboBox.addValidator(new BeanValidator(JobAdvertSearchBean.class, field));
     comboBox.setImmediate(true);
     comboBox.setWidth(250, Sizeable.Unit.PIXELS);
     binder.bind(comboBox, field);
     return comboBox;
     }

     private ComboBox getPositionTypeComboBox(String label, String field) {
     ComboBox comboBox = new ComboBox(label);
     List<PositionType> positionTypes = PositionFacade.getPositionTypeModelService().findAll();
     for (PositionType positionType : positionTypes) {
     comboBox.addItem(positionType.getId());
     comboBox.setItemCaption(positionType.getId(), positionType.getName());
     }
     comboBox.addValidator(new BeanValidator(JobAdvertSearchBean.class, field));
     comboBox.setImmediate(true);
     comboBox.setWidth(250, Sizeable.Unit.PIXELS);
     binder.bind(comboBox, field);
     return comboBox;
     }



     private ListSelect getListSelect(String label, String field) {
     //        ComboBox comboBox = new ComboBox(label);
     final ListSelect select = new ListSelect(label);
     List<RolesList> roleList = DemographicsFacade.getRolesListModelService().findAll();
     for (RolesList role : roleList) {
     select.addItem(role.getId());
     select.setItemCaption(role.getId(), role.getRolename());
     }

     select.setMultiSelect(true);
     select.setNullSelectionAllowed(false);
     // select.addValidator(new BeanValidator(UserBean.class, field));
     select.setImmediate(true);
     select.setWidth(250, Unit.PIXELS);
     binder.bind(select, field);
     return select;
     }*/
    public ComboBox getRequestingPersonComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Person> persons = PersonFacade.getPersonService().findAllRequestors();
        for (Person p : persons) {
            comboBox.addItem(p.getId());
            comboBox.setItemCaption(p.getId(), p.getFirstname() + " " + p.getLastname());

        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getUOMComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addItem("#");
        comboBox.setItemCaption("#", "#");
        comboBox.addItem("%");
        comboBox.setItemCaption("%", "%");
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getWeightingComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addItem("H");
        comboBox.setItemCaption("H", "High");
        comboBox.addItem("M");
        comboBox.setItemCaption("M", "Medium");
        comboBox.addItem("L");
        comboBox.setItemCaption("L", "Low");
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getVendorComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<ServiceProvider> serviceProviders = ServiceProviderFacade.getServiceProviderService().findAll();

        for (ServiceProvider serviceProvider : serviceProviders) {
            comboBox.addItem(serviceProvider.getId());
            comboBox.setItemCaption(serviceProvider.getId(), serviceProvider.getName());
        }
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getProductDescriptionComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getMonthComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);


        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            comboBox.addItem(months[i]);
            comboBox.setItemCaption(months[i], months[i]);
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getAllMonthComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);


        String[] months = new DateFormatSymbols().getMonths();
        comboBox.addItem("all");
        comboBox.setItemCaption("all", "All");
        for (int i = 0; i < months.length; i++) {
            comboBox.addItem(months[i]);
            comboBox.setItemCaption(months[i], months[i]);
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getYearComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);

        int thisYear = 2020;
        int startYear = thisYear - 6;
        for (int i = 0; i < 11; i++) {
            String value = (startYear + i) + "";
            comboBox.addItem(value);
            comboBox.setItemCaption(value, value);
        }

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }

    public ComboBox getEventTypeComboBox(String fieldText, String fieldName, Class<RequestBean> fieldClass, FieldGroup binder) {
        ComboBox comboBox = new ComboBox(fieldText);
        comboBox.addItem("Contract");
        comboBox.setItemCaption("Contract", "Contract");
        comboBox.addItem("Construction");
        comboBox.setItemCaption("Construction", "Construction");
        comboBox.addItem("Special Event");
        comboBox.setItemCaption("Special Event", "Special Event");
        comboBox.addItem("Private");
        comboBox.setItemCaption("Private", "Private");

        comboBox.addValidator(new BeanValidator(fieldClass, fieldName));
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        binder.bind(comboBox, fieldName);
        return comboBox;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.procurement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;
import zm.hashcode.mshengu.domain.ui.util.ItemCategoryType;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Luckbliss
 */
@Document
public final class Request implements Serializable, Comparable<Request> {

    @Id
    private String id;
    @DBRef
    private Person person;
    private String orderNumber = null;
    @DBRef
    private CostCentreType costCentreType;
    @DBRef
    private CostCentreCategoryType categoryType;
    @DBRef
    private Truck truck;
    @DBRef
    private ItemCategoryType itemCategoryType;
    @DBRef
    private ServiceProvider serviceProvider;
    @DBRef
    private Set<RequestPurchaseItem> items;
    private boolean approvalStatus;
    private String reasonForDisapproval;
    private String deliveryInstructions;
    private Date deliveryDate;
    private BigDecimal total;
    private String matchStatus;
    private String invoiceNumber;

    private Request() {
    }

    private Request(Builder builder) {
        this.id = builder.id;
        this.person = builder.person;
        this.serviceProvider = builder.serviceProvider;
        this.items = builder.items;
        this.approvalStatus = builder.approvalStatus;
        this.matchStatus = builder.matchStatus;
        this.reasonForDisapproval = builder.reasonForDisapproval;
        this.deliveryInstructions = builder.deliveryInstructions;
        this.deliveryDate = builder.deliveryDate;
        this.total = builder.total;
        this.invoiceNumber = builder.invoiceNumber;
        this.costCentreType = builder.costCentreType;
        this.categoryType = builder.categoryType;
        this.itemCategoryType = builder.itemCategoryType;
        this.truck = builder.truck;
        this.orderNumber = builder.orderNumber;
    }

    @Override
    public int compareTo(Request o) {
        return getOrderNumber().compareToIgnoreCase(o.getOrderNumber());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Request other = (Request) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final Person person;
        private ServiceProvider serviceProvider;
        private Set<RequestPurchaseItem> items;
        private boolean approvalStatus;
        private String reasonForDisapproval;
        private String deliveryInstructions;
        private Date deliveryDate = null;
        private BigDecimal total;
        private String matchStatus;
        private String invoiceNumber = null;
        private CostCentreType costCentreType;
        private CostCentreCategoryType categoryType;
        private ItemCategoryType itemCategoryType;
        private Truck truck;
        private String orderNumber;

        public Builder(Person value) {
            this.person = value;
        }

        public Builder request(Request request) {
            this.id = request.getId();
            this.serviceProvider = request.getServiceProvider();
            this.items = request.getRequestPurchaseItems();
            this.orderNumber = request.getOrderNumber();
            this.approvalStatus = request.isApprovalStatus();
            this.matchStatus = request.getStatus();
            this.reasonForDisapproval = request.getReasonForDisapproval();
            this.deliveryInstructions = request.getDeliveryInstructions();
            this.deliveryDate = request.getDeliveryDate();
            this.total = request.getTotal();
            this.invoiceNumber = request.getInvoiceNumber();
            this.costCentreType = request.getCostCentreType();
            this.categoryType = request.getCategoryType();
            this.itemCategoryType = request.getItemCategoryType();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder truck(Truck value) {
            this.truck = value;
            return this;
        }

        public Builder costCentreType(CostCentreType value) {
            this.costCentreType = value;
            return this;
        }

        public Builder categoryType(CostCentreCategoryType value) {
            this.categoryType = value;
            return this;
        }

        public Builder itemCategoryType(ItemCategoryType value) {
            this.itemCategoryType = value;
            return this;
        }

        public Builder invoiceNumber(String value) {
            this.invoiceNumber = value;
            return this;
        }

        public Builder total(BigDecimal value) {
            this.total = value;
            return this;
        }

        public Builder deliveryInstructions(String value) {
            this.deliveryInstructions = value;
            return this;
        }

        public Builder deliveryDate(Date value) {
            this.deliveryDate = value;
            return this;
        }

        public Builder reasonForDisapproval(String value) {
            this.reasonForDisapproval = value;
            return this;
        }

        public Builder approvalStatus(boolean value) {
            this.approvalStatus = value;
            return this;
        }

        public Builder matchStatus(String value) {
            this.matchStatus = value;
            return this;
        }

        public Builder items(Set<RequestPurchaseItem> value) {
            this.items = value;
            return this;
        }

        public Builder serviceProvider(ServiceProvider value) {
            this.serviceProvider = value;
            return this;
        }

        public Builder orderNumber(String value) {
            this.orderNumber = value;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

    public CostCentreType getCostCentreType() {
        return costCentreType;
    }

    public CostCentreCategoryType getCategoryType() {
        return categoryType;
    }

    public ItemCategoryType getItemCategoryType() {
        return itemCategoryType;
    }

    public String getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Truck getTruck() {
        return truck;
    }

    public Person getPerson() {
        return person;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public Set<RequestPurchaseItem> getRequestPurchaseItems() {
        return items;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public String getReasonForDisapproval() {
        return reasonForDisapproval;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getStatus() {
        return matchStatus;
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getPersonId() {
        if (!isNullObject(person)) {
            return person.getId();
        } else {
            return null;
        }
    }

    public String getPersonName() {
        if (!isNullObject(person)) {
            return person.getFirstname() + " " + person.getLastname();
        } else {
            return null;
        }
    }

    public String getServiceProviderId() {
        if (!isNullObject(serviceProvider)) {
            return serviceProvider.getId();
        } else {
            return null;
        }
    }

    public String getServiceProviderName() {
        if (!isNullObject(serviceProvider)) {
            return serviceProvider.getName();
        } else {
            return null;
        }
    }

    public String getServiceProviderEmail() {
        if (!isNullObject(serviceProvider)) {
            return serviceProvider.getContactPersonEmail();
        } else {
            return null;
        }
    }
}

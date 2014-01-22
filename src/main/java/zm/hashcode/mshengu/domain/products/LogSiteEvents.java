/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public class LogSiteEvents implements Serializable, Comparable<LogSiteEvents> {

    @Id
    private String id;
    private Date date;
    private String siteId;
    private String action;
    private String truckId;
    private String unitId;

    public LogSiteEvents() {
    }

    private LogSiteEvents(Builder builder) {
        this.date = builder.date;
        this.action = builder.action;
        this.siteId = builder.siteId;
        this.truckId = builder.truckId;
        this.unitId = builder.unitId;
    }

    @Override
    public int compareTo(LogSiteEvents logSiteEvents) {
        return -1 * date.compareTo(logSiteEvents.date);
    }

    public static class Builder {

        private String id;
        private Date date;
        private String siteId;
        private String action;
        private String truckId;
        private String unitId;

        public Builder(Date value) {
            this.date = value;
        }

        public Builder siteId(String value) {
            this.siteId = value;
            return this;
        }

        public Builder truckId(String value) {
            this.truckId = value;
            return this;
        }

        public Builder unitId(String value) {
            this.unitId = value;
            return this;
        }

        public Builder action(String value) {
            this.action = value;
            return this;
        }

        public LogSiteEvents build() {
            return new LogSiteEvents(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTruckId() {
        return truckId;
    }

    public String getUnitId() {
        return unitId;
    }
}

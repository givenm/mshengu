/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ferox
 */
public class SiteLogsBean implements Serializable{
    
   
    private String id;
    private Date serviceDate;
    private Date serviceTime;
    
    private String servicedBy;
    private String servicePerformed;
    private int numberOfUnitsServiced;
    private int numberOfUnitsNotServiced;
    
}

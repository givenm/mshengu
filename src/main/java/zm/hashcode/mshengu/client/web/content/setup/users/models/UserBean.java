/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.users.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import zm.hashcode.mshengu.domain.ui.util.Role;

/**
 *
 * @author boniface
 */
public final class UserBean implements Serializable, Comparable<UserBean> {

    private String id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    private String othername;
    private Date dateofbirth;
    private String password;
    @NotNull
    private String email;
    private boolean enabled;
    @NotNull
    private Set<String> roleIds;
    
    
    private String institutionId;
//    
//    private Set<Role> roleIds;
    @NotNull
    private String username;
//    private boolean enable;
//    private Set<PersonEducation> educations;
//    private Set<PersonEmploymentHistory> personEmploymentHistorys;
//    private PersonDemography demography;
//    private Set<PersonIdentities> personIdentities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int compareTo(UserBean o) {
        return this.getLastname().compareTo(o.getLastname());
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the roleIds
     */
    public Set<String> getRoleIds() {
        return roleIds;
    }

    /**
     * @param roleIds the roleIds to set
     */
    public void setRoleIds(Set<String> roleIds) {
        this.roleIds = roleIds;
    }

    /**
     * @return the institutionId
     */
    public String getInstitutionId() {
        return institutionId;
    }

    /**
     * @param institutionId the institutionId to set
     */
    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * @return the roleIds
//     */
//    public Set<Role> getRoleIds() {
//        return roleIds;
//    }
//
//    /**
//     * @param roleIds the roleIds to set
//     */
//    public void setRoleIds(Set<Role> roleIds) {
//        this.roleIds = roleIds;
//    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.password.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author boniface
 */
public class PasswordBean implements Serializable {

    @NotNull
    private String oldpassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String repeatPassword;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

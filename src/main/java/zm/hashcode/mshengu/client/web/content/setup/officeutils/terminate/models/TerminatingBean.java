/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.terminate.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Luckbliss
 */
public class TerminatingBean implements Serializable {

    private String id;
    @NotNull
    private String code;
    @NotNull
    private String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

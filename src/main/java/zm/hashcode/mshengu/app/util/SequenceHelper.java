/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util;

import java.io.Serializable;
import java.util.Date;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.domain.ui.util.Sequence;
//import net.glxn.qrgen.QRCode;

/**
 *
 * @author Ferox
 */
public class SequenceHelper implements Serializable {

    private String formatNotificationNumber(int refNumber) {
        //100000
        if (refNumber < 10) {
            return "00000" + refNumber; //000001-000009
        } else if (refNumber < 100) {
            return "0000" + refNumber;  //000010-000099
        } else if (refNumber < 1000) {
            return "000" + refNumber;   //000100-000999
        } else if (refNumber < 10000) {
            return "00" + refNumber;    //001000-009999
        } else if (refNumber < 100000) {
            return "0" + refNumber;     //010000-099999
        } else {
            return "" + refNumber;
        }
    }

    private void updateSequence(Sequence notificationSequence) {

//        sequenceService = ctx.getBean(sequenceService.class);
//        SequenceFacade
        int sequenceValue = notificationSequence.getValue() + 1;

        if (!StringUtils.isEmpty(notificationSequence)) {
            Sequence newUnitIdSequence = new Sequence.Builder(notificationSequence.getName())
                    .sequenceType(notificationSequence.getSequenceType())
                    .namingCode(notificationSequence.getNamingCode())
                    .value(sequenceValue)
                    .id(notificationSequence.getId())
                    .build();
            SequenceFacade.getSequenceListService().merge(newUnitIdSequence);
        }
    }
//    String refNumber = "";
//    Sequence sequence = getSequence(mailNotifications);

    public String getRefNumber(Sequence sequence) {

        String refNumber = "EN_MSH" + new Date().toString();

        if (sequence != null) {
//                Sequence sequence = mailNotifications.getSequence();
            String counter = formatNotificationNumber(sequence.getValue());
            refNumber = sequence.getNamingCode() + "-" + counter;// formatNotificationNumber(sequence.getValue());; //get formated refnumber
            updateSequence(sequence); // update sequence
            return refNumber;
        } else {
            return refNumber;
        }

    }

    public String getSequenceInitialNumber(Sequence sequence) {

        String refNumber = "EN_MSH" + new Date().toString();

        if (sequence != null) {
            refNumber = sequence.getNamingCode() + "-" + formatNotificationNumber(sequence.getValue());// formatNotificationNumber(sequence.getValue());; //get formated refnumber
            return refNumber;
        } else {
            return refNumber;
        }

    }
}
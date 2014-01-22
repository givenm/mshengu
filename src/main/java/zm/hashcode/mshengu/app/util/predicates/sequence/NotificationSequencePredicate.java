package zm.hashcode.mshengu.app.util.predicates.sequence;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.ui.util.Sequence;
import zm.hashcode.mshengu.domain.ui.util.SequenceType;

/**
 *
 * @author boniface
 */
public class NotificationSequencePredicate implements Predicate<Sequence> {

    @Override
    public boolean apply(Sequence sequence) {
        if ("NOTIFICATION_SEQUENCE".equalsIgnoreCase(getSequenceName(sequence))) {
            return true;
        }
        return false;
    }

    private String getSequenceName(Sequence sequence) {
        if (sequence != null) {
            return getSequenceType(sequence.getSequenceType());
        }
        return null;

    }

    private String getSequenceType(SequenceType sequenceType) {
        if (sequenceType != null) {
            return sequenceType.getName();
        }
        return null;
    }
}

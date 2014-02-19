/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Colin
 */
public class FlagImage implements Serializable {

    /**
     * Determines Flagging based on parameter and return an Embedded type (a
     * flag) with the appropriate color
     *
* @param value BigDecimal
     * @return Embedded
     */
    public Embedded determineFlag(BigDecimal value) {
        // Image as a tHEME Resource
        Embedded greenImage = new Embedded("", new ThemeResource("images/green_flag.png"));
        Embedded redImage = new Embedded("", new ThemeResource("images/red_flag.png"));
        Embedded yellowImage = new Embedded("", new ThemeResource("images/yellow_flag.png"));
        Double val = value.doubleValue();
        if (val.compareTo(new Double("0.0")) == 0) {
            return new Embedded();
        }
        if (val.compareTo(new Double("7.9")) > 0) {
            return redImage;
        } else if (val.compareTo(new Double("6.0")) > 0) {
            return yellowImage;
        }

        return greenImage;
    }

    /**
     * Determines Flagging based on parameter and return an image (a flag) with
     * the appropriate color
     *
* @param value BigDecimal
     * @return Image
     */
    public Image determineImageFlag(BigDecimal value) {
        // Image as a tHEME Resource
        Image greenImage = new Image("", new ThemeResource("images/green_flag.png"));
        Image redImage = new Image("", new ThemeResource("images/red_flag.png"));
        Image yellowImage = new Image("", new ThemeResource("images/yellow_flag.png"));
        Double val = value.doubleValue();

        if (val.compareTo(new Double("0.0")) == 0) {
            return new Image();
        }

        if (val.compareTo(new Double("7.9")) > 0) {
            return redImage;
        } else if (val.compareTo(new Double("6.0")) > 0) {
            return yellowImage;
        }

        return greenImage;
    }

//
//    /**
//     * Determines Flagging based on parameter and return an image (a flag) with
//     * the appropriate color
//     *
//     * @param value BigDecimal
//     * @return Image
//     */
//    public Image determineImageFlag(BigDecimal value) {
//        // Image as a tHEME Resource
//        Image greenImage = new Image("", new ThemeResource("images/green_flag.png"));
//        Image redImage = new Image("", new ThemeResource("images/red_flag.png"));
//        Image yellowImage = new Image("", new ThemeResource("images/yellow_flag.png"));
//        Double val = value.doubleValue();
//
//        if (val.compareTo(new Double("0.0")) == 0) {
//            return new Image();
//        }
//        if (val.compareTo(new Double("5.0")) > 0) {
//            return redImage;
//        } else if (val.compareTo(new Double("3.5")) > 0) {
//            return yellowImage;
//        }
//
//        return greenImage;
//    }
//
//    /**
//     * Determines Flagging based on parameter and return an Embedded type (a
//     * flag) with the appropriate color
//     *
//     * @param value BigDecimal
//     * @return Embedded
//     */
//    public Embedded determineFlag(BigDecimal value) {
//        // Image as a tHEME Resource
//        Embedded greenImage = new Embedded("", new ThemeResource("images/green_flag.png"));
//        Embedded redImage = new Embedded("", new ThemeResource("images/red_flag.png"));
//        Embedded yellowImage = new Embedded("", new ThemeResource("images/yellow_flag.png"));
//        Double val = value.doubleValue();
//
//        if (val.compareTo(new Double("0.0")) == 0) {
//            return new Embedded();
//        }
//        if (val.compareTo(new Double("5.0")) > 0 || val.compareTo(new Double("5.0")) == 0) {
//            return redImage;
//        } else if (val.compareTo(new Double("3.5")) > 0 || val.compareTo(new Double("3.5")) == 0) {
//            return yellowImage;
//        }
//        return greenImage;
//    }
    /**
     * Determines Vehicle Ranking Flagging based on parameter and return an
     * image (a flag) with the appropriate color
     *
     * @param value BigDecimal
     * @return Image
     */
    public Image determineVehicleRankingFlag(BigDecimal value) {
        // Image as a tHEME Resource
        Image greenImage = new Image("", new ThemeResource("images/green_flag.png"));
        Image redImage = new Image("", new ThemeResource("images/red_flag.png"));
        Image yellowImage = new Image("", new ThemeResource("images/yellow_flag.png"));
        Double val = value.doubleValue();

        if (val.compareTo(new Double("0.0")) == 0) {
            return new Image();
        }
        if (val.compareTo(new Double("5.0")) > 0 || val.compareTo(new Double("5.0")) == 0) {
            return redImage;
        } else if (val.compareTo(new Double("3.5")) > 0 || val.compareTo(new Double("3.5")) == 0) {
            return yellowImage;
        }

        return greenImage;
    }

    public Embedded determineFuelUsageFlag(BigDecimal value) {
        // Image as a tHEME Resource
        Embedded greenImage = new Embedded("", new ThemeResource("images/green_flag.png"));
        Embedded redImage = new Embedded("", new ThemeResource("images/red_flag.png"));
        Embedded yellowImage = new Embedded("", new ThemeResource("images/yellow_flag.png"));
        Double val = value.doubleValue();

        if (val.compareTo(new Double("0.0")) == 0) {
            return new Embedded();
        }
        if (val.compareTo(new Double("8.0")) == 0 || val.compareTo(new Double("8.0")) > 0) {
            return redImage;
        } else if (val.compareTo(new Double("6.0")) > 0) {
            return yellowImage;
        }
        return greenImage;
    }
}

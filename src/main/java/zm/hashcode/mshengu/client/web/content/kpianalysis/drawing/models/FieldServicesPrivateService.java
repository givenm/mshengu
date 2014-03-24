/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models;

/**
 *
 * @author Luckbliss
 */
public class FieldServicesPrivateService {
    
    //No Services Completed
    public String computeNoServicesCompleted(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Completed Percentage
    public String computeCompletedPercentage(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //No Services Not Completed
    public String computeNoServicesNotCompleted(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Uncompleted Percentage
    public String computeUncompletedPercentage(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Private Contribution
    public String computePrivateContribution(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }
    
    //topLeftRectangleColorBigPentagon
    public String computeRectangleColorInBigPentagon(String month, String year){
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }
}

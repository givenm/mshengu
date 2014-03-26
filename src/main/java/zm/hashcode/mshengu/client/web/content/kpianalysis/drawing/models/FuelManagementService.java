/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models;

/**
 *
 * @author Luckbliss
 */
public class FuelManagementService {

    //Fuel Efficiency
    public String computeFuelEfficiency(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Spend Per Service
    public String computeSpendPerService(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Spend Per Unit
    public String computeSpendPerUnit(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Vehicles Above Specifications
    public String computeVehiclesAboveSpecifications(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Fuel Spend
    public String computeFuelSpend(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }
    
    //bottomRightRectangleColorBigPentagon
    public String computeRectangleColorInBigPentagon(String month, String year){
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

}

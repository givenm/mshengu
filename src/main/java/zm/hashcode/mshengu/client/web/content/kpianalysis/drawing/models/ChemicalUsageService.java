/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models;

/**
 *
 * @author Luckbliss
 */
public class ChemicalUsageService {
    
    //Cost per Service
    public String computeCostPerService(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Sanitizer Per Service
    public String computeSanitizerPerService(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //Deodirize Per Unit
    public String computeDeodirizePerUnit(String month, String year) {
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

    //Chemical Spend
    public String computeChemicalSpend(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }

    //bottomLeftRectangleColorBigPentagon
    public String computeRectangleColorInBigPentagon(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            return "green";
        }
        return "white";
    }
}

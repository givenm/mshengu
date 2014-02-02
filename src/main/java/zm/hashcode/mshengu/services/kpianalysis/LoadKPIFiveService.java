/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis;

/**
 *
 * @author Luckbliss
 */
//Chemical Usage
public interface LoadKPIFiveService {
    public double getCostPerService(String month, int year);
    public double getSanitizerPerService(String month, int year);
    public double getDeodorizerPerService(String month, int year);
    public double getSpendPerUnit(String month, int year);
    public double getChemicalSpend(String month, int year);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis;

/**
 *
 * @author Luckbliss
 */
//Field Services (Contract)
public interface LoadKPIOneService {
    public double getNoServicesPerformed(String month, int year);
    public double getCompletedPercentage(String month, int year);
    public double getNoServicesNotCompleted(String month, int year);
    public double getUncompletedPercentage(String month, int year);
    public double getUnitDeployment(String month, int year);
}

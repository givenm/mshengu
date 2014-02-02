/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.kpianalysis;

/**
 *
 * @author Luckbliss
 */
//Maintenance Managament
public interface LoadKPIFourService {
    public double getMaintenanceEfficiency(String month, int year);
    public double getSpendPerService(String month, int year);
    public double getSpendPerUnit(String month, int year);
    public double getVehiclesAboveSpec(String month, int year);
    public double getMaintenanceSpend(String month, int year);
}

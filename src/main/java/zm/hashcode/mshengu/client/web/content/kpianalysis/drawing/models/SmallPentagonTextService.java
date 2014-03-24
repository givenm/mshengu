/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.drawing.models;

import java.util.Random;

/**
 *
 * @author Luckbliss
 */
public class SmallPentagonTextService {
    //Add text to small pentagon

    public String addText(String month, String year) {
        if (!month.equals("") && !year.equals("")) {
            Random random = new Random();
            int value = random.nextInt(5);
            String text = "Z";
            if (value == 1) {
                text = "A";
            } else if (value == 2) {
                text = "B";
            } else if (value == 3) {   
                text = "C";
            } else if (value == 4) {
                text = "D";
            } else if (value == 5) {
                text = "E";
            }
            return text;
        }
        return "";
    }
}

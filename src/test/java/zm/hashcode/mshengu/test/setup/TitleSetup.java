/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.setup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.ui.demographics.Title;
import zm.hashcode.mshengu.services.ui.demographics.TitleService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Tiwana Siyabonga
 */
public class TitleSetup extends AppTest {

    private TitleService titleService;

//    @Test
    public void setUpTitles() {

        try {

            titleService = ctx.getBean(TitleService.class);
            URL url = this.getClass().getResource("/titleList.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                String titleValue = worksheet.getRow(i).getCell(0).toString();

                Title title = new Title.Builder(titleValue).build();
                titleService.persist(title);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }
}

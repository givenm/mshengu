/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.products.DaysOfWeekEnum;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author boniface
 */
public class DateTimeHelperWeeklyTest extends AppTest {


    DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();


    //@Test
    public void getPrevCurrentNextDateTest() {
        System.out.println("DATES IN FULL");
        System.out.println("======================================================");
        System.out.println("Last Saturday:  " + dtfwh.getLastSaturdayDateFull());
        System.out.println("Last Sunday:  " + dtfwh.getLastSundayDateFull());
        System.out.println("Yesterday:  " + dtfwh.getYesterdayDateFull());
        System.out.println("Today:  " + dtfwh.getTodaysDateFull());
        System.out.println("Tomorrow:  " + dtfwh.getTomorrowDateFull());

        System.out.println("\nDATES IN YYMMDD");
        System.out.println("======================================================");
        System.out.println("Last Saturday:  " + dtfwh.getLastSaturdayDateYYMMDD());
        System.out.println("Last Sunday:  " + dtfwh.getLastSundayDateYYMMDD());
        System.out.println("======================================================");
        System.out.println("Yesterday:  " + dtfwh.getYesterdayDateYYMMDD());
        System.out.println("Today:  " + dtfwh.getTodaysDateYYMMDD());
        System.out.println("Tomorrow:  " + dtfwh.getTomorrowDateYYMMDD());
//        c.setTime(date);
    }

    //@Test
    public void testWeekDays() {


            System.out.println("DATES TEST STARTS");
//        for (int i = 1; i <= 7; i++) {
//            dtfwh.resetDayOfWeek(i);
            printDayOfTheWeekDate(dtfwh.getDayOfWeek());
            
            System.out.println("\n======================================================");
            System.out.println("DAY OF WEEK EREYESTERDAY: " + dtfwh.getDayOfWeekEreyesterdayStr());
            System.out.println("DAY OF WEEK YESTERDAY:    " + dtfwh.getDayOfWeekYesterdayStr());
            System.out.println("DAY OF WEEK TODAY:        " + dtfwh.getDayOfWeekTodayStr());

            System.out.println("\n======================================================");
            System.out.println("DAY OF WEEK EREYESTERDAY: (int) " + dtfwh.getDayOfWeekEreyesterday());
            System.out.println("DAY OF WEEK YESTERDAY: (int)    " + dtfwh.getDayOfWeekYesterday());
            System.out.println("DAY OF WEEK TODAY: (int)        " + dtfwh.getDayOfWeekToday());


            System.out.println("\n======================================================");
            System.out.println("LAST SATURDAY's DATE: " + dtfwh.getLastSaturdayDateFull());
            System.out.println("LAST SUNDAYS's DATE:  " + dtfwh.getLastSundayDateFull());
            System.out.println("\n");
            System.out.println("EREYESTERDAY's DATE:  " + dtfwh.getDateEreyesterday());
            System.out.println("YESTERDAY's DATE:     " + dtfwh.getDateYesterday());
            System.out.println("TODAY's DATE:         " + dtfwh.getDateToday());


            System.out.println("\n======================================================");
            System.out.println("LAST SATURDAY's DATE:  " + dtfwh.getLastSaturdayDateYYMMDD());
            System.out.println("LAST SUNDAYS's DATE:   " + dtfwh.getLastSundayDateYYMMDD());
            System.out.println("\n");
            System.out.println("EREYESTERDAY's DATE: (str) " + dtfwh.getDateEreyesterdayStr());
            System.out.println("YESTERDAY's DATE: (str)    " + dtfwh.getDateYesterdayStr());
            System.out.println("TODAY's DATE: (str)        " + dtfwh.getDateTodayStr());

//        }
    }

    private void printDayOfTheWeekDate(int dayOfWeek) {

        
            System.out.println("\n==================DAY OF WEEK (I = ( " + dayOfWeek + ") ====================================\n");
        switch (dayOfWeek) {
            case 1:
                System.out.println("DAY OF WEEK :" + DaysOfWeekEnum.SUNDAY.name());
                System.out.println("DATE FULL : " + dtfwh.getSundayDateFull());
                System.out.println("DATE YYMMDD : " + dtfwh.getSundayDateYYMMDD());
                break;
            case 2:
                System.out.println("DAY OF WEEK :" + DaysOfWeekEnum.MONDAY.name());
                System.out.println("DATE FULL : " + dtfwh.getMondayDateFull());
                System.out.println("DATE YYMMDD : " + dtfwh.getMondayDateYYMMDD());
                break;
            case 3:
                System.out.println("DAY OF WEEK :" + DaysOfWeekEnum.TUESDAY.name());
                System.out.println("DATE FULL : " + dtfwh.getTuesdayDateFull());
                System.out.println("DATE YYMMDD : " + dtfwh.getTuesdayDateYYMMDD());
                break;
            case 4:
                System.out.println("DAY OF WEEK :" + DaysOfWeekEnum.WEDNESDAY.name());
                System.out.println("DATE FULL : " + dtfwh.getWednesdayDateFull());
                System.out.println("DATE YYMMDD : " + dtfwh.getWednesdayDateYYMMDD());
                break;
            case 5:
                System.out.println("DAY OF WEEK :" + DaysOfWeekEnum.THURSDAY.name());
                System.out.println("DATE FULL : " + dtfwh.getThursdayDateFull());
                System.out.println("DATE YYMMDD : " + dtfwh.getThursdayDateYYMMDD());
                break;
            case 6:
                System.out.println("DAY OF WEEK :" + DaysOfWeekEnum.FRIDAY.name());
                System.out.println("DATE FULL : " + dtfwh.getFridayDateFull());
                System.out.println("DATE YYMMDD : " + dtfwh.getFridayDateYYMMDD());
                break;
            case 7:
                System.out.println("DAY OF WEEK :" + DaysOfWeekEnum.SATURDAY.name());
                System.out.println("DATE FULL : " + dtfwh.getSaturdayDateFull());
                System.out.println("DATE YYMMDD : " + dtfwh.getYesterdayDateYYMMDD());
                break;
            default:
//                dayOfWeek = "DAY_NOT_FOUND";
                System.out.println("WTF!!!");
                break;

        }

    }
}

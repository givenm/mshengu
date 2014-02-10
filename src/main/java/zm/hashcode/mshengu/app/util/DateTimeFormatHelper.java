/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ferox
 */
public class DateTimeFormatHelper implements Serializable {

    private final String standardDateFormat = "yyyy-MM-dd HH:mm:ss";
    private final String otherStandardDateFormat = "EEE MMM dd HH:mm:ss yyyy"; // "Mon Sep 30 00:00:00 SAST 2013"
    private final String returnDateTimeFormat = "yyyy-MM-dd HH:mm";
    private final String returnDateFormat = "yyyy-MM-dd";
    private final String returnDayMonthYearFormat = "dd-MM-yyyy";
    private final String returnTimeFormat = "HH:mm";
    private final String returnMonthYearFormat = "MM-yyyy";
    private final String fullDateTimeFormat = "EEE MMM dd, yyyy, HH:mm:ss";
    private final String fullDateTimeFormatNoTime = "EEE MMM dd, yyyy";

    public String getFullDate(String dateIn) {
        Date dateOut;
        String time = "";
        try {
            dateOut = new SimpleDateFormat(standardDateFormat).parse(dateIn);
            time = new SimpleDateFormat(fullDateTimeFormat).format(dateOut);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeFormatHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    /**
     *
     * @param dateIn format "2010-07-14 09:45:02"
     * @return time format 09:45
     */
    public String getHourMinute(String dateIn) {
        Date dateOut;
        String time = "";
        try {
            dateOut = new SimpleDateFormat(standardDateFormat).parse(dateIn);
            time = new SimpleDateFormat(returnTimeFormat).format(dateOut);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeFormatHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    /**
     *
     * @param dateIn (string) format "2010-07-14 09:45:02"
     * @return Date (string) format 2010-07-14
     */
    public String getYearMonthDay(String dateIn) {
        Date dateOut;
        String time = "";
        try {
            dateOut = new SimpleDateFormat(standardDateFormat).parse(dateIn);
            time = new SimpleDateFormat(returnDateFormat).format(dateOut);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeFormatHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    public String getDayMonthYear(String dateIn) {
        Date dateOut;
        String time = "";
        try {
            dateOut = new SimpleDateFormat(standardDateFormat).parse(dateIn);
            time = new SimpleDateFormat(returnDayMonthYearFormat).format(dateOut);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeFormatHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    /**
     *
     * @param dateIn (string) format "2010-07-14 09:45"
     * @return Date (string) format 2010-07-14 09:45
     */
    public String getDateTime(String dateIn) {
        Date dateOut;
        String time = "";
        try {
            dateOut = new SimpleDateFormat(standardDateFormat).parse(dateIn);
            time = new SimpleDateFormat(returnDateTimeFormat).format(dateOut);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeFormatHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    /**
     *
     * @param dateIn(any format format)
     * @return time format 09:45
     */
    public String getHourMinute(Date dateIn) {
        return new SimpleDateFormat(returnTimeFormat).format(dateIn);
    }

    /**
     *
     * @param dateIn (any format format)
     * @return Date format 2010-07-14
     */
    public String getYearMonthDay(Date dateIn) {
        if (dateIn != null) {
            return new SimpleDateFormat(returnDateFormat).format(dateIn);
        }
        return null;

    }

    public String getDayMonthYear(Date dateIn) {
        if (dateIn != null) {
            return new SimpleDateFormat(returnDayMonthYearFormat).format(dateIn);
        }
        return null;

    }

    public String getFullFormateddate(Date dateIn) {
        if (dateIn != null) {
            return new SimpleDateFormat(fullDateTimeFormat).format(dateIn);
        }
        return null;

    }

    public String getFullFormateddateNoTime(Date dateIn) {
        if (dateIn != null) {
            return new SimpleDateFormat(fullDateTimeFormatNoTime).format(dateIn);
        }
        return null;

    }

    /**
     *
     * @param dateIn (any format format)
     * @return Date format 2010-07-14 09:45
     */
    public String getDateTime(Date dateIn) {
        return new SimpleDateFormat(returnDateTimeFormat).format(dateIn);
    }

    /*
     * resets the time in a date to zero e.g. Mon Jul 02 23:03:54 SAST 2012
     * to Mon Jul 02 00:00:00 SAST 2012 because the time part makes
     * a big difference btn two dates.
     * Can be used to compare to dates for equality, before or after
     *
     * @param dateIn Date
     * @return Date
     */
    public Date resetTimeOfDate(Date inDate) {
        //
        Calendar cal = Calendar.getInstance();
        cal.setTime(inDate);

        // Set time fields to zero
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();

    }

    /*
     * reset the time of a Date to 00:00:00 and resets the month to the
     * first day of the month
     *
     * @param dateIn Date
     * @return Date
     */
    public Date resetTimeAndMonthStart(Date inDate) {
        //
        Calendar cal = Calendar.getInstance();
        cal.setTime(resetTimeOfDate(inDate));

        // will reset to first day of current month
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();

    }

    /*
     * reset the time of a Date to 00:00:00 and resets the month to the
     * last day of the month
     *
     * @param dateIn Date
     * @return Date
     */
    public Date resetTimeAndMonthEnd(Date inDate) {
        //
        Calendar cal = Calendar.getInstance();
        cal.setTime(resetTimeOfDate(inDate));

        // will reset to Last day of current month
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        return cal.getTime();

    }

    /**
     *
     * @param dateIn Date (any format format) e.g. 2010-07-14 09:45
     * @return String e.g. "07"
     */
    public String getMonthNumber(Date date) {
        Calendar calendarQueried = Calendar.getInstance();
        calendarQueried.setTime(date);
        return calendarQueried.get(Calendar.MONTH) + "";
    }

    /**
     *
     * @param dateIn (any format format) e.g. 2010-07-14 09:45
     * @return String e.g. "2010"
     */
    public String getYearNumber(Date date) {
        Calendar calendarQueried = Calendar.getInstance();
        calendarQueried.setTime(date);
        return calendarQueried.get(Calendar.YEAR) + "";
    }

    /**
     * incoming format "Mon Sep 30 00:00:00 SAST 2013" and returns format
     * "07-2010"
     *
     * @param dateIn String
     * @return String
     */
    public String getMonthYear(String dateIn) {
        DateFormat readFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); // // "Mon Sep 30 00:00:00 SAST 2013"
//        DateFormat readFormat = new SimpleDateFormat( "EEE MMM dd yyyy hh:mm aaa");
        //    DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        DateFormat writeFormat = new SimpleDateFormat("MM-yyyy");
//        Date dateOut;
        String time = "";
        try {
//            Date d = df.parse("Sat May 22 00:00:00 CEST 1993");
            Date d = readFormat.parse(dateIn);
//            dateOut = new SimpleDateFormat(otherStandardDateFormat).parse(dateIn);
//            time = new SimpleDateFormat(returnMonthYearFormat).format(d);
            time = writeFormat.format(d);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeFormatHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    /**
     *
     * @param dateIn format "Mon Sep 30 00:00:00 SAST 2013"
     * @return String format "NOV-2010"
     */
    public String getMonthYearMonthAsMediumString(String dateIn) {
        DateFormat readFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); // // "Mon Sep 30 00:00:00 SAST 2013"
//        DateFormat readFormat = new SimpleDateFormat( "EEE MMM dd yyyy hh:mm aaa");
        //    DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        DateFormat writeFormat = new SimpleDateFormat("MMM-yyyy");
//        Date dateOut;
        String time = "";
        try {
//            Date d = df.parse("Sat May 22 00:00:00 CEST 1993");
            Date d = readFormat.parse(dateIn);
//            dateOut = new SimpleDateFormat(otherStandardDateFormat).parse(dateIn);
//            time = new SimpleDateFormat(returnMonthYearFormat).format(d);
            time = writeFormat.format(d);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeFormatHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    /**
     *
     * @param date Date (any format format) e.g. 2010-07-14 09:45
     * @return String e.g. "07"
     */
    public String getPreviousMonthNumber(Date date) {
        Calendar calendarQueried = Calendar.getInstance();
        calendarQueried.setTime(date);
        calendarQueried.add(Calendar.MONTH, -1);
        return calendarQueried.get(Calendar.MONTH) + "";
    }

    /**
     *
     * @param date Date (any format format) e.g. 2010-07-14 09:45
     * @return String e.g. "2010"
     */
    public String getPreviousMonthYearNumber(Date date) {
        Calendar calendarQueried = Calendar.getInstance();
        calendarQueried.setTime(date);
        calendarQueried.add(Calendar.MONTH, -1);
        return calendarQueried.get(Calendar.YEAR) + "";
    }

    public Date getDate(int year, int month) {
        Calendar calendar = new GregorianCalendar();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public Date getDate(int day, int month, int year) {
        Calendar calendar = new GregorianCalendar();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /* public String getMondayDateYYMMDD() {
     return getYearMonthDay("");
     }
     System.out.println("Today:  return calendar.getTime();
     //        c.setTime(date;
     calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY;
     System.out.println("MONDAY: return calendar.getTime());
     //        c.setTime(date);
     calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
     System.out.println("TUESDAY: return calendar.getTime());
     //        c.setTime(date);
     calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
     System.out.println("WEDNESDAY: return calendar.getTime());
     //        c.setTime(date);
     calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
     System.out.println("THURSDAY: return calendar.getTime());
     //        c.setTime(date);
     calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
     System.out.println("FRIDAY: return calendar.getTime());
     //        c.setTime(date);
     calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
     System.out.println("SATURDAY: return calendar.getTime());
     //        c.setTime(date);
     calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
     System.out.println("SUNDAY: return calendar.getTime());
     */
}

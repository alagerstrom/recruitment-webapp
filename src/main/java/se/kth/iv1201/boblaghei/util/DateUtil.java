package se.kth.iv1201.boblaghei.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Helper class that eases usage of java.util.Date
 */
public class DateUtil {
    /**
     * Helper method to convert localdates to dates
     *
     * @param localDate The localdate to convert
     *
     * @return the converted date
     */
    public static Date getDateFrom(LocalDate localDate) {
        if (localDate == null)
            return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Helper method to format a date as a string on the pattern yyyy-MM-dd
     *
     * @param date The date to format
     *
     * @return A string representation of the date on the form yyyy-MM-dd
     */
    public static String formatAsDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}

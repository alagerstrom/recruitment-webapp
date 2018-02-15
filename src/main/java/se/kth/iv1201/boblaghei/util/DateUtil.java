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
    public static Date getDateFrom(LocalDate localDate) {
        if (localDate == null)
            return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String formatAsDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}

package se.kth.iv1201.boblaghei.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Helper class that eases usage of java.util.Date
 */
public class DateUtil {
    public static Date getDateFrom(LocalDate localDate){
        if (localDate == null)
            return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

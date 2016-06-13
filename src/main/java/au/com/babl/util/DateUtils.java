package main.java.au.com.babl.util;

import java.util.Calendar;

/**
 * Created by holly on 06/06/2016.
 */
public class DateUtils
{
    public static Long createFutureDate(int min)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, min);
        return cal.getTimeInMillis();
    }
}

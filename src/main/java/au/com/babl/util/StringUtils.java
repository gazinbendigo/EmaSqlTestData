package main.java.au.com.babl.util;

import java.util.Random;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class StringUtils
{

    public static boolean isValidString(String val)
    {
        if(val != null)
        {
            String trimmed = val.trim();
            return trimmed.length() > 0;
        }
        return false;
    }

    public static String generateRandomNumericString(int maxLength)
    {
        Random random = new Random();
        Long number = new Long((Math.round(Math.random() * maxLength)));
        return number.toString();
    }

    public static String generateRandomString()
    {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

}

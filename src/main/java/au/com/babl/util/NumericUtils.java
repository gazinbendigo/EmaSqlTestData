package main.java.au.com.babl.util;

/**
 * Created by holly on 06/06/2016.
 */
public class NumericUtils
{
    public static int createRandomNumber(int index)
    {
        int value = (int)(Math.random() * index + 1);
        return value;
    }

}

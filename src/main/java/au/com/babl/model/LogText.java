package main.java.au.com.babl.model;

/**
 * Created by holly on 04/06/2016.
 */
public class LogText
{
    private String text;

    public LogText(String message)
    {
        text = message;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}

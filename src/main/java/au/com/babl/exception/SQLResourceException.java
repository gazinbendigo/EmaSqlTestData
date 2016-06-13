package main.java.au.com.babl.exception;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class SQLResourceException extends SQLException implements Serializable
{
    private static final long serialVersionUID = 1797927537848479816L;


    public SQLResourceException()
    {
        super();
    }

    public SQLResourceException(String message)
    {
        super(message);
    }

    public SQLResourceException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SQLResourceException(Throwable cause)
    {
        super(cause);
    }

    public SQLResourceException(SQLException se, String message)
    {
        super(message, se);
    }
}

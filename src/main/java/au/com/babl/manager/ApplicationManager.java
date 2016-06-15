package main.java.au.com.babl.manager;

import main.java.au.com.babl.dao.ApplicationDAO;
import main.java.au.com.babl.model.Application;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by holly on 08/06/2016.
 */
public class ApplicationManager implements Serializable
{
    private static final long serialVersionUID = 8335976457214060778L;
    private Connection conn;

    public ApplicationManager(Connection connection)
    {
        conn = connection;
    }


    public void insertConsumer(Application application)
    {
        ApplicationDAO dao = new ApplicationDAO(conn);
        try
        {
            dao.insertApplication(application);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}

package main.java.au.com.babl.manager;

import main.java.au.com.babl.dao.ConsumerDAO;
import main.java.au.com.babl.model.Consumer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by holly on 08/06/2016.
 */
public class ConsumerManager implements Serializable
{
    private static final long serialVersionUID = 8335976457214060778L;
    private Connection conn;

    public ConsumerManager(Connection connection)
    {
        conn = connection;
    }


    public void insertConsumer(Consumer consumer)
    {
        ConsumerDAO dao = new ConsumerDAO(conn);
        try
        {
            dao.insertConsumer(consumer);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}

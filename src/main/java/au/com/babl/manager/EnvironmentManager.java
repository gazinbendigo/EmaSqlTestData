package main.java.au.com.babl.manager;

import main.java.au.com.babl.dao.EnvironmentDAO;
import main.java.au.com.babl.model.Environment;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class EnvironmentManager implements Serializable
{
    private static final long serialVersionUID = 8335976457214060777L;
    private Connection conn;
    private EnvironmentDAO environmentDAO;

    public EnvironmentManager(Connection connection)
    {
        conn = connection;
    }

    public void insertEnvironment(Environment environment)
    {
        environmentDAO = new EnvironmentDAO(conn);
        try
        {
            environmentDAO.insertEnvironment(environment);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Environment getEnvironmentByName(String name)
    {
        Environment environment = null;
        try
        {
            environmentDAO = new EnvironmentDAO(conn);
            environment = environmentDAO.getEnvironmentByName(name);
        }
        catch(SQLException se)
        {

        }
        return environment;
    }

    public List<Environment> getEnvironments()
    {
        List<Environment> environments = new ArrayList<Environment>();
        try
        {
            environmentDAO = new EnvironmentDAO(conn);
            environments =  environmentDAO.getHubEnvironments();
        }
        catch (SQLException se)
        {

        }
        return environments;
    }

}

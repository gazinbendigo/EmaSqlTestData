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




    public void insertEnvironments()
    {
        final String[] environmentNames = {"HUBA1","HUBA2", "HUBB1", "HUBB2", "HUBC1", "HUBC2", "HUBD1", "HUBD2", "HUBF1", "HUBG1", "HUBG2", "HUBI1", "HUBI2", "HUBJ1"};
        final String[] environmentDescriptions = {"Development", "Development2", "System Test", "System Test2", "System Integration", "System Integration2", "UAT", "UAT2", "Stressed and Volumed", "Tan", "Red", "Gold", "Bob", "Lime"};
        environmentDAO = new EnvironmentDAO(conn);
        for (int i = 0; i < environmentDescriptions.length; i++)
        {
            Environment bean = new Environment(new Long(0), environmentNames[i], environmentDescriptions[i]);
            try
            {
                environmentDAO.insertEnvironment(bean);
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}

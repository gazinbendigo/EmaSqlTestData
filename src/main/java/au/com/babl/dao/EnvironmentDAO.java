package main.java.au.com.babl.dao;

import main.java.au.com.babl.exception.SQLResourceException;
import main.java.au.com.babl.model.Environment;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by adm9360 on 13/11/2015.
 */
public class EnvironmentDAO implements Serializable, DAOProperties
{
    private static final long serialVersionUID = 2049398824131580339L;
    private static final String INSERT_ENVIRONMENT = "INSERT INTO ENVIRONMENT (ENV_NAME, ENV_DESCRIPTION, ORDER_ID) VALUES (?,?,?)";
    private static final String GET_ENVIRONMENT_BY_ID = "SELECT ENV_ID, ENV_NAME, ENV_DESCRIPTION FROM dbo.ENVIRONMENT WHERE ENV_ID = ?";
    private static final String GET_ALL_ENVIRONMENTS = "SELECT ENV_ID, ENV_NAME, ENV_DESCRIPTION FROM dbo.ENVIRONMENT";
    private static final String GET_ENVIRONMENT_BY_NAME = "SELECT ENV_ID, ENV_NAME, ENV_DESCRIPTION FROM dbo.ENVIRONMENT WHERE ENV_NAME = ?";
    private Connection conn;

    public EnvironmentDAO(Connection connection)
    {
        conn = connection;
    }


    public Environment getEnvironmentByName(String name)
            throws SQLException
    {
        Environment environment = null;
        try (PreparedStatement ps = conn.prepareStatement(GET_ENVIRONMENT_BY_NAME))
        {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                environment = extractEnvironmentFromResultSet(rs);
            }
        }
        catch (SQLException se)
        {
            throw new SQLResourceException(se, CREATE_STATEMENT_FAILED);
        }
        return environment;
    }


    public int insertEnvironment(Environment environment)
            throws SQLException
    {
        int rowCount = 0;
        try(PreparedStatement ps = conn.prepareStatement(INSERT_ENVIRONMENT))
        {
            ps.setString(1, environment.getName());
            ps.setString(2, environment.getDescription());
            ps.setInt(3, environment.getOrder());
            rowCount = rowCount + ps.executeUpdate();
        }
        catch (SQLException se)
        {
            throw new SQLResourceException(se, CREATE_STATEMENT_FAILED);
        }

        return rowCount;
    }

    public Environment getEnvironmentById(Environment environment)
            throws SQLException
    {
        Environment env = new Environment();
        try(PreparedStatement ps = conn.prepareStatement(GET_ENVIRONMENT_BY_ID))
        {
            ResultSet rs = ps.executeQuery();
            while(rs.first())
            {
                env = extractEnvironmentFromResultSet(rs);
            }
        }
        catch (SQLException se)
        {
            throw new SQLResourceException(se, CREATE_STATEMENT_FAILED);
        }

        return env;
    }

    public List<Environment> getHubEnvironments()
            throws SQLException
    {
        List<Environment> environments = new ArrayList<Environment>();
        try(Statement st = conn.createStatement())
        {
            ResultSet rs = st.executeQuery(GET_ALL_ENVIRONMENTS);
            while (rs.next())
            {
                environments.add(extractEnvironmentFromResultSet(rs));
            }
        }
        catch (SQLException se)
        {
            throw new SQLResourceException(se, CREATE_STATEMENT_FAILED);
        }


        return environments;
    }

    private Environment extractEnvironmentFromResultSet(ResultSet rs)
            throws SQLException
    {
        Environment env = new Environment();
        env.setId(rs.getLong(1));
        env.setName(rs.getString(2));
        env.setDescription(rs.getString(3));
        env.setOrder(rs.getInt(4));
        return env;
    }

}

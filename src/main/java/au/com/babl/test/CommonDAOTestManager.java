package main.java.au.com.babl.test;

import main.java.au.com.babl.sql.SQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by holly on 06/06/2016.
 */
public abstract class CommonDAOTestManager
{
    private Connection conn;
    private String environment;
    private String environmentName;
    protected static final String[] ENVIRONMENTS = {"EMA_DEV", "EMA_SV", "EMA_UAT"};
    private static final String CREATE_PROCEDURE_GET_CONSUMERS = "";

    CommonDAOTestManager(int id)
    {
        conn = new SQLConnection(ENVIRONMENTS[id]).getConnection();
        setEnvironment(ENVIRONMENTS[id]);
        setEnvironmentName(ENVIRONMENTS[id]);
    }

    protected Connection getDbConnection()
    {
        return conn;
    }

    protected Connection getInstance()
    {
        if(conn == null)
        {
            return new SQLConnection(environment).getConnection();
        }
        else
        {
            return conn;
        }
    }

    public void setEnvironmentName(String env)
    {
        if(env.equalsIgnoreCase(ENVIRONMENTS[0]))
        {
            environmentName = "DEV Test Lab";
        }
        else if(env.equalsIgnoreCase(ENVIRONMENTS[1]))
        {
            environmentName = "System Test Lab";
        }
        else if(env.equalsIgnoreCase(ENVIRONMENTS[2]))
        {
            environmentName = "UAT Test Lab";
        }
        else
        {
            environmentName = "NFI";
        }
    }

    public String getEnvironmentName()
    {
        return environmentName;
    }

    public void setEnvironment(String env)
    {
        environment = env;
    }

    public String getEnvironment()
    {
        return environment;
    }

    public boolean executeStatement(String statement)
    {
        boolean success = false;
        try(Statement st = conn.createStatement())
        {
            success = st.execute(statement);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        return success;
    }



    protected abstract void generateTestData();

    protected abstract void insertTestData();

    protected abstract void refreshTableComponents();

    protected abstract void createTable();

}

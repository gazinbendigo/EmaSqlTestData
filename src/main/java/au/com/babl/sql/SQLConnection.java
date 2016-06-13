package main.java.au.com.babl.sql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by holly on 12/11/2015.
 */
public class SQLConnection
{
    private Connection conn;
    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";//"com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String URL = "jdbc:mysql://";//jdbc:sqlserver://<host>[:<port1433>];databaseName=<database>
    private final String SERVER_NAME = "localhost";
    private final String PORT = "3306";
    private String dbName = "HUBDL";
    private final String USERNAME = "root";
    private final String PWD = "Iluv2java2";


    public SQLConnection(String databaseName)
    {
        dbName = databaseName;
    }

    public Connection getConnection()
    {
        try
        {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(URL + SERVER_NAME + ":" + PORT + "/" + dbName, USERNAME, PWD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }


}

package main.java.au.com.babl.manager;

import main.java.au.com.babl.dao.HubLogDAO;
import main.java.au.com.babl.model.HubLog;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class HublogsManager implements Serializable
{
    private static final long serialVersionUID = 8335976488214060777L;
    private HubLogDAO dao;
    private Connection conn;


    public HublogsManager(Connection connection)
    {
        conn = connection;
    }

    public void insertHubLog(HubLog log)
    {
        dao = new HubLogDAO(conn);
        try
        {
            dao.insertHubLog(log);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public HubLog getHubLogById(int id)
    {
        HubLog log = null;
        try
        {
            dao = new HubLogDAO(conn);
            log = dao.findById(id);
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return log;
    }

    public List<HubLog> getHubLogs()
    {
        List<HubLog> logs = new ArrayList<HubLog>();
        try
        {
            dao = new HubLogDAO(conn);
            logs = dao.getAllLogs();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return logs;
    }


}

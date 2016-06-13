package main.java.au.com.babl.dao;

import main.java.au.com.babl.model.HubLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by holly on 12/11/2015.
 */
public class HubLogDAO
{

    private final String INSERT_HUBLOG = "INSERT INTO HUBLOG(REQUEST_ID, MESSAGE_ID, REQUEST_SERVICE_ID, REQUEST_DTE, BRAND_NME, " +
            "APPLICATION_CDE, APPLICATION_NME, INSTANCE_NME, USER_ID, MESSAGE_TXT, SERVICE_ID, SERVICE_NME, SEVERITY_CDE, LOG_CODE, LOG_TXT, " +
            "SOURCE_NAME, PROVIDER, LOG_MESSAGE, THUNDERHEAD_CONTENT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private final String FIND_ALL_LOGS = "SELECT ID, REQUEST_ID, SERVICE_ID, LOG_TEXT, LOG_MESSAGE FROM HUBLOG";
    private final String FIND_BY_ID = "SELECT ID, REQUEST_ID, SERVICE_ID, LOG_TEXT, LOG_MESSAGE FROM HUBLOG WHERE ID = ?";
    private final String UPDATE_HUBLOG = "";
    private final String FIND_HUB_LOGS = "{call pGetHubLogs(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    public static final String TABLE_NAME = "HUBLOG";

    private Connection conn;


    public HubLogDAO(Connection connection)
    {
        conn = connection;
    }



    public int insertHubLog(HubLog log)
    throws SQLException
    {
        int rowCount = 0;
        try(PreparedStatement ps = conn.prepareStatement(INSERT_HUBLOG))
        {
            ps.setLong(1, log.getRequestId());
            ps.setLong(2, log.getMessageId());
            ps.setLong(3, log.getRequestServiceId());
            ps.setDate(4, new java.sql.Date(log.getDateOfLog().getTime()));
            ps.setString(5, log.getBrand());
            ps.setString(6, log.getApplicationCode());
            ps.setString(7, log.getApplications());
            ps.setString(8, log.getRegion());
            ps.setString(9, log.getUserId());
            ps.setString(10, log.getRequestMessage());
            ps.setInt(11, log.getServiceId());
            ps.setString(12, log.getServiceName());
            ps.setString(13, log.getSeverity());
            ps.setInt(14, log.getLogCode());
            ps.setString(15, log.getLogText());
            ps.setString(16, log.getSourceName());
            ps.setString(17, log.getProvider());
            ps.setString(18, log.getLogMessage());
            ps.setString(19, log.getThunderheadRequestContent());
            rowCount = rowCount + ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return rowCount;
    }

    public HubLog findById(int id)
    throws SQLException
    {
        HubLog log = null;
        try(PreparedStatement ps = conn.prepareStatement(FIND_BY_ID))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                log = extractHublogFromResultSet(rs);
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return log;
    }

    public List<HubLog> getAllLogs()
    throws SQLException
    {
        List<HubLog> logs = new ArrayList<HubLog>();
        try(Statement st = conn.createStatement())
        {
            ResultSet rs = st.executeQuery(FIND_ALL_LOGS);
            while (rs.next())
            {
                logs.add(extractHublogFromResultSet(rs));
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return logs;
    }


    public HubLog extractHublogFromResultSet(ResultSet rs)
        throws SQLException
    {
        HubLog log = new HubLog();
        log.setRequestId(new Long(rs.getLong(1)));
        log.setMessageId(rs.getLong(2));
        log.setRequestServiceId(new Long(rs.getLong(3)));
        log.setDateOfLog(new Date(rs.getDate(4).getTime()));
        log.setBrand(rs.getString(5));
        log.setApplicationCode(rs.getString(6));
        log.setApplications(rs.getString(7));
        log.setRegion(rs.getString(8));
        log.setUserId(rs.getString(9));
        log.setRequestMessage(rs.getString(10));
        log.setServiceId(rs.getInt(11));
        log.setServiceName(rs.getString(12));
        log.setSeverity(rs.getString(13));
        log.setLogCode(rs.getInt(14));
        log.setLogText(rs.getString(15));
        log.setSourceName(rs.getString(16));
        log.setProvider(rs.getString(17));
        log.setLogMessage(rs.getString(18));
        log.setThunderheadRequestContent(rs.getString(19));

        return log;
    }



    private void printLog(HubLog log)
    {
        System.out.println("RequestID: " + log.getRequestId() + " ServiceID: " + log.getServiceId() + " Text: " + log.getLogText()
        + " Message: " + log.getLogMessage());
    }

}

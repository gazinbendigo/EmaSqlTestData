package main.java.au.com.babl.dao;

import main.java.au.com.babl.exception.SQLResourceException;
import main.java.au.com.babl.model.Application;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class ApplicationDAO implements Serializable, DAOProperties
{
    private static final long serialVersionUID = -6641107497778861985L;
    private static final String GET_APPLICATIONS = "SELECT APPLICATION_ID, APPLICATION_NME, APPLICATION_CDE, APPLICATION_TYPE_IND, APPLICATION_LOG_IND FROM APPLICATION";
    private static final String GET_APPLICATIONS_BY_CODE = "SELECT APPLICATION_ID, APPLICATION_NME, APPLICATION_CDE, APPLICATION_TYPE_IND, APPLICATION_LOG_IND FROM APPLICATION WHERE APPLICATION_CDE = ?";
    private static final String INSERT_APPLICATIONS = "INSERT INTO APPLICATION(APPLICATION_NME, APPLICATION_CDE, APPLICATION_TYPE_IND, APPLICATION_LOG_IND) VALUES(?,?,?,?)";
    private Connection conn;

    public ApplicationDAO(Connection connection)
    {
        conn = connection;
    }

    public void insertApplication(Application application)
    throws SQLException
    {
        try(PreparedStatement ps = conn.prepareStatement(INSERT_APPLICATIONS))
        {
            ps.setString(1, application.getApplicationName());
            ps.setString(2, application.getApplicationCode());
            ps.setString(3, application.getApplicationType());
            ps.setInt(4, application.getApplicationLogInd());
            try
            {
                ps.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
    }

    /**
     * Gets a list of Consumers or Applications whose Application Type Indicator is equal to C
     * @return A list of Applications
     * @throws SQLException
     */
    public List<Application> getApplications()
            throws SQLException
    {
        List<Application> applications = new ArrayList<Application>();
        try(Statement st = conn.createStatement())
        {
            try
            {
                ResultSet rs = st.executeQuery(GET_APPLICATIONS);
                while(rs.next())
                {
                    applications.add(extractApplicationFromResultSet(rs));
                }
            }
            catch (SQLException e)
            {
                throw new SQLResourceException(e, EXECUTE_QUERY_FAILED);
            }
        }
        catch (SQLException se)
        {
            System.out.println("failed to initialise Statement " + se.getMessage());
            throw new SQLResourceException(se, CREATE_STATEMENT_FAILED);
        }

        return applications;
    }

    public Application getApplicationByCode(String code)
        throws SQLException
    {
        Application application = null;
        try(PreparedStatement ps = conn.prepareStatement(GET_APPLICATIONS_BY_CODE))
        {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                application = extractApplicationFromResultSet(rs);
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        return application;
    }

    /**
     * Extracts and Application from the given ResultSet
     * @param rs
     * @return an Application is returned.
     * @throws SQLException
     */
    private Application extractApplicationFromResultSet(ResultSet rs)
            throws SQLException
    {
        Application application = new Application();
        application.setApplicationId(rs.getInt(1));
        application.setApplicationName(rs.getString(2));
        application.setApplicationCode(rs.getString(3));
        application.setApplicationType(rs.getString(4));
        application.setApplicationLogInd(rs.getInt(5));
        //app.setConsumerType(rs.getString(4));
        //application.setConsumerLogInd(rs.getBoolean(4));
        return application;
    }

}

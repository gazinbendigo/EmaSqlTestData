package main.java.au.com.babl.dao;

import main.java.au.com.babl.exception.SQLResourceException;
import main.java.au.com.babl.model.Consumer;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class ConsumerDAO implements Serializable, DAOProperties
{
    private static final long serialVersionUID = -6641107497778861985L;
    private static final String GET_CONSUMER_APPLICATIONS = "SELECT APPLICATION_ID, NAME, APPLICATION_NME FROM CONSUMER";
    private static final String GET_CONSUMER_BY_CODE = "SELECT ID, NAME, APPLICATION_NME FROM CONSUMER WHERE APPLICATION_CDE = ?";
    private static final String INSERT_CONSUMER = "INSERT INTO CONSUMER(APPLICATION_CDE, APPLICATION_NME) VALUES(?,?)";
    private Connection conn;

    public ConsumerDAO(Connection connection)
    {
        conn = connection;
    }

    public void insertConsumer(Consumer consumer)
    throws SQLException
    {
        try(PreparedStatement ps = conn.prepareStatement(INSERT_CONSUMER))
        {
            ps.setString(1, consumer.getConsumerCode());
            ps.setString(2, consumer.getConsumerName());
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
    public List<Consumer> getHubConsumers()
            throws SQLException
    {
        List<Consumer> consumers = new ArrayList<Consumer>();
        try(Statement st = conn.createStatement())
        {
            try
            {
                ResultSet rs = st.executeQuery(GET_CONSUMER_APPLICATIONS);
                while(rs.next())
                {
                    consumers.add(extractConsumerFromResultSet(rs));
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

        return consumers;
    }

    public Consumer getConsumerByCode(String code)
        throws SQLException
    {
        Consumer consumer = null;
        try(PreparedStatement ps = conn.prepareStatement(GET_CONSUMER_BY_CODE))
        {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                consumer = extractConsumerFromResultSet(rs);
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        return consumer;
    }

    /**
     * Extracts and Application from the given ResultSet
     * @param rs
     * @return an Application is returned.
     * @throws SQLException
     */
    private Consumer extractConsumerFromResultSet(ResultSet rs)
            throws SQLException
    {
        Consumer consumer = new Consumer();
        consumer.setConsumerId(rs.getInt(1));
        consumer.setConsumerName(rs.getString(2));
        consumer.setConsumerCode(rs.getString(3));
        //app.setConsumerType(rs.getString(4));
        //consumer.setConsumerLogInd(rs.getBoolean(4));
        return consumer;
    }

}

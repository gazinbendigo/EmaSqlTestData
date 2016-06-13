package main.java.au.com.babl.test;

import main.java.au.com.babl.manager.ConsumerManager;
import main.java.au.com.babl.model.Consumer;

import java.util.ArrayList;

/**
 * Created by holly on 08/06/2016.
 */
public class ConsumerDAOTestManager extends CommonDAOTestManager
{
    private static final String CREATE_TABLE = "CREATE TABLE CONSUMER (APPLICATION_ID MEDIUMINT NOT NULL AUTO_INCREMENT, APPLICATION_CDE VARCHAR(30) NOT NULL, APPLICATION_NME VARCHAR(60) NOT NULL,PRIMARY KEY (APPLICATION_ID))";
    private static final String DROP_TABLE = "DROP TABLE CONSUMER";
    private static final String DROP_PROCEDURE_GET_ALL_CONSUMERS = "DROP PROCEDURE pGetApplicationConsumers";
    private static final String CREATE_PROCEDURE_GET_ALL_CONSUMERS = "CREATE DEFINER=`root`@`localhost` PROCEDURE `pGetApplicationConsumers`() BEGIN SELECT APPLICATION_ID, APPLICATION_CDE, APPLICATION_NME FROM EMA_DEV.CONSUMER; END";
    private ArrayList<Consumer> consumers = new ArrayList<Consumer>();

    public ConsumerDAOTestManager(int id)
    {
        super(id);
    }

    @Override
    protected void refreshTableComponents()
    {
        executeStatement(DROP_TABLE);
        executeStatement(CREATE_TABLE);
        executeStatement(DROP_PROCEDURE_GET_ALL_CONSUMERS);
        executeStatement(CREATE_PROCEDURE_GET_ALL_CONSUMERS);
        generateTestData();
        insertTestData();
    }

    @Override
    protected void generateTestData()
    {
        String[] appCode = {"BDS", "BER", "BPM", "BOB", "BRF", "COMS", "CRS", "CTM", "DXWG", "EDML", "HUB", "IBS", "LAPS", "NFI"};
        String[] appName = {"Branch Delivery System", "Bendigo EBanking Re-imagined", "Business Process Management", "Business Offline Batches",
                "Branch Registration Form", "Communications Online Management System", "Customer Registration System",
                "Control-M", "DX Web Gateway", "ED Letters", "HUB", "Internet Banking", "Loan Application Processing System",  "Unknown" };
        for(int i = 0; i < appCode.length; i++)
        {
            consumers.add(new Consumer(appCode[i], appName[i]));
        }

    }

    @Override
    protected void insertTestData()
    {
        ConsumerManager manager = new ConsumerManager(getDbConnection());
        for (Consumer consumer: consumers)
        {
            manager.insertConsumer(consumer);
        }

    }

    @Override
    public void createTable()
    {
        executeStatement(DROP_TABLE);
        executeStatement(CREATE_TABLE);
    }
}

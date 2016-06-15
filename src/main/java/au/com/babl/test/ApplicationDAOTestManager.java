package main.java.au.com.babl.test;

import main.java.au.com.babl.manager.ApplicationManager;
import main.java.au.com.babl.model.Application;

import java.util.ArrayList;

/**
 * Created by holly on 08/06/2016.
 */
public class ApplicationDAOTestManager extends CommonDAOTestManager
{
    private static final String CREATE_TABLE = "CREATE TABLE APPLICATION (APPLICATION_ID MEDIUMINT NOT NULL AUTO_INCREMENT, APPLICATION_NME VARCHAR(60) NOT NULL, APPLICATION_CDE VARCHAR(30) NOT NULL, APPLICATION_TYPE_IND VARCHAR(1) NOT NULL, APPLICATION_LOG_IND INT NOT NULL, PRIMARY KEY (APPLICATION_ID))";
    private static final String DROP_TABLE = "DROP TABLE APPLICATION";
    private static final String DROP_PROCEDURE_GET_ALL_CONSUMERS = "DROP PROCEDURE pGetApplicationConsumers";
    private static final String CREATE_PROCEDURE_GET_ALL_CONSUMERS = "CREATE DEFINER=`root`@`localhost` PROCEDURE `pGetApplicationConsumers`() BEGIN SELECT APPLICATION_ID, APPLICATION_NME, APPLICATION_CDE, APPLICATION_LOG_IND FROM APPLICATION WHERE APPLICATION_TYPE_IND = 'C'; END";
    private ArrayList<Application> applications = new ArrayList<Application>();

    public ApplicationDAOTestManager(int id)
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
        String[] appCode = {"BDS", "BER", "BPM", "BOB", "BRF", "COMS", "CRS", "CTM", "DXWG", "EDML", "HUB", "IBS", "LAPS", "NFI", "RFS", "CAD"};
        String[] appName = {"Branch Delivery System", "Bendigo EBanking Re-imagined", "Business Process Management", "Business Offline Batches",
                "Branch Registration Form", "Communications Online Management System", "Customer Registration System",
                "Control-M", "DX Web Gateway", "ED Letters", "HUB", "Internet Banking", "Loan Application Processing System",  "Unknown", "Retail Finance System", "Cadencie" };
        for(int i = 0; i < appCode.length; i++)
        {
            String c = ((i + 1) > 14) ? "P":"C";
            Application app = new Application();
            app.setApplicationName(appName[i]);
            app.setApplicationCode(appCode[i]);
            app.setApplicationType(c);
            app.setApplicationLogInd(1);
            applications.add(app);
        }

    }

    @Override
    protected void insertTestData()
    {
        ApplicationManager manager = new ApplicationManager(getDbConnection());
        for (Application application : applications)
        {
            manager.insertConsumer(application);
        }

    }

    @Override
    public void createTable()
    {
        executeStatement(DROP_TABLE);
        executeStatement(CREATE_TABLE);
    }
}

package main.java.au.com.babl.test;

/**
 * Created by holly on 08/06/2016.
 */
public class DAOTestSuite
{
    private ApplicationDAOTestManager consumerTestManager;
    private EnvironmentDAOTestManager environmentTestManager;
    private HubLogsDAOTestManager hubLogsTestManager;
    private int region = 0;

    public DAOTestSuite()
    {

    }

    public DAOTestSuite(int id)
    {
        region = id == 0 ? 0:id;
        consumerTestManager = new ApplicationDAOTestManager(id);
        environmentTestManager = new EnvironmentDAOTestManager(id);
        hubLogsTestManager = new HubLogsDAOTestManager(id);
    }



    private void refreshAllTables()
    {
        System.out.println("****************  Hub Environment: " + CommonDAOTestManager.ENVIRONMENTS[region] + "  ****************");
        consumerTestManager.refreshTableComponents();
        System.out.println("Application Table Done.");
        environmentTestManager.refreshTableComponents();
        System.out.println("Environment Table Done.");
        hubLogsTestManager.refreshTableComponents();
        System.out.println("Hublogs Table Done.");
        System.out.println("****************  Hub Environment: " + CommonDAOTestManager.ENVIRONMENTS[region] + " Complete.  ****************");
    }

    private void createAllTables()
    {
        consumerTestManager.createTable();
        environmentTestManager.createTable();
        hubLogsTestManager.createTable();
    }


    private static void refreshAllDatabaseTables()
    {
        for(int i = 0; i < CommonDAOTestManager.ENVIRONMENTS.length; i++)
        {
            DAOTestSuite suite = new DAOTestSuite(i);
            suite.refreshAllTables();
        }
    }

    private static void createAllDatabaseTables()
    {
        for(int i = 0; i < CommonDAOTestManager.ENVIRONMENTS.length; i++)
        {
            DAOTestSuite suite = new DAOTestSuite(i);
            suite.createAllTables();
        }
    }

    public static void main(String[] args)
    {
//        DAOTestSuite.createAllDatabaseTables();
        DAOTestSuite.refreshAllDatabaseTables();
//        DAOTestSuite suite = new DAOTestSuite(0);
//        suite.createAllDatabaseTables();
//        suite.refreshAllTables();
        //suite.refreshAllDatabases();
    }

}

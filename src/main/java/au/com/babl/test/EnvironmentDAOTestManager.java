package main.java.au.com.babl.test;

import main.java.au.com.babl.manager.EnvironmentManager;
import main.java.au.com.babl.model.Environment;

import java.util.ArrayList;

/**
 * Created by holly on 08/06/2016.
 */
public class EnvironmentDAOTestManager extends CommonDAOTestManager
{
    private static final String CREATE_TABLE = "CREATE TABLE ENVIRONMENT (ENV_ID MEDIUMINT NOT NULL AUTO_INCREMENT, ENV_NAME VARCHAR(50) NOT NULL, ENV_DESCRIPTION VARCHAR(60) NOT NULL,PRIMARY KEY (ENV_ID))";
    private static final String DROP_TABLE = "DROP TABLE ENVIRONMENT";
    private static final String DROP_PROCEDURE_GET_ALL_ENVIRONMENTS = "DROP PROCEDURE pGetAllEnvironments";
    private static final String CREATE_PROCEDURE_GET_ALL_ENVIRONMENTS = "CREATE DEFINER=`root`@`localhost` PROCEDURE `pGetAllEnvironments` () BEGIN SELECT ENV_ID, ENV_NAME, ENV_DESCRIPTION FROM ENVIRONMENT; END";
    private ArrayList<Environment> environments = new ArrayList<Environment>();


    public EnvironmentDAOTestManager(int id)
    {
        super(id);

    }

    @Override
    protected void refreshTableComponents()
    {
        executeStatement(DROP_TABLE);
        executeStatement(CREATE_TABLE);
        executeStatement(DROP_PROCEDURE_GET_ALL_ENVIRONMENTS);
        executeStatement(CREATE_PROCEDURE_GET_ALL_ENVIRONMENTS);
        generateTestData();
        insertTestData();
    }

    @Override
    protected void generateTestData()
    {
        String[] environmentNames = {"HUBDL", "HUBA1", "HUBA2", "HUBB1", "HUBB2", "HUBC1", "HUBC2", "HUBD1", "HUBD2", "HUBF1", "HUBG1", "HUBG2", "HUBI1", "HUBI2", "HUBJ1"};
        String[] descriptions = {"Development", "Development-old", "Development2", "System Test", "System Test2", "System Integration", "System Integration2", "UAT", "UAT2", "Stressed and Volumed", "Tan", "Red", "Gold", "Bob", "Lime", };
        for(int i = 0; i < environmentNames.length; i++)
        {
            environments.add(createEnvironment((i + 1), environmentNames[i], descriptions[i]));
        }
    }

    private Environment createEnvironment(int id, String code, String description)
    {
        return new Environment(new Long(id), code, description);
    }

    @Override
    protected void insertTestData()
    {
        EnvironmentManager manager = new EnvironmentManager(getDbConnection());
        for (Environment env: environments)
        {
            manager.insertEnvironment(env);
        }
    }

    @Override
    protected void createTable()
    {
        executeStatement(DROP_TABLE);
        executeStatement(CREATE_TABLE);
    }
}

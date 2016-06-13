package main.java.au.com.babl.test;

import main.java.au.com.babl.dao.HubLogDAO;
import main.java.au.com.babl.manager.HublogsManager;
import main.java.au.com.babl.model.HubLog;
import main.java.au.com.babl.model.LogText;
import main.java.au.com.babl.util.DateUtils;
import main.java.au.com.babl.util.NumericUtils;
import main.java.au.com.babl.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by holly on 06/06/2016.
 */
public class HubLogsDAOTestManager extends CommonDAOTestManager
{
    private Map<Integer, Integer> serviceIds = new HashMap<Integer, Integer>();
    private int serviceIdsSize = 0;
    private ArrayList<LogText> requests = new ArrayList<LogText>();
    private ArrayList<LogText> responses = new ArrayList<LogText>();
    private ArrayList<LogText> exceptions = new ArrayList<LogText>();
    private static final String REQUEST = "request";
    private static final String RESPONSE = "response";
    private static final String EXCEPTION = "exception";
    private HubLogDAO dao;
    private HublogsManager manager;
    private ArrayList<HubLog> hubLogs = new ArrayList<HubLog>();
    private int numberOfRowsToCreate = 15001;
    private static final String CREATE_HUB_LOG_TABLE = "CREATE TABLE HUBLOG (RowNum MEDIUMINT NOT NULL AUTO_INCREMENT, REQUEST_ID BIGINT, " +
            "MESSAGE_ID BIGINT, REQUEST_SERVICE_ID BIGINT, REQUEST_DTE DATE, BRAND_NME VARCHAR(50), " +
            "APPLICATION_CDE VARCHAR(6), APPLICATION_NME VARCHAR(50), INSTANCE_NME VARCHAR(50), USER_ID VARCHAR(50), MESSAGE_TXT VARCHAR(2000), " +
            "SERVICE_ID INTEGER, SERVICE_NME VARCHAR(100), SEVERITY_CDE VARCHAR(1), LOG_CODE INTEGER, LOG_TXT VARCHAR(2000), " +
            "SOURCE_NAME VARCHAR(30), PROVIDER VARCHAR(50), LOG_MESSAGE  VARCHAR(2000), THUNDERHEAD_CONTENT VARCHAR(2000), PRIMARY KEY (RowNum))";
    //MS SQL Server data types
    //REQUEST_ID bigint, MESSAGE_ID bigint, REQUEST_SERVICE_ID bigint, REQUEST_DTE datetime, BRAND_NME varchar(50)
    //APPLICATION_CDE varchar(6), APPLICATION_NME varchar(50), INSTANCE_NME varchar(50), USER_ID varchar(50)
    //MESSAGE_TXT text, SERVICE_ID smallInt, SERVICE_NME varchar(100), SEVERITY_CDE char(1), LOG_CDE int
    //LOG_TXT varchar(256), SOURCE_NME varchar(50), APPLICATION_NME varchar(50), MESSAGE_TXT

    private static final String DROP_HUBLOG_TABLE = "DROP TABLE HUBLOG";

    private static final String CREATE_SEARCH_HUBLOGS_PROCEDURE = "CREATE DEFINER=`root`@`localhost` PROCEDURE `pGetHubLogs`(in startIndex integer, resultSizeLimit integer," +
            "requestId integer, serviceId integer, sourceName varchar(50), userId varchar(10), severityCode varchar(1), logCode integer, requestDate DATE, applications varchar(600)," +
            "requestMessage varchar(500), logMessage varchar(500), errorsOnly bit, includeOlbPing bit) NO SQL select RowNum," +
            "REQUEST_ID, MESSAGE_ID, REQUEST_SERVICE_ID, REQUEST_DTE, BRAND_NME, APPLICATION_CDE, APPLICATION_NME,INSTANCE_NME," +
            "USER_ID, MESSAGE_TXT, SERVICE_ID, SERVICE_NME, SEVERITY_CDE, LOG_CODE, LOG_TXT, SOURCE_NAME, PROVIDER, LOG_MESSAGE, " +
            "THUNDERHEAD_CONTENT from Hublog where (requestId is null or REQUEST_ID = requestId) and (serviceId is null or SERVICE_ID = serviceId)" +
            "and (sourceName is null or SERVICE_NME = sourceName) and(requestDate is null or REQUEST_DTE = requestDate)" +
            "and(severityCode is null or SEVERITY_CDE = severityCode) and (applications is null or APPLICATION_CDE = applications) LiMIT resultSizeLimit";

    private static final String DROP_SEARCH_HUBLOGS_PROCEDURE = "DROP PROCEDURE pGetHubLogs";


    public HubLogsDAOTestManager(int environment)
    {
        super(environment);
        manager = new HublogsManager(getDbConnection());
    }

    @Override
    public void refreshTableComponents()
    {
        executeStatement(DROP_HUBLOG_TABLE);
        executeStatement(DROP_SEARCH_HUBLOGS_PROCEDURE);
        executeStatement(CREATE_HUB_LOG_TABLE);
        executeStatement(CREATE_SEARCH_HUBLOGS_PROCEDURE);
//        System.out.println("Generating Test Data.");
        generateTestData();
//        System.out.println("Inserting HubLogs Test Data.");
        insertTestData();
//        System.out.println("HubLogs Components Built.");
    }


    private void createServiceIds()
    {
        for(int i = 1; i < 501; i++)
        {
            int value = NumericUtils.createRandomNumber(600);
            int key = value;
            serviceIds.put(key, value);
        }
        serviceIdsSize = serviceIds.size();
    }

    private void buildLogMessages(List<LogText> list, String filter)
    {
        String workingDir = System.getProperty("user.dir");
        Path path = Paths.get(workingDir);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path file: stream)
            {
                if(file.getFileName().toString().startsWith(filter))
                {
                    BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
                    StringBuilder fileContents = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        fileContents.append(line);
                    }
                    LogText text = new LogText(fileContents.toString());
                    list.add(text);
                }

            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }
    }

    public void generateTestData()
    {
        if(requests.size() == 0)
        {
            buildLogMessages(requests, REQUEST);
            buildLogMessages(responses, RESPONSE);
            buildLogMessages(exceptions, EXCEPTION);
        }
        createServiceIds();
        int totalRecords = getNumberOfRows();
        int index = Integer.valueOf(StringUtils.generateRandomNumericString(10));
        String requestId = "";
        String reqServiceId = "";
        int serviceId = 0;
        String requestMessage = "";
        String logMessage = "";
        String[] logTexts = {"Response Received", "Logging Web Response", "Response Sent from Database", "Response Sent from Mainframe", "Logging Response"};
        String[] brands = {"Bank North", "Bank South", "Bank East", "Bank West"};
        String[] appCodes = {"BEN", "BDS", "ADS", "CTM", "BRF", "HUB", "LVO", "NFI", "MPW", "PETS", "PBS", "REDY", "RFS", "SQM"};
        String appCode = "";
        String brand = "";
        String userId = "";
        String appName = "";
        String logText = "";
        int logCode = 0;
        String[] serviceNames = {"Service Entry", "Customer Info", "Loan Statement", "Fee", "Home loan", "Boing", "Financial Transaction", "Secure Transaction", "Credit Enquiry", "Teller Override"};
        String serviceName = "";
        Date logDate = null;
        for(int i = 0; i < totalRecords; i++)
        {
            HubLog log = new HubLog();
            if (i % index == 0) {
                requestId = StringUtils.generateRandomNumericString(99999999);
                reqServiceId = StringUtils.generateRandomNumericString(999999);
                Integer servId = new Integer(NumericUtils.createRandomNumber(serviceIds.size()));
                serviceId = servId != null ? servId.intValue(): 10;
                brand = brands[(NumericUtils.createRandomNumber(brands.length) -1)];
                appCode = appCodes[(NumericUtils.createRandomNumber(appCodes.length)-1)];
                appName = appCode;
                int messageIndex = NumericUtils.createRandomNumber(requests.size() -1);
                requestMessage = extractMessageFromList(requests, messageIndex);
                logText = logTexts[(NumericUtils.createRandomNumber(logTexts.length) -1)];
                logMessage = extractMessageFromList(responses, messageIndex);
                logCode = Integer.valueOf(StringUtils.generateRandomNumericString(9999999)) + i;
                userId = "adw" + StringUtils.generateRandomNumericString(999);
                logDate = new Date(DateUtils.createFutureDate(i));
                serviceName = serviceNames[(NumericUtils.createRandomNumber(serviceNames.length) -1)];
            }

            log.setRequestId(new Long(requestId));
            log.setMessageId(new Long(StringUtils.generateRandomNumericString(999)));
            log.setRequestServiceId(new Long(reqServiceId));
            log.setDateOfLog(logDate);
            log.setBrand(brand);
            log.setApplicationCode(appCode);
            log.setApplications(appName);
            log.setRegion(getEnvironment());
            log.setUserId(userId);
            log.setRequestMessage(requestMessage);
            log.setServiceId(serviceId);
            log.setServiceName(serviceName);
            log.setSeverity("I");
            log.setLogCode(logCode);
            log.setLogText(logText);
            log.setSourceName("Localhost");
            log.setProvider("Localhost");
            if(index % 4 == 0)
            {
                log.setLogMessage(extractMessageFromList(exceptions, NumericUtils.createRandomNumber(exceptions.size() -1)));
                log.setSeverity("E");
                log.setApplicationCode(appCodes[7]);
                log.setServiceId(0);
            }
            else
            {
                log.setLogMessage(logMessage);
            }
            log.setThunderheadRequestContent("Request Message " + (i + 1));
            hubLogs.add(log);

        }
    }

    protected void insertTestData()
    {
        HublogsManager manager = new HublogsManager(getDbConnection());
        for (HubLog log: hubLogs)
        {
            manager.insertHubLog(log);
        }

    }

    private String extractMessageFromList(List<LogText> list, int index)
    {
        LogText text = list.get(index);
        return text.getText();
    }

    public void setNumberOfRows(int rows)
    {
        numberOfRowsToCreate = rows;
    }

    public int getNumberOfRows()
    {
        return numberOfRowsToCreate;
    }

    @Override
    public void createTable()
    {
        executeStatement(DROP_HUBLOG_TABLE);
        executeStatement(CREATE_HUB_LOG_TABLE);
    }

    private String getRegion()
    {
        return getEnvironment();
    }

}

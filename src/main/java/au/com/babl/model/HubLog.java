package main.java.au.com.babl.model;

import java.util.Date;

/**
 * Created by holly on 12/11/2015.
 */
public class HubLog
{

    private static final long serialVersionUID = -1955972674391884381L;
    private long requestId;
    private long messageId;
    private long requestServiceId;
    private Date dateOfLog;
    private String brand;
    private String applicationCode;
    private String applicationName;
    private String region;
    private String userId;
    private String requestMessage;
    private int serviceId;
    private String serviceName;
    private String severity;
    private int logCode;
    private String logText;
    private String sourceName;
    private String provider;	//dbo.APPLICATION.APPLICATION_NME
    private String logMessage; 	//dbo.MESSAGE.MESSAGE_TXT
    private boolean errorsOnly;
    private boolean includeOlbPing;
    private String thunderheadRequestContent;
    private String apps;


    public HubLog()
    {
        requestId = 0;
        messageId = 0;
        requestServiceId = 0;
        dateOfLog = new Date();
        brand = "";
        applicationCode = "";
        applicationName = "";
        region = "";
        userId = "";
        requestMessage = "";
        serviceId = 0;
        serviceName = "";
        severity = "";
        logCode = 0;
        logText = "";
        sourceName = "";
        provider = "";
        logMessage = "";
        errorsOnly = false;
        includeOlbPing = false;
        thunderheadRequestContent = "";
        apps = "";
    }


    public HubLog(int start, int resultSetSize, int logId, long requestId, long messageId, long requestServiceId, Date dateOfLog,
                  String brand, String applicationCode, String applicationName, String region, String userId, String requestMessage,
                  int serviceId, String serviceName, String severity, int logCode, String logText, String sourceName,
                  String provider, String logMessage, boolean errorsOnly, boolean includeOlbPing, String thunderheadRequestContent,
                  String apps)
    {
        this.requestId = requestId;
        this.messageId = messageId;
        this.requestServiceId = requestServiceId;
        this.dateOfLog = dateOfLog;
        this.brand = brand;
        this.applicationCode = applicationCode;
        this.applicationName = applicationName;
        this.region = region;
        this.userId = userId;
        this.requestMessage = requestMessage;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.severity = severity;
        this.logCode = logCode;
        this.logText = logText;
        this.sourceName = sourceName;
        this.provider = provider;
        this.logMessage = logMessage;
        this.errorsOnly = errorsOnly;
        this.includeOlbPing = includeOlbPing;
        this.thunderheadRequestContent = thunderheadRequestContent;
        this.apps = apps;
    }

    public String getApplicationName()
    {
        return applicationName;
    }

    public HubLog setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
        return this;
    }


    public boolean getErrorsOnly()
    {
        return errorsOnly;
    }

    public void setErrorsOnly(boolean errorsOnly)
    {
        this.errorsOnly = errorsOnly;
    }

    public boolean getIncludeOlbPing()
    {
        return includeOlbPing;
    }

    public void setIncludeOlbPing(boolean includeOlbPing)
    {
        this.includeOlbPing = includeOlbPing;
    }

    public String getApps()
    {
        return apps;
    }

    public void setApps(String apps)
    {
        this.apps = apps;
    }

    public long getRequestId()
    {
        return requestId;
    }

    public void setRequestId(long requestId)
    {
        this.requestId = requestId;
    }

    public long getMessageId()
    {
        return messageId;
    }

    public void setMessageId(long messageId)
    {
        this.messageId = messageId;
    }

    public long getRequestServiceId()
    {
        return requestServiceId;
    }

    public void setRequestServiceId(long requestServiceId)
    {
        this.requestServiceId = requestServiceId;
    }

    public Date getDateOfLog()
    {
        return dateOfLog;
    }

    public void setDateOfLog(Date dateOfLog)
    {
        this.dateOfLog = dateOfLog;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getApplicationCode()
    {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode)
    {
        this.applicationCode = applicationCode;
    }

    public String getApplications()
    {
        return applicationName;
    }

    public void setApplications(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getRequestMessage()
    {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage)
    {
        this.requestMessage = requestMessage;
    }

    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public int getLogCode()
    {
        return logCode;
    }

    public void setLogCode(int logCode)
    {
        this.logCode = logCode;
    }

    public String getLogText()
    {
        return logText;
    }

    public void setLogText(String logText)
    {
        this.logText = logText;
    }

    public String getSourceName()
    {
        return sourceName;
    }

    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
    }

    public String getProvider()
    {
        return provider;
    }

    public void setProvider(String provider)
    {
        this.provider = provider;
    }

    public String getLogMessage()
    {
        return logMessage;
    }

    public void setLogMessage(String logMessage)
    {
        this.logMessage = logMessage;
    }

    public String getThunderheadRequestContent()
    {
        return thunderheadRequestContent;
    }

    public void setThunderheadRequestContent(String thunderheadRequestContent)
    {
        this.thunderheadRequestContent = thunderheadRequestContent;
    }

}

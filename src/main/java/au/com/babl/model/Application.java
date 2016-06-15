package main.java.au.com.babl.model;

import java.io.Serializable;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class Application implements Serializable
{
    private static final long serialVersionUID = 2000240808221585714L;
    private int applicationId;
    private String applicationCode;
    private String applicationName;
    private String applicationType;
    private int applicationLogInd;

    public Application()
    {
        applicationCode = "";
        applicationName = "";
        applicationType = "";
        applicationLogInd = 1;
    }

    public Application(int appId, String name, String code, String type, int logInd)
    {
        applicationId = appId;
        applicationName = name;
        applicationCode = code;
        applicationType = type;
        applicationLogInd = logInd;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String type) {
        this.applicationType = type;
    }

    public int getApplicationLogInd() {
        return applicationLogInd;
    }

    public void setApplicationLogInd(int applicationLogInd) {
        this.applicationLogInd = applicationLogInd;
    }


}

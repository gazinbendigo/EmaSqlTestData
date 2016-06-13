package main.java.au.com.babl.model;

import java.io.Serializable;

/**
 * Created by adm9360 on 13/11/2015.
 */
public class Consumer implements Serializable
{
    private static final long serialVersionUID = 2000240808221585714L;
    private int consumerId;
    private String consumerName;
    private String consumerCode;
    private String consumerType;
    private boolean consumerLogInd;
    private String consumerLabelName;

    public Consumer()
    {

    }

    public Consumer(String code, String name)
    {
        consumerCode = code;
        consumerName = name;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerCode() {
        return consumerCode;
    }

    public void setConsumerCode(String consumerCode) {
        this.consumerCode = consumerCode;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String type) {
        this.consumerType = type;
    }

    public boolean isConsumerLogInd() {
        return consumerLogInd;
    }

    public void setConsumerLogInd(boolean consumerLogInd) {
        this.consumerLogInd = consumerLogInd;
    }


    public String getConsumerLabelName() {
        return consumerLabelName;
    }

    public void setConsumerLabelName(String consumerLabelName) {
        this.consumerLabelName = consumerLabelName;
    }
}

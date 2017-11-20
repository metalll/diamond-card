package com.nsd.diamondcard.Model.Activity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Activity {

    public static final String ACTVITY_TYPE_CASHBACK = "ACTVITY_TYPE_CASHBACK";
    public static final String ACTVITY_TYPE_BUY = "ACTVITY_TYPE_BUY";
    public static final String ACTIVITY_TYPE_REJECT = "ACTIVITY_TYPE_REJECT";
    public static final String ACTIVITY_TYPE_GET_ALL_CASH = "ACTIVITY_TYPE_GET_ALL_CASH" ;
    //from
    @DatabaseField(generatedId = true)
    private long activityId;

    @DatabaseField
    private long initiatorId;
    //to
    @DatabaseField
    private long targetId;

    @DatabaseField
    private String type;

    @DatabaseField
    private String operationValue;

    @DatabaseField
    private String data;

    @DatabaseField
    private String endData;

    @DatabaseField
    private boolean isActiveOperation;

    @DatabaseField
    private boolean isSuccessComplete;

    @DatabaseField
    private String preparedTagetOperationValue;




    public Activity() {
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(long initiatorId) {
        this.initiatorId = initiatorId;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(String operationValue) {
        this.operationValue = operationValue;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public boolean isActiveOperation() {
        return isActiveOperation;
    }

    public void setActiveOperation(boolean activeOperation) {
        isActiveOperation = activeOperation;
    }

    public boolean isSuccessComplete() {
        return isSuccessComplete;
    }

    public void setSuccessComplete(boolean successComplete) {
        isSuccessComplete = successComplete;
    }

    public String getPreparedTagetOperationValue() {
        return preparedTagetOperationValue;
    }

    public void setPreparedTagetOperationValue(String preparedTagetOperationValue) {
        this.preparedTagetOperationValue = preparedTagetOperationValue;
    }
}

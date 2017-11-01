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

}

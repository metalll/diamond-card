package com.nsd.diamondcard.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.nsd.diamondcard.Utils.Constants.USERS_TABLE_NAME;


@DatabaseTable
public class User {

    public User() {
    }
    // column constant declaration

    public static final String COLUMN_EMAIL = "EMAIL";

    private static final String COLUMN_ID = "USER_ID";
    private static final String COLUMN_PASSWD = "PASSWD";
    private static final String COLUMN_BILLING_CARD_NUM = "BILLING_CARD";
    public static final String COLUMN_UUID_CASHBACK_CARD = "CASHBACK_CARD";

    // declaration entity var

    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    private long userID;

    @DatabaseField(columnName = COLUMN_EMAIL, unique = true)
    private String email;


    @DatabaseField(columnName = COLUMN_PASSWD)
    private String passwd;

    @DatabaseField(columnName = COLUMN_BILLING_CARD_NUM)
    private String billingCardNum;

    @DatabaseField(columnName = COLUMN_UUID_CASHBACK_CARD)
    private String cashbackCardNumber;

    public long getUserID() {
        return userID;
    }

    private void setUserID(long userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getBillingCardNum() {
        return billingCardNum;
    }

    public void setBillingCardNum(String billingCardNum) {
        this.billingCardNum = billingCardNum;
    }

    public String getCashbackCardNumber() {
        return cashbackCardNumber;
    }

    public void setCashbackCardNumber(String cashbackCardNumber) {
        this.cashbackCardNumber = cashbackCardNumber;
    }

}
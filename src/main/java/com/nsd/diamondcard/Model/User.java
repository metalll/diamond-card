package com.nsd.diamondcard.Model;

import javax.persistence.*;

import java.io.Serializable;

import static com.nsd.diamondcard.Utils.Constants.USERS_TABLE_NAME;


@Entity
@Table(name = USERS_TABLE_NAME)
public class User implements Serializable{

    // column constant declaration

    private static final String COLUMN_ID = "USER_ID";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_PASSWD = "PASSWD";
    private static final String COLUMN_BILLING_CARD_NUM = "BILLING_CARD";
    private static final String COLUMN_UUID_CASHBACK_CARD = "CASHBACK_CARD";

    //serializable var

    private static final long serialVersionUID = 1L;

    // declaration entity var

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID)
    private long userID;

    @Column(name = COLUMN_EMAIL)
    private String email;

    @Column(name = COLUMN_PASSWD)
    private String passwd;

    @Column(name = COLUMN_BILLING_CARD_NUM)
    private String billingCardNum;

    @Column(name = COLUMN_UUID_CASHBACK_CARD)
    private String cashbackCardNumber;



}
package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.BillingInfo;

import java.util.List;

public interface DBBillingInfo {

    void createBillingInfo(BillingInfo billingInfo);
    void updateBillingInfo(BillingInfo billingInfo);
    void removeBillingInfo(BillingInfo billingInfo);

    List<BillingInfo> getBillingInfosWithForeign(long foreignId);
    BillingInfo getBillingInfo(long id);
    List<BillingInfo> getAllBillingInfos();

}

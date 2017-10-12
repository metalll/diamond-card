package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.BuyerTarget;

import java.util.List;

public interface DBBuyerTarget {
    void createBuyerTarget(BuyerTarget BuyerTarget);
    void updateBuyerTarget(BuyerTarget BuyerTarget);
    void removeBuyerTarget(BuyerTarget BuyerTarget);
    BuyerTarget getBuyerTarget(String login);
    BuyerTarget getBuyerTarget(long id);
    List<BuyerTarget> getAllBuyerTargets();
    void validateBuyerTarget(BuyerTarget BuyerTarget);

}

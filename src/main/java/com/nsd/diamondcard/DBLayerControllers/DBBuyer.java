package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.Buyer;

import java.util.List;

public interface DBBuyer {
    void createBuyer(Buyer Buyer);
    void updateBuyer(Buyer Buyer);
    void removeBuyer(Buyer Buyer);
    Buyer getBuyer(String login);
    Buyer getBuyerWithForeign(long foreignId);
    Buyer getBuyer(long id);
    List<Buyer> getAllBuyers();
    void validateBuyer(Buyer Buyer);
}

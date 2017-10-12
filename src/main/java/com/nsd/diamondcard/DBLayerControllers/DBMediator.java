package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.Mediator;

import java.util.List;

public interface DBMediator {

    void createMediator(Mediator Mediator);
    void updateMediator(Mediator Mediator);
    void removeMediator(Mediator Mediator);
    Mediator getMediator(String login);
    Mediator getMediator(long id);
    List<Mediator> getAllMediators();
    void validateMediator(Mediator Mediator);
}

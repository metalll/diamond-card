package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.Activity.Activity;

import java.util.List;

public interface DBActivity {
    void createActivity(Activity Activity);
    void updateActivity(Activity Activity);
    void removeActivity(Activity Activity);
    Activity getActivity(long id);
    List<Activity> getAllActivitys();
    void validateActivity(Activity Activity);
}

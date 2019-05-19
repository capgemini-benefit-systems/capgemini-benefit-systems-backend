package com.app.model.dao;

import com.app.model.Activity;
import com.app.model.dao.generic.GenericDao;

import java.util.List;

public interface ActivityDao extends GenericDao<Activity> {

    String getPhotoPathByActivityId(Long id);
    List<Activity> getActivitiesByProjectId(Long id);
    List<Activity> getFinishedActivitiesByUserId(Long id, boolean finished);

}
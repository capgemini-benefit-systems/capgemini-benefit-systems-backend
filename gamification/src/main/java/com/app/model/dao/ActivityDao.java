package com.app.model.dao;

import com.app.model.Activity;
import com.app.model.dao.generic.GenericDao;

import java.util.List;

public interface ActivityDao extends GenericDao<Activity> {

    public String getPhotoPathByActivityId(Long id);
    public List<Activity> getActivitiesByProjectId(Long id);

}
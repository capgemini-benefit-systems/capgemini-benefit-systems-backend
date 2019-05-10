package com.app.model.dao;

import com.app.model.Activity;
import com.app.model.ActivityResult;
import com.app.model.User;
import com.app.model.dao.generic.GenericDao;

import java.util.List;

public interface ActivityResultDao extends GenericDao<ActivityResult> {

   ActivityResult getActivityResultByActivity(Long id);
   List<Activity> getActivitiesByUserId(Long id);

}
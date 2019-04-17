package com.app.model.dao;

import com.app.model.ActivityResult;
import com.app.model.dao.generic.GenericDao;

public interface ActivityResultDao extends GenericDao<ActivityResult> {

   public ActivityResult getActivityResultByActivity(Long id);

}
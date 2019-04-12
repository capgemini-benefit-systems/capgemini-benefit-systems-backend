package com.app.controller;

import com.app.model.Activity;
import com.app.model.Project;
import com.app.model.dao.ActivityDao;
import com.app.model.dao.ProjectDao;
import com.app.model.dao.ProjectMembersDaoImpl;
import com.app.model.dao.UserDao;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityDao activityDao;
    private final UserDao userDao;

    @Autowired
    public ActivityController(ActivityDao activityDao, UserDao userDao) {
        this.activityDao = activityDao;
        this.userDao = userDao;
    }

    @PostMapping("/add")
    public void addActivityPost(Activity activity){
        activityDao.insert(activity);
    }

    @RequestMapping("/all")
    public List<Activity> findAll(){
        return activityDao.findAll();
    }

    @RequestMapping(value = "/{id}/photo",  method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {

        var imgFile = new ClassPathResource(activityDao.getPhotoPathByActivityId(id));
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @GetMapping("/{id}/activities")
    public List<Activity> getActivitiesByProjectId(@PathVariable Long id){
        return activityDao.getActivitiesByProjectId(id);
    }

}


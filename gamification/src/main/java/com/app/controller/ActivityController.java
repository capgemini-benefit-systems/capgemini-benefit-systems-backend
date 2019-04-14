package com.app.controller;

import com.app.model.Activity;
import com.app.model.Project;
import com.app.model.dao.ActivityDao;
import com.app.model.dao.ProjectDao;
import com.app.model.Project;
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
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityDao activityDao;
    private final UserDao userDao;
    private final ProjectDao projectDao;

    @Autowired
    public ActivityController(ActivityDao activityDao, UserDao userDao, ProjectDao projectDao) {
        this.activityDao = activityDao;
        this.userDao = userDao;
        this.projectDao=projectDao;
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

    @GetMapping("/addSamples")
    public String addSampleActivities() {
        List<Activity> activities = createSampleActivities();
        activities.forEach(activityDao::insert);
        return "{\"message\": \"samples-added\"}";
    }


    private List<Activity> createSampleActivities() {
        Project p1 = projectDao.findById(7l).orElseThrow(NullPointerException::new);
        Project p2 = projectDao.findById(8l).orElseThrow(NullPointerException::new);



        Activity a1 = new Activity(null, 100l, "wyklad", "Wyklad o Javie","Litwo ojczyzno moja", "image/kon.jpg",
                null, null, 15l, 10l, p1,null);
        Activity a2 = new Activity(null, 100l, "wyklad", "Wyklad o Python","Litwo ojczyzno moja", "image/kon.jpg",
                null, null, 15l, 10l, p1,null);
        Activity a3 = new Activity(null, 100l, "wyklad", "Wyklad o C#","Litwo ojczyzno moja", "image/kon.jpg",
                null, null, 15l, 10l, p2,null);
        return Arrays.asList(a1, a2, a3);
    }

}


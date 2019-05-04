package com.app.controller;

import com.app.model.*;
import com.app.model.dao.*;
import com.app.model.Project;
import com.app.model.dto.ActivityDto;
import com.app.model.dto.ActivityResultDto;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityDao activityDao;
    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ActivityResultDao activityResultDao;

    @Autowired
    public ActivityController(ActivityDao activityDao, UserDao userDao, ProjectDao projectDao, ActivityResultDao activityResultDao) {
        this.activityDao = activityDao;
        this.userDao = userDao;
        this.projectDao=projectDao;
        this.activityResultDao=activityResultDao;
    }

    @PostMapping("/add")
    public ActivityDto addActivityPost(@RequestBody ActivityDto activityDto){
        Activity activity = ActivityDto.getActivityByActivityDto(activityDto);
        return ActivityDto.getActivityDtoByActivity(activityDao.insert(activity));
    }

    @GetMapping("/all")
    public List<ActivityDto> findAll(){
        List<Activity> activities = activityDao.findAll();
        return activities
                .stream()
                .map(ActivityDto::getActivityDtoByActivity)
                .collect(Collectors.toList());
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
    public List<ActivityDto> getActivitiesByProjectId(@PathVariable Long id){
        List<Activity> activities = activityDao.getActivitiesByProjectId(id);
        return activities
                .stream()
                .map(ActivityDto::getActivityDtoByActivity)
                .collect(Collectors.toList());
    }

    @GetMapping("/addSamples")
    public String addSampleActivities() {
        List<Activity> activities = createSampleActivities();
        activities.forEach(activityDao::insert);
        return "{\"message\": \"samples-added\"}";
    }



    @GetMapping("/addSamplesActivityResults")
    public String addSampleActivityResults() {
        List<ActivityResult> activityResults = createSampleActivityResults();
        activityResults.forEach(activityResultDao::insert);
        return "{\"message\": \"samples-added\"}";
    }

    @PostMapping("/addActivityResult")
    public void addActivityResultPost(ActivityResultDto arDto){
        ActivityResult ar = ActivityResultDto.activityResultDtoToActivityResult(arDto);
        activityResultDao.insert(ar);
    }

    @GetMapping("/{id}/activityResult")
    public ActivityResultDto getActivityResultByActivityId(@PathVariable Long id){
        ActivityResult ar = activityResultDao.getActivityResultByActivity(id);
        return ActivityResultDto.activityResultToActivityResultDto(ar);
    }

    private List<Activity> createSampleActivities() {
        Project p1 = projectDao.findById(1l).orElseThrow(NullPointerException::new);
        Project p2 = projectDao.findById(2l).orElseThrow(NullPointerException::new);

        Activity a1 = new Activity(null, 100l, "wyklad", "Wyklad o Javie","Litwo ojczyzno moja", "image/kon.jpg",
                null, null, 15l, 10l, p1,null);
        Activity a2 = new Activity(null, 100l, "wyklad", "Wyklad o Python","Litwo ojczyzno moja", "image/kon.jpg",
                null, null, 15l, 10l, p1,null);
        Activity a3 = new Activity(null, 100l, "wyklad", "Wyklad o C#","Litwo ojczyzno moja", "image/kon.jpg",
                null, null, 15l, 10l, p2,null);
        return Arrays.asList(a1, a2, a3);
    }

    private List<ActivityResult> createSampleActivityResults(){

        Activity a1 = activityDao.findById(33l).orElseThrow(NullPointerException::new);
        Activity a2= activityDao.findById(34l).orElseThrow(NullPointerException::new);
        User u1=userDao.findById(1l).orElseThrow(NullPointerException::new);

        ActivityResultId activityResultId1= new ActivityResultId(a1.getId(),u1.getId());
        ActivityResultId activityResultId2= new ActivityResultId(a2.getId(),u1.getId());

        ActivityResult ar1=new ActivityResult(activityResultId1,a1,u1, null);
        ActivityResult ar2=new ActivityResult(activityResultId2,a2,u1,null);

        return Arrays.asList(ar1,ar2);
    }
}


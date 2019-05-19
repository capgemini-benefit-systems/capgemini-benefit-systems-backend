package com.app.controller;

import com.app.model.*;
import com.app.model.dao.*;
import com.app.model.Project;
import com.app.model.dto.ActivityDto;
import com.app.model.dto.ActivityResultDto;
import com.app.model.dto.UserDto;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityDao activityDao;
    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ActivityResultDao activityResultDao;

    @Autowired
    public ActivityController(ActivityDao activityDao, UserDao userDao, ProjectDao projectDao,
                              ActivityResultDao activityResultDao) {
        this.activityDao = activityDao;
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.activityResultDao = activityResultDao;
    }

    @PostMapping("/add")
    public ActivityDto addActivityPost(@RequestBody ActivityDto activityDto) {
        Activity activity = ActivityDto.getActivityByActivityDto(activityDto);
        return ActivityDto.getActivityDtoByActivity(activityDao.insert(activity));
    }

    @GetMapping("/all")
    public List<ActivityDto> findAll() {
        List<Activity> activities = activityDao.findAll();
        return activities
                .stream()
                .map(ActivityDto::getActivityDtoByActivity)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {

        var imgFile = new ClassPathResource(activityDao.getPhotoPathByActivityId(id));
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @GetMapping("/{id}/activities")
    public List<ActivityDto> getActivitiesByProjectId(@PathVariable Long id) {
        List<Activity> activities = activityDao.getActivitiesByProjectId(id);
        return activities
                .stream()
                .map(ActivityDto::getActivityDtoByActivity)
                .collect(Collectors.toList());
    }


    @GetMapping("/addSamples")
    public Map<String, String> addSampleActivities() {
        List<Activity> activities = createSampleActivities();
        if (!activities.isEmpty()) {
            activities.forEach(activityDao::insert);
            return Collections.singletonMap("message", "samples added");
        } else {
            return Collections.singletonMap("message", "samples not added, Projects entity in DB is empty");
        }

    }


    @GetMapping("/addSamplesActivityResults")
    public Map<String, String> addSampleActivityResults() {

        List<ActivityResult> activityResults = createSampleActivityResults();
        if (!activityResults.isEmpty()) {
            activityResults.forEach(activityResultDao::insert);
            return Collections.singletonMap("message", "samples added");
        } else {
            return Collections.singletonMap("message", "samples not added, Activities and/or Users entity in DB is empty");
        }

    }

    @PostMapping("/addActivityResult")
    public void addActivityResultPost(ActivityResultDto arDto) {
        ActivityResult ar = ActivityResultDto.activityResultDtoToActivityResult(arDto);
        activityResultDao.insert(ar);
    }

    @GetMapping("/{id}/activityResult")
    public ActivityResultDto getActivityResultByActivityId(@PathVariable Long id) {
        ActivityResult ar = activityResultDao.getActivityResultByActivity(id);
        return ActivityResultDto.activityResultToActivityResultDto(ar);
    }

    @GetMapping("/{id}/users")
    public List<UserDto> getUsersByActivityId(@PathVariable Long id) {
        List<User> users = activityResultDao.getUsersByActivityId(id);
        return users
                .stream()
                .map(UserDto::getUserDtoByUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}/finished")
    public List<ActivityDto> getFinishedActivitiesByUserId(@PathVariable Long userId) {
        List<Activity> activities = activityDao.getFinishedActivitiesByUserId(userId, true);
        return activities
                .stream()
                .map(ActivityDto::getActivityDtoByActivity)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}/notFinished")
    public List<ActivityDto> getNotFinishedActivitiesByUserId(@PathVariable Long userId) {
        List<Activity> activities = activityDao.getFinishedActivitiesByUserId(userId, false);
        return activities
                .stream()
                .map(ActivityDto::getActivityDtoByActivity)
                .collect(Collectors.toList());
    }

    private List<Activity> createSampleActivities() {
        List<Project> projects = projectDao.findAll();
        if (projects != null && !projects.isEmpty()) {
            Random random = new Random();
            Activity a1 = new Activity(null, 100L, "wyklad", "Wyklad o Javie",
                    "Litwo ojczyzno moja", "image/kon.jpg", null, null,
                    15L, 10L, projects.get(random.nextInt(projects.size())), null);
            Activity a2 = new Activity(null, 100L, "wyklad", "Wyklad o Python",
                    "Litwo ojczyzno moja", "image/kon.jpg", null, null,
                    15L, 10L, projects.get(random.nextInt(projects.size())), null);
            Activity a3 = new Activity(null, 100L, "wyklad", "Wyklad o C#",
                    "Litwo ojczyzno moja", "image/kon.jpg", null, null,
                    15L, 10L, projects.get(random.nextInt(projects.size())), null);
            return Arrays.asList(a1, a2, a3);
        }
        return new ArrayList<>();

    }

    private List<ActivityResult> createSampleActivityResults() {

        List<Activity> activities = activityDao.findAll();
        List<User> users = userDao.findAll();
        if (activities != null && !activities.isEmpty() && users != null && !users.isEmpty()) {
            Random random = new Random();

            Activity a2 = activities.get(random.nextInt(activities.size()));
            User u2 = users.get(random.nextInt(users.size()));
            ActivityResultId activityResultId2 = new ActivityResultId(a2.getId(), u2.getId());

            Activity a1 = activities.get(random.nextInt(activities.size()));
            User u1 = users.get(random.nextInt(users.size()));
            ActivityResultId activityResultId1 = new ActivityResultId(a1.getId(), u1.getId());

            ActivityResult ar1 = new ActivityResult(activityResultId1, a1, u1, LocalDate.now());
            ActivityResult ar2 = new ActivityResult(activityResultId2, a2, u2, null);

            return Arrays.asList(ar1, ar2);
        }

        return new ArrayList<>();
    }
}


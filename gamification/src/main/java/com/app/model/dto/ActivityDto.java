package com.app.model.dto;


import com.app.model.Activity;
import com.app.model.Project;
import com.app.model.dao.ActivityDao;
import com.app.model.dao.ProjectDao;
import com.app.model.dao.ProjectDaoImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDto {

    private Long id;
    private Long points;
    private String type;
    private String name;
    private String description;
    private String photo;
    private LocalDate startingDate;
    private LocalDate finishDate;
    private Long maxUsers;
    private Long actualUsers;
    private Long projectId;

    public static ActivityDto getActivityDtoByActivity(Activity modelActivity){
        return ActivityDto.builder()
                .id(modelActivity.getId())
                .points(modelActivity.getPoints())
                .type(modelActivity.getType())
                .name(modelActivity.getName())
                .description(modelActivity.getDescription())
                .photo(modelActivity.getPhoto())
                .startingDate(modelActivity.getStartingDate())
                .finishDate(modelActivity.getFinishDate())
                .maxUsers(modelActivity.getMaxUsers())
                .actualUsers(modelActivity.getActualUsers())
                .projectId(modelActivity.getProject() == null ? null : modelActivity.getProject().getId())
                .build();
    }

    public static Activity getActivityByActivityDto(ActivityDto activityDto){
        return Activity.builder()
                .id(activityDto.getId())
                .points(activityDto.getPoints())
                .type(activityDto.getType())
                .name(activityDto.getName())
                .description(activityDto.getDescription())
                .photo(activityDto.getPhoto())
                .startingDate(activityDto.getStartingDate())
                .finishDate(activityDto.getFinishDate())
                .maxUsers(activityDto.getMaxUsers())
                .actualUsers(activityDto.getActualUsers())
                //.project(projectDao.findById(activityDto.getProjectId()).orElseThrow(NullPointerException::new))
                .project(new Project(activityDto.projectId))
                .activityResults(new ArrayList<>())
                .build();


    }
}


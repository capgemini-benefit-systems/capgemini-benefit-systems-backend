package com.app.model.dto;

import com.app.model.Project;
import com.app.model.enums.Stage;
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
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private String photo;
    private LocalDate startingDate;
    private LocalDate finishDate;
    private Long maxUsers;
    private Long actualUsers;
    private String stage;

    public static ProjectDto getProjectDtoByProject(Project modelProject){
        return ProjectDto.builder()
                .id(modelProject.getId())
                .name(modelProject.getName())
                .description(modelProject.getDescription())
                .photo(modelProject.getPhoto())
                .startingDate(modelProject.getStartingDate())
                .finishDate(modelProject.getFinishDate())
                .maxUsers(modelProject.getMaxUsers())
                .actualUsers(modelProject.getActualUsers())
                .stage(modelProject.getStage().name())
                .build();
    }

    public static Project getProjectByProjectDto(ProjectDto projectDto){
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .photo(projectDto.getPhoto())
                .startingDate(projectDto.getStartingDate())
                .finishDate(projectDto.getFinishDate())
                .maxUsers(projectDto.getMaxUsers())
                .actualUsers(projectDto.getActualUsers())
                .stage(Stage.valueOf(projectDto.getStage()))
                .activities(new ArrayList<>())
                .build();
    }

}
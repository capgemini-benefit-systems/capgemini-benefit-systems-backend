package com.app.model.dto;

import com.app.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

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
    private Long stage;
    private Long activityId;
}
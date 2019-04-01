package com.app.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcitivityDto {

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

}


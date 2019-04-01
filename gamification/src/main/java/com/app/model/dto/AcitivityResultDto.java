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
public class AcitivityResultDto {

    private Long activityId;
    private Long userId;
    private LocalDate dateOfReceipt;

}
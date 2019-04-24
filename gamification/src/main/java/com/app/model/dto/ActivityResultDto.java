package com.app.model.dto;


import com.app.model.Activity;
import com.app.model.ActivityResult;
import com.app.model.ActivityResultId;
import com.app.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResultDto {

    private Long activityId;
    private Long userId;
    private LocalDate dateOfReceipt;

    public static ActivityResult activityResultDtoToActivityResult(ActivityResultDto arDto){
        return ActivityResult.builder()
                .id(new ActivityResultId(arDto.activityId, arDto.userId))
                .activity(new Activity(arDto.activityId))
                .user(new User(arDto.userId))
                .dateOfReceipt(arDto.dateOfReceipt)
                .build();
    }

    public static ActivityResultDto activityResultToActivityResultDto(ActivityResult ar){
        return ActivityResultDto.builder()
                .activityId(ar.getActivity().getId())
                .userId(ar.getUser().getId())
                .dateOfReceipt(ar.getDateOfReceipt())
                .build();
    }
}
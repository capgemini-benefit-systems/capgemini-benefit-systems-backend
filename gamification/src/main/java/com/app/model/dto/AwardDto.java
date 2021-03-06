package com.app.model.dto;

import com.app.model.Award;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardDto {
    private Long id;
    private String type;
    private String name;
    private String photo;
    private Long cost;

    public static AwardDto getAwardDtoByAward(Award modelAward){
        return AwardDto.builder()
                .id(modelAward.getId())
                .type(modelAward.getType())
                .name(modelAward.getName())
                .photo(modelAward.getPhoto())
                .cost(modelAward.getCost())
                .build();
    }

    public static Award getAwardByAwardDto(AwardDto awardDto){
        return Award.builder()
                .id(awardDto.getId())
                .type(awardDto.getType())
                .name(awardDto.getName())
                .photo(awardDto.getPhoto())
                .cost(awardDto.getCost())
                .transactions(new ArrayList<>())
                .build();
    }
}